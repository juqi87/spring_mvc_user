<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  	<script type="text/javascript" src="../resources/js/admin/updateAdminUser.js"></script>
  	<script type="text/javascript" src="../resources/jquery.js"></script>
  	<style type="text/css">
  		.maintable{
  			width: 50%;
  		}
  		.maintable td{
  			text-align: left;
  			padding-left: 20px;
  		}
  	</style>
  </head>
  
 <body>
  	<div style="text-align: center;">
		<div class="tsea">
		    <h2>用户修改</h2>
		</div>
			<table border="0" cellspacing="0" cellpadding="0" class="maintable"
				id="tab" name="tab">
				<tr>
					<td>
						用 户 名：&nbsp;
						<input type="hidden" name="userId" id="userId" value="${userObj.adminUserId }"/>
						<input type="text" name="userName" readonly="readonly" id="userName" value="${userObj.userName }" />
						<font color="red"> * </font>
					</td>
				</tr>
				<tr>
					<td>
						密&nbsp;&nbsp;&nbsp; 码：&nbsp;
						<input type="password" name="userPw" id="userPw" value="" />
						<font color="red"> * 注意：不填密码，密码将不被修改</font>
					</td>
				</tr>
				<tr>
					<td>
						角&nbsp;&nbsp;&nbsp; 色：&nbsp;${roleSelect }
					</td>
				</tr>
				<tr>
					<td>
						是否启用：&nbsp;
						<input type="radio" name="userPass" id="userPass" ${userObj.isPass== '1'?'checked':'' } value="1" />
						是
						<input type="radio" name="userPass" id="userPass2" ${userObj.isPass== '0'?'checked':'' } value="0" />
						否
					</td>
				</tr>
				<tr>
					<td style="text-align: center;">
						<input type="button" name="button" id="button" value="修 改"
							onclick="updateUser();" class="use_input" />
						&nbsp;
						<input type="button" name="button" id="button" value="返 回"
							onclick="updateBack();" class="use_input" />
					</td>
				</tr>
			</table>
		</div>
 </body>
</html>
