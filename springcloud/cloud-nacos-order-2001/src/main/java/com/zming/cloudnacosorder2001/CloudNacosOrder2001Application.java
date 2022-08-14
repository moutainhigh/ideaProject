package com.zming.cloudnacosorder2001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CloudNacosOrder2001Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudNacosOrder2001Application.class, args);
    }

}
