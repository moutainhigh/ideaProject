package com.zming.cloudnacos6000.service.impl;

import com.zming.cloudcommon.entity.JsonResult;
import com.zming.cloudnacos6000.service.OpenFeginService;
import org.springframework.stereotype.Component;

/*
* 当远程调用失败或出现超时时间容错处理逻辑
* */
@Component  // 注入到容器中
public class OpenFeginServiceImpl implements OpenFeginService {
    @Override
    public JsonResult<String> getResult(Long id) {
        return new JsonResult<String>(400, false, "服务降级返回");
    }

    @Override
    public String getServerTime() {
        return null;
    }
}
