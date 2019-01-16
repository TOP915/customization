/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cus.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 任务Entity
 * @author dengyn
 * @version 2019-01-16
 */
public class CusTask extends DataEntity<CusTask> {
	
	private static final long serialVersionUID = 1L;
	private String taskName;		// 任务简称
	private String ownerId;		// 归属人id
	private String ownerName;		// 归属人名称
	private String relevantSubject;		// 学科
	private String site;		// 地点、位置
	private String taskType;		// 任务类型
	private String detailRequirements;		// 详细需求
	private Date deadline;		// 最后期限
	private String writerLevel;		// 写手水平要求
	private String schedule;		// 对接schedule;标示任务进行状态;
	
	public CusTask() {
		super();
	}

	public CusTask(String id){
		super(id);
	}

	@Length(min=0, max=128, message="任务简称长度必须介于 0 和 128 之间")
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	@Length(min=1, max=64, message="归属人id长度必须介于 1 和 64 之间")
	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	@Length(min=1, max=128, message="归属人名称长度必须介于 1 和 128 之间")
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	@Length(min=1, max=128, message="学科长度必须介于 1 和 128 之间")
	public String getRelevantSubject() {
		return relevantSubject;
	}

	public void setRelevantSubject(String relevantSubject) {
		this.relevantSubject = relevantSubject;
	}
	
	@Length(min=0, max=128, message="地点、位置长度必须介于 0 和 128 之间")
	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
	
	@Length(min=0, max=6, message="任务类型长度必须介于 0 和 6 之间")
	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	
	@Length(min=1, max=128, message="详细需求长度必须介于 1 和 128 之间")
	public String getDetailRequirements() {
		return detailRequirements;
	}

	public void setDetailRequirements(String detailRequirements) {
		this.detailRequirements = detailRequirements;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	@Length(min=0, max=64, message="写手水平要求长度必须介于 0 和 64 之间")
	public String getWriterLevel() {
		return writerLevel;
	}

	public void setWriterLevel(String writerLevel) {
		this.writerLevel = writerLevel;
	}
	
	@Length(min=0, max=6, message="对接schedule;标示任务进行状态;长度必须介于 0 和 6 之间")
	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
}