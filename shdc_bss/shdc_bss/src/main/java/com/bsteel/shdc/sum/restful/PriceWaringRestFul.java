package com.bsteel.shdc.sum.restful;

import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsteel.shdc.common.util.HttpClientUtils;
import com.bsteel.shdc.sum.domain.Result;
import com.bsteel.shdc.sum.service.Sumservice;


@Controller
@RequestMapping(value = "/priceSumRestFul", produces = "application/json", consumes = "application/json")
public class PriceWaringRestFul {
	private static Log log = LogFactory.getLog(PriceWaringRestFul.class);

	@Autowired
	private Sumservice sumservice;

	// 查询今日，本月，本年消费金额
	@RequestMapping(value = "/testSumFul", method = RequestMethod.POST)
	@ResponseBody
	public Result testWaringFul(@RequestBody String data) {
		String str = sumservice.queryAppInfo(data);
		log.info("testSumFul返回值：" + str);
		Result result = new Result(str);
		return result;
	}

	// 录入消费记录
	@RequestMapping(value = "/saveFul", method = RequestMethod.POST)
	@ResponseBody
	public Result saveFul(@RequestBody String data) {
		String str = sumservice.save(data);
		log.info("saveFul返回值：" + str);
		Result result = new Result(str);
		return result;
	}

	// 查询时间区间消费记录
	@RequestMapping(value = "/queryTime", method = RequestMethod.POST)
	@ResponseBody
	public Result queryTime(@RequestBody String data) {
		log.info("传入参数：" + data);
		String str = sumservice.queryStartEndInfo(data);
		log.info("queryTime返回值：" + str);
		Result result = new Result(str);
		return result;
	}

	// 统计表
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public Result queryList(@RequestBody String data) {
		String str = sumservice.queryList(data);
		log.info("queryList返回值：" + str);
		Result result = new Result(str);
		return result;
	}

	//详情查询
	@RequestMapping(value = "/queryById", method = RequestMethod.POST)
	@ResponseBody
	public Result queryById(@RequestBody String data) {
		String str = sumservice.queryById(data);
		log.info("queryById返回值：" + str);
		Result result = new Result(str);
		return result;
	}
	
	//详情查询
		@RequestMapping(value = "/queryByCode", method = RequestMethod.POST)
		@ResponseBody
		public String queryByCode(@RequestBody String data) {
			JSONObject json = JSONObject.fromObject(data);
			String code=json.getString("code");
			String param="appid=wxb4b1e5a50d2a0702&secret=cbf2c5e8878dcf03dbd2f87b3043f63b&js_code="+code+"&grant_type=authorization_code";
			String openId=HttpClientUtils.sendGet("https://api.weixin.qq.com/sns/jscode2session", param);
			return openId;
		}
		
	//首页删除一条记录
	@RequestMapping(value = "/delRecordBill", method = RequestMethod.POST)
	@ResponseBody
	public Result delRecordBill(@RequestBody String data) {
		String str = sumservice.delRecordById(data);
		log.info("queryList返回值："+str);
		Result result = new Result(str);
		return result;
	}
	
	// 查询当月消费账单
	@RequestMapping(value = "/queryMonthBill", method = RequestMethod.POST)
	@ResponseBody
	public Result queryMonthBill(@RequestBody String data) {
		log.info("传入参数：" + data);
		String str = sumservice.queryMonthBill(data);
		log.info("queryMonthBill返回值：" + str);
		Result result = new Result(str);
		return result;
	}
}
