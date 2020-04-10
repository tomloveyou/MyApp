package com.yl.markremember.net;




import com.yl.markremember.bean.Result;
import com.yl.markremember.common.ErrorCode;
import com.yl.markremember.common.ResultCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallBackWrapper<R> implements Callback<Result<R>> {
    private ResultCallback<R> mCallBack;

    public CallBackWrapper(ResultCallback<R> callBack) {
        mCallBack = callBack;
    }

    @Override
    public void onResponse(Call<Result<R>> call, Response<Result<R>> response) {
        Result<R> body = response.body();
        if (body != null) {
            int code = body.getCode();
            if (code == 0) {
                mCallBack.onSuccess(body.getData());
            } else {
                mCallBack.onFail(code);
            }
           // SLog.e(LogTag.API, "url:" + call.request().url().toString()
              //      + " ,code:" + body.getCode());
        } else {
          //  SLog.e(LogTag.API, "url:" + call.request().url().toString() + ", no response body");
            mCallBack.onFail(ErrorCode.API_ERR_OTHER.getCode());
        }
    }

    @Override
    public void onFailure(Call<Result<R>> call, Throwable t) {
     //   SLog.e(LogTag.API, call.request().url().toString() + " - " + (t != null ? t.getMessage() : ""));
        mCallBack.onFail(ErrorCode.NETWORK_ERROR.getCode());
    }
}
