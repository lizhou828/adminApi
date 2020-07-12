package com.liz.adminApi.enums;

/**
 * Created by Administrator on 2020/4/16.
 */
public class ResponseCode {
    public static final int OK = 200;

    public static final String OK_MSG = "处理成功";

    public static final int NOT_LOGIN = 401;

    public static final int OPERATE_FAIL = 444;

    public static final String NOT_LOGIN_MSG = "未认证身份";

    public static final int ERROR = 500;

    public static final String ERROR_MSG = "服务异常";

    public static final String MESSAGE = "message";

    public static final String SUCCESS = "success";

    public static final String FAIL = "fail";

    public static final int SERVICE_UNAVAILABLE = 503;

    public static final int FORBIDDEN = 403;

    public static final String FORBIDDEN_MESSAGE = "未授权";
}
