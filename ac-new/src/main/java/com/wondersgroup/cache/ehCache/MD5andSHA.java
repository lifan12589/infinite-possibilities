package com.wondersgroup.cache.ehCache;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5andSHA {

	public static String shaEncode(String inStr) throws Exception {
		MessageDigest sha = null;
		try {
			sha = MessageDigest.getInstance("SHA");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}

		byte[] byteArray = inStr.getBytes("UTF-8");
		byte[] md5Bytes = sha.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
	
	 public static String shaEncode1(String source) {
		    StringBuilder sb = new StringBuilder();
		    MessageDigest md5;
		    try {
		      md5 = MessageDigest.getInstance("MD5");
		      md5.update(source.getBytes());
		      for (byte b : md5.digest()) {
		        sb.append(String.format("%02X", b)); // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
		      }
		      return sb.toString().toLowerCase();//返回小写字符
		    } catch (NoSuchAlgorithmException e) {
		      e.printStackTrace();
		    }
		    return null;
		  }

	 public static String encode(String str) {
	        MessageDigest digest;
	        try {
	            digest = MessageDigest.getInstance("MD5");
	            try {
					digest.update(str.getBytes("utf-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            return new BigInteger(1, digest.digest()).toString(16).toLowerCase();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	 
	 
	 
	 
//	
//	public static void main(String[] args) throws Exception {
//		
//		System.out.println(shaEncode("统一受理"));
//		System.out.println(shaEncode1("统一受理"));
//		System.out.println(encode("统一受理"));
//		
//	}
	
	
	
	
	
	
}
