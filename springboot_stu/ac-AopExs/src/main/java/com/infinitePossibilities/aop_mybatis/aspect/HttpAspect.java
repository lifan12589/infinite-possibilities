package com.infinitePossibilities.aop_mybatis.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);


    @Pointcut("execution(public * com.infinitePossibilities.aop_mybatis.controller.AopExController.* (..))")
    public void log(){

    }


    @Before("log()")
    public void doBefore(JoinPoint joinPoint){

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = servletRequestAttributes.getRequest();

        request.getServletPath();
        System.out.println("请求的url ："+request.getRequestURL());
        System.out.println("GET/POST ："+ request.getMethod());
        System.out.println("请求的IP ："+request.getRemoteAddr());
        System.out.println("类方法 ："+joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        System.out.println("参数 ："+ Arrays.toString(joinPoint.getArgs()));
//        Object[] arrays = joinPoint.getArgs();
//        for (int i = 0;i<arrays.length;i++){
//             Object a = arrays[i];
//            System.out.println(a);
//        }
//        System.out.println("被代理的对象:" + joinPoint.getTarget());
//        System.out.println("代理对象自己:" + joinPoint.getThis());

    }


    @After("log()")
    public void doAfter(JoinPoint joinPoint){
        System.out.println("请求完成");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        System.out.println("响应内容 ："+object.toString());
    }

}
