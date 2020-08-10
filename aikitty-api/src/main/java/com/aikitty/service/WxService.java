package com.aikitty.service;

import org.springframework.stereotype.Service;

@Service
public interface WxService {

	//获取微信接口调用凭证
	public String getWxAccessToken();
}
