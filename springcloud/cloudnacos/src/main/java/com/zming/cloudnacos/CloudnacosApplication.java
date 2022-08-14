package com.zming.cloudnacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudnacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudnacosApplication.class, args);
    }

}
