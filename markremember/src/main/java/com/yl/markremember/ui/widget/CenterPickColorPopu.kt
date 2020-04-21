package com.yl.markremember.ui.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.lxj.xpopup.core.CenterPopupView
import com.standards.library.adapter.BaseQuickAdapter
import com.standards.library.adapter.BaseViewHolder
import com.standards.library.widget.EaseImageView
import com.yl.markremember.R
import com.yl.markremember.bean.ColorBean
import kotlinx.android.synthetic.main.activity_list_add.*
import kotlinx.android.synthetic.main.popu_color_pick.view.*

class CenterPickColorPopu(context: Context) : CenterPopupView(context) {
    private var adapter: BaseQuickAdapter<ColorBean, BaseViewHolder>? = null
    private var isShowMoreColor:Boolean=false;
    private var selectPosition:Int=-1;
    private val datas: MutableList<ColorBean> by lazy {
        ArrayList<ColorBean>()
    }

    override fun getImplLayoutId(): Int {
        return R.layout.popu_color_pick
    }

    override fun onCreate() {
        super.onCreate()
        for (index in 0..9) {
            val tvId: Int = context.resources.getIdentifier("color$index", "arrays", context.getPackageName())
            var colors = context.resources.getIntArray(tvId)
            var colorBean = ColorBean(colors)
            datas.add(colorBean)
        }
        val layoutManager=LinearLayoutManager(context)
        layoutManager.orientation=LinearLayoutManager.VERTICAL
        recyclerView.layoutManager=layoutManager
        adapter = object : BaseQuickAdapter<ColorBean, BaseViewHolder>(R.layout.item_color_pick,datas) {
            override fun convert(helper: BaseViewHolder?, item: ColorBean?) {
                helper?.setGone(R.id.ll_color_more,isShowMoreColor)
                item?.run {
                    for (index in 0..4) {
                        val IvId: Int = context.resources.getIdentifier("eiv_color_img$index", "id", context.getPackageName())
                        val statusIvId: Int = context.resources.getIdentifier("eiv_color_select_status_img$index", "id", context.getPackageName())
                        var eimg=helper?.getView<EaseImageView>(IvId)
                        val up: Drawable = eimg!!.drawable
                        val drawableUp: Drawable = DrawableCompat.wrap(up);
                        DrawableCompat.setTint(drawableUp, this.group_colors[index]);
                        eimg.setImageDrawable(drawableUp);
                        helper?.setGone(statusIvId,selectPosition!=-1&&selectPosition==helper.adapterPosition)
                    }

                }
            }
        }
        recyclerView.adapter=adapter
        rl_arrow_view.setOnClickListener {
            isShowMoreColor=!isShowMoreColor
            adapter?.notifyDataSetChanged()
        }
    }
}