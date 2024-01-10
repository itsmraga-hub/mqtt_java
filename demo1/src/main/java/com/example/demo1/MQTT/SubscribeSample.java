package com.example.demo1.MQTT;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubscribeSample {

    public void subscribeToMQTT() {
        String broker = "tcp://analytics.teleeza.africa:1883";
        String topic = "mqtt/test";
        String username = "teleezaAnalytics";
        String password = "teleezaAnalyticsAdmin2023";
        String clientid = UUID.randomUUID().toString();
        int qos = 0;

        try {
            MqttClient client = new MqttClient(broker, clientid, new MemoryPersistence());
            // connect options
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            options.setConnectionTimeout(60);
            options.setKeepAliveInterval(60);
            // setup callback
            client.setCallback(new MqttCallback() {

                public void connectionLost(Throwable cause) {
                    System.out.println("connectionLost: " + cause.getMessage());
                }

                public void messageArrived(String topic, MqttMessage message) throws MqttException {
                    System.out.println("topic: " + topic);
                    System.out.println("Qos: " + message.getQos());
                    System.out.println("message content: " + new String(message.getPayload()));
                    client.disconnect();
                    client.close();
                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("deliveryComplete---------" + token.isComplete());
                }

            });
            client.connect(options);
            System.out.println("MQTT connected");
            client.subscribe(topic, qos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
