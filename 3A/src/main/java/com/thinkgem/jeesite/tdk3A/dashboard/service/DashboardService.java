/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.dashboard.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.tdk3A.dashboard.dao.DashboardDao;
import com.thinkgem.jeesite.tdk3A.dashboard.entity.DashboardEntity;

/**
 * 主页仪表盘Service
 * @author baifu
 * @version 2017-03-03
 */
@Service
@Transactional(readOnly = true)
public class DashboardService extends CrudService<DashboardDao, DashboardEntity> {
	
	@Resource
	private DashboardDao dashboardDao;
	/**
	 * 获取餐卡消费情况
	 * @param dashboardEntity
	 * @return
	 */
	public List<DashboardEntity> getMealsStatistics(DashboardEntity dashboardEntity){
		return dashboardDao.getMealsStatistics(dashboardEntity);
	}
	
}