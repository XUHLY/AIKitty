package com.aikitty.controller;

import com.aikitty.entity.MusicParams;
import com.aikitty.utils.HttpUtils;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Controller
public class SongController {

	public static void main(String[] args) throws Exception {
		
        URL url = new URL("https://api.bzqll.com/music/tencent/search/?key=579621905&s=123&limit=100&offset=0&type=song");
        // 打开和URL之间的连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        // 设置通用的请求属性
        connection.setRequestProperty("Connection", "Keep-Alive");
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
        in = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"));
        String result = "";
        String getLine;
        while ((getLine = in.readLine()) != null) {
            result += getLine;
        }
        in.close();
        System.err.println("result:" + result);
        
        MusicParams s = new MusicParams();
        s.setS("默");
        s.setLimit(100);
        s.setOffset(0);
        s.setType("song");
		
		System.out.println(HttpUtils.getSongs(s.getS(),s.getLimit(),s.getOffset(),s.getType()));
		
	}
	
	
}

