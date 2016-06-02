
//添加用户
function addUser(){
	var userName = $("#userName").val();
	var userPw = $("#userPw").val();
	var userPass = $("input[name='userPass']:checked").val();
	var selectRole = $("#selectRole").val();
	var areaId = $("#areaId").val();
	if(selectRole=="0" ){
		alert("请选择角色!");
		return false;
	}
	var userTrueName = $("#userTrueName").val();
	var BindMobilePhone = $("#BindMobilePhone").val();
	if(userName==="" || userPw==="" || userPass==="" || userTrueName==="" || BindMobilePhone===""){
		alert("用户信息请填写完整！");
	}else{
		$.ajax({
			  type: "GET",
			  url: "ajaxAddUser?userName="+userName+"&userPw="+userPw+"&userPass="+userPass+"&roleId="+selectRole,
			  dataType: "text",
			  complete:function(XMLHttpRequest, textStatus){
					if(XMLHttpRequest.responseText=="1"){
						alert("添加成功！");
						window.location.reload();
					}else if(XMLHttpRequest.responseText=="2"){
						alert("用户名已经存在，添加失败！");
						window.location.reload();
					}else{
						alert("添加失败！");
						window.location.reload();
					}
			  }
			});
		}
}
//查询用户信息
function searchUser(){
	var searchUserName = $("#searchUserName").val();
	window.location.href="gotoManageAdminUser?userName="+searchUserName;
}

//跳转到修改用户页面
function gotoUpdateUser(userId){
	window.location.href="gotoUpdateAdminUser?userId="+userId;
}

//删除用户
function gotoDeleteUser(userId){
	var b = confirm("确定要删除吗?");
	if(b==true){
		$.post("deleteUser",{userId:userId},function(data){
			if(data==2){
				alert("不能删除自己！");
			}else if(data==1){
				alert("删除成功！");
				window.location.reload();
			}else{
				alert("删除失败，请重试！");
			}
		});
	}
}









