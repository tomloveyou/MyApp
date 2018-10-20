package com.standards.library.model;

import com.google.gson.annotations.SerializedName;
import com.standards.library.app.ReturnCodeConfig;

/**
 * <网络请求返回实体类>
 *
 * @author chenml@cncn.com
 * @data: 2015/11/16 20:31
 * @version: V1.0
 */
public class Response<T> {

    @SerializedName("code")
    public int code;

    @SerializedName("msg")
    public String msg;

    @SerializedName("data")
    public T data;

    public Response(int rsCode) {
        this.code = rsCode;
    }

    public Response(int rsCode, String rsMsg) {
        this.code = rsCode;
        this.msg = rsMsg;
    }

    public boolean isSuccess() {
        return code == ReturnCodeConfig.getInstance().successCode;
    }

    public boolean isEmptyCode() {
        return ReturnCodeConfig.getInstance().isEmptyCode(code);
    }

}
