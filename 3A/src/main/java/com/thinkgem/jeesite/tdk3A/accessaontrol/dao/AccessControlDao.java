/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.accessaontrol.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.tdk3A.accessaontrol.entity.AccessControlEntity;

/**
 * 门禁管理DAO接口
 * @author baifu
 * @version 2017-03-12
 */
@MyBatisDao
public interface AccessControlDao extends CrudDao<AccessControlEntity> {
	//获取某一区域下的所有门禁控制器
	List<AccessControlEntity> getAllDoorByArea(AccessControlEntity accessControlEntity);
	//插入数据到dept_con
	void insertIntoDeptCon(AccessControlEntity accessControlEntity);
	//根据部门id(officeId)删除数据dept_con
	void deleteDeptConByOfficeId(AccessControlEntity accessControlEntity);
	//获取某一部门区域下的已有的门禁控制器
	List<AccessControlEntity> getAllDoorAreadySelect(AccessControlEntity accessControlEntity);
	//获取部门已有的门禁控制器
	//List<AccessControlEntity> findList(AccessControlEntity accessControlEntity);
	
	
}