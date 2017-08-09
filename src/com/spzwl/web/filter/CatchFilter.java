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

public class CatchFilter implements Filter {
	private FilterConfig config;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		long expire = 0;
		String uri = request.getRequestURI();
		if (uri.endsWith(".jpg")) {
			expire = Long.parseLong(config.getInitParameter("jpg"));
		} else if (uri.endsWith(".css")) {
			expire = Long.parseLong(config.getInitParameter("css"));

		} else if (uri.endsWith(".js")) {
			expire = Long.parseLong(config.getInitParameter("js"));
		}
		// 设置缓存时间的单位为小时
		expire = expire * 1000 * 60 * 60;
		// 特别注意,缓存的时间计算是从 1970年的毫秒值开始加起
		response.setDateHeader("Expires", System.currentTimeMillis() + expire);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}
}
