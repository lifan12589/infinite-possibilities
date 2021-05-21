package com.wondersgroup.aopCommit;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommitController {

    @RequestMapping("/Commit")
    @CommitAop
    public String Commit() {
        System.out.println("进入方法,执行业务！");
        return "返回结果！";
    }
}
