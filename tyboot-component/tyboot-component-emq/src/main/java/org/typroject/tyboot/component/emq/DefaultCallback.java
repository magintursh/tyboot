package org.typroject.tyboot.component.emq;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class DefaultCallback extends CallbackOrListener {

    private final Logger logger = LogManager.getLogger(EmqKeeper.class);

    @Override
    public void processMessage(String topic, MqttMessage message) throws Exception {
        logger.debug("DefaultCallback ");
        logger.debug(new String(message.getPayload()));
    }


    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

        logger.debug("deliveryComplete---------" + token.isComplete());

    }
}
