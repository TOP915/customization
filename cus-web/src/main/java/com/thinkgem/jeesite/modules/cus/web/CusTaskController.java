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
import com.thinkgem.jeesite.modules.cus.entity.CusTask;
import com.thinkgem.jeesite.modules.cus.service.CusTaskService;

/**
 * 任务Controller
 * @author dengyn
 * @version 2019-01-16
 */
@Controller
@RequestMapping(value = "${adminPath}/cus/cusTask")
public class CusTaskController extends BaseController {

	@Autowired
	private CusTaskService cusTaskService;
	
	@ModelAttribute
	public CusTask get(@RequestParam(required=false) String id) {
		CusTask entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cusTaskService.get(id);
		}
		if (entity == null){
			entity = new CusTask();
		}
		return entity;
	}
	
	@RequiresPermissions("cus:cusTask:view")
	@RequestMapping(value = {"list", ""})
	public String list(CusTask cusTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CusTask> page = cusTaskService.findPage(new Page<CusTask>(request, response), cusTask); 
		model.addAttribute("page", page);
		return "modules/cus/cusTaskList";
	}

	@RequiresPermissions("cus:cusTask:view")
	@RequestMapping(value = "form")
	public String form(CusTask cusTask, Model model) {
		model.addAttribute("cusTask", cusTask);
		return "modules/cus/cusTaskForm";
	}

	@RequiresPermissions("cus:cusTask:edit")
	@RequestMapping(value = "save")
	public String save(CusTask cusTask, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cusTask)){
			return form(cusTask, model);
		}
		cusTaskService.save(cusTask);
		addMessage(redirectAttributes, "保存任务成功");
		return "redirect:"+Global.getAdminPath()+"/cus/cusTask/?repage";
	}
	
	@RequiresPermissions("cus:cusTask:edit")
	@RequestMapping(value = "delete")
	public String delete(CusTask cusTask, RedirectAttributes redirectAttributes) {
		cusTaskService.delete(cusTask);
		addMessage(redirectAttributes, "删除任务成功");
		return "redirect:"+Global.getAdminPath()+"/cus/cusTask/?repage";
	}

}