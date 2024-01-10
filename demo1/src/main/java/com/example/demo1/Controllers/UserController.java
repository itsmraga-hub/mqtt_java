package com.example.demo1.Controllers;

import com.example.demo1.MQTT.UserService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> createUser() throws MqttException {
        userService.createUser();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
