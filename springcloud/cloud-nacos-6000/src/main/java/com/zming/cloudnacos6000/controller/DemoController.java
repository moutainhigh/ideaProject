package com.zming.cloudnacos6000.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zming.cloudcommon.entity.JsonResult;
import com.zming.cloudnacos6000.exception.BlockHandler;
import com.zming.cloudnacos6000.exception.FallBack;
import com.zming.cloudnacos6000.service.OpenFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@RestController
public class DemoController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    // openfegin 使用
    /**
     * openfegin调用请求 默认等待业务1s钟，超时则报错。
     * 解决方式：因为openfegin底层整合了Ribbon, 则需要在yml中设置超时时间控制
     * */
    @Autowired
    private OpenFeginService openFeginService;

    /**
     * Ribbon使用
     * 服务间调用
     * */
    @GetMapping(value = "/getResult/{id}")
    @SentinelResource(value = "getResult",
            fallbackClass = FallBack.class, fallback = "fallBackOne",
            blockHandlerClass = BlockHandler.class, blockHandler = "handlerOne",
            exceptionsToIgnore = {NullPointerException.class,RuntimeException.class})
    public JsonResult getResult(@PathVariable Long id) {
        // 参数： 连接 参数返回类型 前端传入参数
        if (id <= 3) {
            JsonResult<String> result = restTemplate.getForObject(serverUrl+"/getResult/"+id, JsonResult.class);
            return result;
        } else {
            throw new NullPointerException();
        }

    }

    /**
     * openFiegin 使用
     * */
    @GetMapping(value = "/getResultTwo/{id}")
    @SentinelResource(value = "getResultTwo")
    public JsonResult getResultTwo(@PathVariable Long id) {
        return openFeginService.getResult(id);
    }


    @GetMapping(value = "/getServerTime")
    public String getServerTime() {
        return openFeginService.getServerTime();
    }
}
