package com.yl.markremember.ui.activity

import com.standards.library.base.BasePresenter
import com.standards.library.base.BaseTitleBarActivity
import com.yl.markremember.R
import com.yl.markremember.ui.fragment.CleverListFragment
import com.yl.markremember.ui.fragment.NormalListFragment
import com.yl.markremember.ui.fragment.label.LabelFragment
import kotlinx.android.synthetic.main.activity_list_label_manager.*

/**
 * 管理清单和标签
 */
class ListLabelManagerActivity : BaseTitleBarActivity<BasePresenter<*>>() {

    override fun setListener() {

    }

    override fun init() {
        setTitle("管理清单和标签")
        tablayout.setViewPager(viewpager, arrayOf("普通清单", "智能清单", "标签"), this, arrayListOf(NormalListFragment(), CleverListFragment(), LabelFragment()))
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_list_label_manager
    }
}