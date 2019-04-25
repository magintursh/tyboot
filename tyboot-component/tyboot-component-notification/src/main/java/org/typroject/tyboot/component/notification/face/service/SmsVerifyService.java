
package org.typroject.tyboot.component.notification.face.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.typroject.tyboot.component.amqp.AmqpMessage;
import org.typroject.tyboot.component.amqp.AmqpSender;
import org.typroject.tyboot.component.notification.face.model.SmsVerifyModel;
import org.typroject.tyboot.component.notification.face.orm.dao.SmsVerifyMapper;
import org.typroject.tyboot.component.notification.face.orm.entity.SmsVerify;
import org.typroject.tyboot.component.notification.sms.SmsTemplate;
import org.typroject.tyboot.core.foundation.utils.Bean;
import org.typroject.tyboot.core.foundation.utils.Sequence;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;
import org.typroject.tyboot.core.rdbms.service.BaseService;

import java.util.Date;
import java.util.HashMap;
/**
 * <p>
 * 验证码发送记录 服务类
 * </p>
 *
 * @author 子杨
 * @since 2017-09-09
 */
@Component
public class SmsVerifyService extends BaseService<SmsVerifyModel,SmsVerify,SmsVerifyMapper>
{
    private final Logger logger = LogManager.getLogger(SmsVerifyService.class);

    public static final String DICT_SMS_TEMPLATE = "SMS_TEMPLATE";


    @Autowired
    private AmqpSender amqpSender;



    public SmsVerifyModel createSms(String smsType,String mobile,String verifyCode,String smsTemplate) throws Exception
    {
        SmsVerifyModel model = new SmsVerifyModel();
        model.setMobile(mobile);
        model.setSendTime(new Date());
        model.setSmsCode(smsTemplate);
        model.setSmsType(smsType);
        model.setSmsVerify(verifyCode);
       return  this.createWithModel(model);
    }



    public SmsVerifyModel queryForVerification(String smsType,String mobile,String verifyCode) throws Exception
    {
        SmsVerifyModel returnModel = null;
        SmsVerifyModel smsVerify = new SmsVerifyModel();
        smsVerify.setMobile(mobile);
        smsVerify.setSmsVerify(verifyCode);
        smsVerify.setSmsType(smsType);
        smsVerify = this.queryByModel(smsVerify);

        if(!ValidationUtil.isEmpty(smsVerify) && !ValidationUtil.isEmpty(smsVerify.getSequenceNbr()))
        {
            returnModel = Bean.toModel(smsVerify,new SmsVerifyModel());
        }
        return returnModel;
    }

    /**
     *
     * @param smsTemplate  短信模板，发送验证码的时候 模板中的 params 不用填值，系统会自动生成验证码
     *                     短信内容模板中也只能有一个变量
     * @return
     * @throws Exception
     */
    public SmsVerifyModel sendVerifyCode(SmsTemplate smsTemplate) throws Exception
    {
        //配置短信参数

        smsTemplate.setParams(paramForVerifyCode(smsTemplate.getParams()));
        //組裝发送amp消息内容
        sendSmsToAmqp(smsTemplate);
        return this.createSms(smsTemplate.getSmsType().getType(),smsTemplate.getMobile(),smsTemplate.getParams().get(smsTemplate.getParams().keySet().iterator().next()),smsTemplate.getTemplateId());
    }


    public SmsVerifyModel sendCommonSms(SmsTemplate smsTemplate)throws Exception
    {
        SmsVerifyModel returnModel = null;

        //配置短信参数
        if(!ValidationUtil.isEmpty(smsTemplate))
        {
            //保存短信記錄
            returnModel = this.createSms(smsTemplate.getSmsType().getType(),smsTemplate.getMobile(),null,smsTemplate.getTemplateId());

            //組裝发送amp消息内容
            sendSmsToAmqp(smsTemplate);
        }else{
            throw new Exception("短信发送失败请稍后重试.");
        }
        return returnModel;
    }






    private HashMap<String,String> paramForVerifyCode(HashMap<String,String> smsParams) throws Exception
    {
        String verifyCode =  Sequence.generatorSmsVerifyCode4();

        if(!ValidationUtil.isEmpty(smsParams) && smsParams.size() == 1)
        {
            smsParams.put(smsParams.keySet().iterator().next(),verifyCode);
        }else{
            logger.error("短信发送失败，短信模板配置有误");
            throw new Exception("短信发送失败请稍后重试.");
        }
        return smsParams;
    }





    private void sendSmsToAmqp(SmsTemplate smsTemplate)
    {
        AmqpMessage amqpMessage =
                AmqpSender.buildMessage(smsTemplate.getSmsType().getQueue(),smsTemplate.getSmsType().getMessageHandler(),smsTemplate);
        amqpSender.send(amqpMessage);
    }

}
