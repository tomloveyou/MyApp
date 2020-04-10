package com.yl.markremember.ui.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.graphics.drawable.DrawableCompat
import com.yl.markremember.R

import kotlinx.android.synthetic.main.app_calendar_text_today.view.*

class CalendarTodayView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    var status = 0
    var todayName: String? = null

    init {
        attrs?.run {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CalendarTodayView)
            status = typedArray.getInteger(R.styleable.CalendarTodayView_calendar_today_select_status, 0)
            todayName = typedArray.getString(R.styleable.CalendarTodayView_calendar_today_text)
            typedArray.recycle()
        }
        LayoutInflater.from(context).inflate(R.layout.app_calendar_text_today, this, true)
        setToday(todayName)
        setViewStatus(status)

    }

    /**
     * 设置当前日期
     */
    public fun setToday(today: String?) {
        today?.run {
            tv_calendar_today_text.text = this
        }

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
        val up: Drawable = iv_calendar_today_img_bg.drawable
        val drawableUp: Drawable = DrawableCompat.wrap(up);
        DrawableCompat.setTint(drawableUp, color);
        iv_calendar_today_img_bg.setImageDrawable(drawableUp);
    }

}