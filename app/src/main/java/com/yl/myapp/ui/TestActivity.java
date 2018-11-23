package com.yl.myapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import com.yl.myapp.R;
import com.yl.myapp.base.BaseTitleBarActivity;

public class TestActivity extends BaseTitleBarActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void setListener() {

    }



    @Override
    public void initTitleBar(android.support.v7.widget.Toolbar titleBar) {
        titleBar.setTitle("测试");
    }
}
