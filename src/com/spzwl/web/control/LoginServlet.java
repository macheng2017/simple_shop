package com.spzwl.web.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.spzwl.admin.custromer.domain.User;
import com.spzwl.admin.custromer.service.UserBusinessService;
import com.spzwl.admin.custromer.service.impl.UserBusinessServiceImpl;
import com.spzwl.util.MD5Utils;
import com.spzwl.util.WebUtils;
import com.spzwl.web.formbean.LoginForm;

public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		1. 将登录页面提交表单封装到LoginForm对象中
//		2. 调用其方法校验数据
//			2.1 将验证码从session中取出封装进LoginForm中
//		3. 数据校验成功后提交到service层
//			3.1 将用户名密码md5加密后提交(不在这里,在service层)
//			a. 用户存在 回显到登录页面信息成功 或者密码错误信息
//			b. 用户不存在  提示用户注册
//		request.setCharacterEncoding("utf-8");
//		1. 将登录页面提交表单封装到LoginForm对象中
		LoginForm form = WebUtils.request2Form(request, LoginForm.class);
//		2. 调用其方法校验数据
//			2.1 将验证码从session中取出封装进LoginForm中
		HttpSession session = request.getSession();	
		if(session!=null){
			String code = (String) session.getAttribute("service_checkcode");
			if (code!=null&&!code.trim().equals("")) {
				form.setService_checkcode(code);
			}
		}
//		在表单中回显数据
		request.setAttribute("form", form);
		boolean  b = form.validate();
		if(!b){
			request.setAttribute("errors", form.getErrors());
			request.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(request, response);
			return;
		}
//		3. 数据校验成功后提交到service层
		UserBusinessService service = new UserBusinessServiceImpl();
			User user = service.login(form.getUsername(), form.getPassword());
//			3.1 将用户名密码md5加密后提交(service层)
//			a. 用户不存在  提示用户注册
			if(user==null){
				form.getErrors().put("username", "用户名或密码错误!");
				request.setAttribute("errors", form.getErrors());
				request.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(request, response);
				return;
			}
//			实现自动登录功能 cookie + filter
			/**
			 * 实现自动登录功能需要获取cookie name=autologin username:expiretime:md5(username:expiretime:password)
			 * 1. 修改登陆页面 添加自动登录保存时间
			 * 2. 修改formBean 添加字段 autologintime
			 * 3. 添加makeCookie()
			 * 4. 返回Cookie
			 */
				int expiretime = 0;
				//当用户选择不使用的时候 过期时间为0,也就是Cookie不缓存
				if(form.getAutologintime()!=null){
					
					if (!"".equals(form.getAutologintime().trim())||expiretime>0) {
						expiretime=Integer.parseInt(form.getAutologintime());
						Cookie coo = makeCookie(user, expiretime, request);
						response.addCookie(coo);
					}
				}
//			b. 用户存在 回显到登录页面信息成功
			request.getSession().setAttribute("user", user);
			/*request.setAttribute("message", "登录成功,1秒钟后自动跳转到首页!<meta http-equiv='Refresh' content='1;"+request.getContextPath()+"/index.jsp'>");
			request.getRequestDispatcher("/message.jsp").forward(request, response);*/
			//response.sendRedirect(this.getServletContext().getContextPath()+"/main.jsp");
			request.getRequestDispatcher("/WEB-INF/page/main.jsp").forward(request, response);
	}
	//生成一个向客户端写入的Cookie
	public Cookie makeCookie(User user,int expiretime,HttpServletRequest request){
		String username = user.getUsername();
		String password = user.getPassword();
		long time = System.currentTimeMillis()+expiretime*1000; //注意这里是毫秒,当前时间 毫秒值+ 保存时间毫秒值  
		String str =  username+":"+ time +":"+ password;
		String md5str =  username+":"+ time +":"+ MD5Utils.getMD5(str);
		Cookie coo = new Cookie("autologin", md5str);
		coo.setMaxAge(expiretime);//注意这里是秒
		coo.setPath(request.getContextPath());
		return coo;
	}
//使用 md5utils 工具类中的 编码方式实现
	/*private String MD5Encode(String username, long time, String password) {
		String str =  username+":"+ time +":"+ password;
				try {
					MessageDigest md = MessageDigest.getInstance("md5");
					byte[] byt = md.digest(str.getBytes());
					//得到md5摘要后还要经过base64编码
//					BASE64Encoder encode = new BASE64Encoder();
//					return encode.encode(byt);
					Encoder md5 =  Base64.getEncoder();
					return md5.encodeToString(byt);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
	}*/
	

}
