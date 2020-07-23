package com.mario.springcloudclient;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@Slf4j
public class HelloController {
    @Autowired
    private DiscoveryClient client;
    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HelloSpringCloud hello() throws InterruptedException {
        ServiceInstance instance = client.getLocalServiceInstance();
        int sleepTime = new Random().nextInt(3000);
        log.info("Sleep {}", sleepTime);
        Thread.sleep(sleepTime);
        log.info("/hello, host: " + instance.getHost() + ", service_id: " + instance.getServiceId());
        return new HelloSpringCloud("hello spring cloud");
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String index() {
        return "hello spring cloud";
    }
}
