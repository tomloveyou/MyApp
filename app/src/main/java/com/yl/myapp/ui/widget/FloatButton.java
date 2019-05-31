package com.yl.myapp.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.standards.library.R;


public class FloatButton extends View {
    private Paint paint = new Paint();
    private Paint paint_line = new Paint();
    private float s_h = 0;
    private float s_w = 0;
    private static final float rad = 300;
    private float statusBarHeight = 0;
    private Canvas canvas2;
    private float aradio = 250;
    float bradio = 250;
    float dradio = 250;
    float eradio = 250;
    float fradio = 250;
    float gradio = 250;
    float hradio = 250;

    public FloatButton(Context context) {
        super(context);
    }

    /**
     * convert px to its equivalent dp
     * <p>
     * 将px转换为与之相等的dp
     */
    public int px2dp(float pxValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * convert dp to its equivalent px
     * <p>
     * 将dp转换为与之相等的px
     */
    public int dp2px(float dipValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public FloatButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        s_h = displayMetrics.heightPixels;
        s_w = displayMetrics.widthPixels;
        statusBarHeight = getStatusBarHeight();
        Logger.d("屏幕高：" + s_h + "屏幕宽：" + s_w);
    }

    public FloatButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(getContext().getResources().getColor(android.R.color.holo_green_dark));
        paint.setAlpha(100);
        canvas2 = canvas;
        //canvas2.drawCircle(s_w / 2, s_h / 2, rad, paint);
        for (int i = 1; i <= 6; i++) {

            draKK(canvas2, (rad / 6) * i);
        }
        ddText(canvas,rad+(rad/12));
        ddd(canvas, aradio, bradio, dradio, eradio, fradio, gradio, hradio);

//        Logger.d("x：" + x + ",y：" + y);
    }
    private void ddText(Canvas canvas,float radio){
        paint_line.setColor(getContext().getResources().getColor(R.color.white));
        paint_line.setTextSize(40);
        canvas.drawText("金",s_w / 2, s_h / 2 - dp2px(radio / 2),paint_line);
        canvas.drawText("木",(float) (s_w / 2 + Math.sin(Math.toRadians(360 / 7)) * dp2px(radio / 2)), (float) (s_h / 2 - Math.abs(Math.cos(Math.toRadians(360 / 7))) * dp2px(radio / 2)),paint_line);

//
//        canvas.drawText("土",s_w / 2-padd, s_h / 2 - dp2px(radio / 2)-padd,paint_line);
//        canvas.drawText("山",s_w / 2-padd, s_h / 2 - dp2px(radio / 2)-padd,paint_line);
//        canvas.drawText("风",s_w / 2-padd, s_h / 2 - dp2px(radio / 2)-padd,paint_line);
        double age = 180 - ((360 / 7) * 2);
        canvas.drawText("水",(float) (s_w / 2 + (Math.sin(Math.toRadians(age)) * dp2px(radio / 2))), (float) (s_h / 2 + Math.abs(Math.cos(Math.toRadians(age))) * dp2px(radio / 2)),paint_line);
        // canvas.drawLine(((float) (s_w / 2 - Math.sin(Math.toRadians(360 / 7)) * dp2px(cradio / 2))), (float) (s_h / 2 - Math.abs(Math.cos(Math.toRadians(360 / 7))) * dp2px(cradio / 2)), (float) (s_w / 2 + (Math.sin(Math.toRadians(age)) * dp2px(dradio / 2))), (float) (s_h / 2 + Math.abs(Math.cos(Math.toRadians(age))) * dp2px(dradio / 2)), paint_line);
        double age2 = 180 - ((360 / 7) * 3);
        canvas.drawText("火",(float) (s_w / 2 + (Math.sin(Math.toRadians(age2)) * dp2px(radio / 2))), (float) (s_h / 2 + Math.abs(Math.cos(Math.toRadians(age2))) * dp2px(radio / 2)),paint_line);
        canvas.drawText("土",(float) (s_w / 2 - (Math.sin(Math.toRadians(age2)) * dp2px(radio / 2))), (float) (s_h / 2 + Math.abs(Math.cos(Math.toRadians(age2))) * dp2px(radio / 2)),paint_line);
        canvas.drawText("山",(float) (s_w / 2 - (Math.sin(Math.toRadians(age)) * dp2px(radio / 2))), (float) (s_h / 2 + Math.abs(Math.cos(Math.toRadians(age))) * dp2px(radio / 2)),paint_line);
        canvas.drawText("风",((float) (s_w / 2 - Math.sin(Math.toRadians(360 / 7)) * dp2px(radio / 2))), (float) (s_h / 2 - Math.abs(Math.cos(Math.toRadians(360 / 7))) * dp2px(radio / 2)),paint_line);
    }

    private void draKK(Canvas canvas, float radio) {
        paint_line.setColor(getContext().getResources().getColor(R.color.white));
        Path path = new Path();
        canvas.drawLine(s_w / 2, s_h / 2, s_w / 2, s_h / 2 - dp2px(radio / 2), paint_line);
        path.lineTo(s_w / 2, s_h / 2 - dp2px(radio / 2));

        canvas.drawLine(s_w / 2, s_h / 2, (float) (s_w / 2 + Math.sin(Math.toRadians(360 / 7)) * dp2px(radio / 2)), (float) (s_h / 2 - Math.abs(Math.cos(Math.toRadians(360 / 7))) * dp2px(radio / 2)), paint_line);
        path.lineTo((float) (s_w / 2 + Math.sin(Math.toRadians(360 / 7)) * dp2px(radio / 2)), (float) (s_h / 2 - Math.abs(Math.cos(Math.toRadians(360 / 7))) * dp2px(radio / 2)));

        canvas.drawLine(s_w / 2, s_h / 2, ((float) (s_w / 2 - Math.sin(Math.toRadians(360 / 7)) * dp2px(radio / 2))), (float) (s_h / 2 - Math.abs(Math.cos(Math.toRadians(360 / 7))) * dp2px(radio / 2)), paint_line);
        double age = 180 - ((360 / 7) * 2);
//        paint_line.setColor(getContext().getResources().getColor(android.R.color.black));
        float x = (float) (s_w / 2 + (Math.sin(Math.toRadians(age)) * dp2px(radio / 2)));
        float y = (float) (s_h / 2 + Math.abs(Math.cos(Math.toRadians(age))) * dp2px(radio / 2));

        canvas.drawLine(s_w / 2, s_h / 2, x, y, paint_line);
        path.lineTo(x, y);
        x = (float) (s_w / 2 - (Math.sin(Math.toRadians(age)) * dp2px(radio / 2)));
        canvas.drawLine(s_w / 2, s_h / 2, x, y, paint_line);
        double age2 = 180 - ((360 / 7) * 3);
        x = (float) (s_w / 2 + (Math.sin(Math.toRadians(age2)) * dp2px(radio / 2)));
        y = (float) (s_h / 2 + Math.abs(Math.cos(Math.toRadians(age2))) * dp2px(radio / 2));
        canvas.drawLine(s_w / 2, s_h / 2, x, y, paint_line);
        path.lineTo(x, y);
        x = (float) (s_w / 2 - (Math.sin(Math.toRadians(age2)) * dp2px(radio / 2)));
        canvas.drawLine(s_w / 2, s_h / 2, x, y, paint_line);
        path.lineTo(x, y);
        path.lineTo((float) (s_w / 2 - (Math.sin(Math.toRadians(age)) * dp2px(radio / 2))), (float) (s_h / 2 + Math.abs(Math.cos(Math.toRadians(age))) * dp2px(radio / 2)));
        path.lineTo(((float) (s_w / 2 - Math.sin(Math.toRadians(360 / 7)) * dp2px(radio / 2))), (float) (s_h / 2 - Math.abs(Math.cos(Math.toRadians(360 / 7))) * dp2px(radio / 2)));
        path.lineTo(s_w / 2, s_h / 2 - dp2px(radio / 2));
        canvas.drawPath(path, paint);

    }

    public void setValues(float aradio, float bradio, float dradio, float eradio, float fradio, float gradio, float hradio) {
        this.aradio = aradio;
        this.bradio = bradio;
        this.dradio = dradio;
        this.eradio = eradio;
        this.fradio = fradio;
        this.gradio = gradio;
        this.hradio = hradio;
        invalidate();
    }

    public void ddd(Canvas canvas, float aradio, float bradio, float dradio, float eradio, float fradio, float gradio, float hradio) {
        if (canvas == null) {
            canvas = canvas2;
        }
        paint_line.setColor(getContext().getResources().getColor(android.R.color.holo_orange_light));

        canvas.drawLine(s_w / 2, s_h / 2 - dp2px(aradio / 2), (float) (s_w / 2 + Math.sin(Math.toRadians(360 / 7)) * dp2px(bradio / 2)), (float) (s_h / 2 - Math.abs(Math.cos(Math.toRadians(360 / 7))) * dp2px(bradio / 2)), paint_line);
        //   canvas.drawLine((float) (s_w / 2 + Math.sin(Math.toRadians(360 / 7)) * dp2px(bradio / 2)), (float) (s_h / 2 - Math.abs(Math.cos(Math.toRadians(360 / 7))) * dp2px(bradio / 2)), ((float) (s_w / 2 - Math.sin(Math.toRadians(360 / 7)) * dp2px(cradio / 2))), (float) (s_h / 2 - Math.abs(Math.cos(Math.toRadians(360 / 7))) * dp2px(cradio / 2)), paint_line);
        double age = 180 - ((360 / 7) * 2);
        canvas.drawLine((float) (s_w / 2 + Math.sin(Math.toRadians(360 / 7)) * dp2px(bradio / 2)), (float) (s_h / 2 - Math.abs(Math.cos(Math.toRadians(360 / 7))) * dp2px(bradio / 2)), (float) (s_w / 2 + (Math.sin(Math.toRadians(age)) * dp2px(dradio / 2))), (float) (s_h / 2 + Math.abs(Math.cos(Math.toRadians(age))) * dp2px(dradio / 2)), paint_line);
        // canvas.drawLine(((float) (s_w / 2 - Math.sin(Math.toRadians(360 / 7)) * dp2px(cradio / 2))), (float) (s_h / 2 - Math.abs(Math.cos(Math.toRadians(360 / 7))) * dp2px(cradio / 2)), (float) (s_w / 2 + (Math.sin(Math.toRadians(age)) * dp2px(dradio / 2))), (float) (s_h / 2 + Math.abs(Math.cos(Math.toRadians(age))) * dp2px(dradio / 2)), paint_line);
        double age2 = 180 - ((360 / 7) * 3);
        canvas.drawLine((float) (s_w / 2 + (Math.sin(Math.toRadians(age)) * dp2px(dradio / 2))), (float) (s_h / 2 + Math.abs(Math.cos(Math.toRadians(age))) * dp2px(dradio / 2)), (float) (s_w / 2 + (Math.sin(Math.toRadians(age2)) * dp2px(eradio / 2))), (float) (s_h / 2 + Math.abs(Math.cos(Math.toRadians(age2))) * dp2px(eradio / 2)), paint_line);
        canvas.drawLine((float) (s_w / 2 + (Math.sin(Math.toRadians(age2)) * dp2px(eradio / 2))), (float) (s_h / 2 + Math.abs(Math.cos(Math.toRadians(age2))) * dp2px(eradio / 2)), (float) (s_w / 2 - (Math.sin(Math.toRadians(age2)) * dp2px(fradio / 2))), (float) (s_h / 2 + Math.abs(Math.cos(Math.toRadians(age2))) * dp2px(fradio / 2)), paint_line);
        canvas.drawLine((float) (s_w / 2 - (Math.sin(Math.toRadians(age2)) * dp2px(fradio / 2))), (float) (s_h / 2 + Math.abs(Math.cos(Math.toRadians(age2))) * dp2px(fradio / 2)), (float) (s_w / 2 - (Math.sin(Math.toRadians(age)) * dp2px(gradio / 2))), (float) (s_h / 2 + Math.abs(Math.cos(Math.toRadians(age))) * dp2px(gradio / 2)), paint_line);
        canvas.drawLine((float) (s_w / 2 - (Math.sin(Math.toRadians(age)) * dp2px(gradio / 2))), (float) (s_h / 2 + Math.abs(Math.cos(Math.toRadians(age))) * dp2px(gradio / 2)), ((float) (s_w / 2 - Math.sin(Math.toRadians(360 / 7)) * dp2px(hradio / 2))), (float) (s_h / 2 - Math.abs(Math.cos(Math.toRadians(360 / 7))) * dp2px(hradio / 2)), paint_line);
        canvas.drawLine(((float) (s_w / 2 - Math.sin(Math.toRadians(360 / 7)) * dp2px(hradio / 2))), (float) (s_h / 2 - Math.abs(Math.cos(Math.toRadians(360 / 7))) * dp2px(hradio / 2)), s_w / 2, s_h / 2 - dp2px(aradio / 2), paint_line);
    }


    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


}
