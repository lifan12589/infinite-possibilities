package com.wondersgroup.web;

import com.wondersgroup.anno.RateLimitAnno;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
public class LimiterController {

    @RateLimitAnno
    @RequestMapping("/limiter")
    public String limiter(HttpServletRequest request){
        StringBuilder sb = new StringBuilder("{");
        //调用业务层，操作
        sb.append("'result':'0000','msg':'成功'");
        return  sb.append("}").toString();
    }

}
