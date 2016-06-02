package com.lw.fsx.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lw.fsx.util.MD5;
import com.lw.fsx.vo.Adminuser;

/**
 * 登录前的操作
 * 
 * Axislogold   
 * Consumemoneyrecordold
 * Liantongsend210box
 * Liantongsendbox
 * Liantongsendnow
 * Smssendboxm
 * Telecomsendbox2
 * 
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	/**
	 *	跳转到登录页面 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/gotoLogin", method = RequestMethod.GET)
	public ModelAndView gotoLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
		return new ModelAndView("login/gotoLogin");
	}
	/**
	 *	验证码
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/validatecode", method = RequestMethod.GET)
	public ModelAndView validatecode(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		return new ModelAndView("common/validatecode");
	}
	/**
	 *	登录
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException 
	 */
	@RequestMapping(value = "/goinLogin", method = RequestMethod.POST)
	public ModelAndView goinLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException, NoSuchAlgorithmException {
		String userName = request.getParameter("userName");
		String userPw = request.getParameter("userPw");
		String vcode = request.getParameter("vcode");
		//判断验证码是否正确
		Object sessionOne=request.getSession().getAttribute("rand");
		if(null == sessionOne || sessionOne.equals("")){
			response.getWriter().print(scriptUtil.ShowMessage("验证码过期！","gotoLogin"));
			return null ;
		}
		if(!sessionOne.equals(vcode)){
			response.getWriter().print(scriptUtil.ShowMessage("验证码不正确！","gotoLogin"));
			return null ;
		}
		
		request.getSession().invalidate();//清除session
		
		List<Adminuser> list = commonDAO.findByProperty("Adminuser", "userName", userName);
		if(null!=list && list.size()>0){//存在此用户
			Adminuser user = list.get(0);
			if(!user.getUserPwd().equals(MD5.MD5Encode(userPw))){//密码不正确
				response.getWriter().print(scriptUtil.ShowMessage("密码错误！", "gotoLogin"));
				return null;
			}else if(!user.getIsPass().equals((short) 1)){
				response.getWriter().print(scriptUtil.ShowMessage("用户锁定！", "gotoLogin"));
				return null;
			}else{
				if(userPw.equals(MD5.MD5Encode("000000"))){
					response.getWriter().print(scriptUtil.ShowMessage("请修改密码后在操作！", "../admin/gotoUpdatePassIndex"));
					return null;
				}
				request.getSession().setAttribute("adminUser", user);
				//request.getSession().setAttribute("userName", user.getAttnName());
			}
			response.getWriter().write(scriptUtil.Redirect("../mainframe.html"));
		}else{//不存在此用户			
			response.getWriter().print(scriptUtil.ShowMessage("用户名错误！", "gotoLogin"));
			return null;
		}
		return null;
		//return new ModelAndView("admin/index");
	}
	/**
	 *	退出登录
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/gotoOutLogin", method = RequestMethod.GET)
	public ModelAndView gotoOutLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.getSession().invalidate();//注销session
		StringBuilder sb = new StringBuilder();
		sb.append("<script language='javascript'>");
		sb.append("window.parent.location.href ='");
		sb.append("gotoLogin");
		sb.append("';");
		sb.append("</script>");
		response.getWriter().write(sb.toString());
		return null;
	}


	
}
