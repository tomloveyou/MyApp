package com.standards.library.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;


import com.alibaba.android.arouter.launcher.ARouter;
import com.standards.library.app.AppContext;
import com.standards.library.app.ReturnCode;
import com.standards.library.app.ReturnCodeConfig;
import com.standards.library.arounter.ARouterUtils;
import com.standards.library.cache.DataProvider;
import com.standards.library.network.NetworkConfig;
import com.standards.library.util.LogUtil;

import cn.bmob.v3.Bmob;


/**
 * <请描述这个类是干什么的>
 *
 * @data: 16/9/19 下午2:40
 * @version: V1.0
 */
public class App extends Application {
    private static Context sContext;

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext.getInstance().init(this);
        sContext = getContext();
        LogUtil.init(true, "lucky");
        DataProvider.init(this);
        Bmob.initialize(this, "e05a803219f6136261f562ed0fd4dadd");
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(this);
        ReturnCodeConfig.getInstance().initReturnCode(ReturnCode.CODE_SUCCESS, ReturnCode.CODE_EMPTY);
    }

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        Log.d("Application", "onTerminate");
        super.onTerminate();
        ARouterUtils.destroy();
    }
}
