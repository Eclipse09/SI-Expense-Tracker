package com.bsteel.shdc.sum.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baosight.baosteel.ume.util.DateUtil;
import com.bsteel.shdc.common.CommonDao;
import com.bsteel.shdc.sum.dao.SumDao;
import com.bsteel.shdc.sum.domain.TwxBillInfo;
import com.bsteel.shdc.sum.domain.TwxBillInfoVo;
import com.bsteel.shdc.sum.repository.TwxBillInfoRepository;
import com.bsteel.shdc.sum.service.Sumservice;

@Service("sumService")
public class SumserviceImpl implements Sumservice {
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private SumDao sumDao;
	@Autowired
	private TwxBillInfoRepository billInfoRepository;

	@Override
	public String queryAppInfo(String data) {
		JSONObject json = JSONObject.fromObject(data);
		String userNo = json.getString("UserNo");
		JSONObject obj = new JSONObject();
		List<String> list = commonDao.querySysdateSum(userNo);
		List<String> curdateList = commonDao.querycurdateSum(userNo);
		List<String> yearList = commonDao.findYearSum(userNo);
		if (list.size() > 0) {
			obj.put("sysdateAmount", String.valueOf(list.get(0)));
		}
		if (curdateList.size() > 0) {
			obj.put("curdateAmount", String.valueOf(curdateList.get(0)));
		}
		if (yearList.size() > 0) {
			obj.put("yearAmount", String.valueOf(curdateList.get(0)));
		}
		System.out.println("返回参数:" + obj);
		return obj.toString();
	}

	@Override
	public String save(String billInfo) {
		JSONObject jsonResult = new JSONObject();
		JSONObject json = JSONObject.fromObject(billInfo);
		String cateGory = json.getString("SpendWayImg");
		String createTime = json.getString("Date");
		String amount = json.getString("SpendMoney");
		String remark = json.getString("Remarks");
		String userloginno = json.getString("UserNo");
		String spendWay = json.getString("SpendWay");
		String location = json.getString("location");
		TwxBillInfo twxBillInfo = new TwxBillInfo();
		twxBillInfo.setId(UUID.randomUUID().toString());
		twxBillInfo.setCreateTime(new Date());
		twxBillInfo.setRemark(remark);
		twxBillInfo.setAmount(amount);
		twxBillInfo.setUserloginno(userloginno);
		twxBillInfo.setPurposeName(spendWay);
		twxBillInfo.setCateGory(cateGory);
		twxBillInfo.setLocation(location);
		sumDao.save(twxBillInfo);
		jsonResult.accumulate("falg", "0");
		return jsonResult.toString();
	}

	@Override
	public String queryStartEndInfo(String data) {
		JSONObject json = JSONObject.fromObject(data);
		JSONObject jsonResult = new JSONObject();
		String startDate = json.getString("startDate");
		String endDate = json.getString("endDate");
		String userNo = json.getString("UserNo");
		List<Object[]> historyBillList = commonDao.query(startDate, endDate,
				userNo);
		List<TwxBillInfoVo> list = new ArrayList<TwxBillInfoVo>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for (Object[] obj : historyBillList) {
			TwxBillInfoVo billInfoVo = new TwxBillInfoVo();
			billInfoVo.setId((String) obj[0]);
			billInfoVo.setUserNo((String) obj[1]);
			billInfoVo.setDate(formatter.format(DateUtil.getDate(obj[5]
					.toString())));
			billInfoVo.setSpendMoney(obj[3].toString());
			billInfoVo.setPurposeIcon((String) obj[2]);
			billInfoVo.setRemark((String) obj[4]);
			billInfoVo.setPurposeName((String) obj[6]);
			list.add(billInfoVo);
		}
		List<String> totalMoney = commonDao.queryAmount(startDate, endDate,
				userNo);
		jsonResult.put("totalMoney", String.valueOf(totalMoney.get(0)));
		jsonResult.put("historyBillList", list);
		System.out.println("历史记录返回参数：" + jsonResult.toString());
		return jsonResult.toString();
	}

	@Override
	public String queryList(String data) {
		JSONObject json = JSONObject.fromObject(data);
		JSONObject jsonResult = new JSONObject();
		List listTime = new ArrayList();
		List listAmount = new ArrayList();
		String userNo = json.getString("UserNo");
		List<Object[]> list = commonDao.querylist(userNo);
		for (Object[] objects : list) {
			listTime.add(objects[0].toString() + "-" + objects[1].toString());
			listAmount.add(objects[2].toString());
		}
		jsonResult.put("listTime", listTime);
		jsonResult.put("listAmount", listAmount);
		System.out.println("$$$$$$$$$$$$:" + jsonResult.toString());
		return jsonResult.toString();
	}

	@Override
	public String queryById(String data) {
		JSONObject json = JSONObject.fromObject(data);
		String id = json.getString("id");
		List<TwxBillInfo> billInfo = billInfoRepository.findByBillId(id);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(sdf.format(billInfo.get(0).getCreateTime())); 
			billInfo.get(0).setCreateTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("billInfo", billInfo.get(0));
		return jsonResult.toString();
	}
	
	@Override
	public String delRecordById(String data){
		JSONObject json =JSONObject.fromObject(data);
		String id=json.getString("Id");
		sumDao.delRecordById(id);
		String flag = "1";
		return flag;
	}
	
	@Override
	public String queryMonthBill(String data){
		JSONObject json = JSONObject.fromObject(data);
		JSONObject jsonResult = new JSONObject();
		String userNo = json.getString("UserNo");
		List<Object[]> list = commonDao.queryMonthBill(userNo);
		List<TwxBillInfoVo> listVo = new ArrayList<TwxBillInfoVo>();
		for (Object[] objects : list) {			
			TwxBillInfoVo billInfoVo = new TwxBillInfoVo();
			billInfoVo.setPurposeIcon((String) objects[0]);
			billInfoVo.setPurposeName(objects[1].toString());
			billInfoVo.setSpendMoney(objects[2].toString());
			
			listVo.add(billInfoVo);
		}
		jsonResult.put("monthBillList", listVo);
		System.out.println("$$$$$$$$$$$$:" + jsonResult.toString());
		return jsonResult.toString();
	}
}
