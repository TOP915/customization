/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cus.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 任务文件Entity
 * @author dengyn
 * @version 2019-01-16
 */
public class CusTaskFile extends DataEntity<CusTaskFile> {
	
	private static final long serialVersionUID = 1L;
	private String fileOname;		// 原文件名称
	private String fileName;		// 文件名称
	private Long fileSize;		// 文件大小(B)
	private String filePath;		// 文件地址
	private String fileMd5;		// md5
	private String fileFormat;		// 文件格式
	private String taskId;		// 任务id
	
	public CusTaskFile() {
		super();
	}

	public CusTaskFile(String id){
		super(id);
	}

	@Length(min=1, max=128, message="原文件名称长度必须介于 1 和 128 之间")
	public String getFileOname() {
		return fileOname;
	}

	public void setFileOname(String fileOname) {
		this.fileOname = fileOname;
	}
	
	@Length(min=1, max=128, message="文件名称长度必须介于 1 和 128 之间")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@NotNull(message="文件大小(B)不能为空")
	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	
	@Length(min=1, max=128, message="文件地址长度必须介于 1 和 128 之间")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@Length(min=0, max=10, message="md5长度必须介于 0 和 10 之间")
	public String getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}
	
	@Length(min=1, max=16, message="文件格式长度必须介于 1 和 16 之间")
	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	
	@Length(min=1, max=64, message="任务id长度必须介于 1 和 64 之间")
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
}