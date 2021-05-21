package com.infinitePossibilities.utils.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;

public class Main {

	public static void main(String[] args) throws Exception {
		
		JSONObject json = new JSONObject();
		 File file=new File("C:\\Users\\lifan\\Desktop\\"); 
		 if(file.length()==0){
				json.put("400", "根据地址找不到指定的文件。");
			}
		 String applyno = "002000012205457";
		 String fileName =file.getName();
					String token = "6c445d95618c95375b88c40ccab4cc4e";
					MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
					multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
					multipartEntityBuilder.setCharset(CharsetUtils.get("UTF-8"));
					multipartEntityBuilder.addPart("accessToken",
							new StringBody(token, ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), "UTF-8")));
					multipartEntityBuilder.addPart("applyNo",
							new StringBody(applyno, ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));
					multipartEntityBuilder.addPart("documentType",
							new StringBody("1", ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));
					byte[] bytes = File2byte(file);
						InputStream is = new ByteArrayInputStream(bytes);
						multipartEntityBuilder.addBinaryBody("file", is, ContentType.MULTIPART_FORM_DATA,
								fileName);
						String reult = apiResFile("/uapply/uploadDocument", multipartEntityBuilder);
						JSONObject reultobj = JSONObject.parseObject(reult);
						System.out.println(reultobj);
	}
	

	/**
     * 将文件转换成byte数组
     * @param tradeFile
     * @return
     */
    public static byte[] File2byte(File tradeFile){
        byte[] buffer = null;
        try
        {
            FileInputStream fis = new FileInputStream(tradeFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[10240];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return buffer;
    }
    
    /**
	 * 上传附件
	 * @param url
	 * @param multipartEntityBuilder
	 * @return
	 * @throws Exception
	 */
	public static String apiResFile(String url, MultipartEntityBuilder multipartEntityBuilder) throws Exception {
		url = "http://" + url;     //测试
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		HttpEntity reqEntity = multipartEntityBuilder.build();
		httpPost.setEntity(reqEntity);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();
		InputStream is2 = httpEntity.getContent();
		byte[] bytes = toByteArray(is2);
		String responseString = new String(bytes, "UTF-8");
		StatusLine statusLine = httpResponse.getStatusLine();
		if (statusLine.getStatusCode() >= 400) {
			return "post failure :caused by-->" + responseString;
		} else {
			return responseString;
		}
	}

    public static byte[] toByteArray(InputStream is) {
        if (is == null) {
            return null;
        } else {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];

            try {
                int bytesRead;
                while((bytesRead = is.read(buffer, 0, 1024)) != -1) {
                	baos.write(buffer, 0, bytesRead);
                }
            } catch (IOException var5) {
            }
            return baos.toByteArray();
        }
    }

	
}
