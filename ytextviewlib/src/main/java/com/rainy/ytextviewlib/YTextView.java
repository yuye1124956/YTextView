package com.rainy.ytextviewlib;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * time:2019-03-20 15:10
 * description:
 * 带圆角按压等效果的TextView
 * 省去了大量shape文件
 *
 * @author yueleilei
 */
public class YTextView extends AppCompatTextView {

    private int mBackgroundNormal;
    private int mBackgroundPressed;
    private int mBackgroundSelected;
    private int mBackgroundUnEnable;

    private int mCornersRadius;
    private int mCornersLeftTop;
    private int mCornersLeftBottom;
    private int mCornersRightTop;
    private int mCornersRightBottom;

    private int mStrokeWidth;
    private int mStrokeColor;
    private int mStrokeDashGap;
    private int mStrokeDashWidth;

    private int mTextColorNormal;
    private int mTextColorPressed;
    private int mTextColorSelected;
    private int mTextColorUnEnable;

    public YTextView(Context context) {
        super(context);
    }

    public YTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public YTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.YTextView);
        mBackgroundNormal = a.getColor(R.styleable.YTextView_y_background_normal, Color.TRANSPARENT);
        mBackgroundPressed = a.getColor(R.styleable.YTextView_y_background_pressed, Color.TRANSPARENT);
        mBackgroundSelected = a.getColor(R.styleable.YTextView_y_background_selected, Color.TRANSPARENT);
        mBackgroundUnEnable = a.getColor(R.styleable.YTextView_y_background_un_enable, Color.TRANSPARENT);

        mCornersRadius = a.getDimensionPixelSize(R.styleable.YTextView_y_corners_radius, 0);
        mCornersLeftTop = a.getDimensionPixelSize(R.styleable.YTextView_y_corners_left_top, 0);
        mCornersLeftBottom = a.getDimensionPixelSize(R.styleable.YTextView_y_corners_left_bottom, 0);
        mCornersRightTop = a.getDimensionPixelSize(R.styleable.YTextView_y_corners_right_top, 0);
        mCornersRightBottom = a.getDimensionPixelSize(R.styleable.YTextView_y_corners_right_bottom, 0);

        mStrokeWidth = a.getDimensionPixelSize(R.styleable.YTextView_y_stroke_width, 0);
        mStrokeColor = a.getColor(R.styleable.YTextView_y_stroke_color, Color.TRANSPARENT);
        mStrokeDashGap = a.getDimensionPixelSize(R.styleable.YTextView_y_stroke_dash_gap, 0);
        mStrokeDashWidth = a.getDimensionPixelSize(R.styleable.YTextView_y_stroke_dash_width, 0);

        mTextColorNormal = a.getColor(R.styleable.YTextView_y_text_color_normal, Color.TRANSPARENT);
        mTextColorPressed = a.getColor(R.styleable.YTextView_y_text_color_pressed, Color.TRANSPARENT);
        mTextColorSelected = a.getColor(R.styleable.YTextView_y_text_color_selected, Color.TRANSPARENT);
        mTextColorUnEnable = a.getColor(R.styleable.YTextView_y_text_color_un_enable, Color.TRANSPARENT);
        a.recycle();
        if (getBackground() == null) {
            setBackgroundDrawable(getStateListDrawable());
        }
        if (useCustomTextColor()) {
            setTextColor(getColorStateList());
        }
    }

    private boolean useCustomTextColor() {
        return mTextColorNormal != 0 || mTextColorPressed != 0 ||
                mTextColorSelected != 0 || mTextColorUnEnable != 0;
    }

    private ColorStateList getColorStateList() {
        int pressed = android.R.attr.state_pressed;
        int enabled = android.R.attr.state_enabled;
        int selected = android.R.attr.state_selected;

        int[][] states = new int[4][];
        states[0] = new int[]{pressed};
        states[1] = new int[]{-enabled};
        states[2] = new int[]{selected};
        states[3] = new int[]{};

        int[] colors = new int[]{mTextColorPressed, mTextColorUnEnable, mTextColorSelected, mTextColorNormal};
        return new ColorStateList(states, colors);
    }

    private StateListDrawable getStateListDrawable() {
        StateListDrawable drawable = new StateListDrawable();
        int pressed = android.R.attr.state_pressed;
        int enabled = android.R.attr.state_enabled;
        int selected = android.R.attr.state_selected;

        drawable.addState(new int[]{pressed}, getDrawable(mBackgroundPressed));
        drawable.addState(new int[]{-enabled}, getDrawable(mBackgroundUnEnable));
        drawable.addState(new int[]{selected}, getDrawable(mBackgroundSelected));
        drawable.addState(new int[]{}, getDrawable(mBackgroundNormal));
        return drawable;
    }

    private Drawable getDrawable(int color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(color);
        setBorder(gradientDrawable);
        setRadius(gradientDrawable);
        return gradientDrawable;
    }

    private void setRadius(GradientDrawable gradientDrawable) {
        if (mCornersRadius != 0) {
            gradientDrawable.setCornerRadius(mCornersRadius);
        } else {
            //1、2两个参数表示左上角，3、4表示右上角，5、6表示右下角，7、8表示左下角
            gradientDrawable.setCornerRadii(
                    new float[]{
                            mCornersLeftTop, mCornersLeftTop,
                            mCornersRightTop, mCornersRightTop,
                            mCornersRightBottom, mCornersRightBottom,
                            mCornersLeftBottom, mCornersLeftBottom
                    }
            );
        }
    }

    private void setBorder(GradientDrawable gradientDrawable) {
        gradientDrawable.setStroke(mStrokeWidth, mStrokeColor, mStrokeDashWidth, mStrokeDashGap);
    }
}
