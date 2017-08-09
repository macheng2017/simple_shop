package com.spzwl.admin.custromer.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.spzwl.admin.custromer.dao.UserDao;
import com.spzwl.admin.custromer.dao.excption.DaoException;
import com.spzwl.admin.custromer.domain.User;
import com.spzwl.util.JdbcUtils;

public class UserDaoImpl implements UserDao {
	// 注册添加用户

		@Override
		public void add(User user) {
		try{	
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
				String sql = "insert into admin_user (userid,username,password,email,qq,address,birthday,phoneid,nickname)"
						+ "values(?,?,?,?,?,?,?,?,?)";
				Object[] params ={
						user.getUserid(),
						user.getUsername(),
						user.getPassword(),
						user.getEmail(),
						user.getQq(),
						user.getAddress(),
						user.getBirthday() == null?null:new java.sql.Date(user.getBirthday().getTime()),
						user.getPhoneid(),
						user.getNickname()
						};
				int len = qr.update(sql, params);
			
				if(len<0){
					throw new DaoException("用户没有注册成功!");
				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw new DaoException(e);
			} 
		}
		//修改用户
		@Override
		public void update(User user) {
			try{	
				QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
				String sql = "update admin_user set username=?,password=?,email=?,qq=?,address=?,birthday=?,phoneid=?,nickname=? where userid=?";
				Object[] params ={
						user.getUsername(),
						user.getPassword(),
						user.getEmail(),
						user.getEmail(),
						user.getQq(),
						user.getAddress(),
						user.getBirthday() == null?null:new java.sql.Date(user.getBirthday().getTime()),
						user.getPhoneid(),
						user.getNickname(),
						user.getUserid()
				};
				int len = qr.update(sql, params);
				
				if(len<0){
					throw new DaoException("用户没有修改成功!");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DaoException(e);
			} 
		}
		//查找用户是否存在
		@Override
		public boolean isExist(String username) {
			try {
				QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		
				String sql = "select count(*) from admin_user where username=?";
				long len =  (Long) qr.query(sql, new ScalarHandler(),new Object[]{username});	
				if(len>0){
					return true;
				}
				return false;
			}catch (Exception e){
				e.printStackTrace();
				throw new DaoException(e);
			}
		}
		//用户登录
		@Override
		public User find(String username, String password) {
			
			try {
				QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
				String sql = "select * from admin_user where username=? and password=?";
				Object [] params = {username,password};
				User user = (User) qr.query(sql, new BeanHandler(User.class), params);
				return user;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DaoException(e);
			} 
		}
		//用户登录
		@Override
		public User findUser(String username) {
			try {
				QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
				String sql = "select * from admin_user where username=? ";
				Object [] params = {username};
				User user = (User) qr.query(sql, new BeanHandler(User.class), params);
				return user;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DaoException(e);
			} 
		}
	}
