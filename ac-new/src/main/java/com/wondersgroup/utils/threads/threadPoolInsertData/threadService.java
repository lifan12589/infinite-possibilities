package com.wondersgroup.utils.threads.threadPoolInsertData;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;

@Service
public class threadService {
	
	private ExecutorService executorService = Executors.newFixedThreadPool(20);
	
	public void  insertData(JSONObject json) {
		System.out.println("insertData--->"+json);
		executorService.execute(new threadUtil(json) );
	}
	
	
	//接收json参数
		 public static String getRequestPostStr(HttpServletRequest request)
		            throws IOException {
			 int contentLength = request.getContentLength();
		        if(contentLength<0){
		            return null;
		        }
		        byte buffer[] = new byte[contentLength];
		        for (int i = 0; i < contentLength;) {
		            int readlen = request.getInputStream().read(buffer, i,
		                    contentLength - i);
		            if (readlen == -1) {
		                break;
		            }
		            i += readlen;
		        }
		        String charEncoding = request.getCharacterEncoding();
		        System.out.println(charEncoding);
		        if (charEncoding == null) {
		            charEncoding = "UTF-8";
		        }
		        return new String(buffer, charEncoding);
		    }
}
