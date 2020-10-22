package com.yl.markremember.ui.adapter.vlayout

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.graphics.drawable.DrawableCompat
import com.alibaba.android.vlayout.LayoutHelper
import com.standards.library.adapter.BaseQuickAdapter
import com.standards.library.adapter.BaseViewHolder
import com.standards.library.widget.EaseImageView
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
            val v = getView<EaseImageView>(R.id.v_bg_color)
            v.setImageDrawable(ColorDrawable(Color.parseColor(item.colorPrimary)))
            setVisible(R.id.iv_theme_color_other_blue_style, adapterPosition==8)
            setVisible(R.id.iv_theme_color_other_yellow_style, adapterPosition==9)
            setGone(R.id.iv_item_theme_level, adapterPosition == 6)
            addOnClickListener(R.id.v_bg_color)
        }


    }


}