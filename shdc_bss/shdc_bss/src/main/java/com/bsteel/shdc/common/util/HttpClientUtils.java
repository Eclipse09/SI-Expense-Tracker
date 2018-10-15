package com.bsteel.shdc.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import common.Logger;


public class HttpClientUtils {
	Logger loggger = Logger.getLogger(HttpClientUtils.class);
	private static HttpClientUtils httpClientUtils;
	
	public static HttpClientUtils getInstance(){
		if(httpClientUtils == null){
			httpClientUtils = new HttpClientUtils();
		}
		return httpClientUtils;
	}

	/**
	 * POST方式提交 入参是map
	 * 
	 * @param params
	 * @return
	 */
	public  String post(Map<String, String> params,String HTTP_URL)
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(HTTP_URL);
		RequestConfig requestConfig = RequestConfig.custom()  
        .setConnectTimeout(5*60*1000).setConnectionRequestTimeout(5*60*1000)  
        .setSocketTimeout(5*60*1000).build(); 
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();

		for (Entry<String, String> entry : params.entrySet())
		{
			formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}

		UrlEncodedFormEntity uefEntity;
		try
		{
			httppost.setConfig(requestConfig);
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			CloseableHttpResponse response = httpclient.execute(httppost);
			try
			{
				HttpEntity entity = response.getEntity();
				if (entity != null)
				{
					return EntityUtils.toString(entity, "UTF-8");
				}
			}
			finally
			{
				response.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				httpclient.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return "";
	}
	
	
	/**
	 * 
	 * @param ip 
	 * @param port 端口
	 * @param restfulUrl 请求url
	 * @param params 参数
	 * @return
	 * @throws Exception
	 */
    public String bigDataPost(String ip,String port,String restfulUrl,String params)throws Exception{
    	String result="1";//设置返回值
    	HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
    	CloseableHttpClient httpClient = httpClientBuilder.build();//初始化httpClient
    	HttpClientContext context = HttpClientContext.create();
    	HttpHost httpHost = new HttpHost(ip, Integer.valueOf(port), "http");
		try{
			final HttpPost  httpPost = new HttpPost(restfulUrl);
			httpPost.addHeader("Content-Type", "application/json");
			httpPost.setEntity(new StringEntity(params,"UTF-8"));
			final CloseableHttpResponse response = httpClient.execute(httpHost, httpPost,context);
			result = EntityUtils.toString(response.getEntity(),Charset.forName("UTF-8"));
			response.getEntity().getContent().close();
			httpClient.close();
		}catch(Exception ex){
			result = "0";
			ex.printStackTrace();
		}
    	return result;
    }
    
    
    /**
    110      * 向指定URL发送GET方法的请求
    111      * 
    112      * @param url
    113      *            发送请求的URL
    114      * @param param
    115      *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
    116      * @return URL 所代表远程资源的响应结果
    117      */
         public static String sendGet(String url, String param) {
             String result = "";
             BufferedReader in = null;
             try {
                 String urlNameString = url + "?" + param;
                 URL realUrl = new URL(urlNameString);
                 // 打开和URL之间的连接
                URLConnection connection = realUrl.openConnection();
                 // 设置通用的请求属性
                 connection.setRequestProperty("accept", "*/*");
                 connection.setRequestProperty("connection", "Keep-Alive");
                 connection.setRequestProperty("user-agent",
                         "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                 // 建立实际的连接
                 connection.connect();
                // 获取所有响应头字段
                 Map<String, List<String>> map = connection.getHeaderFields();
                 // 遍历所有的响应头字段
                 for (String key : map.keySet()) {
                     System.out.println(key + "--->" + map.get(key));
                 }
                 // 定义 BufferedReader输入流来读取URL的响应
                 in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                 String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } catch (Exception e) {
                 System.out.println("发送GET请求出现异常！" + e);
                 e.printStackTrace();
             }
            // 使用finally块来关闭输入流
             finally {
                try {
                     if (in != null) {
                         in.close();
                     }
                 } catch (Exception e2) {
                     e2.printStackTrace();
                 }
             }
            return result;
         }
}
