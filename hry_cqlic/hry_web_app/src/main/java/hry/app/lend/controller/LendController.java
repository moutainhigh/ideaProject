package hry.app.lend.controller;

import hry.app.lend.aspect.LoginValid;
import hry.app.lend.model.LendRedis;
import hry.app.lend.model.LendUser;
import hry.bean.JsonResult;
import hry.lend.constant.ExchangeLendKey;
import hry.lend.constant.LendConfig;
import hry.lend.remote.RemoteLendMoneyService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @Copyright (c)<b> HeC<br/>
 * @createTime 2018/10/25 9:52
 * @Description: 杠杆借款类
 */
@RestController
@RequestMapping("/user/lever-lend")
@Api(value = "杠杆相关接口", description = "杠杆核心功能", tags = "杠杆核心功能")
public class LendController {

    @Resource
    private RemoteLendMoneyService remoteLendMoneyService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌",required = true,dataType = "String",paramType = "header")
    })
    @RequiresAuthentication
    @RequestMapping(value = "/lendMoney", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "借款", httpMethod = "POST", response = JsonResult.class)
    @LoginValid
    public JsonResult lendMoney(HttpServletRequest request, @ApiParam(value = "交易对BTC_USDT", required = true) String coinCode,
                                @ApiParam(value = "借款币种BTC or USDT", required = true) String code,
                                @ApiParam(value = "借款数量", required = true) BigDecimal count,
                                @ApiParam(hidden = true) LendUser user) {
        if (StringUtils.isNotEmpty(coinCode) && StringUtils.isNotEmpty(code) &&
                null != count && count.compareTo(BigDecimal.ZERO) > 0) {
            String key = ExchangeLendKey.getLendMoneyKey(user.getCustomerId().toString(), coinCode);
            try(Jedis jedis = LendRedis.JEDIS_POOL.getResource()){
                String label = jedis.get(key);
                if(StringUtils.isNotEmpty(label)){
                    return new JsonResult("操作频繁，请稍后再试");
                }
            }catch(Exception e){
                e.printStackTrace();
            }

            JsonResult result = remoteLendMoneyService.lendMoney(coinCode, code, count, user.getCustomerId());
            if(result.getSuccess()){
                try(Jedis jedis = LendRedis.JEDIS_POOL.getResource()){
                    jedis.set(key,"1");
                    jedis.expire(key, LendConfig.LEND_MONEY_TIMEOUT);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            return result;
        } else {
            return new JsonResult("参数有误");
        }
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌",required = true,dataType = "String",paramType = "header")
    })
    @RequiresAuthentication
    @RequestMapping(value = "/repayMoney", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "还款", httpMethod = "POST", response = JsonResult.class)
    @LoginValid
    public JsonResult repayMoney(HttpServletRequest request,
                                 @ApiParam(value = "还款单号", required = true) String lendNum,
                                 @ApiParam(value = "部分还款时需要指定还款数量") BigDecimal count,
                                 @ApiParam(hidden = true) LendUser user) {
        if (StringUtils.isNotEmpty(lendNum)) {
            if (count == null) {
                return remoteLendMoneyService.repayMoney(lendNum, count, user.getCustomerId());
            } else if (count.compareTo(BigDecimal.ZERO) > 0) {
                return remoteLendMoneyService.repayMoney(lendNum, count, user.getCustomerId());
            }
        }
        return new JsonResult("参数有误");
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌",required = true,dataType = "String",paramType = "header")
    })
    @RequiresAuthentication
    @RequestMapping(value = "/inOrOutMoney", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "转入或转出", httpMethod = "POST", response = JsonResult.class)
    @LoginValid
    public JsonResult inOrOutMoney(HttpServletRequest request, @ApiParam(value = "交易对BTC_USDT", required = true) String coinCode,
                                   @ApiParam(value = "币种BTC or USDT", required = true) String code,
                                   @ApiParam(value = "数量", required = true) BigDecimal count,
                                   @ApiParam(value = "类型1转入=账户加 2转出=账户减", required = true) Integer type,
                                   @ApiParam(hidden = true) LendUser user) {
        if (StringUtils.isNotEmpty(coinCode) && StringUtils.isNotEmpty(code) &&
                null != count && count.compareTo(BigDecimal.ZERO) > 0 && null != type) {
            return remoteLendMoneyService.inOrOutMoney(user.getCustomerId(),coinCode,code,count,type);
        }
        return new JsonResult("参数有误");
    }

}
