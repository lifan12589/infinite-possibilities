/**
 * @author lifan
 *
 */
package com.wondersgroup.utils.Listener;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestPersonBindingServlet extends HttpServlet{
	  
	@RequestMapping("/inSession.do")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //将person对象绑定到session中
        Person p = new Person("100","jack");
        session.setAttribute("person",p);
        System.out.println(session);
        
        // 获取session中所有的键值
        Enumeration<?> enumeration = session.getAttributeNames();
        // 遍历enumeration
        while (enumeration.hasMoreElements()) {
        	// 获取session键值
        	String name = enumeration.nextElement().toString();
        	// 根据键值取session中的值
        	Object value = session.getAttribute(name);
        	// 打印结果
        	System.out.println("this is :" + session.toString() + "--> ");
        	System.out.println(name + " : " + value);
        }
        
        //将person对象从session中解绑
        session.removeAttribute("person");
        System.out.println(session);
    }

}