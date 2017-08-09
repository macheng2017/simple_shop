package com.spzwl.web.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.spzwl.admin.custromer.dao.excption.UserIsExistException;
import com.spzwl.admin.custromer.domain.User;
import com.spzwl.admin.custromer.service.UserBusinessService;
import com.spzwl.admin.custromer.service.impl.UserBusinessServiceImpl;
import com.spzwl.util.WebUtils;
import com.spzwl.web.formbean.RegisterForm;

public class RegisterServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/page/register.jsp").forward(request, response);

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//	     1. 对提交的表单进行数据有效校验  
//			1.1涉及到中文字符需要转换字符格式
//			1.2调用RegisterForm 自带的校验方法 对表单中的数据进行校验
//			1.3从session中取出验证码,验证其有效性,并封装进RegisterForm中
//			1.4判断校验是否成功 都需要回显 填写过的数据到用户表单
//	     2. 数据校验不成功,返回注册页面,回显不成功原因给用户.     
//	     3. 数据校验成功,调用service层处理注册请求  
//			3.1 使用WebUtils生成用户id并加入到formBean中
//			3.2 在调用service之前需要将要处理的数据从RegisterForm(formBean) 封装到UserBean(javaBean)中
//	     4. service层处理不成功,原因是, 因为用户存在,回显到注册页面不成功原因       
//	     5. service层处理不成功,是其他原因(向数据库中存入失败),跳转的全局信息显示页面,为用户显示友好信息  
//	     6. service层处理成功,跳转全局信息显示页面,提示用户注册成功.  
//		涉及到中文字符需要转换字符格式
		request.setCharacterEncoding("utf-8");
//		调用工具类将 jsp注册表单封装到formBean中
		RegisterForm formBean =	 WebUtils.request2Form(request, RegisterForm.class);
//		调用RegisterForm 自带的校验方法 对表单中的数据进行校验
//		从session中取出验证码,验证其有效性,并封装进RegisterForm中
		HttpSession session = request.getSession();
		if(session!=null){
			String code = (String) session.getAttribute("service_checkcode");
			if(code!=null&&!code.trim().equals("")){
				formBean.setService_checkcode(code);
			}
		}
		boolean b = formBean.validate();

//		判断校验是否成功 都需要回显 填写过的数据到用户表单
		request.setAttribute("form", formBean);
//		1. 校验不通过,将错误信息封装到request域中,在register.jsp 中回显.
		if(!b){
			request.setAttribute("errors", formBean.getErrors());
			request.getRequestDispatcher("/WEB-INF/page/register.jsp").forward(request, response);
			return;
		}
//	     3. 数据校验成功,调用service层处理注册请求  
//		    3.1 使用WebUtils生成用户id并加入到formBean中
				formBean.setUserid(WebUtils.generateUUID());
//			3.2 在调用service之前需要将要处理的数据从RegisterForm(formBean) 封装到UserBean(javaBean)中
				User user = new User();
		WebUtils.copy2Bean(formBean, user);
		UserBusinessService service = new UserBusinessServiceImpl();
		try {
			service.reg(user);
		} catch (UserIsExistException e) {
//	     4. service层处理不成功,原因是, 因为用户存在,回显到注册页面不成功原因       
			formBean.getErrors().put("username", "用户已经存在!");
			request.setAttribute("errors", formBean.getErrors());
			request.getRequestDispatcher("/WEB-INF/page/register.jsp").forward(request, response);
			return;
		}catch(Exception e){
//	     5. service层处理不成功,是其他原因(向数据库中存入失败),跳转的全局信息显示页面,为用户显示友好信息  
			e.printStackTrace(); 
			request.setAttribute("message", "服务器有点累了,请稍候重试!");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
//	     6. service层处理成功,跳转全局信息显示页面,提示用户注册成功.  
		//request.setAttribute("message", "恭喜你,注册成功!!,1秒钟后自动跳转到首页!<meta http-equiv='Refresh' content='1;"+request.getContextPath()+"/index.jsp'>");
		request.setAttribute("message", "恭喜你,注册成功!!,1秒钟后自动跳转到首页!<meta http-equiv='Refresh' content='1;"+request.getContextPath()+"/servlet/LoginServlet'>");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	
	}

}
