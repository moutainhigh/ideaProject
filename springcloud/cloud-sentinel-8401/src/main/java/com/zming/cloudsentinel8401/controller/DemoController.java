package com.zming.cloudsentinel8401.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zming.cloudsentinel8401.exception.BlockHandler;
import com.zming.cloudsentinel8401.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/sentinel")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping(value = "getTestA")
    public String getTestA() {
        System.out.println("------------TestA");
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        return demoService.common();
    }

    @GetMapping(value = "getTestB")
    public String getTestB() {
        return demoService.common();
    }


    @GetMapping(value = "getTestC")
    public String getTestC() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        System.out.println("+++++TestC");
        return "+++++TestC";
    }

    /**
     * throw new Runtime
     * @RequestParam 下 required = false 非必填参数
     * */
    @GetMapping(value = "getTestD")
    @SentinelResource(value = "testD", blockHandler = "exTestD")
    public String getTestD(@RequestParam(value = "hot1", required = false) String hot1,
                           @RequestParam(value = "hot2", required = false) String hot2,
                           @RequestParam(value = "hot3", required = false) String hot3) {

        return "this is testD";
    }

    public String exTestD(String hot1, String hot2, String hot3, BlockException e) {
        return "系统繁忙，请稍后再试！";
    }


    @GetMapping(value = "/getTestE")
    @SentinelResource(value = "getTestE")
    public String getTestE() {
        return "----------TestE";
    }
    /**
     * 自定义处理限流方法
     * */
    @GetMapping(value = "/getTestF")
    @SentinelResource(value = "getTestF",
            blockHandlerClass = BlockHandler.class,
            blockHandler = "handlerOne")
    public String getTestF() {
        return "----------TestF";
    }
}
