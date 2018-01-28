/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.systemmanagement.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.tdk3A.systemmanagement.dao.AccessControlDSDao;
import com.thinkgem.jeesite.tdk3A.systemmanagement.entity.AccessControlDSEntity;

/**
 * 门禁控制器数据同步Service
 * @author xubaifu
 * @version 2017-03-21
 */
@Service
//@Transactional(readOnly = true)
public class AccessControlDSService extends CrudService<AccessControlDSDao, AccessControlDSEntity> {
	@Resource
	private AccessControlDSDao accessControlDSDao;
	/**
	 * 查新人事系统中的门禁控制器信息
	 * @param params
	 * @return
	 */
	public List<AccessControlDSEntity> getControllerFromEHR(AccessControlDSEntity entity){
		List<AccessControlDSEntity> list = new ArrayList<AccessControlDSEntity>();
		list = accessControlDSDao.getAllController(entity);
		return list;
	}
	/**
	 * 查询3A系统下的门禁控制器信息
	 * @return
	 */
	public List<AccessControlDSEntity> getControllerFrom3A(AccessControlDSEntity entity){
		List<AccessControlDSEntity> list = new ArrayList<AccessControlDSEntity>();
		list = accessControlDSDao.getControllerById(entity);
		return list;
	}
	/**
	 * 新增3A系统中的门禁控制器信息
	 * @param entity
	 */
	public void addController(AccessControlDSEntity entity){
		accessControlDSDao.addController(entity);
	}
	/**
	 * 修改3A系统中的门禁控制器信息
	 * @param entity
	 */
	public void updateController(AccessControlDSEntity entity){
		accessControlDSDao.updateController(entity);
	}
	/**
	 * 根据更新日期查询门禁控制器信息
	 * @param entity
	 * @return
	 */
	public List<AccessControlDSEntity> getAllControllerByUpdate(AccessControlDSEntity entity){
		List<AccessControlDSEntity> list = new ArrayList<AccessControlDSEntity>();
		list = accessControlDSDao.getAllControllerByUpdate(entity);
		return list;
	}
	/**
	 * 删除门禁控制器信息
	 * @param entity
	 */
	
	public void deleteController(AccessControlDSEntity entity){
		accessControlDSDao.deleteController(entity);
	}
	
	/*public AccessControlDSEntity get(String id) {
		return super.get(id);
	}
	
	public List<AccessControlDSEntity> findList(AccessControlDSEntity accessControlDS) {
		return super.findList(accessControlDS);
	}
	
	public Page<AccessControlDSEntity> findPage(Page<AccessControlDSEntity> page, AccessControlDSEntity accessControlDS) {
		return super.findPage(page, accessControlDS);
	}
	
	@Transactional(readOnly = false)
	public void save(AccessControlDSEntity accessControlDS) {
		super.save(accessControlDS);
	}
	
	@Transactional(readOnly = false)
	public void delete(AccessControlDSEntity accessControlDS) {
		super.delete(accessControlDS);
	}*/
	
}