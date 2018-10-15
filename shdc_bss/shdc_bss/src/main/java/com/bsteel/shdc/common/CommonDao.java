package com.bsteel.shdc.common;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Service;

@Service("commonDao")
public class CommonDao {
	@PersistenceUnit
	private EntityManagerFactory emf;

	// 今日总额
	@SuppressWarnings("unchecked")
	public List<String> querySysdateSum(String userNo) {
		String sql = "select sum(t.amount) as amount from T_WX_BILL_INFO t where date_format(t.create_time,'%Y-%m-%d')=DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and t.user_login_no='"+userNo+"'";
		EntityManager em = emf.createEntityManager();
		return (List<String>) em.createNativeQuery(sql).getResultList();
	}

	// 本月总额
	@SuppressWarnings("unchecked")
	public List<String> querycurdateSum(String userNo) {
		String sql = "SELECT SUM(t.amount) FROM T_WX_BILL_INFO t WHERE DATE_FORMAT(t.create_time,'%Y%m') = DATE_FORMAT(CURDATE(),'%Y%m') and t.user_login_no='"+userNo+"'";
		EntityManager em = emf.createEntityManager();
		return (List<String>) em.createNativeQuery(sql).getResultList();
	}

	// 本年总支出
	@SuppressWarnings("unchecked")
	public List<String> findYearSum(String userNo) {
		String sql = "select SUM(t.amount) from T_WX_BILL_INFO t where YEAR(t.create_time)=YEAR(NOW()) and t.user_login_no='"+userNo+"'";
		EntityManager em = emf.createEntityManager();
		return (List<String>) em.createNativeQuery(sql).getResultList();
	}
	
	//查询记录
	@SuppressWarnings("unchecked")
	public List<Object[]> query(String startDate,String endDate,String userNo) {
		String sql = "SELECT * from T_WX_BILL_INFO t where DATE_FORMAT(t.create_time,'%Y-%m-%d') BETWEEN '"+startDate+"' and '"+endDate+"' and t.user_login_no='"+userNo+"' order by t.create_time desc";
		EntityManager em = emf.createEntityManager();
		return (List<Object[]>) em.createNativeQuery(sql).getResultList();
	}
	

	//查询消费总金额
	@SuppressWarnings("unchecked")
	public List<String> queryAmount(String startDate,String endDate,String userNo) {
		String sql = "SELECT SUM(t.amount) from T_WX_BILL_INFO t where DATE_FORMAT(t.create_time,'%Y-%m-%d') BETWEEN '"+startDate+"' and '"+endDate+"' and t.user_login_no='"+userNo+"'";
		EntityManager em = emf.createEntityManager();
		return (List<String>) em.createNativeQuery(sql).getResultList();
	}
	
	//查询消费总金额
	@SuppressWarnings("unchecked")
	public List<Object[]> querylist(String userNo) {
		String sql = "select year(t.create_time) as years ,month(t.create_time) as moths,SUM(t.amount) as allamount from T_WX_BILL_INFO t where t.user_login_no='"+userNo+"' group by year(t.create_time),month(t.create_time)";
		EntityManager em = emf.createEntityManager();
		return (List<Object[]>) em.createNativeQuery(sql).getResultList();
	}
		
	//查询消费总金额
	@SuppressWarnings("unchecked")
	public List<Object[]> queryMonthBill(String userNo) {
		String sql = "select t.category as category ,t.purposeName as purposeName,SUM(t.amount) as allamount from T_WX_BILL_INFO t where t.user_login_no='"+userNo+"' group by t.category,t.purposeName;";
		EntityManager em = emf.createEntityManager();
		return (List<Object[]>) em.createNativeQuery(sql).getResultList();
	}
}
