package com.bsplat.system.common.domain;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bsplat.system.common.domain.BSBaseDomain;

/**
 * 用户父子表: V_USER_ORGTREE
 * 
 * 
 * @author chenyawei
 * @vision 2012-10-28 13:02:13
 * @revision
 */
@Entity
@Table(name="V_USER_ORGTREE")
public class UsersOrgtree extends BSBaseDomain {
	 	@Id
	    @Column(name="ID")
	    private String id;
	 	
	 	// 子节点代码 : USER_NUM
	 	@Column(name="USER_NUM")
	    private  String   userNum;
	 	
	 	// 子节点名称 : USER_NAME
	    @Column(name="USER_NAME")
	    private  String   userName;
	 	
	 	// 父节点代码 : PARENT_USER_NUM
	    @Column(name="PARENT_USER_NUM")
	    private  String   parentUserNum;
	 	
	 	// 父节点名称 : PARENT_USER_NAME
	    @Column(name="PARENT_USER_NAME")
	    private  String   parentUserName;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getUserNum() {
			return userNum;
		}

		public void setUserNum(String userNum) {
			this.userNum = userNum;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getParentUserNum() {
			return parentUserNum;
		}

		public void setParentUserNum(String parentUserNum) {
			this.parentUserNum = parentUserNum;
		}

		public String getParentUserName() {
			return parentUserName;
		}

		public void setParentUserName(String parentUserName) {
			this.parentUserName = parentUserName;
		}
}
