//package com.wondersgroup.utils.threads.thresdPool_MQ_Impl;
//
//import com.alibaba.fastjson.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.Timestamp;
//import java.util.Date;
//
//@Controller
//public class CallableController {
//
//	@Autowired
//	private CallableService callableService;
//
//	@Autowired
//	private ListenableFutureService listenableFutureService;
//	/**
//	 * 接收params参数
//	 * @param request
//	 * @param response
//	 * @throws IOException
//	 */
//	@RequestMapping("/callable/requestData.do")
//	public void requestData(HttpServletRequest request,HttpServletResponse response) throws IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/plain; charset=UTF-8");
//
////		stFjId = UUID.randomUUID().toString();
//		String stApplyId =listenableFutureService.getData();
//		String stFjId = stApplyId;
//		String url = request.getParameter("url");
////			String stApplyId = "CSJ000000000000001";
//			String fjName = request.getParameter("fjName");
//
//			Timestamp  saveTime=new Timestamp(new Date().getTime());
//			JSONObject json = new  JSONObject();
//			json.put("stFjId", stFjId);
//			json.put("url", url);
//			json.put("stApplyId", stApplyId);
//			json.put("fjName", fjName);
//			json.put("time", saveTime);
////		System.out.println("requestData--->"+json);
////		String result = callableService.CallableToFuture(json);
//		String result = listenableFutureService.ListenableFutureService(json);
//
//
//		System.out.println("stFjId  :"+stFjId + "  /  result :"+result);
//		response.getWriter().print(stFjId.equals(result));
//
//	}
//
//
//}
