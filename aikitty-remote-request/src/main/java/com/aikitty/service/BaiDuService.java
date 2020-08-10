package com.aikitty.service;


import com.aikitty.vo.UnitRes;

/**
 * @author huangliyang
 * @description
 * @date 2020/8/10
 */
public interface BaiDuService {

    /**
     * 语音识别出文本
     * @param audioPath 语音文件所在路径
     * @param tempFileName 临时文件名
     * @return 返回语音识别的文本
     * @throws Exception 异常
     */
    String  voiceRecognition(String audioPath, String tempFileName) throws Exception;


    /**
     * 语音合成的方法
     * @param Text 要合成语音的文本
     * @param per 设置发音人  0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女
     * @return
     */
    boolean  voiceSynthesis(String Text, String per);

    /**
     * 与unit对话，unit返回的内容封装
     * @param query
     * @return
     */
    UnitRes getUnitRes(String query);

}
