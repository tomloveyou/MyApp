package com.yl.myapp.base;

import android.app.Application;
import android.content.Context;



import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
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
        NetworkConfig.setBaseUrl(BuildConfig.HOST_URL);

    }

}
