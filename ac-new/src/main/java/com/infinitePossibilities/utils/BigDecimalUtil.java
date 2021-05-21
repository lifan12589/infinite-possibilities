package com.infinitePossibilities.utils;

import java.math.BigDecimal;

public class BigDecimalUtil {

	public static void main(String[] args) {
		
		BigDecimal bignum = new BigDecimal("1");  
		BigDecimal bignum1 = new BigDecimal("5");  
		BigDecimal bignum2 = new BigDecimal("10");  
		BigDecimal bignum3 = null;  
		  
		//加法  
		bignum3 =  bignum1.add(bignum2);       
		System.out.println("和 是：" + bignum3);  
		  
		//减法  
		bignum3 = bignum1.subtract(bignum2);  
		System.out.println("差  是：" + bignum3);  
		  
		//乘法  
		bignum3 = bignum1.multiply(bignum2);  
		System.out.println("积  是：" + bignum3);  
		  
		//除法  
		bignum3 = bignum1.divide(bignum2);  
		System.out.println("商  是：" + bignum3);  
		
		//两数比较
		System.out.println(bignum1.compareTo(bignum2));
		//两数相等不再循环增加
		for(int i =0;i<22;i++){
			if(bignum1.compareTo(bignum2)<0){
				bignum1 = bignum1.add(bignum);
				System.out.println(bignum1);
			}
		}
	
	}	
	
}	
	
	
	
	
	
	
	
	
	
	
