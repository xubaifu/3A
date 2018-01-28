package com.thinkgem.jeesite.test.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.test.entity.TestUserEntity;
import com.thinkgem.jeesite.test.service.TestUserService;

@Controller
@RequestMapping(value = "${adminPath}/test/testPage")
public class TestPage extends BaseController {
	@Resource
	private TestUserService testUserService;
	
	@RequiresPermissions("test:testPage:view")
	@RequestMapping(value = {"list", ""})
	public String list(String name, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "jeesite/test/test";
	}
	
	@RequiresPermissions("test:testPage:edit")
	@RequestMapping(value = {"finduser"})
	@ResponseBody
	public List<TestUserEntity> findUser(String name, HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("name", name);
		List<TestUserEntity> result = testUserService.findUser(params); 
		model.addAttribute("list", result);
		return result;
	}
}
