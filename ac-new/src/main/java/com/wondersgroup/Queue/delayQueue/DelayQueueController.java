package com.wondersgroup.Queue.delayQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 业务场景：
 * 查看数据库办件的推送情况
 * 推送限时为60秒
 * 如果60秒内被推送出去，数据库状态为1
 * 没被推送过为0
 * 超过60秒失效为-1
 * （推送过的不考虑，只实现未推送的，和超时的
 * 以及服务器重启后从新加入队列的处理)
 */
@Controller
public class DelayQueueController {

    private static final String SUCCESS = "seccess";
    private static final String FAILUER = "failure";

    @Autowired
    private DelayQueueSave pDelayQueueSave;

    @RequestMapping("/index")
    public String userReg(){
        return "";
    }

    /**
     * http://localhost:8080/submit?Number=5
     * 接收5个请求，数据库生成5条办件
     * @param Number
     * @return
     */
    @RequestMapping("/submit")
    @ResponseBody
    public String saveUser(@RequestParam("Number")int Number){
        pDelayQueueSave.insert(Number);
        return SUCCESS;
    }

}
