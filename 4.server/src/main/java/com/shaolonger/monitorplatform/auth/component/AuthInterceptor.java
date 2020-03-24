package com.shaolonger.monitorplatform.auth.component;

import com.shaolonger.monitorplatform.auth.annotation.AuthIgnore;
import com.shaolonger.monitorplatform.utils.ResponseResultBase;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuthIgnore annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthIgnore.class);
        } else {
            return true;
        }

        // 如果有AuthIgnore注解，则不验证token直接放行
        if (annotation != null) {
            return true;
        }

        // 获取token
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            throw new Exception("token不能为空");
        }
        return true;
    }
}
