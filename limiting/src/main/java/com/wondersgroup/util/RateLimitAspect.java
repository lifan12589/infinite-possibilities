package com.wondersgroup.util;

import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
@Scope
@Aspect
public class RateLimitAspect {

    final double permitsPerSecond = 5.0;        //每秒生成5个令牌
    final long warmupPeriod = 1;                //在warmupPeriod时间内RateLimiter会增加它的速率，在抵达它的稳定速率或者最大速率之前
    final TimeUnit timeUnit = TimeUnit.SECONDS; //参数warmupPeriod 的时间单位
    /*
     * 创建一个稳定输出令牌的RateLimiter，保证了平均每秒不超过qps个请求
     * 当请求到来的速度超过了qps，保证每秒只处理qps个请求
     * 当这个RateLimiter使用不足(即请求到来速度小于qps)，会囤积最多qps个请求
     *
     * 创建的是SmoothBursty 实例 平滑稳定
     */
    RateLimiter rateLimiter = RateLimiter.create(permitsPerSecond);

    /**
     *
     * 根据指定的稳定吞吐率和预热期来创建RateLimiter，
     * 这里的吞吐率是指每秒多少许可数（通常是指QPS，每秒多少查询），
     * 在这段预热时间内，RateLimiter每秒分配的许可数会平稳地增长直到预热期结束时达到其最大速率（只要存在足够请求数来使其饱和）。
     * 同样地，如果RateLimiter 在warmupPeriod时间内闲置不用，它将会逐步地返回冷却状态。
     * 也就是说，它会像它第一次被创建般经历同样的预热期。
     * 返回的RateLimiter 主要用于那些需要预热期的资源，这些资源实际上满足了请求（比如一个远程服务），
     * 而不是在稳定（最大）的速率下可以立即被访问的资源。
     * 返回的RateLimiter 在冷却状态下启动（即预热期将会紧跟着发生），并且如果被长期闲置不用，它将回到冷却状态
     *
     * 创建的是SmoothWarmingUp实例    平滑预热
     */
    RateLimiter rl = RateLimiter.create(permitsPerSecond,warmupPeriod,timeUnit);


    //设置业务切入点为标注了自定义注解的位置
    @Pointcut("@annotation(com.wondersgroup.anno.RateLimitAnno)")
    public void aspectService(){ }

    //统计
    int countSuccess,countFail = 0;

    //环绕通知
    @Around("aspectService()")
    public Object aroundMsg(ProceedingJoinPoint joinPoint){

        Object obj = null;

        boolean flag = rateLimiter.tryAcquire(); // 在无延迟下的情况下获得

        // 从RateLimiter获取一个许可，该方法会被阻塞直到获取到请求。
        // 如果存在等待的情况的话，告诉调用者获取到该请求所需要的睡眠时间。该方法等同于acquire(1)。
        //double waitTime = rabuyGoodsteLimiter.acquire(); 我非要得到令牌才返回

        try{

            if(flag){ //如果获取了令牌，则可以继续执行业务层面的逻辑
                obj = joinPoint.proceed();

                countSuccess++;//并发时，统计不准确!!!

            }else{
                obj = "{'result':'0001','msg':'当前系统繁忙，请重试...'}"; //未获取到令牌，直接返回

                countFail++;
            }

        }catch(Throwable ex){
            ex.printStackTrace();
        }

        System.out.println(flag +"   :    "+ obj + " success:" + countSuccess + " , fail:" + countFail);

        return obj;
    }

}
