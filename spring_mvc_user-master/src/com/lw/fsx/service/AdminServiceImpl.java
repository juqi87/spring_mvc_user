package com.lw.fsx.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lw.fsx.iservice.IAdminService;
import com.lw.fsx.util.JDBCUtil;
import com.lw.fsx.vo.Adminuser;
import com.lw.fsx.vo.Roledefinition;
import com.lw.fsx.web.BaseController;

public class AdminServiceImpl extends BaseController implements IAdminService {

	private static final Log log = LogFactory.getLog(AdminServiceImpl.class);
	private JDBCUtil jdbc = JDBCUtil.getInstance();

	/**
	 * 检查用户名的合法性
	 * 
	 * @param user
	 * @return false 非法、true合法
	 */
	@Override
	public boolean checkUserName(String userName) {
		boolean b = false;
		List<?> list = commonDAO.findByProperty("Adminuser", "userName",userName);
		if (null != list && list.size() > 0) {// 存在非法
			b = false;
		} else {// 不存在合法用户
			b = true;
		}
		return b;
	}

	/**
	 * 获得角色下拉框修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public String getRoleSelctForUpdateUser(String roleId) {
		List<Roledefinition> roles = commonDAO.findAll("Roledefinition");
		StringBuffer sb = new StringBuffer();
		if (null != roles && roles.size() > 0) {
			sb
					.append("<select name=\"roleId\" id=\"roleId\" onchange=\"selectRoleItem(this)\">");
			sb.append("<option value='0'>请选择...</option>");
			for (Roledefinition role : roles) {
				if (roleId.equals(role.getRoleId())) {
					sb.append("<option selected='selected' value="
							+ role.getRoleId() + "> " + role.getRoleName()
							+ " </option>");
				} else
					sb.append("<option value=" + role.getRoleId() + "> "
							+ role.getRoleName() + " </option>");

			}
			sb.append("</select>");
		}
		return sb.toString();
	}

	/**
	 * 获得所有的模块（模块管理）
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public String getAllItem() {
		String tree = "{ id:\"0\", pId:\"-1\", name:\"目录\",visitUrl:\"\",\"open\":\"true\"},\n";
		JDBCUtil localJdbc = JDBCUtil.getInstance();
		String querySql = "SELECT i.ItemId   AS 'ItemId', i.ItemName AS 'ItemName', i.FatherId AS 'FatherId', i.Url AS 'Url' FROM itemdefine i WHERE i.FatherId = \"0\" OR i.FatherId IN (SELECT ItemId FROM  itemdefine WHERE FatherId=\"0\");";
		List<Map<String, Object>> roleList;
		try {
			roleList = localJdbc.query(querySql);
			for (Map<String, Object> map : roleList) {
				tree += "{ id:\"" + map.get("ItemId") + "\", pId:\""
						+ map.get("FatherId") + "\", name:\""
						+ map.get("ItemName") + "\",visitUrl:\""
						+ map.get("Url").toString()
						+ "\",\"open\":\"true\"},\n";
			}
		} catch (SQLException e) {
			log.error(" 获得所有的模块（模块管理）错误！", e);
		}
		return tree;
	}

	/**
	 * 更新某个角色的模块（权限管理）
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public void updateRoleItem(String roleId, String roles) throws Exception {
		
	}

	/**
	 * 获得某个角色的模块（权限管理）
	 * 
	 * @param user
	 * @return <input type="checkbox" name="checkbox" value="checkbox"
	 *         checked="checked"/> if(str.indexOf("ABC")!=-1){
	 *         System.out.println("包含"); }else{ System.out.println("不包含"); }
	 */
	@Override
	public String getItemSelct(String roleId) {
		return null;
	}

	/**
	 * 获得角色下拉框
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public String getRoleSelct() {
		List<Roledefinition> roles = commonDAO.findAll("Roledefinition");
		StringBuffer sb = new StringBuffer();
		if (null != roles && roles.size() > 0) {
			sb
					.append("<select name=\"selectRole\" id=\"selectRole\" onchange=\"selectRoleItem(this)\">");
			sb.append("<option value='0' selected='selected'>请选择...</option>");
			for (Roledefinition role : roles) {
				sb.append("<option value=" + role.getRoleId() + "> "
						+ role.getRoleName() + " </option>");
			}
			sb.append("</select>");
		}
		return sb.toString();
	}

	/**
	 * 获得左边菜单
	 * 
	 * @param user
	 * @return r.RoleId='5c4acec61be5025f011c11b5df1602cc' AND r.ItemId=i.ItemId
	 *         AND i.FatherId='0' AND r.Pruview=1
	 * @throws SQLException
	 */
	@Override
	public String getLeft(Adminuser user) throws SQLException {
		String sql = "SELECT item.* FROM rolelist role,itemdefine item WHERE role.RoleId='"
				+ user.getRoledefinition().getRoleId()
				+ "' AND role.ItemId=item.ItemId order by AdderTime asc;";
		List<Map<String, Object>> maps = jdbc.query(sql);
		StringBuffer sb = new StringBuffer();
		for (Map<String, Object> item : maps) {
			if (!"0".equals(item.get("FatherId").toString())) {
				continue;
			}
			sb.append("<li>");
			sb.append("<h4>" + item.get("ItemName") + "</h4>");
			sb.append("<div class=\"list-item none\">");
			for (Map<String, Object> map : maps) {
				if (map.get("FatherId").toString().equals(item.get("ItemId").toString())) {
					sb.append("<p ><a href=\"" + map.get("url") + "\" target=\"" + map.get("Target") + "\">" + map.get("ItemName") + "</a></p>");
					continue;
				}
			}
			sb.append("</div>");
			sb.append("</li>");
		}
		return sb.toString();
	}

	/**
	 * 删除用户
	 */
	@Override
	public void deleteUser(String userId) throws Exception {
		Adminuser user = new Adminuser();
		user.setAdminUserId(userId);
		user.delete();
	}

}
