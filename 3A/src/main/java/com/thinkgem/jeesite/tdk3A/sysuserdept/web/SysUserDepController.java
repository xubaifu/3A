/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.sysuserdept.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.tdk3A.sysuserdept.entity.SysUserDep;
import com.thinkgem.jeesite.tdk3A.sysuserdept.service.SysUserDepService;
import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * 人与部门记录表Controller
 * @author zh
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/sysuserdept/sysUserDep")
public class SysUserDepController extends BaseController {

	@Autowired
	private SysUserDepService sysUserDepService;
	
	@ModelAttribute
	public SysUserDep get(@RequestParam(required=false) String id) {
		SysUserDep entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysUserDepService.get(id);
		}
		if (entity == null){
			entity = new SysUserDep();
		}
		return entity;
	}
	
	@RequiresPermissions("sysuserdept:sysUserDep:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysUserDep sysUserDep, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysUserDep> page = sysUserDepService.findPage(new Page<SysUserDep>(request, response), sysUserDep); 
		model.addAttribute("page", page);
		return "tdk3a/sysuserdept/sysUserDepList";
	}

	@RequiresPermissions("sysuserdept:sysUserDep:view")
	@RequestMapping(value = "form")
	public String form(SysUserDep sysUserDep, Model model) {
		model.addAttribute("sysUserDep", sysUserDep);
		return "tdk3a/sysuserdept/sysUserDepForm";
	}

	@RequiresPermissions("sysuserdept:sysUserDep:edit")
	@RequestMapping(value = "save")
	public String save(SysUserDep sysUserDep, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysUserDep)){
			return form(sysUserDep, model);
		}
		sysUserDepService.save(sysUserDep);
		addMessage(redirectAttributes, "保存人与部门记录表成功");
		return "redirect:"+Global.getAdminPath()+"/sysuserdept/sysUserDep/?repage";
	}
	
	@RequiresPermissions("sysuserdept:sysUserDep:edit")
	@RequestMapping(value = "delete")
	public String delete(SysUserDep sysUserDep, RedirectAttributes redirectAttributes) {
		sysUserDepService.delete(sysUserDep);
		addMessage(redirectAttributes, "删除人与部门记录表成功");
		return "redirect:"+Global.getAdminPath()+"/sysuserdept/sysUserDep/?repage";
	}

}