<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    
    <title>系统登录</title>
    
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	
	<link href="../resources/css/index.css" rel="stylesheet" type="text/css" />
  	
  </head>
  
 <body>
    查询系统权限管理。。。gotoAdminMain
    <div id="inTopMenu" style="display: none">${topMenu }</div>
    <div id="firstURL" style="display: none">${firstURL }</div>
    <script type="text/javascript">
    	window.parent.frames[0].document.getElementById("topMenu").innerHTML= document.getElementById("inTopMenu").innerHTML;
    	window.location = document.getElementById("firstURL").innerHTML;
    </script>
 </body>
</html>
