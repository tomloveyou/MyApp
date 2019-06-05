package com.yl.userlibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.standards.library.arounter.ARouterConstant;
import com.standards.library.arounter.ARouterUtils;
import com.standards.library.base.BaseTitleBarActivity;
import com.standards.library.util.ToastUtil;
import com.standards.library.widget.EaseImageView;
import com.yl.userlibrary.mvp.module.UsedLogBean;
import com.yl.userlibrary.mvp.module.UserinfoBean;

import java.io.File;
import java.util.ArrayList;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FetchUserInfoListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

@Route(path = ARouterConstant.ACTIVITY_USER_INFO)
public class PersonActivity extends BaseUserInfoActivity implements View.OnClickListener {

    private LinearLayout activityUserHeadLl;
    private EaseImageView activityUserHeadAvator;
    private LinearLayout activityUserNicknameLl;
    private TextView activityUserNickname;
    private LinearLayout activityUserSignLl;
    private TextView activityUserSign;
    private LinearLayout activityUserAcountLl;
    private TextView activityUserAcount;
    private LinearLayout activityUserMoreLl;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_person;
    }

    @Override
    protected void init() {
        super.init();
        setTitle("个人中心");
        initView();

    }

    @Override
    protected void setListener() {
        activityUserHeadLl.setOnClickListener(this);
        activityUserNicknameLl.setOnClickListener(this);
        activityUserSignLl.setOnClickListener(this);
        activityUserAcountLl.setOnClickListener(this);
        activityUserMoreLl.setOnClickListener(this);

    }

    private void initView() {

        activityUserHeadLl = (LinearLayout) findViewById(R.id.activity_user_head_ll);
        activityUserHeadAvator = (EaseImageView) findViewById(R.id.activity_user_head_avator);
        activityUserNicknameLl = (LinearLayout) findViewById(R.id.activity_user_nickname_ll);
        activityUserNickname = (TextView) findViewById(R.id.activity_user_nickname);
        activityUserSignLl = (LinearLayout) findViewById(R.id.activity_user_sign_ll);
        activityUserSign = (TextView) findViewById(R.id.activity_user_sign);
        activityUserAcountLl = (LinearLayout) findViewById(R.id.activity_user_acount_ll);
        activityUserAcount = (TextView) findViewById(R.id.activity_user_acount);
        activityUserMoreLl = (LinearLayout) findViewById(R.id.activity_user_more_ll);

    }

    @Override
    protected void onResume() {
        super.onResume();
        BmobUser.fetchUserInfo(new FetchUserInfoListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                userinfoBean= BmobUser.getCurrentUser(UserinfoBean.class);
                setUIDataShow();
            }
        });

    }

    public void setUIDataShow(){
        activityUserNickname.setText(userinfoBean.getNickname());
        activityUserAcount.setText(userinfoBean.getUsername());
        activityUserSign.setText(userinfoBean.getPersonal_sign());
        RequestOptions requestOptions = new RequestOptions().placeholder(R.mipmap.user_defaul_avator);
        Glide.with(this).load(userinfoBean.getAvator_url()).apply(requestOptions).into(activityUserHeadAvator);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.activity_user_head_ll) {
            PictureSelector.create(this)
                    .openGallery(PictureMimeType.ofImage())
                    .theme(R.style.picture_default_style)
                    .maxSelectNum(1)
                    .minSelectNum(1)
                    .selectionMode(PictureConfig.SINGLE)
                    .previewImage(true)
                    .previewVideo(false)
                    .enablePreviewAudio(false) // 是否可播放音频
                    .isCamera(false)
                    .enableCrop(true)
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
                    .selectionMedia(new ArrayList<>())
                    .forResult(PictureConfig.CHOOSE_REQUEST);
        } else if (i == R.id.activity_user_nickname_ll) {
            Bundle bundle=new Bundle();
            bundle.putInt("type", 0);
            ARouterUtils.navigation(ARouterConstant.ACTIVITY_USER_UPDATE_INFO_ACTIVITY,bundle);
        } else if (i == R.id.activity_user_sign_ll) {
            Bundle bundle=new Bundle();
            bundle.putInt("type", 1);
            ARouterUtils.navigation(ARouterConstant.ACTIVITY_USER_UPDATE_INFO_ACTIVITY,bundle);
        } else if (i == R.id.activity_user_acount_ll) {
        } else if (i == R.id.activity_user_more_ll) {
            startActivity(new Intent(this, UserinfoMoreActivity.class));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {
                BmobFile bmobFile = new BmobFile(new File(PictureSelector.obtainMultipleResult(data).get(0).getCompressPath()));
                bmobFile.upload(new UploadFileListener() {
                    @Override
                    public void done(BmobException e) {
                        userinfoBean.setAvator_url(bmobFile.getUrl());
                        userinfoBean.update(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    ToastUtil.showToast(e.getMessage());
                                } else {
                                    RequestOptions requestOptions = new RequestOptions().placeholder(R.mipmap.user_defaul_avator);
                                    Glide.with(PersonActivity.this).load(userinfoBean.getAvator_url()).apply(requestOptions).into(activityUserHeadAvator);

                                }
                            }
                        });
                    }
                });
            }
        }
    }
}
