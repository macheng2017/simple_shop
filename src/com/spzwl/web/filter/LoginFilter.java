package com.spzwl.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spzwl.admin.custromer.dao.excption.UserIsNotLoginException;
import com.spzwl.admin.custromer.domain.User;

/**
 * 判断是否登录
 * 
 * @author mac
 */
public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// 将servletrequest 都转换为HttpServletRequest
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		try {
			// 从session域获取用户登录信息
			User user = (User) request.getSession().getAttribute("user");
			if (user == null) {
				throw new UserIsNotLoginException("请先登录!");
			}
			chain.doFilter(request, response);
		} catch (UserIsNotLoginException e) {
			request.setAttribute("message", "您还没有登录!请先登录,再试!,2秒钟后自动跳转到首页!<meta http-equiv='Refresh' content='2;url="
					+ request.getContextPath() + "/index.html'>");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "服务器有点累,稍后再试!,3秒钟后自动跳转到首页!<meta http-equiv='Refresh' content='3;url="
					+ request.getContextPath() + "/index.html'>");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
}
