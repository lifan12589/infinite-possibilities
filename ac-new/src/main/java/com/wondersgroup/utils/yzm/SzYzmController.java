package com.wondersgroup.utils.yzm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 自用4位数字验证码
 */
@Controller
public class SzYzmController {

	@RequestMapping("/yzmImage.do")
	public void yzmImage(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");

      //在内存中创建一个长80，宽30的图片，默认黑色背景
        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
 
        //获取画笔,颜色为灰色
        Graphics g = image.getGraphics();
        g.setColor(Color.GRAY);
        //填充图片
        g.fillRect(0, 0, width, height);
 
        //产生4个随机验证码，12Ey
        String checkCode = getCheckCode();
        //将验证码放入HttpSession中
        request.getSession().setAttribute("checkCode", checkCode);
 
        //设置画笔颜色为黄色,字体的小大,并向图片上写入验证码
        g.setColor(Color.RED);
        g.setFont(new Font("微软雅黑", Font.BOLD, 24));
        g.drawString(checkCode, 15, 25);
 
        //将内存中的图片输出到浏览器
        //参数一：图片对象
        //参数二：图片的格式，如PNG,JPG,GIF
        //参数三：图片输出到哪里去
        ImageIO.write(image, "PNG", response.getOutputStream());
    }

 
    /**
     * 产生4位随机字符串
     */
    private String getCheckCode() {
        String base = "0123456789";
        int size = base.length();
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 4; i++) {
            //产生0到size-1的随机值
            int index = r.nextInt(size);
            //在base字符串中获取下标为index的字符
            char c = base.charAt(index);
            //将c放入到StringBuffer中去
            sb.append(c);
        }
        return sb.toString();
    }
 
    public static void main(String[] args) {
    	
    	
	}
    
}
