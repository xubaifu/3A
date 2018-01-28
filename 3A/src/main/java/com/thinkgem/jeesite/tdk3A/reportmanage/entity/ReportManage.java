/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.reportmanage.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 报表统计Entity
 * @author baifu
 * @version 2017-03-05
 */
public class ReportManage extends DataEntity<ReportManage> {
	

	private static final long serialVersionUID = 1L;
	private Long idKey;		// id_key
	private Integer personId;		// person_id
	private String systemNo;		// system_no
	private Integer consumeMode;		// consume_mode
	private Date consumeTime;		// consume_time
	private Double consumeFund;		// consume_fund
	private Double beforeFund;		// before_fund
	private Integer consumeCopy;		// consume_copy
	private Long consumeTimelen;		// consume_timelen
	private Long consumeCapacity;		// consume_capacity
	private String repastNo;		// repast_no
	private Integer wareId;		// ware_id
	private String mocNo;		// moc_no
	private String comNo;		// com_no
	private Double cardBalance;		// card_balance
	private Double dbBalance;		// db_balance
	private Double cardSmallaccount;		// card_smallaccount
	private Integer dataType;		// data_type
	private String remark;		// remark
	private Date downloadTime;		// download_time
	
	private String num;
	
	private String officeId;
	private String officeName;
	private String name;
	private String no;
	
	private String subsidy;//补助
	private String allmoney;//该餐次总钱数
	private String times;//次数
	private String person;//个人承担
	private String mealType;//餐别
	
	private Date startTime;
	private Date endTime;
	
	
	public ReportManage() {
		super();
	}

	public ReportManage(String id){
		super(id);
	}

	@NotNull(message="id_key不能为空")
	public Long getIdKey() {
		return idKey;
	}

	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}
	
	@NotNull(message="person_id不能为空")
	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	
	@Length(min=1, max=20, message="system_no长度必须介于 1 和 20 之间")
	public String getSystemNo() {
		return systemNo;
	}

	public void setSystemNo(String systemNo) {
		this.systemNo = systemNo;
	}
	
	@NotNull(message="consume_mode不能为空")
	public Integer getConsumeMode() {
		return consumeMode;
	}

	public void setConsumeMode(Integer consumeMode) {
		this.consumeMode = consumeMode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="consume_time不能为空")
	public Date getConsumeTime() {
		return consumeTime;
	}

	public void setConsumeTime(Date consumeTime) {
		this.consumeTime = consumeTime;
	}
	
	@NotNull(message="consume_fund不能为空")
	public Double getConsumeFund() {
		return consumeFund;
	}

	public void setConsumeFund(Double consumeFund) {
		this.consumeFund = consumeFund;
	}
	
	@NotNull(message="before_fund不能为空")
	public Double getBeforeFund() {
		return beforeFund;
	}

	public void setBeforeFund(Double beforeFund) {
		this.beforeFund = beforeFund;
	}
	
	public Integer getConsumeCopy() {
		return consumeCopy;
	}

	public void setConsumeCopy(Integer consumeCopy) {
		this.consumeCopy = consumeCopy;
	}
	
	public Long getConsumeTimelen() {
		return consumeTimelen;
	}

	public void setConsumeTimelen(Long consumeTimelen) {
		this.consumeTimelen = consumeTimelen;
	}
	
	public Long getConsumeCapacity() {
		return consumeCapacity;
	}

	public void setConsumeCapacity(Long consumeCapacity) {
		this.consumeCapacity = consumeCapacity;
	}
	
	@Length(min=0, max=6, message="repast_no长度必须介于 0 和 6 之间")
	public String getRepastNo() {
		return repastNo;
	}

	public void setRepastNo(String repastNo) {
		this.repastNo = repastNo;
	}
	
	public Integer getWareId() {
		return wareId;
	}

	public void setWareId(Integer wareId) {
		this.wareId = wareId;
	}
	
	@Length(min=1, max=12, message="moc_no长度必须介于 1 和 12 之间")
	public String getMocNo() {
		return mocNo;
	}

	public void setMocNo(String mocNo) {
		this.mocNo = mocNo;
	}
	
	@Length(min=0, max=12, message="com_no长度必须介于 0 和 12 之间")
	public String getComNo() {
		return comNo;
	}

	public void setComNo(String comNo) {
		this.comNo = comNo;
	}
	
	@NotNull(message="card_balance不能为空")
	public Double getCardBalance() {
		return cardBalance;
	}

	public void setCardBalance(Double cardBalance) {
		this.cardBalance = cardBalance;
	}
	
	public Double getDbBalance() {
		return dbBalance;
	}

	public void setDbBalance(Double dbBalance) {
		this.dbBalance = dbBalance;
	}
	
	public Double getCardSmallaccount() {
		return cardSmallaccount;
	}

	public void setCardSmallaccount(Double cardSmallaccount) {
		this.cardSmallaccount = cardSmallaccount;
	}
	
	@NotNull(message="data_type不能为空")
	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	
	@Length(min=0, max=40, message="remark长度必须介于 0 和 40 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDownloadTime() {
		return downloadTime;
	}

	public void setDownloadTime(Date downloadTime) {
		this.downloadTime = downloadTime;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
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

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getSubsidy() {
		return subsidy;
	}

	public void setSubsidy(String subsidy) {
		this.subsidy = subsidy;
	}

	public String getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(String allmoney) {
		this.allmoney = allmoney;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
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