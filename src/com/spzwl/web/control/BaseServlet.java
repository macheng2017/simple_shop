package com.spzwl.web.control;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 实现多个请求转发servlet
 * @author mac
 *
 */
public class BaseServlet extends HttpServlet {
	

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取方法名
		String methodName = request.getParameter("method");

		if (null == methodName || methodName.isEmpty()) {

			throw new RuntimeException("没有传递method方法,给出你想调用的方法");
		}

		Class<? extends BaseServlet> clazz = this.getClass();
		Method method = null;
		try {
			// 获取method对象
			method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("没有找到" + methodName + "方法，请检查该方法是否存在" + e);
		}
		try {
			// 反射调用
			method.invoke(this, request, response);
		} catch (Exception e) {
			System.out.println("");
			throw new RuntimeException("你调用的方法" + methodName + "内部发生了异常" + e);
		}
	}
}
