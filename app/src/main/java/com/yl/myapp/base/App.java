package com.yl.myapp.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;


import com.standards.library.app.AppContext;
import com.standards.library.app.ReturnCode;
import com.standards.library.app.ReturnCodeConfig;
import com.standards.library.cache.DataProvider;
import com.standards.library.network.NetworkConfig;
import com.standards.library.util.LogUtil;
import com.yl.myapp.BuildConfig;

import org.litepal.LitePal;

import cn.bmob.v3.Bmob;


/**
 * <请描述这个类是干什么的>
 *
 * @data: 16/9/19 下午2:40
 * @version: V1.0
 */
public class App extends com.standards.library.base.App {
    private static Context sContext;

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        LitePal.initialize(this);
        MultiDex.install(this);
        Bmob.initialize(this, "e05a803219f6136261f562ed0fd4dadd");
        NetworkConfig.setBaseUrl(BuildConfig.HOST_URL);

    }

}
