/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.systemmanagement.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.db.CustomerContextHolder;
import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.tdk3A.systemmanagement.entity.KqOriginalityData;
import com.thinkgem.jeesite.tdk3A.systemmanagement.service.KqOriginalityDataService;

/**
 * 考勤信息同步Controller
 * @author xubaifu
 * @version 2017-05-01
 */
@Controller
@RequestMapping(value = "${adminPath}/systemmanagement/systemManagement")
public class KqOriginalityDataController extends BaseController {

	@Autowired
	private KqOriginalityDataService kqOriginalityDataService;
	
	private Timer timer = null; 
	
	@ModelAttribute
	public KqOriginalityData get(@RequestParam(required=false) String id) {
		KqOriginalityData entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = kqOriginalityDataService.get(id);
		}
		if (entity == null){
			entity = new KqOriginalityData();
		}
		return entity;
	}
	
	@RequiresPermissions("systemmanagement:kqOriginalityData:view")
	@RequestMapping(value = {"listKq"})
	public String list(KqOriginalityData kqOriginalityData, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<KqOriginalityData> page = kqOriginalityDataService.findPage(new Page<KqOriginalityData>(request, response), kqOriginalityData); 
		model.addAttribute("page", page);
		return "tdk3a/systemmanagement/kqOriginalityDataList";
	}

	@RequiresPermissions("systemmanagement:kqOriginalityData:view")
	@RequestMapping(value = "formKq")
	public String form(KqOriginalityData kqOriginalityData, Model model) {
		model.addAttribute("kqOriginalityData", kqOriginalityData);
		return "tdk3a/systemmanagement/kqOriginalityDataForm";
	}

	@RequiresPermissions("systemmanagement:kqOriginalityData:edit")
	@RequestMapping(value = "saveKq")
	public String save(KqOriginalityData kqOriginalityData, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, kqOriginalityData)){
			return form(kqOriginalityData, model);
		}
		kqOriginalityDataService.save(kqOriginalityData);
		addMessage(redirectAttributes, "保存考勤信息同步成功");
		return "redirect:"+Global.getAdminPath()+"/systemmanagement/kqOriginalityData/?repage";
	}
	
	@RequiresPermissions("systemmanagement:kqOriginalityData:edit")
	@RequestMapping(value = "deleteKq")
	public String delete(KqOriginalityData kqOriginalityData, RedirectAttributes redirectAttributes) {
		kqOriginalityDataService.delete(kqOriginalityData);
		addMessage(redirectAttributes, "删除考勤信息同步成功");
		return "redirect:"+Global.getAdminPath()+"/systemmanagement/kqOriginalityData/?repage";
	}
	/**
	 * 定时任务
	 * @param startTime
	 * @param timeLag
	 * @param type
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = {"startKqDetail","endKqDetail"})
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
        		startKqDetail();
        	}else{//重复启动
        		timer.cancel();
        		timer = null;
        		timer = new Timer();
        	}
        	//执行任务
        	timer.scheduleAtFixedRate(new TimerTask() {  
                public void run() {  
                	System.out.println(new Date()); 
                	startKqDetail();
                }  
            }, time, 1000 * 60 * 60 * timeLag);// 这里设定将延时固定执行
        	
        }
        listEHR.add(0, 1);
        return listEHR;
    }
	
	public List<KqOriginalityData> startKqDetail() {
		KqOriginalityData entity = new KqOriginalityData();
		System.out.println("开始执行数据同步");
		//Map<String,Object> params = new HashMap<String,Object>();
		//切换到数据源2（连接ehr数据库）
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_B);
		List<KqOriginalityData> listEHR = new ArrayList<KqOriginalityData>();
		//List<KqOriginalityData> list3A = new ArrayList<KqOriginalityData>();
		//使用ehr系统数据库查询EHR系统的人员信息
		listEHR = kqOriginalityDataService.getKqDetailFromEHR(entity);
		
		int length = listEHR.size();
		//切换到数据源2（连接3A数据库）
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
		//遍历查询结果，跟3A数据库中的人员信息对比，若有对应的结构，则修改为最新的，若没有则新增
		if(length>0){
			/*String OperTime = "";
			String workDate*/
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd HH:mm");
			for(int i = 0; i < length; i++){
				try {
					entity.setKqDetailId(listEHR.get(i).getA0100());
					entity.setUsername(listEHR.get(i).getA0101());
					entity.setLocation(listEHR.get(i).getLocation());
					
					entity.setMachineNo(listEHR.get(i).getMachineNo());
					entity.setCardNo(listEHR.get(i).getCardNo());
					entity.setStatus(listEHR.get(i).getStatus());
					entity.setDatafrom(listEHR.get(i).getDatafrom());
					entity.setInoutFlag(listEHR.get(i).getInoutFlag());
					entity.setOperUser(listEHR.get(i).getOperUser());
					entity.setOperCause(listEHR.get(i).getOperCause());
					entity.setOperMach(listEHR.get(i).getOperMach());
					entity.setSpFlag(listEHR.get(i).getSpFlag());
					entity.setSpUser(listEHR.get(i).getSpUser());
					//entity.setSpTime(listEHR.get(i).getSpTime());
					entity.setCurrUser(listEHR.get(i).getCurrUser());
					entity.setLatitude(listEHR.get(i).getLatitude());
					entity.setLongitude(listEHR.get(i).getLongitude());
					
					if(listEHR.get(i).getOperTime()!=null && listEHR.get(i).getOperTime().length()>19 ){
						entity.setOperTime3A(sdf.parse(listEHR.get(i).getOperTime().substring(0, 19)));
					}else{
						entity.setOperTime3A(null);
					}
					if(listEHR.get(i).getSpTime()!=null && listEHR.get(i).getSpTime().length()>19 ){
						entity.setSpTime3A(sdf.parse(listEHR.get(i).getSpTime().substring(0, 19)));
					}else{
						entity.setOperTime3A(null);
					}
					entity.setWorkDate3A(sdf2.parse(listEHR.get(i).getWorkDate()+" "+listEHR.get(i).getWorkTime()));
					
					kqOriginalityDataService.addKqDetail(entity);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}
		}
		return listEHR;
	}

}