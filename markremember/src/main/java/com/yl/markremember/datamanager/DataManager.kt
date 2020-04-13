package com.yl.markremember.datamanager

import com.standards.library.adapter.entity.MultiItemEntity
import com.yl.markremember.R
import com.yl.markremember.bean.MenuBean
import com.yl.markremember.common.NetConstant
import java.util.*

object DataManager {
    val homeMenuList: List<MultiItemEntity>
        get() {
            val menuBeanList = ArrayList<MultiItemEntity>()
            menuBeanList.add(MenuBean(R.drawable.ic_widget_today, NetConstant.MENU_P_CODE,51352, 0,"今天"))
            menuBeanList.add(MenuBean(R.drawable.ic_widget_today, NetConstant.MENU_P_CODE,NetConstant.LABEL_P_CODE, 1,"标签"))//表示有子集
            menuBeanList.add(MenuBean(R.drawable.ic_widget_today, NetConstant.MENU_P_CODE,NetConstant.LIST_P_CODE, 0,"收集箱"))
            menuBeanList.add(MenuBean(R.drawable.ic_widget_today, NetConstant.MENU_P_CODE,null, 4,""))
            return menuBeanList
        }
}