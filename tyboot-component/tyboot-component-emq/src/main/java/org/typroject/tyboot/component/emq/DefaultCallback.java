package org.typroject.tyboot.component.emq;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class DefaultCallback extends CallbackOrListener {

    private final Logger logger = LogManager.getLogger(EmqKeeper.class);

    @Override
    public void processMessage(String topic, MqttMessage message) throws Exception {
        logger.info("DefaultCallback ");
        logger.info(new String(message.getPayload()));
    }
}
