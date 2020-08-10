package com.aikitty.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.aikitty.service.BaiDuTokenService;

//@Service
public class BaiDuTokenServiceImpl implements BaiDuTokenService{

//	@Autowired
//	private BaiDuTokenDao baiDuTokenDao; 
	
	@Override
	@Transactional
	public void updateToken(String token) {
//		baiDuTokenDao.updateToken(token);		
	}

}
