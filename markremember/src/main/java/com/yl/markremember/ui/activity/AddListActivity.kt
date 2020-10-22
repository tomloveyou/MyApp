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
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.interfaces.OnCancelListener
import com.lxj.xpopup.interfaces.OnConfirmListener
import com.lxj.xpopup.interfaces.OnSelectListener
import com.standards.library.base.BasePresenter
import com.standards.library.base.BaseTitleBarActivity
import com.yl.markremember.R
import com.yl.markremember.base.MarkBaseTitleActivity
import com.yl.markremember.common.NetConstant
import com.yl.markremember.db.model.LabelInfo
import com.yl.markremember.db.model.ListInfo


import com.yl.markremember.db.viewmodel.ListViewModel
import com.yl.markremember.ui.widget.CenterPickColorPopu
import com.yl.markremember.ui.widget.LabelMergePopuView
import kotlinx.android.synthetic.main.activity_label_add.*

import kotlinx.android.synthetic.main.activity_list_add.*

import java.util.*

class AddListActivity : MarkBaseTitleActivity<BasePresenter<*>>() {
    private var labelinfo: ListInfo? = null
    private lateinit var labelViewModel: ListViewModel
    private  var megerData:List<ListInfo>?=null
    private  var ddddddd:List<String>?=null
    private var colorPopu: BasePopupView? = null
    private var label_tint_color: String? = "0"
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
            label_tint_color=list_tint_color;
            if (!TextUtils.isEmpty(list_tint_color)&&!"0".equals(list_tint_color)&&!"-1".equals(list_tint_color)) {
                iv_list_tint_color.visibility=View.VISIBLE
                val up: Drawable = iv_list_tint_color.drawable
                val drawableUp: Drawable = DrawableCompat.wrap(up);
                DrawableCompat.setTint(drawableUp, Color.parseColor(list_tint_color));
                iv_list_tint_color.setImageDrawable(drawableUp);
            }

        }
        ll_list_color_pick.setOnClickListener {
            if (colorPopu == null) {
                colorPopu = XPopup.Builder(this)//.maxHeight((ScreenUtils.getScreenHeight(this)*0.8f).toInt())
                        .asCustom(CenterPickColorPopu(this,label_tint_color, object : CenterPickColorPopu.ColorPickCallBack {
                            override fun getColor(color: String) {
                                label_tint_color = color;
                                if ("0".equals(color)) {
                                    iv_list_tint_color.visibility = View.GONE
                                } else {
                                    iv_list_tint_color.visibility = View.VISIBLE
                                }
                                if (!"0".equals(color) && !"-1".equals(color)) {

                                    val up: Drawable = iv_list_tint_color.drawable
                                    val drawableUp: Drawable = DrawableCompat.wrap(up);
                                    DrawableCompat.setTint(drawableUp, Color.parseColor(color));
                                    iv_list_tint_color.setImageDrawable(drawableUp);
                                }
                            }
                        })).show()
            } else {
                colorPopu?.show()
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
            val lable_name=et_list_name.text.toString();
            if (!TextUtils.isEmpty(lable_name)) {
                if (labelinfo == null) {
                    var labelInfo = ListInfo(null, NetConstant.LABEL_P_CODE, label_tint_color, lable_name, DateUtils.timeParse(Date().time), null, 0);
                    labelViewModel.insert(labelInfo)
                } else {
                    labelinfo?.list_name =lable_name;
                    labelinfo?.list_tint_color = label_tint_color;
                    labelinfo?.list_update_time=DateUtils.timeParse(Date().time)
                    labelViewModel.update(labelinfo!!)
                }
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