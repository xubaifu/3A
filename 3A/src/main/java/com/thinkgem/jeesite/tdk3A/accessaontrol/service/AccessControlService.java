/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.accessaontrol.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.tdk3A.accessaontrol.dao.AccessControlDao;
import com.thinkgem.jeesite.tdk3A.accessaontrol.entity.AccessControlEntity;

/**
 * 门禁管理Service
 * @author baifu
 * @version 2017-03-12
 */
@Service
public class AccessControlService extends CrudService<AccessControlDao, AccessControlEntity> {

	@Resource
	private AccessControlDao accessControlDao;
	
	/**
	 * 获取某一区域下的所有门禁控制器
	 * @param accessControlEntity
	 * @return
	 */
	public List<AccessControlEntity> getAllDoorByArea(AccessControlEntity accessControlEntity){
		List<AccessControlEntity> list = accessControlDao.getAllDoorByArea(accessControlEntity);
		return list;
	}
	/**
	 * 插入数据到dept_con
	 * @param accessControlEntity
	 */
	public void insertIntoDeptCon(AccessControlEntity accessControlEntity){
		accessControlDao.insertIntoDeptCon(accessControlEntity);
	}
	
	/**
	 * 根据部门id(officeId)删除数据dept_con
	 * @param accessControlEntity
	 */
	public void deleteDeptConByOfficeId(AccessControlEntity accessControlEntity){
		accessControlDao.deleteDeptConByOfficeId(accessControlEntity);
	}
	
	/**
	 * 获取某一部门区域下的已有的门禁控制器
	 * @param accessControlEntity
	 * @return
	 */
	public List<AccessControlEntity> getAllDoorAreadySelect(AccessControlEntity accessControlEntity){
		List<AccessControlEntity> list = accessControlDao.getAllDoorAreadySelect(accessControlEntity);
		return list;
	}
	
	/**
	 * 获取部门已有的门禁控制器
	 * @param accessControlEntity
	 * @return
	 */
	/*List<AccessControlEntity> getOfficeDooor(AccessControlEntity accessControlEntity){
		List<AccessControlEntity> list = accessControlDao.findList(accessControlEntity);
		return list;
	}*/
	/*public AccessControlEntity get(String id) {
		return super.get(id);
	}
	
	public List<AccessControlEntity> findList(AccessControlEntity accessControl) {
		return super.findList(accessControl);
	}
	
	public Page<AccessControlEntity> findPage(Page<AccessControlEntity> page, AccessControlEntity accessControl) {
		return super.findPage(page, accessControl);
	}
	
	@Transactional(readOnly = false)
	public void save(AccessControlEntity accessControl) {
		super.save(accessControl);
	}
	
	@Transactional(readOnly = false)
	public void delete(AccessControlEntity accessControl) {
		super.delete(accessControl);
	}*/
	
}