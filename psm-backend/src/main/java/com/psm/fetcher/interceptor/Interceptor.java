package com.psm.fetcher.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class Interceptor implements HandlerInterceptor {

    @Value("${SkipIdentify}")
    private List<String> SkipIdentify;

    /**
     * @author: moye
     * @date: 2023/10/22 16:37
     * @description:请求拦截器，用于拦截未授权的请求。
     * @Param request: http请求体
     * @Param response: http响应体
     * @Param handler: mvc响应方法
     * @return: boolean: true为可以进入fetcher处理，false为不再终止处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        return true;
    }
}
