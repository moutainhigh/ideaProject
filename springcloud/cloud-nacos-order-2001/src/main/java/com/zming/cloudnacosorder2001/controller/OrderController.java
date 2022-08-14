package com.zming.cloudnacosorder2001.controller;

import com.zming.cloudnacosorder2001.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    @GlobalTransactional // 开启AT分布式事务
    public String create() {
        orderService.create();
        return "订单生成成功！";
    }
}
