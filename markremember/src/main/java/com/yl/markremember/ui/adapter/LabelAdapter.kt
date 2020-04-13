package com.yl.markremember.ui.adapter

import android.content.Intent
import android.os.Bundle
import com.standards.library.adapter.BaseViewHolder
import com.standards.library.adapter.entity.MultiItemEntity
import com.yl.markremember.R
import com.yl.markremember.base.BaseMultiItemDragQuickAdapter
import com.yl.markremember.bean.MenuBean
import com.yl.markremember.db.model.LabelInfo
import com.yl.markremember.ui.activity.AddLabelActivity
import com.yl.markremember.ui.activity.ListLabelManagerActivity

class LabelAdapter(args: List<MultiItemEntity>) : BaseMultiItemDragQuickAdapter<MultiItemEntity, BaseViewHolder>(args) {
    init {
        addItemType(0, R.layout.drawerlayout_section_groupitem_view)
        addItemType(2, R.layout.item_ticke_normal_layout)
    }

    override fun convert(helper: BaseViewHolder?, item: MultiItemEntity?) {
        item?.run {
            if (helper?.itemViewType == 0) {
                val dd=item as MenuBean
                helper.setText(R.id.menu_name,dd.title)

                helper.itemView.setOnClickListener {
                    if (dd.isExpanded) {
                        collapse(helper.adapterPosition)
                    } else {
                        expand(helper.adapterPosition)
                    }
                }
                helper.itemView.setOnClickListener {
                    if (dd.id==5){
                       mContext. startActivity(Intent(mContext, ListLabelManagerActivity::class.java))
                    }
                }
            }else{
                val dd=item as LabelInfo
                helper?.setText(R.id.menu_name,dd.label_name)
                helper?.itemView?.setOnClickListener {
                    val bundle=Bundle()
                    bundle.putSerializable("label_data",dd)
                    val intent=Intent(mContext, AddLabelActivity::class.java)
                    intent.putExtras(bundle)
                    mContext. startActivity(intent)
                }
                //helper?.setText(R.id.menu_right_text,"${dd.label_use_count}")
            }
        }
    }



}