/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.reportmanage.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 门禁刷卡记录Entity
 * @author xubaifu
 * @version 2017-05-15
 */
public class TDSwiperecord extends DataEntity<TDSwiperecord> {
	
	private static final long serialVersionUID = 1L;
	private Long fRecid;		// f_recid
	private Date fReaddate;		// f_readdate
	private String fCardno;		// f_cardno
	private String fConsumerid;		// f_consumerid
	private Long fCharacter;		// f_character
	private Long fInout;		// f_inout
	private Long fStatus;		// f_status
	private Long fRecoption;		// f_recoption
	private Long fControllersn;		// f_controllersn
	private Long fReaderid;		// f_readerid
	private Long fReaderno;		// f_readerno
	private Long fRecordflashloc;		// f_recordflashloc
	private String fRecordall;		// f_recordall
	private Date fModified;		// f_modified
	
	private String name;
	private String no;
	private String cardNo;
	private String fDoorNames; 
	private Date startTime;
	private Date endTime;
	
	public TDSwiperecord() {
		super();
	}

	public TDSwiperecord(String id){
		super(id);
	}

	public Long getfRecid() {
		return fRecid;
	}

	public void setfRecid(Long fRecid) {
		this.fRecid = fRecid;
	}

	public Date getfReaddate() {
		return fReaddate;
	}

	public void setfReaddate(Date fReaddate) {
		this.fReaddate = fReaddate;
	}

	public String getfCardno() {
		return fCardno;
	}

	public void setfCardno(String fCardno) {
		this.fCardno = fCardno;
	}

	public String getfConsumerid() {
		return fConsumerid;
	}

	public void setfConsumerid(String fConsumerid) {
		this.fConsumerid = fConsumerid;
	}

	public Long getfCharacter() {
		return fCharacter;
	}

	public void setfCharacter(Long fCharacter) {
		this.fCharacter = fCharacter;
	}

	public Long getfInout() {
		return fInout;
	}

	public void setfInout(Long fInout) {
		this.fInout = fInout;
	}

	public Long getfStatus() {
		return fStatus;
	}

	public void setfStatus(Long fStatus) {
		this.fStatus = fStatus;
	}

	public Long getfRecoption() {
		return fRecoption;
	}

	public void setfRecoption(Long fRecoption) {
		this.fRecoption = fRecoption;
	}

	public Long getfControllersn() {
		return fControllersn;
	}

	public void setfControllersn(Long fControllersn) {
		this.fControllersn = fControllersn;
	}

	public Long getfReaderid() {
		return fReaderid;
	}

	public void setfReaderid(Long fReaderid) {
		this.fReaderid = fReaderid;
	}

	public Long getfReaderno() {
		return fReaderno;
	}

	public void setfReaderno(Long fReaderno) {
		this.fReaderno = fReaderno;
	}

	public Long getfRecordflashloc() {
		return fRecordflashloc;
	}

	public void setfRecordflashloc(Long fRecordflashloc) {
		this.fRecordflashloc = fRecordflashloc;
	}

	public String getfRecordall() {
		return fRecordall;
	}

	public void setfRecordall(String fRecordall) {
		this.fRecordall = fRecordall;
	}

	public Date getfModified() {
		return fModified;
	}

	public void setfModified(Date fModified) {
		this.fModified = fModified;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getfDoorNames() {
		return fDoorNames;
	}

	public void setfDoorNames(String fDoorNames) {
		this.fDoorNames = fDoorNames;
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