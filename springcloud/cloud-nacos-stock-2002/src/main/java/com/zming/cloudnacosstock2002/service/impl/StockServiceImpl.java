package com.zming.cloudnacosstock2002.service.impl;

import com.zming.cloudnacosstock2002.mapper.StockMapper;
import com.zming.cloudnacosstock2002.service.StockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StockServiceImpl implements StockService {

    @Resource
    private StockMapper stockMapper;

    @Override
    public void decr() {
        stockMapper.decr();
    }
}
