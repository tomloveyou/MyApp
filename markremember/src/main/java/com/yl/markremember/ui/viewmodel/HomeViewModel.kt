package com.yl.markremember.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.blankj.utilcode.util.SPUtils
import com.huayi.parkmanager.im.db.ParkDatabase
import com.yl.markremember.common.NetConstant
import com.yl.markremember.db.model.ListInfo
import com.yl.markremember.db.repository.ListRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    // private val label_tab:MutableLiveData<Boolean>
    val calendar_tab: MutableLiveData<Boolean>
    val countdown_tab: MutableLiveData<Boolean>
    val setting_tab: MutableLiveData<Boolean>
    val sign_tab: MutableLiveData<Boolean>

    init {
        // label_tab= MutableLiveData(SPUtils.getInstance().getBoolean(NetConstant.key_home_has_label_tab))
        countdown_tab = MutableLiveData(SPUtils.getInstance().getBoolean(NetConstant.KEY_HOME_HAS_COUNTDOWN_TAB))
        setting_tab = MutableLiveData(SPUtils.getInstance().getBoolean(NetConstant.KEY_HOME_HAS_SETTING_TAB))
        sign_tab = MutableLiveData(SPUtils.getInstance().getBoolean(NetConstant.KEY_HOME_HAS_SIGN_TAB))
        calendar_tab = MutableLiveData(SPUtils.getInstance().getBoolean(NetConstant.KEY_HOME_HAS_CALENDER_TAB))
    }

    /**
     * 设置tab设置是否显示
     */
    fun switchSettingTab(boolean: Boolean) {
        setting_tab.value = boolean
        SPUtils.getInstance().put(NetConstant.KEY_HOME_HAS_COUNTDOWN_TAB, boolean)
    }

    /**
     * 设置tab番茄倒计时是否显示
     */
    fun switchCountdownTab(boolean: Boolean) {
        countdown_tab.value = boolean
        SPUtils.getInstance().put(NetConstant.KEY_HOME_HAS_SETTING_TAB, boolean)
    }

    /**
     * 设置tab签到是否显示
     */
    fun switchSignTab(boolean: Boolean) {
        sign_tab.value = boolean
        SPUtils.getInstance().put(NetConstant.KEY_HOME_HAS_SIGN_TAB, boolean)
    }

    /**
     * 设置tab番茄倒计时是否显示
     */
    fun switchCalendarTab(boolean: Boolean) {
        calendar_tab.value = boolean
        SPUtils.getInstance().put(NetConstant.KEY_HOME_HAS_CALENDER_TAB, boolean)
    }
}