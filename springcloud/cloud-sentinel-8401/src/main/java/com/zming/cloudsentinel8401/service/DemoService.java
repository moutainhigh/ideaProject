package com.zming.cloudsentinel8401.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zming.cloudsentinel8401.exception.BlockHandler;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    /**
     * 可设置自定义流控规则
     * */
    @SentinelResource("common")
    /*@SentinelResource(value = "getTestF",
            blockHandlerClass = BlockHandler.class,
            blockHandler = "handlerOne")*/
    public String common() {
        return "common";
    }
}
