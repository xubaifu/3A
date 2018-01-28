/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.test.dao;


import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.tdk3A.test.entity.TestSysUser;

/**
 * 练习类DAO接口
 * @author baifu
 * @version 2017-03-03
 */
@MyBatisDao
public interface TestSysUserDao extends CrudDao<TestSysUser> {
	
}