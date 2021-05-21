package com.wondersgroup.utils.requestUtils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Encoder;
import wfc.service.util.StreamHelper;

public class HttpApiClient {
	
	public static String getAccessToken(String client_id, String client_secret) {
		//生产
//		client_id="zwdtuser";
//		client_secret="zwdtuser";
//		String url = "http://172.16.224.21:18018/ac-product-api/oauth2/getToken";
		//测试
//		client_id="apicommon";
//		client_secret="111111b";
		client_id="SHYCSH";
		client_secret="111111b";
		String url = "http://10.81.16.36:9080/ac-product-api/oauth2/getToken";
		//长三角
//		client_id="zwdtuser";
//		client_secret="zwdtuser";
//		String url = "http://ywtb.sh.gov.cn:18018/ac-product-api/oauth2/getToken";
//		String url = "http://10.81.16.161:8888/ac-product-api/oauth2/getToken";
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			httpPost.setEntity(new StringEntity("clientId=" + client_id
					+ "&clientSecret=" + client_secret));
			HttpResponse response = httpClient.execute(httpPost);
			JSONObject jsonObject = JSONObject.parseObject(EntityUtils
					.toString(response.getEntity()));
			return jsonObject.getString("access_token");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
	public static String apiResData(String url, JSONObject jsonObject) {
//		url = "http://172.16.224.21:18018/ac-product-api" + url;   生产
		url = "http://10.81.16.36:9080/ac-product-api" + url;     //测试
//		url = "http://10.81.16.161:8888/ac-product-api" + url;    //长三角
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(180 * 1000)
				.setConnectionRequestTimeout(180 * 1000)
				.setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();
		httpPost.setConfig(requestConfig);
		httpPost.setHeader("Content-Type", "application/json");
		try {
			httpPost.setEntity(new StringEntity(jsonObject.toString(),
					ContentType.create("application/json", "utf-8")));
			HttpResponse response = httpClient.execute(httpPost);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
			return "post failure :caused by-->" + e.getMessage().toString();
		}
	}
	
	
	public static String apiResData2(String url, JSONObject jsonObject) {
//		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(180 * 1000)
				.setConnectionRequestTimeout(180 * 1000)
				.setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();
		httpPost.setConfig(requestConfig);
		httpPost.setHeader("Content-Type", "application/json");
		try {
			httpPost.setEntity(new StringEntity(jsonObject.toString(),
					ContentType.create("application/json", "utf-8")));
			HttpResponse response = httpClient.execute(httpPost);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
			return "post failure :caused by-->" + e.getMessage().toString();
		}
	}
	
	
	
	/**
	 * 上传附件
	 * @param url
	 * @param multipartEntityBuilder
	 * @return
	 * @throws Exception
	 */
	public static String apiResFile(String url, MultipartEntityBuilder multipartEntityBuilder) throws Exception {
//		url = "http://172.16.224.21:18018/ac-product-api" + url;   生产
		url = "http://10.81.16.36:9080/ac-product-api" + url;     //测试
//		url = "http://10.81.16.161:8888/ac-product-api" + url;    //长三角
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		HttpEntity reqEntity = multipartEntityBuilder.build();
		httpPost.setEntity(reqEntity);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();
		InputStream is2 = httpEntity.getContent();
		byte[] bytes = StreamHelper.toByteArray(is2);
		String responseString = new String(bytes, "UTF-8");
		StatusLine statusLine = httpResponse.getStatusLine();
		if (statusLine.getStatusCode() >= 400) {
			return "post failure :caused by-->" + responseString;
		} else {
			return responseString;
		}
	}

	public static JSONObject getData(String url,String data) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			httpPost.setEntity(new StringEntity(data));
			HttpResponse response = httpClient.execute(httpPost);
			JSONObject jsonObject = JSONObject.parseObject(EntityUtils
					.toString(response.getEntity()));
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static String getData2(String url,String data) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			httpPost.setEntity(new StringEntity(data));
			HttpResponse response = httpClient.execute(httpPost);
			String jsonObject = EntityUtils
					.toString(response.getEntity());
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//接收JSONArray
	public static JSONArray getData3(String url,String data) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			httpPost.setEntity(new StringEntity(data));
			HttpResponse response = httpClient.execute(httpPost);
			JSONArray JSONArrays = JSONArray.parseArray(EntityUtils
					.toString(response.getEntity()));
			return JSONArrays;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//教委企业制证
	public static JSONArray getData4(String url,String code,JSONArray array) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		BASE64Encoder encoder = new BASE64Encoder();
		final String text = "bigdatacenter:WkCM987NVab2zhX";
		String replayId = UUID.randomUUID().toString();
		String strEncoder="";
		try {
			//BASE64编码
			strEncoder = encoder.encode(text.getBytes("UTF-8"));
			System.out.println(strEncoder);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader("Authorization", strEncoder);
		httpPost.setHeader("replayId", replayId);
		try {
			httpPost.setEntity(new StringEntity(code));
			httpPost.setEntity(new StringEntity(array.toString(),
					ContentType.create("application/json", "utf-8")));
			HttpResponse response = httpClient.execute(httpPost);
			JSONArray JSONArrays = JSONArray.parseArray(EntityUtils
					.toString(response.getEntity()));
			return JSONArrays;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	


	public static void main(String[] args) {
//		System.out.println(getAccessToken("admin","admin"));
		//System.out.println(UUID.randomUUID().toString());
//		String url = "http://172.18.125.34:9081/shfbybxxcx/ydjy/jygxzycx.action";
//	    String urlS = "http://117.184.226.149:9081/shfbybxxcx/ydjy/jygxzycx.action";
		
//	    String url = "http://ybj.sh.gov.cn/shfbybxxcx/ydjy/ydbaxxcx.action";
//		//String data = "json={sfzh:\"310110195601011674\"}";
//		String sfzh = "372923199102050077";
//		String requestdata = "json={";
//		requestdata += "sfzh:\""+sfzh+"\"";
//		requestdata += "}";
//		
//		System.out.println(getData(url, requestdata));
		
		
		
//		SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date1 = formatter.parse(sjkzxtime);
//		Date date2  = new Date();
//		GregorianCalendar now = new GregorianCalendar();
//		now.setTime(date1);//数据库时间
//		int day1 = now.get(GregorianCalendar.MONTH);
//		now.setTime(date2);//申请当天时间
//		int day2 = now.get(GregorianCalendar.MONTH);
//		System.out.println("===========day-TIME--->"+day2);

//		String nf= "1111";
//		String lhlx= "2222";
//		String lhjs= "3333";
//		String nf2= "1";
//		String lhlx2= "2";
//		String lhjs2= "3";
//		String requestData ="[{\"thyy\":\"444\",\"xm\":\"555\",\"pass_no\":\"666\"}]";
//		  String u = "[{\"success\": [{\"nf\":\""+nf+"\",\"lhlx\":\""+lhlx+"\",\"lhjs\":\""+lhjs+"\"}, {\"nf\":\""+nf2+"\",\"lhlx\":\""+lhlx2+"\",\"lhjs\":\""+lhjs2+"\"}]}, {\"error\": [{\"thyy\": \"111\",\"xm\": \"222\",\"pass_no\": \"333\"}, {\"thyy\": \"444\",\"xm\": \"555\",\"pass_no\": \"666\"}]}]";
//		//String requestData ="{\"data\": [{\"nf\": \""+nf+"\",\"lhlx\": \""+lhlx+"\",\"lhjs\": \""+lhjs+"\"},{\"nf\": \""+nf2+"\",\"lhlx\": \""+lhlx2+"\",\"lhjs\": \""+lhjs2+"\"}]}";
//		String requestData2 ="{[{\"data\":[{\"nf\":\""+nf+"\",\"lhlx\":\""+lhlx+"\",\"lhjs\":\""+lhjs+"\"}]},{\"data2\":[{\"nf\":\""+nf2+"\",\"lhlx\":\""+lhlx2+"\",\"lhjs\":\""+lhjs2+"\"}]}]}";
//			org.json.JSONObject json =new org.json.JSONObject(requestData);
//			System.out.println(json);
//            String  data =  json.getString("data");
//		    System.out.println(data);
//		    JSONArray array = JSONArray.parseArray(data);
//		    System.out.println(array);
//		    JSONObject myjObject =null;
//		    for(int i=0 ; i < array.size();i++)
//		    {
//		     //获取每一个JsonObject对象
//		     myjObject = array.getJSONObject(0);
//             System.out.println(myjObject);
//		 }
		
		    
		  
//		    String url = "http://10.81.16.36:9080/ac-product-api/portal/reciveAttach.do";
//			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder
//			.create();
//			multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//			try {
//			multipartEntityBuilder.setCharset(CharsetUtils.get("UTF-8"));
//			multipartEntityBuilder.addPart(
//			"sendId",
//			new StringBody("123712378172", ContentType.create(
//			ContentType.TEXT_PLAIN.getMimeType(), "UTF-8")));
//			byte[] bytes = null;
//			File file = new File("C:/Users/lifan/Desktop/jj.docx");
//			FileInputStream fis = new FileInputStream(file);
//			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
//			byte[] b = new byte[1000];
//			int n;
//			while ((n = fis.read(b)) != -1) {
//			bos.write(b, 0, n);
//			}
//			fis.close();
//			bos.close();
//			bytes = bos.toByteArray();
//			InputStream is = new ByteArrayInputStream(bytes);
//			multipartEntityBuilder.addBinaryBody("file", is,
//			ContentType.MULTIPART_FORM_DATA, "支付模板.xls");
//			CloseableHttpClient httpClient = HttpClients.createDefault();
//			HttpPost httpPost = new HttpPost(url);
//			org.apache.http.HttpEntity reqEntity = multipartEntityBuilder
//			.build();
//			httpPost.setEntity(reqEntity);
//			httpClient.execute(httpPost);
//			} catch (Exception e) {
//			e.printStackTrace();
//			}   
		   
		
		
	}
	
}
