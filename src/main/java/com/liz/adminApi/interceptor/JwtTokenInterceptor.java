/*
 *Project: bigData
 *File: com.diyun.bigData.appApi.interceptor.JwtTokenInterceptor.java <2019年05月23日}>
 ****************************************************************
 * 版权所有@2015 国裕网络科技  保留所有权利.
 ***************************************************************/

package com.liz.adminApi.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.liz.adminApi.constants.Constant;
import com.liz.adminApi.enums.ResponseCode;
import com.liz.adminApi.model.ResponseObject;
import com.liz.adminApi.service.JedisClientIService;
import com.liz.adminApi.shiro.AuthorizationUtil;
import com.liz.adminApi.utils.JwtUtils;
import com.liz.adminApi.utils.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.Subject;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.SignatureException;
import java.text.MessageFormat;


/**
 * @author lizhou
 * @version 1.0
 * @Date 2019年05月23日 14时01分
 */
public class JwtTokenInterceptor implements HandlerInterceptor {

    private static final Logger log = LogManager.getLogger(JwtTokenInterceptor.class);

    @Autowired
    private JedisClientIService jedisClientIService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //自动排除生成token的路径,并且如果是options请求是cors跨域预请求，设置allow对应头信息
        if( request.getRequestURI().equals("/api/login/login") || request.getRequestURI().startsWith("/api/register")
            || request.getRequestURI().contains("/api/index")
            || request.getRequestURI().contains("/api/wechat")
            || request.getRequestURI().indexOf("swagger") > 0
            || request.getRequestURI().indexOf("setNewPassword") > 0
            || request.getRequestURI().indexOf("sendSmsCode") > 0
            || request.getRequestURI().indexOf("verifySmsCode") > 0
            || request.getRequestURI().indexOf("page/") > 0
            || request.getRequestURI().indexOf("static/") > 0
            || request.getRequestURI().indexOf("h5/") > 0
            || RequestMethod.OPTIONS.toString().equals(request.getMethod())){
            return true ;
        }

        //获取头信息
        final String authHeader = request.getHeader(Constant.JWT_REQUEST_HEADER) ;

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {

            //如果取出的token信息为空
            if (StringUtils.isEmpty(authHeader)){
                throw new SignatureException("无法获取"+Constant.JWT_REQUEST_HEADER+"！");
            }

            final Claims claims = JwtUtils.verify(authHeader);
            String subject = claims.getSubject();

            //验证token
            String tokenInRedis = jedisClientIService.get(Constant.JWT_REDIS_KEY+subject);

            if (StringUtils.isEmpty(tokenInRedis)){
                throw new SignatureException("Redis中无法获取token信息，请重新获取！");
            }
            tokenInRedis = StringUtil.formatID(tokenInRedis);

            //token是否与客户端传来的一致
            if(!tokenInRedis.equals(authHeader)){
                throw new SignatureException("与Redis中token信息不一致，请重新获取！");
            }
        }catch (SignatureException | ExpiredJwtException e){
            log.error("Token needed!" + e.getMessage(),e);
            //输出对象
            PrintWriter writer = response.getWriter();
            ResponseObject<String> rest = new ResponseObject(ResponseCode.NOT_LOGIN,"token已过期");
            String responseJson = JSONObject.toJSONString(rest);
            //输出error消息
            writer.write(responseJson);
            writer.close();
            return false;
        } catch (final Exception e) {
            log.error("非法token!"+ e.getMessage(),e);
            //输出对象
            PrintWriter writer = response.getWriter();
            ResponseObject<String> rest = new ResponseObject(ResponseCode.NOT_LOGIN,"token验证失败");
            String responseJson = JSONObject.toJSONString(rest);
            //输出error消息
            writer.write(responseJson);
            writer.close();
            return false;
        }
//        boolean canAccessURL = isAccessURL(request,response);
//        if(!canAccessURL){
//            //输出对象
//            PrintWriter writer = response.getWriter();
//            ResponseObject<String> rest = new ResponseObject(ResponseCode.FORBIDDEN,ResponseCode.FORBIDDEN_MESSAGE);
//            String responseJson = JSONObject.toJSONString(rest);
//            //输出error消息
//            writer.write(responseJson);
//            writer.close();
//            return false;
//        }
        return true;
    }

    /**
     * 动态URL过滤
     * @author hardy<2015年8月3日>
     * @param request
     * @param response
     * @return
     */
    private boolean isAccessURL(ServletRequest request, ServletResponse response){
        String permissionUrl = ((HttpServletRequest)request).getServletPath();

        if(org.apache.commons.lang.StringUtils.isEmpty(permissionUrl))
            return false;

        // 模块操作(忽略功能模块首页)
        String permissionMethod = permissionUrl.substring(permissionUrl.lastIndexOf("/")==-1 ? 0 : permissionUrl.lastIndexOf("/")+1).split("\\.")[0];
        if(org.apache.commons.lang.StringUtils.isNotEmpty(permissionMethod) && permissionMethod.equalsIgnoreCase("index")){
            return true;
        }
        String lower = permissionMethod.toLowerCase();
        if( lower.contains("list") || lower.contains("query") || lower.contains("search") || lower.contains("find")|| lower.contains("get")  || lower.contains("detail")){
            return true;//查询的 不做鉴权处理
        }


        // 模块标记
        String permissionMark = AuthorizationUtil.getMenuCodeByUrl(permissionUrl);
        if(org.apache.commons.lang.StringUtils.isNotEmpty(permissionMark)){
            log.debug(MessageFormat.format("Authorization Filter ==> {0}:{1}", permissionMark, permissionMethod));
            //以“ign”、“ignore”开头的方法名，不做鉴权处理
            if(permissionMethod.startsWith("ign") || permissionMethod.startsWith("ignore") || org.apache.commons.lang.StringUtils.isEmpty(permissionMethod.trim())){//个别方法名公用的、以“/”结尾的，不做处理
                log.debug("Authorization Filter ==> Result:Ignore Method");
                return true;
            }
            // 权限校验
            if (SecurityUtils.getSubject().isPermitted(MessageFormat.format("{0}:{1}", permissionMark, permissionMethod))) {
                return true;
            }
        }
        log.debug("Authorization Filter==>Result:UnAuthorized");
        return false;
    }

    /**
     * 根据传入的类型获取spring管理的对应bean
     * @param clazz 类型
     * @param request 请求对象
     * @param <T>
     * @return
     */
    private <T> T getBean(Class<T> clazz ,HttpServletRequest request){
        BeanFactory factory = (BeanFactory) WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return factory.getBean(clazz);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
