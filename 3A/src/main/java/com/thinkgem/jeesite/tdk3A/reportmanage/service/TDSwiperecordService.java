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
import com.thinkgem.jeesite.tdk3A.reportmanage.dao.TDSwiperecordDao;
import com.thinkgem.jeesite.tdk3A.reportmanage.entity.TDSwiperecord;

/**
 * 门禁刷卡记录Service
 * @author xubaifu
 * @version 2017-05-15
 */
@Service
@Transactional(readOnly = true)
public class TDSwiperecordService extends CrudService<TDSwiperecordDao, TDSwiperecord> {

	@Resource
	private TDSwiperecordDao tDSwiperecordDao;
	
	public TDSwiperecord get(String id) {
		return super.get(id);
	}
	
	public List<TDSwiperecord> findList(TDSwiperecord tDSwiperecord) {
		return super.findList(tDSwiperecord);
	}
	
	public Page<TDSwiperecord> findPage(Page<TDSwiperecord> page, TDSwiperecord tDSwiperecord) {
		return super.findPage(page, tDSwiperecord);
	}
	
	@Transactional(readOnly = false)
	public void save(TDSwiperecord tDSwiperecord) {
		super.save(tDSwiperecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(TDSwiperecord tDSwiperecord) {
		super.delete(tDSwiperecord);
	}
	/**
	 * 查询刷卡信息（门禁系统）
	 * @param tDSwiperecord
	 * @return
	 */
	public List<TDSwiperecord> findListFromEhr(TDSwiperecord tDSwiperecord){
		return tDSwiperecordDao.findListFromEhr(tDSwiperecord);
	}
	
}