package com.bsteel.shdc.common.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bsplat.system.common.util.DateUtil;

@Component
public class IJobTaskImpl{
	private static Log logger = LogFactory.getLog(IJobTaskImpl.class);//设置log
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * CRON表达式    含义
	 * "0 0 0 ? * *" 			每天0点0分0秒触发
	 * "0/5 * *  * * ? "		每5秒执行一次  
	 * "0 0 12 * * ?"    		每天中午十二点触发 
	 * "0 15 10 ? * *"    		每天早上10：15触发 
	 * "0 15 10 * * ?"    		每天早上10：15触发 
	 * "0 15 10 * * ? *"    	每天早上10：15触发 
	 * "0 15 10 * * ? 2005"    	2005年的每天早上10：15触发 
	 * "0 * 14 * * ?"    		每天从下午2点开始到2点59分每分钟一次触发 
	 * "0 0/5 14 * * ?"    		每天从下午2点开始到2：55分结束每5分钟一次触发 
	 * "0 0/5 14,18 * * ?"    	每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发 
	 * "0 0-5 14 * * ?"    		每天14:00至14:05每分钟一次触发 
	 * "0 10,44 14 ? 3 WED"    	三月的每周三的14：10和14：44触发 
	 * "0 15 10 ? * MON-FRI"    每个周一、周二、周三、周四、周五的10：15触发 
	 */

	/*@Scheduled(cron="0 0 0 ? * *")
	public void updateAgreement(){//每天0点跟新三方协议和授信是否有效
		int size = 0;
		String agrSql = "UPDATE RZ_T_TRIPLE_AGREEMENT T SET T.ARGEEMENT_STATUS='00' WHERE trunc(T.AGREEMENT_END_DATE) = to_date('"+DateUtil.toDateString(new Date(), "yyyyMMdd")+"','yyyy-MM-dd')";
		String limitSql ="UPDATE RZ_T_CREDIT_LIMITS T SET T.CREDIT_STATUS='00' WHERE trunc(T.CREDIT_END_DATE) = to_date('"+DateUtil.toDateString(new Date(), "yyyyMMdd")+"','yyyy-MM-dd')"; 
		List<String> sqlList = new ArrayList<String>();
		sqlList.add(agrSql);
		sqlList.add(limitSql);
		for(String sqls : sqlList){
			logger.info("sqls:"+sqls);
			int lineNum = jdbcTemplate.update(sqls);
			if(size == 0){
				logger.info("当前日期"+DateUtil.toDateString(new Date(), "yyyy-MM-dd")+"：有"+lineNum+"条协议已到期!");
			}else if(size == 1){
				logger.info("当前日期"+DateUtil.toDateString(new Date(), "yyyy-MM-dd")+"：有"+lineNum+"条授信已到期!");
			}
			size++;
		}
	}*/
	/*@Scheduled(cron="0 0 0 ? * *")
	public void updateAgreement(){//每天0点跟新三方协议和授信是否有效
		String agrSql = "UPDATE RZ_T_TRIPLE_AGREEMENT T SET T.ARGEEMENT_STATUS='00' WHERE trunc(T.AGREEMENT_END_DATE) = to_date('"+DateUtil.toDateString(new Date(), "yyyyMMdd")+"','yyyy-MM-dd')";
		String limitSql ="UPDATE RZ_T_CREDIT_LIMITS T SET T.CREDIT_STATUS='00' WHERE trunc(T.CREDIT_END_DATE) = to_date('"+DateUtil.toDateString(new Date(), "yyyyMMdd")+"','yyyy-MM-dd')"; 
		logger.info("agrSql:"+agrSql);
		int argeNum = jdbcTemplate.update(agrSql);
		logger.info("当前日期"+DateUtil.toDateString(new Date(), "yyyy-MM-dd")+"：有"+argeNum+"条协议已到期!");
		logger.info("limitSql:"+limitSql);
		int limitsNum = jdbcTemplate.update(limitSql);
		logger.info("当前日期"+DateUtil.toDateString(new Date(), "yyyy-MM-dd")+"：有"+limitsNum+"条授信已到期!");
			
	}*/
}
