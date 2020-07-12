package com.liz.adminApi.controller;

import com.github.pagehelper.util.StringUtil;
import com.liz.adminApi.constants.Constant;
import com.liz.adminApi.enums.ResponseCode;
import com.liz.adminApi.model.ResponseObject;
import com.liz.adminApi.model.SysUser;
import com.liz.adminApi.model.dto.UserLoginInfoDto;
import com.liz.adminApi.service.JedisClientIService;
import com.liz.adminApi.service.LoginIService;
import com.liz.adminApi.service.SysUserService;
import com.liz.adminApi.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lizhou on 2020/7/12.
 */
@RestController
@RequestMapping(value = "/api/login",name = "登陆")
@Api(value="登陆",tags = "登陆接口")
public class LoginController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginIService loginIService;
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private JedisClientIService jedisClientIService;

    @ApiOperation(value = "登陆")
    @RequestMapping(value="login",method = RequestMethod.POST,name = "登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.JWT_REQUEST_HEADER,value = "用于鉴权的token",required = false,dataType = "String",paramType="header")
    })
    public ResponseObject login(@RequestBody SysUser sysUser) {
        if(null == sysUser || StringUtils.isEmpty(sysUser.getUsername()) || StringUtils.isEmpty(sysUser.getPassword())){
            return ResponseObject.ok("非法参数");
        }
         /* Shiro认证 */
        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUsername(), sysUser.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        }catch(AuthenticationException ae){
            return ResponseObject.error(ae.getMessage());
        }catch(Exception e){
            logger.error("Shiro认证登陆失败：" + e.getMessage(),e);
            return ResponseObject.error("登陆失败！");
        }
        //验证是否登录成功
        if(!subject.isAuthenticated()){
            token.clear();//登陆失败
            logger.error("登陆失败,验证没有登录成功" );
            return ResponseObject.error("登陆失败！");
        }
        String username = (String) subject.getPrincipal();
        if(StringUtils.isEmpty(username)){
            return ResponseObject.error("登陆失败！");
        }
        SysUser sysUser1 = sysUserService.getByUserName(username);
        UserLoginInfoDto userLoginInfoDto = loginInfo(sysUser1);
        return new ResponseObject(userLoginInfoDto);
    }

    private UserLoginInfoDto loginInfo(SysUser sysUser) {
        String userId = sysUser.getId()+"";
        String realName = sysUser.getUsername();

        String token = JwtUtils.createNewToken(userId);
        jedisClientIService.set(Constant.JWT_REDIS_KEY+userId,token,30 * 24 * 3600L);

        if(StringUtil.isEmpty(token)){
            logger.error("token创建失败,请重试");
            return null;
        }

        UserLoginInfoDto userLoginInfoVO = new UserLoginInfoDto();
        userLoginInfoVO.setToken(token);
        userLoginInfoVO.setUserId(userId);
        userLoginInfoVO.setRealName(realName);
        return userLoginInfoVO;
    }

    @ApiOperation(value = "退出")
    @RequestMapping(value="logout",method = RequestMethod.GET,name = "退出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.JWT_REQUEST_HEADER,value = "用于鉴权的token",required = true,dataType = "String",paramType="header")
    })
    public ResponseObject logout() {
        String jwt = request.getHeader(Constant.JWT_REQUEST_HEADER);
        String userId = JwtUtils.getUserId(jwt);
        if(StringUtils.isEmpty(userId)){
            return ResponseObject.error("token已失效");
        }
        try {
            Boolean result =  true;
//            Boolean result =  redisService.delete(Constant.JWT_REDIS_KEY+userId);

            Subject subject = SecurityUtils.getSubject();
            try {
                subject.logout();
            }catch(Exception e){
                logger.error("Shiro退出系统失败：" + e.getMessage(),e);
            }
            return null!= result &&result ? ResponseObject.ok("退出成功") : ResponseObject.error("退出失败");
        }catch (Exception e){
            logger.error("执行退出发生异常：" + e.getMessage(), e);
        }
        return ResponseObject.error("退出异常");
    }
}
