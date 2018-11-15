package com.shaulayh.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("pre handle method was called {}", handler);
        log.debug("pre handle request is {}", request.getRequestURL());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("post handle method was called {}", handler);
        log.debug("post handle request is {}", request);
        log.info("the model and view is {}", modelAndView.getModel());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("after Completion method was called {}", handler);
        log.debug("after completion request is {}", request.getRequestURL());
        log.info("the response was ", response.getStatus());
    }
}
