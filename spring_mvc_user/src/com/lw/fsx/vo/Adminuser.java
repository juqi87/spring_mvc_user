package com.lw.fsx.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Adminuser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Adminuser extends BaseVo  implements java.io.Serializable {

	// Fields

	private String adminUserId;
	private Roledefinition roledefinition;
	private String userName;
	private String userPwd;
	private Date endDate;
	private Short isPass;
	private String note;
	private Date adderTime;
	private String realityName;
	private String bindMobilePhone;
	private String lastLoginIp;
	private Short sex;
	private Date birthday;
	private String identification;
	private String email;
	private Short smsvalidateState;
	private Integer smsvalidateNumber;
	private String areaAddress; 

	/** default constructor */
	
	public Adminuser() {
	}

	public String getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(String adminUserId) {
		this.adminUserId = adminUserId;
	}

	public Roledefinition getRoledefinition() {
		return roledefinition;
	}

	public void setRoledefinition(Roledefinition roledefinition) {
		this.roledefinition = roledefinition;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Short getIsPass() {
		return isPass;
	}

	public void setIsPass(Short isPass) {
		this.isPass = isPass;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getAdderTime() {
		return adderTime;
	}

	public void setAdderTime(Date adderTime) {
		this.adderTime = adderTime;
	}

	public String getRealityName() {
		return realityName;
	}

	public void setRealityName(String realityName) {
		this.realityName = realityName;
	}

	public String getBindMobilePhone() {
		return bindMobilePhone;
	}

	public void setBindMobilePhone(String bindMobilePhone) {
		this.bindMobilePhone = bindMobilePhone;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Short getSex() {
		return sex;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Short getSmsvalidateState() {
		return smsvalidateState;
	}

	public void setSmsvalidateState(Short smsvalidateState) {
		this.smsvalidateState = smsvalidateState;
	}

	public Integer getSmsvalidateNumber() {
		return smsvalidateNumber;
	}

	public void setSmsvalidateNumber(Integer smsvalidateNumber) {
		this.smsvalidateNumber = smsvalidateNumber;
	}

	public String getAreaAddress() {
		return areaAddress;
	}

	public void setAreaAddress(String areaAddress) {
		this.areaAddress = areaAddress;
	}

}