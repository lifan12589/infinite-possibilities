//package com.infinitePossibilities.web;
//
//import com.infinitePossibilities.service.MQService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class ProducerController {
//
//    @Autowired
//    MQService mqService;
//
//    @RequestMapping("send")
//    public String send(){
//
//        Object object = mqService.sendMessage();
//
//        return object.toString();
//    }
//
//
//}
