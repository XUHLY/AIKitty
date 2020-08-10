package com.aikitty.cfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @description:全局变量配置类
 * @author: huangliyang
 * @Date：2019/01/29
 */
@Component
public class AppCfg {

    /**
     * 上传录音临时存储位置
     **/
    @Value("${application.file.dir}")
    private String springTempPath;

    @Value("${application.baidu.unit.app_id}")
    private String springUnitAppId;
    @Value("${application.baidu.unit.api_key}")
    private String springUnitApiKey;
    @Value("${application.baidu.unit.secret_key}")
    private String springUnitSecretKey;
    @Value("${application.baidu.unit.talkUrl}")
    private String springUnitTalkUrl;
    @Value("${application.baidu.unit.authHost}")
    private String springUnitAuthHost;

    public static String TEMP_PATH;
    public static String UNIT_APP_ID;
    public static String UNIT_API_KEY;
    public static String UNIT_SECRET_KEY;
    public static String UNIT_TALK_URL;
    public static String UNIT_AUTH_HOST;

    @PostConstruct
    public void init() {
        TEMP_PATH = springTempPath;
        UNIT_APP_ID = springUnitAppId;
        UNIT_API_KEY = springUnitApiKey;
        UNIT_SECRET_KEY = springUnitSecretKey;
        UNIT_TALK_URL = springUnitTalkUrl;
        UNIT_AUTH_HOST = springUnitAuthHost;
    }
}
