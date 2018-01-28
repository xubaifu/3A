/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.systemmanagement.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.tdk3A.systemmanagement.entity.KqOriginalityData;

/**
 * 考勤信息同步DAO接口
 * @author xubaifu
 * @version 2017-05-01
 */
@MyBatisDao
public interface KqOriginalityDataDao extends CrudDao<KqOriginalityData> {
	//查询EHR系统中的考勤明细
	public List<KqOriginalityData> getKqDetailFromEHR(KqOriginalityData kqOriginalityData);
	//查询3A系统中的考勤明细
	public List<KqOriginalityData> getKqDetailFrom3A(KqOriginalityData kqOriginalityData);
	//新增考勤明细
	public void addKqDetail(KqOriginalityData kqOriginalityData);
	//更新考勤明细
	public void updateKqDetail(KqOriginalityData kqOriginalityData);
	//根据ID获取考勤明细条数
	public List<KqOriginalityData> getKqDetailFromById(KqOriginalityData kqOriginalityData);
	
}