package com.infinitePossibilities.utils.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.util.CharsetUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.infinitePossibilities.utils.requestUtils.HttpApiClient;
import wfc.service.database.BlobHelper;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.SQL;

@Controller
public class UapplyUploadDocument {


	@RequestMapping("Multipart/upLoadFile.do")
	public void upFile (HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/piain;charset=UTF-8");
			MultipartHttpServletRequest params = (MultipartHttpServletRequest) request;
			String id = params.getParameter("id");
			System.out.println("id"+id);
			MultipartFile file = params.getFile("file");
			if (file == null) {
				System.out.println("附件为空!");
				response.getWriter().print("附件为空!");
				return;
			}
			byte[] filebyte;
			String fileName = file.getOriginalFilename();
			filebyte = file.getBytes();
			if (filebyte == null || filebyte.length == 0) {
				System.out.println("附件为空!");
				response.getWriter().print("附件为空!");
				return;
			}
			String uuid = UUID.randomUUID().toString();
			String fjId = "202008"+UUID.randomUUID().toString();
			String sql = "insert into DANGAN_FJ(ST_FJ_ID,ST_APPLY_ID,FJ_NAME) values (?,?,?)";
			Object[] objects = new Object[] { uuid, fjId, fileName };
			SQL.execute(sql,objects);
			Conditions conds = Conditions.newOrConditions();
			conds.add(new Condition("ST_FJ_ID", Condition.OT_EQUAL, uuid));
			BlobHelper.setBlob("DANGAN_FJ", "APPLY_FJ", conds.toString(),filebyte, conds.getObjectArray());
			response.getWriter().print("上传成功！");
		}
	
	
	public static void main(String[] args) throws Exception {
		
		JSONObject json = new JSONObject();
		 File file=new File("C:\\Users\\lifan\\Desktop\\jj.jpg"); 
		 if(file.length()==0){
				json.put("400", "根据地址找不到指定的文件。");
			}
		 String applyno = "002000012205457";
		 String fileName =file.getName();
					String token = HttpApiClient.getAccessToken("", "");
					MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
					multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
					multipartEntityBuilder.setCharset(CharsetUtils.get("UTF-8"));
					multipartEntityBuilder.addPart("accessToken",
							new StringBody(token, ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), "UTF-8")));
					multipartEntityBuilder.addPart("applyNo",
							new StringBody(applyno, ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));
					multipartEntityBuilder.addPart("documentType",
							new StringBody("1", ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),"UTF-8")));
					byte[] bytes = File2byte(file);
						InputStream is = new ByteArrayInputStream(bytes);
						multipartEntityBuilder.addBinaryBody("file", is, ContentType.MULTIPART_FORM_DATA,
								fileName);
						String reult = HttpApiClient.apiResFile("/uploadDocument", multipartEntityBuilder);
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
