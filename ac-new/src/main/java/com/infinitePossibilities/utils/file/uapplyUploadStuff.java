package com.infinitePossibilities.utils.file;

import com.alibaba.fastjson.JSONObject;
import com.infinitePossibilities.utils.requestUtils.HttpApiClient;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.util.CharsetUtils;

import java.io.*;

public class uapplyUploadStuff {

    public static void main(String[] args) throws Exception {

        JSONObject json = new JSONObject();
        File file=new File("C:\\Users\\lifan\\Desktop\\jj.jpg");
        if(file.length()==0){
            json.put("400", "根据地址找不到指定的文件。");
        }
        String applyno = "2018060687434026227";
        String fileName =file.getName();
        String token = HttpApiClient.getAccessToken("", "");
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        multipartEntityBuilder.setCharset(CharsetUtils.get("UTF-8"));

        multipartEntityBuilder.addPart("accessToken",
                new StringBody(token, ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), "UTF-8")));
        multipartEntityBuilder.addPart("applyNo",
                new StringBody(applyno, ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));

        multipartEntityBuilder.addPart("stuffId",
                new StringBody("2020-08-19", ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));

        multipartEntityBuilder.addPart("stuffCode",
                new StringBody("01", ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));

        multipartEntityBuilder.addPart("stuffType",
                new StringBody("0", ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));

        multipartEntityBuilder.addPart("stuffName",
                new StringBody("jj.jpg", ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));

        multipartEntityBuilder.addPart("stuffStatus",
                new StringBody("0", ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));

        multipartEntityBuilder.addPart("stuffSource",
                new StringBody("未实现复用", ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));

        byte[] bytes = File2byte(file);
        InputStream is = new ByteArrayInputStream(bytes);
        multipartEntityBuilder.addBinaryBody("file", is, ContentType.MULTIPART_FORM_DATA,
                fileName);
        String reult = HttpApiClient.apiResFile("/uploadStuff", multipartEntityBuilder);
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




}
