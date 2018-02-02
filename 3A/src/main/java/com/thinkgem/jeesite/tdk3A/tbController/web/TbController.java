/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.tbController.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.tdk3A.authorization.entity.TBControllerEntity;
import com.thinkgem.jeesite.tdk3A.tbController.entity.TBEntity;
import com.thinkgem.jeesite.tdk3A.tbController.service.TdControllerService;

/**
 * 门禁控制器Controller
 * @author 
 * @version 
 */
@Controller
@RequestMapping(value = "${adminPath}/dbcontroller/manage")
public class TbController extends BaseController {

	@Autowired
	private TdControllerService tdControllerService;
	


	@RequiresPermissions("dbcontroller:manage:view")
	@RequestMapping(value = {"list", ""})
	public String list(TBEntity tBEntity,Model model,HttpServletRequest request, HttpServletResponse response) {
		//List<TBEntity> list = Lists.newArrayList();
		/*List<Menu> sourcelist = systemService.findAllMenu();
		Menu.sortList(list, sourcelist, Menu.getRootId(), true);*/
		
		/*Page<TBEntity> page = tdControllerService.findPage(new Page<TBEntity>(request, response), tBEntity); 
		
        model.addAttribute("page", page);*/
		return "jeesite/tdk3A/tbController/tbControllerList";
	}
	
	@RequiresPermissions("dbcontroller:manage:view")
	@RequestMapping(value = {"getTbController"})
	@ResponseBody
	public Page<TBEntity> getTbController(TBEntity tBEntity,Model model,HttpServletRequest request, HttpServletResponse response) {
		//List<TBEntity> list = Lists.newArrayList();
		/*List<Menu> sourcelist = systemService.findAllMenu();
		Menu.sortList(list, sourcelist, Menu.getRootId(), true);*/
		
		Page<TBEntity> page = tdControllerService.findPage(new Page<TBEntity>(request, response), tBEntity); 
		
		return page;
        //model.addAttribute("page", page);
		//return "jeesite/tdk3A/tbController/tbControllerList";
	}
	@RequiresPermissions("dbcontroller:manage:view")
	@RequestMapping(value = "form")
	public String form(TBEntity tBEntity, Model model) {
		TBEntity data = tdControllerService.findController(tBEntity);
		if(data == null){
			data = new TBEntity();
			data.setType("add");
		}else{
			data.setType("update");
		}
		model.addAttribute("tbController", data);
		return "jeesite/tdk3A/tbController/tbControllerForm";
	}
	
	@RequiresPermissions("dbcontroller:manage:edit")
	@RequestMapping(value = "save")
	public String save(TBEntity tBEntity, Model model, RedirectAttributes redirectAttributes) {
		tBEntity.setfDoornames(tBEntity.getfNote()+";");
		tBEntity.setfDoorid(tBEntity.getfControllerid());
		tBEntity.setfDoorname(tBEntity.getfNote());
		
		if("add".equals(tBEntity.getType())){
			tdControllerService.insertController(tBEntity);
		}
		if("update".equals(tBEntity.getType())){
			tdControllerService.updateController(tBEntity);
		}
		
		return "redirect:" + adminPath + "/dbcontroller/manage/";
	}
	
	@RequiresPermissions("dbcontroller:manage:edit")
	@RequestMapping(value = "delete")
	public String delete(TBEntity tBEntity, RedirectAttributes redirectAttributes) {
		tdControllerService.delController(tBEntity);
		return "redirect:" + adminPath + "/dbcontroller/manage/";
	}

	@RequiresPermissions("dbcontroller:manage:view")
	@RequestMapping(value = "getControllerById")
	@ResponseBody
	public List<TBEntity> getControllerById(TBEntity tBEntity, Model model) {
		List<TBEntity> list = tdControllerService.getControllerById(tBEntity);
		return list;
	}
	
}
