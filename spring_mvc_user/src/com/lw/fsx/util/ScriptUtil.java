package com.lw.fsx.util;

/**
 * 用于在后台往前台输出javascript语句。
 * @author 
 *
 */
public class ScriptUtil {
	
	public ScriptUtil(){
		
	}
	
	/**
	 * 重新加载页面
	 * @return
	 */
	public String Reload()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("<script language=\"javascript\">\n");
		sb.append("window.location.href=window.location.href;");
		sb.append("\n");
		sb.append("</script>");
        return sb.toString();
	}
	
	/**
	 * 跳转到指定的url
	 * @param url 被跳转到url
	 * @return
	 */
	public String Redirect(String url)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<script language='javascript'>");
		sb.append("window.location.href ='");
		sb.append(url);
		sb.append("';");
		sb.append("</script>");
		return sb.toString();
	}
	
	/**
	 * 弹出alert警告框
	 * @param msgInfo 警告信息
	 * @param url 点击确定后被重定向到的地址
	 * @return
	 */
	public String ShowMessage(String msgInfo,String url)
	{  
		String strMessage;
		strMessage="<script language='javascript'>";
		strMessage=strMessage + "alert('" + msgInfo + "')" + ";";
		if (!"".equals(url) && url!=null)
			strMessage=strMessage + "window.location.href='" + url + "';";
		strMessage=strMessage + "</script>";
		return strMessage;
	}
	/**
	 * Confirm 当用户选择是时，跳转到url，否则返回
	 * @param msgInfo	confirm内容
	 * @param okUrl	点击确定跳转到的url
	 * @param cancleUrl 点击取消跳转到的url
	 * @return String
	 */
	public String showConfirm(String msgInfo,String okUrl,String cancleUrl)
	{  
		StringBuffer sb=new StringBuffer();
		sb.append("<script language='javascript'>\n");
		sb.append("if(confirm('" + msgInfo + "')){\n");
		sb.append("window.location.href='" + okUrl + "';\n}else{\n");
		sb.append("window.location.href='" + cancleUrl + "';\n}\n");
		sb.append("</script>");
		return sb.toString();
	}
	/**
	 * Confirm 当用户选择是时，跳转到url，否则返回
	 * @param msgInfo	confirm内容
	 * @param okUrl	点击确定跳转到的url
	 * @return String
	 */
	public String showConfirm(String msgInfo,String okUrl)
	{  
		StringBuffer sb=new StringBuffer();
		sb.append("javascript:\n");
		sb.append("if(confirm('" + msgInfo + "')){\n");
		sb.append("window.location.href='" + okUrl + "';\n}\n");
		return sb.toString();
	}

}