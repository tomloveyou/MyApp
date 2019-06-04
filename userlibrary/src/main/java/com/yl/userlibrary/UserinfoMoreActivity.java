package com.yl.userlibrary;

import com.standards.library.base.BaseTitleBarActivity;

public class UserinfoMoreActivity extends BaseTitleBarActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_userinfo_more_layout;
    }

    @Override
    protected void init() {
        setTitle("更多用户信息");
    }

    @Override
    protected void setListener() {

    }
}
