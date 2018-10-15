package com.bsplat.system.common.exception;


/**
 ***********************************************
 * Copyright (c)  by goldensoft
 * All right reserved.
 * Create Date: 2011-12-31
 * Create Author: huangxin
 * File Name:  异常处理基类
 * Last version:  1.0
 * Function:
 * Last Update Date:
 * Change Log:
 *************************************************
 */
public abstract class BaseException extends RuntimeException {
	
	
	private static final long serialVersionUID = -7126114767624436789L;
	
	/**
	 * 异常编号 项目编码（2位）+模块编号(1级2位+2级2位)+类编号+方法编号+异常编号
	 */
	private String err_num = ""; 
	/**
	 * 异常信息
	 */
	private String err_msg = "";
	
	
	public BaseException(Exception ex){
		this(ex.getMessage());
		this.setStackTrace(ex.getStackTrace());
	}
	
	/**
	 * 异常构造方法
	 * @param msg 异常信息
	 */
	public BaseException(String msg) {
		super(msg);
		if(msg != null ){
			this.err_msg = msg;
		}
		
	}
	
	
	/**
	 * 异常构造方法
	 * @param num 异常编号
	 * @param msg 异常信息
	 * @param cause 异常对象
	 */
	public BaseException(String err_num, String msg, Throwable cause) {
		super(msg, cause);
		
		if(err_num != null){
			this.err_num = err_num;
		}
		if(msg != null){
			this.err_msg = msg;
		}
	}
	
	/**
	 * 获取异常信息
	 */
	public String getMessage() {
		if("".equals(this.err_num)){
			return this.err_msg;
		}
		return this.err_num + ":" + super.getMessage();
	}


}
