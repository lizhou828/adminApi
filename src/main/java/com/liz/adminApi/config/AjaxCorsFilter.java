package com.liz.adminApi.config;

import com.liz.adminApi.interceptor.JwtTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
public class AjaxCorsFilter implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/api/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                //是否允许证书 不再默认开启
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("*")
                //设置请求头
                .allowedHeaders("*")
                //跨域允许时间
                .maxAge(3600);
    }



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    //将自定义拦截器注册到spring bean中
    //这里注册拦截器时要以bean的方式注册进去，这样启动时拦截器才能取到配置文件的值。
    @Bean
    public JwtTokenInterceptor jwtTokenInterceptor() {
        return new JwtTokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludeList = new ArrayList<>();
        excludeList.add("");
        excludeList.add("/");
        excludeList.add("/test/**");
        excludeList.add("/test**");
        excludeList.add("/api");
        excludeList.add("/api/index");
        excludeList.add("/api/login/login");
        excludeList.add("/api/area/**");
        excludeList.add("/api/register/**");
        excludeList.add("/swagger-ui.html");
        excludeList.add("/doc.html");
        excludeList.add("/webjars/**");
        excludeList.add("/csrf");
        excludeList.add("/404");
        excludeList.add("/error");
        excludeList.add("/page/**");
        excludeList.add("/h5/**");
        excludeList.add("/static/**");
        excludeList.add("/api/payNotify/**");
        //JWT TOKEN拦截器
        registry.addInterceptor(jwtTokenInterceptor())
                .excludePathPatterns(excludeList);
    }
}