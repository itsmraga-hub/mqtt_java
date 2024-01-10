package com.example.demo1.MQTT;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class MQTTClientBackgroundService {

    // String broker = "tcp://broker.emqx.io:1883";
    // String broker = "analytics.teleeza.africa:1883";
    // TLS/SSL
    // String broker = "ssl://broker.emqx.io:8883";
    // String username = "teleezaAnalytics";
    // String password = "bytes";
    // String clientid = "publish_client";

    private static final byte[] bytes = "teleezaAnalyticsAdmin2023".getBytes(StandardCharsets.US_ASCII);
    private static final char[] password = "teleezaAnalyticsAdmin2023".toCharArray();
    private final String mqttUsername = "teleezaAnalytics";
    private final byte[] mqttPassword = bytes;
    private final String mqttIpAddress = "analytics.teleeza.africa";
    private final int mqttPortAddress = 1883;
    private final String testTopic = "BaseTeleezaClientAppPublishTopic";

    private final PublishSample publishSample;

    private final SubscribeSample subscribeSample;

    public MQTTClientBackgroundService(PublishSample publishSample, SubscribeSample subscribeSample) {
        this.publishSample = publishSample;
        this.subscribeSample = subscribeSample;
    }

    /*private IMqttClient client = new MqttFactory().CreateMqttClient();
    static byte[] bytes = Encoding.ASCII.GetBytes("teleezaAnalyticsAdmin2023");
    private readonly string mqttUsername = "teleezaAnalytics";
    private readonly byte[] mqttPassword = bytes;
    // private readonly string mqttIpAddress = "18.222.172.61";
    private readonly string mqttIpAddress = "analytics.teleeza.africa";
    // private readonly string mqttIpAddress = "3.15.232.204";
    private readonly int mqttPortAddrerss = 1883;
    private readonly string testTopic = "BaseTeleezaClientAppPublishTopic";*/



    public static void main(String[] args) {

        String broker = "tcp://broker.emqx.io:1883";
        String topic = "mqtt/test";
        String username = "emqx";
        String password = "public";
        String clientid = "publish_client";
        String content = "Hello MQTT";
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
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            // publish message
            client.publish(topic, message);
            System.out.println("Message published");
            System.out.println("topic: " + topic);
            System.out.println("message content: " + content);
            // disconnect
            client.disconnect();
            // close client
            client.close();
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

}
