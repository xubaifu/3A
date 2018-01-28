/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.systemmanagement.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.tdk3A.systemmanagement.entity.AccessControlDSEntity;

/**
 * 门禁控制器数据同步DAO接口
 * @author xubaifu
 * @version 2017-03-21
 */
@MyBatisDao
public interface AccessControlDSDao extends CrudDao<AccessControlDSEntity> {
	/**
	 * 获取所有门禁控制器信息（通过人事系统门禁控制器信息表获取）
	 * @return
	 */
	List<AccessControlDSEntity> getAllController(AccessControlDSEntity entity);
	/**
	 * 根据条件获取门禁控制器信息
	 * @return
	 */
	List<AccessControlDSEntity> getControllerById(AccessControlDSEntity entity);
	
	/**
	 * 添加门禁控制器信息（插入到3A系统中）
	 */
	void addController(AccessControlDSEntity entity);
	/**
	 * 修改门禁控制器信息（插入到3A系统中）
	 */
	void updateController(AccessControlDSEntity entity);
	
	/**
	 * 根据更新日期查询门禁控制器信息
	 * @param entity
	 * @return
	 */
	List<AccessControlDSEntity> getAllControllerByUpdate(AccessControlDSEntity entity);
	/**
	 * 删除门禁控制器信息
	 * @param entity
	 */
	void deleteController(AccessControlDSEntity entity);
}