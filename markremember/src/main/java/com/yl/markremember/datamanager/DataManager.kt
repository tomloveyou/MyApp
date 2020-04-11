package com.yl.markremember.datamanager

import com.standards.library.adapter.entity.MultiItemEntity
import com.yl.markremember.R
import com.yl.markremember.bean.MenuBean
import java.util.*

object DataManager {
    val homeMenuList: List<MultiItemEntity>
        get() {
            val menuBeanList = ArrayList<MultiItemEntity>()
            menuBeanList.add(MenuBean(R.drawable.ic_widget_today, 1, "今天"))
            menuBeanList.add(MenuBean(R.drawable.ic_widget_today, 2, "标签"))
            menuBeanList.add(MenuBean(R.drawable.ic_widget_today, 3, "收集箱"))
            return menuBeanList
        }
}