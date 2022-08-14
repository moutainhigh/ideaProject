package com.zming.cloudnacos6002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudNacos6002Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudNacos6002Application.class, args);
    }

}
