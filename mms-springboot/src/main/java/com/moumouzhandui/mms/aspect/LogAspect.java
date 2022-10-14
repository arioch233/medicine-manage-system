package com.moumouzhandui.mms.aspect;


import lombok.AllArgsConstructor;
import lombok.ToString;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * [权限修饰符] [包路径].[类(全部为*)].[方法](参数[所有..])
     * 包路径内点与点之间也可以使用*代表所有
     * 第一个*为返回值
     */
    @Pointcut("execution(* com.moumouzhandui.mms.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);

        logger.info("---------------------do before---------------------\n" +
                "request: {}", requestLog
        );
    }

    @After("log()")
    public void doAfter() {
        logger.info("---------------------do after---------------------");
    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result) {
        logger.info("result: {}", result);
    }

    @AllArgsConstructor
    @ToString
    private static class RequestLog {
        private String url;         // 访问的url链接
        private String ip;          // 访问者的ip
        private String classMethod; // 调用方法
        private Object[] args;      // 访问携带的参数
    }

}

