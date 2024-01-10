package com.example.demo1.MQTT;

import com.example.demo1.MQTT.PublishSample;
import com.example.demo1.MQTT.SubscribeSample;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final ApplicationEventPublisher eventPublisher;

    private final PublishSample publishSample;
    private final SubscribeSample subscribeSample;

    public UserService(ApplicationEventPublisher eventPublisher, PublishSample publishSample, SubscribeSample subscribeSample) {
        this.eventPublisher = eventPublisher;
        // this.mqttPublisherService = mqttPublisherService;
        this.publishSample = publishSample;
        this.subscribeSample = subscribeSample;
    }

    public void createUser(/* User details */) throws MqttException {
        // Logic to create a user
        subscribeSample.subscribeToMQTT();
        System.out.println();
        // Publish user created event
        // mqttPublisherService.publishMessage("William Raga", "William");
        publishSample.publishMessage("New User Created", "0");
        // eventPublisher.publishEvent();
    }
}

