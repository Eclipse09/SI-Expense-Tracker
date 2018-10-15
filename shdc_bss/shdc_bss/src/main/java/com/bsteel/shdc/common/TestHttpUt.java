//package com.bsteel.shdc.common;
//
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.util.Map;
//
//import net.sf.json.JSONObject;
//
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.junit.Test;
//import common.Logger;
//
//public class TestHttpUt
//{
//	/**
//	 * 以post的方式发送数据到指定的http接口---利用httpclient.jar包---HTTP接口的调用 Company:
//	 */
//
//	private static final Logger log = Logger.getLogger(TestHttpUt.class);
//
//	/**
//	 * post方式  
//	 */
//	public  String postHttp(String jsonData, String url)
//	{
//		String responseMsg = "";
//		// 1.构造HttpClient的实例
//		HttpClient httpClient = new HttpClient();
////		String url = "http://localhost:8085/shdc_iface/bspSynUserInfoController";
////		String url="http://localhost:8085/shdc_iface/checkSignController/warrantyCancel";
////		String url="http://localhost:8085/shdc_iface/checkSignController/warrantyRegistration";
////		String url="http://localhost:8085/shdc_iface/checkSignController/warrantyComparison";
//		//String url="http://10.60.17.35:8087/shdc_iface/checkSignController/warrntyQuery";
//		//String url="http://uat.shdongchan.com/shdc_service/valuationRestFul/warningDataDetailCallback";
////		String url="http://localhost:8085/shdc_iface/warehouseList";
////		String url="http://localhost:8085/shdc_iface/handleInfoLog";
////		String url="http://uat.shdongchan.com/shdc_iface/handleInfoLog";
//		// 2.构造PostMethod的实例
//		PostMethod postMethod = new PostMethod(url);
//		postMethod.setRequestHeader("Content-Type","application/json; charset=UTF-8");//设置请求的编码
//		// 3.把参数值放入到PostMethod对象中
//		// 方式1：
//		postMethod.setRequestBody(jsonData);
//		try
//		{
//			// 4.执行postMethod,调用http接口
//			httpClient.executeMethod(postMethod);// 200
//			// 5.读取内容
//			responseMsg = postMethod.getResponseBodyAsString();
//			log.info(responseMsg);
//			// 6.处理返回的内容
//		} catch (HttpException e)
//		{
//			e.printStackTrace();
//		} catch (IOException e)
//		{
//			e.printStackTrace();
//		} finally
//		{
//			// 7.释放连接
//			postMethod.releaseConnection();
//		}
//		return responseMsg;
//	}
//
//
//	
//
///*	*//**
//	 * 生成密钥
//	 * @param order业务单据
//	 * @return
//	 *//*
//	public  String setSignature(String order) {
//		Map shdcSys = ReadPropertiesFile.getProperties();
//		String path = (String) shdcSys.get("IFACE_INTERFACE_PATH");//证书路径
//		System.out.println("#证书路径#"+path);
//		String password = shdcSys.get("IFACE_INTERFACE_PASSWORD").toString().trim();//证书密码
//		JSONObject jsonOrder = JSONObject.fromObject(order);
//		System.out.println("【##业务单据:##】"+jsonOrder);
//		String signature = SignUtil.signDataDetached(path, password, jsonOrder.toString());
//		System.out.println("【##密钥:##】"+signature);
//		return signature;
//	}*/
//	@Test
//	public void warningDataDetailCallback(){
//		JSONObject jsonData=new JSONObject();
//		String url="http://uat.shdongchan.com/shdc_service/valuationRestFul/warningDataDetailCallback";
//		postHttp(jsonData.toString(), url);
//	}
//	
//	@Test
//	public void test5()throws Exception{
//		JSONObject json = new JSONObject();
//		json.element("businessId", "");
//		json.element("loanResult", "3");
//		json.element("loanContractNo", "");
//		json.element("loanResultDesc", "");
//		json.element("detailCount", "");
//		json.element("detail", "");
//		String url="http://127.0.0.1:8090/shdc_service/DcWarrantyApplyRestFul/receiveRzWarrantyApplyFeedback";
//		postHttp(json.toString(), url);
//	}
//	
//	@Test
//	public void TestWaring()throws Exception{
//		JSONObject jsonData=new JSONObject();
//		System.out.println("12121212");
//		String url="http://localhost:8080/shdc_bss/priceSumRestFul/saveFul";
//		System.out.println(url);
//		JSONObject jsonObject=new JSONObject();
//		jsonObject.put("SpendWay", "淘宝");
//		jsonObject.put("Date", "2018-08-13");
//		jsonObject.put("SpendMoney", "234");
//		jsonObject.put("Remarks", "淘宝");
//		jsonObject.put("UserNo", "UserNo宝");
//		jsonObject.put("SpendWayImg", "../../food_p.png");
//		postHttp(jsonObject.toString(), url);
//	}
//	@Test
//	public void queryVGzTContrast()throws Exception{
//		JSONObject jsonData=new JSONObject();
//		jsonData.element("typeName", "热镀锌");
//		jsonData.element("grade", "SGHC+Z275一级品");
//		jsonData.element("areaP", "南极光");
//		String url="http://localhost:8090/shdc_service/valuationRestFul/queryVGzTContrast";
//		postHttp(jsonData.toString(), url);
//	}
//}
