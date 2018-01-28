/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.dashboard.web;

import java.util.List;

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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.tdk3A.dashboard.entity.DashboardEntity;
import com.thinkgem.jeesite.tdk3A.dashboard.service.DashboardService;

/**
 * 主页仪表盘Controller
 * @author baifu
 * @version 2017-03-03
 */
@Controller
@RequestMapping(value = "${adminPath}/dashboard/dashboard")
public class DashboardController extends BaseController {

	@Autowired
	private DashboardService dashboardService;
	
	
	@RequiresPermissions("dashboard:dashboard:view")
	@RequestMapping(value = {"list", ""})
	public String list(DashboardEntity dashboard, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*Page<DashboardEntity> page = dashboardService.findPage(new Page<DashboardEntity>(request, response), dashboard); 
		model.addAttribute("page", page);*/
		return "jeesite/tdk3A/dashboard/index";
	}

	@RequestMapping(value = {"getMealsStatistics"})
	@ResponseBody
	public List<DashboardEntity> getMealsStatistics(DashboardEntity dashboard, HttpServletRequest request, HttpServletResponse response, Model model) {
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_E);
		List<DashboardEntity> list =  dashboardService.getMealsStatistics(dashboard);
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
		return list;
	}
	/*@RequiresPermissions("dashboard:dashboard:view")
	@RequestMapping(value = "form")
	public String form(DashboardEntity dashboard, Model model) {
		model.addAttribute("dashboard", dashboard);
		return "tdk3a/dashboard/dashboardForm";
	}

	@RequiresPermissions("dashboard:dashboard:edit")
	@RequestMapping(value = "save")
	public String save(DashboardEntity dashboard, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dashboard)){
			return form(dashboard, model);
		}
		dashboardService.save(dashboard);
		addMessage(redirectAttributes, "保存主页仪表盘成功");
		return "redirect:"+Global.getAdminPath()+"/dashboard/dashboard/?repage";
	}
	
	@RequiresPermissions("dashboard:dashboard:edit")
	@RequestMapping(value = "delete")
	public String delete(DashboardEntity dashboard, RedirectAttributes redirectAttributes) {
		dashboardService.delete(dashboard);
		addMessage(redirectAttributes, "删除主页仪表盘成功");
		return "redirect:"+Global.getAdminPath()+"/dashboard/dashboard/?repage";
	}*/

}