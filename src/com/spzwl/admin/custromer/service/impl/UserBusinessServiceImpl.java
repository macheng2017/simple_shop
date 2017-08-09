package com.spzwl.admin.custromer.service.impl;

import com.spzwl.admin.custromer.dao.UserDao;
import com.spzwl.admin.custromer.dao.excption.UserIsExistException;
import com.spzwl.admin.custromer.domain.User;
import com.spzwl.admin.custromer.service.UserBusinessService;
import com.spzwl.admin.factory.DaoFactory;
import com.spzwl.util.MD5Utils;

public class UserBusinessServiceImpl implements UserBusinessService {
//	使用工厂模式将 dao 与service 解耦.
	private UserDao userDao = DaoFactory.getInstance().getImpl(UserDao.class);
	// 用户注册功能
	/* (non-Javadoc)
	 * @see com.yydtk.service.BusinessService#reg(com.yydtk.domain.User)
	 */
	
	@Override
	public boolean reg(User user) throws UserIsExistException {
//		再注册前需要先查询下用户是否存在
		boolean b = userDao.isExist(user.getUsername());
		if(b){
//		a.如果存在则抛出一个异常提醒调用者用户存在
			throw new UserIsExistException("注册用户已经存在!");
		}
//		b.用户不存在,注册成功
//			1. 为密码进行md5加密
//			2. 产生唯一id;(这个需要在web层添加)
		user.setPassword(MD5Utils.getMD5(user.getPassword()));
		userDao.add(user);
		return true;
	}

	// 用户登录功能
	/* (non-Javadoc)
	 * @see com.yydtk.service.impl.BusinessService#login(java.lang.String, java.lang.String)
	 */

	@Override
	public User login(String username,String password) {
		User user = userDao.find(username, MD5Utils.getMD5(password));
		
		if(user==null){
			return null;
		}
		return user;
	}

	@Override
	public User findUser(String username) {
		return userDao.findUser(username);
		
	}

}
