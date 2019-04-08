package com.rainy.ytextviewlib;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * time:2019-04-08 18:02
 * description:
 *
 * @author yueleilei
 */
public class YFrameLayout extends FrameLayout {
    private YHelper mHelper;

    public YFrameLayout(Context context) {
        super(context);
    }

    public YFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public YFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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
