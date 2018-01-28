/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.authorization.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.tdk3A.authorization.entity.AuthorizationEntity;

/**
 * 一卡通统一授权DAO接口
 * @author baifu
 * @version 2017-03-04
 */
@MyBatisDao
public interface AuthorizationDao extends CrudDao<AuthorizationEntity> {
	//获取一卡通授权列表
	//List<AuthorizationEntity> findList(AuthorizationEntity authorizationEntity);
	//根据用户id获取授权信息
	List<AuthorizationEntity> getAuthorizationById(AuthorizationEntity authorizationEntity);
	//获取门禁列表
	List<AuthorizationEntity> getAllDoor(AuthorizationEntity authorizationEntity);
	//添加考勤授权
	void addAttendanceFun(AuthorizationEntity authorizationEntity);
	
	//更新考勤授权
	void updateAttendanceFun(AuthorizationEntity authorizationEntity);
	//根据用户no查询考勤授权信息
	List<AuthorizationEntity> getAttendanceByUserNo(AuthorizationEntity authorizationEntity);
	//获取所有的考勤机
	List<AuthorizationEntity> getAttendanceController(AuthorizationEntity authorizationEntity);
	//根据userno获取用户是否存在卡号信息
	List<AuthorizationEntity> getUserCardCount(AuthorizationEntity authorizationEntity);
	//新增userCard
	void addUserCard(AuthorizationEntity authorizationEntity);
	//获取用户可用卡号信息
	List<AuthorizationEntity> getUserCard(AuthorizationEntity authorizationEntity);
	//更新卡号状态
	void updateUserCard(AuthorizationEntity authorizationEntity);
	//更新sys_user表中的door_id,赋值为max(door_id)+1
	void updateDoorId(AuthorizationEntity authorizationEntity);
	//插入当前用户门禁权限信息到EHR系统中的[AccessData].[dbo].[t_d_privilege]
	void addPrivilege(AuthorizationEntity authorizationEntity);
	//获取[AccessData].[dbo].[t_d_privilege]的所有信息
	List<AuthorizationEntity> getPrivilegeFromEHR(AuthorizationEntity authorizationEntity);
	//将[AccessData].[dbo].[t_d_privilege]的所有信息插入到3A系统中的t_d_privilege
	void addPrivilegeTo3A(AuthorizationEntity authorizationEntity);
	//将[AccessData].[dbo].[t_d_privilege]的所有信息更新到3A系统中的t_d_privilege
	void updatePrivilegeTo3A(AuthorizationEntity authorizationEntity);
	//根据id获取t_d_privilege行数
	List<AuthorizationEntity> getPrivilegeById(AuthorizationEntity authorizationEntity);
	//根据doorId查询当前用户原有的控制器信息
	List<AuthorizationEntity> getPrivilegeByDoorId(AuthorizationEntity authorizationEntity);
	//删除当前用户原有的门禁信息(3A)
	void deletePrivilegeTo3A(AuthorizationEntity authorizationEntity);
	//删除当前用户原有的门禁信息(EHR)
	void deletePrivilegeToEHR(AuthorizationEntity authorizationEntity);
	//获取用户权限下所有的门禁控制器
	List<AuthorizationEntity> getDoorByUserAuthorzation(AuthorizationEntity authorizationEntity);
	//获取部门的门禁控制器
	List<AuthorizationEntity> getDoorByDept(AuthorizationEntity authorizationEntity);
	
	//餐卡授权************************start
	//向EHR系统新增数据[STCard_Enp].[dbo].[ST_Person]
	void addSTPerson(AuthorizationEntity authorizationEntity);
	//向EHR系统新增数据[STCard_Enp].[dbo].[ST_Card]
	void addSTCard(AuthorizationEntity authorizationEntity);
	
	List<AuthorizationEntity> findSTPersonById(AuthorizationEntity authorizationEntity);
	
	List<AuthorizationEntity> findSTCardById(AuthorizationEntity authorizationEntity);
	
	void updateSTPerson(AuthorizationEntity authorizationEntity);
	
	void updateSTCard(AuthorizationEntity authorizationEntity);
	//餐卡授权************************end 
	
	
	
}