package com.yl.markremember.ui.activity

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.ViewModelProvider
import com.luck.picture.lib.tools.DateUtils
import com.luck.picture.lib.tools.StringUtils
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.interfaces.OnCancelListener
import com.lxj.xpopup.interfaces.OnConfirmListener
import com.lxj.xpopup.interfaces.OnSelectListener
import com.standards.library.base.BasePresenter
import com.standards.library.base.BaseTitleBarActivity
import com.yl.markremember.R
import com.yl.markremember.common.NetConstant
import com.yl.markremember.db.model.ListInfo


import com.yl.markremember.db.viewmodel.ListViewModel
import com.yl.markremember.ui.widget.LabelMergePopuView

import kotlinx.android.synthetic.main.activity_list_add.*

import java.util.*

class AddListActivity : BaseTitleBarActivity<BasePresenter<*>>() {
    private var labelinfo: ListInfo? = null
    private lateinit var labelViewModel: ListViewModel
    private  var megerData:List<ListInfo>?=null
    private  var ddddddd:List<String>?=null
    override fun setListener() {

    }

    override fun init() {
        setTitle("添加清单")
        labelViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        val data = (intent?.extras?.getSerializable("list_data"))
        labelViewModel.allLabels.observe(this, androidx.lifecycle.Observer {
            megerData= it?.filter { it.list_id!=labelinfo?.list_id }
            ddddddd= megerData?.map {
                it.list_name
            }
        })

            data?.run {
            labelinfo = this as ListInfo
        }
        labelinfo?.run {
            setTitle("编辑清单")
            et_list_name.setText(list_name)
            val tint = if (TextUtils.isEmpty(list_name)) {
                "无"
            } else {
                list_name
            }
            tv_folder_pname.text = tint
            if (!TextUtils.isEmpty(list_tint_color)) {
                val up: Drawable = iv_list_tint_color.drawable
                val drawableUp: Drawable = DrawableCompat.wrap(up);
                DrawableCompat.setTint(drawableUp, Color.parseColor(list_tint_color));
                iv_list_tint_color.setImageDrawable(drawableUp);
            }

        }

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_list_add
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu);
        if (labelinfo == null) {//有数据时菜单项变化
            menuInflater.inflate(R.menu.add_menu, menu)
        } else {
            menuInflater.inflate(R.menu.edite_list_menu, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.label_action_add) {
            if (!TextUtils.isEmpty(et_list_name.text.toString())) {
                var labelInfo = ListInfo(null, NetConstant.LIST_P_CODE,null, et_list_name.text.toString(), DateUtils.timeParse(Date().time), null,  0);
                labelViewModel.insert(labelInfo)
                finish()
            }

        } else if (item?.itemId == R.id.label_action_delete) {
            labelinfo?.run {
                XPopup.Builder(this@AddListActivity).asConfirm("删除标签","删除后，标签“${labelinfo?.list_name}”将会从任务中删除","取消","确定", {
                    labelViewModel.deleteLabel(this)
                    finish()
                }, {

                },false).show()

            }

        } else if (item?.itemId == R.id.label_action_merge) {
            XPopup.Builder(this).asCustom(LabelMergePopuView(this)).show()
        }
        return super.onOptionsItemSelected(item)
    }

}