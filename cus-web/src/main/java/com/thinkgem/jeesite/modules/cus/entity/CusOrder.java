/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cus.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 订单模块Entity
 * @author dengyn
 * @version 2019-01-16
 */
public class CusOrder extends DataEntity<CusOrder> {
	
	private static final long serialVersionUID = 1L;
	private String taskId;		// 任务id
	private String writerId;		// 写手id
	private String writerName;		// 写手姓名
	private String orderPrice;		// 订单价格
	private String schedule;		// 对接schedule;标示任务进行状态;
	
	public CusOrder() {
		super();
	}

	public CusOrder(String id){
		super(id);
	}

	@Length(min=1, max=64, message="任务id长度必须介于 1 和 64 之间")
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	@Length(min=0, max=64, message="写手id长度必须介于 0 和 64 之间")
	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	
	@Length(min=0, max=128, message="写手姓名长度必须介于 0 和 128 之间")
	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	
	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	@Length(min=0, max=6, message="对接schedule;标示任务进行状态;长度必须介于 0 和 6 之间")
	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
}