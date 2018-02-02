/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.reportmanage.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.db.CustomerContextHolder;
import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.tdk3A.reportmanage.entity.ReportManage;
import com.thinkgem.jeesite.tdk3A.reportmanage.service.ReportManageService;

/**
 * 报表统计Controller
 * @author baifu
 * @version 2017-03-05
 */
@Controller
@RequestMapping(value = "${adminPath}/reportmanage/reportManage")
public class ReportManageController extends BaseController {

	@Autowired
	private ReportManageService reportManageService;
	
	/*@ModelAttribute
	public ReportManage get(@RequestParam(required=false) String id) {
		ReportManage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = reportManageService.get(id);
		}
		if (entity == null){
			entity = new ReportManage();
		}
		return entity;
	}*/
	
	/**
	 * 餐卡报表统计（明细查询）
	 * @param reportManage
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("reportmanage:mealCardReport:view")
	@RequestMapping(value = {"mealCardReport"})
	public String MealCardReport(ReportManage reportManage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ReportManage> page = reportManageService.findPage(new Page<ReportManage>(request, response), reportManage); 
		model.addAttribute("page", page);
		return "jeesite/tdk3A/reportmanage/mealCardReport";
	}
	/**
	 * 考勤卡报表统计
	 * @param reportManage
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("reportmanage:attendanceReport:view")
	@RequestMapping(value = {"attendanceReport"})
	public String AttendanceReport(ReportManage reportManage, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*Page<ReportManage> page = reportManageService.findPage(new Page<ReportManage>(request, response), reportManage); 
		model.addAttribute("page", page);*/
		return "jeesite/tdk3A/reportmanage/attendanceReport";
	}
	
	/**
	 * 餐卡报表统计（个人统计查询）
	 * @param reportManage
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("reportmanage:mealCardReport:view")
	@RequestMapping(value = {"findPersonList"})
	public String findPersonList(ReportManage reportManage, HttpServletRequest request, HttpServletResponse response, Model model) {
		DynamicDataSource.setCurrentLookupKey(CustomerContextHolder.DATA_SOURCE_A);
		Page<ReportManage> page = reportManageService.findPersonList(new Page<ReportManage>(request, response), reportManage); 
		model.addAttribute("page", page);
		List<ReportManage> sumResult = reportManageService.sum(reportManage);
		model.addAttribute("sum", sumResult);
		return "jeesite/tdk3A/reportmanage/mealCardPerson";
	}


}