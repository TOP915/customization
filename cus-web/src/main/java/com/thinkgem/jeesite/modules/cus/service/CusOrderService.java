/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cus.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cus.entity.CusOrder;
import com.thinkgem.jeesite.modules.cus.dao.CusOrderDao;

/**
 * 订单模块Service
 * @author dengyn
 * @version 2019-01-16
 */
@Service
@Transactional(readOnly = true)
public class CusOrderService extends CrudService<CusOrderDao, CusOrder> {

	public CusOrder get(String id) {
		return super.get(id);
	}
	
	public List<CusOrder> findList(CusOrder cusOrder) {
		return super.findList(cusOrder);
	}
	
	public Page<CusOrder> findPage(Page<CusOrder> page, CusOrder cusOrder) {
		return super.findPage(page, cusOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(CusOrder cusOrder) {
		super.save(cusOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(CusOrder cusOrder) {
		super.delete(cusOrder);
	}
	
}