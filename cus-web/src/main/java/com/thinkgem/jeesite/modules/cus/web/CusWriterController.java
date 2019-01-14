/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cus.web;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.cus.entity.CusWriter;
import com.thinkgem.jeesite.modules.cus.service.CusWriterService;

/**
 * 写手Controller
 * @author dengyn
 * @version 2019-01-14
 */
@Controller
@RequestMapping(value = "${adminPath}/cus/cusWriter")
public class CusWriterController extends BaseController {

	@Autowired
	private CusWriterService cusWriterService;
	
	@ModelAttribute
	public CusWriter get(@RequestParam(required=false) String id) {
		CusWriter entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cusWriterService.get(id);
		}
		if (entity == null){
			entity = new CusWriter();
		}
		return entity;
	}
	
	@RequiresPermissions("cus:cusWriter:view")
	@RequestMapping(value = {"list", ""})
	public String list(CusWriter cusWriter, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CusWriter> page = cusWriterService.findPage(new Page<CusWriter>(request, response), cusWriter); 
		model.addAttribute("page", page);
		return "modules/cus/cusWriterList";
	}

	@RequiresPermissions("cus:cusWriter:view")
	@RequestMapping(value = "form")
	public String form(CusWriter cusWriter, Model model) {
		model.addAttribute("cusWriter", cusWriter);
		return "modules/cus/cusWriterForm";
	}

	@RequiresPermissions("cus:cusWriter:edit")
	@RequestMapping(value = "save")
	public String save(CusWriter cusWriter, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cusWriter)){
			return form(cusWriter, model);
		}
		cusWriterService.save(cusWriter);
		addMessage(redirectAttributes, "保存写手成功");
		return "redirect:"+Global.getAdminPath()+"/cus/cusWriter/?repage";
	}
	
	@RequiresPermissions("cus:cusWriter:edit")
	@RequestMapping(value = "delete")
	public String delete(CusWriter cusWriter, RedirectAttributes redirectAttributes) {
		cusWriterService.delete(cusWriter);
		addMessage(redirectAttributes, "删除写手成功");
		return "redirect:"+Global.getAdminPath()+"/cus/cusWriter/?repage";
	}

}