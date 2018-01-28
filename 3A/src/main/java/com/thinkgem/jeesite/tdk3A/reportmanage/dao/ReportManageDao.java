/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.reportmanage.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.tdk3A.reportmanage.entity.ReportManage;

/**
 * 报表统计DAO接口
 * @author baifu
 * @version 2017-03-05
 */
@MyBatisDao
public interface ReportManageDao extends CrudDao<ReportManage> {
	
	public List<ReportManage> findPersonList(ReportManage reportManage);
	public List<ReportManage> sum(ReportManage reportManage);
	
}