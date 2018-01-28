/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.systemmanagement.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.tdk3A.systemmanagement.dao.SystemManagementDao;
import com.thinkgem.jeesite.tdk3A.systemmanagement.entity.SystemManagementEntity;

/**
 * 系统管理Service
 * @author baifu
 * @version 2017-03-07
 */
@Service
public class SystemManagementService extends CrudService<SystemManagementDao, SystemManagementEntity> {
	
	@Resource
	private SystemManagementDao systemManagementDao;
	/**
	 * 查新人事系统中的组织机构
	 * @param params
	 * @return
	 */
	public List<SystemManagementEntity> getOrganizationFromEHR(SystemManagementEntity entity){
		List<SystemManagementEntity> list = new ArrayList<SystemManagementEntity>();
		list = systemManagementDao.getAllOrganization(entity);
		return list;
	}
	/**
	 * 查询3A系统下的组织机构
	 * @return
	 */
	public List<SystemManagementEntity> getOrganizationFrom3A(SystemManagementEntity entity){
		List<SystemManagementEntity> list = new ArrayList<SystemManagementEntity>();
		list = systemManagementDao.getOrganizationById(entity);
		return list;
	}
	/**
	 * 新增3A系统中的组织结构
	 * @param entity
	 */
	public void addOrganization(SystemManagementEntity entity){
		systemManagementDao.addOrganization(entity);
	}
	/**
	 * 修改3A系统中的组织结构
	 * @param entity
	 */
	public void updateOrganization(SystemManagementEntity entity){
		systemManagementDao.updateOrganization(entity);
	}
	
	/**
	 * 修改3A系统中的组织结构
	 * @param entity
	 */
	public void getAllOfficeByUpdate(SystemManagementEntity entity){
		systemManagementDao.updateOrganization(entity);
	}
	
	/**
	 * 修改3A系统中的组织结构
	 * @param entity
	 */
	public List<SystemManagementEntity> getListByUpdate(SystemManagementEntity entity){
		List<SystemManagementEntity> list = new ArrayList<SystemManagementEntity>();
		list = systemManagementDao.getListByUpdate(entity);
		return list;
	}
	
	
	/*public SystemManagement get(String id) {
		return super.get(id);
	}
	
	public List<SystemManagement> findList(SystemManagement systemManagement) {
		return super.findList(systemManagement);
	}
	
	public Page<SystemManagement> findPage(Page<SystemManagement> page, SystemManagement systemManagement) {
		return super.findPage(page, systemManagement);
	}
	
	@Transactional(readOnly = false)
	public void save(SystemManagement systemManagement) {
		super.save(systemManagement);
	}
	
	@Transactional(readOnly = false)
	public void delete(SystemManagement systemManagement) {
		super.delete(systemManagement);
	}*/
	
	
}