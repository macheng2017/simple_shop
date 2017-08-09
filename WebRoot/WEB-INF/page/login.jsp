<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	ul {list-style:none;}
	#reg_box{ width:650px; margin:150px auto;}
	span{display: inline-block; width:150px; text-align: right;}
	ul li {height:35px;line-height:35px;}
</style>
<title>登录</title>
</head>
<body>
		<div >
					<form action="${pageContext.request.contextPath}/servlet/LoginServlet" method="post">
		<div id="reg_box">
		<a href="${pageContext.request.contextPath}/index.html">回到首页</a>
		<%-- <a href="${pageContext.request.contextPath}/servlet/RegisterServlet">注册</a> --%>
		
			<ul>
				<li> <span> 用户名:</span> <input type="text" name="username" value="${form.username }" />${errors.username}</li>
				<li> <span> 密码:</span> <input type="password" name="password"value="${form.password }"/>${errors.password}</li>
				<li> <span> 验证码:</span> <input type="text" name="client_checkcode" size="5"value="${form.client_checkcode }" />
					 <img src="${pageContext.request.contextPath}/servlet/ImgCodeServlet" width="125px" height="25px" onclick="this.src=this.src+'?'+ new Date().toString()" />
				${errors.client_checkcode}
				</li>
				<li><span>自动登录:</span>  <input type="radio"  name ="autologintime" value=""  />不使用
				 	<input type="radio"  name ="autologintime" value="${20*60}" checked />20分钟以内
				  	<input type="radio"  name ="autologintime" value="${180*60}" />3个小时以内
				</li>
				<li> <span> </span> <input type="submit" value="提交" /></li>
			</ul>
		</div>
	</form>
		</div>
</body>
</html>