package com.zming.cloudnacosorder2001.service.impl;

import com.zming.cloudnacosorder2001.client.StockClientService;
import com.zming.cloudnacosorder2001.mapper.OrderMapper;
import com.zming.cloudnacosorder2001.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private StockClientService stockClientService;

    @Override
    public void create() {
        // 减库存
        /*stockClientService.stocKDecr();
        //int i = 1/0;
        //创建订单
        orderMapper.createOrder();*/
    }
}
