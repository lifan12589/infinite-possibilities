package com.infinitePossibilities.utils.threads.threadPoolInsertData;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSONObject;

@Controller
public class threadController {

	@Autowired
	private threadService threadService;
	/**
	 * 接收params参数
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/thread/requestData.do") 
	public void  requestData(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");
		String stFjId = UUID.randomUUID().toString();
		String url = request.getParameter("url");
		String stApplyId = "CSJ000000000000001";
		String fjName = request.getParameter("fjName"); 
//		Date date = new Date();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String time = format.format(date).toString();
		Timestamp  saveTime=new Timestamp(new Date().getTime());
		JSONObject json = new  JSONObject();
		json.put("stFjId", stFjId);
		json.put("url", url);
		json.put("stApplyId", stApplyId);
		json.put("fjName", fjName);
		json.put("time", saveTime);
		System.out.println("requestData--->"+json);
		threadService.insertData(json);
		
	}
	
	/**
	 * 接收json参数
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/thread/requestData1.do")
	public void  requestData1(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String data = threadService.getRequestPostStr(request);
		JSONObject json = JSONObject.parseObject(data);
		String stFjId = UUID.randomUUID().toString();
		String stApplyId = "CSJ000000000000001";
//		String url = json.getString("url");
//		String fjName =  json.getString("fjName");
		Timestamp  saveTime=new Timestamp(new Date().getTime());
		json.put("stFjId", stFjId);
		json.put("stApplyId", stApplyId);
		json.put("time", saveTime);
		System.out.println("requestData--->"+json);
		threadService.insertData(json);
	}
	
}
