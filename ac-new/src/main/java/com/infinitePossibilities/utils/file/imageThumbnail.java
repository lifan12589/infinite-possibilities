package com.infinitePossibilities.utils.file;

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import javax.imageio.ImageIO;

import wfc.service.database.BlobHelper;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.SQL;

public class imageThumbnail {

private static void createThumbnail(String filename, int thumbWidth, int thumbHeight, String outFilename)throws Exception{
		  //根据地址载入图片
	      Image image = Toolkit.getDefaultToolkit().getImage(filename);
		  MediaTracker mediaTracker = new MediaTracker(new Container());
		  mediaTracker.addImage(image, 0);
		  mediaTracker.waitForID(0);
		  
		  double thumbRatio = (double)thumbWidth / (double)thumbHeight;
		  int imageWidth = image.getWidth(null);
		  int imageHeight = image.getHeight(null);
		  double imageRatio = (double)imageWidth / (double)imageHeight;
		  if (thumbRatio < imageRatio) {
		  thumbHeight = (int)(thumbWidth / imageRatio);
		  } else {
		  thumbWidth = (int)(thumbHeight * imageRatio);
		  }
		  
		  BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
		  Graphics2D graphics2D = thumbImage.createGraphics();
		  graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		  graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
		  File fileThumbnail = new File(outFilename);  
		  ImageIO.write(thumbImage, "jpg", fileThumbnail);

		  
		  //获取缩略图做其他业务用--放在别处
		  BufferedInputStream in = new BufferedInputStream(new FileInputStream(outFilename));  
	        File file = new File("C:\\Users\\lifan\\Desktop\\pay\\jjj.jpg");  
	        if (file != null) {  
	            file.createNewFile();  
	        }  
	        // 指定要写入文件的缓冲输出字节流  
	        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));  
	        byte[] bb = new byte[1024];// 用来存储每次读取到的字节数组  
	        int n;// 每次读取到的字节数组的长度  
	        while ((n = in.read(bb)) != -1) {  
	            out.write(bb, 0, n);// 写入到输出流  
	        }  
	        out.close();// 关闭流  
	        in.close();  
	        fileThumbnail.delete();//删除缩略图
		  
	        
		  	//或把缩略图入库
	        byte [] filebyte = File2byte(fileThumbnail);
	        Timestamp  saveTime=new Timestamp(new Date().getTime());
		  	String uuid = UUID.randomUUID().toString();
			String fjId = "202004"+UUID.randomUUID().toString();
			String sql = "insert into DANGAN_FJ(ST_FJ_ID,ST_APPLY_ID,TIME) values (?,?,?)";
			Object[] objects = new Object[] {uuid,fjId,saveTime};
			SQL.execute(sql,objects);
			Conditions conds = Conditions.newOrConditions();
			conds.add(new Condition("ST_FJ_ID", Condition.OT_EQUAL, uuid));
			BlobHelper.setBlob("DANGAN_FJ", "APPLY_FJ", conds.toString(),filebyte, conds.getObjectArray());
			fileThumbnail.delete();//删除缩略图
  }
	
		/**
		 * 将文件转换成byte数组
		 * @param filePath
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


	public static void main(String[] args) throws Exception {
		//原地址，缩略图高，宽，输出地址和缩略图名
		createThumbnail("C:\\Users\\lifan\\Desktop\\jj.jpg",100,75,"C:\\Users\\lifan\\Desktop\\pay\\jj.jpg");
	}
	
}
