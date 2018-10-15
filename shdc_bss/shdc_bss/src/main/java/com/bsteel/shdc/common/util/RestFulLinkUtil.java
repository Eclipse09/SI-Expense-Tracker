package com.bsteel.shdc.common.util;

import java.net.URLEncoder;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.bsplat.system.common.util.PropertiesUtil;
import com.waxberry.log.ILogger;
import com.waxberry.log.LoggerFactory;

/**
 * 
 * @author ETHAN.DU
 * 
 * 共用的restful链接
 */
public class RestFulLinkUtil {
	protected final ILogger logger = getLogger(this);
	Map paramList=PropertiesUtil.read("sys_parameter");
	private final static HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
	private static CloseableHttpClient httpClient;
	//private final  HttpHost httpHost = new HttpHost((String)paramList.get("RESTFUL_HOST"), Integer.valueOf(paramList.get("RESTFUL_HOST_PORT").toString()), "http");
	//private final HttpHost httpHost = new HttpHost("localhost", 8081, "http");
	private  HttpHost httpHost = null;
	private final static HttpClientContext context = HttpClientContext.create();
    private static RestFulLinkUtil restFulLinkUtil;
	
    private RestFulLinkUtil(){
    	logger.info("调用远程服务开始.....");
    	httpHost = new HttpHost((String)paramList.get("RESTFUL_HOST"), Integer.valueOf(paramList.get("RESTFUL_HOST_PORT").toString()), "http");
    	logger.info("地址:"+(String)paramList.get("RESTFUL_HOST")+":"+paramList.get("RESTFUL_HOST_PORT"));
    }
    private RestFulLinkUtil(String context){
    	logger.info("调用远程服务开始.....");
    	if(context.equals("shdc_web")){
    		httpHost = new HttpHost((String)paramList.get("RESTFUL_HOST"), Integer.valueOf(paramList.get("RESTFUL_HOST_PORT").toString()), "http");
    		logger.info("地址:"+(String)paramList.get("RESTFUL_HOST")+":"+paramList.get("RESTFUL_HOST_PORT"));
    	}
    }
    public ILogger getLogger(Object classObject) {
		return LoggerFactory.getLogger(classObject.getClass());
	}
    
    public static RestFulLinkUtil getInstance(String context){
    	if(restFulLinkUtil==null){
    		if(null == context || context.equals("")){
    			restFulLinkUtil=new RestFulLinkUtil();
    		}else{
    			restFulLinkUtil=new RestFulLinkUtil(context);
    		}
    	}
    	return restFulLinkUtil;
    }

    /**
     * @description 提供共用的restful链接，链接方式为POST
     * @param host 需要链接的应用名
     * @param useAddress 需要映射到的类
     * @param url  映射的方法地址
     * @param params 参数（一般为json格式或者单个字符串）
     * @return 返回可为json格式或者单个字符串
     */
    public String commonLinkPost(String host,String useAddress,String url,String params){
    	String result="1";//设置返回值
		httpClient = httpClientBuilder.build();//初始化httpClient
		try{
			final HttpPost  httpPost = new HttpPost("/"+host+"/"+useAddress+((null == url || url.equals(""))?"":url)+"/"+(null==params?"":URLEncoder.encode(params,"UTF-8")));
			httpPost.addHeader("Content-Type", "application/json");
			final CloseableHttpResponse response = httpClient.execute(httpHost, httpPost,context);
			result = EntityUtils.toString(response.getEntity());
			response.getEntity().getContent().close();
			httpClient.close();
		}catch(Exception ex){
			result = "0";
			ex.printStackTrace();
		}
    	return result;
    }
    
    /**
     * @description 提供共用的restful链接，链接方式为GET
     * @param host 需要链接的应用名
     * @param url  映射的方法地址
     * @param params 参数（一般为json格式或者单个字符串）
     * @return 返回可为json格式或者单个字符串
     */
    public String commonLinkGet(String host,String useAddress,String url,String params)throws Exception{
    	String result="1";//设置返回值
		httpClient = httpClientBuilder.build();//初始化httpClient
		try{
			final HttpGet  httpGet = new HttpGet("/"+host+"/"+useAddress+((null == url || url.equals(""))?"":url)+"/"+(null==params?"":URLEncoder.encode(params,"UTF-8")));
			httpGet.addHeader("Content-Type", "application/json");
			final CloseableHttpResponse response = httpClient.execute(httpHost, httpGet,context);
			result = EntityUtils.toString(response.getEntity());
			response.getEntity().getContent().close();
			httpClient.close();
		}catch(Exception ex){
			ex.printStackTrace();
			return "0";
		}
    	return result;
    }
    
    /**
     * @description 处理大数据传输
     * @param host 需要链接的应用名
     * @param useAddress 需要映射到的类
     * @param url 映射的方法地址
     * @param params 参数（为json格式）
     * @return
     * @throws Exception
     */
    public String bigDataPost(String host,String useAddress,String url,String params)throws Exception{
    	String result="1";//设置返回值
    	httpClient = httpClientBuilder.build();//初始化httpClient
		try{
			final HttpPost  httpPost = new HttpPost("/"+host+"/"+useAddress+((null == url || url.equals(""))?"":url));
			httpPost.addHeader("Content-Type", "application/json");
			httpPost.setEntity(new StringEntity(params,"UTF-8"));
			final CloseableHttpResponse response = httpClient.execute(httpHost, httpPost,context);
			result = EntityUtils.toString(response.getEntity());
			response.getEntity().getContent().close();
			httpClient.close();
		}catch(Exception ex){
			result = "0";
			ex.printStackTrace();
		}
    	return result;
    }
}
