package com.yl.myapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;



import com.standards.library.cache.SPHelp;
import com.standards.library.listview.ListGroupPresenter;
import com.standards.library.listview.adapter.LoadMoreRecycleAdapter;
import com.standards.library.listview.listview.RecycleListViewImpl;
import com.standards.library.util.ToastUtil;
import com.yl.myapp.bean.ControlBean;
import com.standards.library.group.LoadingPage;
import com.standards.library.group.Scene;
import com.yl.myapp.bean.UserinfoBean;
import com.yl.myapp.manager.ClassManager;
import com.yl.myapp.ui.ListStyleActivity;
import com.yl.myapp.ui.MenuActivity;

import com.yl.myapp.ui.TestActivity;

import com.yl.myapp.ui.utils.FileUtils;
import com.yl.myapp.ui.web.WebActivity;
import com.standards.library.widget.RecycleViewDivider;
import com.zhy.adapter.recyclerview.CommonAdapter;

import org.litepal.LitePal;
import org.litepal.crud.callback.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private CommonAdapter<ControlBean> adapter;
    private ListGroupPresenter presenter;
    private RecycleListViewImpl recycleListView;
    private LinearLayout header_bg_ll;

    private List<ControlBean> datas = new ArrayList();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            List<ControlBean> data = (List<ControlBean>) msg.obj;
            if (data == null || data.size() == 0) {
                ToastUtil.showToast("没有可选项目");
                return;
            }
            LitePal.saveAllAsync(data).listen(new SaveCallback() {
                @Override
                public void onFinish(boolean success) {
                    ToastUtil.showToast("保存数据成功");
                    SPHelp.setAccessParam("dbexit", true);

                }
            });

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        header_bg_ll=findViewById(R.id.header_bg_ll);
        setSupportActionBar(toolbar);
        UserinfoBean bmobUser = BmobUser.getCurrentUser(this,UserinfoBean.class);
//        Glide.with(this).load(bmobUser.getHead_bg_url()).asBitmap().into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                header_bg_ll.setBackground(new BitmapDrawable(resource).getCurrent());
//            }
//        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initView();
        boolean exit = (boolean) SPHelp.getAccessParam("dbexit", false);
        if (!exit) {
            handler.post(runnable);
        }
    }



    private Runnable runnable = () -> {
        List<ControlBean> data = FileUtils.TxtToBean(MainActivity.this);
        if (data != null) {
            Message message = new Message();
            message.obj = data;

            handler.sendMessage(message);
        }

    };


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if (id == R.id.default_reset) {
            startActivity(new Intent(this, ListStyleActivity.class));
           // showDialog();
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            startActivity(new Intent(this, TestActivity.class));
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(this, WebActivity.class));
        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(this, MenuActivity.class).putExtra("menuType",0));
        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(this, MenuActivity.class).putExtra("menuType",1));
        } else if (id == R.id.nav_share) {
            startActivity(new Intent(this, MenuActivity.class).putExtra("menuType",2));
        } else if (id == R.id.nav_send) {
            startActivity(new Intent(this, MenuActivity.class).putExtra("menuType",3));
        } else if (id == R.id.nav_oneimg) {
            startActivity(new Intent(this, MenuActivity.class));
        } else if (id == R.id.nav_twoimg) {
            startActivity(new Intent(this, MenuActivity.class).putExtra("menuType",3));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void initView() {
        recycleListView = new RecycleListViewImpl(false, false, false);
        LinearLayout rlContent = findViewById(R.id.activity_container);
        LoadingPage loadingPage = new LoadingPage(this, Scene.DEFAULT);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        presenter = ListGroupPresenter.create(MainActivity.this, recycleListView, new ClassManager(), new Ad(this), loadingPage);
        recycleListView.getRecyclerView().addItemDecoration(new RecycleViewDivider(this,
                LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.main_black_color_999999)));
        rlContent.addView(presenter.getRootView(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


    }
    class Ad extends LoadMoreRecycleAdapter<Class,Ad.ViewHolder> {

        public Ad(Context mContext) {
            super(mContext);
            removeHeaderView(0X666);
            removeFooterView(0X11);

        }

        @Override
        public ViewHolder onCreateContentView(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(MainActivity.this).inflate(android.R.layout.simple_expandable_list_item_1, parent, false));
        }

        @Override
        public void onBindView(ViewHolder viewHolder, int realPosition) {
            viewHolder.setData(mData.get(realPosition), realPosition);
        }


        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView title;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(android.R.id.text1);
            }

            public void setData(Class data, int position) {
                title.setText(data.getName());
                title.setTextColor(getResources().getColor(R.color.black));
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, data);
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("data", data);
//                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
