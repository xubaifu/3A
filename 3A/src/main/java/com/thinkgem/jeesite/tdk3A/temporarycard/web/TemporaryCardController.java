/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.temporarycard.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.tdk3A.temporarycard.entity.TemporaryCardEntity;
import com.thinkgem.jeesite.tdk3A.temporarycard.service.TemporaryCardService;

/**
 * 临时卡管理Controller
 * @author baifu
 * @version 2017-03-05
 */
@Controller
@RequestMapping(value = "${adminPath}/temporarycard/temporaryCard")
public class TemporaryCardController extends BaseController {

	@Autowired
	private TemporaryCardService temporaryCardService;
	
	/*@ModelAttribute
	public TemporaryCard get(@RequestParam(required=false) String id) {
		TemporaryCard entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = temporaryCardService.get(id);
		}
		if (entity == null){
			entity = new TemporaryCard();
		}
		return entity;
	}*/
	
	@RequiresPermissions("temporarycard:temporaryCard:view")
	@RequestMapping(value = {"list", ""})
	public String list(TemporaryCardEntity temporaryCard, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TemporaryCardEntity> page = temporaryCardService.findPage(new Page<TemporaryCardEntity>(request, response), temporaryCard); 
		model.addAttribute("page", page);
		return "jeesite/tdk3A/temporarycard/temporaryCard";
	}
	/**
	 * 临时卡管理
	 * @param temporaryCard
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("temporarycard:temporaryCard:view")
	@RequestMapping(value = {"addTemporaryCard"})
	public String addTemporaryCard(TemporaryCardEntity temporaryCard,Model model, RedirectAttributes redirectAttributes) {
		String str = temporaryCardService.addTemporaryCard(temporaryCard);
		addMessage(redirectAttributes, str);
		return "redirect:"+Global.getAdminPath()+"/temporarycard/temporaryCard/?repage";
	}

	/*@RequiresPermissions("temporarycard:temporaryCard:view")
	@RequestMapping(value = "form")
	public String form(TemporaryCard temporaryCard, Model model) {
		model.addAttribute("temporaryCard", temporaryCard);
		return "tdk3a/temporarycard/temporaryCardForm";
	}

	@RequiresPermissions("temporarycard:temporaryCard:edit")
	@RequestMapping(value = "save")
	public String save(TemporaryCard temporaryCard, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, temporaryCard)){
			return form(temporaryCard, model);
		}
		temporaryCardService.save(temporaryCard);
		addMessage(redirectAttributes, "保存临时卡管理成功");
		return "redirect:"+Global.getAdminPath()+"/temporarycard/temporaryCard/?repage";
	}
	
	@RequiresPermissions("temporarycard:temporaryCard:edit")
	@RequestMapping(value = "delete")
	public String delete(TemporaryCard temporaryCard, RedirectAttributes redirectAttributes) {
		temporaryCardService.delete(temporaryCard);
		addMessage(redirectAttributes, "删除临时卡管理成功");
		return "redirect:"+Global.getAdminPath()+"/temporarycard/temporaryCard/?repage";
	}*/

}