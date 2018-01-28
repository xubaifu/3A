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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.db.CustomerContextHolder;
import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.tdk3A.systemmanagement.entity.AccessControlDSEntity;
import com.thinkgem.jeesite.tdk3A.systemmanagement.service.AccessControlDSService;

/**
 * 门禁控制器数据同步Controller
 * @author xubaifu
 * @version 2017-03-21
 */
@Controller
@RequestMapping(value = "${adminPath}/systemmanagement/systemManagement")
public class AccessControlDSController extends BaseController {

	@Autowired
	private AccessControlDSService accessControlDSService;
	
	private Timer timer = null; 
	
	/**
	 * 定时任务
	 * @param startTime
	 * @param timeLag
	 * @param type
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = {"startController","endController"})
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
        		 startOrEndController();
        	}else{//重复启动
        		timer.cancel();
        		timer = null;
        		timer = new Timer();
        	}
        	//执行任务
        	timer.scheduleAtFixedRate(new TimerTask() {  
                public void run() {  
                	System.out.println(new Date()); 
                    startOrEndController();
                }  
            }, time, 1000 * 60 * 60 * timeLag);// 这里设定将延时固定执行
        	
        }
        listEHR.add(0, 1);
        return listEHR;
    } 
	
	/**
	 * 门禁器信息数据同步启动、停止(主要的数据同步业务逻辑)
	 * @param startTime
	 * @param timeLag
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public List<AccessControlDSEntity> startOrEndController() {
		AccessControlDSEntity entity = new AccessControlDSEntity();
		System.out.println("开始执行数据同步");
		//Map<String,Object> params = new HashMap<String,Object>();
		//切换到数据源2（连接ehr数据库）
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_D);
		List<AccessControlDSEntity> listEHR = new ArrayList<AccessControlDSEntity>();
		List<AccessControlDSEntity> list3A = new ArrayList<AccessControlDSEntity>();
		//使用ehr系统数据库查询EHR系统的门禁器信息
		listEHR = accessControlDSService.getControllerFromEHR(entity);
		
		int length = listEHR.size();
		//切换到数据源1（连接3A数据库）
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
		//遍历查询结果，跟3A数据库中的门禁器信息对比，若有对应的结构，则修改为最新的，若没有则新增
		if(length>0){
			for(int i = 0; i < length; i++){
				entity.setfControllerid(listEHR.get(i).getfControllerid());
				entity.setfControllerno(listEHR.get(i).getfControllerno());
				entity.setfControllersn(listEHR.get(i).getfControllersn());
				entity.setfEnabled(listEHR.get(i).getfEnabled());
				entity.setfIp(listEHR.get(i).getfIp());
				entity.setfPort(listEHR.get(i).getfPort());
				entity.setfNote(listEHR.get(i).getfNote());
				entity.setfDoornames(listEHR.get(i).getfDoornames());
				entity.setfZoneid(listEHR.get(i).getfZoneid());
				entity.setfAntiback(listEHR.get(i).getfAntiback());
				entity.setfInterlock(listEHR.get(i).getfInterlock());
				entity.setfMorecardsGoinout(listEHR.get(i).getfMorecardsGoinout());
				entity.setfDoorinvalidopen(listEHR.get(i).getfDoorinvalidopen());
				entity.setfDooropentoolong(listEHR.get(i).getfDooropentoolong());
				entity.setfForcewarn(listEHR.get(i).getfForcewarn());
				entity.setfInvalidcardwarn(listEHR.get(i).getfInvalidcardwarn());
				entity.setfPeripheralcontrol(listEHR.get(i).getfPeripheralcontrol());
				entity.setfLastdeladddatetime(listEHR.get(i).getfLastdeladddatetime());
				entity.setfLastdeladdconsuemrstotal(listEHR.get(i).getfLastdeladdconsuemrstotal());
				entity.setfLastdeladdanduploaddatetime(listEHR.get(i).getfLastdeladdanduploaddatetime());
				entity.setfLastdeladdanduploadconsuemrs(listEHR.get(i).getfLastdeladdanduploadconsuemrs());
				entity.setfLastconsoleuploaddatetime(listEHR.get(i).getfLastconsoleuploaddatetime());
				entity.setfLastconsoleuploadconsuemrsto(listEHR.get(i).getfLastconsoleuploadconsuemrsto());
				entity.setfLastconsoleuploadprivilege(listEHR.get(i).getfLastconsoleuploadprivilege());
				entity.setfLastconsoleuploadvalidprivil(listEHR.get(i).getfLastconsoleuploadvalidprivil());
				entity.setfLastDelAddDateTime(listEHR.get(i).getfLastDelAddDateTime());
				
				list3A = accessControlDSService.getControllerFrom3A(entity);
				if("0".equals(list3A.get(0).getNum())){
					accessControlDSService.addController(entity);
				}else{
					accessControlDSService.updateController(entity);
				}
			}
		}
		
		//根据当前日期查询门禁控制器信息，凡是更新日期在今日之前的门禁控制器删除
		List<AccessControlDSEntity> listForDel = new ArrayList<AccessControlDSEntity>();
		listForDel = accessControlDSService.getAllControllerByUpdate(entity);
		if(listForDel.size()>0){
			for(int j = 0; j < listForDel.size(); j++){
				entity.setfControllerid(listForDel.get(j).getfControllerid());
				accessControlDSService.deleteController(entity);
			}
		}
		
		return listEHR;
	}
	/*@ModelAttribute
	public AccessControlDSEntity get(@RequestParam(required=false) String id) {
		AccessControlDSEntity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = accessControlDSService.get(id);
		}
		if (entity == null){
			entity = new AccessControlDSEntity();
		}
		return entity;
	}
	
	@RequiresPermissions("systemmanagement:accessControlDS:view")
	@RequestMapping(value = {"list", ""})
	public String list(AccessControlDSEntity accessControlDS, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AccessControlDSEntity> page = accessControlDSService.findPage(new Page<AccessControlDSEntity>(request, response), accessControlDS); 
		model.addAttribute("page", page);
		return "tdk3a/systemmanagement/accessControlDSList";
	}

	@RequiresPermissions("systemmanagement:accessControlDS:view")
	@RequestMapping(value = "form")
	public String form(AccessControlDSEntity accessControlDS, Model model) {
		model.addAttribute("accessControlDS", accessControlDS);
		return "tdk3a/systemmanagement/accessControlDSForm";
	}

	@RequiresPermissions("systemmanagement:accessControlDS:edit")
	@RequestMapping(value = "save")
	public String save(AccessControlDSEntity accessControlDS, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, accessControlDS)){
			return form(accessControlDS, model);
		}
		accessControlDSService.save(accessControlDS);
		addMessage(redirectAttributes, "保存门禁控制器数据同步成功");
		return "redirect:"+Global.getAdminPath()+"/systemmanagement/accessControlDS/?repage";
	}
	
	@RequiresPermissions("systemmanagement:accessControlDS:edit")
	@RequestMapping(value = "delete")
	public String delete(AccessControlDSEntity accessControlDS, RedirectAttributes redirectAttributes) {
		accessControlDSService.delete(accessControlDS);
		addMessage(redirectAttributes, "删除门禁控制器数据同步成功");
		return "redirect:"+Global.getAdminPath()+"/systemmanagement/accessControlDS/?repage";
	}*/

}