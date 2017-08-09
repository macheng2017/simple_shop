package com.spzwl.web.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.spzwl.admin.custromer.domain.Order;
import com.spzwl.admin.custromer.domain.QueryResult;
import com.spzwl.admin.custromer.domain.User;
import com.spzwl.admin.custromer.service.OrderService;
import com.spzwl.admin.custromer.service.impl.OrderServiceImpl;
import com.spzwl.util.JsonReader;
import com.spzwl.util.WebUtils;
import com.spzwl.web.formbean.PageBean;
import com.spzwl.web.formbean.QueryInfo;

import net.sf.json.JSONObject;

public class OrderServlet extends BaseServlet {
	//记录日志
	Logger logger = Logger.getLogger(OrderServlet.class.getName());
	OrderService service = new OrderServiceImpl();
	//分页查询订单
	public void add_order(HttpServletRequest request,HttpServletResponse response) throws IOException{
		 response.setContentType("text/html;charset=utf-8");  
	        /** 设置响应头允许ajax跨域访问 **/  
	        response.setHeader("Access-Control-Allow-Origin", "*");  
	        /* 星号表示所有的异域请求都可以接受， */  
	        response.setHeader("Access-Control-Allow-Methods", "GET,POST");  
	        Writer out =null;
	        try {
	        out = response.getWriter();  
	        JSONObject json=JsonReader.receivePost(request);  
	        Order order = (Order) JSONObject.toBean(json, Order.class);
		       
		       JSONObject jsonObject = new JSONObject();
		    	   service.addOrder(order);
		    	   jsonObject.put("flag", "1");
		    	   jsonObject.put("msg", "1");
		    	   out.write(jsonObject.toString());
				} catch (Exception e) {
					out.write("false");
					logger.info(e.getMessage());
				/*	request.setAttribute("message", "出错了,请重试!");
					request.getRequestDispatcher("/message.jsp").forward(request, response);*/
				}finally{
					out.close();
				}
	}
	//删除单个订单
	public void delete_order(HttpServletRequest request,HttpServletResponse response){
		String orderid = request.getParameter("orderid");
		if(orderid==null || "".equals(orderid.trim())){
			//返回错误信息
			try {
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				return;
			} catch (Exception e) {
				
				e.printStackTrace();
			};
		}
		service.deleteOrderByID(orderid);
		try {
			// TODO 这里有个问题就是办法在跳转到当前OrderServlet处理了，要不然会出现死循环，报错。
			request.getRequestDispatcher("/servlet/ListMyfileServlet").forward(request, response);
			//request.getRequestDispatcher("/WEB-INF/page/main.jsp").forward(request, response);
			return;
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//分页订单列表
	public void query_order(HttpServletRequest request,HttpServletResponse response){
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

	/*public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(request, response);
	}
public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//记录日志
		Logger logger = Logger.getLogger(OrderServlet.class.getName());
//		1. 将登录页面提交表单封装到LoginForm对象中
//		2. 调用其方法校验数据
//			2.1 将验证码从session中取出封装进LoginForm中
//		3. 数据校验成功后提交到service层
//			3.1 将用户名密码md5加密后提交(不在这里,在service层)
//			a. 用户存在 回显到登录页面信息成功 或者密码错误信息
//			b. 用户不存在  提示用户注册
//		request.setCharacterEncoding("utf-8");
//		1. 将登录页面提交表单封装到LoginForm对象中
		Order orderForm = WebUtils.request2Form(request, Order.class);
////		2. 调用其方法校验数据
////			2.1 将验证码从session中取出封装进LoginForm中
//		HttpSession session = request.getSession();	
//		if(session!=null){
//			String code = (String) session.getAttribute("service_checkcode");
//			if (code!=null&&!code.trim().equals("")) {
//				form.setService_checkcode(code);
//			}
//		}
//		在表单中回显数据
		request.setAttribute("form", orderForm);
		boolean  b = orderForm.validate();
		if(!b){
			request.setAttribute("errors", orderForm.getErrors());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
//		3. 数据校验成功后提交到service层
		OrderService service = new OrderServiceImpl();
			 try {
				service.addOrder(orderForm);
			} catch (Exception e) {
				
				logger.info(e.getMessage());
				request.setAttribute("message", "出错了,请重试!");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
			}
			 request.setAttribute("message", "购买信息提交成功!");
			 request.getRequestDispatcher("/message.jsp").forward(request, response);
			//response.sendRedirect(this.getServletContext().getContextPath()+"/index.jsp");
	}
	*/
	

	 public void doGet(HttpServletRequest request, HttpServletResponse response)  
	            throws ServletException, IOException {  
	        doPost(request, response);  
	    }  
	  
	    public void doPost(HttpServletRequest request, HttpServletResponse response)  
	            throws ServletException, IOException {  
	        // response.setContentType("text/html");  
	        // 设置字符编码为UTF-8, 这样支持汉字显示  
	        // response.setCharacterEncoding("UTF-8");  
	  
	        response.setContentType("text/html;charset=utf-8");  
	  
	        /** 设置响应头允许ajax跨域访问 **/  
	        response.setHeader("Access-Control-Allow-Origin", "*");  
	        /* 星号表示所有的异域请求都可以接受， */  
	        response.setHeader("Access-Control-Allow-Methods", "GET,POST");  
	  
	        Writer out = response.getWriter();  
	  
	  
	        JSONObject json=JsonReader.receivePost(request);  
	        //System.out.println(json);  
	          
	        /* UserService userService=new UserService();  
	          
	      //将建json对象转换为java对象  
	        User loginUser = (User)JSONObject.toBean(json,User.class);  
	        User user = userService.LoginUser(loginUser);  
	        JSONObject jsonObject=new JSONObject() ;  
	        if(user!=null){  
	            //将java对象转换为json对象  
	            jsonObject.put("user", JSONObject.fromObject(user));  
	            jsonObject.put("message", "用户登录成功！");  
	        }else{  
	  
	            jsonObject.put("message", "用户登录失败！");  
	        }  */
	     /*   out.write(jsonObject.toString());  
	        out.flush();*/ 
	        
	       Order order = (Order) JSONObject.toBean(json, Order.class);
	       OrderService service = new OrderServiceImpl();
	       JSONObject jsonObject = new JSONObject();
	       
	       try {
	    	   service.addOrder(order);
	    	   jsonObject.put("flag", "1");
	    	   jsonObject.put("msg", "1");
	    	   out.write(jsonObject.toString());
			} catch (Exception e) {
				out.write("false");
				logger.info(e.getMessage());
			/*	request.setAttribute("message", "出错了,请重试!");
				request.getRequestDispatcher("/message.jsp").forward(request, response);*/
			}finally{
				out.close();
			}
		/*	 request.setAttribute("message", "购买信息提交成功!");
			 request.getRequestDispatcher("/message.jsp").forward(request, response);*/
	    } 
}
