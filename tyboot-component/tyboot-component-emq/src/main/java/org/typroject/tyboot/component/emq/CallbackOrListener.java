package org.typroject.tyboot.component.emq;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;
import org.typroject.tyboot.core.foundation.context.SpringContextHelper;

public abstract class CallbackOrListener implements MqttCallback, IMqttMessageListener {

    private final Logger logger = LogManager.getLogger(CallbackOrListener.class);


    @Override
    public void connectionLost(Throwable throwable) {
        logger.info("EMQ连接断开.");


        //尝试重新连接，
        //10次
        for (int i = 0; i < 10; i++) {

            logger.info("第 " + i + " 次尝试重新连接.");
            EmqKeeper emqKeeper = (EmqKeeper) SpringContextHelper.getBean(EmqKeeper.class);

            if (!emqKeeper.getMqttClient().isConnected())
                emqKeeper.connetToServer();

            if (emqKeeper.getMqttClient().isConnected())
                break;
            try {
                Thread.sleep(10L * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public abstract void processMessage(String topic, MqttMessage message) throws Exception;


    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        try {
            // subscribe后得到的消息会执行到这里面
            logger.debug("message id             : " + message.getId());
            logger.debug("message topic          : " + topic);
            logger.debug("message Qos            : " + message.getQos());
            byte[] messageContent = message.getPayload();
            logger.debug("message Payload        : " + new String(messageContent));

            processMessage(topic, message);
        } catch (Exception e) {
            //报错后断掉的问题，临时将错误吃掉。
            e.printStackTrace();
        }

    }

}
