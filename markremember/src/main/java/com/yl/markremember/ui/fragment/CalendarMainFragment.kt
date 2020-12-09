package com.yl.markremember.ui.fragment

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
import com.yl.markremember.R
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


            imageView2.setOnClickListener {
                if (ll_view_type_switch.getVisibility() == View.GONE) {
                    animateOpen(ll_view_type_switch);
                    //  animationIvOpen(imageView2);
                } else {
                    animateClose(ll_view_type_switch);
                    // animationIvClose(imageView2);
                }
            }
        }
        childFragmentManager.beginTransaction().add(R.id.fragment_container,CalendarListEventFragment()).commit()



        return rootView
    }


    private fun animateOpen(v: View) {
        v.visibility = View.VISIBLE;
        val animator: ValueAnimator = createDropAnimator(v, 0,
                mHiddenViewMeasuredHeight);
        animator.start();

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