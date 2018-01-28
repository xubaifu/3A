/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.systemmanagement.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.tdk3A.systemmanagement.entity.MealCardDS;

/**
 * 餐卡信息同步DAO接口
 * @author xubaifu
 * @version 2017-04-16
 */
@MyBatisDao
public interface MealCardDSDao extends CrudDao<MealCardDS> {
	//获取EHR系统中的餐卡明细
	public List<MealCardDS> getAllMealCard(MealCardDS entity);
	//3A系统中添加餐卡明细
	public void addMealCard(MealCardDS entity);
	//3A系统中更新餐卡明细
	public void updateMealCard(MealCardDS entity);
	//3A系统中删除餐卡明细
	public void deleteMealCard(MealCardDS entity);
	
	//根据条件获取餐卡信息
	List<MealCardDS> getMealCardById(MealCardDS entity);
	
	
}