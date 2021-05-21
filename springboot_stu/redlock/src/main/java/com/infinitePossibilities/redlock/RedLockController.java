package com.infinitePossibilities.redlock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/lock")
public class RedLockController {

    @Autowired
    // 红锁
    @Qualifier("RedLockLockService")
    private RedLockService grabService;

    @RequestMapping("/do")
    public String grabMysql(){

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        int port = request.getLocalPort();
        long i = Thread.currentThread().getId();
          String type = grabService.grabOrder(port+"-"+i);
        System.out.println(port+"-"+i+"返回页面："+type);
        return type;
    }
}