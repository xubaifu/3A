/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.reportmanage.web;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.db.CustomerContextHolder;
import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.tdk3A.reportmanage.entity.TDSwiperecord;
import com.thinkgem.jeesite.tdk3A.reportmanage.service.TDSwiperecordService;

/**
 * 门禁刷卡记录Controller
 * @author xubaifu
 * @version 2017-05-15
 */
@Controller
@RequestMapping(value = "${adminPath}/reportmanage/tDSwiperecord")
public class TDSwiperecordController extends BaseController {

	@Autowired
	private TDSwiperecordService tDSwiperecordService;
	
	private Timer timer = null; 
	
	/*@ModelAttribute
	public TDSwiperecord get(@RequestParam(required=false) String id) {
		TDSwiperecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tDSwiperecordService.get(id);
		}
		if (entity == null){
			entity = new TDSwiperecord();
		}
		return entity;
	}*/
	
	@RequiresPermissions("reportmanage:tDSwiperecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(TDSwiperecord tDSwiperecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TDSwiperecord> page = tDSwiperecordService.findPage(new Page<TDSwiperecord>(request, response), tDSwiperecord); 
		model.addAttribute("page", page);
		return "jeesite/tdk3A/reportmanage/tDSwiperecordList";
	}

	
	/**
	 * 定时任务
	 * @param startTime
	 * @param timeLag
	 * @param type
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = {"startTDSwiperecord","endTDSwiperecord"})
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
        		startOrEndTDSwiperecord();
        	}else{//重复启动
        		timer.cancel();
        		timer = null;
        		timer = new Timer();
        	}
        	//执行任务
        	timer.scheduleAtFixedRate(new TimerTask() {  
                public void run() {  
                	System.out.println(new Date()); 
                	startOrEndTDSwiperecord();
                }  
            }, time, 1000 * 60 * 60 * timeLag);// 这里设定将延时固定执行
        	
        }
        listEHR.add(0, 1);
        return listEHR;
    } 
	
	/**
	 * 查询门禁刷卡记录（门禁系统）
	 * @return
	 */
	public List<TDSwiperecord> startOrEndTDSwiperecord(){
		TDSwiperecord tDSwiperecord = new TDSwiperecord();
		System.out.println("开始执行数据同步");
		//切换到数据源2（连接ehr数据库）
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_E);
		List<TDSwiperecord> list = tDSwiperecordService.findListFromEhr(tDSwiperecord);
		//切换到数据源1（连接3A数据库）
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
		
		//遍历查询结果，跟3A数据库中的人员信息对比，若有对应的结构，则修改为最新的，若没有则新增
		int length = list.size();
		for(int i = 0; i < length; i++){
			tDSwiperecord.setfReaddate(list.get(i).getfReaddate());
			tDSwiperecord.setfCardno(list.get(i).getfCardno());
			tDSwiperecord.setfRecid(list.get(i).getfRecid());
			tDSwiperecord.setfConsumerid(list.get(i).getfConsumerid());
			tDSwiperecord.setfCharacter(list.get(i).getfCharacter());
			tDSwiperecord.setfInout(list.get(i).getfInout());
			tDSwiperecord.setfStatus(list.get(i).getfStatus());
			tDSwiperecord.setfRecoption(list.get(i).getfRecoption());
			tDSwiperecord.setfControllersn(list.get(i).getfControllersn());
			tDSwiperecord.setfReaderid(list.get(i).getfReaderid());
			tDSwiperecord.setfReaderno(list.get(i).getfReaderno());
			tDSwiperecord.setfRecordflashloc(list.get(i).getfRecordflashloc());
			tDSwiperecord.setfRecordall(list.get(i).getfRecordall());
			tDSwiperecord.setfModified(list.get(i).getfModified());
			tDSwiperecordService.save(tDSwiperecord);
		}
		return list;
	}
	/*@RequiresPermissions("reportmanage:tDSwiperecord:view")
	@RequestMapping(value = "form")
	public String form(TDSwiperecord tDSwiperecord, Model model) {
		model.addAttribute("tDSwiperecord", tDSwiperecord);
		return "tdk3A/reportmanage/tDSwiperecordForm";
	}

	@RequiresPermissions("reportmanage:tDSwiperecord:edit")
	@RequestMapping(value = "save")
	public String save(TDSwiperecord tDSwiperecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tDSwiperecord)){
			return form(tDSwiperecord, model);
		}
		tDSwiperecordService.save(tDSwiperecord);
		addMessage(redirectAttributes, "保存门禁刷卡记录成功");
		return "redirect:"+Global.getAdminPath()+"/reportmanage/tDSwiperecord/?repage";
	}
	
	@RequiresPermissions("reportmanage:tDSwiperecord:edit")
	@RequestMapping(value = "delete")
	public String delete(TDSwiperecord tDSwiperecord, RedirectAttributes redirectAttributes) {
		tDSwiperecordService.delete(tDSwiperecord);
		addMessage(redirectAttributes, "删除门禁刷卡记录成功");
		return "redirect:"+Global.getAdminPath()+"/reportmanage/tDSwiperecord/?repage";
	}*/

}