package com.aikitty.entity;

import org.springframework.stereotype.Component;

/**
 * 
 * @author huangliyang
 * @Description 机器人返回的数据类型
 * @Version 
 * @Date 2019年2月20日
 */
@Component
public class RobotReqVo {
	
	private Integer role;// 用于确定是用户还是机器人

	private String chatInfo;// 具体内容

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getChatInfo() {
		return chatInfo;
	}

	public void setChatInfo(String chatInfo) {
		this.chatInfo = chatInfo;
	}
	
	
}
