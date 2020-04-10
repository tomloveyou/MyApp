package com.yl.markremember.net;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.yl.markremember.bean.Result;
import com.yl.markremember.common.ErrorCode;
import com.yl.markremember.common.NetConstant;


public abstract class NetworkOnlyResource<ResultType,RequestType> {
    private final ThreadManager threadManager;

    private final MediatorLiveData<Result<ResultType>> result = new MediatorLiveData<>();

    @MainThread
    public NetworkOnlyResource() {
        this.threadManager = ThreadManager.getInstance();
        if(threadManager.isInMainThread()) {
            init();
        }else {
            threadManager.runOnUIThread(() -> init());
        }

    }
    private void init(){
        result.setValue(Result.loading(null));
        fetchFromNetwork();
    }

    private void fetchFromNetwork() {
        LiveData<RequestType> apiResponse = createCall();
        result.addSource(apiResponse, response -> {
            result.removeSource(apiResponse);
            if (response != null) {
                if(response instanceof Result){
                    Result dd= ((Result)response);
                    int code =dd.code;
                    if(code != NetConstant.REQUEST_SUCCESS_CODE){
                        result.setValue(Result.error(code, code==401?"登录失效":dd.getMessage(),null));
                        return;
                    } else {


                        // do nothing
                    }
                }
                threadManager.runOnWorkThread(() -> {
                    ResultType resultType = transformRequestType(response); //自定义的
                    if(resultType == null){
                        resultType = transformDefault(response); //默认
                    }
                    try {
                        saveCallResult(resultType);
                    } catch (Exception e) {
                       // SLog.e(LogTag.DB, "saveCallResult failed:" + e.toString());
                    }
                    result.postValue(Result.success(resultType));
                });
            } else {
                result.setValue(Result.error(ErrorCode.API_ERR_OTHER.getCode(), null));
            }
        });
    }

    /**
     * 重写此方法完成请求类型和响应类型转换
     * 如果是请求结果是 Result<ResultType>  类型则不用重写
     * @param response
     * @return
     */
    @WorkerThread
    protected ResultType transformRequestType(RequestType response){
        return null;
    }

    @WorkerThread
    private ResultType transformDefault(RequestType response){
        if(response instanceof Result){
            Object result = ((Result) response).getData();
            if(result != null){
                try {
                    return  (ResultType)result;
                } catch (Exception e){
                    return null;
                }
            }else {
                return null;
            }
        }else{
            return null;
        }
    }

    public LiveData<Result<ResultType>> asLiveData() {
        return result;
    }

    @WorkerThread
    protected void saveCallResult(@NonNull ResultType item){
    }

    @NonNull
    @MainThread
    protected abstract LiveData<RequestType> createCall();
}