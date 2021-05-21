package com.wondersgroup.zhongTai;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;

public class Main {

    //加密
    public static String Encrypt(String str) {
        try {
            String key = "4e2df0b2a02140bc9d5c17241b038a57"; //密钥
            byte[] kb = key.getBytes("utf-8");
            SecretKeySpec sks = new SecretKeySpec(kb, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//算法/模式/补码方式
            cipher.init(Cipher.ENCRYPT_MODE, sks);
            byte[] eb = cipher.doFinal(str.getBytes("utf-8"));
            return new Base64().encodeToString(eb);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception {

        String url = "https://ywopen.sh.gov.cn/clientgateway/";

        //开发商应用app_ID
        String appId = "09973a75-2b06-4724-a5e5-077aa6008d79";

        //申请的服务商接口id（APIID）
        String apiName = "095e151a-75ca-4d80-a570-ae9cd4034eb3";
        //AES加密
        String content = appId + apiName + System.currentTimeMillis() / 1000;
        String enCon = Encrypt(content);
        System.out.println("加密:" + enCon);
        //调用
        Map<String, Object> params = new HashMap<>();

        Map<String, Object> headers = new HashMap<>();
        headers.put("appid", appId);
        headers.put("apiname", apiName);
        headers.put("signature", enCon);


    }
}