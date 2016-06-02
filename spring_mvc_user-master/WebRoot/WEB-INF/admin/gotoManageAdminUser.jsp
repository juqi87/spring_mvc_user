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
  	<script type="text/javascript" src="../resources/js/admin/gotoManageAdminUser.js"></script>
  	<script type="text/javascript" src="../resources/jquery.js"></script>
  </head>
  
 <body>
    <div class="mainc2">
    	<div class="tsea">
          <div class="tsea2" style="padding-left: 10px;"> 
            <span class="">用户名：<input  name="userName" type="text" class="bgein" id="userName"/><font color="red">*</font></span>
            <span class="">密码：<input  name="userPw" type="password" class="bgein" id="userPw"/><font color="red">*</font></span>
            <span class="">是否启用：
         							<input type="radio" name="userPass" value="1" checked="checked" />是
         						   <input type="radio" name="userPass" value="0" />否
            </span>&nbsp;&nbsp;&nbsp;
            <span class="">所属角色：${roleSelect }</span>
            
            <input type="button" name="button" id="button" value="添 加 用 户" onclick="addUser();" class="use_input"/>
          </div>
        </div>
        &nbsp;&nbsp;&nbsp;&nbsp;用户名：<input type="text" name="searchUserName" id="searchUserName"/>
        <input type="button" value="" id="button" name="button" class="margpt" onclick="searchUser();" />
        
        <table border="0" cellspacing="0" cellpadding="0" class="maintable" id="tab" name="tab">
          <tr>
            <th>用户名称</th>
            <th>角色</th>
            <th>状态</th>
            <th>添加时间</th>
            <th>操 作</th>
          </tr>
          <c:forEach var="user" items="${listUser}">
	          <tr onmouseout="this.style.backgroundColor='white'" onmouseover="this.style.backgroundColor='#BBEFB8'" style="background-color: white;">
	            <td>${user.userName }&nbsp;</td>
	            <td>${user.roledefinition.roleName }&nbsp;</td>
	            <td>${user.isPass=='1'?'启用':'锁定' }&nbsp;</td>
	            <td>${user.adderTime }&nbsp;</td>
	            <td><a href="javascript:void(0)" onclick="gotoUpdateUser('${user.adminUserId }');">修改</a>&nbsp;|&nbsp;<a href="javascript:void(0)" onclick="gotoDeleteUser('${user.adminUserId }');">删除</a></td>
		      </tr>
	      </c:forEach>
        </table>
        
        ${page }
    </div>
 </body>
</html>
