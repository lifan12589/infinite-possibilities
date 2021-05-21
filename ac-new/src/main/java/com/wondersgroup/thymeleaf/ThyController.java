package com.wondersgroup.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ThyController {


    @RequestMapping("index.do")
    public ModelAndView index (HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");
        ModelAndView mv = new ModelAndView();
//        ModelAndView mv = new ModelAndView("onlyChild");
        mv.addObject("name","测试");
        mv.addObject("type","身份证");
        mv.addObject("typeNm","37292319910000001");
        mv.addObject("phone","150000006666");
        mv.addObject("address","上海");
        mv.setViewName("onlyChild");
        System.out.println("进了Controller ...");
        return mv;
    }

    @RequestMapping("text.do")
    public String test(Map<String,String> map){
//        Map<String,String> map = new HashMap<>();
        String str="111111111111";
        map.put("str",str);
        return "index";
    }




}
