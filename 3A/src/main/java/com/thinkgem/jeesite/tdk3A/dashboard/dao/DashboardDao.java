/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.dashboard.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.tdk3A.dashboard.entity.DashboardEntity;

/**
 * 主页仪表盘DAO接口
 * @author baifu
 * @version 2017-03-03
 */
@MyBatisDao
public interface DashboardDao extends CrudDao<DashboardEntity> {
	
	public List<DashboardEntity> getMealsStatistics(DashboardEntity dashboardEntity);
	
}