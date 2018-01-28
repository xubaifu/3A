/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.test.web;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.tdk3A.test.entity.TestSysUser;
import com.thinkgem.jeesite.tdk3A.test.service.TestSysUserService;

/**
 * 练习类Controller
 * @author baifu
 * @version 2017-03-03
 */
@Controller
@RequestMapping(value = "${adminPath}/test/testSysUser")
public class TestSysUserController extends BaseController {

	@Autowired
	private TestSysUserService testSysUserService;
	
	@ModelAttribute
	public TestSysUser get(@RequestParam(required=false) String id) {
		TestSysUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testSysUserService.get(id);
		}
		if (entity == null){
			entity = new TestSysUser();
		}
		return entity;
	}
	
	@RequiresPermissions("test:testSysUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestSysUser testSysUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TestSysUser> page = testSysUserService.findPage(new Page<TestSysUser>(request, response), testSysUser); 
		model.addAttribute("page", page);
		return "jeesite/tdk3A/test/testSysUserList";
	}

	@RequiresPermissions("test:testSysUser:view")
	@RequestMapping(value = "form")
	public String form(TestSysUser testSysUser, Model model) {
		model.addAttribute("testSysUser", testSysUser);
		return "jeesite/tdk3A/test/testSysUserForm";
	}

	@RequiresPermissions("test:testSysUser:edit")
	@RequestMapping(value = "save")
	public String save(TestSysUser testSysUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testSysUser)){
			return form(testSysUser, model);
		}
		testSysUserService.save(testSysUser);
		addMessage(redirectAttributes, "保存test成功");
		return "redirect:"+Global.getAdminPath()+"/test/testSysUser/?repage";
	}
	
	@RequiresPermissions("test:testSysUser:edit")
	@RequestMapping(value = "delete")
	public String delete(TestSysUser testSysUser, RedirectAttributes redirectAttributes) {
		testSysUserService.delete(testSysUser);
		addMessage(redirectAttributes, "删除test成功");
		return "redirect:"+Global.getAdminPath()+"/test/testSysUser/?repage";
	}

}