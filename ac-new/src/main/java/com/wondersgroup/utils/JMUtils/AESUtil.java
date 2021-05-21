package com.wondersgroup.utils.JMUtils;
import com.alibaba.fastjson.JSONObject;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * AES 加密方法，是对称的密码算法(加密与解密的密钥一致)，这里使用最大的 256 位的密钥
 */
public class AESUtil {

    private static final BASE64Encoder BASE64_ENCODER = new BASE64Encoder();

    private static final BASE64Decoder BASE64_DECODER = new BASE64Decoder();

    /**
     * 获得一个 密钥长度为 256 位的 AES 密钥，
     * @return 返回经 BASE64 处理之后的密钥字符串
     */
    public static String getStrKeyAES() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes("utf-8"));
        // 这里可以是 128、192、256、越大越安全
        keyGen.init(256, secureRandom);
        SecretKey secretKey = keyGen.generateKey();
        return BASE64_ENCODER.encode(secretKey.getEncoded());
    }
 
    /**
     *  将使用 Base64 加密后的字符串类型的 secretKey 转为 SecretKey
     * @param strKey
     * @return SecretKey
     */
    public static SecretKey strKey2SecretKey(String strKey){
        byte[] bytes;
        SecretKeySpec secretKey = null;
		try {
			bytes = BASE64_DECODER.decodeBuffer(strKey);
			secretKey = new SecretKeySpec(bytes, "AES");
		} catch (IOException e) {
			e.printStackTrace();
		}
        return secretKey;
    }
 
    /**
     * 加密
     * @param content 待加密内容
     * @param secretKey 加密使用的 AES 密钥
     * @return 加密后的密文 byte[]
     */
    public static byte[] encryptAES(byte[] content, SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(content);
    }
 
    /**
     * 解密
     * @param content 待解密内容
     * @param secretKey 解密使用的 AES 密钥
     * @return 解密后的明文 byte[]
     */
    public static byte[] decryptAES(byte[] content, SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(content);
    }

    /**
     * 字节数组转Base64编码
     * @param bytes
     * @return
     */
    public static String byte2Base64(byte[] bytes){
        return BASE64_ENCODER.encode(bytes);
    }

    /**
     * Base64编码转字节数组
     * @param base64Key
     * @return
     */
    public static byte[] base642Byte(String base64Key) {
        try {
			return BASE64_DECODER.decodeBuffer(base64Key);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
    }

    public static void main(String[] args) {
        try {
            String strKeyAES = "6rQpl2r3t9BDlu+wawhqeAlHnSsVU3Vmis9n4mLXthA=";
            System.out.println("密钥：" + strKeyAES);
            // 将使用 Base64 加密后的字符串类型的 secretKey 转为 SecretKey
            SecretKey secretKey = strKey2SecretKey(strKeyAES);

            // 要加密的内容
            // ======================加密开始===================================
            JSONObject jsonObject = new JSONObject(true);
            jsonObject.put("itemCode", "test123");
            jsonObject.put("itemName", "某办件");
            jsonObject.put("deptName", "某部门");
            jsonObject.put("userId", "123456789");
            jsonObject.put("userType", 1);
            jsonObject.put("projectTarget", "张三");
            jsonObject.put("licenseNo", "310212190011111234");
            jsonObject.put("mobile", "18888888888");
            String content = jsonObject.toJSONString();
            System.out.println("加密的内容：" + content);
            byte[] publicEncrypt = AESUtil.encryptAES(content.getBytes("utf-8"), secretKey);
            // 使用 base64 转字符串
            String publicEncryptStr = AESUtil.byte2Base64(publicEncrypt);
            System.out.println("AES加密后：" + publicEncryptStr);
            // ======================加密结束===================================

            // ======================解密开始===================================
//            publicEncryptStr = "0QOEpDXGO4lM+Ft3RWAUJP+KMjmXcI245cyr5mFOINtttwYLgPQX1dDZiYpPBaBD45WNxxu8dXBKv09HZsl6jYbItJg/2/MNgEGYnO+a6qM6zab4io79NyLmaOH+X1pNjyuFd0ohqF42302R0tZq0LA74+Zb3oXDCbprx2ZzreI4XA4NGHzqka1xcsyE2c5aWS3MgXp5385/nG7rcpEZymjrogiBxtqDH4PEitrg8MYxXdnaQdHxPR2RGur30yTY/pfkrSVPwmKB4NDKiUgvcmT8Oprzdi1+YknoZjMc5Jr9cSBQKuhu+xO6qNQZMYVx";
            byte[] publicEncryptByte = AESUtil.decryptAES(AESUtil.base642Byte(publicEncryptStr), secretKey);
            String decodeContent = new String(publicEncryptByte);
            System.out.println("AES解密：" + decodeContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}