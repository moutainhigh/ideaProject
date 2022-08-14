package com.zming.cloudsentinel8401.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @SentinelResource 中 自定义Handler
 * */
public class BlockHandler {
    // 方法必须是static 返回类型和主方法保持一直
    public static String handlerOne(BlockException e) {
        return "handlerOne---:系统异常，请重试！";
    }
    public static String handlerTwo(BlockException e) {
        return "handlerTwo---:网络异常，请重试！";
    }
}
