/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.accessaontrol.web;

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

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.tdk3A.accessaontrol.entity.AccessControlEntity;
import com.thinkgem.jeesite.tdk3A.accessaontrol.service.AccessControlService;

/**
 * 门禁管理Controller
 * @author baifu
 * @version 2017-03-12
 */
@Controller
@RequestMapping(value = "${adminPath}/accessaontrol/accessControl")
public class AccessControlController extends BaseController {

	@Autowired
	private AccessControlService accessControlService;
	
	/*@ModelAttribute
	public AccessControl get(@RequestParam(required=false) String id) {
		AccessControl entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = accessControlService.get(id);
		}
		if (entity == null){
			entity = new AccessControl();
		}
		return entity;
	}*/
	
	/**
	 * 字典表
	 * @param accessControl
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("accessaontrol:dictionaryTable:view")
	@RequestMapping(value = {"dictionaryTable"})
	public String dictionaryManage(AccessControlEntity accessControl, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*Page<AccessControl> page = accessControlService.findPage(new Page<AccessControl>(request, response), accessControl); 
		model.addAttribute("page", page);*/
		return "jeesite/tdk3A/accessaontrol/dictionaryTable";
	}
	/**
	 * 部门对应门禁查看
	 * @param accessControl
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("accessaontrol:accessControl:view")
	@RequestMapping(value = {"ccessControlCheck"})
	public String ccessControlCheck(AccessControlEntity accessControl, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*Page<AccessControlEntity> page = accessControlService.findPage(new Page<AccessControlEntity>(request, response), accessControl); 
		model.addAttribute("page", page);*/
		return "jeesite/tdk3A/accessaontrol/ccessControlCheck";
	}
	/**
	 * 部门对应门禁查看(获取列表信息)
	 * @param accessControl
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	//@RequiresPermissions("accessaontrol:accessControl:view")
	@RequestMapping(value = {"getOfficeDooor"})
	@ResponseBody
	public List<Object> getOfficeDooor(AccessControlEntity accessControl, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Object> list = new ArrayList<Object>();
		Page<AccessControlEntity> page = accessControlService.findPage(new Page<AccessControlEntity>(request, response), accessControl);
		list.add(page);
		return list;
		/*Page<AccessControlEntity> page = accessControlService.findPage(new Page<AccessControlEntity>(request, response), accessControl); 
		model.addAttribute("page", page);*/
		//return "jeesite/tdk3A/accessaontrol/ccessControlCheck";
	}
	/**
	 * 部门对应门禁编辑
	 * @param accessControl
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("accessaontrol:accessControl:edit")
	@RequestMapping(value = {"ccessControlEdit"})
	public String ccessControlEdit(AccessControlEntity accessControl, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*Page<AccessControl> page = accessControlService.findPage(new Page<AccessControl>(request, response), accessControl); 
		model.addAttribute("page", page);*/
		//model.addAttribute("entity", accessControl);
		return "jeesite/tdk3A/accessaontrol/ccessControlEdit";
	}

	/**
	 * 获取某一区域下的所有门禁控制器
	 * @param accessControl
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"getAllDoorByArea"})
	@ResponseBody
	public List<Object> getAllDoorByArea(AccessControlEntity accessControl, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Object> result = new ArrayList<Object>();
		//获取用户角色id
		String roleIds = "";
		for(int i = 0;i<UserUtils.getUser().getRoleIdList().size();i++){
			//Role role = UserUtils.getUser().getRoleList().get(i);
			roleIds = UserUtils.getUser().getRoleIdList().get(i) +","+roleIds;
		}
		accessControl.setRoleIds(roleIds.substring(0, roleIds.length()-1));
		List<AccessControlEntity> doorList = accessControlService.getAllDoorByArea(accessControl);
		List<AccessControlEntity> doorSelectList = accessControlService.getAllDoorAreadySelect(accessControl);
		/*model.addAttribute("doorList", doorList);
		model.addAttribute("doorSelectList", doorSelectList);*/
		result.add(0, doorList);
		result.add(1, doorSelectList);
		return result;
	}
	@RequestMapping(value = {"addDoorToOffice"})
	@ResponseBody
	public List<Object> addDoorToOffice(AccessControlEntity accessControl, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Object> list = new ArrayList<Object>();
		//获取前台传过来的门禁控制器信息
		String[] doorArr = accessControl.getDoorMessage().split(",");
		//删除当前部门的门禁信息
		accessControl.setDeptId(accessControl.getOfficeId());
		accessControlService.deleteDeptConByOfficeId(accessControl);
		//将前台传过来的门禁控制器信息遍历并赋值给accessControl
		for(int i=0;i<doorArr.length;i++){
			//String[] doorList = doorArr[i].split(";");
			accessControl.setConId(doorArr[i]);
			//插入当前部门的门禁信息
			accessControlService.insertIntoDeptCon(accessControl);
		}
		list.add(0, 1);
		return list;
	}
	/*@RequiresPermissions("accessaontrol:accessControl:view")
	@RequestMapping(value = "form")
	public String form(AccessControl accessControl, Model model) {
		model.addAttribute("accessControl", accessControl);
		return "tdk3a/accessaontrol/accessControlForm";
	}

	@RequiresPermissions("accessaontrol:accessControl:edit")
	@RequestMapping(value = "save")
	public String save(AccessControl accessControl, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, accessControl)){
			return form(accessControl, model);
		}
		accessControlService.save(accessControl);
		addMessage(redirectAttributes, "保存门禁管理成功");
		return "redirect:"+Global.getAdminPath()+"/accessaontrol/accessControl/?repage";
	}
	
	@RequiresPermissions("accessaontrol:accessControl:edit")
	@RequestMapping(value = "delete")
	public String delete(AccessControl accessControl, RedirectAttributes redirectAttributes) {
		accessControlService.delete(accessControl);
		addMessage(redirectAttributes, "删除门禁管理成功");
		return "redirect:"+Global.getAdminPath()+"/accessaontrol/accessControl/?repage";
	}*/

}