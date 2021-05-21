package com.wondersgroup.youHiTest.demo.utils;

import java.util.HashMap;
import java.util.Set;

import org.reflections.Reflections;

import com.wondersgroup.youHiTest.PayTest.Pay;

public class StrategyFactory {

	private static StrategyFactory strategyFactory = new StrategyFactory();
	
	private StrategyFactory(){
	}
	
	public static StrategyFactory getstrategyFactory(){
		return strategyFactory;
	}
	
	private static HashMap<String, String> map = new HashMap<>();
	
	static{
		Reflections reflections = new Reflections("com.wondersgroup.youHiTest.demo.service");
		System.out.println("扫描出的类--->"+reflections);
		Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(SendService.class);
		System.out.println("带注解的类--->"+classSet);
		for (Class classzz : classSet) {
			SendService sendService = (SendService) classzz.getAnnotation(SendService.class);
			map.put(sendService.sheng(), classzz.getCanonicalName());
		}
		System.out.println("map--->"+map);
	}
	
	public Strategy getClass(String sheng ) throws Exception{
		String  classzz =  map.get(sheng);
		Class<?> classz = Class.forName(classzz);
		return (Strategy) classz.newInstance();
	}
	
	
	
	
	
	
	
}
