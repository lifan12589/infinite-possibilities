package com.wondersgroup.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * 将一个输入流转化为字符串
 */
public class StreamHelperUtils {

	public StreamHelperUtils() {
	}

	public static byte[] toByteArray(InputStream is) {
		if (is == null) {
			return null;
		} else {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			toOutputStream(is, baos);
			return baos.toByteArray();
		}
	}

	public static void toOutputStream(InputStream is, OutputStream os) {
		byte buffer[] = new byte[2048];
		int bytesRead;
		try {
			while ((bytesRead = is.read(buffer, 0, 1024)) != -1)
				os.write(buffer, 0, bytesRead);
		} catch (IOException ex) {
                System.out.println(ex.toString());		
                }
	}

	public static StringBuffer toStringBuffer(Reader reader) {
		if (reader == null) {
			return null;
		} else {
			StringWriter writer = new StringWriter();
			toWriter(reader, writer);
			return writer.getBuffer();
		}
	}

	public static void toWriter(Reader reader, Writer writer) {
		char buffer[] = new char[2048];
		int charsRead;
		try {
			while ((charsRead = reader.read(buffer, 0, 1024)) != -1)
				writer.write(buffer, 0, charsRead);
		} catch (IOException ex) {
            System.out.println(ex.toString());		
		}
	}
}
