package com.yl.markremember.ui.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.SPUtils
import com.standards.library.adapter.entity.MultiItemEntity
import com.standards.library.base.BaseFuncActivity
import com.standards.library.base.BasePresenter
import com.yl.markremember.R
import com.yl.markremember.bean.MenuBean
import com.yl.markremember.ui.adapter.MenuExpandableAdapter
import com.yl.markremember.callback.HomeItemDragAndSwipeCallback
import com.yl.markremember.common.NetConstant
import com.yl.markremember.datamanager.DataManager
import com.yl.markremember.db.model.LabelInfo
import com.yl.markremember.db.model.ListInfo
import com.yl.markremember.db.viewmodel.LabelViewModel
import com.yl.markremember.db.viewmodel.ListViewModel
import com.yl.markremember.event.MsgEvent
import com.yl.markremember.ui.fragment.SettingFragment
import com.yl.markremember.ui.fragment.label.LabelTaskListFragment
import com.yl.markremember.ui.fragment.list.ListTaskListFragment
import com.yl.markremember.ui.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_calendar_main.*
import kotlinx.android.synthetic.main.activity_list_add.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.drawerlayout_foot_view.view.*
import kotlinx.android.synthetic.main.drawerlayout_head_view.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class CalendarMainActivity : BaseFuncActivity<BasePresenter<*>>() {
    private lateinit var listViewModel: ListViewModel
    private lateinit var labelViewModel: LabelViewModel
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var data: ArrayList<MultiItemEntity>
    private lateinit var headdata: List<MultiItemEntity>
    private lateinit var label_menu: MenuBean
    private var adapter: MenuExpandableAdapter? = null;
    private var fragments = mutableMapOf<String, Fragment>(NetConstant.KEY_HOME_HAS_LABEL_TAB to LabelTaskListFragment())
    override fun setListener() {
        labelViewModel.allLabels.observe(this, Observer {
            label_menu.subItems = it
            adapter?.notifyDataSetChanged()

        })
        listViewModel.allLabels.observe(this, Observer {
            val menuBeanList = java.util.ArrayList<MultiItemEntity>()
            menuBeanList.addAll(headdata)
            menuBeanList.addAll(it)
            adapter?.setNewData(menuBeanList)

        })
        homeViewModel.calendar_tab.observe(this, Observer {


        })

        rl_list_data.setOnClickListener {
            setCheckByPosition(NetConstant.KEY_HOME_HAS_LABEL_TAB)
        }
        rl_calendar.setOnClickListener {
            setCheckByPosition(NetConstant.KEY_HOME_HAS_CALENDER_TAB)
        }
        rl_fanqie_counttime.setOnClickListener {
            setCheckByPosition(NetConstant.KEY_HOME_HAS_COUNTDOWN_TAB)
        }
        rl_location_sign.setOnClickListener {
            setCheckByPosition(NetConstant.KEY_HOME_HAS_SIGN_TAB)
        }
        rl_setting.setOnClickListener {
            setCheckByPosition(NetConstant.KEY_HOME_HAS_SETTING_TAB)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun getSettingNotify(msgEvent: MsgEvent<Boolean>) {
        if (NetConstant.KEY_HOME_HAS_LABEL_TAB == msgEvent.code) {//选中首页

        } else if (NetConstant.KEY_HOME_HAS_CALENDER_TAB == msgEvent.code) {//选中日历
            rl_calendar.visibility = if (msgEvent.t) {
                fragments[NetConstant.KEY_HOME_HAS_CALENDER_TAB] = ListTaskListFragment()
                View.VISIBLE
            } else {
                fragments.remove(NetConstant.KEY_HOME_HAS_CALENDER_TAB)
                View.GONE
            }

        } else if (NetConstant.KEY_HOME_HAS_COUNTDOWN_TAB == msgEvent.code) {//选中番茄倒计时
            rl_fanqie_counttime.visibility = if (msgEvent.t) {
                fragments[NetConstant.KEY_HOME_HAS_COUNTDOWN_TAB] = ListTaskListFragment()
                View.VISIBLE
            } else {
                fragments.remove(NetConstant.KEY_HOME_HAS_COUNTDOWN_TAB)
                View.GONE
            }

        } else if (NetConstant.KEY_HOME_HAS_SIGN_TAB == msgEvent.code) {
            rl_location_sign.visibility = if (msgEvent.t) {
                fragments[NetConstant.KEY_HOME_HAS_SIGN_TAB] = ListTaskListFragment()
                View.VISIBLE
            } else {
                fragments.remove(NetConstant.KEY_HOME_HAS_SIGN_TAB)
                View.GONE
            }

        } else {
            rl_setting.visibility = if (msgEvent.t) {
                fragments[NetConstant.KEY_HOME_HAS_SETTING_TAB] = SettingFragment()
                View.VISIBLE
            } else {
                fragments.remove(NetConstant.KEY_HOME_HAS_SETTING_TAB)
                View.GONE
            }
        }
        if (fragments.size > 1) {
            rl_list_data.visibility = View.VISIBLE
        } else {
            rl_list_data.visibility = View.GONE
        }
    }

    fun setCheckByPosition(key: String) {
        val select_color = resources.getColor(R.color.colorPrimary)
        val unselect_color = resources.getColor(R.color.home_title_img_bg_color)
        if (NetConstant.KEY_HOME_HAS_LABEL_TAB == key) {//选中首页
            setImgTintColor(home_view_list_bg, select_color)//首页
            setImgTintColor(iv_calendar_today_img_bg, unselect_color)//日历
            setImgTintColor(home_fanqie_counttime, unselect_color)//番茄
            setImgTintColor(home_iv_location_sign, unselect_color)//打卡
            setImgTintColor(home_iv_setting, unselect_color)//设置
        } else if (NetConstant.KEY_HOME_HAS_CALENDER_TAB == key) {//选中日历
            setImgTintColor(home_view_list_bg, unselect_color)//首页
            setImgTintColor(iv_calendar_today_img_bg, select_color)//日历
            setImgTintColor(home_fanqie_counttime, unselect_color)//番茄
            setImgTintColor(home_iv_location_sign, unselect_color)//打卡
            setImgTintColor(home_iv_setting, unselect_color)//设置
        } else if (NetConstant.KEY_HOME_HAS_COUNTDOWN_TAB == key) {//选中番茄倒计时
            setImgTintColor(home_view_list_bg, unselect_color)//首页
            setImgTintColor(iv_calendar_today_img_bg, unselect_color)//日历
            setImgTintColor(home_fanqie_counttime, select_color)//番茄
            setImgTintColor(home_iv_location_sign, unselect_color)//打卡
            setImgTintColor(home_iv_setting, unselect_color)//设置
        } else if (NetConstant.KEY_HOME_HAS_SIGN_TAB == key) {
            setImgTintColor(home_view_list_bg, unselect_color)//首页
            setImgTintColor(iv_calendar_today_img_bg, unselect_color)//日历
            setImgTintColor(home_fanqie_counttime, unselect_color)//番茄
            setImgTintColor(home_iv_location_sign, select_color)//打卡
            setImgTintColor(home_iv_setting, unselect_color)//设置
        } else {
            setImgTintColor(home_view_list_bg, unselect_color)//首页
            setImgTintColor(iv_calendar_today_img_bg, unselect_color)//日历
            setImgTintColor(home_fanqie_counttime, unselect_color)//番茄
            setImgTintColor(home_iv_location_sign, unselect_color)//打卡
            setImgTintColor(home_iv_setting, select_color)//设置
        }


    }

    fun setImgTintColor(img: ImageView, color: Int) {
        val up: Drawable = img.drawable
        val drawableUp: Drawable = DrawableCompat.wrap(up);
        DrawableCompat.setTint(drawableUp, color);
        img.setImageDrawable(drawableUp);
    }

    override fun init() {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        labelViewModel = ViewModelProvider(this).get(LabelViewModel::class.java)
        val nav: View = nav_view.getHeaderView(0)
        val recyclerView = nav.findViewById<RecyclerView>(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        headdata = DataManager.homeMenuList
        data = ArrayList()
        label_menu = headdata.find {
            val menu = it as MenuBean
            menu.id == 51353
        } as MenuBean
        data.addAll(headdata)
        adapter = MenuExpandableAdapter(data)
        val headview: View = LayoutInflater.from(this).inflate(R.layout.drawerlayout_head_view, null, false)
        val footview: View = LayoutInflater.from(this).inflate(R.layout.drawerlayout_foot_view, null, false)

        adapter?.run {
            setHeaderAndEmpty(true)
            val itemTouchHelper = ItemTouchHelper(HomeItemDragAndSwipeCallback(this))
            itemTouchHelper.attachToRecyclerView(recyclerView)
            enableDragItem(itemTouchHelper)
            setHeaderView(headview)
            setFooterView(footview)
            recyclerView.adapter = this
            setOnItemClickListener { adapter, view, position ->
                val viewtype = getItemViewType(position)
                if (viewtype == 2) {//标签
                    //点击跳转page2
                    // Navigation.findNavController(view).navigate(R.id.ragment_task_list_to_nav_gallery)
                } else if (viewtype == 5) {//清单
                    //点击跳转page2
                    // Navigation.findNavController(view).navigate(R.id.ragment_task_list_to_nav_gallery)
                }
            }
        }

        footview.list_label_manager_view.setOnClickListener {
            startActivity(Intent(this, ListLabelManagerActivity::class.java))
        }
        footview.add_label_view.setOnClickListener {
            startActivity(Intent(this, AddListActivity::class.java))
        }
        headview.rl_action_settings.setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }
        getSettingNotify(MsgEvent(NetConstant.KEY_HOME_HAS_CALENDER_TAB,null,SPUtils.getInstance().getBoolean(NetConstant.KEY_HOME_HAS_CALENDER_TAB,true)))
        getSettingNotify(MsgEvent(NetConstant.KEY_HOME_HAS_SIGN_TAB,null,SPUtils.getInstance().getBoolean(NetConstant.KEY_HOME_HAS_SIGN_TAB)))
        getSettingNotify(MsgEvent(NetConstant.KEY_HOME_HAS_COUNTDOWN_TAB,null,SPUtils.getInstance().getBoolean(NetConstant.KEY_HOME_HAS_COUNTDOWN_TAB)))
        getSettingNotify(MsgEvent(NetConstant.KEY_HOME_HAS_SETTING_TAB,null,SPUtils.getInstance().getBoolean(NetConstant.KEY_HOME_HAS_SETTING_TAB)))

    }


    override fun getLayoutId(): Int {
        return R.layout.activity_calendar_main
    }
}