package com.lw.fsx.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Roledefinition entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Roledefinition extends BaseVo  implements java.io.Serializable {

	// Fields

	private String roleId;
	private String roleName;
	private Date adderTime;
	private Set rolelists = new HashSet(0);
	private Set adminusers = new HashSet(0);

	// Constructors

	/** default constructor */
	public Roledefinition() {
	}

	/** full constructor */
	public Roledefinition(String roleName, Date adderTime, Set rolelists,
			Set adminusers) {
		this.roleName = roleName;
		this.adderTime = adderTime;
		this.rolelists = rolelists;
		this.adminusers = adminusers;
	}

	// Property accessors

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getAdderTime() {
		return this.adderTime;
	}

	public void setAdderTime(Date adderTime) {
		this.adderTime = adderTime;
	}

	public Set getRolelists() {
		return this.rolelists;
	}

	public void setRolelists(Set rolelists) {
		this.rolelists = rolelists;
	}

	public Set getAdminusers() {
		return this.adminusers;
	}

	public void setAdminusers(Set adminusers) {
		this.adminusers = adminusers;
	}

}