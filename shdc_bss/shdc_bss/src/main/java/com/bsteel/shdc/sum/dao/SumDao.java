package com.bsteel.shdc.sum.dao;

import com.bsteel.shdc.sum.domain.TwxBillInfo;

public interface SumDao {
	public String queryAppInfo();
	public TwxBillInfo save(TwxBillInfo billInfo);
	public void delRecordById(String data);
}
