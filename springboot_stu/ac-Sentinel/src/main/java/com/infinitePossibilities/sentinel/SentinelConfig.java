package com.infinitePossibilities.sentinel;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CompositeFilter;
import javax.servlet.Filter;


@Configuration
public class SentinelConfig {

    /**
     * 用于注解限流
     * 将SentinelResourceAspect注册为一个Bean
     *
     */
    @Bean
    public SentinelResourceAspect sentinelResourceAspect(){

        return new SentinelResourceAspect();
    }


    /**
     * 配置filter
     * 与 Sentinel 控制台进行通信,
     * 把所有访问的 Web URL 自动统计为 Sentinel 的资源.
     * @return
     */
    public FilterRegistrationBean sentinelFilterRegistration(){

        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new CompositeFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("sentinelFilter");
        registrationBean.setOrder(1);

        return registrationBean;
    }


}
