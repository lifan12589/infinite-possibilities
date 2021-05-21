package com.wondersgroup.utils.requestUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.utils.StreamHelperUtils;

/**
* @author 
* HttpClient工具类
*/
public class HttpClientUtil {
	

	public static JSONArray getData(String url, JSONObject jsonObject) {
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
					"UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			JSONArray JSONArrays = JSONArray.parseArray(EntityUtils
					.toString(response.getEntity(),"UTF-8"));
			return JSONArrays;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	/**
	 * get请求
	 * @return
	 */
	public static String doGet(String url) {
       try {
    	   HttpClient client = HttpClientBuilder.create().build(); 
           //发送get请求
           HttpGet request = new HttpGet(url);
           HttpResponse response = client.execute(request);

           /**请求发送成功，并得到响应**/
           if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
               /**读取服务器返回过来的json字符串数据**/
               String strResult = EntityUtils.toString(response.getEntity());
               
               return strResult;
           }
       } 
       catch (IOException e) {
       	e.printStackTrace();
       }
       
       return null;
	}
	
	/**
	 * post请求(用于key-value格式的参数)
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url, Map params){
		System.out.println("-------------"+params.toString());
		
		BufferedReader in = null;  
       try {  
           // 定义HttpClient  
    	   HttpClient client = HttpClientBuilder.create().build(); 
           // 实例化HTTP方法  
           HttpPost request = new HttpPost();  
           request.setURI(new URI(url));
           
           //设置参数
           //List<NameValuePair> nvps = new ArrayList<NameValuePair>(); 
           List<NameValuePair> nvps = new ArrayList<NameValuePair>();
           for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
   			String name = (String) iter.next();
   			String value = String.valueOf(params.get(name));
   			nvps.add(new BasicNameValuePair(name, value));
   			
   			System.out.println("doPost方法返回的参数："+name +"-"+value);
   		}
           request.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));
           
           HttpResponse response = client.execute(request);  
           int code = response.getStatusLine().getStatusCode();
           if(code == 200){	//请求成功
           	in = new BufferedReader(new InputStreamReader(response.getEntity()  
                       .getContent(),"utf-8"));
               StringBuffer sb = new StringBuffer("");  
               String line = "";  
               String NL = System.getProperty("line.separator");  
               while ((line = in.readLine()) != null) {  
                   sb.append(line + NL);  
               }
               
               in.close();  
               
               return sb.toString();
           }
           else{	//
           	System.out.println("状态码：" + code);
           	return null;
           }
       }
       catch(Exception e){
       	e.printStackTrace();
       	
       	return null;
       }
	}
	
	/**
	 * post请求（用于请求json格式的参数）
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url, String params) throws Exception {
		System.out.println("================="+params);
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);// 创建httpPost   
		httpPost.setHeader("Accept", "application/json"); 
		httpPost.setHeader("Content-Type", "application/json");
		String charSet = "UTF-8";
		StringEntity entity = new StringEntity(params, charSet);
		httpPost.setEntity(entity);        
		CloseableHttpResponse httpResponse = null;
       
       try {
       	
       	httpResponse = httpclient.execute(httpPost);
          // StatusLine status = httpResponse.getStatusLine().getStatusCode();
           int state = httpResponse.getStatusLine().getStatusCode();
           if (state == HttpStatus.SC_OK) {
           	HttpEntity responseEntity = httpResponse.getEntity();
           	String jsonString = EntityUtils.toString(responseEntity);
           	return jsonString;
           }
           else{
				 System.err.println("请求返回:"+state+"("+url+")");
			}
       	/*if (200 == httpResponse.getStatusLine().getStatusCode()) {
				HttpEntity httpEntity = httpResponse.getEntity();
				InputStream is = httpEntity.getContent();
				byte[] bytes = StreamHelper.toByteArray(is);
				File localFile = new File("C:\\Users\\99462\\Desktop\\640.jpeg");
				  BufferedOutputStream bos = null;
				  FileOutputStream fos = null;
				  if (!localFile.exists())
			      {
			         localFile.createNewFile();
			      }
				  
		          fos = new FileOutputStream(localFile);
		          bos = new BufferedOutputStream(fos);
		          bos.write(bytes);
		          
		          bos.close();
		          fos.close();
			}*/
       }
       finally {
           if (httpResponse != null) {
               try {
               	httpResponse.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
       }
       return null;
	}
	
	
	public static String doPost(String url, MultipartEntityBuilder multipartEntityBuilder, byte[] bytes) throws Exception {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpResponse httpResponse = null;
		multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		try {
			multipartEntityBuilder.setCharset(CharsetUtils.get("UTF-8"));
			HttpPost httpPost = new HttpPost(url);
			HttpEntity reqEntity = multipartEntityBuilder
					.build();
			httpPost.setEntity(reqEntity);
			httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			InputStream is2 = httpEntity.getContent();
			bytes = StreamHelperUtils.toByteArray(is2);
			String responseString = new String(bytes, "UTF-8");
			int state = httpResponse.getStatusLine().getStatusCode();
			if (state >= 400) {
				return "post failure :caused by-->" + responseString;
			} else {
				return responseString;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}/*finally {
	           if (httpResponse != null) {
	               try {
	               	((BufferedReader) httpResponse).close();
	               } catch (IOException e) {
	                   e.printStackTrace();
	               }
	           }
	           try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	       }*/
	       return null;
		
	}
	public static void main(String[] args) {
		StreamHelperUtils s =new StreamHelperUtils();
	}
}
