package com.rainy.ytextviewlib;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * time:2019-04-08 18:02
 * description:
 *
 * @author yueleilei
 */
public class YRelativeLayout extends RelativeLayout {
    private YHelper mHelper;

    public YRelativeLayout(Context context) {
        super(context);
    }

    public YRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public YRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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
