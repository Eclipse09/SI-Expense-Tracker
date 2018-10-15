package com.bsteel.shdc.rest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

import com.bsplat.system.common.JSONObjectSample;
import com.bsteel.shdc.common.util.RestFulLinkUtil;

public class SettruleRestTest {

	@Test
	public void queryTest()throws Exception{
		JSONObject resultJson = JSONObjectSample.createJSONObject();
		JSONArray array = JSONObjectSample.createJSONArray();
		array.add("Listsssss");
		array.add("List00001");
		resultJson.element("id1", "00001");
		resultJson.element("name1", "ethan");
		resultJson.element("age1", "30");
		resultJson.element("home1", "shanghai");
		resultJson.element("phome1", "1233221");
		resultJson.element("array1", array);
		String result = RestFulLinkUtil.getInstance("shdc_bss").bigDataPost("shdc_bss", "settrule", "/queryRule", resultJson.toString());
		System.out.println("result:"+result);		
	}
}
