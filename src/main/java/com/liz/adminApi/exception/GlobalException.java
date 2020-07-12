/*
 *Project: bigData
 *File: com.diyun.bigData.appApi.exeception.GlobalException.java <2019年06月17日}>
 ****************************************************************
 * 版权所有@2015 国裕网络科技  保留所有权利.
 ***************************************************************/

package com.liz.adminApi.exception;


import com.liz.adminApi.enums.ResponseCode;
import com.liz.adminApi.model.ResponseObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * @author lizhou
 * @version 1.0
 * @Date 2019年06月17日 14时06分
 */
@RestControllerAdvice
public class GlobalException {
    private static final Logger log = LogManager.getLogger(GlobalException.class);

    @ExceptionHandler
    @ResponseBody
    public ResponseObject ErrorHandler(AuthorizationException e) {
        log.error("没有通过权限验证！", e);
        return new ResponseObject(ResponseCode.FORBIDDEN,"没有通过权限验证");
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseObject exceptionHandler(Exception e){
        log.error(e.getMessage(),e);
        return new ResponseObject(ResponseCode.SERVICE_UNAVAILABLE,"服务不可用");
    }
}
