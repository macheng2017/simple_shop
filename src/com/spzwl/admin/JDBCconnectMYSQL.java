package com.spzwl.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class JDBCconnectMYSQL {
	
	
	public  void getConnection() throws SQLException {
//连接步骤:
	//  1. 加载驱动
//		2. 获取链接.
//		3. 获取向数据库发送sql语句的,statement对象
//		4. 向数据库发送sql,获取数据库返回的结果集
//		5. 从结果集中返回数据
//		6. 关闭资源
		String url = "jdbc:mysql://localhost:3306/day7.15"; //这里的写法其实和 http://www.baidu.com 差不多 协议名+....
		String username ="root";
		String password ="47580688Mac";
		//  1. 加载驱动
		DriverManager.registerDriver(new Driver());
//		2. 获取链接.
		Connection conn = DriverManager.getConnection(url, username, password); //注意添加是 java.sql.Connection
//		3. 获取向数据库发送sql语句的,statement对象
		  Statement statement = conn.createStatement();
//		4. 向数据库发送sql,获取数据库返回的结果集
		  ResultSet resultSet =statement.executeQuery("select * from customer_order");
//		5. 从结果集中返回数据
		  while (resultSet.next()){
			  System.out.println(resultSet.getObject("uuid"));
			  System.out.println(resultSet.getObject("name"));
			  System.out.println(resultSet.getObject("address"));
			  System.out.println(resultSet.getObject("tell"));
			  //System.out.println(resultSet.getObject("birthday"));
		  }
//		6. 关闭资源
//		  按照打开顺序的反向顺序关闭资源
		  resultSet.close();
		  statement.close();
		  conn.close();
	}
}



	
	

