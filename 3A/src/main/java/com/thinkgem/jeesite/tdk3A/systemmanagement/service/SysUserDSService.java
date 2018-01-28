/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.systemmanagement.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.tdk3A.systemmanagement.dao.SysUserDSDao;
import com.thinkgem.jeesite.tdk3A.systemmanagement.entity.SysUserDSEntity;

/**
 * 人员数据抽取Service
 * @author xubaifu
 * @version 2017-03-19
 */
@Service
@Transactional(readOnly = true)
public class SysUserDSService extends CrudService<SysUserDSDao, SysUserDSEntity> {
	@Resource
	private SysUserDSDao sysUserDSDao;
	/**
	 * 查新人事系统中的人员信息
	 * @param params
	 * @return
	 */
	public List<SysUserDSEntity> getUserFromEHR(SysUserDSEntity entity){
		List<SysUserDSEntity> list = new ArrayList<SysUserDSEntity>();
		list = sysUserDSDao.getAllUser(entity);
		return list;
	}
	/**
	 * 查询3A系统下的人员信息
	 * @return
	 */
	public List<SysUserDSEntity> getUserFrom3A(SysUserDSEntity entity){
		List<SysUserDSEntity> list = new ArrayList<SysUserDSEntity>();
		list = sysUserDSDao.getUserById(entity);
		return list;
	}
	/**
	 * 新增3A系统中的人员信息
	 * @param entity
	 */
	public void addUser(SysUserDSEntity entity){
		sysUserDSDao.addUser(entity);
	}
	/**
	 * 修改3A系统中的人员信息
	 * @param entity
	 */
	public void updateUser(SysUserDSEntity entity){
		sysUserDSDao.updateUser(entity);
	}
	/**
	 * 根据更新日期查询人员信息
	 * @param entity
	 * @return
	 */
	public List<SysUserDSEntity> getAllUserByUpdate(SysUserDSEntity entity){
		List<SysUserDSEntity> list = new ArrayList<SysUserDSEntity>();
		list = sysUserDSDao.getAllUserByUpdate(entity);
		return list;
	}
	/**
	 * 删除人员信息
	 * @param entity
	 */
	public void deleteUser(SysUserDSEntity entity){
		sysUserDSDao.deleteUser(entity);
	}
	/*public SysUserDSEntity get(String id) {
		return super.get(id);
	}
	
	public List<SysUserDSEntity> findList(SysUserDSEntity sysUserDS) {
		return super.findList(sysUserDS);
	}
	
	public Page<SysUserDSEntity> findPage(Page<SysUserDSEntity> page, SysUserDSEntity sysUserDS) {
		return super.findPage(page, sysUserDS);
	}
	
	@Transactional(readOnly = false)
	public void save(SysUserDSEntity sysUserDS) {
		super.save(sysUserDS);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysUserDSEntity sysUserDS) {
		super.delete(sysUserDS);
	}*/
	
}