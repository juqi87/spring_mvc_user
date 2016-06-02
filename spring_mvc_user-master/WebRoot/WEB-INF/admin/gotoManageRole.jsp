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
  	<script type="text/javascript" src="../resources/js/admin/gotoManageRole.js"></script>
  	<script type="text/javascript" src="../resources/jquery.js"></script>
  </head>
  
 <body>
    <div class="mainc2">
    	<div class="tsea">
          <div class="tseal"> 
            <span class="marg">添加角色：<input  name="roleName" type="text" class="bgein" id="roleName"/></span>
            <input type="button" name="button" id="button" value="添 加 角 色" onclick="addRole();" class="use_input"/>
          </div>
        </div>
        <table border="0" cellspacing="0" cellpadding="0" class="maintable" id="tab" name="tab">
          <tr>
            <th>序号</th>
            <th>角色名称</th>
            <th>添加时间</th>
            <th>操 作</th>
          </tr>
          
          <c:forEach items="${roles}" var="role" varStatus="vs">
	          <tr onmouseout="this.style.backgroundColor='white'" onmouseover="this.style.backgroundColor='#BBEFB8'" style="background-color: white;">
	            <td>${vs.index+1 }&nbsp;</td>
	            <td>
	            	<div id="rn${vs.index+1 }">${role.roleName }</div>
	            	<div id="newRN${vs.index+1 }" style="display: none">
	            		<input type="text" value="${role.roleName }" name="roleName${vs.index+1 }" id="roleName${vs.index+1 }"/>
	            	</div>
	            </td>
	            <td>${role.adderTime }&nbsp;</td>
	            <td>
	            	<div id="showUpdateRN${vs.index+1 }">
	            		<input type="button" name="button" onclick="showRoleName('${vs.index+1 }')" id="edit" value="修 改" class="use_input"/>
	            		<input type="button" name="button" onclick="delRole('${role.roleId }')" id="del"  value="删 除" class="use_input"/>
	            	</div>
	            	<div id="UpdateRN${vs.index+1 }" style="display: none">
	            		<input type="button" name="button" onclick="updateRoleName('${vs.index+1 }','${role.roleId }')" id="edit" value="提 交" class="use_input"/>
	            		<input type="button" name="button" onclick="delRole('${role.roleId }')" id="del"  value="删 除" class="use_input"/>
	            	</div>
	            	
	            </td>
	          </tr>
          </c:forEach>
        </table>
    </div>
 </body>
</html>
