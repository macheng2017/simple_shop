<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@page isELIgnored="false" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 
    <title>上传界面</title>
	
  </head>
<frameset rows="5%,*" style="border:1px solid #ccc"  >
			<frame  src="${pageContext.request.contextPath}/servlet/MainServlet?method=top_show" >
			<frameset cols="15%,*" frameborder="no"  >
			<frame src="${pageContext.request.contextPath}/servlet/MainServlet?method=left_show" >
			<frame src="${pageContext.request.contextPath}/servlet/OrderServlet?method=query_order" name="showframe">
			</frameset>

</frameset>
</html>
