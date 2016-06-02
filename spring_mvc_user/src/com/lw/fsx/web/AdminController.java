package com.lw.fsx.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lw.fsx.iservice.IAdminService;
import com.lw.fsx.util.JDBCUtil;
import com.lw.fsx.util.MD5;
import com.lw.fsx.util.Paginator;
import com.lw.fsx.vo.Adminuser;
import com.lw.fsx.vo.Itemdefine;
import com.lw.fsx.vo.Roledefinition;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
	@Autowired
	private IAdminService adminService;
	private static final Log log = LogFactory.getLog(AdminController.class);
	private JDBCUtil jdbc = JDBCUtil.getInstance();
	
	/**
	 * 跳转到角色管理的list列表页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/gotoManageRole", method = RequestMethod.GET)
	public ModelAndView gotoManageRole(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<Roledefinition> roles = commonDAO.findAll("Roledefinition");
		request.setAttribute("roles", roles);
		return new ModelAndView("admin/gotoManageRole");
	}

	/**
	 * 跳转到系统管理主页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/gotoAdminMain", method = RequestMethod.GET)
	public ModelAndView gotoAdminMain(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		return new ModelAndView("admin/gotoAdminMain");
	}

	/**
	 * 跳转到顶部top页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/gotoTop", method = RequestMethod.GET)
	public ModelAndView gotoTop(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		return new ModelAndView("admin/top");
	}

	/**
	 * 跳转到左边页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws SQLException 
	 */
	@RequestMapping(value = "/gotoLeft", method = RequestMethod.GET)
	public ModelAndView gotoLeft(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException {
		Adminuser user = (Adminuser) request.getSession().getAttribute("adminUser");
		String leftTree = adminService.getLeft(user);
		request.setAttribute("leftMenu", leftTree);
		return new ModelAndView("admin/left");
	}


	/**
	 * 增加角色
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/ajaxAddRole", method = RequestMethod.GET)
	public ModelAndView ajaxAddRole(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String re = "0";
		String roleName = request.getParameter("roleName");
		if (null != roleName) {
			roleName = new String(roleName.getBytes("iso-8859-1"), "utf-8");
			roleName = URLDecoder.decode(roleName, "utf-8").trim();
			List<Roledefinition> list = commonDAO.findByHql("from Roledefinition where roleName='" + roleName + "'");
			if (null != list && list.size() > 0) {
				re = "1";// 角色名已经存在
			} else {
				Roledefinition role = new Roledefinition();
				role.setRoleName(roleName);
				role.setAdderTime(new Date());
				role.save();
				re = "2";
			}
		}
		response.getWriter().print(re);
		return null;
	}

	/**
	 * 删除角色
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/ajaxDelRole", method = RequestMethod.GET)
	public ModelAndView ajaxDelRole(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String roleId = request.getParameter("roleId");
		Roledefinition role = new Roledefinition();
		role = role.findById(roleId);
		try {
			role.delete();
		} catch (Exception e) {
			log.error("删除错误！",e);
			response.getWriter().print("删除错误！");
		}
		response.getWriter().print("1");
		return null;
	}

	/**
	 * 更新角色名称
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/ajaxupdateRole", method = RequestMethod.GET)
	public ModelAndView ajaxupdateRole(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String re = "0";
		String roleName = request.getParameter("roleName");
		if (null != roleName) {
			roleName = new String(roleName.getBytes("iso-8859-1"), "utf-8");
			List<Roledefinition> list = commonDAO
					.findByHql("from Roledefinition where roleName='"
							+ roleName + "'");
			if (null != list && list.size() > 0) {
				re = "1";// 角色名已经存在
			} else {
				String roleId = request.getParameter("roleId");
				Roledefinition role = new Roledefinition();
				role = role.findById(roleId);
				role.setRoleName(roleName);
				role.update();
				re = "2";
			}
		}
		response.getWriter().print(re);
		return null;
	}

	/**
	 * 跳转到权限主页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/gotoManageRoleItem", method = RequestMethod.GET)
	public ModelAndView gotoManagerRoleItem(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//查出所有的角色
		List<Roledefinition> roles = commonDAO.findAll("Roledefinition");
		request.setAttribute("roleList", roles);
		//所有模块
		List<Itemdefine> items = commonDAO.findAll("Itemdefine order by AdderTime asc");
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (Itemdefine item:items) {
			sb.append("{ id:\""+item.getItemId()+"\", pId:\""+item.getFatherId()+"\",name:\""+item.getItemName()+"\",visitUrl:\""+item.getUrl()+"\",\"open\":\"true\"},");
		}
		sb.append("];");
		request.setAttribute("items", sb);
		return new ModelAndView("admin/gotoManageRoleItem");
	}

	/**
	 * 
	 * @描述 查询某个角色的权限
	 * @作者 lw
	 * @创建日期 2014-3-11 
	 * @版本 V 1.0 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws SQLException 
	 */
	@RequestMapping(value = "/gotoRightList", method = RequestMethod.POST)
	public ModelAndView gotoRightList(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException {
		String roleId = request.getParameter("roleId");
		//查出所有的角色
		List<Roledefinition> roles = commonDAO.findAll("Roledefinition");
		request.setAttribute("roleList", roles);
		//查询某个角色的权限
		List<Map<String, Object>> rolelist = jdbc.query("select ItemId from rolelist where RoleId='"+roleId+"'");
		//所有模块
		List<Itemdefine> items = commonDAO.findAll("Itemdefine");
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		boolean flag = false;
		for (Itemdefine item:items) {
			for(Map<String, Object> role:rolelist){
				if(item.getItemId().equals(role.get("ItemId").toString())){
					flag=true;
					break;
				}
			}
			if(flag){
				flag = false;
				sb.append("{ id:\""+item.getItemId()+"\", pId:\""+item.getFatherId()+"\",name:\""+item.getItemName()+"\",visitUrl:\""+item.getUrl()+"\",\"open\":\"true\",checked:true},");
			}else{
				sb.append("{ id:\""+item.getItemId()+"\", pId:\""+item.getFatherId()+"\",name:\""+item.getItemName()+"\",visitUrl:\""+item.getUrl()+"\",\"open\":\"true\"},");
			}
		}
		sb.append("];");
		request.setAttribute("items", sb);
		request.setAttribute("roleId", roleId);
		return new ModelAndView("admin/gotoManageRoleItem");
	}
	
	/**
	 * 
	 * @描述 更新某个角色的权限
	 * @作者 lw
	 * @创建日期 2014-3-11 
	 * @版本 V 1.0 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws SQLException 
	 */
	@RequestMapping(value = "/gotoUpdateRightList", method = RequestMethod.POST)
	public ModelAndView gotoUpdateRightList(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException {
		String roleId = request.getParameter("roleId");
		Connection con = jdbc.getConnection();
		con.setAutoCommit(false);//取消自动提交
		PreparedStatement pstm = null;
		try {
			//删除原有的权限
			pstm = con.prepareStatement("DELETE FROM rolelist where RoleId= ?");
			pstm.setString(1, roleId);
			pstm.execute();
			//传入选中的权限id
			String rightIds = request.getParameter("rightIds");
			if(rightIds!=null && rightIds!=""){
				String str[] = rightIds.split(",");
				//修改后的权限ID
				for(String itemId:str){
					pstm = con.prepareStatement("INSERT INTO rolelist(RoleListId,ItemId,RoleId,AdderTime) VALUES (REPLACE(UUID(),'-',''), ?, ?, SYSDATE() )");
					pstm.setString(1, itemId);
					pstm.setString(2, roleId);
					pstm.execute();
				}
			}
			con.commit();//提交事务
		} catch (Exception e) {
			con.rollback();//出错回滚事务
			log.error("更新权限失败！",e);
			response.getWriter().print(scriptUtil.ShowMessage("更新权限失败！","../admin/gotoManageRoleItem"));
			return null;
		}finally{
			pstm.close();
			con.close();
		}
		response.getWriter().print(scriptUtil.ShowMessage("更新权限成功！","../admin/gotoManageRoleItem"));
		return null;
	}
	

	/**
	 * 跳转到模块管理页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/gotoManageItem", method = RequestMethod.GET)
	public ModelAndView gotoManagerItem(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setAttribute("allItem", adminService.getAllItem());
		return new ModelAndView("admin/gotoManageItem");
	}

	/**
	 * 
	 * @描述 添加模块
	 * @作者 lw
	 * @创建日期 2014-3-11 
	 * @版本 V 1.0 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/gotoAddItem", method = RequestMethod.POST)
	public ModelAndView gotoAddItem(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String parentId = request.getParameter("fatherItemId");
		if(parentId==null || parentId==""){
			response.getWriter().print(scriptUtil.ShowMessage("请选择目录上级！","../admin/gotoManageItem"));
			return null;
		}
		String itemName = request.getParameter("newItemName");
		if(itemName==null || itemName==""){
			response.getWriter().print(scriptUtil.ShowMessage("请填写目录名称！","../admin/gotoManageItem"));
			return null;
		}
		String visitUrl = request.getParameter("newItemURL");
		Itemdefine item = new Itemdefine();
		item.setFatherId(parentId);
		item.setItemName(itemName);
		item.setUrl(visitUrl);
		item.setTarget("right");
		item.setAdderTime(new Date());
		item.setIsShow(1);
		item.save();
		response.getWriter().print(scriptUtil.ShowMessage("保存成功！","../admin/gotoManageItem"));
		return null;
	}
	/**
	 * 
	 * @描述 删除模块
	 * @作者 lw
	 * @创建日期 2014-3-11 
	 * @版本 V 1.0 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/gotoDeletItem", method = RequestMethod.POST)
	public ModelAndView gotoDeletItem(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//模块ID
		String moduleId = request.getParameter("itemId");
		String sql = "DELETE FROM itemdefine WHERE ItemId = ?;";
		String [] params = {moduleId};
		try {
			jdbc.execute(sql, params);
			response.getWriter().print(scriptUtil.ShowMessage("删除成功！","../admin/gotoManageItem"));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除失败！",e);
			response.getWriter().print(scriptUtil.ShowMessage("删除失败！","../admin/gotoManageItem"));
		}
		return null;
	}
	/**
	 * 
	 * @描述 更新模块
	 * @作者 lw
	 * @创建日期 2014-3-11 
	 * @版本 V 1.0 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/gotoUpdateItem", method = RequestMethod.POST)
	public ModelAndView gotoUpdateItem(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//模块ID
		String moduleId = request.getParameter("itemId");
		//模块名称
		String itemName = request.getParameter("itemName");
		if(itemName==null || itemName==""){
			response.getWriter().print(scriptUtil.ShowMessage("请输入目录名称！","../admin/gotoManageItem"));
			return null;
		}
		String visitUrl = request.getParameter("itemURL");
		String sql = "UPDATE itemdefine SET ItemName = ?, Url = ? WHERE ItemId = ?;";
		String [] params = {itemName,visitUrl,moduleId};
		try {
			jdbc.execute(sql, params);
			response.getWriter().print(scriptUtil.ShowMessage("修改成功！","../admin/gotoManageItem"));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("修改失败！",e);
			response.getWriter().print(scriptUtil.ShowMessage("修改失败！","../item/gotoManageItem"));
		}
		return null;
	}

	/**
	 * 跳转到用户管理页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/gotoManageAdminUser", method = RequestMethod.GET)
	public ModelAndView gotoManageAdminUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setAttribute("roleSelect", adminService.getRoleSelct());
		String currPage = request.getParameter("currPage");
		String userName = request.getParameter("userName");
		String content = "";
		String curl = "";
		if (null != userName) {
			userName = new String(userName.getBytes("iso-8859-1"), "utf-8");
			curl += "&userName=" + userName;
			content += " and userName like '%" + userName + "%' ";
		}
		int int_pageNow = 1;
		int pageSize = 20;
		if (null != currPage) {
			int_pageNow = Integer.parseInt(currPage);
		}
		String hql = "from Adminuser where 1=1 " + content
				+ " order by adderTime desc";
		int count = 0;
		List<?> list = commonDAO.findByHql("select count(adminUserId) " + hql);
		if (null != list && list.size() > 0) {
			count = Integer.parseInt(list.get(0).toString());
		}
		List<Adminuser> listUser = commonDAO.findPageByHql(int_pageNow,
				pageSize, hql);
		request.setAttribute("listUser", listUser);
		// 分页方法
		Paginator pager = new Paginator(int_pageNow, pageSize, count);
		request.setAttribute("page", pager.toHtmlPageStyleT(
				"gotoManageAdminUser", curl));
		return new ModelAndView("admin/gotoManageAdminUser");
	}

	/**
	 * 添加用户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/ajaxAddUser", method = RequestMethod.GET)
	public ModelAndView ajaxAddUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userName = request.getParameter("userName");
		String userPw = request.getParameter("userPw");
		String userPass = request.getParameter("userPass");
		String roleId = request.getParameter("roleId");

		if (null != userName && null != userPw && null != userPass
				&& null != roleId) {
			if (adminService.checkUserName(userName)) {// 用户名合法
				Adminuser user = new Adminuser();
				user.setUserName(userName);
				user.setUserPwd(MD5.MD5Encode(userPw));
				user.setIsPass(Short.parseShort(userPass));
				Roledefinition role = new Roledefinition();
				role = role.findById(roleId);
				user.setRoledefinition(role);
				user.setAdderTime(new Date());
				user.setSmsvalidateState(Short.parseShort("0"));
				user.setSmsvalidateNumber(0);
				user.save();
				response.getWriter().print("1");
			} else {// 用户存在
				response.getWriter().print("2");
			}
		}
		return null;
	}

	/**
	 * 跳转到修改用户信息界面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/gotoUpdateAdminUser", method = RequestMethod.GET)
	public ModelAndView gotoUpdateAdminUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String userId = request.getParameter("userId");
		Adminuser user = new Adminuser();
		user = user.findById(userId);
		request.setAttribute("userObj", user);
		request.setAttribute("roleSelect",adminService.getRoleSelctForUpdateUser(user.getRoledefinition().getRoleId()));
		return new ModelAndView("admin/updateAdminUser");
	}

	/**
	 * 修改用户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/ajaxUpdateUser", method = RequestMethod.GET)
	public ModelAndView ajaxUpdateUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userName = request.getParameter("userName");
		String userPw = request.getParameter("userPw");
		String userPass = request.getParameter("userPass");
		String roleId = request.getParameter("roleId");
		String userId = request.getParameter("userId");

		Adminuser user = new Adminuser();
		user = user.findById(userId);
		user.setUserName(userName);
		if (null != userPw && !"".equals(userPw)) {// 重新设置密码
			user.setUserPwd(MD5.MD5Encode(userPw));
		}
		user.setIsPass(Short.parseShort(userPass));
		Roledefinition role = new Roledefinition();
		role = role.findById(roleId);
		user.setRoledefinition(role);
		user.setAdderTime(new Date());
		user.update();
		response.getWriter().print("1");
		return null;
	}


	/**
	 * 跳转到用户修改密码页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/gotoUpdatePass", method = RequestMethod.GET)
	public ModelAndView gotoUpdatePass(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("admin/gotoUpdatePass");
	}

	/**
	 * 用户修改密码
	 */
	@RequestMapping(value = "/goinUpdatePwd", method = RequestMethod.POST)
	public ModelAndView goinUpdatePwd(HttpServletRequest request,
			HttpServletResponse response) throws NoSuchAlgorithmException,
			IOException {
		// 老密码
		String oldpwd = request.getParameter("oldpwd");
		// 新密码
		String newpwd = request.getParameter("newpwd");
		String renewpwd = request.getParameter("renewpwd");
		// 获得登录用户信息
		Adminuser user = (Adminuser) request.getSession().getAttribute(
				"adminUser");
		if (oldpwd.equals("")
				|| !user.getUserPwd().equals(MD5.MD5Encode(oldpwd))) {
			response.getWriter().print(
					scriptUtil.ShowMessage("抱歉！原始密码不正确！", "gotoUpdatePass"));
			return null;
		}
		if (newpwd.equals("") || newpwd == null) {
			response.getWriter().print(
					scriptUtil.ShowMessage("抱歉！新密码不能为空！", "gotoUpdatePass"));
			return null;
		}
		if (renewpwd.equals("") || renewpwd == null) {
			response.getWriter().print(
					scriptUtil.ShowMessage("抱歉！新密码不能为空！", "gotoUpdatePass"));
			return null;
		}
		if (!renewpwd.equals(newpwd)) {
			response.getWriter().print(
					scriptUtil.ShowMessage("抱歉！重复密码不正确！", "gotoUpdatePass"));
			return null;
		}
		user.setUserPwd(MD5.MD5Encode(newpwd));
		user.update();
		log.info("用户：" + user.getAdminUserId() + "修改密码成功！");
		request.getSession().setAttribute("adminUser", user);
		response
				.getWriter()
				.print(
						"<script>alert('恭喜你！密码修改成功！');parent.location.href='../login/gotoLogin'</script>");
		// response.getWriter().print("<script>alert('恭喜你！密码修改成功！');parent.location.href='http://www.51663.net/sd_manage/new_updatePW.jsp?username="+user.getUserName()+"'</script>");
		return null;
	}

	/**
	 * 删除用户
	 * 
	 * @param userId用户id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public ModelAndView deleteUser(@RequestParam("userId")
	String userId, HttpServletRequest request, HttpServletResponse response) {
		log.debug("删除用户开始");
		Adminuser user = (Adminuser) request.getSession().getAttribute(
				"adminUser");
		String i = null;
		if (userId.equals(user.getAdminUserId())) {
			log.debug("删除自己失败！");
			i = "2";
		} else {
			try {
				adminService.deleteUser(userId);
				i = "1";
				log.debug("删除用户成功!");
			} catch (Exception e) {
				i = "0";
				log.error("删除用户失败！");
				e.printStackTrace();
			}
		}
		try {
			log.debug("删除用户，向客户端输出" + i);
			response.getOutputStream().print(i);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
