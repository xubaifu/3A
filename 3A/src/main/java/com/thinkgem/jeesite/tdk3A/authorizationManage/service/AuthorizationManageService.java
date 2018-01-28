/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.authorizationManage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.tdk3A.authorization.entity.AttendanceEntity;
import com.thinkgem.jeesite.tdk3A.authorizationManage.dao.AuthorizationManageDao;
import com.thinkgem.jeesite.tdk3A.authorizationManage.entity.AuthorizationManageEntity;

/**
 * 授权管理Service
 * @author baifu
 * @version 2017-03-05
 */
@Service
//@Transactional(readOnly = true)
public class AuthorizationManageService extends CrudService<AuthorizationManageDao, AuthorizationManageEntity> {
	@Resource
	AuthorizationManageDao authorizationManageDao;
	/**
	 * 根据部门获取用户信息
	 * @param officeId
	 * @return
	 */
	public List<AuthorizationManageEntity> getUserByOffice(AuthorizationManageEntity authorizationManageEntity){
		List<AuthorizationManageEntity> list = new ArrayList<AuthorizationManageEntity>();
		list = authorizationManageDao.getUserByOffice(authorizationManageEntity);
		return list;
	}
	/**
	 * 获取考勤机信息
	 * @param authorizationEntity
	 * @return
	 */
	public List<AttendanceEntity> getAttendanceController(AttendanceEntity attendanceEntity){
		List<AttendanceEntity> list = authorizationManageDao.getAttendanceController(attendanceEntity);
		return list;
	}
	/*public AuthorizationManageEntity get(String id) {
		return super.get(id);
	}
	
	public List<AuthorizationManageEntity> findList(AuthorizationManageEntity testSysUser) {
		return super.findList(testSysUser);
	}
	
	public Page<AuthorizationManageEntity> findPage(Page<AuthorizationManageEntity> page, AuthorizationManageEntity testSysUser) {
		return super.findPage(page, testSysUser);
	}
	
	@Transactional(readOnly = false)
	public void save(AuthorizationManageEntity testSysUser) {
		super.save(testSysUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(AuthorizationManageEntity testSysUser) {
		super.delete(testSysUser);
	}*/
	
}