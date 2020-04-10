package com.yl.markremember.bean;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yl.markremember.common.ErrorCode;


public class Result<T> {
    public Status status;
    public String message;
    public int code;
    public T data;

    public Result(@NonNull Status status, @Nullable T data, @Nullable int code) {
        this.status = status;
        this.data = data;
        this.code = code;

    }
    public Result(@NonNull Status status, String message, @Nullable T data, @Nullable int code) {
        this.status = status;
        this.message=message;
        this.data = data;
        this.code = code;

    }
    public Result() {
    }

    public static <T> Result<T> success(@Nullable T data) {
        return new Result<>(Status.SUCCESS, data, ErrorCode.NONE_ERROR.getCode());
    }
    public static <T> Result<T> success(int code,String msg, @Nullable T data) {
        return new Result<>(Status.ERROR, msg,data, code);
    }
    public static <T> Result<T> error(int code, @Nullable T data) {
        return new Result<>(Status.ERROR, data, code);
    }
    public static <T> Result<T> error(int code,String msg, @Nullable T data) {
        return new Result<>(Status.ERROR, msg,data, code);
    }
    public static <T> Result<T> loading(@Nullable T data) {
        return new Result<>(Status.LOADING, data, ErrorCode.NONE_ERROR.getCode());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
