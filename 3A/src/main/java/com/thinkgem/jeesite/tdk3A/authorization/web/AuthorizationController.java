/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.authorization.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wiegand.at8000.WgUdpCommShort;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.db.CustomerContextHolder;
import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.tdk3A.authorization.entity.AuthorizationEntity;
import com.thinkgem.jeesite.tdk3A.authorization.service.AuthorizationService;
import com.thinkgem.jeesite.tdk3A.sysuserdept.entity.SysUserDep;
import com.thinkgem.jeesite.tdk3A.sysuserdept.service.SysUserDepService;
import com.thinkgem.jeesite.tdk3A.tbController.entity.TBEntity;
import com.thinkgem.jeesite.tdk3A.tbController.service.TdControllerService;

/**
 * 一卡通统一授权Controller
 * @author baifu
 * @version 2017-03-04
 */
@Controller
@RequestMapping(value = "${adminPath}/authorization/authorization")
public class AuthorizationController extends BaseController {

	@Autowired
	private AuthorizationService authorizationService;
	@Autowired
	private SysUserDepService sysuserdepservice;
	@Autowired
	private TdControllerService tdControllerService;
	/*@ModelAttribute
	public AuthorizationEntity get(@RequestParam(required=false) String id) {
		AuthorizationEntity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = authorizationService.get(id);
		}
		if (entity == null){
			entity = new AuthorizationEntity();
		}
		return entity;
	}*/
	
	/**
	 * 跳转到一卡通授权列表
	 * @param authorization
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("authorization:authorization:view")
	@RequestMapping(value = {"list", ""})
	public String list(AuthorizationEntity authorization, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*Page<AuthorizationEntity> page = authorizationService.findPage(new Page<AuthorizationEntity>(request, response), authorization); 
		model.addAttribute("page", page);*/
		return "jeesite/tdk3A/authorization/authorizationList";
	}
	/**
	 * 获取一卡通授权列表
	 * @param authorizationEntity
	 * @return
	 */
	@RequestMapping(value = "getAuthorizationList")
	@ResponseBody
	public List<Object> getAuthorizationList(AuthorizationEntity authorizationEntity, HttpServletRequest request, HttpServletResponse response, Model model){
		List<Object> list = new ArrayList<Object>();//authorizationService.getAuthorizationList(authorizationEntity);
		//获取用户角色id
		String roleIds = "";
		for(int i = 0;i<UserUtils.getUser().getRoleIdList().size();i++){
			//Role role = UserUtils.getUser().getRoleList().get(i);
			roleIds = UserUtils.getUser().getRoleIdList().get(i) +","+roleIds;
		}
		authorizationEntity.setRoleIds(roleIds.substring(0, roleIds.length()-1));
		
		Page<AuthorizationEntity> page = authorizationService.findPage(new Page<AuthorizationEntity>(request, response), authorizationEntity); 
		list.add(page);
		return list;
	}
	/**
	 * 跳转到员工授权编辑页面
	 * @param authorization
	 * @param model
	 * @return
	 */
	@RequiresPermissions("authorization:authorization:edit")
	@RequestMapping(value = "form")
	public String form(AuthorizationEntity authorization, Model model) {
		

		List<AuthorizationEntity> listUserDoor=null;
		//获取授权信息
		List<AuthorizationEntity> list = authorizationService.getAuthorizationById(authorization);
		User user = new User();
		user.setId(list.get(0).getUserId());
		SysUserDep sysUserDep = new SysUserDep();
		sysUserDep.setUser(user);
		List<SysUserDep> sysUserDepList = sysuserdepservice.findList(sysUserDep);
		
		if(sysUserDepList.size()!=0){
			sysUserDep = sysUserDepList.get(0);
			authorization.setDept_id(sysUserDep.getDeptId());
			if(sysUserDep.getDeptId()!=null&&!"".equals(sysUserDep.getDeptId())&&(sysUserDep.getDeptId()).equals(list.get(0).getOfficeId())){
				listUserDoor = authorizationService.getDoorByUserAuthorzation(authorization);
			}else{
				listUserDoor = authorizationService.getDoorByDept(authorization);
			}
		}else{
			authorization.setDept_id(list.get(0).getOfficeId());
			listUserDoor = authorizationService.getDoorByDept(authorization);
		}
		
		//获取用户角色id
		String roleIds = "";
		for(int i = 0;i<UserUtils.getUser().getRoleIdList().size();i++){
			//Role role = UserUtils.getUser().getRoleList().get(i);
			roleIds = UserUtils.getUser().getRoleIdList().get(i) +","+roleIds;
		}
		authorization.setRoleIds(roleIds.substring(0, roleIds.length()-1));
		//获取门禁列表
		List<AuthorizationEntity> listDoor = authorizationService.getAllDoor(authorization);
		//获取考勤机列表
		List<AuthorizationEntity> listAttendance = authorizationService.getAttendanceController(authorization);
		//获取用户权限下所有的门禁控制器
		 
		model.addAttribute("list", list);
		model.addAttribute("listDoor", listDoor);
		model.addAttribute("listAttendance", listAttendance);
		model.addAttribute("listUserDoor", listUserDoor);
		return "jeesite/tdk3A/authorization/authorizationForm";
	}
	
	/**
	 * 考勤机授权&& 门禁授权&& 餐卡授权
	 * @param authorizationEntity
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "addAttendanceFun")
	@ResponseBody
	public List<Object> addAttendanceFun(AuthorizationEntity authorizationEntity, HttpServletRequest request, HttpServletResponse response, Model model){
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
		List<AuthorizationEntity> list = new ArrayList<AuthorizationEntity>();
		List<Object> result = new ArrayList<Object>();
		//查询当前用户是否已经存在卡号信息，若不存在则为新员工，并将卡号信息插入到sys_card表，把用户信息插入到考勤系统dt_user表
		List<AuthorizationEntity> listCount = authorizationService.getUserCardCount(authorizationEntity);
		//获取前台传过来的新的门禁信息并存在数组中备用
		String[] doorArr = authorizationEntity.getDoorMessage().split(",");
		
		String cardNo = authorizationEntity.getCardNo();
		
		//String cqCardNo = cardNo; 考勤取员工号后5位并在前面加0
		String cqCardNo=authorizationEntity.getCardNo().substring(authorizationEntity.getCardNo().length()-5,authorizationEntity.getCardNo().length());
		cqCardNo = "0"+cqCardNo;
		
		
		if(listCount != null && listCount.get(0) != null){
			//新员工(未发卡)（userno为空）
			if("0".equals(listCount.get(0).getNum())){
				
				//新员工,将卡号信息插入到sys_card表，
				authorizationService.addUserCard(authorizationEntity);
			
				/************考勤授权********************/
				//把用户信息插入到EHR系统dt_user表
				/*DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_C);
				//现根据用户的no查询该用户是否有授权，有则更新，没有则添加
				authorizationEntity.setCardNo(cqCardNo);
				list = authorizationService.getAttendanceByUserNo(authorizationEntity);
				if("0".equals(list.get(0).getNum())){
					authorizationService.addAttendanceFun(authorizationEntity);
				}else{
					authorizationService.updateAttendanceFun(authorizationEntity);
				}*/
				/************门禁授权********************/
				authorizationEntity.setCardNo(cardNo);
				

				//若是新员工，需要先把所有门禁遍历一遍，然后删除所有门禁权限（首次赋权的老员工需要此操作）
				//获取所有门禁信息
				List<TBEntity> allControllerList = tdControllerService.getAllController();
				//遍历删除门禁信息
				for(int i = 0; i < allControllerList.size(); i++){
					authorizationEntity.setOldCardNo(cardNo);
					authorizationEntity.setfIp(allControllerList.get(i).getfIp());
					authorizationEntity.setfControllersn(allControllerList.get(i).getfControllersn());
					delControllerAuthorization(authorizationEntity);
				}
				
				
				//插入当前用户门禁权限信息到考勤系统中的[AccessData].[dbo].[t_d_privilege]
				//DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_D);
				//控制器和用户关联信息保存到3A数据库
				//需要循环前台配置的控制器信息，然后插入到数据库并授权
				DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
				for(int i=0;i<doorArr.length;i++){
					String[] doorList = doorArr[i].split(";");
					authorizationEntity.setfControllerno(doorList[0]);
					authorizationEntity.setfControllerid(doorList[1]);
					authorizationEntity.setfDoorid(doorList[2]);
					authorizationEntity.setfDoorno(doorList[3]);
					authorizationEntity.setfIp(doorList[4]);
					authorizationEntity.setfControllersn(doorList[5]);
					authorizationService.addPrivilege(authorizationEntity);
					//控制器授权
					//保存到本地-------------!!!!!!!-----------
					controllerAuthorization(authorizationEntity);
				}//授权结束
				
				//同步当前用户门禁权限信息到t_d_privilege
				//DataPrivilegeDS(authorizationEntity);
			}else{
				//不是新员工
				//获取用户可用卡号信息
				List<AuthorizationEntity> listCard = authorizationService.getUserCard(authorizationEntity);
				//如果查询结果为空，表示离职员工重回
				if(listCard.size() == 0){
					/************考勤授权********************/
					authorizationEntity.setCardNo(cardNo);
					//将卡号信息插入到sys_card表，新卡号与员工绑定
					DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
					//将卡号信息插入到sys_card表，新卡号与员工绑定
					authorizationService.addUserCard(authorizationEntity);
					//考勤系统dt_user表的用户信息
					DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_C);
					authorizationEntity.setCardNo(cqCardNo);
					//-----------!!!!!!先查询，有update，无add
					//authorizationService.updateAttendanceFun(authorizationEntity);
					list = authorizationService.getAttendanceByUserNo(authorizationEntity);
					if("0".equals(list.get(0).getNum())){
						authorizationService.addAttendanceFun(authorizationEntity);
					}else{
						authorizationService.updateAttendanceFun(authorizationEntity);
					}
					
					/************门禁授权********************/
					/************门禁授权********************/
					//获取当前用户原有的控制器权限信息
					DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
					authorizationEntity.setCardNo(cardNo);
					//获得
					List<AuthorizationEntity> listUsersPrivilege = authorizationService.getPrivilegeByDoorId(authorizationEntity);
					//遍历结果集，删除用户对应控制器的权限信息
					for(int i=0;i<listUsersPrivilege.size();i++){
						authorizationEntity.setfIp(listUsersPrivilege.get(i).getfIp());
						//authorizationEntity.setOldCardNo(listCard.get(0).getCardNo());
						authorizationEntity.setfControllersn(listUsersPrivilege.get(i).getfControllersn());
						delControllerAuthorization(authorizationEntity);
					}
					//删除当前用户原有的门禁信息，并把新的门禁信息插入到[AccessData].[dbo].[t_d_privilege]和t_d_privilege
					authorizationService.deletePrivilegeTo3A(authorizationEntity);
					//插入新的门禁信息到3A------!!!!!-------需要保存到本地注释需要放开
					//authorizationService.addPrivilegeTo3A(authorizationEntity);
					//取消与门禁系统的交互
					//DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_D);
					//authorizationService.deletePrivilegeToEHR(authorizationEntity);
					/*//插入新的门禁信息到门禁系统
					authorizationService.addPrivilege(authorizationEntity);*/
					//控制器授权
					//需要循环获取每一个控制器doorMessage，然后插入到数据库并授权
					//String[] doorArr = authorizationEntity.getDoorMessage().split(",");
					DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
					for(int i=0;i<doorArr.length;i++){
						String[] doorList = doorArr[i].split(";");
						authorizationEntity.setfControllerno(doorList[0]);
						authorizationEntity.setfControllerid(doorList[1]);
						authorizationEntity.setfDoorid(doorList[2]);
						authorizationEntity.setfDoorno(doorList[3]);
						authorizationEntity.setfIp(doorList[4]);
						authorizationEntity.setfControllersn(doorList[5]);
						//保存到本地需要放开注释--------!!!!!!!--------
						authorizationService.addPrivilege(authorizationEntity);
						//控制器授权
						controllerAuthorization(authorizationEntity);
					}//授权结束
					//同步当前用户门禁权限信息到t_d_privilege（插入新的门禁信息到3A）不需要同步
					//DataPrivilegeDS(authorizationEntity);
					
				}else{
					
					authorizationEntity.setCardNo(cardNo);
					//获取查询结果中的卡号，与页面中获取用户的卡号对比，若卡号相同，更改权限结束，若不同换新卡
					if(authorizationEntity.getCardNo().equals(listCard.get(0).getCardNo())){
						//老员工换权限
						/************门禁授权********************/
						/************门禁授权********************/
						//获取当前用户原有的控制器权限信息
						DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
						List<AuthorizationEntity> listUsersPrivilege = authorizationService.getPrivilegeByDoorId(authorizationEntity);
						//遍历结果集，删除用户对应控制器的权限信息
						for(int i=0;i<listUsersPrivilege.size();i++){
							authorizationEntity.setfIp(listUsersPrivilege.get(i).getfIp());
							authorizationEntity.setfControllersn(listUsersPrivilege.get(i).getfControllersn());
							//authorizationEntity.setOldCardNo(listCard.get(0).getCardNo());
							delControllerAuthorization(authorizationEntity);
						}
						//删除当前用户原有的门禁信息，并把新的门禁信息插入到[AccessData].[dbo].[t_d_privilege]和t_d_privilege
						authorizationService.deletePrivilegeTo3A(authorizationEntity);
						/*//插入新的门禁信息到3A
						authorizationService.addPrivilegeTo3A(authorizationEntity);*/
						//取消与门禁系统交互
//						DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_D);
//						authorizationService.deletePrivilegeToEHR(authorizationEntity);
						/*//插入新的门禁信息到门禁系统
						authorizationService.addPrivilege(authorizationEntity);*/
						//控制器授权
						//需要循环获取每一个控制器doorMessage，然后插入到数据库并授权
						//String[] doorArr = authorizationEntity.getDoorMessage().split(",");
						DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
						for(int i=0;i<doorArr.length;i++){
							String[] doorList = doorArr[i].split(";");
							authorizationEntity.setfControllerno(doorList[0]);
							authorizationEntity.setfControllerid(doorList[1]);
							authorizationEntity.setfDoorid(doorList[2]);
							authorizationEntity.setfDoorno(doorList[3]);
							authorizationEntity.setfIp(doorList[4]);
							authorizationEntity.setfControllersn(doorList[5]);
							//保存到3A数据库，需要修改------!!!!!--------
							authorizationService.addPrivilege(authorizationEntity);
							//控制器授权
							controllerAuthorization(authorizationEntity);
						}//授权结束
						//同步当前用户门禁权限信息到t_d_privilege（插入新的门禁信息到3A）
					//	DataPrivilegeDS(authorizationEntity);
						/************考勤授权********************/
						result.add(0,"1");
						DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
						
					}else{
						//老员工换卡
						/************考勤授权********************/
						authorizationEntity.setCardNo(cardNo);
						//更新sys_user表中的door_id,赋值为max(door_id)+1(针对换卡情况)
						//authorizationService.updateDoorId(authorizationEntity);
						//若不同换新卡
						DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
						//将原卡号置为不可用
						authorizationEntity.setOldCardNo(listCard.get(0).getCardNo());
						authorizationService.updateUserCard(authorizationEntity);
						//将卡号信息插入到sys_card表，
						authorizationService.addUserCard(authorizationEntity);
						authorizationEntity.setCardNo(cqCardNo);
						//考勤系统dt_user表的用户信息
						DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_C);
						//------!!!!!-----加判断有update，无add
						//authorizationService.updateAttendanceFun(authorizationEntity);
						list = authorizationService.getAttendanceByUserNo(authorizationEntity);
						if("0".equals(list.get(0).getNum())){
							authorizationService.addAttendanceFun(authorizationEntity);
						}else{
							authorizationService.updateAttendanceFun(authorizationEntity);
						}
						
						/************门禁授权********************/
						//获取当前用户原有的控制器权限信息
						DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
						authorizationEntity.setCardNo(cardNo);
						List<AuthorizationEntity> listUsersPrivilege = authorizationService.getPrivilegeByDoorId(authorizationEntity);
						//遍历结果集，删除用户对应控制器的权限信息
						for(int i=0;i<listUsersPrivilege.size();i++){
							authorizationEntity.setfIp(listUsersPrivilege.get(i).getfIp());
							authorizationEntity.setfControllersn(listUsersPrivilege.get(i).getfControllersn());
							//authorizationEntity.setOldCardNo(listCard.get(0).getCardNo());
							delControllerAuthorization(authorizationEntity);
						}
						//删除当前用户原有的门禁信息，并把新的门禁信息插入到[AccessData].[dbo].[t_d_privilege]和t_d_privilege
						authorizationService.deletePrivilegeTo3A(authorizationEntity);
						/*//插入新的门禁信息到3A
						authorizationService.addPrivilegeTo3A(authorizationEntity);*/
						//取消与门禁系统交互 
//						DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_D);
//						authorizationService.deletePrivilegeToEHR(authorizationEntity);
						/*//插入新的门禁信息到EHR
						authorizationService.addPrivilege(authorizationEntity);*/
						//控制器授权
						//需要循环获取每一个控制器doorMessage，然后插入到数据库并授权
						//String[] doorArr = authorizationEntity.getDoorMessage().split(",");
						DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
						for(int i=0;i<doorArr.length;i++){
							String[] doorList = doorArr[i].split(";");
							authorizationEntity.setfControllerno(doorList[0]);
							authorizationEntity.setfControllerid(doorList[1]);
							authorizationEntity.setfDoorid(doorList[2]);
							authorizationEntity.setfDoorno(doorList[3]);
							authorizationEntity.setfIp(doorList[4]);
							authorizationEntity.setfControllersn(doorList[5]);
							//-----!!!!-------门禁保存到3A系统
							authorizationService.addPrivilege(authorizationEntity);
							//控制器授权
							controllerAuthorization(authorizationEntity);
						}//授权结束
						//同步当前用户门禁权限信息到t_d_privilege（插入新的门禁信息到3A）
						//DataPrivilegeDS(authorizationEntity);
					}
				}
			}
		}else{
			result.add(0,"1");
			DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
			return result;
		}
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
		authorizationEntity.setCardNo(cardNo);
		User user = new User();
		user.setId(authorizationEntity.getUserId());
		SysUserDep sysUserDep = new SysUserDep();
		SysUserDep sysUserDep_new = new SysUserDep();
		sysUserDep.setUser(user);
		List<SysUserDep> sysUserDepList = sysuserdepservice.findList(sysUserDep);
		if(sysUserDepList.size()!=0){
			if(!(sysUserDepList.get(0).getDeptId()).equals(authorizationEntity.getOfficeId())){
				sysUserDep=sysUserDepList.get(0);
				sysUserDep.setDelFlag("1");
				sysUserDep.setUpdateDate(new Date());
				sysuserdepservice.save(sysUserDep);
				sysUserDep_new.setDeptId(authorizationEntity.getOfficeId());
				sysUserDep_new.setUser(user);
				sysUserDep_new.setCreateDate(new Date()); 
				sysUserDep_new.setDelFlag("0");
				sysuserdepservice.save(sysUserDep_new);
			}
		}else{
			sysUserDep_new.setDeptId(authorizationEntity.getOfficeId());
			sysUserDep_new.setUser(user);
			sysUserDep_new.setCreateDate(new Date()); 
			sysUserDep_new.setDelFlag("0");
			sysuserdepservice.save(sysUserDep_new);
		}
		//餐卡授权
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_E);
		List<AuthorizationEntity> listSTPerson = authorizationService.findSTPersonById(authorizationEntity);
		//截取cardNo后五位，如果第一位为零，则把0去掉
		int n=5;
		int length = cardNo.length();
		String str=cardNo.substring(length-n,length);
		str = String.valueOf(Integer.valueOf(str));
		authorizationEntity.setCardNo(str);
		if("0".equals(listSTPerson.get(0).getNum())){
			authorizationService.addSTPerson(authorizationEntity);
		}else{
			authorizationService.updateSTPerson(authorizationEntity);
		}
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
		result.add(0,"1");
		return result;
	}
	
	/**
	 * 批量添加门禁授权
	 * @param authorizationEntity
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "addAccessControllerBatch")
	@ResponseBody
	public List<Object> addAccessControllerBatch(AuthorizationEntity authorizationEntity, HttpServletRequest request, HttpServletResponse response, Model model){
		List<Object> result = new ArrayList<Object>();
		
		//获取前台传过来的新的门禁信息并存在数组中备用
		String[] doorArr = authorizationEntity.getDoorMessage().split(",");
		//获取前台传过来的新的用户信息并存在数组中备用
		String[] userArr = authorizationEntity.getUserMessage().split(",");
		
		/************门禁授权********************/
		for(int k=0; k<userArr.length; k++){
			String[] duserList = userArr[k].split(";");
			if(duserList[1] == null || "undefined".equals(duserList[1]) || "null".equals(duserList[1]) || "".equals(duserList[1])){
				break;
			}
			authorizationEntity.setDoorId(duserList[2]);
			authorizationEntity.setOldCardNo(duserList[1]);
			authorizationEntity.setCardNo(duserList[1]);
			
			//获取当前用户原有的控制器权限信息
			DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
			List<AuthorizationEntity> listUsersPrivilege = authorizationService.getPrivilegeByDoorId(authorizationEntity);
			//遍历结果集，删除用户对应控制器的权限信息
			for(int i=0;i<listUsersPrivilege.size();i++){
				authorizationEntity.setfIp(listUsersPrivilege.get(i).getfIp());
				//authorizationEntity.setOldCardNo(listCard.get(0).getCardNo());
				authorizationEntity.setfControllersn(listUsersPrivilege.get(i).getfControllersn());
				delControllerAuthorization(authorizationEntity);
			}
			//删除当前用户原有的门禁信息，------并把新的门禁信息插入到[AccessData].[dbo].[t_d_privilege]和t_d_privilege
			authorizationService.deletePrivilegeTo3A(authorizationEntity);
			//取消与门襟系统的交互
			//DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_D);
			//authorizationService.deletePrivilegeToEHR(authorizationEntity);
			//控制器授权
			//需要循环获取每一个控制器doorMessage，然后插入到数据库并授权
			for(int i=0;i<doorArr.length;i++){
				String[] doorList = doorArr[i].split(";");
				authorizationEntity.setfControllerno(doorList[0]);
				authorizationEntity.setfControllerid(doorList[1]);
				authorizationEntity.setfDoorid(doorList[2]);
				authorizationEntity.setfDoorno(doorList[3]);
				authorizationEntity.setfIp(doorList[4]);
				authorizationEntity.setfControllersn(doorList[5]);
				authorizationService.addPrivilege(authorizationEntity);
				//控制器授权
				controllerAuthorization(authorizationEntity);
			}//授权结束
		}
		//同步当前用户门禁权限信息到t_d_privilege（插入新的门禁信息到3A）
		//DataPrivilegeDS(authorizationEntity);
		result.add(0,"1");
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
		return result;
	}
	
	
	/**
	 * 同步当前用户门禁权限信息到t_d_privilege
	 * @param authorization
	 */
	public void DataPrivilegeDS(AuthorizationEntity authorization){
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_D);
		List<AuthorizationEntity> listPrivilege = authorizationService.getPrivilegeFromEHR(authorization);
		int length = listPrivilege.size();
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
		//遍历[AccessData].[dbo].[t_d_privilege]所有数据，并更新到3A中的t_d_privilege
		for(int i=0;i<length;i++){
			//(#{fPrivilegerecid}, #{fDoorid}, #{fControlsegid}, #{fConsumerid}, #{fControllerid}, #{fDoorno})
			authorization.setfPrivilegerecid(listPrivilege.get(i).getfPrivilegerecid());
			authorization.setfDoorid(listPrivilege.get(i).getfDoorid());
			authorization.setfControlsegid(listPrivilege.get(i).getfControlsegid());
			authorization.setfConsumerid(listPrivilege.get(i).getfConsumerid());
			authorization.setfControllerid(listPrivilege.get(i).getfControllerid());
			authorization.setfDoorno(listPrivilege.get(i).getfDoorno());
			List<AuthorizationEntity> listCount = authorizationService.getPrivilegeById(authorization);
			if("0".equals(listCount.get(0).getNum())){
				authorizationService.addPrivilegeTo3A(authorization);
			}else{
				authorizationService.updatePrivilegeTo3A(authorization);
			}
		}
	}
	/**
	 * 控制器授权
	 * @param authorizationEntity
	 */
	public void controllerAuthorization(AuthorizationEntity authorizationEntity){
		// 前台获得的tbcontrollerentity集合（前台加载控制器列表附带用到信息）
		ArrayList<AuthorizationEntity> tbcon = new ArrayList<AuthorizationEntity>();
		tbcon.add(authorizationEntity);
		//卡号信息(取后七位)
		String cardno = authorizationEntity.getCardNo();
		
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
			addAlert(card_no,controllerSN,controllerIP,watchServerIP,watchServerPort);
		}
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
	
/*
	@RequiresPermissions("authorization:authorization:edit")
	@RequestMapping(value = "save")
	public String save(AuthorizationEntity authorization, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, authorization)){
			return form(authorization, model);
		}
		authorizationService.save(authorization);
		addMessage(redirectAttributes, "保存一卡通统一授权成功");
		return "redirect:"+Global.getAdminPath()+"/authorization/authorization/?repage";
	}
	
	@RequiresPermissions("authorization:authorization:edit")
	@RequestMapping(value = "delete")
	public String delete(AuthorizationEntity authorization, RedirectAttributes redirectAttributes) {
		authorizationService.delete(authorization);
		addMessage(redirectAttributes, "删除一卡通统一授权成功");
		return "redirect:"+Global.getAdminPath()+"/authorization/authorization/?repage";
	}*/
	public static String addAlert(long card_no, long controllerSN,
			String controllerIP, String watchServerIP, int watchServerPort) {
		byte[] recvBuff;
		int success = 0;
		WgUdpCommShort pkt = new WgUdpCommShort();
		pkt.iDevSn = controllerSN;
		// log(String.format("控制器SN = %d \r\n", controllerSN));
		// 打开udp连接
		pkt.CommOpen(controllerIP);
		// 1.11 权限添加或修改[功能号: 0x50]
		// **********************************************************************************
		// 增加卡号0D D7 37 00, 通过当前控制器的所有门
		pkt.Reset();
		pkt.functionID = (byte) 0x50;
		pkt.iDevSn = controllerSN;
		// 0D D7 37 00 要添加或修改的权限中的卡号 = 0x0037D70D = 3659533 (十进制)
		long cardNOOfPrivilege = card_no;
		// memcpy(&(pkt.data[0]), &cardNOOfPrivilege, 4);
		System.arraycopy(WgUdpCommShort.longToByte(cardNOOfPrivilege), 0,
				pkt.data, 0, 4);
		// 20 10 01 01 起始日期: 2010年01月01日 (必须大于2001年)
		pkt.data[4] = 0x20;
		pkt.data[5] = 0x10;
		pkt.data[6] = 0x01;
		pkt.data[7] = 0x01;
		// 20 29 12 31 截止日期: 2029年12月31日
		pkt.data[8] = 0x20;
		pkt.data[9] = 0x29;
		pkt.data[10] = 0x12;
		pkt.data[11] = 0x31;
		// 01 允许通过 一号门 [对单门, 双门, 四门控制器有效]
		pkt.data[12] = 0x01;
		// 01 允许通过 二号门 [对双门, 四门控制器有效]
		pkt.data[13] = 0x01; // 如果禁止2号门, 则只要设为 0x00
		// 01 允许通过 三号门 [对四门控制器有效]
		pkt.data[14] = 0x01;
		// 01 允许通过 四号门 [对四门控制器有效]
		pkt.data[15] = 0x01;

		recvBuff = pkt.run();
		success = 0;
		if (recvBuff != null) {
			if (WgUdpCommShort.getIntByByte(recvBuff[8]) == 1) {
				// 这时 刷卡号为= 0x0037D70D = 3659533 (十进制)的卡, 1号门继电器动作.
				// log("1.11 权限添加或修改	 成功...");
				success = 1;
			}
		}
		// 关闭udp连接
		pkt.CommClose();
		return "success";
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
	
	
	public static void main(String[] args) {
		String str = "123014567";
		int n=5;
		int length = str.length();
		String str1=str.substring(length-n,length);
		Integer.valueOf(str1);
		System.out.println(String.valueOf(Integer.valueOf(str1)));
	}
}