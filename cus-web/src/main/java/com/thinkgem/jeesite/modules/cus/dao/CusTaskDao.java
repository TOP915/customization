/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cus.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cus.entity.CusTask;

/**
 * 任务DAO接口
 * @author dengyn
 * @version 2019-01-16
 */
@MyBatisDao
public interface CusTaskDao extends CrudDao<CusTask> {
	
}