<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="../resources/css/index.css" rel="stylesheet" type="text/css" />
	<link href="../resources/css/left.css?v=1" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="../resources/jquery.js"></script>
	<script type="text/javascript" src="../resources/js/admin/left.js"></script>

<style type="text/css">
	
	</style>
  </head>
  <body>
	<div class="main content" style="margin-top: 0px;">
		<div class="left-sider">
			<div class="operate">
				<div class="mcdtu">
					<h2>&nbsp;</h2>
			    </div>
				<ul id="J_navlist">
					${leftMenu}
				</ul>
				<script type="text/javascript" language="javascript">
					navList(12);
				</script>
			</div>
		</div>
	</div>
  </body>
</html>