package com.yl.markremember

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import com.standards.library.adapter.BaseQuickAdapter
import com.standards.library.adapter.BaseViewHolder
import kotlinx.android.synthetic.main.fragment_calendar_main.view.*

class CalendarMainFragment : Fragment() {
    private var emptyView: View? = null
    private var mHiddenViewMeasuredHeight: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = LayoutInflater.from(this.activity).inflate(R.layout.fragment_calendar_main, null)
        emptyView = LayoutInflater.from(this.activity).inflate(R.layout.empty_layout, null)
        val layoutManager = LinearLayoutManager(this.activity);
        val mDensity: Float = resources.displayMetrics.density
        mHiddenViewMeasuredHeight = (mDensity * 56 + 0.5).toInt()
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val adapter: BaseQuickAdapter<String, BaseViewHolder> = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.cv_layout_calendar_view) {
            override fun convert(helper: BaseViewHolder?, item: String?) {


            }
        }
        adapter.isUseEmpty(true)
        adapter.emptyView = emptyView
        rootView.apply {
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
            tv_top_title.text=getMouthString(calendarView.curMonth)
            calendar_todayView.setToday(calendarView.curDay.toString())
            calendarView.setOnCalendarSelectListener(object : CalendarView.OnCalendarSelectListener {
                override fun onCalendarSelect(calendar: Calendar?, isClick: Boolean) {
                    tv_top_title.text=getMouthString(calendar!!.month)
                }

                override fun onCalendarOutOfRange(calendar: Calendar?) {

                }
            })

            calendar_todayView.setOnClickListener {
                calendarView.scrollToCalendar(calendarView.curYear, calendarView.curMonth, calendarView.curDay)

            }
            imageView2.setOnClickListener {
                if (ll_view_type_switch.getVisibility() == View.GONE) {
                    animateOpen(ll_view_type_switch);
                    animationIvOpen(imageView2);
                } else {
                    animateClose(ll_view_type_switch);
                    animationIvClose(imageView2);
                }
            }
        }



        return rootView
    }

    /**
     * 获取汉字月份
     * @param mouth 罗马数字月份
     */
    fun getMouthString(mouth: Int): String {
        return activity!!.resources.getStringArray(R.array.lunar_first_of_month)[(mouth - 1)]
    }

    private fun animateOpen(v: View) {
        v.visibility = View.VISIBLE;
        val animator: ValueAnimator = createDropAnimator(v, 0,
                mHiddenViewMeasuredHeight);
        animator.start();

    }

    private fun animationIvOpen(view: View) {
        val animation: RotateAnimation = RotateAnimation(0f, 180f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.fillAfter = true;
        animation.duration = 100;
        view.startAnimation(animation);
    }

    private fun animationIvClose(view: View) {
        val animation: RotateAnimation = RotateAnimation(180f, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.fillAfter = true;
        animation.duration = 100;
        view.startAnimation(animation);
    }

    private fun animateClose(view: View) {
        val origHeight = view.height;
        val animator: ValueAnimator = createDropAnimator(view, origHeight, 0);
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                view.visibility = View.GONE;
            }

        });
        animator.start();
    }

    private fun createDropAnimator(v: View, start: Int, end: Int): ValueAnimator {
        val animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener { animation ->
            val value: Int = animation?.animatedValue as Int;
            val layoutParams: ViewGroup.LayoutParams = v.layoutParams;
            layoutParams.height = value;
            v.layoutParams = layoutParams;
        };
        return animator;
    }


}