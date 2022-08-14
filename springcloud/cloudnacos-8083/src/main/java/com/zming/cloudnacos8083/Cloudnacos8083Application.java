package com.zming.cloudnacos8083;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class Cloudnacos8083Application {

    public static void main(String[] args) {
        SpringApplication.run(Cloudnacos8083Application.class, args);
    }
    // Ribbon 工具类库 作用：消费调用服务获取数据 服务间调用
    // 注入bean
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
