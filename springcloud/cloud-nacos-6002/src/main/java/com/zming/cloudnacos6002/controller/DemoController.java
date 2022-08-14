package com.zming.cloudnacos6002.controller;

import com.zming.cloudcommon.entity.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/zm")
public class DemoController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long,String> map = new HashMap();
    static {
        map.put(1L,"桌子");
        map.put(2L,"椅子");
        map.put(3L,"凳子");
    }
    @RequestMapping(value = "/getResult/{id}")
    public JsonResult<String> getResult(@PathVariable Long id){
        JsonResult<String> result = new JsonResult<>(200L, true, "producer-"+serverPort+":"+map.get(id));
        return result;
    }

    @GetMapping(value = "/getServerTime")
    public String getServerTime() {
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (Exception e) {
        }
        return "------serverPort:"+serverPort;
    }

    @GetMapping(value = "/getTestA")
    public String getTest() {
        return "-------TestA:"+ serverPort;
    }
}
