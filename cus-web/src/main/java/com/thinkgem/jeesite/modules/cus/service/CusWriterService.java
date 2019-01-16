/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cus.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cus.entity.CusWriter;
import com.thinkgem.jeesite.modules.cus.dao.CusWriterDao;

/**
 * 写手模块Service
 * @author dengyn
 * @version 2019-01-16
 */
@Service
@Transactional(readOnly = true)
public class CusWriterService extends CrudService<CusWriterDao, CusWriter> {

	public CusWriter get(String id) {
		return super.get(id);
	}
	
	public List<CusWriter> findList(CusWriter cusWriter) {
		return super.findList(cusWriter);
	}
	
	public Page<CusWriter> findPage(Page<CusWriter> page, CusWriter cusWriter) {
		return super.findPage(page, cusWriter);
	}
	
	@Transactional(readOnly = false)
	public void save(CusWriter cusWriter) {
		super.save(cusWriter);
	}
	
	@Transactional(readOnly = false)
	public void delete(CusWriter cusWriter) {
		super.delete(cusWriter);
	}
	
}