package com.bsteel.shdc.sum.domain;

public class Result {
	private Object message;
	private Object code;
	
	/*public Result(){
		super();
	}*/
	public Result(final String message){
		this.message = message;
		this.code = message;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	public Object getCode() {
		return code;
	}
	public void setCode(Object code) {
		this.code = code;
	}
}
