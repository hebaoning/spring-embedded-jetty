package demo.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KafkaInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(KafkaInterceptor.class);

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("kafka request {}", request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        logger.debug("kafka response {}", response.getStatus());
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, @Nullable Exception ex) throws Exception {
        logger.debug("kafka request completion");
    }
}
