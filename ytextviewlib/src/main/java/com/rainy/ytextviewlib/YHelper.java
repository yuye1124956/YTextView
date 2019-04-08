package com.rainy.ytextviewlib;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;

/**
 * time:2019-04-08 17:45
 * description:
 *
 * @author yueleilei
 */
public class YHelper {

    public int mBackgroundNormal;
    public int mBackgroundPressed;
    public int mBackgroundSelected;
    public int mBackgroundUnEnable;

    public int mCornersRadius;
    public int mCornersLeftTop;
    public int mCornersLeftBottom;
    public int mCornersRightTop;
    public int mCornersRightBottom;

    public int mStrokeWidth;
    public int mStrokeColor;
    public int mStrokeDashGap;
    public int mStrokeDashWidth;

    public int mTextColorNormal;
    public int mTextColorPressed;
    public int mTextColorSelected;
    public int mTextColorUnEnable;

    public void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.YAttrs);
        mBackgroundNormal = a.getColor(R.styleable.YAttrs_y_background_normal, Color.TRANSPARENT);
        mBackgroundPressed = a.getColor(R.styleable.YAttrs_y_background_pressed, mBackgroundNormal);
        mBackgroundSelected = a.getColor(R.styleable.YAttrs_y_background_selected, mBackgroundNormal);
        mBackgroundUnEnable = a.getColor(R.styleable.YAttrs_y_background_un_enable, mBackgroundNormal);

        mCornersRadius = a.getDimensionPixelSize(R.styleable.YAttrs_y_corners_radius, 0);
        mCornersLeftTop = a.getDimensionPixelSize(R.styleable.YAttrs_y_corners_left_top, 0);
        mCornersLeftBottom = a.getDimensionPixelSize(R.styleable.YAttrs_y_corners_left_bottom, 0);
        mCornersRightTop = a.getDimensionPixelSize(R.styleable.YAttrs_y_corners_right_top, 0);
        mCornersRightBottom = a.getDimensionPixelSize(R.styleable.YAttrs_y_corners_right_bottom, 0);

        mStrokeWidth = a.getDimensionPixelSize(R.styleable.YAttrs_y_stroke_width, 0);
        mStrokeColor = a.getColor(R.styleable.YAttrs_y_stroke_color, Color.TRANSPARENT);
        mStrokeDashGap = a.getDimensionPixelSize(R.styleable.YAttrs_y_stroke_dash_gap, 0);
        mStrokeDashWidth = a.getDimensionPixelSize(R.styleable.YAttrs_y_stroke_dash_width, 0);

        mTextColorNormal = a.getColor(R.styleable.YAttrs_y_text_color_normal, Color.TRANSPARENT);
        mTextColorPressed = a.getColor(R.styleable.YAttrs_y_text_color_pressed, mTextColorNormal);
        mTextColorSelected = a.getColor(R.styleable.YAttrs_y_text_color_selected, mTextColorNormal);
        mTextColorUnEnable = a.getColor(R.styleable.YAttrs_y_text_color_un_enable, mTextColorNormal);
        a.recycle();
    }

    public StateListDrawable getStateListDrawable() {
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

    public ColorStateList getColorStateList() {
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

    public boolean useCustomTextColor() {
        return mTextColorNormal != 0 || mTextColorPressed != 0 ||
                mTextColorSelected != 0 || mTextColorUnEnable != 0;
    }
}
