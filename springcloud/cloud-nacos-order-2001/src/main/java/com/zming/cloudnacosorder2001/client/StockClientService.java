package com.zming.cloudnacosorder2001.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "seata-stock")
@RequestMapping("/stock")
public interface StockClientService {
    @GetMapping("/decr")
    public String stocKDecr();
}
