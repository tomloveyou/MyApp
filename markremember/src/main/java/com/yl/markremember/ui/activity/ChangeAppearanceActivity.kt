package com.yl.markremember.ui.activity

import androidx.fragment.app.Fragment
import com.standards.library.base.BaseFuncActivity
import com.standards.library.base.BasePresenter
import com.standards.library.base.BaseTitleBarActivity
import com.yl.markremember.R
import com.yl.markremember.base.MarkBaseActivity
import com.yl.markremember.ui.fragment.appearance.ChangeFontSizeFragment
import com.yl.markremember.ui.fragment.appearance.ChangeThemeFragment
import kotlinx.android.synthetic.main.activity_change_theme.*

/**
 * 改变app外观
 */
class ChangeAppearanceActivity : MarkBaseActivity<BasePresenter<*>>() {
    override fun setListener() {
        rl_left_top.setOnClickListener {
            finish()
        }
    }

    override fun init() {
        tablayout.setViewPager(viewpager, arrayOf("主题","字体大小"),this, arrayListOf(ChangeThemeFragment(),ChangeFontSizeFragment()))
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_change_theme
    }
}