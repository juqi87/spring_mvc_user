//返回
function updateBack(){
	window.history.go(-1);
}


//修改用户信息
function updateUser(){
	
	var userName = $("#userName").val();
	var userPw = $("#userPw").val();
	var userPass = $("input[name='userPass']:checked").val();
	var roleId = $("#roleId").val();
	var userId = $("#userId").val();
	
	if(userName==="" ){
		alert("请输入用户名！");
	}else{
		$.ajax({
			  type: "GET",
			  url: "ajaxUpdateUser?userId="+userId+"&userName="+userName+"&userPw="+userPw+"&userPass="+userPass+"&roleId="+roleId,
			  dataType: "text",
			  complete:function(XMLHttpRequest, textStatus){
					if(XMLHttpRequest.responseText=="1"){
						alert("修改成功！");
						window.location.href='../admin/gotoManageAdminUser';
					}else{
						alert("修改失败！");
						window.location.reload();
					}
			  }
			});
		}
}



