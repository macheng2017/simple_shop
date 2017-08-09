package com.spzwl.admin.custromer.service.impl;

import java.util.List;

import com.spzwl.admin.custromer.dao.OrderDao;
import com.spzwl.admin.custromer.domain.Order;
import com.spzwl.admin.custromer.domain.QueryResult;
import com.spzwl.admin.custromer.service.OrderService;
import com.spzwl.admin.factory.DaoFactory;
import com.spzwl.util.WebUtils;
import com.spzwl.web.formbean.QueryInfo;

public class OrderServiceImpl implements OrderService {
	
	// 低耦合
	private OrderDao orderDao = DaoFactory.getInstance().getImpl(OrderDao.class);
	@Override
	public void addOrder(Order order) {
		 //添加uuid 
		order.setOrderid(WebUtils.generateUUID());
		orderDao.add(order);

	}

	@Override
	public void deleteOrderByID(String orderid) {
		// TODO service订单删除功能
		orderDao.delete(orderid);
		
	}

	@Override
	public List<Order> findALL() {
		return orderDao.findAll();
	}

	@Override
	public QueryResult<Order> pageQuery(QueryInfo queryInfo) {
		return orderDao.pageQuery(queryInfo);
	}

	@Override
	public void update(String orderid) {
		// TODO service订单更新功能

	}

}
