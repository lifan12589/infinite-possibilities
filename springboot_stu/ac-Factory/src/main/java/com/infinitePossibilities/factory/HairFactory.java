package com.infinitePossibilities.factory;

import java.util.Map;

/**
 * 发型工厂
 * @author Administrator
 *
 */
public class HairFactory {

	/**
	 * 根据类型来创建对象
	 * @param key
	 * @return
	 */
	public HairInterface getHair(String key){
		if("left".equals(key)){
			return new LeftHair();
		}else if("right".equals(key)){
			return new RightHair();
		}else{
			return new InHair();
		}

	}
	/**
	 * 根据类的名称来生产对象
	 * @param className
	 * @return
	 */
	public HairInterface getHairByClass(String className){

		try {
			HairInterface hair = (HairInterface) Class.forName(className).newInstance();
			return hair;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 根据类的名称来生产对象
	 * @param
	 * @return
	 */
	public HairInterface getHairByClassKey(String key){

		try {
			Map<String, String> map = new PropertiesReader().getProperties();
			System.out.println(map+"////"+key);
			HairInterface hair = (HairInterface) Class.forName(map.get(key)).newInstance();
			return hair;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}