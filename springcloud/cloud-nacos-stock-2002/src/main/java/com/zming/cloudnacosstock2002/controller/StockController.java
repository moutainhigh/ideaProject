package com.zming.cloudnacosstock2002.controller;

import com.zming.cloudnacosstock2002.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/decr")
    public String stocKDecr(){
        stockService.decr();
        return "库存减少成功";
    }
}
