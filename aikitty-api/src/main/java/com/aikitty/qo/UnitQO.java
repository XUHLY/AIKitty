package com.aikitty.qo;

import java.util.HashMap;
import java.util.List;

/**
 * @author huangliyang
 * @description Unit请求参数
 * @date 2020/7/20
 */
public class UnitQO {
    /**
     * 2.0，当前api版本对应协议版本号为2.0，固定值
     */
    private String version;
    /**
     * 可选	机器人ID，service_id 与skill_ids不能同时缺失，至少一个有值。
     */
    private String service_id;
    /**
     * 可选	技能ID列表
     */
    private List<String> skill_ids;
    /**
     * 必需	开发者需要在客户端生成的唯一id，用来定位请求，响应中会返回该字段。对话中每轮请求都需要一个log_id
     */
    private String log_id;
    /**
     * 必需	session保存机器人的历史会话信息，由机器人创建，客户端从上轮应答中取出并直接传递，不需要了解其内容。
     */
    private String session_id;
    /**
     * 可选 机器人对话状态
     */
    private HashMap<String, String> dialog_state;
    /**
     * 必需	本轮请求体。
     */
    private HashMap<String, String> request;



}

/**
 * 机器人对话状态
 */
class dialogState {

}
