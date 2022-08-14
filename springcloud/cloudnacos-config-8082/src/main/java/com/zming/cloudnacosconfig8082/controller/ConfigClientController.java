package com.zming.cloudnacosconfig8082.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope /*动态刷新配置文件 针对于编辑*/
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;
    @GetMapping(value = "/info")
    public String getConfigInfo(){
        return configInfo;
    }
}
