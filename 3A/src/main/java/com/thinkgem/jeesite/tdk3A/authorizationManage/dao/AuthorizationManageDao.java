/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.authorizationManage.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.tdk3A.authorization.entity.AttendanceEntity;
import com.thinkgem.jeesite.tdk3A.authorizationManage.entity.AuthorizationManageEntity;

/**
 * 授权管理DAO接口
 * @author baifu
 * @version 2017-03-05
 */
@MyBatisDao
public interface AuthorizationManageDao extends CrudDao<AuthorizationManageEntity> {
	//根据部门获取用户信息
	List<AuthorizationManageEntity> getUserByOffice(AuthorizationManageEntity authorizationManageEntity);
	//获取所有的考勤机
	List<AttendanceEntity> getAttendanceController(AttendanceEntity authorizationEntity);
}