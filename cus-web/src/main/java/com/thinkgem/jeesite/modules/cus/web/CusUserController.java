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
import com.thinkgem.jeesite.modules.cus.entity.CusUser;
import com.thinkgem.jeesite.modules.cus.service.CusUserService;

/**
 * cus用户登录Controller
 * @author dengyn
 * @version 2019-01-14
 */
@Controller
@RequestMapping(value = "${adminPath}/cus/cusUser")
public class CusUserController extends BaseController {

	@Autowired
	private CusUserService cusUserService;
	
	@ModelAttribute
	public CusUser get(@RequestParam(required=false) String id) {
		CusUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cusUserService.get(id);
		}
		if (entity == null){
			entity = new CusUser();
		}
		return entity;
	}
	
	@RequiresPermissions("cus:cusUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(CusUser cusUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CusUser> page = cusUserService.findPage(new Page<CusUser>(request, response), cusUser); 
		model.addAttribute("page", page);
		return "modules/cus/cusUserList";
	}

	@RequiresPermissions("cus:cusUser:view")
	@RequestMapping(value = "form")
	public String form(CusUser cusUser, Model model) {
		model.addAttribute("cusUser", cusUser);
		return "modules/cus/cusUserForm";
	}

	@RequiresPermissions("cus:cusUser:edit")
	@RequestMapping(value = "save")
	public String save(CusUser cusUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cusUser)){
			return form(cusUser, model);
		}
		cusUserService.save(cusUser);
		addMessage(redirectAttributes, "保存用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/cus/cusUser/?repage";
	}
	
	@RequiresPermissions("cus:cusUser:edit")
	@RequestMapping(value = "delete")
	public String delete(CusUser cusUser, RedirectAttributes redirectAttributes) {
		cusUserService.delete(cusUser);
		addMessage(redirectAttributes, "删除用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/cus/cusUser/?repage";
	}

}