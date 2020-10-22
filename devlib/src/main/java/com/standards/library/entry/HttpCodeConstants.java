package com.standards.library.entry;

/**
 * desc:解析或者服务端异常的常量
 *
 */
public class HttpCodeConstants {
  /** 返回成功码 服务器响应成功*/
  public static final String CODE_OK = "200";
  /* public static final String CODE_OK = "201";
  public static final String CODE_OK = "204";
  public static final String CODE_OK = "403";*/
  /** 认证失败或token无效 */
  public static final String INVALID_CODE = "401";

  public static final String CODE_401 = "HTTP 401";
  public static final String CODE_400 = "HTTP 400";
  public static final String CODE_403 = "HTTP 403";
}
