package com.zming.cloudnacos6000;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient  //nacos
@EnableFeignClients // openFegin
public class CloudNacos6000Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudNacos6000Application.class, args);
    }

    /*Ribbon使用需注册一个 bean*/
    // Ribbon 工具类库 作用：消费调用服务获取数据 服务间调用
    // 注入bean  适用openFegin 则不用在注入bean
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /*
     * 需要注入日志功能
     * */
    @Bean
    Logger.Level feignLoggerLevel() {
        // 开启日志级别
        return Logger.Level.FULL;
    }
}
