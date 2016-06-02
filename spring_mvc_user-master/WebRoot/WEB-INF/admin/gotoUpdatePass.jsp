<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="../c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="../fmt.tld" %>
<%@ taglib prefix="fn" uri="../fn.tld" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>    
    <title>缴费业务</title>
    
	<link href="../resources/css/index.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		//判断密码
		function checkPwd(){
			var form = document.getElementById("form1");
			var newpwd = document.getElementById("newpwd").value;
			var renewpwd = document.getElementById("renewpwd").value;
			for (var i=0; i<form.elements.length; i++) {
				var element = form.elements[i].value;
				if(form.elements[i].type == "password" && element == ""){
					alert(form.elements[i].title);
					form.elements[i].focus();
					return false;
				}
			}
			if(newpwd != renewpwd){
				alert("抱歉！两次密码不一致！");
				return false;
			}
			/*
			if(newpwd.length < 6 || newpwd.length > 20){
				alert("抱歉！密码的长度不能小于6位，最大不能大于20位！");
				return false;
			}
			if(!isNaN(newpwd) || isLetter(newpwd)){
				alert("密码过于简单，建议用字母加数字的组合!");
				return false;
			}
			*/
			return true;
		}
		//判断是否全为字母
		function isLetter(str){
			if(""==str){
				return false;
			}
			for(var i=0;i<str.length;i++){
				var ss = str.charAt(i);
				if((ss<"a"||ss>"z")&&(ss<"A"||ss>"Z")){
					return false;
				}
			}
			return true;
		} 
	</script>
  </head>
  
  <body>
   <div class="mainc2">
  <form id="form1" name="form1" method="post" action="../admin/goinUpdatePwd" onsubmit="return checkPwd();">
    <div class="passdiv">
      <table width="40%" border="0" cellspacing="0" cellpadding="0" class="passtab">
        <tr>
          <td align="right">原密码：</td>
          <td><input type="password" name="oldpwd" id="oldpwd" title="请输入原密码！"  maxlength="20"/></td>
        </tr>
        <tr>
          <td align="right">新密码：</td>
          <td><input type="password" name="newpwd" id="newpwd" title="请输入新密码！"  maxlength="20"/></td>
        </tr>
        <tr>
          <td align="right">确认密码：</td>
          <td><input type="password" name="renewpwd" id="renewpwd" title="请输入确认密码！"  maxlength="20"/></td>
        </tr>
      </table>
    </div>
    <div class="butt">
    	<!--注意：由于系统正在升级，请到<a href="http://www.51663.net" target="_blank">老系统</a>修改密码！-->
       <input class="buti" type="submit" name="button" id="button" value="" /> 
      <input class="butset" type="reset" name="button2" id="button2" value="" />
    </div>
  </form>
</div>
  </body>
</html>
