/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.systemmanagement.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import net.sf.ehcache.statistics.CacheUsageListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.db.CustomerContextHolder;
import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.tdk3A.systemmanagement.entity.AttendanceMachineDSEntity;
import com.thinkgem.jeesite.tdk3A.systemmanagement.service.AttendanceMachineDSService;

/**
 * 考勤机数据同步Controller
 * @author xubaifu
 * @version 2017-03-21
 */
@Controller
@RequestMapping(value = "${adminPath}/systemmanagement/systemManagement")
public class AttendanceMachineDSController extends BaseController {

	@Autowired
	private AttendanceMachineDSService attendanceMachineDSService;
	
	private Timer timer = null; 
	
	/**
	 * 定时任务
	 * @param startTime
	 * @param timeLag
	 * @param type
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = {"startAttendance","endAttendance"})
	@ResponseBody
	public List<Object> start(Date startTime, int timeLag,String type) {  
		List<Object> listEHR = new ArrayList<Object>();
		//先执行一次同步
		//startOrEndController();
		
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
        calendar.set(Calendar.HOUR_OF_DAY, 0); // 控制时  
        calendar.set(Calendar.MINUTE, 0);       // 控制分  
        calendar.set(Calendar.SECOND, 0);       // 控制秒  
   
        Date time = calendar.getTime();         // 得出执行任务的时间  
        
        if("STOP".equals(type)){//停止定时任务
        	if(timer != null){
        		timer.cancel();
        		timer = null;
        	}
        }else{//启动定时任务
        	if(timer == null){//首次启动
        		timer = new Timer();
        		startOrEndAttendance();
        	}else{//重复启动
        		timer.cancel();
        		timer = null;
        		timer = new Timer();
        	}
        	//执行任务
        	timer.scheduleAtFixedRate(new TimerTask() {  
                public void run() {  
                	System.out.println(new Date()); 
                	startOrEndAttendance();
                }  
            }, time, 1000 * 60 * 60 * timeLag);// 这里设定将延时固定执行
        	
        }
        listEHR.add(0, 1);
        return listEHR;
    } 
	/**
	 * 考勤机信息数据同步启动、停止(主要的数据同步业务逻辑)
	 * @param startTime
	 * @param timeLag
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public List<AttendanceMachineDSEntity> startOrEndAttendance() {
		AttendanceMachineDSEntity entity = new AttendanceMachineDSEntity();
		System.out.println("开始执行数据同步");
		//Map<String,Object> params = new HashMap<String,Object>();
		//切换到数据源2（连接ehr数据库）
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_C);
		List<AttendanceMachineDSEntity> listEHR = new ArrayList<AttendanceMachineDSEntity>();
		List<AttendanceMachineDSEntity> list3A = new ArrayList<AttendanceMachineDSEntity>();
		//使用ehr系统数据库查询EHR系统的考勤机信息
		listEHR = attendanceMachineDSService.getAttendanceFromEHR(entity);
		
		int length = listEHR.size();
		//切换到数据源2（连接3A数据库）
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
		//遍历查询结果，跟3A数据库中的考勤机信息对比，若有对应的结构，则修改为最新的，若没有则新增
		if(length>0){
			for(int i = 0; i < length; i++){
				entity.setBh(listEHR.get(i).getBh());
				entity.setMc(listEHR.get(i).getMc());
				entity.setBz(listEHR.get(i).getBz());
				entity.setIp(listEHR.get(i).getIp());
				entity.setCom(listEHR.get(i).getCom());
				entity.setPort(listEHR.get(i).getPort());
				entity.setBtl(listEHR.get(i).getBtl());
				entity.setMm(listEHR.get(i).getMm());
				entity.setZt(listEHR.get(i).getZt());
				entity.setSj(listEHR.get(i).getSj());
				entity.setXs(listEHR.get(i).getXs());
				entity.setMj(listEHR.get(i).getMj());
				entity.setPztj(listEHR.get(i).getPztj());
				entity.setPzlj(listEHR.get(i).getPzlj());
				entity.setLx(listEHR.get(i).getLx());
				entity.setYzfs(listEHR.get(i).getYzfs());
				entity.setGlyNo(listEHR.get(i).getGlyNo());
				entity.setGlyPass(listEHR.get(i).getGlyPass());
				entity.setMac(listEHR.get(i).getMac());
				entity.setDevXs(listEHR.get(i).getDevXs());
				entity.setDevLx(listEHR.get(i).getDevLx());
				entity.setKmry(listEHR.get(i).getKmry());
				entity.setDyip(listEHR.get(i).getDyip());
				entity.setJksz(listEHR.get(i).getJksz());
				entity.setTjkq(listEHR.get(i).getTjkq());
				entity.setOnLine(listEHR.get(i).getOnLine());
				entity.setRunState(listEHR.get(i).getRunState());
				entity.setDevLb(listEHR.get(i).getDevLb());
				entity.setDevVersion(listEHR.get(i).getDevVersion());
				entity.setDevUp(listEHR.get(i).getDevUp());
				entity.setMaxFlowNo(listEHR.get(i).getMaxFlowNo());
				entity.setDevLock(listEHR.get(i).getDevLock());
				
				list3A = attendanceMachineDSService.getAttendanceFrom3A(entity);
				if("0".equals(list3A.get(0).getNum())){
					attendanceMachineDSService.addAttendance(entity);
				}else{
					attendanceMachineDSService.updateAttendance(entity);
				}
			}
		}
		
		//根据当前日期查询考勤机信息信息，凡是更新日期在今日之前的考勤机信息删除
		List<AttendanceMachineDSEntity> listForDel = new ArrayList<AttendanceMachineDSEntity>();
		listForDel = attendanceMachineDSService.getAllAttendanceByUpdate(entity);
		if(listForDel.size()>0){
			for(int j = 0; j < listForDel.size(); j++){
				entity.setBh(listEHR.get(j).getBh());
				attendanceMachineDSService.deleteAttendance(entity);
			}
		}
		return listEHR;
	}

}