package com.spzwl.util;

import java.sql.Connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
//	使用c3p0数据源比较简单,加载默认c3p0-config.xml即可
	public static ComboPooledDataSource cpds =null ;
	static{
		 try {
			cpds = new ComboPooledDataSource();
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		} 
	}
	//返回Connection对象
	public static Connection getConnection() {
			Connection conn;
			try {
				conn = th.get();
				if (conn == null) {
					conn = cpds.getConnection();
					th.set(conn);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		return conn;
	}
	//返回数据源
	public static ComboPooledDataSource getDataSource(){
		return cpds;
	}
	//使用 TreadLocal开启事务
//	使用ThreadLocal 改造工具类使其线程带上事务管理
	
//	1.定义一个静态的ThreadLocal 使其在类加载的时候就开启并且在web应用的生命周期内驻留内存中
//	2.创建管理事务的4个方法
//	3.创建开启线程事务方法 startTransaction 
//	4.创建提交线程事务方法 commitTransaction
//	5.改造获取Connection链接 方法使其带上线程事务
//  6.最最重要的,关闭Connection连接并且从ThreadLocal中清除Connection,一定要记住要不然长时间运行内存溢出
	
	private static ThreadLocal<Connection> th = new ThreadLocal<Connection>();
	
	public  static void startTransaction(){
		try {
			Connection conn = th.get();
			if (conn == null) {
				conn = getConnection();
				th.set(conn);
			}
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	public  static void commitTransaction(){
		try {
			Connection conn = th.get();
			if (conn != null) {
				conn.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	public  static void closeConnection(){
		try {
			Connection conn = th.get();
			if (conn != null) {
				conn.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
//			一定要确保从ThreadLocal 中移除 Connection
			th.remove();
		}
	}
}
