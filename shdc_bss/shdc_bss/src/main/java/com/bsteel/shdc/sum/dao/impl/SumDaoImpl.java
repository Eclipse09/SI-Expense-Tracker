package com.bsteel.shdc.sum.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsteel.shdc.sum.dao.SumDao;
import com.bsteel.shdc.sum.domain.TwxBillInfo;
import com.bsteel.shdc.sum.repository.TwxBillInfoRepository;

@Service("sumDao")
public class SumDaoImpl implements SumDao{
	@Autowired
	private TwxBillInfoRepository billInfoRepository;
	
	@Override
	public String queryAppInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TwxBillInfo save(TwxBillInfo billInfo) {
		return billInfoRepository.save(billInfo);
	}
	
	@Override
	public void delRecordById(String data) {
		 billInfoRepository.delRecordById(data);
	}

}
