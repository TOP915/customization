/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cus.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cus.entity.CusTask;
import com.thinkgem.jeesite.modules.cus.dao.CusTaskDao;

/**
 * 任务模块Service
 * @author dengyn
 * @version 2019-01-14
 */
@Service
@Transactional(readOnly = true)
public class CusTaskService extends CrudService<CusTaskDao, CusTask> {

	public CusTask get(String id) {
		return super.get(id);
	}
	
	public List<CusTask> findList(CusTask cusTask) {
		return super.findList(cusTask);
	}
	
	public Page<CusTask> findPage(Page<CusTask> page, CusTask cusTask) {
		return super.findPage(page, cusTask);
	}
	
	@Transactional(readOnly = false)
	public void save(CusTask cusTask) {
		super.save(cusTask);
	}
	
	@Transactional(readOnly = false)
	public void delete(CusTask cusTask) {
		super.delete(cusTask);
	}
	
}