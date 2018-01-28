/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.dashboard.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 主页仪表盘Entity
 * @author baifu
 * @version 2017-03-03
 */
public class DashboardEntity extends DataEntity<DashboardEntity> {
	
	private static final long serialVersionUID = 1L;
	
	private String mealType;
	private String date;
	private String times;
	private String personCost;
	private String officeCost;
	private String companyCost;
	private String startTime;
	private String endTime;
	public String getMealType() {
		return mealType;
	}
	public void setMealType(String mealType) {
		this.mealType = mealType;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getPersonCost() {
		return personCost;
	}
	public void setPersonCost(String personCost) {
		this.personCost = personCost;
	}
	public String getOfficeCost() {
		return officeCost;
	}
	public void setOfficeCost(String officeCost) {
		this.officeCost = officeCost;
	}
	public String getCompanyCost() {
		return companyCost;
	}
	public void setCompanyCost(String companyCost) {
		this.companyCost = companyCost;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}