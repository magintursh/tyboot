package org.typroject.tyboot.component.emq;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.typroject.tyboot.component.emq.message.EmqMessage;
import org.typroject.tyboot.core.foundation.utils.ValidationUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class EmqKeeper {

    private final Logger logger = LogManager.getLogger(EmqKeeper.class);


    private MqttClient client;
    private MqttConnectOptions options;
    private static ObjectMapper objectMapper;
    private Set<String> subscribeTopics;
    private EmqProperties emqProperties;

   public  EmqKeeper(EmqProperties emqProperties,MqttCallback mqttCallback) throws Exception
    {
        this.emqProperties  = emqProperties;
        objectMapper        = new ObjectMapper();
        subscribeTopics     = new HashSet<>(emqProperties.getSubTopics());
        client              = new MqttClient(emqProperties.getBroker(), emqProperties.getClientId(), new MemoryPersistence());// host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
        options             = new MqttConnectOptions();// MQTT的连接设置
        options.setCleanSession(true);// 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
        options.setConnectionTimeout(10);// 设置超时时间 单位为秒
        options.setKeepAliveInterval(20);// 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        client.setCallback(mqttCallback);// 设置回调
        connetToServer();

    }




    public void subscribe(String topic)throws Exception
    {
        getMqttClient().subscribe(topic,emqProperties.getQos());
        this.subscribeTopics.add(topic);
        logger.info("已订阅主题："+topic);
    }


    public void connetToServer() throws Exception
    {
        client.connect(options);
        logger.info("成功连接到EMQ;"+emqProperties.getBroker());

        if(!ValidationUtil.isEmpty(subscribeTopics))
            for(String topic:subscribeTopics)
                this.subscribe(topic);
    }



    public MqttClient getMqttClient()  throws Exception{
       if(ValidationUtil.isEmpty(client) || !client.isConnected())
           throw new Exception("mqttClient 未连接到服务器.");
        return client;
    }



    /**
     *
     * @param message
     * @throws MqttPersistenceException
     * @throws MqttException
     */
    public void publish(EmqMessage message) throws Exception{


        MqttTopic mqttTopic = getMqttClient().getTopic(message.getTopic());
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(objectMapper.writeValueAsString(message).getBytes("UTF-8"));

        MqttDeliveryToken token = mqttTopic.publish(mqttMessage);
        token.waitForCompletion();
        logger.info("message is published completely! "+ token.isComplete());
    }


    /**
     * 构建消息实体
     * @param body   消息内容
     * @param topic  主题
     * @param messageType 消息类型
     * @return
     */
    public  <T> EmqMessage<T> buildMessage(T body,String topic,String messageType) throws Exception
    {
        EmqMessage<T> emqMessage = new EmqMessage();
        emqMessage.setBody(body);
        emqMessage.setMessageId(UUID.randomUUID().toString());
        emqMessage.setMessageType(messageType);
        emqMessage.setTopic(topic);
        emqMessage.setSourceClientId(this.getMqttClient().getClientId());
        return emqMessage;
    }

}
