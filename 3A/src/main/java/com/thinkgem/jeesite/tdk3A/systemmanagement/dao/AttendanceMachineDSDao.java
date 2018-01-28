/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.systemmanagement.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.tdk3A.systemmanagement.entity.AttendanceMachineDSEntity;

/**
 * 考勤机数据同步DAO接口
 * @author xubaifu
 * @version 2017-03-21
 */
@MyBatisDao
public interface AttendanceMachineDSDao extends CrudDao<AttendanceMachineDSEntity> {
	/**
	 * 获取所有考勤机信息（通过人事系统考勤机信息表获取）
	 * @return
	 */
	List<AttendanceMachineDSEntity> getAllAttendance(AttendanceMachineDSEntity entity);
	/**
	 * 根据条件获取考勤机信息
	 * @return
	 */
	List<AttendanceMachineDSEntity> getAttendanceById(AttendanceMachineDSEntity entity);
	
	/**
	 * 添加考勤机信息（插入到3A系统中）
	 */
	void addAttendance(AttendanceMachineDSEntity entity);
	/**
	 * 修改考勤机信息（插入到3A系统中）
	 */
	void updateAttendance(AttendanceMachineDSEntity entity);
	/**
	 * 根据更新日期获取考勤机信息
	 * @return
	 */
	List<AttendanceMachineDSEntity> getAllAttendanceByUpdate(AttendanceMachineDSEntity entity);
	
	/**
	 * 删除考勤机信息
	 */
	void deleteAttendance(AttendanceMachineDSEntity entity);
	
}