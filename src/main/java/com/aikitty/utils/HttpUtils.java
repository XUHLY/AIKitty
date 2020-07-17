package com.aikitty.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class HttpUtils {

	public static String getSongs(String s, Integer limit, Integer offset, String type) throws Exception {

		String s2 = URLEncoder.encode(s,"UTF-8");
		String uri = "http://api.bzqll.com/music/tencent/search/?key=579621905&s="+s2;
		return doGet(uri);

	}

	public static String getWxToken(String appId, String appSecret) throws Exception {

		String uri = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx74c8bbeafdfd6393&secret=7d4ccb31209ba05cc945bde6030a25be";
		return doGet(uri);

	}

	
	public static String doGet(String uri) throws Exception {
		URL url = new URL(uri.trim());
		// 打开和URL之间的连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		// 设置通用的请求属性
		connection.setUseCaches(false);
		connection.setDoOutput(true);
		connection.setDoInput(true);
		String params = "";
		// 得到请求的输出流对象
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		out.write(params.getBytes("utf-8"));
		out.flush();
		out.close();
		// 建立实际的连接
		connection.connect();
		// 获取所有响应头字段
		Map<String, List<String>> headers = connection.getHeaderFields();
		// 遍历所有的响应头字段
		for (String key : headers.keySet()) {
			System.err.println(key + "--->" + headers.get(key));
		}
		// 定义 BufferedReader输入流来读取URL的响应
		BufferedReader in = null;
		in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		String result = "";
		String getLine;
		while ((getLine = in.readLine()) != null) {
			result += getLine;
		}
		in.close();
		return result;
	}
	
	
	public static void main(String[] args) throws Exception {
//		System.out.println(getWxToken("", ""));
//		System.out.println(getSongs("", null, null, ""));
		System.out.println(getSongs("默", null, null, ""));
		
//		String result = getSongs("", null, null, "");
//		JSONObject object = JSONObject.parseObject(result);
//		JSONArray array = object.getJSONArray("data");
//		List<Music> parseArray = JSONObject.parseArray(array.toJSONString(), Music.class);
//		for (Music song : parseArray) {
//			System.out.println(song);
//		}
		
	}

}
