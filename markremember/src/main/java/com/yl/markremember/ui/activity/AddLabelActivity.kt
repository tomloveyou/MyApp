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
import com.luck.picture.lib.tools.ScreenUtils
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
import com.yl.markremember.db.viewmodel.LabelViewModel
import com.yl.markremember.ui.widget.CenterPickColorPopu
import com.yl.markremember.ui.widget.LabelMergePopuView
import kotlinx.android.synthetic.main.activity_label_add.*
import kotlinx.android.synthetic.main.app_calendar_text_today.view.*
import java.util.*

class AddLabelActivity : MarkBaseTitleActivity<BasePresenter<*>>() {
    private var labelinfo: LabelInfo? = null
    private lateinit var labelViewModel: LabelViewModel
    private var megerData: List<LabelInfo>? = null
    private var ddddddd: List<String>? = null
    private var colorPopu: BasePopupView? = null
    private var label_tint_color: String? = "0"
    override fun setListener() {

    }

    override fun init() {
        setTitle("添加标签")
        labelViewModel = ViewModelProvider(this).get(LabelViewModel::class.java)
        val data = (intent?.extras?.getSerializable(NetConstant.KEY_LABEL_DATA))
        labelViewModel.allLabels.observe(this, androidx.lifecycle.Observer {
            megerData = it?.filter { it.label_id != labelinfo?.label_id }
            ddddddd = megerData?.map {
                it.label_name
            }
        })

        data?.run {
            labelinfo = this as LabelInfo
        }
        labelinfo?.run {
            setTitle("编辑标签")
            et_label_name.setText(label_name)
            val tint = if (TextUtils.isEmpty(label_pname)) {
                "无"
            } else {
                label_pname
            }
            tv_label_pname.text = tint
            this@AddLabelActivity.label_tint_color=label_tint_color
            if (!TextUtils.isEmpty(label_tint_color)&&!"0".equals(label_tint_color)&&!"-1".equals(label_tint_color)) {
                iv_label_tint_color.visibility=View.VISIBLE
                val up: Drawable = iv_label_tint_color.drawable
                val drawableUp: Drawable = DrawableCompat.wrap(up);
                DrawableCompat.setTint(drawableUp, Color.parseColor(label_tint_color));
                iv_label_tint_color.setImageDrawable(drawableUp);
            }

        }
        ll_color_pick.setOnClickListener {
            if (colorPopu == null) {
                colorPopu = XPopup.Builder(this)//.maxHeight((ScreenUtils.getScreenHeight(this)*0.8f).toInt())
                        .asCustom(CenterPickColorPopu(this, label_tint_color,object : CenterPickColorPopu.ColorPickCallBack {
                            override fun getColor(color: String) {
                                label_tint_color = color;
                                if ("0".equals(color)) {
                                    iv_label_tint_color.visibility = View.GONE
                                } else {
                                    iv_label_tint_color.visibility = View.VISIBLE
                                }
                                if (!"0".equals(color) && !"-1".equals(color)) {
                                    val up: Drawable = iv_label_tint_color.drawable
                                    val drawableUp: Drawable = DrawableCompat.wrap(up);
                                    DrawableCompat.setTint(drawableUp, Color.parseColor(color));
                                    iv_label_tint_color.setImageDrawable(drawableUp);
                                }
                            }
                        })).show()
            } else {
                colorPopu?.show()
            }

        }

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_label_add
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu);
        if (labelinfo == null) {//有数据时菜单项变化
            menuInflater.inflate(R.menu.add_menu, menu)
        } else {
            menuInflater.inflate(R.menu.edite_label_menu, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.label_action_add) {
            if (!TextUtils.isEmpty(et_label_name.text.toString())) {
                if (labelinfo == null) {
                    var labelInfo = LabelInfo(null, NetConstant.LABEL_P_CODE, null, label_tint_color, et_label_name.text.toString(), DateUtils.timeParse(Date().time), null, 0);
                    labelViewModel.insert(labelInfo)
                } else {
                    labelinfo?.label_name = et_label_name.text.toString();
                    labelinfo?.label_tint_color = label_tint_color;
                    labelinfo?.label_update_time=DateUtils.timeParse(Date().time)
                    labelViewModel.update(labelinfo!!)
                }
                finish()
            }

        } else if (item?.itemId == R.id.label_action_delete) {
            labelinfo?.run {
                XPopup.Builder(this@AddLabelActivity).asConfirm("删除标签", "删除后，标签“${labelinfo?.label_name}”将会从任务中删除", "取消", "确定", {
                    labelViewModel.deleteLabel(this)
                    finish()
                }, {

                }, false).show()

            }

        } else if (item?.itemId == R.id.label_action_merge) {

            XPopup.Builder(this).asCustom(LabelMergePopuView(this)).show()
        }
        return super.onOptionsItemSelected(item)
    }

}