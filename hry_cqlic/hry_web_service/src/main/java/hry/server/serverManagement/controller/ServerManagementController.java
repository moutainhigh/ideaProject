package hry.server.serverManagement.controller;

import hry.bean.JsonResult;
import hry.redis.common.utils.RedisService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/static/ServerManagement")
public class ServerManagementController {
	@Resource
	private RedisService redisService;

	/**
	 * 心跳机制
	 *
	 */
	@RequestMapping(value = "/heartbeatConnection", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult heartbeatConnection(HttpServletRequest request) {
			Map<String,Boolean> map=new HashMap<>();
			String config = redisService.get("configCache:all");
			if(config !=null){
				map.put("redis",true);
			}else{
				map.put("redis",false);
			}
			return new JsonResult().setSuccess(true).setObj(map);

	}

}
