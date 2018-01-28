/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.systemmanagement.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.tdk3A.systemmanagement.dao.MealCardDSDao;
import com.thinkgem.jeesite.tdk3A.systemmanagement.entity.MealCardDS;

/**
 * 餐卡信息同步Service
 * @author xubaifu
 * @version 2017-04-16
 */
@Service
//@Transactional(readOnly = true)
public class MealCardDSService extends CrudService<MealCardDSDao, MealCardDS> {
	
	@Resource
	private MealCardDSDao mealCardDSDao;
	
	//获取EHR系统中的餐卡明细
	public List<MealCardDS> getAllMealCard(MealCardDS entity){
		return mealCardDSDao.getAllMealCard(entity);
	}
	//3A系统中添加餐卡明细
	public void addMealCard(MealCardDS entity){
		mealCardDSDao.addMealCard(entity);
	}
	//3A系统中更新餐卡明细
	public void updateMealCard(MealCardDS entity){
		mealCardDSDao.updateMealCard(entity);
	}
	//3A系统中删除餐卡明细
	public void deleteMealCard(MealCardDS entity){
		mealCardDSDao.deleteMealCard(entity);
	}
	
	/**
	 * 查询3A系统下的门禁控制器信息
	 * @return
	 */
	public List<MealCardDS> getMealCardById(MealCardDS entity){
		List<MealCardDS> list = new ArrayList<MealCardDS>();
		list = mealCardDSDao.getMealCardById(entity);
		return list;
	}

	/*public MealCardDS get(String id) {
		return super.get(id);
	}
	
	public List<MealCardDS> findList(MealCardDS mealCardDS) {
		return super.findList(mealCardDS);
	}
	
	public Page<MealCardDS> findPage(Page<MealCardDS> page, MealCardDS mealCardDS) {
		return super.findPage(page, mealCardDS);
	}
	
	@Transactional(readOnly = false)
	public void save(MealCardDS mealCardDS) {
		super.save(mealCardDS);
	}
	
	@Transactional(readOnly = false)
	public void delete(MealCardDS mealCardDS) {
		super.delete(mealCardDS);
	}*/
	
}