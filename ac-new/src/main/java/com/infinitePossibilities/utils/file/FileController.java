/**
 * 
 */
/**
 * @author lifan
 *
 */
package com.infinitePossibilities.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import wfc.service.database.BlobHelper;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.SQL;

@RestController
@RequestMapping("/upload")
public class FileController {
	
	@RequestMapping("/file")
	public String file(MultipartFile file,HttpServletRequest request){
		if(file.isEmpty()){
			return "上传文件不能为空，请重新上传！";
		}else if(file.getSize()<=0){
			return "上传文件大小不能为0，请重新上传！";
		}
		System.out.println(file.getContentType());//文件类型
		String fileName = file.getOriginalFilename();//文件原始名字
		System.out.println(fileName);
//		Date date = new  Date();
//		Long time = date.getTime();
//		String newFileName = fileName;
		String filePath = System.getProperty("user.dir");//项目在本地的绝对路径
		String newFilePath = filePath+"\\src\\main\\webapp\\uploadFile\\images\\";
		File fl = new File(newFilePath);
		if(fl.exists()){//存在
			fl.mkdirs();//建立下级目录
		}
		FileOutputStream out = null;
			try {
				out = new FileOutputStream(newFilePath+fileName);
				out.write(file.getBytes());
				out.flush();
				out.close();
				return "http://localhost:8080/uploadFile/images/"+fileName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		return "上传失败！";
	}
	/**
	 * 多个附件传到本地
	 * @param files
	 * @param request
	 * @return
	 */
	@RequestMapping("/files")
	public String  files(MultipartFile [] files ,HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		for(MultipartFile file :files){
			if(files.length>=2){
				return "最多上传3张！";
			}
			if(file.isEmpty()){
				return "上传文件不能为空，请重新上传！";
			}else if(file.getSize()<=0){
				return "上传文件大小不能为0，请重新上传！";
			}
			System.out.println(file.getContentType());//文件类型
			String fileName = file.getOriginalFilename();//文件原始名字
			System.out.println(fileName);
//			Date date = new  Date();
//			Long time = date.getTime();
//			String newFileName = fileName;
			String filePath = System.getProperty("user.dir");//项目在本地的绝对路径
			String newFilePath = filePath+"\\src\\main\\webapp\\uploadFile\\images\\";
			File fl = new File(newFilePath);
			if(fl.exists()){//存在
				fl.mkdirs();//建立下级目录
			}
			FileOutputStream out = null;
				try {
					out = new FileOutputStream(newFilePath+fileName);
					out.write(file.getBytes());
					out.flush();
					out.close();
					sb.append("http://localhost:8080/uploadFile/images/"+fileName+"\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return sb.toString();
	}
	
	@RequestMapping("/filesInSql")
	public String  filesInSql(MultipartFile [] files ,HttpServletRequest request) throws IOException {
		try {
			for(MultipartFile file :files){
				if(files.length>=4){
					System.out.println(files.length);
					return "最多上传3张！";
				}
				if(file.isEmpty()){
					return "上传文件不能为空，请重新上传！";
				}else if(file.getSize()<=0){
					return "上传文件大小不能为0，请重新上传！";
				}
				System.out.println(file.getContentType());//文件类型
				String fileName = file.getOriginalFilename().toString();//文件原始名字
				System.out.println(fileName);
				byte[] filebyte= file.getBytes();
				String uuid = UUID.randomUUID().toString();
				String fjId = "202004"+UUID.randomUUID().toString();
				String sql = "insert into YWTB_PJ_FJ(PJ_FJ_ID,ST_PJ_ID,IMG_NAME) values (?,?,?)";
				Object[] objects = new Object[] { uuid, fjId, fileName };
				SQL.execute(sql,objects);
				Conditions conds = Conditions.newOrConditions();
				conds.add(new Condition("PJ_FJ_ID", Condition.OT_EQUAL, uuid));
				BlobHelper.setBlob("YWTB_PJ_FJ", "PJ_IMG", conds.toString(),filebyte, conds.getObjectArray());
				
				return "上传成功！";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "系统繁忙，请稍候再试！";
	}
	
	/**
	 * File 转成byte64参数传递
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String  fileEncoder(File file) throws IOException{
		 BASE64Encoder encoder = new BASE64Encoder();
//		 File file = new File("E:\\test.jpg");  
//		 byte[] attach = BlobHelper.getBlob("AC_UAPPLY_ATTACH", "BL_CONTENT", conditions.toString(),conditions.getObjectArray());
		 FileInputStream fin = null;
	     fin = new FileInputStream(file);
	     byte[] by = new byte[fin.available()];
		 String yx = encoder.encode(by).replaceAll("(\r\n|\r|\n|\n\r)", "");
		return yx;
	}
	
	/**
	 * File 转成byte64
	 * @param file
	 * @return
	 */
	public static String file2Base64(File file) {
        if(file==null) {
            return null;
        }
        String base64 = null;
        FileInputStream fin = null;
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            fin = new FileInputStream(file);
            byte[] buff = new byte[fin.available()];
            fin.read(buff);
            base64 =  encoder.encode(buff);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return base64;
    }
	
	/**
	 * base64 转 File
	 * @param base64
	 * @return
	 * @throws IOException
	 */
	public static File base64ToFile(String base64) throws IOException {
        if(base64==null||"".equals(base64)) {
            return null;
        }
        byte[] buff= new BASE64Decoder().decodeBuffer(base64);
        File file=null;
        FileOutputStream fout=null;
        try {
            file = File.createTempFile("tmp", null);
            fout=new FileOutputStream(file);
            fout.write(buff);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fout!=null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }
	
	
	
	
	
	
//	public static void main(String[] args) throws IOException {
//		
////		String filePath = System.getProperty("user.dir");//项目在本地的绝对路径
////		System.out.println(filePath); //D:\eclipse-workspace\..
//		
//		try {
//			 File f = base64ToFile("111111111111111");
//			 BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
//			    byte[] bs = new byte[1024];
//			    int len = 0;
//			    FileOutputStream out = new FileOutputStream(f) ;
//			    while ((len = br.read(bs)) > 0) {
//			        out.write(bs, 0, len);
//			    }
//			    System.out.println(out);
//			    out.flush();
//			    out.close();
//			    br.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//	}
	
}