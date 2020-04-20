package com.yl.markremember.ui.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.FrameLayout
import androidx.core.graphics.drawable.DrawableCompat
import com.yl.markremember.R
import kotlinx.android.synthetic.main.app_calendar_item_view.view.*


class CalendarItemView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    var calendar_item_select_status = 0
    var calendar_item_text: String? = null
    var calendar_item_leftText: String? = null
    var calendar_item_textColor = 0
    var calendar_item_leftImgTintColor=0
    var calendar_item_textSize:Float = 12f
    var calendar_item_leftImgSrc = 0
    var calendar_item_leftImgPadding:Int = 0
    var calendar_item_isRightImg :Boolean= false
    var calendar_item_rightImgSrc = 0
    var calendar_item_rightTextSize = 0f
    var calendar_item_rightText:String? = null
    var calendar_item_isRightImgRota:Boolean = false
    var current_slide_isopen=false
    init {
        attrs?.run {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CalendarItemView)
            calendar_item_select_status = typedArray.getInteger(R.styleable.CalendarItemView_calendar_item_select_status, 0)
            calendar_item_text = typedArray.getString(R.styleable.CalendarItemView_calendar_item_text)
            calendar_item_leftText= typedArray.getString(R.styleable.CalendarItemView_calendar_item_leftText)
            calendar_item_textColor = typedArray.getColor(R.styleable.CalendarItemView_calendar_item_textColor,resources.getColor(R.color.black))
            calendar_item_leftImgTintColor = typedArray.getColor(R.styleable.CalendarItemView_calendar_item_leftImgTintColor,resources.getColor(R.color.home_title_img_bg_color))
            calendar_item_textSize = typedArray.getDimension(R.styleable.CalendarItemView_calendar_item_textSize,12f)
            calendar_item_leftImgSrc = typedArray.getResourceId(R.styleable.CalendarItemView_calendar_item_leftImgSrc,0)
            calendar_item_leftImgPadding = typedArray.getDimensionPixelSize(R.styleable.CalendarItemView_calendar_item_leftImgPadding,0)
            calendar_item_isRightImg = typedArray.getBoolean(R.styleable.CalendarItemView_calendar_item_isRightImg,false)
            calendar_item_rightImgSrc = typedArray.getResourceId(R.styleable.CalendarItemView_calendar_item_rightImgSrc,0)
            calendar_item_rightTextSize = typedArray.getDimension(R.styleable.CalendarItemView_calendar_item_rightTextSize,12f)
            calendar_item_rightText = typedArray.getString(R.styleable.CalendarItemView_calendar_item_rightText)
            calendar_item_isRightImgRota = typedArray.getBoolean(R.styleable.CalendarItemView_calendar_item_isRightImgRota,false)
            typedArray.recycle()
        }
        LayoutInflater.from(context).inflate(R.layout.app_calendar_item_view, this, true)
        //setToday(calendar_item_text)
        calendar_item_text?.run {
            tv_calendar_center_text.text=calendar_item_text
        }
        tv_calendar_center_text.setTextColor(calendar_item_textColor)
        tv_calendar_left_text.text=calendar_item_leftText
        tv_calendar_left_text.textSize=calendar_item_textSize
        if(calendar_item_leftImgSrc!=0){
          setLeftImg(calendar_item_leftImgSrc)
        }

        if (calendar_item_leftImgPadding!=0){
            iv_calendar_left_img.setPadding(calendar_item_leftImgPadding,calendar_item_leftImgPadding,calendar_item_leftImgPadding,calendar_item_leftImgPadding)
        }
        if (calendar_item_isRightImg){
            tv_calendar_right_text.visibility=View.GONE
            tv_calendar_right_img.visibility=View.VISIBLE
            if(calendar_item_rightImgSrc!=0){
                tv_calendar_right_img.setImageResource(calendar_item_rightImgSrc)
                val up: Drawable = tv_calendar_right_img.drawable
                val drawableUp: Drawable = DrawableCompat.wrap(up);
                DrawableCompat.setTint(drawableUp, calendar_item_leftImgTintColor);
                tv_calendar_right_img.setImageDrawable(drawableUp);
            }
        }else{
            tv_calendar_right_text.visibility=View.VISIBLE
            tv_calendar_right_img.visibility=View.GONE
            tv_calendar_right_text.text=calendar_item_rightText
        }
        tv_calendar_right_text.textSize=calendar_item_rightTextSize
        setViewStatus(calendar_item_select_status)


    }
    public fun changeStatus(stat:Boolean){
        if (calendar_item_isRightImgRota){
            current_slide_isopen=stat
            if(current_slide_isopen){
                animationIvClose(tv_calendar_right_img)
            }else{
                animationIvOpen(tv_calendar_right_img)
            }
        }

    }
    private fun animationIvOpen(view: View) {
        val animation: RotateAnimation = RotateAnimation(0f, -90f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.fillAfter = true;
        animation.duration = 100;
        view.startAnimation(animation);
    }

    private fun animationIvClose(view: View) {
        val animation: RotateAnimation = RotateAnimation(-90f, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.fillAfter = true;
        animation.duration = 100;
        view.startAnimation(animation);
    }
    /**
     * 设置当前日期
     */
    public fun setToday(today: String?) {
        today?.run {
            tv_calendar_center_text.text = this
        }

    }
    public fun setLeftImg(resid:Int){
        if (resid==0)return
        iv_calendar_left_img.setImageResource(resid)
        val up: Drawable = iv_calendar_left_img.drawable
        val drawableUp: Drawable = DrawableCompat.wrap(up);
        DrawableCompat.setTint(drawableUp, calendar_item_leftImgTintColor);
        iv_calendar_left_img.setImageDrawable(drawableUp);
    }

    /**
     * 设置当前View的
     */
    public fun setViewStatus(status: Int) {
        val color = if (status == 0) {
            context.resources.getColor(R.color.home_title_img_bg_color)
        } else if (status == 1) {
            context.resources.getColor(R.color.colorPrimary)
        } else {
            context.resources.getColor(R.color.colorAccent)
        }
        val up: Drawable = iv_calendar_left_img.drawable
        val drawableUp: Drawable = DrawableCompat.wrap(up);
        DrawableCompat.setTint(drawableUp, color);
        iv_calendar_left_img.setImageDrawable(drawableUp);
    }

}