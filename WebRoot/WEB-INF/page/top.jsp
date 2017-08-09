<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@page isELIgnored="false" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>管理界面</title>
  </head>

<body>
 <c:if test="${!empty(user)}">
		  		欢迎您,管理员${user.nickname} 光临本站!
		  		<a href="#" onclick = "top.location.replace('${pageContext.request.contextPath}/servlet/LogoutServlet')">退出</a>
 </c:if>
</body>
</html>
