package com.zming.cloudnacosconfig8082;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudnacosConfig8082Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudnacosConfig8082Application.class, args);
    }

}
