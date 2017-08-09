package com.spzwl.web.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spzwl.admin.custromer.domain.QueryResult;
import com.spzwl.admin.custromer.domain.User;
import com.spzwl.admin.custromer.service.OrderService;
import com.spzwl.admin.custromer.service.impl.OrderServiceImpl;
import com.spzwl.util.WebUtils;
import com.spzwl.web.formbean.PageBean;
import com.spzwl.web.formbean.QueryInfo;

public class ListMyfileServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//从session域获取用户登录信息
			User user = (User) request.getSession().getAttribute("user");
			/*if(user==null){
				throw new UserIsNotLoginException("请先登录!");
			}*/
			//1. 对页面传递过来的数据进行封装
		QueryInfo queryInfo = WebUtils.request2Form(request, QueryInfo.class);	
		OrderService service = new OrderServiceImpl();
		QueryResult queryResult = service.pageQuery(queryInfo);
		PageBean pageBean = new PageBean();
		pageBean.setCurrentpage(queryInfo.getCurrentpage());
		pageBean.setPagesize(queryInfo.getPagesize());
		pageBean.setList(queryResult.getList());
		pageBean.setTotalrecord(queryResult.getTotalrecord());
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("/WEB-INF/page/listmyupfile.jsp").forward(request, response);
		}/*catch(UserIsNotLoginException e){
			request.setAttribute("message", "请先登录,再试!");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}*/catch(Exception e){
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
