package org.typroject.tyboot.component.notification.sms;


import org.typroject.tyboot.component.amqp.MessageType;
import org.typroject.tyboot.component.amqp.RabbitConfig;

public class SmsType implements MessageType {


/*

    LOGIN_VERIFYCODE("登陆验证码","aliSmsMessageHandler"),

    REPAYMENT_SUCCESS("还款成功","huaxinSmsMessageHandler");

*/


   public  SmsType(String typeCode,String typeName, String messageHandler)
    {
        this.typeCode = typeCode;
        this.typeName = typeName;
        this.messageHandler = messageHandler;
    }

    private String typeName;
    private String typeCode;
    private String messageHandler;

    public String getQueue()
    {
        return RabbitConfig.QUEUE_SMS;//短信类型固定走短信的通道
    }

   public  String getType()
    {
        return this.typeCode;
    }

    public String getName()
    {
        return this.typeName;
    }

    public String getMessageHandler()
    {
        return this.messageHandler;
    }
}
