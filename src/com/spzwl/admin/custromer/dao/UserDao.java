package com.spzwl.admin.custromer.dao;

import com.spzwl.admin.custromer.domain.User;

public interface UserDao {

	//	添加用户
	public abstract void add(User user);

	public abstract boolean isExist(String username);

	public abstract User find(String username, String password);

	void update(User user);

	User findUser(String username);

}