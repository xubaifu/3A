/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.systemmanagement.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 考勤信息同步Entity
 * @author xubaifu
 * @version 2017-05-01
 */
public class KqOriginalityData extends DataEntity<KqOriginalityData> {
	
	private static final long serialVersionUID = 1L;
	private String kqDetailId;		// kq_detail_id
	private String username;		// username
	private String location;		// location
	private String machineNo;		// machine_no
	private String cardNo;		// card_no
	private String workDate;		// work_date
	private Long status;		// status
	private Long datafrom;		// datafrom
	private Long inoutFlag;		// inout_flag
	private String operTime;		// oper_time
	private String operUser;		// oper_user
	private String operCause;		// oper_cause
	private String operMach;		// oper_mach
	private String spFlag;		// sp_flag
	private String spUser;		// sp_user
	private String spTime;		// sp_time
	private String currUser;		// curr_user
	private Double latitude;		// latitude
	private Double longitude;		// longitude
	
	private String num;
	
	private String workTime;
	private String A0101;
	private String A0100;
	
	private Date workDate3A;
	private Date operTime3A;	
	private Date spTime3A;
	
	private Date startTime;
	private Date endTime;
	private User user;
	
	public KqOriginalityData() {
		super();
	}

	public KqOriginalityData(String id){
		super(id);
	}

	@Length(min=1, max=30, message="kq_detail_id长度必须介于 1 和 30 之间")
	public String getKqDetailId() {
		return kqDetailId;
	}

	public void setKqDetailId(String kqDetailId) {
		this.kqDetailId = kqDetailId;
	}
	
	@Length(min=0, max=30, message="username长度必须介于 0 和 30 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=0, max=200, message="location长度必须介于 0 和 200 之间")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Length(min=0, max=50, message="machine_no长度必须介于 0 和 50 之间")
	public String getMachineNo() {
		return machineNo;
	}

	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}
	
	@Length(min=0, max=20, message="card_no长度必须介于 0 和 20 之间")
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
	public Long getDatafrom() {
		return datafrom;
	}

	public void setDatafrom(Long datafrom) {
		this.datafrom = datafrom;
	}
	
	public Long getInoutFlag() {
		return inoutFlag;
	}

	public void setInoutFlag(Long inoutFlag) {
		this.inoutFlag = inoutFlag;
	}
	
	@Length(min=0, max=50, message="oper_user长度必须介于 0 和 50 之间")
	public String getOperUser() {
		return operUser;
	}

	public void setOperUser(String operUser) {
		this.operUser = operUser;
	}
	
	@Length(min=0, max=250, message="oper_cause长度必须介于 0 和 250 之间")
	public String getOperCause() {
		return operCause;
	}

	public void setOperCause(String operCause) {
		this.operCause = operCause;
	}
	
	@Length(min=0, max=50, message="oper_mach长度必须介于 0 和 50 之间")
	public String getOperMach() {
		return operMach;
	}

	public void setOperMach(String operMach) {
		this.operMach = operMach;
	}
	
	@Length(min=0, max=2, message="sp_flag长度必须介于 0 和 2 之间")
	public String getSpFlag() {
		return spFlag;
	}

	public void setSpFlag(String spFlag) {
		this.spFlag = spFlag;
	}
	
	@Length(min=0, max=50, message="sp_user长度必须介于 0 和 50 之间")
	public String getSpUser() {
		return spUser;
	}

	public void setSpUser(String spUser) {
		this.spUser = spUser;
	}
	
	@Length(min=0, max=50, message="curr_user长度必须介于 0 和 50 之间")
	public String getCurrUser() {
		return currUser;
	}

	public void setCurrUser(String currUser) {
		this.currUser = currUser;
	}
	
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public String getA0101() {
		return A0101;
	}

	public void setA0101(String a0101) {
		A0101 = a0101;
	}

	public String getA0100() {
		return A0100;
	}

	public void setA0100(String a0100) {
		A0100 = a0100;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWorkDate3A() {
		return workDate3A;
	}

	public void setWorkDate3A(Date workDate3A) {
		this.workDate3A = workDate3A;
	}

	public String getOperTime() {
		return operTime;
	}

	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}

	public String getSpTime() {
		return spTime;
	}

	public void setSpTime(String spTime) {
		this.spTime = spTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOperTime3A() {
		return operTime3A;
	}

	public void setOperTime3A(Date operTime3A) {
		this.operTime3A = operTime3A;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSpTime3A() {
		return spTime3A;
	}

	public void setSpTime3A(Date spTime3A) {
		this.spTime3A = spTime3A;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
}