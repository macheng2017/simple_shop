package com.spzwl.admin.custromer.service;

import java.util.List;

import com.spzwl.admin.custromer.domain.Order;
import com.spzwl.admin.custromer.domain.QueryResult;
import com.spzwl.web.formbean.QueryInfo;

/**
 * 订单服务
 * @author mac
 *
 */
public interface OrderService {
	//添加订单
	public void addOrder(Order order);
	//删除订单通过orderid
	public void deleteOrderByID(String orderid);
	//显示所有订单
	public List<Order> findALL();
	//分页显示多个订单
	public QueryResult<Order> pageQuery(QueryInfo queryInfo);
	//修改订单
	public void update(String orderid);

}
