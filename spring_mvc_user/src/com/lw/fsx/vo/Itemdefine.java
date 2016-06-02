package com.lw.fsx.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Itemdefine entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Itemdefine extends BaseVo  implements java.io.Serializable {

	// Fields

	private String itemId;
	private String itemName;
	private String fatherId;
	private String target;
	private String url;
	private Date adderTime;
	private String imageName;
	private String note;
	private Integer isShow;
	private Set rolelists = new HashSet(0);

	// Constructors

	/** default constructor */
	public Itemdefine() {
	}

	/** full constructor */
	public Itemdefine(String itemName, String fatherId, String target,
			String url, Date adderTime, String imageName, String note,
			Set rolelists) {
		this.itemName = itemName;
		this.fatherId = fatherId;
		this.target = target;
		this.url = url;
		this.adderTime = adderTime;
		this.imageName = imageName;
		this.note = note;
		this.rolelists = rolelists;
	}

	// Property accessors

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getFatherId() {
		return this.fatherId;
	}

	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}

	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getAdderTime() {
		return this.adderTime;
	}

	public void setAdderTime(Date adderTime) {
		this.adderTime = adderTime;
	}

	public String getImageName() {
		return this.imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set getRolelists() {
		return this.rolelists;
	}

	public void setRolelists(Set rolelists) {
		this.rolelists = rolelists;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

}