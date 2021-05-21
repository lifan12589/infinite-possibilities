//package com.apexedu.message;
package com.wondersgroup.utils.requestUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import wfc.service.config.Config;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * java实现post远程提交文件
 * @author wangxianchao
 *
 */
public class HttpPostUtil {
	private static String LICENSE_URL = Config.get("license_jwUrl")+"/GxZxTransferServlet";
	URL url;  
    HttpURLConnection conn;  
    String boundary = "--------httppost123";  
    Map<String, String> textParams = new HashMap<String, String>();  
    Map<String, File> fileparams = new HashMap<String, File>();  
    DataOutputStream ds;  
    
    
    public HttpPostUtil(String url) throws Exception {  
        this.url = new URL(url);  
    }  
    //重新设置要请求的服务器地址，即上传文件的地址。  
    public void setUrl(String url) throws Exception {  
        this.url = new URL(url);  
    }  
    //增加一个普通字符串数据到form表单数据中  
    public void addTextParameter(String name, String value) {  
        textParams.put(name, value);  
    }  
    //增加一个文件到form表单数据中  
    public void addFileParameter(String name, File value) {  
        fileparams.put(name, value);  
    }  
    // 清空所有已添加的form表单数据  
    public void clearAllParameters() {  
        textParams.clear();  
        fileparams.clear();  
    }  
    // 发送数据到服务器，返回一个字节包含服务器的返回结果的数组  
    public byte[] send() throws Exception {  
        initConnection();  
        try {  
            conn.connect();  
        } catch (SocketTimeoutException e) {  
            // something  
            throw new RuntimeException();  
        }  
        ds = new DataOutputStream(conn.getOutputStream());  
        writeFileParams();  
        writeStringParams();  
        paramsEnd();  
        InputStream in = conn.getInputStream();  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        int b;  
        while ((b = in.read()) != -1) {  
            out.write(b);  
        }  
        conn.disconnect();  
        return out.toByteArray();  
    }  
    //文件上传的connection的一些必须设置  
    private void initConnection() throws Exception {  
        conn = (HttpURLConnection) this.url.openConnection();  
        conn.setDoOutput(true);  
        conn.setUseCaches(false);  
        conn.setConnectTimeout(10000); //连接超时为10秒  
        conn.setRequestMethod("POST");  
        conn.setRequestProperty("Content-Type",  
                "multipart/form-data; charset=utf-8; boundary=" + boundary);  
    }  
    //普通字符串数据  
    private void writeStringParams() throws Exception {  
        Set<String> keySet = textParams.keySet();  
        for (Iterator<String> it = keySet.iterator(); it.hasNext();) {  
            String name = it.next();  
            String value = textParams.get(name);  
            ds.writeBytes("--" + boundary + "\r\n");  
            ds.writeBytes("Content-Disposition: form-data; name=\"" + name  
                    + "\"\r\n");  
            ds.writeBytes("\r\n");  
            ds.writeBytes(encode(value) + "\r\n");  
        }  
    }  
    //文件数据  
    private void writeFileParams() throws Exception {  
        Set<String> keySet = fileparams.keySet();  
        for (Iterator<String> it = keySet.iterator(); it.hasNext();) {  
            String name = it.next();  
            File value = fileparams.get(name);  
            ds.writeBytes("--" + boundary + "\r\n");  
            ds.writeBytes("Content-Disposition: form-data; name=\"" + name  
                    + "\"; filename=\"" + encode(value.getName()) + "\"\r\n");  
            ds.writeBytes("Content-Type: " + getContentType(value) + "\r\n");  
            ds.writeBytes("\r\n");  
            ds.write(getBytes(value));  
            ds.writeBytes("\r\n");  
        }  
    }  
    //获取文件的上传类型，图片格式为image/png,image/jpg等。非图片为application/octet-stream  
    private String getContentType(File f) throws Exception {  
          
//      return "application/octet-stream";  // 此行不再细分是否为图片，全部作为application/octet-stream 类型  
        ImageInputStream imagein = ImageIO.createImageInputStream(f);  
        if (imagein == null) {  
            return "application/octet-stream";  
        }  
        Iterator<ImageReader> it = ImageIO.getImageReaders(imagein);  
        if (!it.hasNext()) {  
            imagein.close();  
            return "application/octet-stream";  
        }  
        imagein.close();  
        return "image/" + it.next().getFormatName().toLowerCase();//将FormatName返回的值转换成小写，默认为大写  
  
    }  
    //把文件转换成字节数组  
    private byte[] getBytes(File f) throws Exception {  
        FileInputStream in = new FileInputStream(f);  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        byte[] b = new byte[1024];  
        int n;  
        while ((n = in.read(b)) != -1) {  
            out.write(b, 0, n);  
        }  
        in.close();  
        return out.toByteArray();  
    }  
    //添加结尾数据  
    private void paramsEnd() throws Exception {  
        ds.writeBytes("--" + boundary + "--" + "\r\n");  
        ds.writeBytes("\r\n");  
    }  
    // 对包含中文的字符串进行转码，此为UTF-8。服务器那边要进行一次解码  
    private String encode(String value) throws Exception{  
        return URLEncoder.encode(value, "UTF-8");  
    }  
    
    public static String hqPfhxx(String zzjgdm,String data,String ywlx) { 
    	  String url = "http://www3.firstjob.com.cn/shangahaiWsqy/GxZxTransferServlet";
    	 
    	  String str="false";
    	  try{
    	   HttpPostUtil u;
    	   u = new HttpPostUtil(url);
    	   u.addTextParameter("ywlx",ywlx);
    	   //System.out.println("--------------------------------------->>>证件号："+ywlx);
    	   u.addTextParameter("zzjgdm",zzjgdm);
    	   //System.out.println("--------------------------------------->>>type："+zzjgdm);
    	   u.addTextParameter("data",data);
    	   //System.out.println("--------------------------------------->>>data："+data);
    	   byte[] b = u.send();
    	      str = new String(b,"utf-8");
    	  }catch(Exception e){
    	   //System.out.print("+++++++++++++++++++>>>>>>>>>>>>>>>>"+e.getMessage());
    	   throw new RuntimeException("失败");
    	  }
    	  
    	  return str;
    	 }
    	    public static void main(String[] args) throws Exception {  
    	     
//    	     String data ="{\"data\": [{\"nf\": \"2018\",\"lhlx\": \"1\",\"lhjs\": \"属地派出所\",\"lhdz\": \"实际居住地社区公共户\",\"pass_no\": \"1819120\",\"grsjhm\": \"13098776621\",\"bysjstr\": \"201801\",\"xwsjstr\": \"201802\",\"pf\": \"www.baidu.com\",\"bdz\": \"www.baidu.com\",\"xwz\": \"www.baidu.com\",\"byz\": \"https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E6%AF%95%E4%B8%9A%E8%AF%81%E4%B9%A6%E7%BC%96%E5%8F%B7%E6%9F%A5%E8%AF%A2\"}]}";
//    	     
//    	     System.out.println("接口返回数据---->"+hqPfhxx("682219885", data,"zmxbl" ));
//    	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    	     String time = sdf.format(Calendar.getInstance().getTime());
// 			System.out.println(time);
 			
 			
 			String nf= "1111";
 			String lhlx= "2222";
 			String lhjs= "3333";
 			String nf2= "1";
 			String lhlx2= "2";
 			String lhjs2= "3";
 		    String u = "[{\"success\": [{\"nf\":\""+nf+"\",\"lhlx\":\""+lhlx+"\",\"lhjs\":\""+lhjs+"\"}, {\"nf\":\""+nf2+"\",\"lhlx\":\""+lhlx2+"\",\"lhjs\":\""+lhjs2+"\"}]}, {\"error\": [{\"thyy\": \"111\",\"xm\": \"222\",\"pass_no\": \"333\"}, {\"thyy\": \"444\",\"xm\": \"555\",\"pass_no\": \"666\"}]}]";
// 		   JSONArray array = JSONArray.parseArray(u);
				  JSONArray arr = JSONArray.parseArray(u);
				  JSONObject myjObject =null;
				    for(int i=0 ; i < arr.size();i++){
				     //获取每一个JsonObject对象
				     myjObject = arr.getJSONObject(0);
				 }
				    //获取想要的数组
				  JSONArray a = myjObject.getJSONArray("success");
				  myjObject = a.getJSONObject(0);
				  //可以获取数组里 不符合的原因
				  nf = myjObject.getString("nf");
				  System.out.println(nf);
 			
    	 }
    	    
    	
    		
    	    
    	    
    	    
    
}
