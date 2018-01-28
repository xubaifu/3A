/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.authorizationManage.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.tdk3A.authorization.entity.AttendanceEntity;
import com.thinkgem.jeesite.tdk3A.authorizationManage.entity.AuthorizationManageEntity;
import com.thinkgem.jeesite.tdk3A.authorizationManage.service.AuthorizationManageService;

/**
 * 授权管理Controller
 * @author baifu
 * @version 2017-03-05
 */
@Controller
@RequestMapping(value = "${adminPath}/authorizationManage/authorizationManage")
public class AuthorizationManageController extends BaseController {

	@Autowired
	private AuthorizationManageService authorizationManageService;
	
	/*@ModelAttribute
	public AuthorizationManageEntity get(@RequestParam(required=false) String id) {
		AuthorizationManageEntity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testSysUserService.get(id);
		}
		if (entity == null){
			entity = new AuthorizationManageEntity();
		}
		return entity;
	}*/
	/**
	 * 考勤机授权
	 * @param authorizationManageEntity
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("authorizationManage:attendance:view")
	@RequestMapping(value = {"attendance"})
	public String attendanceList(AttendanceEntity attendanceEntity, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*Page<AuthorizationManageEntity> page = authorizationManageService.findPage(new Page<AuthorizationManageEntity>(request, response), authorizationManageEntity); 
		model.addAttribute("page", page);*/
		List<AttendanceEntity> list = authorizationManageService.getAttendanceController(attendanceEntity);
		model.addAttribute("list",list);
		return "jeesite/tdk3A/authorizationManage/attendanceManage";
	}
	
	/**
	 * 根据部门获取用户
	 * @param authorizationManageEntity
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"getUserByOffice"})
	@ResponseBody
	public List<AuthorizationManageEntity> getUserByOffice(AuthorizationManageEntity authorizationManageEntity, HttpServletRequest request, HttpServletResponse response, Model model){
		List<AuthorizationManageEntity> list = new ArrayList<AuthorizationManageEntity>();
		list = authorizationManageService.getUserByOffice(authorizationManageEntity);
		return list;
	}
	/** 
	 * 门禁器授权
	 * @param authorizationManageEntity
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("authorizationManage:accessControl:view")
	@RequestMapping(value = {"accessControl"})
	public String accessControlList(AuthorizationManageEntity authorizationManageEntity, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*Page<AuthorizationManageEntity> page = authorizationManageService.findPage(new Page<AuthorizationManageEntity>(request, response), authorizationManageEntity); 
		model.addAttribute("page", page);*/
		return "jeesite/tdk3A/authorizationManage/accessControl";
	}
	
	/**
	 * 餐卡机授权
	 * @param authorizationManageEntity
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("authorizationManage:mealCard:view")
	@RequestMapping(value = {"mealCard"})
	public String mealCardlList(AuthorizationManageEntity authorizationManageEntity, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*Page<AuthorizationManageEntity> page = authorizationManageService.findPage(new Page<AuthorizationManageEntity>(request, response), authorizationManageEntity); 
		model.addAttribute("page", page);*/
		return "jeesite/tdk3A/authorizationManage/mealCardManage";
	}
	/*@RequiresPermissions("authorizationmanagement:testSysUser:view")
	@RequestMapping(value = "form")
	public String form(AuthorizationManageEntity testSysUser, Model model) {
		model.addAttribute("testSysUser", testSysUser);
		return "tdk3a/authorizationmanagement/testSysUserForm";
	}

	@RequiresPermissions("authorizationmanagement:testSysUser:edit")
	@RequestMapping(value = "save")
	public String save(AuthorizationManageEntity testSysUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testSysUser)){
			return form(testSysUser, model);
		}
		testSysUserService.save(testSysUser);
		addMessage(redirectAttributes, "保存授权管理成功");
		return "redirect:"+Global.getAdminPath()+"/authorizationmanagement/testSysUser/?repage";
	}
	
	@RequiresPermissions("authorizationmanagement:testSysUser:edit")
	@RequestMapping(value = "delete")
	public String delete(AuthorizationManageEntity testSysUser, RedirectAttributes redirectAttributes) {
		testSysUserService.delete(testSysUser);
		addMessage(redirectAttributes, "删除授权管理成功");
		return "redirect:"+Global.getAdminPath()+"/authorizationmanagement/testSysUser/?repage";
	}*/

}