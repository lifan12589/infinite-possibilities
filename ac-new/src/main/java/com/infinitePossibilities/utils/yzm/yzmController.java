package com.infinitePossibilities.utils.yzm;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class yzmController {

	@RequestMapping("getVerifiCode")
    @ResponseBody
    public void getVerifiCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*
             1.生成验证码
             2.把验证码上的文本存在session中
             3.把验证码图片发送给客户端
             */
		yzmBean yzm = new yzmBean();     //用我们的验证码类，生成验证码类对象
        BufferedImage image = yzm.getImage();  //获取验证码
        request.getSession().setAttribute("text", yzm.getText()); //将验证码的文本存在session中
        yzm.output(image, response.getOutputStream());//将验证码图片响应给客户端
    }
	
	@RequestMapping("Login_authentication")
    @ResponseBody
    public String Login_authentication(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        String session_yzm=(String) request.getSession().getAttribute("text");    //从session中获取真正的验证码
        return session_yzm;
    }
	
	@RequestMapping("yzm")
	@ResponseBody
	public String yzm(HttpServletRequest request,HttpServletResponse response){
		yzmBean yzm = new yzmBean();
		 BufferedImage image = yzm.getImage();  //获取验证码
		return yzm.getText();
	}
	
}
