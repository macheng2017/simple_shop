<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <style type="text/css">
  	#T7-list{
  		width:300px;
  		height:120px;
  		border: 1px solid red ;
  	
  	}
  </style>
    <title>上传页面</title>
   <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
 <!--   <script type="text/javascript" src="../js/jquery-2.1.4.min.js"></script> -->
  <script type="text/javascript" src="../js/jquery.MultiFile.js"></script>
    <script type="text/javascript">
		    $(function() { // wait for document to load 
		  $('#T7').MultiFile({
		    list: '#T7-list'
		  });
		});
    </script>
  </head>
  
  <body style="text-align: center;">
  <a href="${pageContext.request.contextPath}/servlet/ListMyfileServlet">我的文件</a>
  <a href="${pageContext.request.contextPath}/index.jsp">回到首页</a>
    <form action="${pageContext.request.contextPath }/servlet/UploadServlet" method="post" enctype="multipart/form-data">
    <table width="50%" frame="border">
  <!--   	<tr>
    		<td>上传用户</td>
    		<td>
    			<input type="file" name="file">
    		</td>
    	</tr> -->
    	
    	<tr>
    		<td>上传文件</td>
    		<td>
    			多文件上传<input  type="file" id="T7" maxlength="5" />
    			<div id="T7-list">
    			</div>
    		</td>
    	</tr>
    	
    	<!-- <tr>
    		<td>文件描述</td>
    		<td>
    			<textarea rows="6" cols="50" name="description"></textarea>
    		</td>
    	</tr> -->
    	
    	<tr>
    		<td></td>
    		<td>
    			<input type="submit" value="上传">
    		</td>
    	</tr>
    </table>
    </form>
  </body>
</html>
