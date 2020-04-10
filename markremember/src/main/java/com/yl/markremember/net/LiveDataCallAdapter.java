package com.yl.markremember.net;

import androidx.lifecycle.LiveData;


import com.yl.markremember.bean.Result;
import com.yl.markremember.common.ErrorCode;

import java.lang.reflect.Type;
import java.net.ConnectException;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yl.markremember.common.NetConstant.REQUEST_SUCCESS_CODE;


public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<R>> {
    private final Type responseType;

    public LiveDataCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public LiveData<R> adapt(Call<R> call) {
        return new LiveData<R>() {
            AtomicBoolean started = new AtomicBoolean(false);

            @Override
            protected void onActive() {
                super.onActive();
                if (started.compareAndSet(false, true)) {
                    call.enqueue(new Callback<R>() {
                        @Override
                        public void onResponse(Call<R> call, Response<R> response) {
                            R body = response.body();
                            // 当没有信息体时通过 http code 判断业务错误
                            if (body == null && !response.isSuccessful()) {
                                Result result = new Result();
                                result.setCode(response.code());
                                try {
                                    body = (R) result;
                                } catch (Exception e) {
                                    // 可能部分接口并不是由 result 包裹，此时无法获取错误码
                                }

                            } else if (body instanceof Result) {
                                Result result = (Result) body;
                                // 当请求失败时，转义API错误码到全局错误码
                                if (result.code != REQUEST_SUCCESS_CODE) {
                                    //int errorCode = ApiErrorCodeMap.getApiErrorCode(path, result.code);
                                    result.setCode(response.code());
                                    result.setMessage(response.message());
                                }
                            }
                            postValue(body);
                        }

                        @Override
                        public void onFailure(Call<R> call, Throwable throwable) {
                           // SLog.d(LogTag.API, "onFailure:" + call.request().url().toString() + ", error:" + throwable.getMessage());
                            if (throwable instanceof ConnectException) {
                                R body = null;
                                Result result = new Result();
                                result.setCode(ErrorCode.NETWORK_ERROR.getCode());
                                try {
                                    body = (R) result;
                                } catch (Exception e) {
                                    // 可能部分接口并不是由 result 包裹，此时无法获取错误码
                                }
                                postValue(body);
                            } else {
                                postValue(null);
                            }
                        }
                    });
                }
            }
        };
    }
}
