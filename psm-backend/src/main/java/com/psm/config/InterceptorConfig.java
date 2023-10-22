package com.psm.config;

import com.psm.fetcher.interceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private Interceptor tokenInterceptor;
    
    /**
     * @author: moye
     * @date: 2023/10/22 15:38
     * @description:注册拦截器tokenInterceptor
     * @Param registry:拦截器注册器，不用用户提供
     * @return: void
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/graphql");
    }
}
