package com.infinitePossibilities.utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;

public class outPdf {

	public void outPut(HttpServletRequest request, HttpServletResponse response, Integer type) {
        ClassPathResource classPathResource = new ClassPathResource("/.pdf");
        String filename = ".pdf";
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            String userAgent = request.getHeader("User-Agent");
            response.setCharacterEncoding("utf-8");
            byte[] bytes = userAgent.contains("MSIE") ? filename.getBytes() : filename.getBytes("UTF-8");
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + new String(bytes, "iso8859-1"));
            bufferedInputStream = new BufferedInputStream(classPathResource.getInputStream());
            //bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            byte[] bytes2 = new byte[1024];
            int i = 0;
            while ((i = bufferedInputStream.read(bytes2)) != -1){
                response.getOutputStream().write(bytes2, 0, i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                response.getOutputStream().flush();
                response.getOutputStream().close();
                //bufferedOutputStream.close();
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	
	//??????BufferedInputStream???BufferedOuputStream????????????
	 public static void main(String[] args) throws Exception {  
	        // ?????????????????????????????????????????????  
	        BufferedInputStream in = new BufferedInputStream(new FileInputStream("F:\\test.jpg"));  
	        File file = new File("E:\\test.jpg");  
	        if (file != null) {  
	            file.createNewFile();  
	        }  
	        // ?????????????????????????????????????????????  
	        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));  
	        byte[] bb = new byte[1024];// ??????????????????????????????????????????  
	        int n;// ???????????????????????????????????????  
	        while ((n = in.read(bb)) != -1) {  
	            out.write(bb, 0, n);// ??????????????????  
	        }  
	        out.close();// ?????????  
	        in.close();  
	    }  
	
	
	
	
}
