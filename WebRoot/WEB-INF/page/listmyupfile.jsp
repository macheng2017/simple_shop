<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示上传文件</title>
<style type="text/css">
	table,td{ border:1px solid #ccc; line-height:30px;  }

</style>
<script type="text/javascript">
	//删除确认框
	function confirmAct(){
		if(confirm("你确定要删除吗？")){
			return true;
		}
		return false;
	}
</script>
</head>
<body style="text-align: center;">

	<%-- 		<c:if test="${pageBean.list!=null}">
				尊敬的 ${user.nickname} 用户,您还没有上传数据!你可以点击<a href="${pageContext.request.contextPath}/index.jsp">回到首页上传文件</a>
			</c:if>
			
			<c:if test="${pageBean.list==null}">
				
			</c:if>
--%>
			
		<table width="60%" cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td>序号</td>
				<td>产品样式</td>
				<td>价格</td>
				<td>姓名</td>
				<td>电话</td>
				<td>地址</td>
				<td>数量</td>
				<td>日期</td>
				<td>操作</td>
			</tr>
	<c:forEach var="b" items="${pageBean.list}" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td> ${b.orderDetail}</td>
				<td> ${b.price }</td>
				<td> ${b.name }</td>
				<td>${b.phone }</td>
				<td>${b.address} </td>
				<td>${b.ordernum}</td>
				<td>${b.createdate}</td>
				<td>
					<%-- <a href="${pageContext.request.contextPath}/servlet/DownLoadServlet?id=${b.orderid}">下载</a> --%>
					<a href="${pageContext.request.contextPath}/">修改</a>
					<a href="${pageContext.request.contextPath}/servlet/OrderServlet?method=delete_order&amp;orderid=${b.orderid}" onclick="return confirmAct()">删除</a>
				</td>																	     
			</tr>
	</c:forEach>
		</table> 
		<br>
		<br>
			总共${pageBean.totalrecord}记录,每页显示${pageBean.pagesize } 条,当前第${pageBean.currentpage}  页,总共${pageBean.totalpage} 页
<a href="${pageContext.request.contextPath}/servlet/ListMyfileServlet?currentpage=${pageBean.previouspage} ">上一页</a>
			<c:forEach var="b" items="${pageBean.pagebar}" varStatus="status">
					<a href="${pageContext.request.contextPath}/servlet/ListMyfileServlet?currentpage=${b}">${b}</a>
			</c:forEach>
			<a href="${pageContext.request.contextPath}/servlet/ListMyfileServlet?currentpage=${pageBean.nextpage} ">下一页</a>

</body>
</html>