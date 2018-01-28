/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.authorization.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.tdk3A.authorization.dao.AuthorizationDao;
import com.thinkgem.jeesite.tdk3A.authorization.entity.AuthorizationEntity;

/**
 * 一卡通统一授权Service
 * @author baifu
 * @version 2017-03-04
 */
@Service
//@Transactional(readOnly = true)
public class AuthorizationService extends CrudService<AuthorizationDao, AuthorizationEntity> {
	
	@Resource
	private AuthorizationDao authorizationDao;
	
	/**
	 * 获取一卡通授权列表
	 * @param authorizationEntity
	 * @return
	 */
	/*public List<AuthorizationEntity> getAuthorizationList(AuthorizationEntity authorizationEntity){
		List<AuthorizationEntity> list = authorizationDao.findList(authorizationEntity);
		return list;
	}*/
	/**
	 * 根据用户id获取授权信息
	 * @param authorizationEntity
	 * @return
	 */
	public List<AuthorizationEntity> getAuthorizationById(AuthorizationEntity authorizationEntity){
		List<AuthorizationEntity> list = authorizationDao.getAuthorizationById(authorizationEntity);
		return list;
	}
	/**
	 * 获取门禁信息
	 * @param authorizationEntity
	 * @return
	 */
	public List<AuthorizationEntity> getAllDoor(AuthorizationEntity authorizationEntity){
		List<AuthorizationEntity> list = authorizationDao.getAllDoor(authorizationEntity);
		return list;
	}
	
	/**
	 * 添加考勤授权
	 * @param authorizationEntity
	 */
	public void addAttendanceFun(AuthorizationEntity authorizationEntity){
		authorizationDao.addAttendanceFun(authorizationEntity);
	}
	
	/**
	 * 更新考勤授权
	 * @param authorizationEntity
	 */
	public void updateAttendanceFun(AuthorizationEntity authorizationEntity){
		authorizationDao.updateAttendanceFun(authorizationEntity);
	}
	
	/**
	 * 根据用户no查询考勤授权信息
	 * @param authorizationEntity
	 */
	public List<AuthorizationEntity> getAttendanceByUserNo(AuthorizationEntity authorizationEntity){
		List<AuthorizationEntity> list = authorizationDao.getAttendanceByUserNo(authorizationEntity);
		return list;
	}
	
	/**
	 * 获取考勤机信息
	 * @param authorizationEntity
	 * @return
	 */
	public List<AuthorizationEntity> getAttendanceController(AuthorizationEntity authorizationEntity){
		List<AuthorizationEntity> list = authorizationDao.getAttendanceController(authorizationEntity);
		return list;
	}
	/**
	 * 根据userno获取用户是否存在卡号信息
	 * @param authorizationEntity
	 * @return
	 */
	public List<AuthorizationEntity> getUserCardCount(AuthorizationEntity authorizationEntity){
		List<AuthorizationEntity> list = authorizationDao.getUserCardCount(authorizationEntity);
		return list;
	}
	/**
	 * 新增userCard
	 * @param authorizationEntity
	 */
	public void addUserCard(AuthorizationEntity authorizationEntity){
		authorizationDao.addUserCard(authorizationEntity);
	}
	/**
	 * 获取用户可用卡号信息
	 * @param authorizationEntity
	 * @return
	 */
	public List<AuthorizationEntity> getUserCard(AuthorizationEntity authorizationEntity){
		List<AuthorizationEntity> list = authorizationDao.getUserCard(authorizationEntity);
		return list;
	}
	
	/**
	 * 更新卡号状态
	 * @param authorizationEntity
	 */
	public void updateUserCard(AuthorizationEntity authorizationEntity){
		authorizationDao.updateUserCard(authorizationEntity);
	}
	
	/**
	 * 更新sys_user表中的door_id,赋值为max(door_id)+1
	 * @param authorizationEntity
	 */
	public void updateDoorId(AuthorizationEntity authorizationEntity){
		authorizationDao.updateDoorId(authorizationEntity);
	}
	
	
	/**
	 * 插入当前用户门禁权限信息到EHR系统中的[AccessData].[dbo].[t_d_privilege]
	 * @param authorizationEntity
	 */
	public void addPrivilege(AuthorizationEntity authorizationEntity){
		authorizationDao.addPrivilege(authorizationEntity);
	}
	
	/**
	 * 获取[AccessData].[dbo].[t_d_privilege]的所有信息
	 * @param authorizationEntity
	 * @return
	 */
	public List<AuthorizationEntity> getPrivilegeFromEHR(AuthorizationEntity authorizationEntity){
		List<AuthorizationEntity> list = authorizationDao.getPrivilegeFromEHR(authorizationEntity);
		return list;
	}
	
	/**
	 * 将[AccessData].[dbo].[t_d_privilege]的所有信息插入到3A系统中的t_d_privilege
	 * @param authorizationEntity
	 * @return
	 */
	public void  addPrivilegeTo3A(AuthorizationEntity authorizationEntity){
		authorizationDao.addPrivilegeTo3A(authorizationEntity);
	}
		
	/**
	 * 将[AccessData].[dbo].[t_d_privilege]的所有信息更新到3A系统中的t_d_privilege
	 * @param authorizationEntity
	 * @return
	 */
	public void  updatePrivilegeTo3A(AuthorizationEntity authorizationEntity){
		authorizationDao.updatePrivilegeTo3A(authorizationEntity);
	}
	
	
	/**
	 * 根据id获取t_d_privilege行数
	 * @param authorizationEntity
	 * @return
	 */
	public List<AuthorizationEntity> getPrivilegeById(AuthorizationEntity authorizationEntity){
		List<AuthorizationEntity> list = authorizationDao.getPrivilegeById(authorizationEntity);
		return list;
	}
	
	/**
	 * 根据doorId查询当前用户原有的控制器信息
	 * @param authorizationEntity
	 * @return
	 */
	public List<AuthorizationEntity> getPrivilegeByDoorId(AuthorizationEntity authorizationEntity){
		List<AuthorizationEntity> list = authorizationDao.getPrivilegeByDoorId(authorizationEntity);
		return list;
	}
	
	/**
	 * 删除当前用户原有的门禁信息(3A)
	 * @param authorizationEntity
	 */
	public void deletePrivilegeTo3A(AuthorizationEntity authorizationEntity){
		authorizationDao.deletePrivilegeTo3A(authorizationEntity);
	}
	
	/**
	 * 删除当前用户原有的门禁信息(EHR)
	 * @param authorizationEntity
	 */
	public void deletePrivilegeToEHR(AuthorizationEntity authorizationEntity){
		authorizationDao.deletePrivilegeToEHR(authorizationEntity);
	}
	
	/**
	 * 获取用户权限下所有的门禁控制器
	 * @param authorizationEntity
	 * @return
	 */
	public List<AuthorizationEntity> getDoorByUserAuthorzation(AuthorizationEntity authorizationEntity){
		List<AuthorizationEntity> list = authorizationDao.getDoorByUserAuthorzation(authorizationEntity);
		return list;
	}
	/**
	 * 获取部门下门禁控制器
	 * @param authorizationEntity
	 * @return
	 */
	public List<AuthorizationEntity> getDoorByDept(AuthorizationEntity authorizationEntity){
		List<AuthorizationEntity> list = authorizationDao.getDoorByDept(authorizationEntity);
		return list;
	}
	
	//餐卡授权************************start
	//向EHR系统新增数据[STCard_Enp].[dbo].[ST_Person]
	//向EHR系统新增数据[STCard_Enp].[dbo].[ST_Card]
	public void addSTPerson(AuthorizationEntity authorizationEntity){
		authorizationDao.addSTPerson(authorizationEntity);
		if("2".equals(authorizationEntity.getTypeNo())){
			authorizationEntity.setTypeNo("1");
		}
		if("18".equals(authorizationEntity.getTypeNo())){
			authorizationEntity.setTypeNo("2");
		}
		authorizationDao.addSTCard(authorizationEntity);
	}
	//根据id获取信息
	public List<AuthorizationEntity> findSTPersonById(AuthorizationEntity authorizationEntity){
		return authorizationDao.findSTPersonById(authorizationEntity);
	}
	
	//更新
	public void updateSTPerson(AuthorizationEntity authorizationEntity){
		authorizationDao.updateSTPerson(authorizationEntity);
		if("2".equals(authorizationEntity.getTypeNo())){
			authorizationEntity.setTypeNo("1");
		}
		if("18".equals(authorizationEntity.getTypeNo())){
			authorizationEntity.setTypeNo("2");
		}
		authorizationDao.updateSTCard(authorizationEntity);
	}
	
	
	//餐卡授权************************end 
	
	
	/*public AuthorizationEntity get(String id) {
		return super.get(id);
	}
	
	public List<AuthorizationEntity> findList(AuthorizationEntity authorization) {
		return super.findList(authorization);
	}
	
	public Page<AuthorizationEntity> findPage(Page<AuthorizationEntity> page, AuthorizationEntity authorization) {
		return super.findPage(page, authorization);
	}
	
	@Transactional(readOnly = false)
	public void save(AuthorizationEntity authorization) {
		super.save(authorization);
	}
	
	@Transactional(readOnly = false)
	public void delete(AuthorizationEntity authorization) {
		super.delete(authorization);
	}*/
	
}