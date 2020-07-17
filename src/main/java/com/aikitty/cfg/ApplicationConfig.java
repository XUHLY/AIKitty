package com.aikitty.cfg;

import org.springframework.stereotype.Component;

/**
 * @description:全局常量配置类
 * @author: huangliyang 
 * @Date：2019/01/29
 */
@Component
public class ApplicationConfig {

	// 百度集成APP_ID
	public static final String APP_ID = "15332293";

	// 百度集成 API_KEY
	public static final String API_KEY = "m9iKRQVwwjW0FP2CZBkIut5C";

	// 百度集成 SECRET_KEY
	public static final String SECRET_KEY = "54gFMUf4nNnzZ4CAxMu5pYjrkRjZLMmG";

	// 智能机器人
	public static final Integer CHATOBJECT_BOT = 0;

	// 用户
	public static final Integer CHATOBJECT_USER = 1;

}
