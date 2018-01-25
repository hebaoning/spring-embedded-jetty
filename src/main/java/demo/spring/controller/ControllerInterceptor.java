package demo.spring.controller;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

//    @Pointcut("execution(* demo.spring.controller.HelloController.getUser(..))")
    @Pointcut("within(demo.spring.controller..*)")
    public void request() {}

    @Around("request()")
    public Object interceptRequest(ProceedingJoinPoint jp) {
        String signature = null;
        if (logger.isDebugEnabled()) {
            signature = jp.getSignature().toShortString();
        }
        try {
            logger.debug(">>> {}", signature);
            return jp.proceed();
        } catch (Throwable e) {
            logger.error("proceed join point error", e);
        } finally {
            logger.debug("<<< {}", signature);
        }
        return null;
    }
}
