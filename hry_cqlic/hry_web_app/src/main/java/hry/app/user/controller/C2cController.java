package hry.app.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.app.jwt.TokenUtil;
import hry.app.user.model.ExProduct;
import hry.bean.ApiJsonResult;
import hry.core.mvc.model.page.HttpServletRequestUtils;
import hry.front.redis.model.UserRedis;
import hry.manage.remote.RemoteAppTransactionManageService;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.AppConfig;
import hry.manage.remote.model.ExCointoMoney;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.User;
import hry.manage.remote.model.base.FrontPage;
import hry.manage.remote.model.c2c.C2cOrder;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.util.SortListUtil;
import hry.util.common.BaseConfUtil;
import hry.util.common.Constant;
import hry.util.common.SpringUtil;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.aspectj.apache.bcel.ExceptionConstants;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/c2c")
@Api(value = "C2C业务类(必须传token)", description = "C2C业务类(必须传token)", tags = "C2C业务类(必须传token)")
public class C2cController {

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
    private RedisService redisService;

    @Resource
    private RemoteManageService remoteManageService;

    @Resource
    private RemoteAppTransactionManageService remoteAppTransactionManageService;


    /**
     * C2C订单分页（个人中心）
     * @param request
     * @return
     */
    @ApiOperation(value = "C2C订单分页接口（个人中心）", httpMethod = "POST", response = FrontPage.class, notes = "C2C订单分页接口（个人中心）")
    @PostMapping("/user/c2clist")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public FrontPage c2clist(
            @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
            @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0",required = true) @RequestParam("offset") String offset,
            @ApiParam(name = "transactionType", value = "订单类型(0查全部,1买，2卖)",required = true) @RequestParam("transactionType") String transactionType,
            HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        if ("0".equals(transactionType)) {// 0查全部
            params.put("transactionType", null);
        }
        params.put("customerId", user.getCustomerId().toString());
        FrontPage findTrades = remoteManageService.c2clist(params);
        return findTrades;
    }


    /**
     * 用户C2C最近10条订单
     * @param request
     * @return
     */
    @ApiOperation(value = "用户C2C最近10条订单", httpMethod = "POST",  notes = "用户C2C最近10条订单")
    @PostMapping("/user/c2cLatelyOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
    })
    @ResponseBody
    public List<C2cOrder> c2cLatelyOrder(
            @ApiParam(name = "coinCode", value = "币种代码", example = "BTC,ETH,EOS",required = true) @RequestParam("coinCode") String coinCode,
            HttpServletRequest request) {
        List<C2cOrder> listOrder = null;
        User user = TokenUtil.getUser(request);
        if (user != null) {
            listOrder = remoteManageService.c2c10Order(user.getCustomerId(), coinCode);
        }
        return listOrder;
    }


    /**
     * 查看c2c订单详情
     * @param request
     * @param transactionNum
     * @return
     */
    @ApiOperation(value = "C2C交易页面商户买卖订单", httpMethod = "POST", response = ApiJsonResult.class, notes = "C2C交易页面商户买卖订单")
    @PostMapping("/getc2cTransaction")
    @ResponseBody
    public ApiJsonResult getc2cTransaction(HttpServletRequest request,
       @ApiParam(name = "transactionNum", value = "订单号", example = "BTC,ETH,EOS",required = true) @RequestParam String transactionNum) {

        ApiJsonResult apiJsonResult = new ApiJsonResult();
        String c2cOrderDetail = remoteManageService.getC2cOrderDetail(transactionNum);
        if(!StringUtils.isEmpty(c2cOrderDetail)){
            JSONObject obj = JSON.parseObject(c2cOrderDetail);
            apiJsonResult.setObj(obj);
            apiJsonResult.setSuccess(true);
            return apiJsonResult;
        }
        return apiJsonResult;
    }


    /**
     * c2c下单
     * @return
     */
    @ApiOperation(value = "c2c下单", httpMethod = "POST", response = ApiJsonResult.class, notes = "c2c下单")
    @PostMapping("/user/createTransaction")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult createTransaction(
            @ApiParam(name = "coinCode", value = "币种类型", example = "BTC,ETH,EOS",required = true) @RequestParam String coinCode,
            @ApiParam(name = "transactionType", value = "交易类型1买，2卖", example = "1,2",required = true) @RequestParam String transactionType,
            @ApiParam(name = "transactionCount", value = "下单数量",required = true) @RequestParam String transactionCount,
            @ApiParam(name = "transactionPrice", value = "交易价格", example = "BTC,ETH,EOS",required = true) @RequestParam String transactionPrice,
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            HttpServletRequest request) {
        String exCoinToMoneyStr = redisService.get("CointoMoney:"+langCode);
        ExCointoMoney exCointoMoney = JSONObject.parseObject(exCoinToMoneyStr,ExCointoMoney.class);

        ApiJsonResult apiJsonResult = new ApiJsonResult();

        if(new BigDecimal(transactionPrice).compareTo(new BigDecimal("0")) < 0 ){
             apiJsonResult.setMsg(SpringUtil.diff("entrust_price_not_lt_zero"));
             return apiJsonResult;
        }

        User user = TokenUtil.getUser(request);

        /**
         * 前台传来的价格做校验
         */
        BigDecimal nowPrice = remoteManageService.getC2cCoinPrice(coinCode,transactionType).divide(new BigDecimal(exCointoMoney.getRate()),3);
        if(new BigDecimal(transactionPrice).compareTo(nowPrice) < 0){
            apiJsonResult.setMsg(SpringUtil.diff("entrust_price_not_lt_zero"));
            return apiJsonResult;
        }

        if (transactionType != null && transactionType.equals("2")) {
            String[] checkLend = remoteManageService.checkLend(user.getCustomerId());
            if (checkLend[0].equals("0000")) {
                apiJsonResult.setMsg(SpringUtil.diff("goingPing"));
                return apiJsonResult;
            }

            // 判断当天卖出量不能超过10笔
            long count = remoteManageService.getTransactionNumOnTodayOfSell();
            if (count >= 10) {
                apiJsonResult.setMsg(SpringUtil.diff("c2c_dayOfsell"));
                return apiJsonResult;
            }
        }

        if(user!=null){
            //查询后台参数配置
            String config = redisService.get("configCache:all");
            JSONObject parseObject = JSONObject.parseObject(config);
            String isStop = redisService.get("deal:stop");
            if(org.apache.commons.lang3.StringUtils.isNotEmpty(isStop)){
                apiJsonResult.setMsg(SpringUtil.diff("tibishibai"));
                return apiJsonResult;
            }

            //判断是否实名
            if (user.getStates() != 2) {
                apiJsonResult.setMsg(SpringUtil.diff("qingxianshimingrenzheng"));
                return apiJsonResult;
            }

            C2cOrder c2cOrder = new C2cOrder();
            //交易币种
            c2cOrder.setCoinCode(coinCode);
            //交易单号
            c2cOrder.setTransactionNum(UUID.randomUUID().toString().replace("-", ""));
            //交易数量
            c2cOrder.setTransactionCount(new BigDecimal(transactionCount));
            //交易价格
            c2cOrder.setTransactionPrice(new BigDecimal(transactionPrice));
            //用户名
            c2cOrder.setUserName(user.getUsername());
            //交易类型1买，2卖
            c2cOrder.setTransactionType(Integer.valueOf(transactionType));
            //customerId
            c2cOrder.setCustomerId(user.getCustomerId());
            RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
            UserRedis userRedis = redisUtil.get(user.getCustomerId().toString());
            Long coinAccountId = userRedis.getDmAccountId(coinCode);
            //虚拟币账户id,要买的币，或者要卖的币
            c2cOrder.setAccountId(coinAccountId);


            RemoteResult remoteResult = null;
            try {
                remoteResult = remoteManageService.createC2cOrder(c2cOrder);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(remoteResult!=null){
                if(remoteResult.getSuccess()){
                    apiJsonResult.setSuccess(true);
                    apiJsonResult.setMsg(SpringUtil.diff("success"));
                    apiJsonResult.setObj(remoteResult.getObj());
                    return apiJsonResult;
                }else{
                    apiJsonResult.setMsg(remoteResult.getMsg());
                    return apiJsonResult;
                }
            }else{
                apiJsonResult.setMsg("remote error");
                return apiJsonResult;
            }

        }else {
            apiJsonResult.setMsg(SpringUtil.diff("before_login"));
        }

        return apiJsonResult;
    }


    /**
     * C2C交易页面商户买卖订单
     * @return
     */
    @ApiOperation(value = "C2C交易页面商户买卖订单", httpMethod = "GET", response = C2cOrder.class, notes = "C2C交易页面商户买卖订单")
    @GetMapping("/businessmanOrder")
    @ResponseBody
    public Map<String,List<C2cOrder>> c2clatelyorder() {
        Map<String,List<C2cOrder>> map = new HashMap<String,List<C2cOrder>>();
        List<C2cOrder> buyList  = remoteManageService.c2cNewBuyOrder();
        map.put("buyList",buyList);
        List<C2cOrder> sellList = remoteManageService.c2cNewSellOrder();
        map.put("sellList",sellList);
        return map;
    }
 /**
     * C2C交易页面商户买卖订单
     * @return
     */
    @ApiOperation(value = "C2C交易页面汇率换算", httpMethod = "GET", response = C2cOrder.class, notes = "C2C交易页面汇率换算")
    @GetMapping("/c2cCoinToMoney")
    @ResponseBody
    public Map<String,List<C2cOrder>> c2cCoinToMoney() {
        Map<String,List<C2cOrder>> map = new HashMap<String,List<C2cOrder>>();
        List<C2cOrder> buyList  = remoteManageService.c2cNewBuyOrder();
        map.put("buyList",buyList);
        List<C2cOrder> sellList = remoteManageService.c2cNewSellOrder();
        map.put("sellList",sellList);
        return map;
    }

    /**
     * C2C交易页面配置信息
     * @param coinCode
     * @return
     */
    @ApiOperation(value = "C2C交易页面配置信息", httpMethod = "GET", response = Map.class, notes = "C2C交易页面配置信息")
    @GetMapping("/c2cConfig")
    @ResponseBody
    public Map<String,Object> c2cinfo(
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            @ApiParam(name = "coinCode", value = "币种代码", example = "BTC,ETH,EOS",required = false) @RequestParam(value = "coinCode", required = false) String coinCode,
            HttpServletRequest request) {
        Map<String,Object> map = new HashMap<String,Object>();
        String exCoinToMoneyStr = redisService.get("CointoMoney:"+langCode);
        ExCointoMoney exCointoMoney = JSONObject.parseObject(exCoinToMoneyStr,ExCointoMoney.class);
        exCointoMoney.setCoinSymbol(HtmlUtils.htmlUnescape(exCointoMoney.getCoinSymbol()));
        map.put("exCointoMoney",exCointoMoney);

        //查询所有开通c2c的币
        String str = redisService.get("cn:c2clist");
        List<Map<String, Object>> coinInfoList = new ArrayList<>();
        if(!StringUtils.isEmpty(str)){
            List<String> list = JSON.parseArray(str, String.class);
            if (list != null && list.size() > 0) {
                list.sort(new Comparator<String>() {
                    @Override
                    public int compare (String o1, String o2) {
                        if (o1.compareToIgnoreCase(o2) < 0) {
                            return -1;
                        }
                        return 1;
                    }
                });
            }
            map.put("coinList", list);
            if (list != null && list.size() > 0) {
                if (StringUtils.isEmpty(coinCode)) {
                    coinCode = list.get(0);
                }
                for (String coin : list) {
                    Map<String, Object> coinMap = new HashMap<>();
                    ExProduct exProduct = JSONObject.parseObject(redisService.hget("ex:Product", coin), ExProduct.class);
                    if (exProduct != null) {
                        coinMap.put("coinCode", exProduct.getCoinCode());
                        coinMap.put("picImg", exProduct.getPicturePath());
                        coinInfoList.add(coinMap);
                    }
                }
            } else {
                return map;
            }
        }
        //当前激活的节点
        map.put("activeCoin",coinCode);
        map.put("coinInfoList", coinInfoList);

        //获得c2cCoin表配置信息
        String c2cCoinList = redisService.get("cn:c2cCoinList");
        if(!StringUtils.isEmpty(c2cCoinList)){
            JSONArray parseArray = JSON.parseArray(c2cCoinList);
            if(parseArray!=null){
                for(int i = 0 ; i < parseArray.size(); i++ ){
                    JSONObject jsonObject = parseArray.getJSONObject(i);
                    if(coinCode.equals(jsonObject.getString("coinCode"))){
                        map.put("c2cBuyPrice", jsonObject.getBigDecimal("buyPrice").divide(new BigDecimal(exCointoMoney.getRate()),3));
                        map.put("c2cSellPrice", jsonObject.getBigDecimal("sellPrice").divide(new BigDecimal(exCointoMoney.getRate()),3));
                    }
                }
            }
        }

        //是否开启语言切换
        List<AppConfig> isOpenLanguage = remoteAppTransactionManageService.getConfigInfo("isOpenLanguage");
        if(isOpenLanguage.size()==0){
            map.put("isOpenLanguage", "1");
        }else{
            String value = isOpenLanguage.get(0).getValue();
            map.put("isOpenLanguage", value);
        }
        Map<String,Object> root = BaseConfUtil.getConfigByKey(Constant.baseConfig, langCode);
        map.put("SEOTitle", "-"+root.get("SEOTitle").toString());
        return map;
    }


}
