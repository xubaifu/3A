/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.authorization.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 一卡通统一授权Entity
 * 
 * @author baifu
 * @version 2017-03-04
 */
public class AuthorizationEntity extends DataEntity<AuthorizationEntity> {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String cardNo;
	private String userNo;
	private String userName;
	private String officeId;
	private String officeName;
	private String isOnjob;
	private String cardStatus;
	private Date startTime;
	private Date endTime;
	private TBControllerEntity doorEntity;
	private String kqId;
	private String userType;
	private String num;

	private AttendanceEntity attendanceEntity;

	private String oldCardNo;
	private String newCardNo;

	private String doorId;
	private String foodId;

	private String fDoorid;
	private String fDoorno;
	private String fDoorname;
	private String fDoorontrol;
	private String fControllerid;
	private String fControllerno;

	private String fControllersn;
	private String fIp;

	private String fPrivilegerecid;
	private String fControlsegid;
	private String fConsumerid;

	private String doorMessage;
	
	private String userMessage;

	private String roleIds;
	
	private String typeNo;//餐卡类型
	
	
	public String getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	//old office id
    private String dept_id;
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getUserId() {
		return userId;
	}

	public String getfControllerno() {
		return fControllerno;
	}

	public void setfControllerno(String fControllerno) {
		this.fControllerno = fControllerno;
	}

	public String getDoorMessage() {
		return doorMessage;
	}

	public void setDoorMessage(String doorMessage) {
		this.doorMessage = doorMessage;
	}

	public String getfControllersn() {
		return fControllersn;
	}

	public void setfControllersn(String fControllersn) {
		this.fControllersn = fControllersn;
	}

	public String getfIp() {
		return fIp;
	}

	public void setfIp(String fIp) {
		this.fIp = fIp;
	}

	public String getfControllerid() {
		return fControllerid;
	}

	public void setfControllerid(String fControllerid) {
		this.fControllerid = fControllerid;
	}

	public String getfDoorid() {
		return fDoorid;
	}

	public void setfDoorid(String fDoorid) {
		this.fDoorid = fDoorid;
	}

	public String getfDoorno() {
		return fDoorno;
	}

	public void setfDoorno(String fDoorno) {
		this.fDoorno = fDoorno;
	}

	public String getfDoorname() {
		return fDoorname;
	}

	public void setfDoorname(String fDoorname) {
		this.fDoorname = fDoorname;
	}

	public String getfDoorontrol() {
		return fDoorontrol;
	}

	public void setfDoorontrol(String fDoorontrol) {
		this.fDoorontrol = fDoorontrol;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getIsOnjob() {
		return isOnjob;
	}

	public void setIsOnjob(String isOnjob) {
		this.isOnjob = isOnjob;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getKqId() {
		return kqId;
	}

	public void setKqId(String kqId) {
		this.kqId = kqId;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getOldCardNo() {
		return oldCardNo;
	}

	public void setOldCardNo(String oldCardNo) {
		this.oldCardNo = oldCardNo;
	}

	public String getNewCardNo() {
		return newCardNo;
	}

	public void setNewCardNo(String newCardNo) {
		this.newCardNo = newCardNo;
	}

	public String getDoorId() {
		return doorId;
	}

	public void setDoorId(String doorId) {
		this.doorId = doorId;
	}

	public String getFoodId() {
		return foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

	public TBControllerEntity getDoorEntity() {
		return doorEntity;
	}

	public void setDoorEntity(TBControllerEntity doorEntity) {
		this.doorEntity = doorEntity;
	}

	public AttendanceEntity getAttendanceEntity() {
		return attendanceEntity;
	}

	public void setAttendanceEntity(AttendanceEntity attendanceEntity) {
		this.attendanceEntity = attendanceEntity;
	}

	public String getfPrivilegerecid() {
		return fPrivilegerecid;
	}

	public void setfPrivilegerecid(String fPrivilegerecid) {
		this.fPrivilegerecid = fPrivilegerecid;
	}

	public String getfControlsegid() {
		return fControlsegid;
	}

	public void setfControlsegid(String fControlsegid) {
		this.fControlsegid = fControlsegid;
	}

	public String getfConsumerid() {
		return fConsumerid;
	}

	public void setfConsumerid(String fConsumerid) {
		this.fConsumerid = fConsumerid;
	}
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}