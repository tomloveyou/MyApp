package com.yl.userlibrary;

import com.standards.library.base.BaseTitleBarActivity;

public class UserInfoEditActivity extends BaseTitleBarActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_userinfo_edit_layout;
    }

    @Override
    protected void init() {
        setTitle("用户信息管理");
    }

    @Override
    protected void setListener() {

    }
}
