//增加角色
function addRole(){
	var roleName = $("#roleName").val();
	if(roleName===""){
		alert("请填写角色名称！");
	}else{
		$.ajax({
		  type: "GET",
		  url: "ajaxAddRole?roleName="+encodeURI(encodeURI(roleName)),
		  dataType: "text",
		  complete:callBack
		});
	}
}
//增加回调
function callBack(XMLHttpRequest, textStatus){
	var v = XMLHttpRequest.responseText;
	if(v=="2"){
		alert("添加成功！");
		window.location.reload();
	}else if(v=="1"){
		alert("添加失败，此角色名已经存在！");
	}else{
		alert("添加失败,未知错误-"+v);	
	}
}
//删除角色
function delRole(roleId){
	if(window.confirm("确定删除？")){
		$.ajax({
		  type: "GET",
		  url: "ajaxDelRole?roleId="+roleId,
		  dataType: "text",
		  complete:function(XMLHttpRequest, textStatus){
				var v = XMLHttpRequest.responseText;
				if(v=="1"){
					alert("删除成功！");
					window.location.reload();
				}else{
					alert("删除失败！");
					window.location.reload();
				}	  	
		  }
		});
	}
		
}
//显示修改内容
function showRoleName(index){
	document.getElementById("newRN"+index).style.display = "block";
	document.getElementById("rn"+index).style.display = "none";
	document.getElementById("UpdateRN"+index).style.display = "block";
	document.getElementById("showUpdateRN"+index).style.display = "none";
}
//修改角色名称
function updateRoleName(index,roleId){
	var roleName = document.getElementById("roleName"+index).value;
	if(roleName===""){
		alert("请填写角色名称！");
	}else{
		$.ajax({
		  type: "GET",
		  url: "ajaxupdateRole?roleId="+roleId+"&roleName="+encodeURI(roleName),
		  dataType: "text",
		  complete:function(XMLHttpRequest, textStatus){
				var v = XMLHttpRequest.responseText;
				if(v=="2"){
					alert("更新成功！");
					window.location.reload();
				}else if(v=="1"){
					alert("更新失败,此角色名已经存在！");
				}else{
					alert("更新失败,未知错误！"+v);
				}	  	
		  }
		});
	}
}















