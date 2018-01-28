/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.reportmanage.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.tdk3A.reportmanage.entity.TDSwiperecord;

/**
 * 门禁刷卡记录DAO接口
 * @author xubaifu
 * @version 2017-05-15
 */
@MyBatisDao
public interface TDSwiperecordDao extends CrudDao<TDSwiperecord> {
	public List<TDSwiperecord> findListFromEhr(TDSwiperecord tDSwiperecord);
}