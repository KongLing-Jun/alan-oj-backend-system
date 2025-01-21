package com.thinking.common.message.service;

import com.alibaba.fastjson2.JSON;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.thinking.common.core.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : AliSmsService
 * @description : [阿里云短信服务]
 * @createTime : [2025/1/21 14:31]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/21 14:31]
 * @updateRemark : [v1.0]
 */
@Component
public class AliSmsService {

    @Autowired
    private Client aliClient;

    private final static Logger logger = LoggerFactory.getLogger(AliSmsService.class);

    //业务配置
    @Value("${sms.aliyun.templateCode}") // 短信模板code
    private String templateCode;

    @Value("${sms.aliyun.signName}")     // 短信签名
    private String signName;

    /**
     * 发送短信验证码
     *
     * @param mobile
     * @param code
     */
    public boolean sendMobileCode(String mobile, String code) {
        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        return sendTempMessage(mobile,signName,templateCode,params);
    }

    private boolean sendTempMessage(String mobile, String signName, String templateCode, Map<String, String> params) {
        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.setPhoneNumbers(mobile);
        sendSmsRequest.setSignName(signName);
        sendSmsRequest.setTemplateCode(templateCode);
        sendSmsRequest.setTemplateParam(JSON.toJSONString(params));
        try {
            SendSmsResponse sendSmsResponse = aliClient.sendSms(sendSmsRequest);
            SendSmsResponseBody sendSmsResponseBody = sendSmsResponse.getBody();
            if (!Constants.SEND_MESSAGE_SUCCESS.equalsIgnoreCase(sendSmsResponseBody.getCode())){
                logger.error("短信{} 发送失败，失败原因:{}.... ",JSON.toJSONString(sendSmsRequest), sendSmsResponseBody.getMessage());
                return false;
            }
            return true;
        } catch (Exception e) {
            logger.error("短信{} 发送失败，失败原因:{}.... ",JSON.toJSONString(sendSmsRequest),e.getMessage());
            return false;
        }
    }
}
