package org.typroject.tyboot.component.emq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.typroject.tyboot.component.emq.message.EmqMessage;
import org.typroject.tyboot.core.foundation.context.SpringContextHelper;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;

public abstract   class PushCallback  implements MqttCallback {

    private final Logger logger = LogManager.getLogger(PushCallback.class);

    private static ObjectMapper objectMapper;

    private String emqKeeperBeanName;




    public PushCallback(String emqKeeperBeanName)
    {
        this.emqKeeperBeanName = emqKeeperBeanName;
        if(ValidationUtil.isEmpty(objectMapper))
            objectMapper = new ObjectMapper();
    }


    @Override
    public void connectionLost(Throwable throwable) {
        logger.info("EMQ连接断开.");
        try {
            EmqKeeper emqKeeper = (EmqKeeper) SpringContextHelper.getBean(emqKeeperBeanName);
            emqKeeper.connetToServer();
        }catch (Exception e)
        {
            logger.info("EMQ重新连接失败.");
            e.printStackTrace();
        }


    }


    public abstract void processByTopic(EmqMessage emqMessage )throws Exception;



    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        logger.info("message id             : " + message.getId());
        logger.info("message topic          : " + topic);
        logger.info("message Qos            : " + message.getQos());
        String messageContent = new String(message.getPayload(),"UTF-8");
        logger.info("message Payload        : " + messageContent);

        EmqMessage emqMessage               = objectMapper.readValue(messageContent, EmqMessage.class);
        String sourceClientId               = emqMessage.getSourceClientId();
        logger.info("message sourceClientId : " + sourceClientId);


        EmqKeeper emqKeeper     = (EmqKeeper) SpringContextHelper.getBean(emqKeeperBeanName);
        String currentClientId  = emqKeeper.getMqttClient().getClientId();

        if(!sourceClientId.equals(currentClientId))//如果当前客户端关注了自己发布的主题，则对自己发布的消息不做处理
            processByTopic(emqMessage);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {


        logger.info("deliveryComplete---------" + token.isComplete());

    }

}
