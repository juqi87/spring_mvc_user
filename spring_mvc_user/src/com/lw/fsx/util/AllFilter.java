package com.lw.fsx.util;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;

import com.lw.fsx.vo.Adminuser;
import com.lw.fsx.vo.HibernateSessionFactory;

/**
 * 整个工程的总过滤器，一般用来判断是否登录和调整编码<br/>
 * web.xml中设置的过滤url规则和spring转发器规则一样，即过滤url /
 * @author 
 *
 */
public class AllFilter implements Filter {
	private static final Logger log = Logger.getLogger(AllFilter.class);
	
	public void init(FilterConfig arg0) throws ServletException {
		log.info("全局过滤器成功启动！");
	}
	
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		((HttpServletRequest) request).setCharacterEncoding("utf-8");
		//清除缓存
		HttpServletResponse httpResponse=(HttpServletResponse)response;
		httpResponse.setHeader("Pragma", "No-cache");
		httpResponse.setHeader("Cache-Control", "no-cache");
		httpResponse.setDateHeader("Expires", 0);
		httpResponse.setContentType("text/html;charset=utf-8");
		String uri = new String(((HttpServletRequest) request).getRequestURI());
		//判断是否登录
		Object user=((HttpServletRequest)request).getSession().getAttribute("adminUser");


		if (uri.contains("/admin/")) {
			//设置页面不缓存
			((HttpServletResponse)response).setHeader("Pragma", "No-cache");
			((HttpServletResponse)response).setHeader("Cache-Control", "no-cache");
			((HttpServletResponse)response).setDateHeader("Expires", 0);
			
			String ss = uri;
			ss = uri.substring(ss.indexOf("/admin/"), ss.length()).replace("/admin/", "");
			String[] ss2 = ss.split("/");
			String out = new String("");
			for(int i=0;i<ss2.length;i++){
				out+="../";
			}
			//System.out.println(out);
			//如果session不存在 转向登录页面/
			if(((HttpServletRequest) request).getSession().getAttribute("adminUser")==null){
				response.getWriter().print("<script>alert('您还没有登录请先登录！');window.parent.location.href='"+out+"login/gotoLogin';</script>");
				return ;
			}
		}
		
		if(uri.contains("/customer/")){
			//设置页面不缓存
			((HttpServletResponse)response).setHeader("Pragma", "No-cache");
			((HttpServletResponse)response).setHeader("Cache-Control", "no-cache");
			((HttpServletResponse)response).setDateHeader("Expires", 0);

			if(user==null || "".equals(user.toString())){
				httpResponse.getWriter().print("<script>alert('抱歉，非法登录！请登录后操作。');parent.location.href='../login/gotoLogin'</script>");
				return ;
			}
			//判断密码是否为初始密码
			Adminuser adminuser = (Adminuser) user;
			try {
				if(adminuser.getUserPwd() != null && !adminuser.getUserPwd().equals("")){
					if(adminuser.getUserPwd().equals(MD5.MD5Encode("000000"))){
						response.getWriter().print("<script>alert('您的密码为初始密码，请修改密码！');location.href='../admin/gotoUpdatePass'</script>");
						return ;
					}
				}
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(uri.contains("/payment/")){
			//设置页面不缓存
			((HttpServletResponse)response).setHeader("Pragma", "No-cache");
			((HttpServletResponse)response).setHeader("Cache-Control", "no-cache");
			((HttpServletResponse)response).setDateHeader("Expires", 0);

			if(user==null || "".equals(user.toString())){
				httpResponse.getWriter().print("<script>alert('抱歉，非法登录！请登录后操作。');parent.location.href='../login/gotoLogin'</script>");
				return ;
			}
			//判断密码是否为初始密码
			Adminuser adminuser = (Adminuser) user;
			try {
				if(adminuser.getUserPwd() != null && !adminuser.getUserPwd().equals("")){
					if(adminuser.getUserPwd().equals(MD5.MD5Encode("000000"))){
						response.getWriter().print("<script>alert('您的密码为初始密码，请修改密码！');location.href='../admin/gotoUpdatePass'</script>");
						return ;
					}
				}
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(uri.contains("/money/")){
			//设置页面不缓存
			((HttpServletResponse)response).setHeader("Pragma", "No-cache");
			((HttpServletResponse)response).setHeader("Cache-Control", "no-cache");
			((HttpServletResponse)response).setDateHeader("Expires", 0);

			if(user==null || "".equals(user.toString())){
				httpResponse.getWriter().print("<script>alert('抱歉，非法登录！请登录后操作。');parent.location.href='../login/gotoLogin'</script>");
				return ;
			}
			//判断密码是否为初始密码
			Adminuser adminuser = (Adminuser) user;
			try {
				if(adminuser.getUserPwd() != null && !adminuser.getUserPwd().equals("")){
					if(adminuser.getUserPwd().equals(MD5.MD5Encode("000000"))){
						response.getWriter().print("<script>alert('您的密码为初始密码，请修改密码！');location.href='../admin/gotoUpdatePass'</script>");
						return ;
					}
				}
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		if(uri.contains("/unicom/")){
			//设置页面不缓存
			((HttpServletResponse)response).setHeader("Pragma", "No-cache");
			((HttpServletResponse)response).setHeader("Cache-Control", "no-cache");
			((HttpServletResponse)response).setDateHeader("Expires", 0);

			if(user==null || "".equals(user.toString())){
				httpResponse.getWriter().print("<script>alert('抱歉，非法登录！请登录后操作。');parent.location.href='../login/gotoLogin'</script>");
				return ;
			}
			//判断密码是否为初始密码
			Adminuser adminuser = (Adminuser) user;
			try {
				if(adminuser.getUserPwd() != null && !adminuser.getUserPwd().equals("")){
					if(adminuser.getUserPwd().equals(MD5.MD5Encode("000000"))){
						response.getWriter().print("<script>alert('您的密码为初始密码，请修改密码！');location.href='../admin/gotoUpdatePass'</script>");
						return ;
					}
				}
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
//		chain.doFilter(request, response);
		try{
			chain.doFilter(request, response);
		}finally{
			try {  
				HibernateSessionFactory.closeSession();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
