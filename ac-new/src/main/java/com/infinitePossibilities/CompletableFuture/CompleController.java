package com.infinitePossibilities.CompletableFuture;

import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CompleController {

	/**
	 * 批量处理接口
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/comple.do")
	public void  index(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/piain;charset=UTF-8");
		String serialNo = UUID.randomUUID().toString();
		String st_fj_id = "keys";
        Map<String ,Object> map = CompletableFutureService.query(st_fj_id,serialNo);
        String fhserialNo = map.get("serialNo").toString();
        if(!serialNo.equals(fhserialNo)){
        	System.out.println("是否一致---->: "+serialNo+"---"+fhserialNo);
        }
//        System.out.println(serialNo+"----"+fhserialNo);
		response.getWriter().print(serialNo+"----"+fhserialNo);
		
	}

}
