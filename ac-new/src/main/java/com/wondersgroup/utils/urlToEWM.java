package com.wondersgroup.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
/**
 * url 转换成二维码
 * 需要jar包：com.google.zxing:core:3.2.1
 * @author lifan
 */

public class urlToEWM {

	public static void main(String[] args) throws WriterException, IOException {
		int width = 500;
		int height = 500;
	        // 图片格式和输出路径
	        String format = "png";
	        String url = "C:\\Users\\lifan\\Desktop\\ewm.png";
	        Hashtable<EncodeHintType, String> hints =
	                new Hashtable<EncodeHintType, String>();
	        // 内容所使用编码
	        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
	        BitMatrix bitMatrix = new MultiFormatWriter()
	                .encode("http://www.baidu.com", BarcodeFormat.QR_CODE, width, height, hints);
	        BufferedImage image = new BufferedImage(width, height,
	                BufferedImage.TYPE_INT_RGB);
	        for (int x = 0; x < width; x++) {
	            for (int y = 0; y < height; y++) {
	                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
	            }
	        }
	        ImageIO.write(image, format, new File(url));
	        System.out.println("转换成功！");
	}








}
