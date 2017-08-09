<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@page isELIgnored="false" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 
    <title>上传界面</title>
	
  </head>
  
  <body>
		  <c:if test="${!empty(user)}">
		  		欢迎您,${user.nickname} 光临本站!<a href="${pageContext.request.contextPath}/servlet/LogoutServlet">注销</a>
		  </c:if>
		    <div id="login_box">
		  <c:if test="${empty(user)}">
		    	<a href="${pageContext.request.contextPath}/servlet/LoginServlet"> 登录</a>
		   <%--  	<a href="${pageContext.request.contextPath}/servlet/RegisterServlet">注册</a> --%>
		  </c:if>
		    </div>
		<%--     <form action="${pageContext.request.contextPath}/servlet/OrderServlet" method="post">
			   姓名: <input type="text" name="username" id="username" value="${form.username}">${errors.username} <br><br>
			   送货地址: <input type="text" name="address" id="address" value="${form.address}">${errors.address}<br><br>
			   联系电话: <input type="text" name="tell" id="tell" value="${form.tell}">${errors.tell}<br><br>
			   购买数量:<input type="text" name="count" id="count"value="${form.count}">${errors.count}<br><br>
			   <input type="submit" value="购买">
		    </form> --%>
  </body>
</html>
