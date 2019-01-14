/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cus.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 写手Entity
 * @author dengyn
 * @version 2019-01-14
 */
public class CusWriter extends DataEntity<CusWriter> {
	
	private static final long serialVersionUID = 1L;
	private String writerId;		// 写手id
	private String writerRecord;		// 写手履历
	private User user;		// 用户id
	
	public CusWriter() {
		super();
	}

	public CusWriter(String id){
		super(id);
	}

	@Length(min=1, max=64, message="写手id长度必须介于 1 和 64 之间")
	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	
	@Length(min=1, max=64, message="写手履历长度必须介于 1 和 64 之间")
	public String getWriterRecord() {
		return writerRecord;
	}

	public void setWriterRecord(String writerRecord) {
		this.writerRecord = writerRecord;
	}
	
	@NotNull(message="用户id不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}