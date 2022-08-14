package com.zming.cloudnacos6000.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zming.cloudcommon.entity.JsonResult;

/**
 * @SentinelResource 中 自定义Handler
 * 异常是 BlockException
 * */
public class BlockHandler {
    // 方法必须是static 返回类型和主方法保持一直
    public static JsonResult<String> handlerOne(Long id, BlockException e) {
        return new JsonResult<>(400,false,"fallBackOne---:"+id + "限流：网络异常");
    }

    public static JsonResult<String> handlerTwo(Long id,BlockException e) {
        return new JsonResult<>(400,false,"fallBackTwo---:"+id + "限流：访问繁忙");
    }
}
