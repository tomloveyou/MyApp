package com.yl.userlibrary;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.standards.library.base.BaseTitleBarActivity;
import com.standards.library.util.ToastUtil;
import com.standards.library.util.Utils;

public class AboutActivity extends BaseTitleBarActivity implements View.OnClickListener {
    private TextView appVersion;
    private LinearLayout activityAboutCommnetLl;
    private LinearLayout activityUserAgreegmenttLl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_layout;
    }

    @Override
    protected void init() {
        setTitle("关于我们");
        initView();
    }

    @Override
    protected void setListener() {
        activityAboutCommnetLl.setOnClickListener(this);
        activityUserAgreegmenttLl.setOnClickListener(this);
    }

    private void initView() {
        appVersion = (TextView) findViewById(R.id.app_version);
        activityAboutCommnetLl = (LinearLayout) findViewById(R.id.activity_about_commnet_ll);
        activityUserAgreegmenttLl = (LinearLayout) findViewById(R.id.activity_user_agreegmentt_ll);
        appVersion.setText(Utils.getAppVersionName(this)+"");

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.activity_about_commnet_ll) {
            ToastUtil.showToast("开发中，请稍后");
        } else if (i == R.id.activity_user_agreegmentt_ll) {
            ToastUtil.showToast("开发中，请稍后");
        }
    }
}
