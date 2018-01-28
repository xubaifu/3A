/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.authorizationManage.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.tdk3A.authorization.entity.AttendanceEntity;

/**
 * 授权管理Entity
 * @author baifu
 * @version 2017-03-05
 */
public class AuthorizationManageEntity extends DataEntity<AuthorizationManageEntity> {
	
	private static final long serialVersionUID = 1L;
	private String companyId;	// 归属公司
	private String officeId;	// 归属部门
	private String loginName;// 登录名
	private String no;		// 工号
	private String name;	// 姓名
	private String userType;// 用户类型
	
	private String cardno;//用户门禁卡号
	private String doorId;
	private AttendanceEntity attendanceEntity;
	
	public String getDoorId() {
		return doorId;
	}
	public void setDoorId(String doorId) {
		this.doorId = doorId;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public AttendanceEntity getAttendanceEntity() {
		return attendanceEntity;
	}
	public void setAttendanceEntity(AttendanceEntity attendanceEntity) {
		this.attendanceEntity = attendanceEntity;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}