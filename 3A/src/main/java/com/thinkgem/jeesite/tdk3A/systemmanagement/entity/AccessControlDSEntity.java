/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.systemmanagement.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 门禁控制器数据同步Entity
 * @author xubaifu
 * @version 2017-03-21
 */
public class AccessControlDSEntity extends DataEntity<AccessControlDSEntity> {
	
	private static final long serialVersionUID = 1L;
	private Integer fControllerid;		// f_controllerid
	private Integer fControllerno;		// f_controllerno
	private Integer fControllersn;		// f_controllersn
	private Integer fEnabled;		// f_enabled
	private String fIp;		// f_ip
	private Integer fPort;		// f_port
	private String fNote;		// f_note
	private String fDoornames;		// f_doornames
	private Integer fZoneid;		// f_zoneid
	private Integer fAntiback;		// f_antiback
	private Integer fInterlock;		// f_interlock
	private Integer fMorecardsGoinout;		// f_morecards_goinout
	private Integer fDoorinvalidopen;		// f_doorinvalidopen
	private Integer fDooropentoolong;		// f_dooropentoolong
	private Integer fForcewarn;		// f_forcewarn
	private Integer fInvalidcardwarn;		// f_invalidcardwarn
	private String fPeripheralcontrol;		// f_peripheralcontrol
	private String fLastdeladddatetime;		// f_lastdeladddatetime
	private Integer fLastdeladdconsuemrstotal;		// f_lastdeladdconsuemrstotal
	private String fLastdeladdanduploaddatetime;		// f_lastdeladdanduploaddatetime
	private Integer fLastdeladdanduploadconsuemrs;		// f_lastdeladdanduploadconsuemrs
	private String fLastconsoleuploaddatetime;		// f_lastconsoleuploaddatetime
	private Integer fLastconsoleuploadconsuemrsto;		// f_lastconsoleuploadconsuemrsto
	private Integer fLastconsoleuploadprivilege;		// f_lastconsoleuploadprivilege
	private Integer fLastconsoleuploadvalidprivil;		// f_lastconsoleuploadvalidprivil
	private String fLastDelAddDateTime;
	private String createTime;
	public String num;
	
	public AccessControlDSEntity() {
		super();
	}

	public AccessControlDSEntity(String id){
		super(id);
	}

	public Integer getfControllerid() {
		return fControllerid;
	}

	public void setfControllerid(Integer fControllerid) {
		this.fControllerid = fControllerid;
	}

	public Integer getfControllerno() {
		return fControllerno;
	}

	public void setfControllerno(Integer fControllerno) {
		this.fControllerno = fControllerno;
	}

	public Integer getfControllersn() {
		return fControllersn;
	}

	public void setfControllersn(Integer fControllersn) {
		this.fControllersn = fControllersn;
	}

	public Integer getfEnabled() {
		return fEnabled;
	}

	public void setfEnabled(Integer fEnabled) {
		this.fEnabled = fEnabled;
	}

	public String getfIp() {
		return fIp;
	}

	public void setfIp(String fIp) {
		this.fIp = fIp;
	}

	public Integer getfPort() {
		return fPort;
	}

	public void setfPort(Integer fPort) {
		this.fPort = fPort;
	}

	public String getfNote() {
		return fNote;
	}

	public void setfNote(String fNote) {
		this.fNote = fNote;
	}

	public String getfDoornames() {
		return fDoornames;
	}

	public void setfDoornames(String fDoornames) {
		this.fDoornames = fDoornames;
	}

	public Integer getfZoneid() {
		return fZoneid;
	}

	public void setfZoneid(Integer fZoneid) {
		this.fZoneid = fZoneid;
	}

	public Integer getfAntiback() {
		return fAntiback;
	}

	public void setfAntiback(Integer fAntiback) {
		this.fAntiback = fAntiback;
	}

	public Integer getfInterlock() {
		return fInterlock;
	}

	public void setfInterlock(Integer fInterlock) {
		this.fInterlock = fInterlock;
	}

	public Integer getfMorecardsGoinout() {
		return fMorecardsGoinout;
	}

	public void setfMorecardsGoinout(Integer fMorecardsGoinout) {
		this.fMorecardsGoinout = fMorecardsGoinout;
	}

	public Integer getfDoorinvalidopen() {
		return fDoorinvalidopen;
	}

	public void setfDoorinvalidopen(Integer fDoorinvalidopen) {
		this.fDoorinvalidopen = fDoorinvalidopen;
	}

	public Integer getfDooropentoolong() {
		return fDooropentoolong;
	}

	public void setfDooropentoolong(Integer fDooropentoolong) {
		this.fDooropentoolong = fDooropentoolong;
	}

	public Integer getfForcewarn() {
		return fForcewarn;
	}

	public void setfForcewarn(Integer fForcewarn) {
		this.fForcewarn = fForcewarn;
	}

	public Integer getfInvalidcardwarn() {
		return fInvalidcardwarn;
	}

	public void setfInvalidcardwarn(Integer fInvalidcardwarn) {
		this.fInvalidcardwarn = fInvalidcardwarn;
	}

	public String getfPeripheralcontrol() {
		return fPeripheralcontrol;
	}

	public void setfPeripheralcontrol(String fPeripheralcontrol) {
		this.fPeripheralcontrol = fPeripheralcontrol;
	}

	public String getfLastdeladddatetime() {
		return fLastdeladddatetime;
	}

	public void setfLastdeladddatetime(String fLastdeladddatetime) {
		this.fLastdeladddatetime = fLastdeladddatetime;
	}

	public Integer getfLastdeladdconsuemrstotal() {
		return fLastdeladdconsuemrstotal;
	}

	public void setfLastdeladdconsuemrstotal(Integer fLastdeladdconsuemrstotal) {
		this.fLastdeladdconsuemrstotal = fLastdeladdconsuemrstotal;
	}

	public String getfLastdeladdanduploaddatetime() {
		return fLastdeladdanduploaddatetime;
	}

	public void setfLastdeladdanduploaddatetime(String fLastdeladdanduploaddatetime) {
		this.fLastdeladdanduploaddatetime = fLastdeladdanduploaddatetime;
	}

	public Integer getfLastdeladdanduploadconsuemrs() {
		return fLastdeladdanduploadconsuemrs;
	}

	public void setfLastdeladdanduploadconsuemrs(
			Integer fLastdeladdanduploadconsuemrs) {
		this.fLastdeladdanduploadconsuemrs = fLastdeladdanduploadconsuemrs;
	}

	public String getfLastconsoleuploaddatetime() {
		return fLastconsoleuploaddatetime;
	}

	public void setfLastconsoleuploaddatetime(String fLastconsoleuploaddatetime) {
		this.fLastconsoleuploaddatetime = fLastconsoleuploaddatetime;
	}

	public Integer getfLastconsoleuploadconsuemrsto() {
		return fLastconsoleuploadconsuemrsto;
	}

	public void setfLastconsoleuploadconsuemrsto(
			Integer fLastconsoleuploadconsuemrsto) {
		this.fLastconsoleuploadconsuemrsto = fLastconsoleuploadconsuemrsto;
	}

	public Integer getfLastconsoleuploadprivilege() {
		return fLastconsoleuploadprivilege;
	}

	public void setfLastconsoleuploadprivilege(Integer fLastconsoleuploadprivilege) {
		this.fLastconsoleuploadprivilege = fLastconsoleuploadprivilege;
	}

	public Integer getfLastconsoleuploadvalidprivil() {
		return fLastconsoleuploadvalidprivil;
	}

	public void setfLastconsoleuploadvalidprivil(
			Integer fLastconsoleuploadvalidprivil) {
		this.fLastconsoleuploadvalidprivil = fLastconsoleuploadvalidprivil;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getfLastDelAddDateTime() {
		return fLastDelAddDateTime;
	}

	public void setfLastDelAddDateTime(String fLastDelAddDateTime) {
		this.fLastDelAddDateTime = fLastDelAddDateTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}