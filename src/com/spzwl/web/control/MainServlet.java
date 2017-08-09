package com.spzwl.web.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 1.该类继承了BeanServlet 类,在BeanServlet中重载了 service方法,doGet,doPost方法都会最终调用service方法,
 * 在子类中就没有必要再写 doGet,doPost方法了
 * 2. 在web.xml 配置中没有必要配置BeanServlet,只配置继承类MainServlet即可
 * @author mac
 *
 */
public class MainServlet extends BaseServlet {
			//TODO 添加错误日志输出
			//Logger logger = Logger.getLogger("");
	public void left_show(HttpServletRequest request ,HttpServletResponse response){
		
			try {
				request.getRequestDispatcher("/WEB-INF/page/left.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				
			} 
		
	}
	
	public void top_show(HttpServletRequest request ,HttpServletResponse response){
		
		try {
			request.getRequestDispatcher("/WEB-INF/page/top.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			
		} 
	
}
	
	

}
