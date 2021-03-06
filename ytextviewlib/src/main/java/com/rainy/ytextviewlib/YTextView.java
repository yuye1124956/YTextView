package com.rainy.ytextviewlib;

import android.content.Context;
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
    private YHelper mHelper;

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
        mHelper = new YHelper();
        mHelper.initAttrs(context, attrs);
        if (getBackground() == null) {
            setBackgroundDrawable(mHelper.getStateListDrawable());
        }
        if (mHelper.useCustomTextColor()) {
            setTextColor(mHelper.getColorStateList());
        }
    }
}
