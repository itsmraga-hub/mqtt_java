package com.example.demo1.MQTT;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PublishSample {


    public void publishMessage(String msg, String payload) {

        String broker = "tcp://analytics.teleeza.africa:1883";
        String topic = "mqtt/test";
        String username = "teleezaAnalytics";
        String password = "teleezaAnalyticsAdmin2023";
        String clientid = UUID.randomUUID().toString();
        // String content = "Hello MQTT";
        int qos = 0;

        try {
            MqttClient client = new MqttClient(broker, clientid, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            options.setConnectionTimeout(60);
            options.setKeepAliveInterval(60);
            // connect
            client.connect(options);
            // create message and setup QoS
            // MqttMessage message = new MqttMessage(content.getBytes());
            MqttMessage message = new MqttMessage(msg.getBytes());
            message.setQos(qos);
            // message.setPayload();
            // publish message
            client.publish(topic, message);
            System.out.println("Message published");
            System.out.println("Name: " + msg);
            System.out.println("topic: " + topic);
            System.out.println("Payload: " + payload);
            // disconnect
            client.disconnect();
            // close client
            client.close();
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }
}
