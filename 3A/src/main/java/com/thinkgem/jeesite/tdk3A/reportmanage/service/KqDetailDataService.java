/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.reportmanage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.tdk3A.reportmanage.dao.KqDetailDataDao;
import com.thinkgem.jeesite.tdk3A.systemmanagement.entity.KqOriginalityData;

/**
 * 考勤信息同步Service
 * @author xubaifu
 * @version 2017-05-01
 */
@Service
@Transactional(readOnly = true)
public class KqDetailDataService extends CrudService<KqDetailDataDao, KqOriginalityData> {

	public KqOriginalityData get(String id) {
		return super.get(id);
	}
	
	public List<KqOriginalityData> findList(KqOriginalityData kqOriginalityData) {
		return super.findList(kqOriginalityData);
	}
	
	public Page<KqOriginalityData> findPage(Page<KqOriginalityData> page, KqOriginalityData kqOriginalityData) {
		return super.findPage(page, kqOriginalityData);
	}
	
	@Transactional(readOnly = false)
	public void save(KqOriginalityData kqOriginalityData) {
		super.save(kqOriginalityData);
	}
	
	@Transactional(readOnly = false)
	public void delete(KqOriginalityData kqOriginalityData) {
		super.delete(kqOriginalityData);
	}
	
}