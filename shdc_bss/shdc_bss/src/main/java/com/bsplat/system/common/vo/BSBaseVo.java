package com.bsplat.system.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.bsplat.system.common.domain.UsersOrgtree;
import com.bsplat.system.constant.LSConstants;


public class BSBaseVo {
	private String sysRoleType; //角色类型
	
	private String sysCompanyNo;//当前登录人所属组织代码 
	
	private String sysBusinessType = LSConstants.DEFAULT_TYPE; //业务类型 默认 货押，厂商银、其它业务完成后该值默认为空
	
	private List<UsersOrgtree> bankList ;
	
	/**
	 * 
	 */
	private List<String> list ;
    
	public List<UsersOrgtree> getBankList() {
		return bankList;
	}


	public void setBankList(List<UsersOrgtree> bankList) {
		this.bankList = bankList;
	}
	public List<String> getList() {
		list = new ArrayList<String>();
		for(int i = 0 ; bankList != null && i < bankList.size() ;i++){
			UsersOrgtree tree = bankList.get(i);
			list.add(tree.getUserNum());
			
		}
		return list;
	}


	public void setList(List<String> list) {
		this.list = list;
	}


	public String getSysRoleType() {
		return sysRoleType;
	}


	public void setSysRoleType(String sysRoleType) {
		this.sysRoleType = sysRoleType;
	}


	public String getSysCompanyNo() {
		return sysCompanyNo;
	}


	public void setSysCompanyNo(String sysCompanyNo) {
		this.sysCompanyNo = sysCompanyNo;
	}


	public String getSysBusinessType() {
		return sysBusinessType;
	}


	public void setSysBusinessType(String sysBusinessType) {
		this.sysBusinessType = sysBusinessType;
	}
	
}
