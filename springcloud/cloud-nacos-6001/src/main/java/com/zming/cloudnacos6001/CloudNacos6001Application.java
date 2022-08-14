package com.zming.cloudnacos6001;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudNacos6001Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudNacos6001Application.class, args);
    }
}
