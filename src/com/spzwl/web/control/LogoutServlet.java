package com.spzwl.web.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		注销登录
		HttpSession session = request.getSession();
		if(session!=null){
			session.removeAttribute("user");
		}
		//找到需要注销的Cookie
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		for(int i=0 ;cookies!=null&&i<cookies.length;i++){
			String name =  cookies[i].getName();
			if(name.equals("autologin")){
				cookie = cookies[i];
			}
		}
		//将过期时间设置为0,则立即过期
/*		除Cookie时，只设置maxAge=0将不能够从浏览器中删除cookie, 
      * 因为一个Cookie应当属于一个path与domain，所以删除时，Cookie的这两个属性也必须设置。 */
		if(cookie!=null){
			System.out.println("0000000000000000");
			cookie.setMaxAge(0);
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
		}
		
		request.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(request, response);
		//response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
