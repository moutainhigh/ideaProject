package hry.app.lend.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import hry.app.lend.aspect.LoginValid;
import hry.app.lend.model.LendRedis;
import hry.app.lend.model.LendUser;
import hry.bean.JsonResult;
import hry.lend.constant.ExchangeLendKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 */
@RestController
@Api(value = "交易大厅", description = "其余交易大厅接口", tags = "其余交易大厅接口")
public class MarginViewController {

    /**
     * 是否同意杠杆协议
     */
    @RequestMapping(value = "/user/lend/checkConfirm", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @LoginValid
    @ResponseBody
    public Map<String,Object> checkConfirm(HttpServletRequest request, LendUser user,
                                   @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode){
        Map<String,Object> returnMap = new HashMap<>(2);
        if (null != user) {
            JedisPool jedisPool = (JedisPool) hry.util.sys.ContextUtil.getBean("jedisPool");
            try (Jedis jedis = jedisPool.getResource()) {
                String isOne = jedis.hget(ExchangeLendKey.FIRST_TIME, user.getCustomerId().toString());
                if (org.springframework.util.StringUtils.isEmpty(isOne)) {
                    returnMap.put("firstIn", true);
                    String arrays = jedis.get("configCache:baseConfig" + langCode);
                    if (!org.springframework.util.StringUtils.isEmpty(arrays)) {
                        JSONArray jsonArray = JSONArray.parseArray(arrays);
                        for (Object obj : jsonArray) {
                            String key = String.valueOf(JSON.parseObject(obj.toString()).get("configkey"));
                            if(key.equals("lend")){
                                returnMap.put("layoutHtml", JSON.parseObject(obj.toString()).get("value"));
                                break;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnMap;
    }

    /**
     * 同意杠杆协议
     */
    @RequestMapping(value = "/user/lend/confirm", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @LoginValid
    public JsonResult confirm(HttpServletRequest request, LendUser user){
        try(Jedis jedis = LendRedis.JEDIS_POOL.getResource()){
            jedis.hset(ExchangeLendKey.FIRST_TIME,user.getCustomerId().toString(),"1");
            return new JsonResult(true);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new JsonResult();
    }

}
