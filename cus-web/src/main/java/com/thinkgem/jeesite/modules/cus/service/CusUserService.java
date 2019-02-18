/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cus.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cus.entity.CusUser;
import com.thinkgem.jeesite.modules.cus.dao.CusUserDao;

/**
 * 用户模块Service
 * @author dengyn
 * @version 2019-02-18
 */
@Service
@Transactional(readOnly = true)
public class CusUserService extends CrudService<CusUserDao, CusUser> {

	public CusUser get(String id) {
		return super.get(id);
	}
	
	public List<CusUser> findList(CusUser cusUser) {
		return super.findList(cusUser);
	}
	
	public Page<CusUser> findPage(Page<CusUser> page, CusUser cusUser) {
		return super.findPage(page, cusUser);
	}
	
	@Transactional(readOnly = false)
	public void save(CusUser cusUser) {
		super.save(cusUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(CusUser cusUser) {
		super.delete(cusUser);
	}
	
}