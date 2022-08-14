package com.zming.cloudnacos6000.service;

import com.zming.cloudcommon.entity.JsonResult;
import com.zming.cloudnacos6000.service.impl.OpenFeginServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * 此接口配合使用OpenFegin的接口
 * 在此接口中添加@FeginClient注解
 * 然后在此接口中标致要远程调用的方法
 * 方法签名和调用方法保持一致
 *
 * fallback 容错降级业务类 服务调用失败
 *
 * */
@Service
@FeignClient(value = "nacos-producer", fallback = OpenFeginServiceImpl.class) // 表示远程调用的名称
public interface OpenFeginService {
    @GetMapping(value = "/getResult/{id}")
    public JsonResult<String> getResult(@PathVariable Long id);


    @GetMapping(value = "/getServerTime")
    public String getServerTime();
}
