package com.yconme.callphone.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by LDX on 2017/1/13.
 */

public class MyLlistview extends ListView {

    public MyLlistview(Context context) {
        super(context);
    }

    public MyLlistview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLlistview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

//    @Override
//    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
//        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //得到它的高度
        int measuredHeight = getMeasuredHeight();
        //赋值给计算出的公式
        measuredHeight = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, measuredHeight);

    }
}
