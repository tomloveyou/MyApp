package com.yl.markremember.base

import com.standards.library.base.App
import skin.support.SkinCompatManager
import skin.support.app.SkinCardViewInflater
import skin.support.design.app.SkinMaterialViewInflater


class MarkApplication :App(){
    override fun onCreate() {
        super.onCreate()
        SkinCompatManager.withoutActivity(this) // 基础控件换肤初始化
                .addInflater(SkinMaterialViewInflater()) // material design 控件换肤初始化[可选]
                .addInflater(SkinCardViewInflater()) // CardView v7 控件换肤初始化[可选]
                .setSkinStatusBarColorEnable(false) // 关闭状态栏换肤，默认打开[可选]
                .setSkinWindowBackgroundEnable(false) // 关闭windowBackground换肤，默认打开[可选]
                .loadSkin()
    }

}