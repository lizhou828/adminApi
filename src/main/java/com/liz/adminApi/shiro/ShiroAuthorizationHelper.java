/*
 *Project: credentials
 *File: com.glorypty.credentials.authority.shiro.ShiroAuthorizationHelper.java <2018年06月25日}>
 ****************************************************************
 * 版权所有@2015 国裕网络科技  保留所有权利.
 ***************************************************************/

package com.liz.adminApi.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author lizhou
 * @version 1.0
 * @Date 2018年06月25日 15时37分
 */
@Component
public class ShiroAuthorizationHelper {
    /**
     *
     */
    @Autowired
    private MemoryConstrainedCacheManager cacheManager;

    private static Logger log = LoggerFactory.getLogger(ShiroAuthorizationHelper.class);


    /**
     * 清除用户的授权信息
     * @param username
     */
    public void clearAuthorizationInfo(String username){
        if(log.isDebugEnabled()){
            log.debug("clear the " + username + " authorizationInfo");
        }
        Cache<Object, Object> cache = cacheManager.getCache(CustomizeRealm.class.getName()+".authorizationCache");
        Object key = new SimplePrincipalCollection(username, CustomizeRealm.class.getName() + "_0");
        cache.remove(key);//查看源代码发现底层的key是SimplePrincipalCollection类，特别注意其hashCode（）方法
    }

    /**
     * 清除当前用户的授权信息
     */
    public  void clearAuthorizationInfo(){
        if(SecurityUtils.getSubject().isAuthenticated()){
            clearAuthorizationInfo(getCurrentUsername());
        }
    }

    /**清除session(认证信息)
     * @param JSESSIONID
     */
    public  void clearAuthenticationInfo(Serializable JSESSIONID){
        if(log.isDebugEnabled()){
            log.debug("clear the session " + JSESSIONID);
        }
        //shiro-activeSessionCache 为shiro自义cache名，该名在org.apache.shiro.session.mgt.eis.CachingSessionDAO抽象类中定义
        //如果要改变此名，可以将名称注入到sessionDao中，例如注入到CachingSessionDAO的子类EnterpriseCacheSessionDAO类中
        Cache<Object, Object> cache = cacheManager.getCache("shiro-activeSessionCache");
        cache.remove(JSESSIONID);
    }

    /**
     * 获得当前用户名
     *
     * @return
     */
    public static String getCurrentUsername() {
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection collection = subject.getPrincipals();
        if (null != collection && !collection.isEmpty()) {
            return (String) collection.iterator().next();
        }
        return null;
    }



}
