/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.sysuserdept.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.tdk3A.sysuserdept.dao.SysUserDepDao;
import com.thinkgem.jeesite.tdk3A.sysuserdept.entity.SysUserDep;

/**
 * 人与部门记录表Service
 * @author zh
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class SysUserDepService extends CrudService<SysUserDepDao, SysUserDep> {

	public SysUserDep get(String id) {
		return super.get(id);
	}
	
	public List<SysUserDep> findList(SysUserDep sysUserDep) {
		return super.findList(sysUserDep);
	}
	
	public Page<SysUserDep> findPage(Page<SysUserDep> page, SysUserDep sysUserDep) {
		return super.findPage(page, sysUserDep);
	}
	
	@Transactional(readOnly = false)
	public void save(SysUserDep sysUserDep) {
		super.save(sysUserDep);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysUserDep sysUserDep) {
		super.delete(sysUserDep);
	}
	public void update(SysUserDep sysUserDep) {
		super.delete(sysUserDep);
	}
	
}