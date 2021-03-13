package com.example.myapplication.Component;

import android.support.v7.widget.RecyclerView;

public class MaxRecyclerView extends RecyclerView {
    public MaxRecyclerView(android.content.Context context, android.util.AttributeSet attrs){
        super(context, attrs);
        setNestedScrollingEnabled(false);
    }
    public MaxRecyclerView(android.content.Context context){
        super(context);
        setNestedScrollingEnabled(false);
    }
    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
