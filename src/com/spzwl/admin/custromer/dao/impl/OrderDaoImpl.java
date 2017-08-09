package com.spzwl.admin.custromer.dao.impl;


import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;

import com.spzwl.admin.custromer.dao.OrderDao;
import com.spzwl.admin.custromer.dao.excption.DaoException;
import com.spzwl.admin.custromer.domain.Order;
import com.spzwl.admin.custromer.domain.QueryResult;
import com.spzwl.admin.custromer.domain.User;
import com.spzwl.util.JdbcUtils;
import com.spzwl.web.control.OrderServlet;
import com.spzwl.web.formbean.QueryInfo;
/**
 * 订单实现类
 * @author mac
 *	orderid,productname,orderDetail, ordernum, price, address, name, qq, phone, paytype, guestremark, referer, device, page,createdate;
 */
public class OrderDaoImpl implements OrderDao {
//添加一条订单信息
	//记录日志
	Logger logger = Logger.getLogger(OrderDaoImpl.class.getName());
	@Override
	public void add(Order orders) {
		
		try{	
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
				String sql = "insert into customer_order (orderid,productname,orderDetail, ordernum, price, address, name, qq, phone, paytype, guestremark, referer, device, page,createdate)"
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				Object[] params ={
						orders.getOrderid(),
						orders.getProductname(),
						orders.getOrderDetail(),
						orders.getOrdernum(),
						orders.getPrice(),
						orders.getAddress(),
						orders.getName(),
						orders.getQq(),
						orders.getPhone(),
						orders.getPaytype(),
						orders.getGuestremark(),
						orders.getReferer(),
						orders.getDevice(),
						orders.getPage(),
						orders.getCreatedate() == null?new Date():new java.sql.Date(orders.getCreatedate().getTime())
						};
				int len = qr.update(sql, params);
			
				if(len<0){
					throw new DaoException("订单没有记录成功！");
				}
			} catch (SQLException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				throw new DaoException(e);
			} 
	}
	@Override
	public void find(String uuid) {
		// TODO 通过id找到订单
		
	}
	// 找到所有的表单
	@Override
	public List<Order> findAll() {
		try {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql= "select * from customer_order";
		List <Order> list = (List<Order>) qr.query(sql, new BeanListHandler(Order.class));
		return list;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(String orderid) {
		
		//1.先查询需要删除的订单是否存在
		try {
			QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
	
			String sql = "select count(*) from customer_order where orderid=?";
			long len =  (Long) qr.query(sql, new ScalarHandler(),new Object[]{orderid});	
			//如果订单不存在则抛出运行时异常
			if(len<0){
				throw new RuntimeException("订单不存在");
			}
			//System.out.println("+++++++++");
			sql="delete from customer_order where orderid=?";
			qr.update(sql,new Object[]{orderid});
			
		}catch (Exception e){
			e.printStackTrace();
			throw new DaoException(e);
		}
		
		
		
	/*	QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql= "select * from customer_order where orderid = ?";*/
		
		//List <Order> list = (List<Order>) qr.
		// TODO orderDaoImpl订单删除未写完
	}
	
	
/*	//修改用户
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
	}*/
	@Override
	public void update(String uuid) {
		// TODO 订单修改
		
	}
	//分页查看订单信息
	@Override
	public QueryResult pageQuery(QueryInfo queryInfo) {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql ="select * from customer_order  order by createdate desc limit ?,?";
		try {
			Object params[]={queryInfo.getStartindex(),queryInfo.getPagesize()};
			List<Order> list = (List<Order>) qr.query(sql, new BeanListHandler(Order.class), params);
			sql ="select count(*)from customer_order";
//			得到总记录数
			long len =(Long)qr.query(sql,new ScalarHandler());
			QueryResult <Order> queryResult = new QueryResult<Order>();
			queryResult.setList(list);
//			这样处理会丢失精度(注意)
			queryResult.setTotalrecord((int) len);
			return queryResult;
		} catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

}
