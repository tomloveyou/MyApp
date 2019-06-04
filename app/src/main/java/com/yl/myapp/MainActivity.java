package com.yl.myapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.core.view.GravityCompat;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.palette.graphics.Palette;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;

import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.android.material.navigation.NavigationView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.standards.library.arounter.ARouterConstant;
import com.standards.library.arounter.ARouterUtils;
import com.standards.library.cache.SPHelp;
import com.standards.library.entry.TabEntity;

import com.standards.library.util.ToastUtil;
import com.yl.myapp.bean.ControlBean;
import com.yl.userlibrary.mvp.module.UserinfoBean;
import com.yl.myapp.ui.HomeFragment;
import com.yl.myapp.ui.ListStyleActivity;
import com.yl.userlibrary.LoginActivity;
import com.yl.myapp.ui.MenuActivity;

import com.yl.myapp.ui.TestActivity;

import com.yl.myapp.ui.utils.FileUtils;
import com.standards.library.web.WebActivity;
import com.yl.userlibrary.MineFragment;

import org.litepal.LitePal;
import org.litepal.crud.callback.SaveCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
@Route(path = ARouterConstant.ACTIVITY_MAIN_ACTIVITY)
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private LinearLayout header_bg_ll;
    private NavigationView navigationView;
    private ImageView imageView;
    private TextView usernickname, usersign;
    private Bitmap bgBitmap = null;
    private Canvas mCanvas = null;
    private Paint mPaint = null;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private CommonTabLayout tabLayout;
    private ViewPager viewPager;
    private String[] mTitles = {"首页", "消息", "联系人", "更多"};

    private List<LocalMedia> selectList = new ArrayList<>();
    private ArrayList<CustomTabEntity> tabsDatas = new ArrayList<>();
    private UserinfoBean bmobUser;
    /**
     * 图片上传类型
     */
    private int type = 0;
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
        navigationView = findViewById(R.id.nav_view);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.tab_viewpager);
        mFragments.add(new HomeFragment());
        mFragments.add(new MineFragment());
        tabsDatas.add(new TabEntity("首页", R.mipmap.tab_home_select, R.mipmap.tab_home_unselect));
        tabsDatas.add(new TabEntity("我的", R.mipmap.tab_contact_select, R.mipmap.tab_contact_unselect));
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabLayout.setTabData(tabsDatas);
        header_bg_ll = navigationView.getHeaderView(0).findViewById(R.id.header_bg_ll);
        imageView = navigationView.getHeaderView(0).findViewById(R.id.imageView);
        usernickname = navigationView.getHeaderView(0).findViewById(R.id.user_nickname);
        usersign = navigationView.getHeaderView(0).findViewById(R.id.user_sign);
        setSupportActionBar(toolbar);
        bmobUser = BmobUser.getCurrentUser(UserinfoBean.class);

        Glide.with(this).load(bmobUser.getHead_bg_url()).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                header_bg_ll.setBackground(resource);
                BitmapDrawable bitmapDrawable = (BitmapDrawable) resource;
                Palette.from(bitmapDrawable.getBitmap()).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        //记得判空
                        if (palette == null) return;
                        //palette取色不一定取得到某些特定的颜色，这里通过取多种颜色来避免取不到颜色的情况
                        if (palette.getDarkVibrantColor(Color.TRANSPARENT) != Color.TRANSPARENT) {
                            createLinearGradientBitmap(palette.getDarkVibrantColor(Color.TRANSPARENT), palette.getVibrantColor(Color.TRANSPARENT));
                        } else if (palette.getDarkMutedColor(Color.TRANSPARENT) != Color.TRANSPARENT) {
                            createLinearGradientBitmap(palette.getDarkMutedColor(Color.TRANSPARENT), palette.getMutedColor(Color.TRANSPARENT));
                        } else {
                            createLinearGradientBitmap(palette.getLightMutedColor(Color.TRANSPARENT), palette.getLightVibrantColor(Color.TRANSPARENT));
                        }
                    }
                });
            }
        });
        RequestOptions requestOptions = new RequestOptions().placeholder(R.mipmap.user_defaul_avator);
        Glide.with(this).load(bmobUser.getAvator_url()).apply(requestOptions).into(imageView);
        usernickname.setText(bmobUser.getNickname());
        usersign.setText(bmobUser.getPersonal_sign());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        header_bg_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 0;
                getImgPicture(false);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 1;
                // getImgPicture(true);
                ARouterUtils.navigation(ARouterConstant.ACTIVITY_USER_INFO);
            }
        });
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initView();
        boolean exit = (boolean) SPHelp.getAccessParam("dbexit", false);
        if (!exit) {
            handler.post(runnable);
        }
    }

    private void initView() {

    }

    private void createLinearGradientBitmap(int darkColor, int color) {

        int bgColors[] = new int[2];
        bgColors[0] = darkColor;
        bgColors[1] = color;

        if (bgBitmap == null) {
            bgBitmap = Bitmap.createBitmap(navigationView.getWidth(), navigationView.getHeight(), Bitmap.Config.ARGB_4444);
        }
        if (mCanvas == null) {
            mCanvas = new Canvas();
        }
        if (mPaint == null) {
            mPaint = new Paint();
        }
        mCanvas.setBitmap(bgBitmap);
        mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        LinearGradient gradient = new LinearGradient(0, 0, 0, bgBitmap.getHeight(), bgColors[0], bgColors[1], Shader.TileMode.CLAMP);
        mPaint.setShader(gradient);
        RectF rectF = new RectF(0, 0, bgBitmap.getWidth(), bgBitmap.getHeight());
        // mCanvas.drawRoundRect(rectF,16,16,mPaint); 这个用来绘制圆角的哈
        mCanvas.drawRect(rectF, mPaint);
        navigationView.setBackground(new BitmapDrawable(bgBitmap));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择
                    selectList = PictureSelector.obtainMultipleResult(data);
                    BmobFile bmobFile = new BmobFile(new File(selectList.get(0).getCompressPath()));
                    bmobFile.upload(new UploadFileListener() {
                        @Override
                        public void done(BmobException e) {
                            if (type == 0) {
                                bmobUser.setHead_bg_url(bmobFile.getUrl());
                            } else {
                                bmobUser.setAvator_url(bmobFile.getUrl());
                            }
                            bmobUser.update(new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        ToastUtil.showToast(e.getMessage());
                                    } else {
                                        if (type == 0) {
                                            Glide.with(MainActivity.this).load(bmobUser.getHead_bg_url()).into(new SimpleTarget<Drawable>() {
                                                @Override
                                                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                                    header_bg_ll.setBackground(resource);
                                                    BitmapDrawable bitmapDrawable = (BitmapDrawable) resource;
                                                    Palette.from(bitmapDrawable.getBitmap()).generate(new Palette.PaletteAsyncListener() {
                                                        @Override
                                                        public void onGenerated(Palette palette) {
                                                            //记得判空
                                                            if (palette == null) return;
                                                            //palette取色不一定取得到某些特定的颜色，这里通过取多种颜色来避免取不到颜色的情况
                                                            if (palette.getDarkVibrantColor(Color.TRANSPARENT) != Color.TRANSPARENT) {
                                                                createLinearGradientBitmap(palette.getDarkVibrantColor(Color.TRANSPARENT), palette.getVibrantColor(Color.TRANSPARENT));
                                                            } else if (palette.getDarkMutedColor(Color.TRANSPARENT) != Color.TRANSPARENT) {
                                                                createLinearGradientBitmap(palette.getDarkMutedColor(Color.TRANSPARENT), palette.getMutedColor(Color.TRANSPARENT));
                                                            } else {
                                                                createLinearGradientBitmap(palette.getLightMutedColor(Color.TRANSPARENT), palette.getLightVibrantColor(Color.TRANSPARENT));
                                                            }
                                                        }
                                                    });
                                                }
                                            });
                                        } else {
                                            RequestOptions requestOptions = new RequestOptions().placeholder(R.mipmap.user_defaul_avator);
                                            Glide.with(MainActivity.this).load(bmobUser.getAvator_url()).apply(requestOptions).into(imageView);
                                        }
                                    }
                                }
                            });


                        }
                    });
                    break;
            }
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

    private void getImgPicture(boolean crop) {
        selectList.clear();
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofAll())
                .theme(R.style.picture_default_style)
                .maxSelectNum(1)
                .minSelectNum(1)
                .selectionMode(PictureConfig.SINGLE)
                .previewImage(true)
                .previewVideo(false)
                .enablePreviewAudio(false) // 是否可播放音频
                .isCamera(false)
                .enableCrop(crop)
                .compress(true)
                .glideOverride(160, 160)
                .previewEggs(true)
                .withAspectRatio(1, 1)
                .hideBottomControls(false)
                .isGif(false)
                .freeStyleCropEnabled(true)
                .circleDimmedLayer(true)
                .showCropFrame(true)
                .showCropGrid(false)
                .openClickSound(false)
                .selectionMedia(selectList)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }


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
        } else if (id == R.id.default_reset) {
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
            startActivity(new Intent(this, MenuActivity.class).putExtra("menuType", 0));
        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(this, MenuActivity.class).putExtra("menuType", 1));
        } else if (id == R.id.nav_share) {
            startActivity(new Intent(this, MenuActivity.class).putExtra("menuType", 2));
        } else if (id == R.id.nav_oneimg) {
            startActivity(new Intent(this, MenuActivity.class));
        } else if (id == R.id.nav_twoimg) {
            startActivity(new Intent(this, MenuActivity.class).putExtra("menuType", 3));
        } else if (id == R.id.nav_send) {
            startActivity(new Intent(this, LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
