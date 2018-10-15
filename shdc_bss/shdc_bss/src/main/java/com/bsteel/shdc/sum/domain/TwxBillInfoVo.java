package com.bsteel.shdc.sum.domain;

public class TwxBillInfoVo{
	private String id;
	private String UserNo;
	private String Date;
	private String SpendMoney;
	private String PurposeIcon;
	private String Remark;
	private String PurposeName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserNo() {
		return UserNo;
	}
	public void setUserNo(String userNo) {
		UserNo = userNo;
	}
	public String getSpendMoney() {
		return SpendMoney;
	}
	public void setSpendMoney(String spendMoney) {
		SpendMoney = spendMoney;
	}
	public String getPurposeIcon() {
		return PurposeIcon;
	}
	public void setPurposeIcon(String purposeIcon) {
		PurposeIcon = purposeIcon;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getPurposeName() {
		return PurposeName;
	}
	public void setPurposeName(String purposeName) {
		PurposeName = purposeName;
	}
}
