/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.temporarycard.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.tdk3A.temporarycard.entity.TemporaryCardEntity;

/**
 * 临时卡管理DAO接口
 * @author baifu
 * @version 2017-03-05
 */
@MyBatisDao
public interface TemporaryCardDao extends CrudDao<TemporaryCardEntity> {
	//临时卡管理
	public void addTemporaryCard(TemporaryCardEntity entity);
	//根据工号获取foodid
	public List<TemporaryCardEntity> getFoodIdByNo(TemporaryCardEntity entity);
	
}