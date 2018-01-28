/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.systemmanagement.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 考勤机数据同步Entity
 * @author xubaifu
 * @version 2017-03-21
 */
public class AttendanceMachineDSEntity extends DataEntity<AttendanceMachineDSEntity> {
	
	private static final long serialVersionUID = 1L;
	private String bh;		// bh
	private String mc;		// mc
	private String bz;		// bz
	private String ip;		// ip
	private Integer com;		// com
	private Integer port;		// port
	private Integer btl;		// btl
	private String mm;		// mm
	private String zt;		// zt
	private String sj;		// sj
	private String xs;		// xs
	private String mj;		// mj
	private String pztj;		// pztj
	private String pzlj;		// pzlj
	private Integer lx;		// lx
	private Integer yzfs;		// yzfs
	private String glyNo;		// gly_no
	private String glyPass;		// gly_pass
	private String mac;		// mac
	private String devXs;		// dev_xs
	private String devLx;		// dev_lx
	private String kmry;		// kmry
	private String dyip;		// dyip
	private Integer jksz;		// jksz
	private Integer tjkq;		// tjkq
	private Date onLine;		// on_line
	private Integer runState;		// run_state
	private Integer devLb;		// dev_lb
	private String devVersion;		// dev_version
	private Integer devUp;		// dev_up
	private Integer maxFlowNo;		// max_flow_no
	private Integer devLock;		// dev_lock
	
	private String num;
	public AttendanceMachineDSEntity() {
		super();
	}

	public AttendanceMachineDSEntity(String id){
		super(id);
	}

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getCom() {
		return com;
	}

	public void setCom(Integer com) {
		this.com = com;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getBtl() {
		return btl;
	}

	public void setBtl(Integer btl) {
		this.btl = btl;
	}

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getSj() {
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

	public String getXs() {
		return xs;
	}

	public void setXs(String xs) {
		this.xs = xs;
	}

	public String getMj() {
		return mj;
	}

	public void setMj(String mj) {
		this.mj = mj;
	}

	public String getPztj() {
		return pztj;
	}

	public void setPztj(String pztj) {
		this.pztj = pztj;
	}

	public String getPzlj() {
		return pzlj;
	}

	public void setPzlj(String pzlj) {
		this.pzlj = pzlj;
	}

	public Integer getLx() {
		return lx;
	}

	public void setLx(Integer lx) {
		this.lx = lx;
	}

	public Integer getYzfs() {
		return yzfs;
	}

	public void setYzfs(Integer yzfs) {
		this.yzfs = yzfs;
	}

	public String getGlyNo() {
		return glyNo;
	}

	public void setGlyNo(String glyNo) {
		this.glyNo = glyNo;
	}

	public String getGlyPass() {
		return glyPass;
	}

	public void setGlyPass(String glyPass) {
		this.glyPass = glyPass;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getDevXs() {
		return devXs;
	}

	public void setDevXs(String devXs) {
		this.devXs = devXs;
	}

	public String getDevLx() {
		return devLx;
	}

	public void setDevLx(String devLx) {
		this.devLx = devLx;
	}

	public String getKmry() {
		return kmry;
	}

	public void setKmry(String kmry) {
		this.kmry = kmry;
	}

	public String getDyip() {
		return dyip;
	}

	public void setDyip(String dyip) {
		this.dyip = dyip;
	}

	public Integer getJksz() {
		return jksz;
	}

	public void setJksz(Integer jksz) {
		this.jksz = jksz;
	}

	public Integer getTjkq() {
		return tjkq;
	}

	public void setTjkq(Integer tjkq) {
		this.tjkq = tjkq;
	}

	public Date getOnLine() {
		return onLine;
	}

	public void setOnLine(Date onLine) {
		this.onLine = onLine;
	}

	public Integer getRunState() {
		return runState;
	}

	public void setRunState(Integer runState) {
		this.runState = runState;
	}

	public Integer getDevLb() {
		return devLb;
	}

	public void setDevLb(Integer devLb) {
		this.devLb = devLb;
	}

	public String getDevVersion() {
		return devVersion;
	}

	public void setDevVersion(String devVersion) {
		this.devVersion = devVersion;
	}

	public Integer getDevUp() {
		return devUp;
	}

	public void setDevUp(Integer devUp) {
		this.devUp = devUp;
	}

	public Integer getMaxFlowNo() {
		return maxFlowNo;
	}

	public void setMaxFlowNo(Integer maxFlowNo) {
		this.maxFlowNo = maxFlowNo;
	}

	public Integer getDevLock() {
		return devLock;
	}

	public void setDevLock(Integer devLock) {
		this.devLock = devLock;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	
}