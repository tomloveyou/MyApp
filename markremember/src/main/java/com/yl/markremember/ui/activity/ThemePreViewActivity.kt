package com.yl.markremember.ui.activity

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.graphics.drawable.DrawableCompat
import com.standards.library.base.BasePresenter
import com.yl.markremember.R
import com.yl.markremember.base.MarkBaseActivity
import com.yl.markremember.bean.ThemeBean
import com.yl.markremember.common.NetConstant
import com.yl.markremember.event.MsgEvent
import kotlinx.android.synthetic.main.activity_theme_preview.*
import org.greenrobot.eventbus.EventBus
import skin.support.SkinCompatManager
import skin.support.content.res.ColorState
import skin.support.content.res.SkinCompatUserThemeManager

class ThemePreViewActivity  : MarkBaseActivity<BasePresenter<*>>() {
    private val themeBean by lazy {
        intent.extras?.getSerializable(NetConstant.KEY_THEME_DATA) as ThemeBean
    }
    override fun setListener() {
        tv_bottom_start_use.setOnClickListener {
            SkinCompatUserThemeManager.get().addColorState(R.color.colorPrimary, themeBean.colorPrimary)
            // 如果用户已经修改了colorPrimary 和 text_color，那么把navigation也修改掉。
            // 如果用户已经修改了colorPrimary 和 text_color，那么把navigation也修改掉。
            if (SkinCompatUserThemeManager.get().getColorState(R.color.colorPrimary) != null
                    && SkinCompatUserThemeManager.get().getColorState(R.color.black) != null) {
                SkinCompatUserThemeManager.get().addColorState(
                        R.color.navigation_item_tint, ColorState.ColorBuilder()
                        .setColorSelected(this@ThemePreViewActivity, R.color.colorPrimary)
                        .setColorPressed(this@ThemePreViewActivity, R.color.colorPrimary)
                        .setColorChecked(this@ThemePreViewActivity, R.color.colorPrimary)
                        .setColorDefault(this@ThemePreViewActivity, R.color.black)
                        .build())
            } else if (SkinCompatUserThemeManager.get().getColorState(R.color.navigation_item_tint) != null) {
                // 如果navigation_item_tint 依赖的颜色用户未设置，则删除。
                SkinCompatUserThemeManager.get().removeColorState(R.color.navigation_item_tint)
            }
            EventBus.getDefault().post(MsgEvent<ThemeBean>(NetConstant.KEY_HOME_HAS_CHANGE_THEME,"1",themeBean))
            SkinCompatManager.getInstance().notifyUpdateSkin()
        }
    }

    /**
     * 初始化主题数据
     */
    override fun init() {
        ll_theme_preview_head.setBackgroundColor(Color.parseColor(themeBean.colorPrimary))
        tv_bottom_start_use.setBackgroundColor(Color.parseColor(themeBean.colorPrimary))
        fab.setBackgroundColor(Color.parseColor(themeBean.colorPrimary))
        /*初始化fabButton颜色*/
        val up: Drawable = fab.background
        val drawableUp: Drawable = DrawableCompat.wrap(up);
        DrawableCompat.setTint(drawableUp, Color.parseColor(themeBean.colorPrimary));
        fab.background=drawableUp;
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_theme_preview
    }
}