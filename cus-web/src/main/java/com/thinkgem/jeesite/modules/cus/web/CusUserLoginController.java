/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cus.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cus.common.ConstantsWeb;
import com.thinkgem.jeesite.modules.cus.entity.CusUser;
import com.thinkgem.jeesite.modules.cus.service.CusUserService;
import com.thinkgem.jeesite.modules.cus.utils.Base64Util;
import com.thinkgem.jeesite.modules.cus.utils.MD5Util;
import com.thinkgem.jeesite.modules.cus.utils.ResultUtil;
import com.thinkgem.jeesite.modules.cus.utils.SessionUtil;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户模块Controller
 * @author dengyn
 * @version 2019-01-16
 */
@Controller
@RequestMapping(value = "${frontPath}/cus/cusUser")
public class CusUserLoginController extends BaseController {

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

	@RequestMapping(value = "form")
	public String form(CusUser cusUser, Model model) {
		model.addAttribute("cusUser", cusUser);
		return "modules/cus/cusUserForm";
	}

	@RequestMapping(value = "save")
	public String save(CusUser cusUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cusUser)){
			return form(cusUser, model);
		}
		cusUserService.save(cusUser);
		addMessage(redirectAttributes, "保存用户成功");
		return "modules/cusf/registerSuccess";
	}
	@RequestMapping(value = "register")
	public String cusUserRegister(Model model){
		return "modules/cusf/cusUserRegister";
	}

	/**
	 * @param loginName
	 * @param password
	 * @Title: userLogin
	 * @Description: 本系统用户登录验证
	 * @return: json
	 */
	@ResponseBody
	@RequestMapping("/userLogin.async")
	public Map<String, Object> userLogin(HttpServletRequest request, @RequestParam String loginName, @RequestParam String password) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		CusUser cusUser = new CusUser();
		int optcode = ConstantsWeb.INVALID_PARAMETER;
		logger.info("userLogin.async======loginName:" + loginName + "======password:" );
		cusUser.setLoginName(loginName);
		cusUser.setPassword(Base64Util.encode(MD5Util.getMD5(Base64Util.decode(password) + ConstantsWeb.PASSWORD_SUFFIX)));
		CusUser sysUserResult = new CusUser();
		sysUserResult = cusUserService.get(cusUser);
		if (sysUserResult != null) {
			logger.info("userLogin is success .");
			optcode = ConstantsWeb.SUCCESS;
			resultMap.put("cusUser", sysUserResult);
			cusUser = new CusUser();
			cusUser.setLoginName(sysUserResult.getLoginName());
			cusUser.setId(sysUserResult.getId());
			SessionUtil.setSession(request,ConstantsWeb.SESSION_USER_INFO,cusUser);
		} else {
			logger.info("userLogin is failed .");
		}
		ResultUtil.resultMessage(resultMap,optcode,null);
		return resultMap;
	}

	/**
	 * @Title: userLogin
	 * @Description: 第三方认证登录
	 * @return: json
	 */
	@ResponseBody
	@RequestMapping("/userOauth.async")
	public Map<String, Object> userLogin(HttpServletRequest request,CusUser cusUser) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String optinfo = "";
		int optcode = -1;

		try{
			if(cusUser != null){
				CusUser userQuery = new CusUser();
				userQuery.setUserSid(cusUser.getUserSid());
				userQuery.setUserSource(cusUser.getUserSource());

				CusUser queryResult = cusUserService.get(userQuery);
				if(queryResult != null){ //用户非初次认证登录本系统
					cusUser.setId(queryResult.getId());
					cusUser.setUpdateDate(new Date());
				}else{
					cusUser.setCreateBy(new User("1"));
					cusUser.setCreateDate(new Date());
				}
				cusUserService.save(cusUser);
				optinfo = "login success .";
				optcode = ConstantsWeb.SUCCESS;
			}else{
				optinfo = "cusUser is null .";
				optcode = ConstantsWeb.INVALID_PARAMETER;
				logger.error(optinfo);
			}
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			optinfo = "error occured ! error messeage ---> "+e.getMessage();
			optcode = ConstantsWeb.UNKNOWN_ERROR;
		}
		resultMap = ResultUtil.resultMessage(resultMap,optcode,optinfo);
		return resultMap;
	}

	@RequiresPermissions("cus:cusUser:edit")
	@RequestMapping(value = "delete")
	public String delete(CusUser cusUser, RedirectAttributes redirectAttributes) {
		cusUserService.delete(cusUser);
		addMessage(redirectAttributes, "删除用户成功");
		return "redirect:"+Global.getAdminPath()+"/cus/cusUser/?repage";
	}

}