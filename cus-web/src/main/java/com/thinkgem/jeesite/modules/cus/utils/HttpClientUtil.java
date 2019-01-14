package com.thinkgem.jeesite.modules.cus.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HttpClientUtil {
	private static Logger logger = Logger.getLogger(HttpClientUtil.class);

	/**
	 * 发送 put请求
	 */
	public String put(String url, Map<String, String> header, String data) {
		String result = null;
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPut httpput = new HttpPut(url);
		httpput.addHeader("Content-Type", "application/json;charset=UTF-8");
		try {
			// 创建Header
			if (header != null) {
				for (String key : header.keySet()) {
					httpput.addHeader(key, header.get(key));
				}
			}
			// 创建参数
			StringEntity se = new StringEntity(data, Charset.forName("UTF-8"));
			httpput.setEntity(se);
			logger.info("executing request " + httpput.getURI());
			// 执行请求
			CloseableHttpResponse response = httpclient.execute(httpput);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
					logger.info("--------------------------------------");
					logger.info("Response content: " + result);
					logger.info("--------------------------------------");
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return result;
	}

	/**
	 * 发送 post请求
	 */
	public String post(String url, Map<String, String> header, String data) {
		String result = null;
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		httppost.addHeader("Content-Type", "application/json;charset=UTF-8");
		try {
			// 创建Header
			if (header != null) {
				for (String key : header.keySet()) {
					httppost.addHeader(key, header.get(key));
				}
			}
			// 创建参数
			StringEntity se = new StringEntity(data, Charset.forName("UTF-8"));
			se.setContentEncoding("UTF-8");
			// 发送Json格式的数据请求
			se.setContentType("application/json");
			httppost.setEntity(se);
			logger.info("executing request " + httppost.getURI());
			// 执行请求
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
					logger.info("--------------------------------------");
					logger.info("Response content: " + result);
					logger.info("--------------------------------------");
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return result;
	}

	/**
	 * 发送 post请求
	 */
	public String post(String url, Map<String, String> header, Map<String, String> data) {
		String result = null;
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		try {
			// 创建Header
			if (header != null) {
				for (String key : header.keySet()) {
					httppost.addHeader(key, header.get(key));
				}
			}
			if (data != null) {
				// 创建参数
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				for (String key : data.keySet()) {
					list.add(new BasicNameValuePair(key, data.get(key)));
				}
				httppost.setEntity(new UrlEncodedFormEntity(list, Charset.forName("UTF-8")));
			}
			logger.info("executing request " + httppost.getURI());
			// 执行请求
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
					logger.info("--------------------------------------");
					logger.info("Response content: " + result);
					logger.info("--------------------------------------");
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return result;
	}

	/**
	 * 发送 get请求
	 */
	public String get(String url, Map<String, String> header, Map<String, String> data) {
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			if (data != null) {
				// 创建参数
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				for (String key : data.keySet()) {
					list.add(new BasicNameValuePair(key, data.get(key)));
				}
				String paramsStr = EntityUtils.toString(new UrlEncodedFormEntity(list, Charset.forName("UTF-8")));
				if(url.indexOf("?") > 0){
					url = url + paramsStr;
				}else{
					url = url + "?" + paramsStr;
				}
			}
			HttpGet httpget = new HttpGet(url);
			// 创建Header
			if (header != null) {
				for (String key : header.keySet()) {
					httpget.addHeader(key, header.get(key));
				}
			}
			logger.info("executing request " + httpget.getURI());
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				logger.info("--------------------------------------");
				// 打印响应状态
				logger.info("Response status : " + response.getStatusLine());
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
					// 打印响应内容长度
					logger.info("Response content length: " + entity.getContentLength());
					// 打印响应内容
					logger.info("Response content: " + result);
				}
				logger.info("------------------------------------");
			} finally {
				response.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return result;
	}

	/**
	 * 发送delete请求
	 */
	public String delete(String url, Map<String, String> header) {
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpDelete httpdelete = new HttpDelete(url);
			// 创建Header
			if (header != null) {
				for (String key : header.keySet()) {
					httpdelete.addHeader(key, header.get(key));
				}
			}
			logger.info("executing request " + httpdelete.getURI());
			// 执行delete请求.
			CloseableHttpResponse response = httpclient.execute(httpdelete);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				logger.info("--------------------------------------");
				// 打印响应状态
				logger.info("Response status : " + response.getStatusLine());
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
					// 打印响应内容长度
					logger.info("Response content length: " + entity.getContentLength());
					// 打印响应内容
					logger.info("Response content: " + result);
				}
				logger.info("------------------------------------");
			} finally {
				response.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return result;
	}
	
	public static void main(String[] args){
	}
}
