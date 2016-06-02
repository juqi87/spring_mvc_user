package com.lw.fsx.iservice;

import java.sql.SQLException;

import com.lw.fsx.vo.Adminuser;


public interface IAdminService {
	
	/**
	 * 检查用户名的合法性
	 * @param user
	 * @return false 非法、true合法
	 */
	public boolean checkUserName(String userName);
	/**
	 * 获得角色下拉框修改用户信息
	 * @param user
	 * @return
	 */
	public String getRoleSelctForUpdateUser(String roleId);
	/**
	 * 获得所有的模块（模块管理）
	 * @param user
	 * @return
	 */
	public String getAllItem();
	/**
	 * 更新某个角色的模块（权限管理）
	 * @param user
	 * @return
	 */
	public void updateRoleItem(String roleId,String roles)throws Exception;
	
	/**
	 * 获得某个角色的模块（权限管理）
	 * @param user
	 * @return
	 */
	public String getItemSelct(String roleId);
	/**
	 * 获得角色下拉框
	 * @param user
	 * @return
	 */
	public String getRoleSelct();
	/**
	 * 获得左边菜单
	 * @param user
	 * @return
	 * @throws SQLException 
	 */
	public String getLeft(Adminuser user) throws SQLException;
	
	/**
	 * 删除用户
	 * @param userId 用户id
	 * @throws Exception
	 */
	public void deleteUser(String userId)throws Exception;


}
