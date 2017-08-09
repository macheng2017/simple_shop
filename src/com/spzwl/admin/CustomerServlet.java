package com.spzwl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerServlet extends HttpServlet {

	@Override
	public  void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 防止出现乱码
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("content-type", "text/html;charset=utf-8");
		//从表单中取出数据
		//姓名 ，地址， 电话，数量，唯一id
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		String tell = req.getParameter("tell");
		String count = req.getParameter("count");
		String uuid = null;
		
		
		
		

	}
	
	@Override
	public  void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
		
		
	}
	
	
}
