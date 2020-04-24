package com.yl.markremember.ui.activity

import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.SPUtils
import com.standards.library.base.BasePresenter
import com.standards.library.base.BaseTitleBarActivity
import com.yl.markremember.R
import com.yl.markremember.common.NetConstant
import com.yl.markremember.event.MsgEvent
import com.yl.markremember.ui.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_tab_control.*
import org.greenrobot.eventbus.EventBus

class TabControlActivity : BaseTitleBarActivity<BasePresenter<*>>() {
    private lateinit var homeViewModel: HomeViewModel
    override fun setListener() {
        sw_calendar.setOnCheckedChangeListener { buttonView, isChecked ->
            EventBus.getDefault().post(MsgEvent(NetConstant.KEY_HOME_HAS_CALENDER_TAB,null,isChecked))
            SPUtils.getInstance().put(NetConstant.KEY_HOME_HAS_CALENDER_TAB,isChecked)
           // homeViewModel.switchCalendarTab(isChecked)
        }
        sw_countdown.setOnCheckedChangeListener { buttonView, isChecked ->
            EventBus.getDefault().post(MsgEvent(NetConstant.KEY_HOME_HAS_COUNTDOWN_TAB,null,isChecked))
            SPUtils.getInstance().put(NetConstant.KEY_HOME_HAS_COUNTDOWN_TAB,isChecked)
           // homeViewModel.switchCountdownTab(isChecked)
        }
        sw_sign.setOnCheckedChangeListener { buttonView, isChecked ->
            EventBus.getDefault().post(MsgEvent(NetConstant.KEY_HOME_HAS_SIGN_TAB,null,isChecked))
            SPUtils.getInstance().put(NetConstant.KEY_HOME_HAS_SIGN_TAB,isChecked)
           // homeViewModel.switchSignTab(isChecked)
        }
        sw_setting.setOnCheckedChangeListener { buttonView, isChecked ->
            EventBus.getDefault().post(MsgEvent(NetConstant.KEY_HOME_HAS_SETTING_TAB,null,isChecked))
            SPUtils.getInstance().put(NetConstant.KEY_HOME_HAS_SETTING_TAB,isChecked)
           // homeViewModel.switchSignTab(isChecked)
        }
    }

    override fun init() {
        homeViewModel= ViewModelProvider(this).get(HomeViewModel::class.java)
        sw_calendar.isChecked=SPUtils.getInstance().getBoolean(NetConstant.KEY_HOME_HAS_CALENDER_TAB,true)
        sw_countdown.isChecked=SPUtils.getInstance().getBoolean(NetConstant.KEY_HOME_HAS_COUNTDOWN_TAB)
        sw_sign.isChecked=SPUtils.getInstance().getBoolean(NetConstant.KEY_HOME_HAS_SIGN_TAB)
        sw_setting.isChecked=SPUtils.getInstance().getBoolean(NetConstant.KEY_HOME_HAS_SETTING_TAB)

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_tab_control
    }
}