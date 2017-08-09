<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@page isELIgnored="false" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 
    <title>上传界面</title>
  <link rel="stylesheet" href="../css/demo.css" type="text/css">
  <link rel="stylesheet" href="../css/zTreeStyle.css" type="text/css">
  <script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
  <script type="text/javascript" src="../js/jquery.ztree.core.js"></script>
	<SCRIPT type="text/javascript">
		
		var setting = {
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zNodes =[
			{ id:1, pId:0, name:"功能列表", open:true},
			{ id:11, pId:1, name:"订单管理", url:"${pageContext.request.contextPath}/servlet/OrderServlet?method=query_order", target:"showframe"},
			{ id:12, pId:1, name:"用户管理", url:"http://www.treejs.cn/", target:"showframe"}
	
		];

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		
	</SCRIPT>
  </head>
  
  <body>
  	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
		
		 
		   
  </body>
</html>
