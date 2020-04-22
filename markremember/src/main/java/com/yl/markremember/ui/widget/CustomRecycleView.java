package com.yl.markremember.ui.widget;

import android.content.Context;

import android.util.AttributeSet;

import androidx.recyclerview.widget.RecyclerView;

public class CustomRecycleView extends RecyclerView {

    public CustomRecycleView(Context context) {
        super(context);
    }

    public CustomRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecycleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
