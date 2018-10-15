package com.bsteel.shdc.count.settrule.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsplat.system.common.JSONObjectSample;
import com.waxberry.log.ILogger;
import com.waxberry.log.LoggerFactory;

/**
 * 计费规则设置模块
 * @author ethan.du
 * @version 1.0
 */
@Controller
@RequestMapping(value="/settrule", produces="application/json", consumes = "application/json")
public class SettruleController {
	protected final ILogger logger = getLogger(this);
	public ILogger getLogger(Object classObject) {
		return LoggerFactory.getLogger(classObject.getClass());
	}
	@RequestMapping(value="/queryRule",method=RequestMethod.POST)
	@ResponseBody
	//接收大数据
	public String testResultFu11l(@RequestBody String jsonData){
		logger.info("params:"+jsonData);
		JSONObject resultJson = JSONObjectSample.createJSONObject();
		JSONArray array = JSONObjectSample.createJSONArray();
		array.add("Listsssss");
		array.add("List00001");
		resultJson.element("id", "00001");
		resultJson.element("name", "ethan");
		resultJson.element("age", "30");
		resultJson.element("home", "shanghai");
		resultJson.element("phome", "1233221");
		resultJson.element("array", array);
		return resultJson.toString();
	}

}
