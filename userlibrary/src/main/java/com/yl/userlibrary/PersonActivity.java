package com.yl.userlibrary;

import com.standards.library.base.BaseTitleBarActivity;

public class PersonActivity extends BaseTitleBarActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person;
    }

    @Override
    protected void init() {
        setTitle("个人中心");

    }

    @Override
    protected void setListener() {

    }
}
