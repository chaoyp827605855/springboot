package com.example.demo.component;

import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by Chao on 2018/7/1.
 *
 *  重写 区域信息解析器
 */
public class MyLocalResolver implements LocaleResolver {
    @Override
    //解析区域信息
    public Locale resolveLocale(HttpServletRequest request) {
        String localResolver = request.getParameter("localResolver");
        Locale locale = Locale.getDefault();
        if( !StringUtils.isEmpty(localResolver) ) {
            String[] split = localResolver.split("_");
            locale = new Locale(split[0] , split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, @Nullable HttpServletResponse httpServletResponse, @Nullable Locale locale) {

    }
}
