package com.rainy.ytextviewlib;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * time:2019-04-08 18:02
 * description:
 *
 * @author yueleilei
 */
public class YLinearLayout extends LinearLayout {
    private YHelper mHelper;

    public YLinearLayout(Context context) {
        super(context);
    }

    public YLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public YLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mHelper = new YHelper();
        mHelper.initAttrs(context, attrs);
        if (getBackground() == null) {
            setBackgroundDrawable(mHelper.getStateListDrawable());
        }
    }
}
