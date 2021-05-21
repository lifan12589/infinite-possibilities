package com.wondersgroup.utils;


public class Main implements Runnable {

	  int leftLimit = 2;
      int rightLimit = 11;
	@Override
	public void run() {
//		 int generatedInteger = leftLimit + (int) (Math.random() * rightLimit);
		 int generatedInteger = leftLimit + (int) (Math.random() * (rightLimit - leftLimit)) ;
         System.out.println(generatedInteger);		
	}

	public static void main(String[] args) {
	      
//		Main m = new Main();
//	       for (int i = 1; i < 10; i++) {
//	    	   Thread t = new Thread(m);
//	    	   t.start();
//	       }
	       
//	     
//	       JSONObject jsonOne = new JSONObject();  
//	        JSONObject jsonTwo = new JSONObject();  
//	  
//	        jsonOne.put("name", "kewen");  
//	        jsonOne.put("age", "24");  
//	  
//	        jsonTwo.put("hobbit", "Dota");  
//	        jsonTwo.put("hobbit2", "wow");  
//	  
//	        JSONObject jsonThree = new JSONObject();  
//	  
//	        jsonThree.putAll(jsonOne);  
//	        jsonThree.putAll(jsonTwo);  
//	  
//	        System.out.println(jsonThree);  
//	       
	       
	String a = "Addressee%3D%E6%94%B6%E4%BB%B6%E4%BA%BA%26ApplyDate%3D2018-09-09+23%3A31%3A22%26ApplyNo%3Dcsj004098020000018%26ApplyType%3D%E5%8A%9E%E4%BB%B6%E6%9D%A5%E6%BA%90%26ApplyerPageCode%3D310000000000000000%26ApplyerPageType%3D%E8%BA%AB%E4%BB%BD%E8%AF%81%26ArchivesType%3D1%26DepartCode%3D%E5%8A%9E%E7%90%86%E7%82%B9%E7%BC%96%E5%8F%B7%26Identity_authentication%3D%E9%AB%98%E7%BA%A7%E5%AE%9E%E5%90%8D%E8%BA%AB%E4%BB%BD%E8%AE%A4%E8%AF%81%26Info%3D%7B%22Year%22%3A%222020%22%2C%22Man%22%3A%22%E7%94%B7%22%2C%22Woman%22%3A%22%E5%A5%B3%22%2C%22Manidenfication%22%3A%22332525198804030027%22%2C%22Womanidenfication%22%3A%22332525198905040027%22%7D%26Mailing_address%3D%E9%82%AE%E4%BB%B6%E5%9C%B0%E5%9D%80%26Mobile%3D15000000000%26OrgName%3D%E5%8A%9E%E7%90%86%E7%82%B9%E5%90%8D%E7%A7%B0%26Projid%3D%E6%94%BF%E5%8A%A1%E6%9C%8D%E5%8A%A1%E4%BA%8B%E9%A1%B9%E7%BB%9F%E4%B8%80%E7%94%B3%E6%8A%A5%E5%8F%B7%26ReceiptDepartCode%3D%E6%94%B6%E4%BB%B6%E9%83%A8%E9%97%A8%EF%BC%88%E5%8A%9E%E7%90%86%E7%82%B9%EF%BC%89%E7%BC%96%E5%8F%B7%26ReceiptOrganName%3D%E6%B5%99%E6%B1%9F%E7%9C%81%26UseWay%3D001.%E5%88%B0%E5%8F%97%E7%90%86%E9%A6%86%E5%8F%96%E4%BB%B6%26Username%3D%E5%BC%A0%E4%B8%89%26sign=";
	String b = "Addressee%3D%E6%94%B6%E4%BB%B6%E4%BA%BA%26ApplyDate%3D2018-09-09+23%3A31%3A22%26ApplyNo%3Dcsj004098020000017%26ApplyType%3D%E5%8A%9E%E4%BB%B6%E6%9D%A5%E6%BA%90%26ApplyerPageCode%3D310000000000000000%26ApplyerPageType%3D%E8%BA%AB%E4%BB%BD%E8%AF%81%26ArchivesType%3D1%26DepartCode%3D%E5%8A%9E%E7%90%86%E7%82%B9%E7%BC%96%E5%8F%B7%26Identity_authentication%3D%E9%AB%98%E7%BA%A7%E5%AE%9E%E5%90%8D%E8%BA%AB%E4%BB%BD%E8%AE%A4%E8%AF%81%26Info%3D%7B%22Year%22%3A%222020%22%2C%22Man%22%3A%22%E7%94%B7%22%2C%22Woman%22%3A%22%E5%A5%B3%22%2C%22Manidenfication%22%3A%22332525198804030027%22%2C%22Womanidenfication%22%3A%22332525198905040027%22%7D%26Mailing_address%3D%E9%82%AE%E4%BB%B6%E5%9C%B0%E5%9D%80%26Mobile%3D15000000000%26OrgName%3D%E5%8A%9E%E7%90%86%E7%82%B9%E5%90%8D%E7%A7%B0%26Projid%3D%E6%94%BF%E5%8A%A1%E6%9C%8D%E5%8A%A1%E4%BA%8B%E9%A1%B9%E7%BB%9F%E4%B8%80%E7%94%B3%E6%8A%A5%E5%8F%B7%26ReceiptDepartCode%3D%E6%94%B6%E4%BB%B6%E9%83%A8%E9%97%A8%EF%BC%88%E5%8A%9E%E7%90%86%E7%82%B9%EF%BC%89%E7%BC%96%E5%8F%B7%26ReceiptOrganName%3D%E6%B5%99%E6%B1%9F%E7%9C%81%26UseWay%3D001.%E5%88%B0%E5%8F%97%E7%90%86%E9%A6%86%E5%8F%96%E4%BB%B6%26Username%3D%E5%BC%A0%E4%B8%89%26sign=";
	System.out.println(a.equals(b));
	
	}
	
	
	
	
	
	
	
}
