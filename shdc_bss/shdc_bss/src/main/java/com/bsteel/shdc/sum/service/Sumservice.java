package com.bsteel.shdc.sum.service;

public interface Sumservice {
	public String queryAppInfo(String data);
	
	public String save(String billInfo);
	
	public String queryStartEndInfo(String data);
	
	public String queryList(String data);
	
	public String queryById(String data);
	
	public String delRecordById(String data);
	
	public String queryMonthBill(String data);
}
