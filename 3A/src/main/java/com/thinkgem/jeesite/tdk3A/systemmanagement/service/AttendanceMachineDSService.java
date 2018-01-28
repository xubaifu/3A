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
import com.thinkgem.jeesite.tdk3A.systemmanagement.dao.AttendanceMachineDSDao;
import com.thinkgem.jeesite.tdk3A.systemmanagement.entity.AttendanceMachineDSEntity;

/**
 * 考勤机数据同步Service
 * @author xubaifu
 * @version 2017-03-21
 */
@Service
@Transactional(readOnly = true)
public class AttendanceMachineDSService extends CrudService<AttendanceMachineDSDao, AttendanceMachineDSEntity> {

	@Resource
	private AttendanceMachineDSDao attendanceMachineDSDao;
	/**
	 * 查新人事系统中的考勤机信息
	 * @param params
	 * @return
	 */
	public List<AttendanceMachineDSEntity> getAttendanceFromEHR(AttendanceMachineDSEntity entity){
		List<AttendanceMachineDSEntity> list = new ArrayList<AttendanceMachineDSEntity>();
		list = attendanceMachineDSDao.getAllAttendance(entity);
		return list;
	}
	/**
	 * 查询3A系统下的考勤机信息
	 * @return
	 */
	public List<AttendanceMachineDSEntity> getAttendanceFrom3A(AttendanceMachineDSEntity entity){
		List<AttendanceMachineDSEntity> list = new ArrayList<AttendanceMachineDSEntity>();
		list = attendanceMachineDSDao.getAttendanceById(entity);
		return list;
	}
	/**
	 * 新增3A系统中的考勤机信息
	 * @param entity
	 */
	public void addAttendance(AttendanceMachineDSEntity entity){
		attendanceMachineDSDao.addAttendance(entity);
	}
	/**
	 * 修改3A系统中的考勤机信息
	 * @param entity
	 */
	public void updateAttendance(AttendanceMachineDSEntity entity){
		attendanceMachineDSDao.updateAttendance(entity);
	}
	/**
	 * 根据更新日期获取考勤机信息
	 * @return
	 */
	public List<AttendanceMachineDSEntity> getAllAttendanceByUpdate(AttendanceMachineDSEntity entity){
		List<AttendanceMachineDSEntity> list = new ArrayList<AttendanceMachineDSEntity>();
		list = attendanceMachineDSDao.getAllAttendanceByUpdate(entity);
		return list;
	}
	
	/**
	 * 删除考勤机信息
	 */
	public void deleteAttendance(AttendanceMachineDSEntity entity){
		attendanceMachineDSDao.deleteAttendance(entity);
	}
	
}