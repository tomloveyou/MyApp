package com.yl.userlibrary;

import com.standards.library.base.BaseTitleBarActivity;

public class FeedBackActivity extends BaseTitleBarActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback_layout;
    }

    @Override
    protected void init() {
        setTitle("用户反馈");
    }

    @Override
    protected void setListener() {

    }
}
