package com.yl.userlibrary;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.standards.library.base.BaseFragment;
import com.standards.library.util.ToastUtil;
import com.standards.library.widget.EaseImageView;
import com.yl.userlibrary.mvp.module.UserinfoBean;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FetchUserInfoListener;

public class MineFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout fragmentUserHeadLl;
    private EaseImageView fragmentUserHeadAvatorImg;
    private TextView fragmentUserHeadNicknameTv;
    private TextView fragmentUserHeadInfoTv;
    private LinearLayout fragmentUserRecordLl;
    private LinearLayout fragmentUserResourceManagerLl;
    private LinearLayout fragmentUserSettingLl;
    private TextView fragmentUserHeadUnlogin;
    private UserinfoBean userinfoBean = null;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_layout;
    }

    @Override
    public void init() {
        initView();
    }

    @Override
    public void setListener() {
        fragmentUserHeadLl.setOnClickListener(this);
        fragmentUserRecordLl.setOnClickListener(this);
        fragmentUserResourceManagerLl.setOnClickListener(this);
        fragmentUserSettingLl.setOnClickListener(this);
    }

    private void initView() {
        fragmentUserHeadLl = (LinearLayout) findView(R.id.fragment_user_head_ll);
        fragmentUserHeadAvatorImg = (EaseImageView) findView(R.id.fragment_user_head_avator_img);
        fragmentUserHeadNicknameTv = (TextView) findView(R.id.fragment_user_head_nickname_tv);
        fragmentUserHeadInfoTv = (TextView) findView(R.id.fragment_user_head_info_tv);
        fragmentUserRecordLl = (LinearLayout) findView(R.id.fragment_user_record_ll);
        fragmentUserResourceManagerLl = (LinearLayout) findView(R.id.fragment_user_resource_manager_ll);
        fragmentUserSettingLl = (LinearLayout) findView(R.id.fragment_user_setting_ll);
        fragmentUserHeadUnlogin = (TextView) findView(R.id.fragment_user_head_unlogin);

    }

    @Override
    public void onResume() {
        super.onResume();
        BmobUser.fetchUserInfo(new FetchUserInfoListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                userinfoBean = BmobUser.getCurrentUser(UserinfoBean.class);
                setUIDataShow();
            }


        });
    }

    private void setUIDataShow() {
        userinfoBean = BmobUser.getCurrentUser(UserinfoBean.class);
        if (userinfoBean != null && BmobUser.isLogin()) {
            fragmentUserHeadUnlogin.setVisibility(View.GONE);
            fragmentUserHeadNicknameTv.setVisibility(View.VISIBLE);
            fragmentUserHeadInfoTv.setVisibility(View.VISIBLE);
            fragmentUserHeadNicknameTv.setText(userinfoBean.getNickname());
            fragmentUserHeadInfoTv.setText("账号：" + userinfoBean.getUsername());
            RequestOptions requestOptions = new RequestOptions().placeholder(R.mipmap.user_defaul_avator);
            Glide.with(this).load(userinfoBean.getAvator_url()).apply(requestOptions).into(fragmentUserHeadAvatorImg);
        } else {
            fragmentUserHeadUnlogin.setVisibility(View.VISIBLE);
            fragmentUserHeadNicknameTv.setVisibility(View.GONE);
            fragmentUserHeadInfoTv.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {

        int i = v.getId();
        if (i == R.id.fragment_user_head_ll) {
            if (BmobUser.isLogin()) {
                startActivity(new Intent(getActivity(), PersonActivity.class));
            } else {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }

        } else if (i == R.id.fragment_user_record_ll) {
            if (userinfoBean == null) {
                ToastUtil.showToast("请先登录");
                return;
            }
            startActivity(new Intent(getActivity(), FuctionUsedActivity.class));
        } else if (i == R.id.fragment_user_resource_manager_ll) {
            if (userinfoBean == null) {
                ToastUtil.showToast("请先登录");
                return;
            }
        } else if (i == R.id.fragment_user_setting_ll) {
            if (userinfoBean == null) {
                ToastUtil.showToast("请先登录");
                return;

            }
            startActivity(new Intent(getActivity(), SettingActivity.class));
        } else {
            ToastUtil.showToast("开发中，请稍后");
        }
    }
}
