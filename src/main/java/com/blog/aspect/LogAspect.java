package com.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 切面记录日志：请求地址、ip、方法、参数、返回的东西
 */
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.blog.controller.*.*(..))")
    public void log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        logger.info("--------before--------");
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        // joinPoint.getSignature().getDeclaringType() 获取包名
        // joinPoint.getSignature().getName() 获取方法名
        String classMethod = joinPoint.getSignature().getDeclaringType() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("Request : {}", requestLog);
    }

    @After("log()")
    public void doAfter(){
        logger.info("--------after--------");
    }

    @AfterReturning(pointcut = "log()",returning = "result")
    public void doAfterReturn(Object result){
        logger.info("Result : {}", result);
    }

    // 获取参数 url ip classMethod args
    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
