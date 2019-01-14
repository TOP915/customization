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
import com.thinkgem.jeesite.modules.cus.entity.CusOrder;
import com.thinkgem.jeesite.modules.cus.service.CusOrderService;

/**
 * 订单Controller
 * @author dengyn
 * @version 2019-01-14
 */
@Controller
@RequestMapping(value = "${adminPath}/cus/cusOrder")
public class CusOrderController extends BaseController {

	@Autowired
	private CusOrderService cusOrderService;
	
	@ModelAttribute
	public CusOrder get(@RequestParam(required=false) String id) {
		CusOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cusOrderService.get(id);
		}
		if (entity == null){
			entity = new CusOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("cus:cusOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(CusOrder cusOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CusOrder> page = cusOrderService.findPage(new Page<CusOrder>(request, response), cusOrder); 
		model.addAttribute("page", page);
		return "modules/cus/cusOrderList";
	}

	@RequiresPermissions("cus:cusOrder:view")
	@RequestMapping(value = "form")
	public String form(CusOrder cusOrder, Model model) {
		model.addAttribute("cusOrder", cusOrder);
		return "modules/cus/cusOrderForm";
	}

	@RequiresPermissions("cus:cusOrder:edit")
	@RequestMapping(value = "save")
	public String save(CusOrder cusOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cusOrder)){
			return form(cusOrder, model);
		}
		cusOrderService.save(cusOrder);
		addMessage(redirectAttributes, "保存订单成功");
		return "redirect:"+Global.getAdminPath()+"/cus/cusOrder/?repage";
	}
	
	@RequiresPermissions("cus:cusOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(CusOrder cusOrder, RedirectAttributes redirectAttributes) {
		cusOrderService.delete(cusOrder);
		addMessage(redirectAttributes, "删除订单成功");
		return "redirect:"+Global.getAdminPath()+"/cus/cusOrder/?repage";
	}

}