package com.wondersgroup.utils.file;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class pdfController {

	/**
	 * pdf  网页打开
	 * @param response
	 * @throws IOException
	 */
	@ResponseBody
    @RequestMapping("/download.do")
    public void download( HttpServletResponse response
                        ) throws IOException {
        String filePath = "C:\\Users\\lifan\\Desktop\\2020.pdf";
        System.out.println("filePath:" + filePath);
        File f = new File(filePath);
        if(!f.isFile() && !f.exists()){ //判断文件是否存在
            response.sendError(404, "File not found!");
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] bs = new byte[1024];
        int len = 0;
        response.reset(); // 非常重要
        if (true) { // true为在线打开方式
            URL u = new URL("file:///" + filePath);
            String contentType = u.openConnection().getContentType();
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "inline;filename="
                    + "2020.pdf");
            // 文件名应该编码成utf-8
        } else {// 纯下载方式
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + "2020.pdf");
        }
        OutputStream out = response.getOutputStream();
        while ((len = br.read(bs)) > 0) {
            out.write(bs, 0, len);
        }
        out.flush();
        out.close();
        br.close();
    }
	



	
	
	/**
	* @methodname formatFileName
	* @Description {设置响应信息和格式化附件名字}
	* @author admin
	*/
	private void formatFileName(HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
	    String fileName = "2020.zip";
	    // 获取浏览器信息
	    if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
	        fileName = new String(fileName.getBytes("GB2312"),"ISO-8859-1");
	    } else {
	        // 处理中文文件名的问题
	        fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
	        fileName = new String(fileName.getBytes("UTF-8"), "GBK");
	    }
	    // 清除首部的空白行
	    response.reset();
	    // 设置Response容器的编码
	    response.setCharacterEncoding("UTF-8");
	    // 不同类型的文件对应不同的MIME类型
	    response.setContentType("application/x-msdownload");
	    response.setHeader("Content-Disposition","attachment; filename="+fileName);
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 压缩.
	 * 
	 * @param inputByte
	 *            需要解压缩的byte[]数组
	 * @return 压缩后的数据
	 * @throws IOException
	 */
	public static byte[] deflater(final byte[] inputByte) throws IOException {
		int compressedDataLength = 0;
		Deflater compresser = new Deflater();
		compresser.setInput(inputByte);
		compresser.finish();
		ByteArrayOutputStream o = new ByteArrayOutputStream(inputByte.length);
		byte[] result = new byte[1024];
		try {
			while (!compresser.finished()) {
				compressedDataLength = compresser.deflate(result);
				o.write(result, 0, compressedDataLength);
			}
		} finally {
			o.close();
		}
		compresser.end();
		return o.toByteArray();
	}
}
