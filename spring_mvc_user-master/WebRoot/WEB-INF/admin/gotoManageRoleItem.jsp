<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>权限管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="../resources/css/index.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="../resources/zTree/css/demo.css">
	<link rel="stylesheet" type="text/css" href="../resources/zTree/css/zTreeStyle/zTreeStyle.css">
	<script type="text/javascript" src="../resources/zTree/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="../resources/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="../resources/zTree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript">
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
	var zNodes =${items}
	function updateRight(){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var sNodes = treeObj.getCheckedNodes(true);
		var rightIds = "";
		if (sNodes.length > 0) {
			for(var i=0;i<sNodes.length;i++){
				rightIds += sNodes[i].id + ",";
			}
		}
		var roles = rightIds.substr(0, rightIds.length-1);
		roleId = $("#roleId").val();
		if(roleId==-1){
			alert("请选择角色！");
			return false;
		}
		$("#rightIds").val(roles);
		$("#rfrm").submit();
	}
	
	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});

	function changeRole(){
		$("#frm").submit();
	}

	</script>
  </head>
  
  <body>
  	<div class="mainc2">
	   	<div style="text-align: center;">
	   		<div style="text-align: center;">
				<div class="tsea">
				    <h2>权限管理</h2>
				</div>
	   		</div><br>
	   		<form action="../admin/gotoRightList" method="post" id="frm">
		   		请选择角色：<select id="roleId" name="roleId" onchange="changeRole()">
							<option value="-1">--请选择--</option>
					<c:forEach items="${roleList}" var="maps">
						<option value="${maps.roleId}" <c:if test="${maps.roleId == roleId}">selected="selected"</c:if>>${maps.roleName}</option>
					</c:forEach>
				</select>
			</form>
			<div align="center" style="margin-top: 10px">
				<form action="../admin/gotoUpdateRightList" method="post" id="rfrm">
					<input type="hidden" name="roleId" id="newRoleId" value="${roleId}">
					<input type="hidden" name="rightIds" id="rightIds">
					<input type="button" value="确定" onclick="updateRight();" class="use_input"/>
				</form>
			</div>
   		</div>
   		<br/>
   		<div class="content_wrap" style="margin-left: 40%; width: auto;">
			<div class="zTreeDemoBackground left">
				<ul id="treeDemo" class="ztree" style="width: auto; height: auto;"></ul>
			</div>
		</div>
	</div>
  </body>
</html>
