package com.junwork.chatbot.global.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    private static final String LOG_ID = "logId";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        String uuid = UUID.randomUUID().toString();
        request.setAttribute(LOG_ID, uuid);

        log.info("REQUEST [{}][{}][{}]", uuid, requestURI, handler);
        if (handler instanceof HandlerMethod hm) {
            // 호출할 컨트롤러 메서드의 모든 정보 포함
            log.info("Controller : {}, Method : {}", hm.getBean().getClass().getName(), hm.getMethod().getName());
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle [{}][{}][{}]", request.getAttribute(LOG_ID), request.getRequestURI(), handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("RESPONSE [{}][{}][{}]", request.getAttribute(LOG_ID), request.getRequestURI(), handler);
        if(ex != null){
            log.error("afterCompletion error!!", ex);
        }
    }
}