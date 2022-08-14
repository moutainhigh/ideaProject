package com.zming.cloudnacosstock2002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudNacosStock2002Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudNacosStock2002Application.class, args);
    }

}
