/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.authorization.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * Entity
 * @author xubaifu
 * @version 2017-03-27
 */
public class TBDoor extends DataEntity<TBDoor> {
	
	private static final long serialVersionUID = 1L;
	private Integer fDoorid;		// f_doorid
	private Integer fControllerid;		// f_controllerid
	private Integer fDoorno;		// f_doorno
	private String fDoorname;		// f_doorname
	private Integer fDoorcontrol;		// f_doorcontrol
	private Integer fDoordelay;		// f_doordelay
	private Integer fDoorenabled;		// f_doorenabled
	private Integer fMorecardsTotal;		// f_morecards_total
	private Integer fMorecardsGrp1;		// f_morecards_grp1
	private Integer fMorecardsGrp2;		// f_morecards_grp2
	private Integer fMorecardsGrp3;		// f_morecards_grp3
	private Integer fMorecardsGrp4;		// f_morecards_grp4
	private Integer fMorecardsGrp5;		// f_morecards_grp5
	private Integer fMorecardsGrp6;		// f_morecards_grp6
	private Integer fMorecardsGrp7;		// f_morecards_grp7
	private Integer fMorecardsGrp8;		// f_morecards_grp8
	private Integer fMorecardsOption;		// f_morecards_option
	private Integer fFirstcardEnabled;		// f_firstcard_enabled
	private Integer fFirstcardWeekday;		// f_firstcard_weekday
	private String fFirstcardBeginhms;		// f_firstcard_beginhms
	private Integer fFirstcardBegincontrol;		// f_firstcard_begincontrol
	private String fFirstcardEndhms;		// f_firstcard_endhms
	private Integer fFirstcardEndcontrol;		// f_firstcard_endcontrol
	
	public TBDoor() {
		super();
	}

	public TBDoor(String id){
		super(id);
	}

	@NotNull(message="f_doorid不能为空")
	public Integer getFDoorid() {
		return fDoorid;
	}

	public void setFDoorid(Integer fDoorid) {
		this.fDoorid = fDoorid;
	}
	
	@NotNull(message="f_controllerid不能为空")
	public Integer getFControllerid() {
		return fControllerid;
	}

	public void setFControllerid(Integer fControllerid) {
		this.fControllerid = fControllerid;
	}
	
	@NotNull(message="f_doorno不能为空")
	public Integer getFDoorno() {
		return fDoorno;
	}

	public void setFDoorno(Integer fDoorno) {
		this.fDoorno = fDoorno;
	}
	
	@Length(min=1, max=100, message="f_doorname长度必须介于 1 和 100 之间")
	public String getFDoorname() {
		return fDoorname;
	}

	public void setFDoorname(String fDoorname) {
		this.fDoorname = fDoorname;
	}
	
	@NotNull(message="f_doorcontrol不能为空")
	public Integer getFDoorcontrol() {
		return fDoorcontrol;
	}

	public void setFDoorcontrol(Integer fDoorcontrol) {
		this.fDoorcontrol = fDoorcontrol;
	}
	
	@NotNull(message="f_doordelay不能为空")
	public Integer getFDoordelay() {
		return fDoordelay;
	}

	public void setFDoordelay(Integer fDoordelay) {
		this.fDoordelay = fDoordelay;
	}
	
	@NotNull(message="f_doorenabled不能为空")
	public Integer getFDoorenabled() {
		return fDoorenabled;
	}

	public void setFDoorenabled(Integer fDoorenabled) {
		this.fDoorenabled = fDoorenabled;
	}
	
	@NotNull(message="f_morecards_total不能为空")
	public Integer getFMorecardsTotal() {
		return fMorecardsTotal;
	}

	public void setFMorecardsTotal(Integer fMorecardsTotal) {
		this.fMorecardsTotal = fMorecardsTotal;
	}
	
	@NotNull(message="f_morecards_grp1不能为空")
	public Integer getFMorecardsGrp1() {
		return fMorecardsGrp1;
	}

	public void setFMorecardsGrp1(Integer fMorecardsGrp1) {
		this.fMorecardsGrp1 = fMorecardsGrp1;
	}
	
	@NotNull(message="f_morecards_grp2不能为空")
	public Integer getFMorecardsGrp2() {
		return fMorecardsGrp2;
	}

	public void setFMorecardsGrp2(Integer fMorecardsGrp2) {
		this.fMorecardsGrp2 = fMorecardsGrp2;
	}
	
	@NotNull(message="f_morecards_grp3不能为空")
	public Integer getFMorecardsGrp3() {
		return fMorecardsGrp3;
	}

	public void setFMorecardsGrp3(Integer fMorecardsGrp3) {
		this.fMorecardsGrp3 = fMorecardsGrp3;
	}
	
	@NotNull(message="f_morecards_grp4不能为空")
	public Integer getFMorecardsGrp4() {
		return fMorecardsGrp4;
	}

	public void setFMorecardsGrp4(Integer fMorecardsGrp4) {
		this.fMorecardsGrp4 = fMorecardsGrp4;
	}
	
	@NotNull(message="f_morecards_grp5不能为空")
	public Integer getFMorecardsGrp5() {
		return fMorecardsGrp5;
	}

	public void setFMorecardsGrp5(Integer fMorecardsGrp5) {
		this.fMorecardsGrp5 = fMorecardsGrp5;
	}
	
	@NotNull(message="f_morecards_grp6不能为空")
	public Integer getFMorecardsGrp6() {
		return fMorecardsGrp6;
	}

	public void setFMorecardsGrp6(Integer fMorecardsGrp6) {
		this.fMorecardsGrp6 = fMorecardsGrp6;
	}
	
	@NotNull(message="f_morecards_grp7不能为空")
	public Integer getFMorecardsGrp7() {
		return fMorecardsGrp7;
	}

	public void setFMorecardsGrp7(Integer fMorecardsGrp7) {
		this.fMorecardsGrp7 = fMorecardsGrp7;
	}
	
	@NotNull(message="f_morecards_grp8不能为空")
	public Integer getFMorecardsGrp8() {
		return fMorecardsGrp8;
	}

	public void setFMorecardsGrp8(Integer fMorecardsGrp8) {
		this.fMorecardsGrp8 = fMorecardsGrp8;
	}
	
	@NotNull(message="f_morecards_option不能为空")
	public Integer getFMorecardsOption() {
		return fMorecardsOption;
	}

	public void setFMorecardsOption(Integer fMorecardsOption) {
		this.fMorecardsOption = fMorecardsOption;
	}
	
	@NotNull(message="f_firstcard_enabled不能为空")
	public Integer getFFirstcardEnabled() {
		return fFirstcardEnabled;
	}

	public void setFFirstcardEnabled(Integer fFirstcardEnabled) {
		this.fFirstcardEnabled = fFirstcardEnabled;
	}
	
	@NotNull(message="f_firstcard_weekday不能为空")
	public Integer getFFirstcardWeekday() {
		return fFirstcardWeekday;
	}

	public void setFFirstcardWeekday(Integer fFirstcardWeekday) {
		this.fFirstcardWeekday = fFirstcardWeekday;
	}
	
	@Length(min=1, max=200, message="f_firstcard_beginhms长度必须介于 1 和 200 之间")
	public String getFFirstcardBeginhms() {
		return fFirstcardBeginhms;
	}

	public void setFFirstcardBeginhms(String fFirstcardBeginhms) {
		this.fFirstcardBeginhms = fFirstcardBeginhms;
	}
	
	@NotNull(message="f_firstcard_begincontrol不能为空")
	public Integer getFFirstcardBegincontrol() {
		return fFirstcardBegincontrol;
	}

	public void setFFirstcardBegincontrol(Integer fFirstcardBegincontrol) {
		this.fFirstcardBegincontrol = fFirstcardBegincontrol;
	}
	
	@Length(min=1, max=200, message="f_firstcard_endhms长度必须介于 1 和 200 之间")
	public String getFFirstcardEndhms() {
		return fFirstcardEndhms;
	}

	public void setFFirstcardEndhms(String fFirstcardEndhms) {
		this.fFirstcardEndhms = fFirstcardEndhms;
	}
	
	@NotNull(message="f_firstcard_endcontrol不能为空")
	public Integer getFFirstcardEndcontrol() {
		return fFirstcardEndcontrol;
	}

	public void setFFirstcardEndcontrol(Integer fFirstcardEndcontrol) {
		this.fFirstcardEndcontrol = fFirstcardEndcontrol;
	}
	
}