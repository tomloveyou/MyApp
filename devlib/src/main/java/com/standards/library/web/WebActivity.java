package com.standards.library.web;

import android.os.Bundle;

import com.standards.library.base.BasePresenter;


/**
 * <请描述这个类是干什么的>
 *
 * @data: 2016/7/12 15:55
 * @version: V1.0
 */
public class WebActivity<T extends BasePresenter> extends WebBaseActivity<T> {
    public static Bundle buildBundle(WebConfig config) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("WebConfig", config);
        return bundle;
    }

    @Override
    public void getExtra() {
        Bundle bundle=getIntent().getExtras();
        if (bundle==null)return;
        config = (WebConfig) bundle.getSerializable("WebConfig");


    }

    @Override
    protected void setListener() {
        setShouldOverrideUrlListener(url -> {

        });
    }

    @Override
    protected String getUrl() {
        return "http://www.jd.com/";
    }


}
