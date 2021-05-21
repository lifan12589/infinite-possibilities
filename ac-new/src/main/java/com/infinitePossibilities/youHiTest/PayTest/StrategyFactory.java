package com.infinitePossibilities.youHiTest.PayTest;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.reflections.Reflections;


public class StrategyFactory {

	private static StrategyFactory strategyFactory = new StrategyFactory();
	
	private StrategyFactory(){
	};
	
	public static StrategyFactory getInstance() {
		return strategyFactory;
	}
	
	private static HashMap<Integer, String > souce_map = new HashMap<>();
	private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
	
	static{
		Reflections reflections = new Reflections("com.infinitePossibilities.youHiTest.impl");
		System.out.println("reflections--->"+reflections);
		Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(Pay.class);
		System.out.println("classSet--->"+classSet);
		for(Class clazz : classSet){
			Pay pay = (Pay) clazz.getAnnotation(Pay.class);
			souce_map.put(pay.channelId(), clazz.getCanonicalName());
		}
		System.out.println("souce_map--->"+souce_map);
	}
	
	public Strategy create(int channelId) throws Exception{
		String classzz= souce_map.get(channelId);
		Class class_z = Class.forName(classzz);
		return (Strategy)class_z.newInstance();

	}
	
}
