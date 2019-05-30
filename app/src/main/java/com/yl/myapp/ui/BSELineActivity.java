package com.yl.myapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.standards.library.base.BaseTitleBarActivity;
import com.yl.myapp.R;


public class BSELineActivity extends BaseTitleBarActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_bseline;
    }

    @Override
    protected void init() {
        setTitle("自定义view");
    }

    @Override
    protected void setListener() {

    }
}
