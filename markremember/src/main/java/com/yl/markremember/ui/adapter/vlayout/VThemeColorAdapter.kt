package com.yl.markremember.ui.adapter.vlayout

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.graphics.drawable.DrawableCompat
import com.alibaba.android.vlayout.LayoutHelper
import com.standards.library.adapter.BaseQuickAdapter
import com.standards.library.adapter.BaseViewHolder
import com.yl.markremember.R
import com.yl.markremember.bean.ThemeBean
import kotlinx.android.synthetic.main.activity_list_add.*

class VThemeColorAdapter(data: List<ThemeBean>, mHelper: LayoutHelper?) : BaseQuickAdapter<ThemeBean, BaseViewHolder?>(R.layout.item_change_theme_just_color, data) {

    override fun onCreateLayoutHelper(): LayoutHelper {
        return mHelper
    }

    init {
        this.mHelper = mHelper
    }

    override fun convert(helper: BaseViewHolder?, item: ThemeBean) {
        helper?.run {
            setText(R.id.tv_item_theme_color_name, item.colorName)
            setVisible(R.id.iv_lock_status, adapterPosition == 6)
            val v = getView<View>(R.id.v_bg_color)
            val up: Drawable = v!!.background
            val drawableUp: Drawable = DrawableCompat.wrap(up);
            DrawableCompat.setTint(drawableUp, Color.parseColor(item.colorPrimary));
            v.background = drawableUp;
            val visible = adapterPosition > 7
            setImgtint(getView(R.id.iv_theme_color_other_style), item.colorAccent)
            setVisible(R.id.iv_theme_color_other_style, visible)
            setVisible(R.id.iv_item_theme_level, adapterPosition == 6)
        }


    }

    fun setImgtint(img: ImageView, color: String) {
        val drawableUp: Drawable = DrawableCompat.wrap(img.drawable);
        DrawableCompat.setTint(drawableUp, Color.parseColor(color));
        img.setImageDrawable(drawableUp)
    }
}