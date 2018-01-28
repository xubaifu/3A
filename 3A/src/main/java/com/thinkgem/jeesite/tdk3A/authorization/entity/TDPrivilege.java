/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.authorization.entity;

import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * Entity
 * @author xubaifu
 * @version 2017-03-27
 */
public class TDPrivilege extends DataEntity<TDPrivilege> {
	
	private static final long serialVersionUID = 1L;
	private Integer fPrivilegerecid;		// f_privilegerecid
	private Integer fDoorid;		// f_doorid
	private Integer fControlsegid;		// f_controlsegid
	private Integer fConsumerid;		// f_consumerid
	private Integer fControllerid;		// f_controllerid
	private Integer fDoorno;		// f_doorno
	
	public TDPrivilege() {
		super();
	}

	public TDPrivilege(String id){
		super(id);
	}

	@NotNull(message="f_privilegerecid不能为空")
	public Integer getFPrivilegerecid() {
		return fPrivilegerecid;
	}

	public void setFPrivilegerecid(Integer fPrivilegerecid) {
		this.fPrivilegerecid = fPrivilegerecid;
	}
	
	@NotNull(message="f_doorid不能为空")
	public Integer getFDoorid() {
		return fDoorid;
	}

	public void setFDoorid(Integer fDoorid) {
		this.fDoorid = fDoorid;
	}
	
	@NotNull(message="f_controlsegid不能为空")
	public Integer getFControlsegid() {
		return fControlsegid;
	}

	public void setFControlsegid(Integer fControlsegid) {
		this.fControlsegid = fControlsegid;
	}
	
	@NotNull(message="f_consumerid不能为空")
	public Integer getFConsumerid() {
		return fConsumerid;
	}

	public void setFConsumerid(Integer fConsumerid) {
		this.fConsumerid = fConsumerid;
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
	
}