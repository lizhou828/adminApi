/*
 *Project: credentials
 *File: com.glorypty.credentials.authority.config.ShiroConfig.java <2018年05月26日}>
 ****************************************************************
 * 版权所有@2015 国裕网络科技  保留所有权利.
 ***************************************************************/

package com.liz.adminApi.shiro;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Configuration 申明这是一个配置类相当于xml配置文件，@Bean表示这是一个Spring管理的bean
 * @author lizhou
 * @version 1.0
 * @Date 2018年05月26日 10时37分
 */
@Configuration
public class ShiroConfiguration {

    @Bean
    public MyCredentialsMatcher myCredentialsMatcher(){
        return new MyCredentialsMatcher();
    }
    @Bean
    public CustomizeRealm authenticationRealm(){
        CustomizeRealm authenticationRealm = new CustomizeRealm();
        authenticationRealm.setCredentialsMatcher(myCredentialsMatcher());
        return authenticationRealm;
    }

    @Bean
    public MemoryConstrainedCacheManager memoryConstrainedCacheManager(){
        return new MemoryConstrainedCacheManager();//shiro中缓存用户的授权信息
    }

    /**
     * SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类。
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authenticationRealm()); // 继承自AuthorizingRealm的自定义Realm
        securityManager.setCacheManager(memoryConstrainedCacheManager()); //用户授权信息Cache
        return securityManager;
    }

    /**
     *
     * LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
     * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
     * 主要是AuthorizingRealm类的子类，以及EhCacheManager类。
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor(); // 保证实现了Shiro内部lifecycle函数的bean执行
    }

    /**
     * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，
     * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor (){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public AuthorizationJhFilter authorizationJhFilter(){
        return new AuthorizationJhFilter();
    }


    /**
     * ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。
     * 它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
     *
     Filter名称	 类路径
     anon	 org.apache.shiro.web.filter.authc.AnonymousFilter(可匿名访问)
     authc	 org.apache.shiro.web.filter.authc.FormAuthenticationFilter(需要登陆认证)
     authcBasic	 org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
     logout	 org.apache.shiro.web.filter.authc.LogoutFilter
     noSessionCreation	 org.apache.shiro.web.filter.session.NoSessionCreationFilter
     perms	 org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter(需要权限)
     port	 org.apache.shiro.web.filter.authz.PortFilter
     rest	 org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter
     roles	 org.apache.shiro.web.filter.authz.RolesAuthorizationFilter(角色)
     ssl	 org.apache.shiro.web.filter.authz.SslFilter
     user	 org.apache.shiro.web.filter.authc.UserFilter
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setLoginUrl("/login/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/401");
        Map<String,String> map = new HashMap<>();

        /* 可以匿名访问的URL */
        String [] anonList = {"/", "/index", "/login", "/login/index", "/caLogin","/usbKeyCALogin", "/error/*", "/register/*",
                "/findPassword/*", "/logOut", "/picCode", "/test*", "/fastdfs/*","/help/index","/charge/alipay/notify","/charge/wechat/notify"
                ,"/js/**","/css/**","/image/**","/*.ico","/error/**"};
        for(String anonStr: anonList){
            map.put(anonStr,"anon");
        }

        /* 需要登录认证后才能访问的URL */
        map.put("/archives/**","authc");//档案管理相关
        map.put("/consume/**","authc");
        map.put("/gift/**","authc");
        map.put("/order/**","authc");
        map.put("/companyAccount/**","authc");
        map.put("/company/**","authc");
        map.put("/companySeal/**","authc");
        map.put("/companyUkeyApply/**","authc");
        map.put("/companyAccount/**","authc");
        map.put("/task/**","authc");

        /* 需要登录认证、相关权限后才能访问的URL */
        map.put("/sysRole/**","authc,perms");

        /* 子账户对增删改的操作，必须要设置权限才能操作 */
        map.put("/**/add","authc,perms");
        map.put("/**/onAdd","authc,perms");
        map.put("/**/save","authc,perms");
        map.put("/**/edit","authc,perms");
        map.put("/**/onEdit","authc,perms");
        map.put("/**/update","authc,perms");
        map.put("/**/delete","authc,perms");
        map.put("/**/batchDelete","authc,perms");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        Map<String, Filter> filtersMap = new HashMap<>();
        filtersMap.put("perms",authorizationJhFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public FilterRegistrationBean filterProxy(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        /*
             DelegatingFilterProxy就是一个对于servlet filter的代理，用这个类的好处主要是通过Spring容器来管理servlet filter的生命周期，
             还有就是如果filter中需要一些Spring容器的实例，可以通过spring直接注入，另外读取一些配置文件这些便利的操作都可以通过Spring来配置实现。
        *
        */
        DelegatingFilterProxy httpBasicFilter = new DelegatingFilterProxy();
        registrationBean.setFilter(httpBasicFilter);
        Map<String,String> m = new HashMap<String,String>();
        m.put("targetBeanName","shiroFilter");//targetBeanName，对应到Spring配置中的beanName
        m.put("targetFilterLifecycle","true");//如果要保留Filter原有的init，destroy方法的调用，还需要配置初始化参数targetFilterLifecycle为true，该参数默认为false
        registrationBean.setInitParameters(m);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST,DispatcherType.FORWARD,DispatcherType.INCLUDE,DispatcherType.ERROR);
        return registrationBean;
    }

}
