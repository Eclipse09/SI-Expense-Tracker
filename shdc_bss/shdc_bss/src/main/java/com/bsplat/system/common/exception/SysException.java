package com.bsplat.system.common.exception;

/**
 ***********************************************
 * Copyright (c)  by goldensoft
 * All right reserved.
 * Create Date: 2011-12-31
 * Create Author: huangxin
 * File Name:  系统异常处理
 * Last version:  1.0
 * Function:
 * Last Update Date:
 * Change Log:
 *************************************************
 */
public class SysException extends BaseException {

	private static final long serialVersionUID = -7951658301578649602L;


	/**
	 * @param msg 异常信息
	 */
	public SysException(String msg) {
		super(msg);
	}

	/**
	 * 异常构造方法
	 * @param err_num 异常编号
	 * @param msg 异常信息
	 * @param cause 异常对象
	 */
	public SysException(String err_num, String msg, Throwable cause) {
		super(err_num, msg, cause);
		
	}

	public SysException(Exception ex) {
		super(ex);
	}

	public SysException(String msg, StackTraceElement[] stackTraceElements) {
		super(msg);
		this.setStackTrace(stackTraceElements);
	}

}
