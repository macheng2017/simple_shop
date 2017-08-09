package com.spzwl.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * 设置所有请求页面编码格式过滤器
 * 
 * @author mac
 */
public class CharacterEncodingFilter implements Filter {
	private FilterConfig config;
	private final String defaultCharset = "utf-8";

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// 先判断下是否获取到xml配置参数,如果没有为保证程序正常运行使用默认的配置
		String charset = config.getInitParameter("charset");
		if (charset == null) {
			charset = defaultCharset;
		}
		// 将servletrequest 都转换为HttpServletRequest
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse reponse = (HttpServletResponse) res;
		request.setCharacterEncoding(charset);
		// 注意:这样只能解决get请求乱码问题,post无能为力,所以我们需要增强request对象的方法
		/**
		 * 增强一个对象的方法总共有三种方式: 1. 继承(太麻烦,需要知道request内容) 2. 包装设计模式 3. 动态代理(没学)
		 * 包装设计模式的步骤 1.创建一个类实现 被包装对象所实现的接口 2.在该类中使用一个变量记录被包装对象 3.创建一个构造方法为上面变量赋值
		 * 4.实现所有接口中的方法 5.增强需要增强的方法,其余方法调用被包装对象的方法
		 * 
		 */
		reponse.setCharacterEncoding(charset);
		reponse.setContentType("text/html;charset=" + charset);
		// 放行
		chain.doFilter(new MyRequest(request), reponse);
	}

	/**
	 * 增强一个对象的方法总共有三种方式: 1. 继承(太麻烦,需要知道request内容) 2. 包装设计模式 3. 动态代理(没学)
	 * 包装设计模式的步骤 1.创建一个类实现 被包装对象所实现的接口 2.在该类中使用一个变量记录被包装对象 3.创建一个构造方法为上面变量赋值
	 * 4.实现所有接口中的方法 5.增强需要增强的方法,其余方法调用被包装对象的方法
	 * 
	 */
	class MyRequest extends HttpServletRequestWrapper {
		private HttpServletRequest request;

		public MyRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}

		public String getParameter(String name) {
			// 1. 先调用原来的方法获取到需要的参数
			String value = this.request.getParameter(name);
			// 2.判断是否为空
			if (value == null) {
				return null;
			}
			// 3. 判断是否为get 如果不是则不用转
			if (!request.getMethod().equalsIgnoreCase("get")) {
				return value;
			}
			// 4.进行转换操作
			try {
				value = new String(value.getBytes("iso-8859-1"), request.getCharacterEncoding());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return filter(value);
		}

		// 为了防止用户提交js 或者其他可以执行的数据需要对特殊字符进行转义
		// 这个转义代码不用写在tomcat例子中有
		// D:_apache-tomcat-7.0.62_webapps_examples_WEB-INF_classes_util
		public String filter(String message) {
			if (message == null)
				return (null);
			char content[] = new char[message.length()];
			message.getChars(0, message.length(), content, 0);
			StringBuilder result = new StringBuilder(content.length + 50);
			for (int i = 0; i < content.length; i++) {
				switch (content[i]) {
				case '<':
					result.append("&lt;");
					break;
				case '>':
					result.append("&gt;");
					break;
				case '&':
					result.append("&amp;");
					break;
				case '"':
					result.append("&quot;");
					break;
				default:
					result.append(content[i]);
				}
			}
			return (result.toString());
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}

}
