package com.yl.myapp.api;

import com.google.gson.JsonParseException;
import com.standards.library.app.AppContext;
import com.standards.library.app.ReturnCode;
import com.standards.library.model.BaseInfo;
import com.standards.library.model.Response;
import com.standards.library.rx.ErrorThrowable;
import com.yl.myapp.R;
import com.yl.myapp.api.Exception.RetryWhenNetworkException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * <请描述这个类是干什么的>
 *
 * @data: 2016/7/7 13:39
 * @version: V1.0
 */
public class ResponseHandle {

    public static <T> Func1<Throwable, Observable<? extends T>> errorResumeFunc() {
        return throwable -> {
            if (!AppContext.isNetworkAvailable()) {
                return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_NO_NETWORK, AppContext.getContext().getString(R.string.load_no_network)));
            }
            if (throwable instanceof UnknownHostException) {
                return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_ERROR_TYPE_ERROR, AppContext.getString(R.string.service_conn_fail)));
            } else if (throwable instanceof JsonParseException) {
                return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_TIMEOUT_ERROR, AppContext.getString(R.string.load_jsonpaser_error)));
            } else if (throwable instanceof SocketTimeoutException || throwable instanceof ConnectException) {
                return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_TIMEOUT_ERROR, AppContext.getString(R.string.load_time_out)));
            } else if (throwable instanceof ErrorThrowable) {
                Observable.error(throwable);
            }
            return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_UNKNOWN_ERROR, AppContext.getString(R.string.load_unknow_error)));
        };
    }

    // 读取实体数据
    private static class ReadDataFunc<E> implements Func1<Response<E>, Observable<E>> {
        @Override
        public Observable<E> call(Response<E> x) {
            if (x.code == ReturnCode.CODE_SUCCESS) {
                if (x.data != null && x.data instanceof BaseInfo) {
                    ((BaseInfo) x.data).setSussceHintMsg(x.msg);
                }
                return Observable.just(x.data);
            } else {
                return Observable.error(new ErrorThrowable(x.code, (x.code == ReturnCode.CODE_TOKEN_INVALID) ? "" : x.msg));//用户失效不显示提示语
            }
        }
    }

    //获取response数据
    public static class ReadResponse implements Func1<Response, Observable<Response>> {

        @Override
        public Observable<Response> call(Response x) {
            if (x.code == ReturnCode.CODE_SUCCESS) {
                return Observable.just(x);
            } else {
                return Observable.error(new ErrorThrowable(x.code, x.code == ReturnCode.CODE_TOKEN_INVALID ? "" : x.msg));
            }
        }
    }

    //新建处理实体类型数据
    public static ReadDataFunc newEntityData() {
        return new ReadDataFunc();
    }

    public static ReadResponse newResponseData() {
        return new ReadResponse();
    }


    public static <T> Observable.Transformer<T, T> applySchedulersWithToken() {
        return tObservable -> tObservable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorResumeNext(errorResumeFunc())
                .retryWhen(new RetryWhenNetworkException())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
