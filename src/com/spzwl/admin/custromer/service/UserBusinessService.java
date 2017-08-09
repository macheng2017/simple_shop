package com.spzwl.admin.custromer.service;

import com.spzwl.admin.custromer.dao.excption.UserIsExistException;
import com.spzwl.admin.custromer.domain.User;

public interface UserBusinessService {

	// 用户注册功能
	public abstract boolean reg(User user) throws UserIsExistException;

	// 用户登录功能
	public abstract User login(String username, String password);

	public abstract User findUser(String username);
}