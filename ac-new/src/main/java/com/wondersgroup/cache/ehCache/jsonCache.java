package com.wondersgroup.cache.ehCache;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSONArray;
import com.wondersgroup.redis.RedisUtil;

import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

@Controller
public class jsonCache {
	
	private Cache cache;
	private RedisUtil redisUtil;
	
	@Resource
	private CacheManager cacheManager;
	@PostConstruct
	public void init() {
		this.cache = cacheManager.getCache("jsonCache");
	}
	@RequestMapping("/ehCache/jsonCache1.do")
	public  void jsonCache (HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");
		JSONArray jsonArray = new JSONArray();
		String sheng = request.getParameter("sheng");
		if (this.cache.get(sheng) == null|| this.cache.get(sheng).get() == null||redisUtil.get(sheng).equals("null")) {
			String sql ="";
			Object[] objects;
			if(sheng  !=null){
				sql = "select * from DANGAN_SHI_QU where SHENG = ?";
				objects = new Object[] {sheng};
				RecordSet rs = SQL.execute(sql, objects);
				while (rs.next()) {
					com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
					String code = rs.getString("SHI_ID");
					String  name = rs.getString("SHI");
					json.put("code", code);
					json.put("name", name);
					jsonArray.add(json);
				}
				this.cache.put(sheng, jsonArray);
				System.out.println("jsonArray---->"+jsonArray);
				System.out.println("this.cache---->"+this.cache);
				System.out.println((JSONArray) this.cache.get(sheng).get());
				//放在redis一小时
				redisUtil.set(sheng, jsonArray.toString(), Long.parseLong("3600"));
		}
		}else{
			jsonArray = (JSONArray) this.cache.get(sheng).get();
			System.out.println("缓存~~~！");
			String array = redisUtil.get(sheng);
			JSONArray redisArray = JSONArray.parseArray(array);
			System.out.println("redis: --->"+redisArray);
		}
		response.getWriter().print(jsonArray.toString());
}	
	

	
	


}
