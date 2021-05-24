package com.infinitePossibilities.aopCommit;

import com.infinitePossibilities.redis.RedisPoolUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Aspect
@Component
public class CommitImpl {

    RedisPoolUtils redisPoolUtils =new RedisPoolUtils();

    @Around("@annotation(com.infinitePossibilities.aopCommit.CommitAop)")
    public Object doubleCommit(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = servletRequestAttributes.getRequest();
        String ip = request.getRemoteAddr();
        String className = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String Mathod = proceedingJoinPoint.getSignature().getName();
        System.out.println("请求的IP ："+ip);
        System.out.println("类方法 ："+className+ "." + Mathod);

        String ipKey = String.format("%s#%s",className,Mathod);
        int hashCode = Math.abs(ipKey.hashCode());
        String key = String.format("%s_%d",ip,hashCode+2);
        String value =  redisPoolUtils.get(key);
        if(StringUtils.isNotBlank(value)){
            System.out.println("请勿重新提交!");
            return "请勿重新提交!";
        }
        redisPoolUtils.setex(key, UUID.randomUUID().toString(),3);

        Object object = proceedingJoinPoint.proceed();

        return object;
    }


}
