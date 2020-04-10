package com.yl.markremember.ui.activity

import com.standards.library.base.BasePresenter
import com.standards.library.base.BaseTitleBarActivity
import com.yl.markremember.R
import kotlinx.android.synthetic.main.activity_list_label_manager.*

/**
 * 管理清单和标签
 */
class ListLabelManagerActivity : BaseTitleBarActivity<BasePresenter<*>>() {
    val titles by lazy {
        arrayOf("普通清单","智能清单","标签")
    }
    override fun setListener() {

    }

    override fun init() {
        tablayout.setViewPager(viewpager, titles)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_list_label_manager
    }
}