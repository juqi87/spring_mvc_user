package com.lw.fsx.vo;

import java.util.Date;

/**
 * Rolelist entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Rolelist extends BaseVo  implements java.io.Serializable {

	// Fields

	private String roleListId;
	private Itemdefine itemdefine;
	private Roledefinition roledefinition;
	private Short pruview;
	private Date adderTime;

	// Constructors

	/** default constructor */
	public Rolelist() {
	}

	/** minimal constructor */
	public Rolelist(Itemdefine itemdefine, Roledefinition roledefinition) {
		this.itemdefine = itemdefine;
		this.roledefinition = roledefinition;
	}

	/** full constructor */
	public Rolelist(Itemdefine itemdefine, Roledefinition roledefinition,
			Short pruview, Date adderTime) {
		this.itemdefine = itemdefine;
		this.roledefinition = roledefinition;
		this.pruview = pruview;
		this.adderTime = adderTime;
	}

	// Property accessors

	public String getRoleListId() {
		return this.roleListId;
	}

	public void setRoleListId(String roleListId) {
		this.roleListId = roleListId;
	}

	public Itemdefine getItemdefine() {
		return this.itemdefine;
	}

	public void setItemdefine(Itemdefine itemdefine) {
		this.itemdefine = itemdefine;
	}

	public Roledefinition getRoledefinition() {
		return this.roledefinition;
	}

	public void setRoledefinition(Roledefinition roledefinition) {
		this.roledefinition = roledefinition;
	}

	public Short getPruview() {
		return this.pruview;
	}

	public void setPruview(Short pruview) {
		this.pruview = pruview;
	}

	public Date getAdderTime() {
		return this.adderTime;
	}

	public void setAdderTime(Date adderTime) {
		this.adderTime = adderTime;
	}

}