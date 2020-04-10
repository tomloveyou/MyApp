package com.yl.markremember.common;

import android.app.Application;

import com.yl.markremember.R;


/**
 * 全局错误码枚举
 * <p>
 * 因为目前不同的 API 会根据业务情况返回相同的错误码，所以需要根据 API 区分每个错误码的提示，所以做出以下处理。
 * <p>
 * API 类型的错误码规则：
 * 每个 API 的错误码由 API 的 url 对应的代码{@link cn.rongcloud} + API 对应的错误码
 * 比如登录接口 LOGIN，此 API 的 url 代码为 1，当 API 返回错误码 为 1000  且错误码偏移为 10000 时,对应 ErrorCode 为 11000
 */
public enum ErrorCode {
    API_COMMON_ERROR(400, 0),
    API_ERR_OTHER(-2, R.string.common_network_error_and_retry_after),
    API_ERR_PASSWORD_ERROR(401, R.string.common_password_error),
    IM_ERROR(-4, 0),
    RTC_ERROR(-5, 0),
    IM_TOKEN_ERROR(-6, 0),
    QRCODE_ERROR(-7, 0),
    UNKNOWN_ERROR(999999, 0),
    UNAUtTH_ERROR(300401, R.string.common_network_unaauth),
    NETWORK_ERROR(-3, R.string.common_network_unavailable),
    NONE_ERROR(0, R.string.common_network_success),;



    private int code;
    private int messageResId;
    private static Application application;

    ErrorCode(int code, int messageResId) {
        this.code = code;
        this.messageResId = messageResId;
    }

    public int getCode() {
        return code;
    }

    public int getMessageResId() {
        return messageResId;
    }

    public String getMessage() {
        if (application == null) {
           // SLog.e(LogTag.COMMON, "ErrorCode getMessage need init first.");
            return "";
        }
        // 默认错误提示语
        String msg = "";
        if (messageResId > 0) {
            msg = application.getResources().getString(messageResId);
        }
        return msg;
    }

    public static ErrorCode fromCode(int code) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.code == code)
                return errorCode;
        }

        return UNKNOWN_ERROR;
    }

    public static void init(Application application) {
        ErrorCode.application = application;
    }

}
