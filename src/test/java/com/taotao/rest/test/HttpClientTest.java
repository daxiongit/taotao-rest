package com.taotao.rest.test;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

// 测试 httpClient
public class HttpClientTest {

	// 不带参数get 请求
	@Test
	public void doGet() {
		// 创建一个可关闭的 CloseableHttpClient 对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建一个 get 对象
		HttpGet get = new HttpGet("https://www.baidu.com");
		// 执行请求
		try {
			CloseableHttpResponse response = httpClient.execute(get);
			// 获取响应结果
			// 状态码
			int code = response.getStatusLine().getStatusCode();
			// 实体对象
			HttpEntity entity = response.getEntity();
			System.out.println("code:" + code);
			System.out.println("entity:" + EntityUtils.toString(entity, "UTF-8"));
			// 关闭流
			response.close();
			httpClient.close();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 带参数的 get请求
	@Test
	public void doGetWithParam() throws Exception{
		// 1.创建一个 可关闭的 CloseableHttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// 2.创建一个带有参数的 URL   //https://www.sogou.com/web?query=汽车
		URIBuilder builder = new URIBuilder("https://www.sogou.com/web");
		builder.addParameter("query", "汽车");
		// 3.创建一个 get 对象
		HttpGet get = new HttpGet(builder.build());
		
		// 4.执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		// 5.获取返回结果
		HttpEntity entity = response.getEntity();
		String str = EntityUtils.toString(entity);
		System.out.println(str);
		
		// 6.关闭流
		response.close();
		httpClient.close();
	}
	
	// 不带参数的 post 请求
	@Test
	public void doPost() throws Exception{
		// 1.创建一个 可关闭的httpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 2.创建一个 post 对象
		HttpPost post = new HttpPost("http://localhost:8081/rest/httpClient/post");
		// 3.执行请求
		CloseableHttpResponse response = httpClient.execute(post);
		// 4.获取响应结果
		HttpEntity entity = response.getEntity();
		String str = EntityUtils.toString(entity,"UTF-8");
		System.out.println(str);
		// 5.关闭流
		response.close();
		httpClient.close();
	}

	// 带参数的post 请求
	@Test
	public void doPostWithParam() throws Exception{
		// 1.httpClient
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 2.post
		HttpPost post = new HttpPost("http://localhost:8081/rest/httpClient/post/param");
		// 3.创建一个 Entity，模拟一个表单
		List<NameValuePair> kvList = new ArrayList<>();
		kvList.add(new BasicNameValuePair("username","daxiong"));
		kvList.add(new BasicNameValuePair("password","dddd"));
		// 4.封装为一个 Entity
		StringEntity entity = new UrlEncodedFormEntity(kvList,"UTF-8");
		// 5.设置请求内容
		post.setEntity(entity);
		// 执行post请求
		CloseableHttpResponse response = httpClient.execute(post);
		// 获取响应结果
		String str = EntityUtils.toString(response.getEntity());
		System.out.println(str);
		// 关闭流
		response.close();
		httpClient.close();
		
	}
}
