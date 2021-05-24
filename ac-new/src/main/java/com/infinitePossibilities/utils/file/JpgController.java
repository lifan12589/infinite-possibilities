package com.infinitePossibilities.utils.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JpgController {

	@ResponseBody
	@RequestMapping("/showJpg.do")
	public void showJpg(HttpServletRequest request,HttpServletResponse response) throws IOException {
	
	    String filePath = "C:\\Users\\lifan\\Desktop\\jj.jpg";
	    File f = new File(filePath);
	    if (!f.exists()) {
	        response.sendError(404, "File not found!");
	        return;
	    }
	    BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
	    byte[] bs = new byte[1024];
	    int len = 0;
	    response.reset(); // 非常重要
	    if (true) { // 在线打开方式
	        URL u = new URL("file:///" + filePath);
	        String contentType = u.openConnection().getContentType();
	        response.setContentType(contentType);
	        response.setHeader("Content-Disposition", "inline;filename="
	                + "jj.jpg");
	        // 文件名应该编码成utf-8
	    } else {// 纯下载方式
	        response.setContentType("application/x-msdownload");
	        response.setHeader("Content-Disposition", "attachment;filename="
	                + "jj.jpg");
	    }
	    OutputStream out = response.getOutputStream();
	    while ((len = br.read(bs)) > 0) {
	        out.write(bs, 0, len);
	    }
	    out.flush();
	    out.close();
	    br.close();

  }
	
	
}