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
import com.thinkgem.jeesite.tdk3A.systemmanagement.entity.MealCardDS;
import com.thinkgem.jeesite.tdk3A.systemmanagement.service.MealCardDSService;

/**
 * 餐卡信息同步Controller
 * @author xubaifu
 * @version 2017-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/systemmanagement/systemManagement")
public class MealCardDSController extends BaseController {

	@Autowired
	private MealCardDSService mealCardDSService;
	
	private Timer timer = null; 
	
	/**
	 * 定时任务
	 * @param startTime
	 * @param timeLag
	 * @param type
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = {"startMealCard","endMealCard"})
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
        		startOrEndMealCard();
        	}else{//重复启动
        		timer.cancel();
        		timer = null;
        		timer = new Timer();
        	}
        	//执行任务
        	timer.scheduleAtFixedRate(new TimerTask() {  
                public void run() {  
                	System.out.println(new Date()); 
                	startOrEndMealCard();
                }  
            }, time, 1000 * 60 * 60 * timeLag);// 这里设定将延时固定执行
        	
        }
        listEHR.add(0, 1);
        return listEHR;
    } 
	
	/**
	 * 餐卡数据同步
	 * @return
	 */
	public List<MealCardDS> startOrEndMealCard(){
		MealCardDS entity = new MealCardDS();
		System.out.println("开始执行数据同步");
		//切换到数据源2（连接ehr数据库）
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_E);
		List<MealCardDS> listEHR = new ArrayList<MealCardDS>();
		List<MealCardDS> list3A = new ArrayList<MealCardDS>();
		//使用ehr系统数据库查询EHR系统的餐卡信息
		listEHR = mealCardDSService.getAllMealCard(entity);
		
		int length = listEHR.size();
		//切换到数据源2（连接3A数据库）
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
		//遍历查询结果，跟3A数据库中的餐卡信息对比，若有对应的结构，则修改为最新的，若没有则新增
		if(length>0){
			for(int i = 0; i < length; i++){
				entity.setIdKey(listEHR.get(i).getIdKey());
				entity.setPersonId(listEHR.get(i).getPersonId());
				entity.setSystemNo(listEHR.get(i).getSystemNo());
				entity.setConsumeMode(listEHR.get(i).getConsumeMode());
				entity.setConsumeTime(listEHR.get(i).getConsumeTime());
				entity.setConsumeFund(listEHR.get(i).getConsumeFund());
				entity.setBeforeFund(listEHR.get(i).getBeforeFund());
				entity.setConsumeCopy(listEHR.get(i).getConsumeCopy());
				entity.setConsumeTimelen(listEHR.get(i).getConsumeTimelen());
				entity.setConsumeCapacity(listEHR.get(i).getConsumeCapacity());
				entity.setRepastNo(listEHR.get(i).getRepastNo());
				entity.setWareId(listEHR.get(i).getWareId());
				entity.setMocNo(listEHR.get(i).getMocNo());
				entity.setComNo(listEHR.get(i).getComNo());
				entity.setCardBalance(listEHR.get(i).getCardBalance());
				entity.setDbBalance(listEHR.get(i).getDbBalance());
				entity.setCardSmallaccount(listEHR.get(i).getCardSmallaccount());
				entity.setDataType(listEHR.get(i).getDataType());
				entity.setRemark(listEHR.get(i).getRemark());
				entity.setDownloadTime(listEHR.get(i).getDownloadTime());
				
				list3A = mealCardDSService.getMealCardById(entity);
				if("0".equals(list3A.get(0).getNum())){
					mealCardDSService.addMealCard(entity);
				}else{
					mealCardDSService.updateMealCard(entity);
				}
			}
		}
		
		return listEHR;
	}
	/*//3A系统中添加餐卡明细
	public void addMealCard(MealCardDS entity){
		mealCardDSService.addMealCard(entity);
	}
	//3A系统中更新餐卡明细
	public void updateMealCard(MealCardDS entity){
		mealCardDSService.updateMealCard(entity);
	}
	//3A系统中删除餐卡明细
	public void deleteMealCard(MealCardDS entity){
		mealCardDSService.deleteMealCard(entity);
	}*/
	
	/*@ModelAttribute
	public MealCardDS get(@RequestParam(required=false) String id) {
		MealCardDS entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mealCardDSService.get(id);
		}
		if (entity == null){
			entity = new MealCardDS();
		}
		return entity;
	}
	
	@RequiresPermissions("systemmanagement:mealCardDS:view")
	@RequestMapping(value = {"list", ""})
	public String list(MealCardDS mealCardDS, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MealCardDS> page = mealCardDSService.findPage(new Page<MealCardDS>(request, response), mealCardDS); 
		model.addAttribute("page", page);
		return "tdk3a/systemmanagement/mealCardDSList";
	}

	@RequiresPermissions("systemmanagement:mealCardDS:view")
	@RequestMapping(value = "form")
	public String form(MealCardDS mealCardDS, Model model) {
		model.addAttribute("mealCardDS", mealCardDS);
		return "tdk3a/systemmanagement/mealCardDSForm";
	}

	@RequiresPermissions("systemmanagement:mealCardDS:edit")
	@RequestMapping(value = "save")
	public String save(MealCardDS mealCardDS, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mealCardDS)){
			return form(mealCardDS, model);
		}
		mealCardDSService.save(mealCardDS);
		addMessage(redirectAttributes, "保存餐卡同步成功");
		return "redirect:"+Global.getAdminPath()+"/systemmanagement/mealCardDS/?repage";
	}
	
	@RequiresPermissions("systemmanagement:mealCardDS:edit")
	@RequestMapping(value = "delete")
	public String delete(MealCardDS mealCardDS, RedirectAttributes redirectAttributes) {
		mealCardDSService.delete(mealCardDS);
		addMessage(redirectAttributes, "删除餐卡同步成功");
		return "redirect:"+Global.getAdminPath()+"/systemmanagement/mealCardDS/?repage";
	}*/

}