package com.aikitty.controller;

import com.aikitty.entity.UnitV2RequestBean;
import com.aikitty.entity.UnitV2RequestBean.ClientSession;
import com.aikitty.entity.UnitV2RequestBean.QueryInfo;
import com.aikitty.entity.UnitV2RequestBean.Request;
import com.aikitty.utils.HttpUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * UNIT 2.0 API请求示例代码
 * @author 小帅丶
 *
 */
public class UnitV2Sample {
	public static String UNIT_URL ="https://aip.baidubce.com/rpc/2.0/unit/bot/chat";
	public static void main(String[] args) throws Exception {
		String result = getUNITV2Result("27228", "唱首歌儿吧", "24.d69b7579545d94b532b74e0f8cce7089.2592000.1549631927.282335-15332293");
		System.out.println(result);
	}
	
	
	/**
	 * UNIT 2.0 API请求方法 只需要场景id 和对话内容参数 大家可以根据需要稍作修改
	 * @param bot_id 场景id
	 * @param query 本轮对话内容
	 * @param access_token 请求接口所需的access_token
	 * @return String
	 * @throws Exception
	 */
	public static String getUNITV2Result(String bot_id,String query,String access_token) throws Exception{
		UnitV2RequestBean bean = new UnitV2RequestBean();
		bean.setVersion("2.0");
		bean.setBot_id(bot_id);
		bean.setLog_id("XS"+System.currentTimeMillis());
		Request request = new Request();
		request.setUser_id("XS0001");//测试设置 大家请自行更改
		request.setQuery(query);
		QueryInfo query_info = new QueryInfo();
		query_info.setType("TEXT");
		query_info.setSource("KEYBOARD");
		request.setQuery_info(query_info);
		request.setBernard_level(0);
		//希望传给bot的本地信息
		ClientSession client_session = new ClientSession();
		client_session.setClient_results("hhhh");
		List candidate_options = new ArrayList();
		candidate_options.add(0, "123");
		candidate_options.add(1, "456");
		client_session.setCandidate_options(candidate_options);
		/**
		 * ClientSession所需要的是字符串类型 内容为json格式  
		 * "client_session": "{\"candidate_options\": [\"123\", \"456\"], \"client_results\": \"hhhh\"}"
		 */
		String client_sessionparam = JSONObject.toJSONString(client_session);
		request.setClient_session(client_sessionparam);
		bean.setRequest(request);
		bean.setBot_session("");
		String jsonparam = JSONObject.toJSONString(bean);
		System.out.println("参数"+jsonparam);
		String result = HttpUtil.post(UNIT_URL, access_token, "application/json", jsonparam);
		return result;
	}
}