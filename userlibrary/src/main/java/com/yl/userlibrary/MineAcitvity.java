package com.yl.userlibrary;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.standards.library.arounter.ARouterConstant;
import com.standards.library.base.BaseFuncActivity;
@Route(path = ARouterConstant.ACTIVITY_USER_MINE_ACTIVITY)
public class MineAcitvity extends BaseFuncActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_layout;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void setListener() {

    }
}
