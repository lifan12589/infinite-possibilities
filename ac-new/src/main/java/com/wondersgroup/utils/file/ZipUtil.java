package com.wondersgroup.utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ZipUtil {
	
		private ZipUtil() {
			
		}
		
		/**
		 * pdf  压缩下载
		 * @param response
		 * @throws IOException
		 */
		@ResponseBody
	    @RequestMapping("/zipDownload.do")
		public static void downZipFile(HttpServletRequest request, HttpServletResponse res){
			String basePath="C:\\Users\\lifan\\Desktop\\2020.pdf";
			File file = new File(basePath);
			File file1=new File("C:\\Users\\lifan\\Desktop\\2020.zip");
			File files=new File("C:\\Users\\lifan\\Desktop");
			if(!files.exists()){
			files.mkdirs();
		    }
			if(!file1.exists()){
			try {
				file1.createNewFile();
			} catch (IOException e){
				System.out.println("记录日志");
				return;
				}
			}
		try (
		ZipOutputStream zip=new ZipOutputStream(new FileOutputStream(file1));//zip的临时存放地址
		ServletOutputStream outputStream = res.getOutputStream();
		FileInputStream in=new FileInputStream(file1);
		BufferedInputStream ins=new BufferedInputStream(in);){
		//读取zip 大文件的时候 记得添加缓冲区
//		File[] listFiles = file.listFiles();
//		createZipFile(listFiles,zip);
		//创建 zip文件
		createZipFile(file,zip);
		 zip.close();
		 res.setContentType("text/html; charset=UTF-8"); //设置编码字符  
		 res.setContentType("application/octet-stream"); //设置内容类型为下载类型  
		 res.setHeader("Content-disposition", "attachment;filename=tes.zip");//设置下载的压缩文件名称
		
		 byte [] buff=new byte[1024];//记得一定要有缓存池
		 int i=0;
		 while((i=ins.read(buff))>0){
		outputStream.write(buff,0,i);
		}
		 outputStream.flush();
		} catch (Exception e) {
		System.out.println("记录日志");

		return;
		}finally {
		//创造的临时文件夹删除
		if(file1.exists()){
		file1.delete();
	    }
	}
}

//	private static void createZipFile(File [] listFiles,ZipOutputStream zip){
	private static void createZipFile(File file,ZipOutputStream zip){
//		for (File file : listFiles) {
//是文件
			if(file.isFile()){
				try(BufferedInputStream buffer=new BufferedInputStream(new FileInputStream(file));){
				ZipEntry zipEntry=new ZipEntry(file.getName());
				zip.putNextEntry(zipEntry);
				byte [] buff=new byte[2048];
				int i=0;
				while((i=buffer.read(buff))>0){
					zip.write(buff,0,i);
				}
				zip.closeEntry();
				}catch(Exception e){
					e.printStackTrace();
				}
				}else{
//					File listFiles2 = file.listFiles();
//					createZipFile(listFiles2,zip);
					createZipFile(file,zip);
				}
			}
//		}

	
	
//==========================================================================
	//zip文件解压缩并解析
	
	
	public static List<String> unzip(String zipFilePath,String outPutDirectory){//输入源zip路径   和 解压文件路径
		List<String> fileList = new ArrayList<String>();
		try {
            ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFilePath));
            BufferedInputStream bin = new BufferedInputStream(zin);
            BufferedOutputStream bout = null;
            File file=null;  
            ZipEntry entry;
            try {
                while((entry = zin.getNextEntry())!=null && !entry.isDirectory()){
                	file = new File(outPutDirectory,entry.getName());  
                    if(!file.exists()){  
                        (new File(file.getParent())).mkdirs();  
                    }
                    bout = new BufferedOutputStream(new FileOutputStream(file));  
                    int b;
                    while((b=bin.read())!=-1){  
                    	bout.write(b);  
                    }
                    bout.flush();
                    fileList.add(file.getAbsolutePath());
                    System.out.println(file+"解压成功");
                }
            } catch (IOException e) {  
                e.printStackTrace();  
            }finally{
                try {
					bin.close();
					zin.close();
					if(bout!=null){
						bout.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}  
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
        }
		return fileList;
	}
	
			public static void main(String[] args) {
				
				String zipFilePath = "C:\\Users\\lifan\\Desktop\\pay\\2020.zip";
				String outPutDirectory = "C:\\Users\\lifan\\Desktop\\pay";
				unzip(zipFilePath,outPutDirectory);
		}

}
