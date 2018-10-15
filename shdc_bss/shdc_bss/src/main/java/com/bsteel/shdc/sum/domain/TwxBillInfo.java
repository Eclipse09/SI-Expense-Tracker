package com.bsteel.shdc.sum.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import com.bsplat.system.common.domain.BSBaseDomain;
@Entity
@Table(name = "T_WX_BILL_INFO")
public class TwxBillInfo extends BSBaseDomain {
	private static final long serialVersionUID = 1L;
	// 主键 : ID
	@Id
	@Column(name = "ID")
	private String id;

	// 操作员代码
	@Column(name = "user_login_no")
	private String userloginno;

	// 类别
	@Column(name = "category")
	private String cateGory;

	// 金额
	@Column(name = "amount")
	private String amount;

	// remark
	@Column(name = "remark")
	private String remark;

	//创建时间: create_time
	@Column(name = "create_time")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date createTime;
	
	// remark
	@Column(name = "purposeName")
	private String purposeName;
	
	// location
	@Column(name = "location")
	private String location;
	

	public String getPurposeName() {
		return purposeName;
	}

	public void setPurposeName(String purposeName) {
		this.purposeName = purposeName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserloginno() {
		return userloginno;
	}

	public void setUserloginno(String userloginno) {
		this.userloginno = userloginno;
	}

	public String getCateGory() {
		return cateGory;
	}

	public void setCateGory(String cateGory) {
		this.cateGory = cateGory;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
