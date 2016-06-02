<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@taglib uri="/WEB-INF/fn.tld" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>

		<title>系统登录</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link href="../resources/css/index.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../resources/jquery.js"></script>
		<script type="text/javascript" src="../resources/js/admin/top.js"></script>
	</head>

	<body>
		<div class="top">
			<h2 title="查询系统">
				&nbsp;
			</h2>
			<div class="tdail">
					<span>欢迎您：${adminUser.userName}</span>
			</div>
			<div class="thome">
				<a href="../admin/gotoTop"><span class="thm">主页</span> </a>
				<a href="../login/gotoOutLogin" ><span class="tex">退出</span> </a>
			</div>
		</div>
		<div class="mcdtu1">
		    <div class="mcdtu">
		      <h2>&nbsp;</h2>
		    </div>
   	 		<div id="topMenu" class="mainrtab2">
   	 			
   	 		</div>
		</div>
	</body>
</html>
