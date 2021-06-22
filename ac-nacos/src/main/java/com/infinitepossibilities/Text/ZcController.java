package com.infinitepossibilities.Text;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ZcController {

    @NacosInjected
    private NamingService namingService;

    //   http://localhost:8080/getData?serviceName=ac-nacos-demo
    @ResponseBody
    @RequestMapping("/getData")
    public List<Instance> getInstance(@RequestParam String serviceName) throws NacosException {

        return namingService.getAllInstances(serviceName);

    }







}
