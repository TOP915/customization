/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cus.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cus.entity.CusTaskFile;
import com.thinkgem.jeesite.modules.cus.dao.CusTaskFileDao;

/**
 * 任务文件Service
 * @author dengyn
 * @version 2019-01-16
 */
@Service
@Transactional(readOnly = true)
public class CusTaskFileService extends CrudService<CusTaskFileDao, CusTaskFile> {

	public CusTaskFile get(String id) {
		return super.get(id);
	}
	
	public List<CusTaskFile> findList(CusTaskFile cusTaskFile) {
		return super.findList(cusTaskFile);
	}
	
	public Page<CusTaskFile> findPage(Page<CusTaskFile> page, CusTaskFile cusTaskFile) {
		return super.findPage(page, cusTaskFile);
	}
	
	@Transactional(readOnly = false)
	public void save(CusTaskFile cusTaskFile) {
		super.save(cusTaskFile);
	}
	
	@Transactional(readOnly = false)
	public void delete(CusTaskFile cusTaskFile) {
		super.delete(cusTaskFile);
	}
	
}