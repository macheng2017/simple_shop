package com.spzwl.admin.custromer.dao;

import com.spzwl.admin.custromer.domain.Order;
import com.spzwl.admin.custromer.domain.QueryResult;
import com.spzwl.web.formbean.QueryInfo;

public interface OrderDao extends BaseDao<Order>  {
	
	//TODO  这里还可以写一些 OrderDao 自己特殊的方法

	//分页显示 所有文件
	public abstract QueryResult<Order> pageQuery(QueryInfo queryInfo);

}
