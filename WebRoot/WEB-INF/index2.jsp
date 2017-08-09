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
		    
		  <c:if test="${empty(user)}">
		    	<a href="${pageContext.request.contextPath}/servlet/LoginServlet"> 登录</a>
		    	<a href="${pageContext.request.contextPath}/servlet/RegisterServlet">注册</a>
		  </c:if>
		 
		   
  </body>
</html>
