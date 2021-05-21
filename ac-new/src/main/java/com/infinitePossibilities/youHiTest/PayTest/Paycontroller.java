package com.infinitePossibilities.youHiTest.PayTest;


import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.tool.helper.LogHelper;

@Controller
public class Paycontroller {

	@RequestMapping("/youHui.do")
	public void  calcAmount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/piain;charset=UTF-8");
		String channelIds = request.getParameter("channelIds");
		String goodsIds= request.getParameter("goodsIds");
		String zhekouIds= request.getParameter("zhekouId");
		
		Integer channelId = Integer.parseInt(channelIds);
		Integer goodsId = Integer.parseInt(goodsIds);
		LogHelper.info("参数获取完毕！");
		Context context = new Context();
		BigDecimal bigDecimal = context.calRecharge(channelId, goodsId,zhekouIds);
		//返回结果保留两位小数
		response.getWriter().print(bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).toString());

	}



	
}
