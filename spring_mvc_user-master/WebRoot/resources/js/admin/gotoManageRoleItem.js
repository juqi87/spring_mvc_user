
//显示某个角色的模块信息
function selectRoleItem(vse){
	$.ajax({
		  type: "GET",
		  url: "ajaxGetItemByRoleId?roleId="+vse.value,
		  dataType: "text",
		  complete:function(XMLHttpRequest, textStatus){
				$("#items").html(XMLHttpRequest.responseText); 	  	
		  }
		});
}

//更改角色模块信息
function updateRoleItem(){
	var cbItem = document.getElementsByName("cbItem");
	var selectRole = document.getElementById("selectRole").value;
	var vRoles = "";
	for(var v=0;v<cbItem.length;v++){
		if(cbItem[v].checked==true){
			vRoles += cbItem[v].value+","; 
		}
	}
	$.ajax({
	  type: "GET",
	  url: "ajaxUpdateRoleItem?roleId="+selectRole+"&roles="+vRoles,
	  dataType: "text",
	  complete:function(XMLHttpRequest, textStatus){
			if(XMLHttpRequest.responseText=="1"){//成功
				alert("角色更新成功！");
				window.location.reload();
			}else{
				alert("更新失败，--未知错误");
				window.location.reload();
			}
	  }
	});
}



















