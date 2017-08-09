package com.spzwl.admin.custromer.dao.impl;



import java.util.List;

import org.junit.Test;

import com.spzwl.admin.custromer.dao.OrderDao;
import com.spzwl.admin.custromer.domain.Order;
import com.spzwl.util.WebUtils;

public class OrderDaoImplTest {

	@Test
	public void testFindAll() {
		
		OrderDao orderDao = new OrderDaoImpl();
		List<Order> orderlist =   orderDao.findAll();
		for(Order order : orderlist){
			
			System.out.println(order.getName());
			System.out.println(order.getAddress());
			System.out.println(order.getPhone());
			System.out.println(order.getCreatedate());			
		}
	}
	
	@Test
	public void testAdd (){
		OrderDao orderDao = new OrderDaoImpl();
		Order order = new Order();
		order.setAddress("深圳市福田区南园街道赤尾社区二坊58栋302室");
		order.setOrderid(WebUtils.generateUUID());
		order.setPhone("12344654");
		order.setName("张三");
		orderDao.add(order);
	}
	

}
