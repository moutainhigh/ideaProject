package com.zming.cloudnacos8083.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DemoController {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    /**
     * 服务间调用
     * */
    @GetMapping(value = "/getCustomer")
    public String getCustomer() {
        // 参数： 连接 参数返回类型 前端传入参数
        return restTemplate.getForObject(serverUrl+"/getServerPort",String.class);
    }
}
