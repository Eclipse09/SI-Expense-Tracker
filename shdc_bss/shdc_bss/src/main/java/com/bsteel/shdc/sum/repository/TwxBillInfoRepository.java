package com.bsteel.shdc.sum.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.bsteel.shdc.sum.domain.TwxBillInfo;


public interface TwxBillInfoRepository extends Repository<TwxBillInfo, String>{
	public TwxBillInfo save(TwxBillInfo billInfo);
	
	@Query(" from TwxBillInfo a where a.id =?1")
	public List<TwxBillInfo> findByBillId(String id);
	
	@Query(value = "delete from T_WX_BILL_INFO where ID=?1 ", nativeQuery = true)
	@Modifying
	public void delRecordById(String id);
}
