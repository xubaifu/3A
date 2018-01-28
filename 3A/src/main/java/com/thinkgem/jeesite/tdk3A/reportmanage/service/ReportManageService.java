/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.reportmanage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.tdk3A.reportmanage.dao.ReportManageDao;
import com.thinkgem.jeesite.tdk3A.reportmanage.entity.ReportManage;

/**
 * 报表统计Service
 * @author baifu
 * @version 2017-03-05
 */
@Service
@Transactional(readOnly = true)
public class ReportManageService extends CrudService<ReportManageDao, ReportManage> {

	@Resource
	private ReportManageDao reportManageDao;
	/*public ReportManage get(String id) {
		return super.get(id);
	}
	
	public List<ReportManage> findList(ReportManage reportManage) {
		return super.findList(reportManage);
	}
	
	public Page<ReportManage> findPage(Page<ReportManage> page, ReportManage reportManage) {
		return super.findPage(page, reportManage);
	}
	
	@Transactional(readOnly = false)
	public void save(ReportManage reportManage) {
		super.save(reportManage);
	}
	
	@Transactional(readOnly = false)
	public void delete(ReportManage reportManage) {
		super.delete(reportManage);
	}*/
	//餐卡明细查询
	public Page<ReportManage> findPage(Page<ReportManage> page, ReportManage reportManage) {
		return super.findPage(page, reportManage);
	}
	//餐卡个人统计查询
	public Page<ReportManage> findPersonList(Page<ReportManage> page, ReportManage reportManage) {
		return findPageList(page, reportManage);
	}
	//餐卡个人统计（计算汇总）
	public List<ReportManage> sum(ReportManage reportManage) {
		return reportManageDao.sum(reportManage);
	}
	public Page<ReportManage> findPageList(Page<ReportManage> page, ReportManage entity) {
		entity.setPage(page);
		page.setList(reportManageDao.findPersonList(entity));
		return page;
	}
	
}