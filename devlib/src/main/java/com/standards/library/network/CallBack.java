package com.standards.library.network;

import android.text.TextUtils;


import com.google.gson.JsonSyntaxException;
import com.standards.library.R;
import com.standards.library.base.App;
import com.standards.library.entry.ApiException;
import com.standards.library.entry.HttpCodeConstants;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * 网络数据返回回调
 *
 * @author Orange_zhi
 */
public abstract class CallBack<T> extends DisposableObserver<T> {


    protected CallBack() {
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            if (!TextUtils.isEmpty(e.getMessage())
                    && (e.getMessage().contains(HttpCodeConstants.CODE_401)
                    || (e.getMessage().contains(HttpCodeConstants.CODE_400)))) {
                onFailed("", App.getContext().getString(R.string.un_authorized));
            } else if (e.getMessage().contains(HttpCodeConstants.CODE_403)) {
                onFailed("", App.getContext().getString(R.string.un_authorized_forbidden));
            } else {
                onFailed(
                        "",
                        e.getMessage().contains("500")
                                ? App.getContext().getString(R.string.network_time_out)
                                : ((HttpException) e).message());
            }
        } else if (e instanceof JsonSyntaxException) {
            onFailed("", App.getContext().getString(R.string.data_parse_exception));
        } else if (e instanceof ApiException) {
            onFailed(((ApiException) e).getCode(), e.getMessage());
        } else if (e instanceof SocketTimeoutException) {
            onFailed("", App.getContext().getString(R.string.network_time_out));
        } else if (e instanceof UnknownHostException) {
            onFailed("", App.getContext().getString(R.string.network_no));
        } else {
            onFailed("", e.getMessage());
        }

    }


    /**
     * 请求失败
     *
     * @param code 错误码
     * @param msg  错误信息
     */
    protected abstract void onFailed(String code, String msg);

    @Override
    public void onComplete() {

    }

    /**
     * 请求成功
     *
     * @param t 返回的解析后的数据
     */
    protected abstract void onSuccess(T t);
}

