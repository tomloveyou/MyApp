package com.yl.userlibrary;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.standards.library.arounter.ARouterConstant;
import com.standards.library.arounter.ARouterUtils;
import com.standards.library.base.BaseTitleBarActivity;

@Route(path = ARouterConstant.ACTIVITY_USER_INFO)
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
