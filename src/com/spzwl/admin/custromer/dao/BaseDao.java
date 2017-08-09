package com.spzwl.admin.custromer.dao;

import java.util.List;

public interface BaseDao<T> {
	// crud 类
	
	//增加一条数据或者多条数据
	public void add(T T);
	
	//查找一条数据
	public void find(String uuid);
	
	//
	
	public List<T> findAll();
	
	
	// 删除一条数据
	public void delete(String uuid);
	
	
	//修改一条数据
	public void update(String uuid);


}
