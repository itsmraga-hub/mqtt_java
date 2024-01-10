package com.example.demo1.MQTT;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.context.annotation.Bean;

public class MQTTClientService {
    private static final String MQTT_PUBLISHER_ID = "spring-server";
    private static final String MQTT_SERVER_ADDRES= "tcp://127.0.0.1:1883";
    private static IMqttClient instance;

    public static IMqttClient getInstance() {
        try {
            if (instance == null) {
                instance = new MqttClient(MQTT_SERVER_ADDRES, MQTT_PUBLISHER_ID);
            }

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);

            if (!instance.isConnected()) {
                instance.connect(options);
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }

        return instance;
    }

    private MQTTClientService() {

    }

    public void publish(final String topic, final String payload, int qos, boolean retained)
            throws MqttPersistenceException, MqttException {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(payload.getBytes());
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(retained);
        // Mqtt.getInstance().publish(messagePublishModel.getTopic(), mqttMessage);
        // MQTTClientService.getInstance().publish(messagePublishModel.getTopic(), mqttMessage);
        MQTTClientService.getInstance().publish("William Raga", mqttMessage);


        // mqttClient.disconnect();
    }

    public void subscribe(final String topic) throws MqttException, InterruptedException {
        System.out.println("Messages received:");

        MQTTClientService.getInstance().subscribeWithResponse(topic, (tpic, msg) -> {
            System.out.println(msg.getId() + " -> " + new String(msg.getPayload()));
        });
    }


}
