function checkUserData(){

		var vname = $("#userName").val();
		var vpw = $("#userPw").val();
		var vcode = $("#vcode").val();
		if(vname===""){
			alert("用户名不能为空！");
			document.getElementById("userName").focus();
			return false;
		}else if(vpw===""){
			alert("密码不能为空！");
			document.getElementById("userPw").focus();
			return false;
		}else if(vcode===""){
			alert("验证码不能为空！");
			document.getElementById("vcode").focus();
			return false;
		}else{
			return true;
		}	
	
	
}
function giveFouce(){
	document.getElementById("userName").focus();
}