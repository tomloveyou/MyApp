package com.yl.userlibrary;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ns.yc.ycutilslib.switchButton.SwitchButton;
import com.standards.library.base.BaseTitleBarActivity;

public class SettingActivity extends BaseTitleBarActivity implements View.OnClickListener {
    private SwitchButton activityUserRecordGetSwitchbutton;
    private LinearLayout activityUserFeedbackLl;
    private LinearLayout activityUserAboutLl;
    private LinearLayout activityUserChangeAcountLl;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_setting_layout;
    }

    @Override
    protected void init() {
        setTitle("用户设置");
        initView();
    }

    @Override
    protected void setListener() {
        activityUserFeedbackLl.setOnClickListener(this);
        activityUserAboutLl.setOnClickListener(this);
        activityUserChangeAcountLl.setOnClickListener(this);
    }

    private void initView() {
        activityUserRecordGetSwitchbutton = (SwitchButton) findViewById(R.id.activity_user_record_get_switchbutton);
        activityUserFeedbackLl = (LinearLayout) findViewById(R.id.activity_user_feedback_ll);
        activityUserAboutLl = (LinearLayout) findViewById(R.id.activity_user_about_ll);
        activityUserChangeAcountLl = (LinearLayout) findViewById(R.id.activity_user_change_acount_ll);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.activity_user_feedback_ll) {
            startActivity(new Intent(this, FeedBackActivity.class));
        } else if (i == R.id.activity_user_about_ll) {
            startActivity(new Intent(this, AboutActivity.class));
        } else if (i == R.id.activity_user_change_acount_ll) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
