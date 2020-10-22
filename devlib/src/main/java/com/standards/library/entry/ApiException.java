package com.standards.library.entry;

/**
 * 网络异常类
 * @author kyosky
 */
public class ApiException extends RuntimeException {

  /**
   * 错误码
   */
  private String code;

  /**
   * 错误信息
   */
  private String message;

  public ApiException(String code, String message) {
    super(message);
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
