/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.tdk3A.test.dao.TestSysUserDao;
import com.thinkgem.jeesite.tdk3A.test.entity.TestSysUser;

/**
 * 练习类Service
 * @author baifu
 * @version 2017-03-03
 */
@Service
@Transactional(readOnly = true)
public class TestSysUserService extends CrudService<TestSysUserDao, TestSysUser> {

	public TestSysUser get(String id) {
		return super.get(id);
	}
	
	public List<TestSysUser> findList(TestSysUser testSysUser) {
		return super.findList(testSysUser);
	}
	
	public Page<TestSysUser> findPage(Page<TestSysUser> page, TestSysUser testSysUser) {
		return super.findPage(page, testSysUser);
	}
	
	@Transactional(readOnly = false)
	public void save(TestSysUser testSysUser) {
		super.save(testSysUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(TestSysUser testSysUser) {
		super.delete(testSysUser);
	}
	
}