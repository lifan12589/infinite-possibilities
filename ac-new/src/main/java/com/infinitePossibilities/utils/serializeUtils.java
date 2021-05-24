package com.infinitePossibilities.utils;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

public class serializeUtils {

	/**
	 * 序列化
	 * @param t
	 * @param clazz
	 * @return
	 */
	public static <T> byte[] enSerialize(T t,Class <T> clazz) {

		return ProtobufIOUtil.toByteArray(t, RuntimeSchema.createFrom(clazz),
				   LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

	}

	/**
	 * 反序列化
	 * @param data
	 * @param clazz
	 * @return
	 */
	public static <T> T deSerialize(byte [] data,Class <T> clazz) {

		RuntimeSchema<T> schema = RuntimeSchema.createFrom(clazz);
		T t = schema.newMessage();
		ProtobufIOUtil.mergeFrom(data, t, schema);
		return t;

	}



}
