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
import org.wiegand.at8000.WgUdpCommShort;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.db.CustomerContextHolder;
import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.tdk3A.authorization.entity.AuthorizationEntity;
import com.thinkgem.jeesite.tdk3A.authorization.service.AuthorizationService;
import com.thinkgem.jeesite.tdk3A.systemmanagement.entity.SysUserDSEntity;
import com.thinkgem.jeesite.tdk3A.systemmanagement.service.SysUserDSService;

/**
 * 人员数据抽取Controller
 * @author xubaifu
 * @version 2017-03-19
 */
@Controller
@RequestMapping(value = "${adminPath}/systemmanagement/systemManagement")
public class SysUserDSController extends BaseController {

	@Autowired
	private SysUserDSService sysUserDSService;
	@Autowired
	private AuthorizationService authorizationService;
	private Timer timer = null; 
	
	@ModelAttribute
	public SysUserDSEntity get(@RequestParam(required=false) String id) {
		SysUserDSEntity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysUserDSService.get(id);
		}
		if (entity == null){
			entity = new SysUserDSEntity();
		}
		return entity;
	}
	
	@RequiresPermissions("sysuserds:sysUserDS:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysUserDSEntity sysUserDS, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysUserDSEntity> page = sysUserDSService.findPage(new Page<SysUserDSEntity>(request, response), sysUserDS); 
		model.addAttribute("page", page);
		return "tdk3a/sysuserds/sysUserDSList";
	}

	@RequiresPermissions("sysuserds:sysUserDS:view")
	@RequestMapping(value = "form")
	public String form(SysUserDSEntity sysUserDS, Model model) {
		model.addAttribute("sysUserDS", sysUserDS);
		return "tdk3a/sysuserds/sysUserDSForm";
	}

	@RequiresPermissions("sysuserds:sysUserDS:edit")
	@RequestMapping(value = "save")
	public String save(SysUserDSEntity sysUserDS, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysUserDS)){
			return form(sysUserDS, model);
		}
		sysUserDSService.save(sysUserDS);
		addMessage(redirectAttributes, "保存人员数据抽取成功");
		return "redirect:"+Global.getAdminPath()+"/sysuserds/sysUserDS/?repage";
	}
	
	@RequiresPermissions("sysuserds:sysUserDS:edit")
	@RequestMapping(value = "delete")
	public String delete(SysUserDSEntity sysUserDS, RedirectAttributes redirectAttributes) {
		sysUserDSService.delete(sysUserDS);
		addMessage(redirectAttributes, "删除人员数据抽取成功");
		return "redirect:"+Global.getAdminPath()+"/sysuserds/sysUserDS/?repage";
	}
	
	/**
	 * 更新food_id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("systemmanagement:datamanagement:view")
	@RequestMapping(value = "updateFoodId")
	@ResponseBody
	public String updateFoodId(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		SysUserDSEntity entity = new SysUserDSEntity();
		//切换到数据源餐卡数据库
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_E);
		List<SysUserDSEntity> listEHR = new ArrayList<SysUserDSEntity>();
		//使用ehr系统数据库查询EHR系统的人员信息
		//listEHR = sysUserDSService.getUserFromEHR(entity);
		
		listEHR = sysUserDSService.getFoodId(entity);
		
		int length = listEHR.size();
		//切换到数据源2（连接3A数据库）
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
		//遍历查询结果，跟3A数据库中的人员信息对比，若有对应的结构，则修改为最新的，若没有则新增
		//String no = "";
		if(length>0){
			for(int i = 0; i < length; i++){
				entity.setNo(listEHR.get(i).getNo());
				entity.setFoodId(listEHR.get(0).getFoodId());
				//System.out.println(entity.getNo());
				//System.out.println(entity.getFoodId());
				//向3A数据库修改food_id
				sysUserDSService.updateFoodId(entity);
			}
		}
		
		return "success";
	}
	
	/**
	 * 定时任务
	 * @param startTime
	 * @param timeLag
	 * @param type
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = {"startUser","endUser"})
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
        		startOrEndUser();
        	}else{//重复启动
        		timer.cancel();
        		timer = null;
        		timer = new Timer();
        	}
        	//执行任务
        	timer.scheduleAtFixedRate(new TimerTask() {  
                public void run() {  
                	System.out.println(new Date()); 
                	startOrEndUser();
                }  
            }, time, 1000 * 60 * 60 * timeLag);// 这里设定将延时固定执行
        	
        }
        listEHR.add(0, 1);
        return listEHR;
    } 
	/**
	 * 人员信息数据同步启动、停止(主要的数据同步业务逻辑)
	 * @param startTime
	 * @param timeLag
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	public List<SysUserDSEntity> startOrEndUser() {
		SysUserDSEntity entity = new SysUserDSEntity();
		System.out.println("开始执行数据同步");
		//Map<String,Object> params = new HashMap<String,Object>();
		//切换到数据源2（连接ehr数据库）
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_B);
		List<SysUserDSEntity> listEHR = new ArrayList<SysUserDSEntity>();
		List<SysUserDSEntity> list3A = new ArrayList<SysUserDSEntity>();
		//使用ehr系统数据库查询EHR系统的人员信息
		listEHR = sysUserDSService.getUserFromEHR(entity);
		
		int length = listEHR.size();
		//切换到数据源2（连接3A数据库）
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
		//遍历查询结果，跟3A数据库中的人员信息对比，若有对应的结构，则修改为最新的，若没有则新增
		if(length>0){
			for(int i = 0; i < length; i++){
				entity.setName(listEHR.get(i).getA0101());
				entity.setOfficeId(listEHR.get(i).getE0122());
				entity.setLoginName(listEHR.get(i).getE0127());
				entity.setNo(listEHR.get(i).getE0127());
				entity.setCreateTime(listEHR.get(i).getCreateTime());
				entity.setKQID(listEHR.get(i).getC01UG());
				entity.setPassword(SystemService.entryptPassword("admin"));
				entity.setKqDetailId(listEHR.get(i).getA0100());
				list3A = sysUserDSService.getUserFrom3A(entity);
				if("0".equals(list3A.get(0).getNum())){
					sysUserDSService.addUser(entity);
				}else{
					sysUserDSService.updateUser(entity);
				}
			}
		}
		//根据当前日期查询人员信息，凡是更新日期在今日之前的人员删除
		List<SysUserDSEntity> listForDel = new ArrayList<SysUserDSEntity>();
		listForDel = sysUserDSService.getAllUserByUpdate(entity);
		AuthorizationEntity authorizationEntity = new AuthorizationEntity();
		if(listForDel.size()>0){
			for(int j = 0; j < listForDel.size(); j++){
				if("-1".equals(listForDel.get(j).getCompanyId())){
					System.out.println(0);
				}else{
					entity.setNo(listForDel.get(j).getNo());
					authorizationEntity.setKqId(entity.getKQID());
					authorizationEntity.setUserType("51");
					List<AuthorizationEntity> listUsersPrivilege = authorizationService.getPrivilegeByDoorId(authorizationEntity);
					DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_C);
					authorizationService.updateAttendanceFun(authorizationEntity);
					authorizationEntity.setDoorId(listForDel.get(j).getDoorid());
					//遍历结果集，删除用户对应控制器的权限信息
					for(int i=0;i<listUsersPrivilege.size();i++){
						authorizationEntity.setfIp(listUsersPrivilege.get(i).getfIp());
						authorizationEntity.setfControllersn(listUsersPrivilege.get(i).getfControllersn());
						authorizationEntity.setOldCardNo(entity.getNo());
						//authorizationEntity.setOldCardNo(listCard.get(0).getCardNo());
						delControllerAuthorization(authorizationEntity);
					}
					/*//插入新的门禁信息到3A
					authorizationService.addPrivilegeTo3A(authorizationEntity);*/
					//取消与门禁的交互
					//authorizationService.deletePrivilegeToEHR(authorizationEntity);
					DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
					//删除当前用户原有的门禁信息，并把新的门禁信息插入到[AccessData].[dbo].[t_d_privilege]和t_d_privilege
					authorizationService.deletePrivilegeTo3A(authorizationEntity);
					sysUserDSService.deleteUser(entity);
				}
				
			}
		}
		
		return listEHR;
	}
	/**
	 * 删除用户对应的控制器权限信息
	 * @param authorizationEntity
	 */
	public void delControllerAuthorization(AuthorizationEntity authorizationEntity){
		// 前台获得的tbcontrollerentity集合（前台加载控制器列表附带用到信息）
				ArrayList<AuthorizationEntity> tbcon = new ArrayList<AuthorizationEntity>();
				tbcon.add(authorizationEntity);
				//卡号信息(取后七位)
				String cardno = authorizationEntity.getOldCardNo();
				
				long card_no = Long.parseLong(cardno);
				// 测试实例
				//本案例中测试说明
				//控制器SN  = 229999901
				//控制器IP  = 192.168.168.123
				//电脑  IP  = 192.168.168.101
				//用于作为接收服务器的IP (本电脑IP 192.168.168.101), 接收服务器端口 (61005)
				long controllerSN;
				String controllerIP;
				//接收服务器设置在jeesite.properties的watchServerIP属性，程序从properties获取
				String watchServerIP = Global.getConfig("watchServerIP");
				int watchServerPort= Integer.parseInt(Global.getConfig("watchServerPort"));
				//循环控制器信息
				System.out.println(tbcon.size());
				for (int k = 0; k < tbcon.size(); k++) {
					System.out.println(tbcon.get(k).getfControllersn());
					controllerSN = Long.parseLong(tbcon.get(k).getfControllersn());
					System.out.println(tbcon.get(k).getfIp());
					controllerIP = tbcon.get(k).getfIp();
					deleteAlert(card_no,controllerSN,controllerIP,watchServerIP,watchServerPort);
				}
	}
public static String deleteAlert(long card_no,long controllerSN,String controllerIP,String watchServerIP,int watchServerPort) {	// TODO 自动生成的方法存根
		
		if(controllerSN==123247916){
			System.out.println("enter...");
		}
			byte[] recvBuff;
		int success =0;
		WgUdpCommShort pkt = new WgUdpCommShort();
		pkt.iDevSn = controllerSN;
		//打开udp连接
		pkt.CommOpen(controllerIP);
		//1.12	权限删除(单个删除)[功能号: 0x52] **********************************************************************************
		pkt.Reset();
		pkt.functionID = (byte) 0x52;
		pkt.iDevSn = controllerSN; 
		//要删除的权限卡号0D D7 37 00  = 0x0037D70D = 3659533 (十进制)
		long cardNOOfPrivilegeToDelete =card_no;
		System.arraycopy(WgUdpCommShort.longToByte(cardNOOfPrivilegeToDelete) , 0, pkt.data, 0, 4);

		recvBuff = pkt.run();
		success =0;
		if (recvBuff != null)
		{
			if (WgUdpCommShort.getIntByByte(recvBuff[8]) == 1)
			{
				//这时 刷卡号为= 0x0037D70D = 3659533 (十进制)的卡, 1号门继电器不会动作.
				success =1;
			}
		}
		pkt.CommClose();
		 return "success";	
	}
}