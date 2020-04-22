package com.yl.markremember.ui.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.lxj.xpopup.core.CenterPopupView
import com.standards.library.adapter.BaseQuickAdapter
import com.standards.library.adapter.BaseViewHolder
import com.standards.library.widget.EaseImageView
import com.yl.markremember.R
import com.yl.markremember.bean.ColorBean
import kotlinx.android.synthetic.main.activity_list_add.*
import kotlinx.android.synthetic.main.popu_color_pick.view.*
import kotlinx.android.synthetic.main.popu_color_pick_head.view.*

class CenterPickColorPopu(context: Context, private val colorPickCallBack: ColorPickCallBack?) : CenterPopupView(context) {
    private var adapter: BaseQuickAdapter<ColorBean, BaseViewHolder>? = null
    private var isShowMoreColor: Boolean = false;
    private  var  select_outner_position= -1;
    private  var  select_inner_position= -1;
    private val datas: MutableList<ColorBean> by lazy {
        ArrayList<ColorBean>()
    }

    override fun getImplLayoutId(): Int {
        return R.layout.popu_color_pick
    }

    override fun onCreate() {
        super.onCreate()
        val datassss = mutableListOf(context.resources.getStringArray(R.array.color0)
                , context.resources.getStringArray(R.array.color1)
                , context.resources.getStringArray(R.array.color2)
                , context.resources.getStringArray(R.array.color3)
                , context.resources.getStringArray(R.array.color4)
                , context.resources.getStringArray(R.array.color5)
                , context.resources.getStringArray(R.array.color6)
                , context.resources.getStringArray(R.array.color7)
                , context.resources.getStringArray(R.array.color8)
                , context.resources.getStringArray(R.array.color9)
        );

        for (clolrs in datassss) {
            var colorBean = ColorBean(clolrs)
            datas.add(colorBean)
        }
        val layoutManager = GridLayoutManager(context, 5)
        recyclerView.layoutManager = layoutManager

        adapter = object : BaseQuickAdapter<ColorBean, BaseViewHolder>(R.layout.item_color_pick, datas) {
            override fun convert(helper: BaseViewHolder?, item: ColorBean?) {
                helper?.setGone(R.id.ll_color_more, isShowMoreColor)
                item?.run {
                    for (index in 0..4) {
                        val flId: Int = context.resources.getIdentifier("fl_color_img$index", "id", context.getPackageName())
                        val IvId: Int = context.resources.getIdentifier("eiv_color_img$index", "id", context.getPackageName())
                        val statusIvId: Int = context.resources.getIdentifier("eiv_color_select_status_img$index", "id", context.getPackageName())
                        var eimg = helper?.getView<ImageView>(IvId)
                        val up: Drawable = eimg!!.drawable
                        val drawableUp: Drawable = DrawableCompat.wrap(up);
                        DrawableCompat.setTint(drawableUp, Color.parseColor(this.group_colors[index]));
                        eimg.setImageDrawable(drawableUp);
                        // eimg.setBorderColor(this.group_colors[index])
                        helper?.setGone(statusIvId,
                                select_outner_position != -1 && select_outner_position == helper.adapterPosition-1
                                        &&
                                select_inner_position != -1 && select_inner_position == index)
                        helper?.addOnClickListener(flId)
                    }

                }
            }
        }
        adapter?.setHeaderAndEmpty(true)
        val headView=LayoutInflater.from(context).inflate(R.layout.popu_color_pick_head,null)
        adapter?.addHeaderView(headView)
        adapter?.bindToRecyclerView(recyclerView)
        rl_arrow_view.setOnClickListener {
            isShowMoreColor = !isShowMoreColor
            adapter?.notifyDataSetChanged()
        }
        adapter?.setOnItemChildClickListener { adapter_inner, view, position ->
            val colorBean = adapter?.getItem(position);

            colorBean?.run {
                var color:String = if (view.id == R.id.fl_color_img0) {
                    headView.rb_none_color.isChecked=false;
                    select_outner_position=position;
                    select_inner_position=0;
                    group_colors[0]
                } else if (view.id == R.id.fl_color_img1) {
                    headView.rb_none_color.isChecked=false;
                    select_outner_position=position;
                    select_inner_position=1;
                    group_colors[1]
                } else if (view.id == R.id.fl_color_img2) {
                    headView.rb_none_color.isChecked=false;
                    select_outner_position=position;
                    select_inner_position=2;
                    group_colors[2]
                } else if (view.id == R.id.fl_color_img3) {
                    headView.rb_none_color.isChecked=false;
                    select_outner_position=position;
                    select_inner_position=3;
                    group_colors[3]
                } else if (view.id == R.id.fl_color_img4) {
                    headView. rb_none_color.isChecked=false;
                    select_outner_position=position;
                    select_inner_position=4;
                    group_colors[4]
                } else {
                    select_inner_position=-1
                    select_outner_position=-1;
                    "-1"
                }
                colorPickCallBack?.getColor(color)
                adapter?.notifyDataSetChanged()
                dismiss()
            }

        }
        tv_cancel.setOnClickListener {
            dismiss()
        }

        headView.ll_none_color_view.setOnClickListener {
            headView.rb_none_color.isChecked=true;
            select_inner_position=-1
            select_outner_position=-1;
            colorPickCallBack?.getColor("0")//不选择颜色
            adapter?.notifyDataSetChanged()
            dismiss()
        }
    }

    public interface ColorPickCallBack {
        fun getColor(color: String);
    }
}