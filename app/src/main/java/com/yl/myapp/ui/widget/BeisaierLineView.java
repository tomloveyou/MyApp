package com.yl.myapp.ui.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

import com.luck.picture.lib.tools.ScreenUtils;

import com.standards.library.util.Util;
import com.standards.library.util.Utils;
import com.yl.myapp.R;

public class BeisaierLineView extends View {
    private Paint paint = new Paint();
    private float s_h = 0;
    private float s_w = 0;
    private float xoffset = 20;
    private float start_x=0;
    private float start_y=0;
    private OverScroller mScroller;
    public BeisaierLineView(Context context) {
        super(context);
    }

    public BeisaierLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BeisaierLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        s_h = displayMetrics.heightPixels;
        s_w = displayMetrics.widthPixels;
        mScroller = new OverScroller(getContext());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        //渐变色1
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(20);
        paint.setColor(getResources().getColor(R.color.yellow));
        path.lineTo((s_w / 2) - 25, s_h / 2);
        path.lineTo((s_w / 2) + 25, s_h / 2);
        path.lineTo((s_w / 2) + 25, (float) ((s_h / 2) - 12.5));
        path.lineTo((s_w / 2), (s_h / 2) - 25);
        path.lineTo((s_w / 2) - 25, (float) ((s_h / 2) - 12.5));
        path.lineTo((s_w / 2) - 25, s_h / 2);
        canvas.drawPath(path, paint);
        paint.setTextSize(40);

        canvas.drawText("A", (s_w / 2) - 200, s_h / 2, paint);
        canvas.drawText("B", (s_w / 2) + 200, s_h / 2, paint);
        canvas.drawText("C", (s_w / 2) + 200, (s_h / 2) - 100, paint);
        canvas.drawText("D", (s_w / 2), (s_h / 2) - 200, paint);
        canvas.drawText("E", (s_w / 2) - 200, (s_h / 2) - 100, paint);
        paint.setStrokeWidth(8);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(20);
        for (int i = 0; i < s_w/15; i++) {
            ddd(canvas, i * 1000 + "");
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                start_x=event.getX();
               // start_y=event.getY();
                break;
          case  MotionEvent.ACTION_MOVE:
              ValueAnimator animator = new ValueAnimator();
              animator.setFloatValues(start_x, event.getX());
              animator.setDuration(1000);

              animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                  @Override
                  public void onAnimationUpdate(ValueAnimator animation) {
//                      xoffset = (float) animation.getAnimatedValue();
//                      invalidate();
                  }
              });
              animator.start();
            break;
        }

        return super.onTouchEvent(event);
    }

    private void ddd(Canvas canvas, String text) {
        canvas.drawLine((xoffset / 2), (s_h / 2) - 250, (xoffset / 2), (s_h / 2) - 300, paint);

        canvas.drawLine((xoffset / 2) + 20, (s_h / 2) - 265, (xoffset / 2) + 20, (s_h / 2) - 285, paint);
        paint.setColor(getResources().getColor(R.color.gray));
        canvas.drawLine((xoffset / 2) + 40, (s_h / 2) - 265, (xoffset / 2) + 40, (s_h / 2) - 285, paint);
        canvas.drawLine((xoffset / 2) + 60, (s_h / 2) - 265, (xoffset / 2) + 60, (s_h / 2) - 285, paint);
        paint.setColor(getResources().getColor(R.color.yellow));
        canvas.drawLine((xoffset / 2) + 80, (s_h / 2) - 265, (xoffset / 2) + 80, (s_h / 2) - 285, paint);
        canvas.drawText(text + "元", (xoffset / 2), (s_h / 2) - 340, paint);
        xoffset+=200;
    }
}
