package com.infinitePossibilities.cache.googleCache;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CacheController {

    @RequestMapping("/setCache")
    public void setCache(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        LocalCache2.setKey(id,name);
        response.getWriter().print(id+"----"+name);
    }

    @RequestMapping("/getCache")
    public void getCache(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        Object result = LocalCache2.getKey(id);
        response.getWriter().print(result.toString());
    }


}
