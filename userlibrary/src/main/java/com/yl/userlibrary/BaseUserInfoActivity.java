package com.yl.userlibrary;

import com.standards.library.base.BasePresenter;
import com.standards.library.base.BaseTitleBarActivity;
import com.yl.userlibrary.mvp.module.UserinfoBean;

import cn.bmob.v3.BmobUser;

public abstract class BaseUserInfoActivity<T extends BasePresenter>  extends BaseTitleBarActivity<T> {
    protected UserinfoBean userinfoBean=null;

    @Override
    protected void init() {
        userinfoBean= BmobUser.getCurrentUser(UserinfoBean.class);
    }
}
