package com.bsplat.system.common.exception;

/**
 ***********************************************
 * Copyright (c)  by goldensoft
 * All right reserved.
 * Create Date: 2011-12-31
 * Create Author: huangxin
 * File Name:  业务异常处理
 * Last version:  1.0
 * Function:这里写注释
 * Last Update Date:
 * Change Log:
 *************************************************
 */
public class BusinessException extends BaseException{
	
	private static final long serialVersionUID = 7537976633438034302L;
	
	/**
	 * 异常编号 项目编码（2位）+模块编号(1级2位+2级2位)+类编号+方法编号+异常编号
	 */
	/**
	 * 异常构造方法
	 * @param msg 异常信息
	 */
	public BusinessException(String msg) {
		super(msg);
	}

	/**
	 * 异常构造方法
	 * @param err_num 异常编号
	 * @param msg 异常信息
	 * @param cause 异常对象
	 */
	public BusinessException(String err_num, String msg, Throwable cause) {
		super(err_num,msg, cause);
	}

	public BusinessException(Exception ex) {
		super(ex);
	}

	public BusinessException(String msg,StackTraceElement[] stackTraceElements) {
		super(msg);
		this.setStackTrace(stackTraceElements);
	}

}
