package hry.app.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.app.jwt.TokenUtil;
import hry.app.lend.model.LendRedis;
import hry.bean.ApiJsonResult;
import hry.lend.constant.ExchangeLendKey;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.Entrust;
import hry.manage.remote.model.User;
import hry.manage.remote.model.base.FrontPage;
import hry.redis.common.dao.RedisUtil;
import hry.trade.redis.model.EntrustByUser;
import hry.trade.redis.model.EntrustTrade;
import hry.util.SortListUtil;
import hry.util.common.SpringUtil;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/entrust")
@Api(value = "个人中心 --> 我的委托", description = "个人中心 --> 我的委托", tags = "个人中心 --> 我的委托")
public class EntrustController {

    /**
     * 注册类型属性编辑器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {

        // 系统注入的只能是基本类型，如int，char，String

        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new DateTimePropertyEditorSupport());

        /**
         * 防止XSS攻击，并且带去左右空格功能
         */
        binder.registerCustomEditor(String.class, new StringPropertyEditorSupport(true, false));
    }

    @Resource
    private RemoteManageService remoteManageService;

    /**
     * 查询交易记录
     *
     * @return
     */
    @ApiOperation(value = "个人中心我的委托查询", httpMethod = "POST", notes = " 个人中心我的委托查询")
    @RequestMapping("/user/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public FrontPage list(
              @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
              @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0", required = true) @RequestParam("offset") String offset,
              @ApiParam(name = "querypath", value = "是否个人中心查询，是:center，否:空字符串", required = true) @RequestParam("querypath") String querypath,
              @ApiParam(name = "typeone", value = "交易类型,0全部，1买，2卖", required = true) @RequestParam("typeone") String typeone,
              @ApiParam(name = "type", value = "当前委托：current；历史委托：history", required = true) @RequestParam("type") String type,
              @ApiParam(name = "coinCode", value = "币种，如果不是个人中心查询，加上coinCode查询", required = false) @RequestParam(value = "coinCode", required = false) String coinCode,
              @ApiParam(name = "startTime", value = "委托开始时间", required = false) @RequestParam(value = "startTime", required = false) String startTime,
              @ApiParam(name = "endTime", value = "委托结束时间", required = false) @RequestParam(value = "endTime", required = false) String endTime,
              HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        Map<String, String> params = new HashMap<>();
        params.put("limit", limit);
        params.put("offset", offset);
        params.put("querypath", querypath);
        params.put("typeone", typeone);
        params.put("type", type);
        params.put("coinCode", coinCode);
        if (!StringUtils.isEmpty(startTime)) {
            params.put("startTime", startTime);
        }
        if (!StringUtils.isEmpty(endTime)) {
            params.put("endTime", endTime);
        }

        // 全部
        if ("0".equals(typeone)) {
            params.put("typeone", null);
        }
        params.put("customerId", user.getCustomerId().toString());
        if (!"center".equals(querypath)) {//
            if (StringUtils.isEmpty(coinCode) || !coinCode.contains("_")) {
                return null;
            }
            if (!StringUtils.isEmpty(coinCode)) {
                String[] split = coinCode.split("_");
                params.put("coinCode", split[0]);
                params.put("fixPriceCoinCode", split[1]);
            }
        }

        FrontPage findTrades = remoteManageService.findEntrust(params);
        List<Entrust> list = findTrades.getRows();
        for (int i = 0; i < list.size(); i++) {
            Entrust entrust = list.get(i);
            entrust.setCoin(list.get(i).getCoin());
            entrust.setEntrustTime_long(entrust.getEntrustTime().getTime());
            entrust.setCoinCode(list.get(i).getCoinCode() + "-" + list.get(i).getFixPriceCoinCode());
        }
        return findTrades;
    }

    /**
     * 交易大厅-查询委托交易
     *
     * @return
     */
    @ApiOperation(value = "交易大厅-查询委托交易", httpMethod = "POST", notes = "交易大厅-查询委托交易")
    @RequestMapping("/user/rlist")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult<List<EntrustTrade>> rlist(
            @ApiParam(name = "type", value = "当前委托：current，历史委托：空", required = true) @RequestParam("type") String type,
            @ApiParam(name = "coinCode", value = "币种类型", required = true) @RequestParam("coinCode") String coinCode,
            @ApiParam(name = "isType", value = "0普通 1杠杆", required = false) @RequestParam(value = "isType", required = false, defaultValue = "0") String isType,
            HttpServletRequest request) {
        ApiJsonResult<List<EntrustTrade>> jsonResult = new ApiJsonResult<List<EntrustTrade>>();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            if (isType.equals("1")) {
                try (Jedis jedis = LendRedis.JEDIS_POOL.getResource()) {
                    String hallEntrustKey = ExchangeLendKey.getHallEntrustKey(coinCode);
                    String buyKey;
                    String sellKey;
                    if ("current".equals(type)) {
                        buyKey = user.getCustomerId() + "=current=buy";
                        sellKey = user.getCustomerId() + "=current=sell";
                    } else {
                        buyKey = user.getCustomerId() + "=history=buy";
                        sellKey = user.getCustomerId() + "=history=sell";
                    }
                    String buyStr = jedis.hget(hallEntrustKey, buyKey);
                    String sellStr = jedis.hget(hallEntrustKey, sellKey);
                    List<EntrustTrade> tradeList = new ArrayList<>();
                    if(!StringUtils.isEmpty(buyStr)){
                        JSONObject object = JSON.parseObject(buyStr);
                        for (Object value : object.values()) {
                            EntrustTrade entrustTrade = JSON.parseObject(value.toString(), EntrustTrade.class);
                            if(entrustTrade.getEntrustWay()==2){
                                entrustTrade.setSurplusEntrustCount(entrustTrade.getTransactionSum());
                                entrustTrade.setEntrustCount(entrustTrade.getEntrustSum());
                            }
                            tradeList.add(entrustTrade);
                        }
                    }
                    if(!StringUtils.isEmpty(sellStr)){
                        JSONObject object = JSON.parseObject(sellStr);
                        for (Object value : object.values()) {
                            EntrustTrade entrustTrade = JSON.parseObject(value.toString(), EntrustTrade.class);
                            tradeList.add(entrustTrade);
                        }
                    }
                    SortListUtil<EntrustTrade> sort = new SortListUtil<EntrustTrade>();
                    sort.SortStringDate(tradeList,"getEntrustTime", "desc");
                    jsonResult.setSuccess(true);
                    jsonResult.setObj(tradeList);
                    return jsonResult;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                RedisUtil<EntrustByUser> redisUtil = new RedisUtil<EntrustByUser>(EntrustByUser.class);
                EntrustByUser entrustByUser = redisUtil.get(user.getCustomerId().toString());
                if (entrustByUser != null) {
                    if ("current".equals(type)) {
                        Map<String, List<EntrustTrade>> entrustingmap = entrustByUser.getEntrustingmap();
                        if (entrustingmap != null) {
                            List<EntrustTrade> list = entrustingmap.get(coinCode);
                            SortListUtil<EntrustTrade> sort = new SortListUtil<EntrustTrade>();
                            if (list != null) {
                                for (EntrustTrade et : list) {
                                    et.setEntrustTime_long(et.getEntrustTime().getTime());
                                }
                                sort.SortStringDate(list, "getEntrustTime", "desc");
                            }
                            jsonResult.setSuccess(true);
                            jsonResult.setObj(list);
                            return jsonResult;
                        }
                    } else {
                        Map<String, List<EntrustTrade>> entrustedmap = entrustByUser.getEntrustedmap();
                        if (entrustedmap != null) {
                            List<EntrustTrade> list = entrustedmap.get(coinCode);
                            SortListUtil<EntrustTrade> sort = new SortListUtil<EntrustTrade>();
                            if (list != null) {
                                for (EntrustTrade et : list) {
                                    et.setEntrustTime_long(et.getEntrustTime().getTime());
                                }
                                sort.SortStringDate(list, "getEntrustTime", "desc");
                            }
                            jsonResult.setSuccess(true);
                            jsonResult.setObj(list);
                            return jsonResult;
                        }
                    }
                }
            }
        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("before_login"));
            return jsonResult;
        }
        jsonResult.setSuccess(false);
        return jsonResult;
    }
}
