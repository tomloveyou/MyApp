package com.standards.library.network.rxframework;



import androidx.lifecycle.LiveData;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<com.standards.library.model.Response>> {
    private final Type responseType;
    public LiveDataCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public LiveData<com.standards.library.model.Response> adapt(Call<R> call) {
        return new LiveData<com.standards.library.model.Response>() {
            AtomicBoolean started = new AtomicBoolean(false);
            @Override
            protected void onActive() {
                super.onActive();
                if (started.compareAndSet(false, true)) {
                    call.enqueue(new Callback<R>() {
                        @Override
                        public void onResponse(Call<R> call, Response<R> response) {

                            com.standards.library.model.Response responseData = new com.standards.library.model.Response();
                            if(response.isSuccessful()){
                                responseData = (com.standards.library.model.Response) response.body();
                            }else{
                                try {
                                    responseData.setMsg(response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                responseData.setData(null);
                                responseData.setCode(response.code());
                            }
                            postValue(responseData);
                        }

                        @Override
                        public void onFailure(Call<R> call, Throwable throwable) {
                            com.standards.library.model.Response responseData = new com.standards.library.model.Response();
                            responseData.setCode(500);
                            responseData.setData(null);
                            responseData.setMsg(throwable.getMessage());

                            postValue(responseData);
                        }
                    });
                }
            }
        };
    }
}

