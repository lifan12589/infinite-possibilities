package com.online.taxi.order.redLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 */
@RestController
@RequestMapping("/lock")
public class RedLockController {

    @Autowired
    // 红锁
    @Qualifier("grabRedisRedissonRedLockLockService")
    private RedLockService grabService;

    @GetMapping("/do")
    public String grabMysql(){
        long i = Thread.currentThread().getId();
            grabService.grabOrder(i);
        return "";
    }
}