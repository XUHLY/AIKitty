package com.aikitty.service.impl;

import com.aikitty.cfg.AppCfg;
import com.aikitty.vo.UnitReq;
import com.aikitty.service.BaiDuService;
import com.aikitty.utils.BaiDuHttpUtils;
import com.aikitty.utils.Mp3ToPcmUtil;
import com.aikitty.vo.UnitRes;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huangliyang
 * @description
 * @date 2020/8/10
 */
@Service
public class BaiDuServiceImpl implements BaiDuService {

    private final static Logger LOGGER = LoggerFactory.getLogger(BaiDuServiceImpl.class);

    @Override
    public String voiceRecognition(String audioPath, String tempFileName) throws Exception {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(AppCfg.UNIT_APP_ID, AppCfg.UNIT_API_KEY,
                AppCfg.UNIT_SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        // client.setHttpProxy("proxy_host", proxy_port); // 设置http代理
        // client.setSocketProxy("proxy_host", proxy_port); // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        // JSONObject res = client.asr("C:/Users/hp/Documents/Tencent
        // Files/1968171453/FileRecv/20171127_213608.wav", "wav", 16000, null);
        // System.out.println(res.toString(2));

        // 将接收到的mp3文件转成pcm文件
        Mp3ToPcmUtil.mp3ToPcm(audioPath + tempFileName, audioPath + "audio.pcm");
        // 设置请求语音识别的参数
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("dev_pid", 1536);
        // 发起请求
        JSONObject res = client.asr(audioPath + "audio.pcm", "pcm", 16000, options);
        // 获得key为“result”的识别内容
        JSONArray result = (JSONArray) res.get("result");
        return result.getString(0);
    }

    @Override
    public boolean voiceSynthesis(String Text, String per) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(AppCfg.UNIT_APP_ID, AppCfg.UNIT_API_KEY,
                AppCfg.UNIT_SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        // client.setHttpProxy("proxy_host", proxy_port); // 设置http代理
        // client.setSocketProxy("proxy_host", proxy_port); // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 设置可选参数
        HashMap<String, Object> options = new HashMap<String, Object>();
        //spd 语速，取值0-9，默认为5中语速
        options.put("spd", "4");
        //pit 音调，取值0-9，默认为5中语调
        options.put("pit", "5");
        //per 发音人选择, 0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女
        options.put("per", per);
        //vol 音量，取值0-15，默认为5中音量
        // 调用接口
        TtsResponse res = client.synthesis(Text, "zh", 1, options);
        byte[] data = res.getData();
        JSONObject res1 = res.getResult();
        if (data != null) {
            try {
                // 获取跟目录
                File path = new File(AppCfg.TEMP_PATH);
                if (!path.exists()) {
                    path = new File(AppCfg.TEMP_PATH);
                }
                String videoPath = path.getAbsolutePath();
                Util.writeBytesToFileSystem(data, videoPath + "/output.mp3");
            } catch (IOException e) {
                LOGGER.error("语音识别为文本失败，异常为：", e);
                return false;
            }
        }
        return true;
    }


    /**
     * @param query 对话问的文本
     * @return UnitRes UNIT返回的对话封装
     */
    @Override
    public UnitRes getUnitRes(String query) {

        //返回的对象
        UnitRes ur = new UnitRes();
        // 请求URL
        String talkUrl = AppCfg.UNIT_TALK_URL;
        //对话参数对象
        UnitReq urp = new UnitReq();
        String version = "2.0";
        String logId = "UNITTEST_10000";
        String serviceId = "S11756";
        List<String> skillIds = null;
        String sessionId = "service-session-id-1550565063117-68c00eb498c94065946f4e71d141159f";
        HashMap<String, String> dialogState = new HashMap<String, String>();
        HashMap<String, String> request = new HashMap<String, String>();
        request.put("query", query);
        request.put("user_id", "88888");

        urp.setVersion(version);
        urp.setLog_id(logId);
        urp.setService_id(serviceId);
        urp.setSkill_ids(null);
        urp.setSession_id(sessionId);
        urp.setDialog_state(dialogState);
        urp.setRequest(request);

        Object json = com.alibaba.fastjson.JSONObject.toJSON(urp);

        try {
            // 请求参数
            String params = json.toString();
            System.out.println(json.toString());
            String accessToken = "24.47ba3221a9f505e1f40e5f65f89f9d2c.2592000.1597564405.282335-15332293";
            String result = BaiDuHttpUtils.post(talkUrl, accessToken, "application/json", params);
            System.out.println(result);
            com.alibaba.fastjson.JSONObject resultObject =
                    (com.alibaba.fastjson.JSONObject) com.alibaba.fastjson.JSONObject.parseObject(result).get("result");
            com.alibaba.fastjson.JSONArray responselist = (com.alibaba.fastjson.JSONArray) resultObject.get(
                    "response_list");
            com.alibaba.fastjson.JSONObject responselist_0 = (com.alibaba.fastjson.JSONObject) responselist.get(0);
            //回答的话
            String say =
                    (String) ((com.alibaba.fastjson.JSONObject) ((com.alibaba.fastjson.JSONArray) responselist_0.get(
                            "action_list")).get(0)).get("say");
            //获取返回的意图名
            String intentName =
                    (String) ((com.alibaba.fastjson.JSONObject) responselist_0.get("schema")).get("intent");
            //词槽列表
            com.alibaba.fastjson.JSONArray slots =
                    (com.alibaba.fastjson.JSONArray) ((com.alibaba.fastjson.JSONObject) responselist_0.get("schema")).get("slots");

            //词槽map
            Map<String, Map<String, String>> userSlots = new HashMap<String, Map<String, String>>();

            if (slots != null) {
                for (Object slot : slots) {
                    Map<String, String> userSlot = new HashMap<String, String>();
                    com.alibaba.fastjson.JSONObject s = (com.alibaba.fastjson.JSONObject) slot;
                    userSlot.put("original_word", s.get("original_word").toString());
                    userSlot.put("normalized_word", s.get("normalized_word").toString());
                    userSlots.put(s.getString("name"), userSlot);
                }
            }

            //设置返回对象
            ur.setSay(say);
            ur.setIntentName(intentName);
            ur.setUserSlots(userSlots);

        } catch (Exception e) {
            LOGGER.error("和unit对话出错：",e);
        }
        return ur;
    }
}
