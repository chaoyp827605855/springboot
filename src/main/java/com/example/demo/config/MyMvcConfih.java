package com.example.demo.config;


import com.example.demo.component.LoginHandleInterceptor;
import com.example.demo.component.MyLocalResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by Chao on 2018/7/1.
 */
@Configuration
public class MyMvcConfih implements WebMvcConfigurer {

    private final Logger logger = LoggerFactory.getLogger(MyMvcConfih.class);

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
        //registry.addViewController("/list.html").setViewName("list");
    }

    @Bean //将组件加入容器中 （区域信息解析器）
    public LocaleResolver localeResolver() {
        return new MyLocalResolver();
    }

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns("/**") 对所有进行拦截
        //excludePathPatterns("/login.html" , "/" , "/emp/login")  对这些不进行拦截
        //告知拦截器：/static/**  不需要拦截 （配置的是 路径）
        registry.addInterceptor(new LoginHandleInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login.html" , "/" , "/emp/login" , "/asserts/**");
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//    }
}
