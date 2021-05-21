    package com.infinitePossibilities.controller;

    import io.swagger.annotations.Api;
    import io.swagger.annotations.ApiOperation;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;

    /**
     * 首页控制器
     * @author 154594742@qq.com
     * @date: 2021/2/22 10:02:00
     */
    @Api(tags = "首页控制器")
    @RestController
    public class IndexController {

        @ApiOperation("首页html")
        @GetMapping("/")
        public String index(){
            return "hello index";
        }
    }
