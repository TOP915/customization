/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cus.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户模块Entity
 * @author dengyn
 * @version 2019-01-16
 */
public class CusUser extends DataEntity<CusUser> {
	
	private static final long serialVersionUID = 1L;
	private String loginName;		// 登录账号
	private String userName;		// 用户姓名
	private String userEmail;		// 邮箱
	private String userPhone;		// 电话
	private String userWechat;		// 用户微信
	private String userQq;		// 用户QQ
	private String userType;		// 用户类型 1:需求客户;2:写手客户;
	private String loginIp;		// 最后登陆IP
	private Date loginDate;		// 最后登陆时间
	private String loginFlag;		// 是否可登录1:可登录；2:不可登录
	
	public CusUser() {
		super();
	}

	public CusUser(String id){
		super(id);
	}

	@Length(min=1, max=128, message="登录账号长度必须介于 1 和 128 之间")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Length(min=0, max=128, message="用户姓名长度必须介于 0 和 128 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=64, message="邮箱长度必须介于 0 和 64 之间")
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	@Length(min=1, max=64, message="电话长度必须介于 1 和 64 之间")
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	@Length(min=0, max=64, message="用户微信长度必须介于 0 和 64 之间")
	public String getUserWechat() {
		return userWechat;
	}

	public void setUserWechat(String userWechat) {
		this.userWechat = userWechat;
	}
	
	@Length(min=0, max=64, message="用户QQ长度必须介于 0 和 64 之间")
	public String getUserQq() {
		return userQq;
	}

	public void setUserQq(String userQq) {
		this.userQq = userQq;
	}
	
	@Length(min=1, max=2, message="用户类型 1:需求客户;2:写手客户;长度必须介于 1 和 2 之间")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@Length(min=0, max=64, message="最后登陆IP长度必须介于 0 和 64 之间")
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
	@Length(min=0, max=1, message="是否可登录1:可登录；2:不可登录长度必须介于 0 和 1 之间")
	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}
	
}