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
import com.thinkgem.jeesite.modules.cus.entity.CusTaskFile;
import com.thinkgem.jeesite.modules.cus.service.CusTaskFileService;

/**
 * 任务文件模块Controller
 * @author dengyn
 * @version 2019-01-14
 */
@Controller
@RequestMapping(value = "${adminPath}/cus/cusTaskFile")
public class CusTaskFileController extends BaseController {

	@Autowired
	private CusTaskFileService cusTaskFileService;
	
	@ModelAttribute
	public CusTaskFile get(@RequestParam(required=false) String id) {
		CusTaskFile entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cusTaskFileService.get(id);
		}
		if (entity == null){
			entity = new CusTaskFile();
		}
		return entity;
	}
	
	@RequiresPermissions("cus:cusTaskFile:view")
	@RequestMapping(value = {"list", ""})
	public String list(CusTaskFile cusTaskFile, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CusTaskFile> page = cusTaskFileService.findPage(new Page<CusTaskFile>(request, response), cusTaskFile); 
		model.addAttribute("page", page);
		return "modules/cus/cusTaskFileList";
	}

	@RequiresPermissions("cus:cusTaskFile:view")
	@RequestMapping(value = "form")
	public String form(CusTaskFile cusTaskFile, Model model) {
		model.addAttribute("cusTaskFile", cusTaskFile);
		return "modules/cus/cusTaskFileForm";
	}

	@RequiresPermissions("cus:cusTaskFile:edit")
	@RequestMapping(value = "save")
	public String save(CusTaskFile cusTaskFile, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cusTaskFile)){
			return form(cusTaskFile, model);
		}
		cusTaskFileService.save(cusTaskFile);
		addMessage(redirectAttributes, "保存任务文件成功");
		return "redirect:"+Global.getAdminPath()+"/cus/cusTaskFile/?repage";
	}
	
	@RequiresPermissions("cus:cusTaskFile:edit")
	@RequestMapping(value = "delete")
	public String delete(CusTaskFile cusTaskFile, RedirectAttributes redirectAttributes) {
		cusTaskFileService.delete(cusTaskFile);
		addMessage(redirectAttributes, "删除任务文件成功");
		return "redirect:"+Global.getAdminPath()+"/cus/cusTaskFile/?repage";
	}

}