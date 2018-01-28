/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.reportmanage.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.tdk3A.reportmanage.service.KqDetailDataService;
import com.thinkgem.jeesite.tdk3A.systemmanagement.entity.KqOriginalityData;

/**
 * 考勤信息同步Controller
 * @author xubaifu
 * @version 2017-05-01
 */
@Controller
@RequestMapping(value = "${adminPath}/systemmanagement/kqOriginalityData")
public class KqDetailDataController extends BaseController {

	@Autowired
	private KqDetailDataService KqDetailDataService;
	
	/*@ModelAttribute
	public KqOriginalityData get(@RequestParam(required=false) String id) {
		KqOriginalityData entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = KqDetailDataService.get(id);
		}
		if (entity == null){
			entity = new KqOriginalityData();
		}
		return entity;
	}
	*/
	@RequiresPermissions("systemmanagement:kqOriginalityData:view")
	@RequestMapping(value = {"list", ""})
	public String list(KqOriginalityData kqOriginalityData, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<KqOriginalityData> page = KqDetailDataService.findPage(new Page<KqOriginalityData>(request, response), kqOriginalityData); 
		model.addAttribute("page", page);
		return "jeesite/tdk3A/reportmanage/kqDetailDataList";
	}


}