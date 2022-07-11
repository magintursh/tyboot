package org.typroject.tyboot.api.controller.systemctl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.typroject.tyboot.api.face.systemctl.enumeration.SmsType;
import org.typroject.tyboot.api.face.systemctl.service.SmsRecordService;
import org.typroject.tyboot.core.foundation.context.RequestContext;
import org.typroject.tyboot.core.foundation.enumeration.UserType;
import org.typroject.tyboot.core.restful.doc.TycloudOperation;
import org.typroject.tyboot.core.restful.doc.TycloudResource;
import org.typroject.tyboot.core.restful.utils.ResponseHelper;
import org.typroject.tyboot.core.restful.utils.ResponseModel;


/**
 * Created by magintursh on 2017-05-03.
 */

@TycloudResource(name = "短信",module = "systemctl", resource = "sms")
@RequestMapping(path = "/v1/systemctl/sms")
@RestController
public class SmsResource {
    private final Logger logger = LogManager.getLogger(FeedbackResource.class);


    @Autowired
    private SmsRecordService smsRecordService;


    @TycloudOperation(operation = "发送短信验证码",ApiLevel = UserType.ANONYMOUS, needAuth = false)
    @RequestMapping(value = "/{smsType}/verify/{mobile}", method = RequestMethod.POST)
    public ResponseModel sendVerifyCode(@PathVariable String smsType, @PathVariable String mobile) throws Exception {
        RequestContext.setExeUserId("SYSTEM");
        SmsType type = SmsType.getInstance(smsType);
        return ResponseHelper.buildResponse(smsRecordService.sendVerificationCodeSms(type.name(), mobile, type.getMessageHandler()));
    }


    @TycloudOperation(operation = "认证手机和验证码",ApiLevel = UserType.ANONYMOUS, needAuth = false)
    @RequestMapping(value = "/{smsType}/verify/{mobile}/{verifyCode}/verification", method = RequestMethod.GET)
    public ResponseModel smsForLogin(@PathVariable String smsType, @PathVariable String mobile, @PathVariable String verifyCode) throws Exception {
        RequestContext.setExeUserId("SYSTEM");
        return ResponseHelper.buildResponse(smsRecordService.isVerifyCodeEnable(mobile, verifyCode, smsType));
    }


}