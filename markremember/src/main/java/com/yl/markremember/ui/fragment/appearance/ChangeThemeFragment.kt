package com.yl.markremember.ui.fragment.appearance

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.yl.markremember.R
import com.yl.markremember.bean.ThemeBean
import com.yl.markremember.common.NetConstant
import com.yl.markremember.ui.activity.ThemePreViewActivity
import com.yl.markremember.ui.adapter.vlayout.VThemeColorAdapter
import kotlinx.android.synthetic.main.fragment_label_list.*
import java.util.ArrayList

/**
 * 改变主题
 */
class ChangeThemeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_label_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val virtualLayoutManager = VirtualLayoutManager(activity!!)
        recyclerView.layoutManager = virtualLayoutManager
        val viewPool = RecyclerView.RecycledViewPool()
        recyclerView.setRecycledViewPool(viewPool)
        viewPool.setMaxRecycledViews(0, 10)
        val adapter = DelegateAdapter(virtualLayoutManager)
        val gridLayoutHelper = GridLayoutHelper(4)
        val themes_name = resources.getStringArray(R.array.color_themes_name)
        val primary_themes = resources.getStringArray(R.array.color_primary_themes)
        val accent_themes = resources.getStringArray(R.array.color_accent_themes)
        val datas = mutableListOf<ThemeBean>()
        for ((index) in themes_name.withIndex()) {
            val themebean = ThemeBean(themes_name[index], primary_themes[index], accent_themes[index])
            datas.add(themebean)
        }
        gridLayoutHelper.setAutoExpand(false)
        gridLayoutHelper.setIgnoreExtra(true)
        val vThemeColorAdapter = VThemeColorAdapter(datas, gridLayoutHelper)
        adapter.addAdapter(vThemeColorAdapter)
        recyclerView.adapter = adapter
        vThemeColorAdapter.setOnItemClickListener { adapter, view, position ->
            val bundle=Bundle()
            bundle.putSerializable(NetConstant.KEY_THEME_DATA,vThemeColorAdapter.getItem(position))
            startActivity(Intent(activity,ThemePreViewActivity::class.java).putExtras(bundle))
        }
        vThemeColorAdapter.setOnItemChildClickListener { adapter, view, position ->
            if (R.id.v_bg_color==view.id){
                val bundle=Bundle()
                bundle.putSerializable(NetConstant.KEY_THEME_DATA,vThemeColorAdapter.getItem(position))
                startActivity(Intent(activity,ThemePreViewActivity::class.java).putExtras(bundle))
            }
        }
    }
}