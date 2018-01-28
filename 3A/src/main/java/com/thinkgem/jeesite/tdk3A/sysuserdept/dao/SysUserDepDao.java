/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.sysuserdept.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.tdk3A.sysuserdept.entity.SysUserDep;

/**
 * 人与部门记录表DAO接口
 * @author zh
 * @version 2017-04-05
 */
@MyBatisDao
public interface SysUserDepDao extends CrudDao<SysUserDep> {
	
}