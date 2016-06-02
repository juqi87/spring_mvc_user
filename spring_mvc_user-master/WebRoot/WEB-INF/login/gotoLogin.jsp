
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    
    <title>查询系统登录</title>
    
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	
	<link rel="stylesheet" href="../resources/css/reg.css" type="text/css"></link>
	<script type="text/javascript" src="../resources/jquery.js"></script>
	<script type="text/javascript" src="../resources/js/admin/gotoLogin.js"></script>
	<script type="text/javascript">
		function checkSize(obj){
			if(obj.value.length==11){
				document.getElementById("userPw").focus();
			}
		}
		//加入收藏
		function addfavorite(){
			var aUrls=document.URL.split("/"); 
			var vDomainName="http://"+aUrls[2]+"/"; 
			var description=document.title; 
			try{//IE 
				window.external.AddFavorite(vDomainName,description); 
			}catch(e){//FF 
				window.sidebar.addPanel(description,vDomainName,""); 
			} 
		 }
	</script>
  </head>
  <body onload="giveFouce();">
	 <div class="login">
	  <div class="logink">
	  
	    <div class="loginkr">
	    <form id="form1" name="form1" method="post" action="goinLogin" onsubmit="return checkUserData();">
	        <div class="logi">
	          <b>用户名：</b><input class="binpt" type="text" name="userName" id="userName" maxlength="11" onkeyup="checkSize(this)"/>
	          <label onclick=""></label></div>
	        <div class="logi">
	          <b>
		          <table width="53" border="0" cellspacing="0" cellpadding="0">
		            <tr>
		              <td width="16">密</td>
		              <td width="14">&nbsp;</td>
		              <td width="23">码:</td>
		            </tr>
		          </table>
	          </b><input class="binptw"  type="password" name="userPw" id="userPw" />
	        </div>
	        <div class="logi">
	          <b>验证码：</b><span><input class="binpty" type="text" name="vcode" id="vcode" /></span>
	          <cite> <img src="validatecode"></img></cite>
	        </div>
	        <input class="dw" type="submit" name="button" id="button" value="" onmouseover="this.className='dws0'" onmouseout="this.className='dw'" />
	        </form>
	  </div>
	   <div class="scbar"><a href="javascript:addfavorite()"></a></div>
	</div>
	</div>
  </body>
</html>
