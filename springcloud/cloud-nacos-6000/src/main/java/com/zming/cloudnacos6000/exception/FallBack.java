package com.zming.cloudnacos6000.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zming.cloudcommon.entity.JsonResult;

/**
 * @SentinelResource 中 自定义FallBack
 * 方法必须是static 返回类型和主方法保持一直
 * 异常是Throwable
 * */
public class FallBack {
    // 方法必须是static 返回类型和主方法保持一直
    public static JsonResult<String> fallBackOne(Long id, Throwable e) {
        return new JsonResult<>(400,false,"fallBackOne---:"+id + "出现未知商品");
    }
    public static JsonResult<String> fallBackTwo(Long id,Throwable e) {
        return new JsonResult<>(400,false,"fallBackTwo---:"+id + "出现未知商品");
    }
}
