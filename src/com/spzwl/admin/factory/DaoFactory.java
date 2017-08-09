package com.spzwl.admin.factory;

import java.io.IOException;
import java.util.Properties;

public class DaoFactory {
	// 使用工厂类做到dao 与 services 层的解耦
	private static Properties config = new Properties();
	private static DaoFactory daoFactory = new DaoFactory();
	static{
		try {
			config.load(DaoFactory.class.getClassLoader().getResourceAsStream("dao.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}
	private DaoFactory() {
	}

	public static DaoFactory getInstance() {
		return daoFactory;
	}
	
	public  <T>T getImpl(Class<T> calzz){
		String key = calzz.getSimpleName();
		
		String className = (String) config.get(key);
		
		try {
			T dao = (T) Class.forName(className).newInstance();
			return dao;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}
	

}
