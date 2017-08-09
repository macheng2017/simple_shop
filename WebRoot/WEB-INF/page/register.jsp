<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册页面</title>
<style type="text/css">
	ul {list-style:none;}
	#reg_box{ width:60%; margin:150px auto;}
	span{display: inline-block; width:80px; text-align: right;}
	ul li {height:35px;}
	

</style>
<!-- <script type="text/javascript" src="../js/ShowCalendar.js"></script> -->
<!-- link calendar resources -->
	<link rel="stylesheet" type="text/css" href="../css/tcal.css" />
	<script type="text/javascript" src="../js/tcal.js"></script> 

</head>
<body>
	<a href="${pageContext.request.contextPath}/index.html">回到首页</a>
	<form action="${pageContext.request.contextPath}/servlet/RegisterServlet" method="post">
		<div id="reg_box">
			<ul>
			
				<li> <span> 用户名:</span> <input type="text" name="username" value="${form.username }" />${errors.username}</li>
				<li> <span> 密码:</span> <input type="password" name="password"value="${form.password }"/>${errors.password}</li>
				<li> <span> 确认密码:</span> <input type="password" name="password2"value="${form.password2 }"/>${errors.password2}</li>
				<li> <span> Email:</span> <input type="text" name="email" value="${form.email }"/>${errors.email}</li>
				<li> <span> 住址:</span> <input type="text" name="address"value="${form.address }" />${errors.address}</li>
				<li> <span> QQ:</span> <input type="text" name="qq"value="${form.qq }"  />${errors.qq}</li>
				<li> <span> 手机:</span> <input type="text" name="phoneid" value="${form.phoneid }" />${errors.phoneid}</li>
				<li> <span> 生日:</span> <input  id="birthday" class="tcal" type="text" name="birthday"onClick="showCalendar(this.id)" value="${form.birthday }" />${errors.birthday}</li>
				<li> <span> 真实姓名:</span> <input type="text" name="nickname" value="${form.nickname }" />${errors.nickname}</li>
				<li> <span> 验证码:</span> <input type="text" name="client_checkcode" size="5"value="${form.client_checkcode }" />
					<img src="${pageContext.request.contextPath}/servlet/ImgCodeServlet" width="125px" height="25px" onclick="this.src=this.src+'?'+ new Date().toString()" />
				${errors.client_checkcode}
				</li>
				<li> <span> </span> <input type="submit" value="提交" /></li>
			</ul>
		</div>
	</form>
</body>
</html>