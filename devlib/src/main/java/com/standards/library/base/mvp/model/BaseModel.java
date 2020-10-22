package com.standards.library.base.mvp.model;

import android.content.Context;

import com.blankj.utilcode.util.NetworkUtils;
import com.standards.library.base.App;
import com.standards.library.network.CallBack;
import com.standards.library.network.retrofit.RetrofitDao;
import com.standards.library.network.retrofit.RetrofitUtils;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Orange_zhi
 * @date on 2019/5/24.
 */

public abstract class BaseModel<S> {
    protected Context mContext;
    protected S modelApi;
    private CompositeDisposable mModelDisposable;

    public BaseModel() {
        this.mContext = App.getContext();
        if (getApiClass() == null && getType() == null) {
            throw new NullPointerException("Statement  a retrofit  ApiService !!");
        }
        if (getApiClass() != null) {
            modelApi = RetrofitUtils.getInstance().getService(getApiClass());
        } else {
            modelApi = RetrofitUtils.getInstance().getService(getType());
        }
        mModelDisposable = new CompositeDisposable();

    }

    /**
     * @return 根据声明的接口类型来获取接口类型
     */
    private Class<S> getType() {
        Class clazz = getClass();
        Type superClass = clazz.getGenericSuperclass();
        if (superClass instanceof ParameterizedType) {
            return (Class<S>) ((ParameterizedType) superClass).getActualTypeArguments()[0];
        }
        return null;
    }

    /**
     * getTyope 不可使用的时候使用该方法获取声明的接口类型
     *
     * @return 非必须实现，
     * 返回ApiService 接口最安全
     */
    protected Class<S> getApiClass() {
        return null;
    }

    protected S getModelApi() {
        return modelApi;
    }

    /**
     * 所有数据层请求网络使用该方法
     */
    protected <T> void requestNetwork(Observable<T> observable, CallBack<T> callBack) {
        if (!NetworkUtils.isConnected()) {
            callBack.onError(new UnknownHostException());
        }
        mModelDisposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(callBack));
    }

    public void detach() {
        if (mModelDisposable != null && !mModelDisposable.isDisposed()) {
            mModelDisposable.dispose();
        }
    }

    /**
     * 统一所有model 回调
     * 也是model 层回调的基类接口
     */
    public interface BaseModelCallBack {
        /**
         * 统一失败的回调
         * @param errorCode
         * @param msg
         */
        void onFailed(String errorCode, String msg);

    }
}
