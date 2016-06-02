<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="../c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="../fmt.tld" %>
<%@ taglib prefix="fn" uri="../fn.tld" %>
<html>
  <head>    
    <title>缴费业务</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="../resources/css/index.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
                欢迎登陆，用户管理页面。。。
      
  <div id="inTopMenu" style="display: none">${adminMenu }</div>
  <div id="firstURL" style="display: none">${firstURL }</div>
    <script type="text/javascript">
    	window.parent.frames[0].document.getElementById("topMenu").innerHTML = document.getElementById("inTopMenu").innerHTML;
    	if(""!=document.getElementById("firstURL").innerHTML)
    		window.location = document.getElementById("firstURL").innerHTML;
    </script>
  </body>
</html>
