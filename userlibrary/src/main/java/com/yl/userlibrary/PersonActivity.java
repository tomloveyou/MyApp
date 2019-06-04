package com.yl.userlibrary;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.standards.library.arounter.ARouterConstant;
import com.standards.library.arounter.ARouterUtils;
import com.standards.library.base.BaseTitleBarActivity;
import com.standards.library.widget.EaseImageView;
import com.yl.userlibrary.mvp.module.UserinfoBean;

import cn.bmob.v3.BmobUser;

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
        } else if (i == R.id.activity_user_nickname_ll) {
            startActivity(new Intent(this, UserInfoEditActivity.class));
        } else if (i == R.id.activity_user_sign_ll) {
            startActivity(new Intent(this, UserInfoEditActivity.class));
        } else if (i == R.id.activity_user_acount_ll) {
        } else if (i == R.id.activity_user_more_ll) {
            startActivity(new Intent(this, UserinfoMoreActivity.class));
        }
    }
}
