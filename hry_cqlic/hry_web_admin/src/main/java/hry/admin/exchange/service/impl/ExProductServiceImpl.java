/**
 * Copyright:
 * @author:      liushilei
 * @version:     V1.0
 * @Date:        2018-06-12 15:44:37
 */
package hry.admin.exchange.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.admin.c2c.model.C2cCoin;
import hry.admin.c2c.service.C2cCoinService;
import hry.admin.customer.model.AppAccount;
import hry.admin.customer.model.AppCustomer;
import hry.admin.customer.model.AppPersonInfo;
import hry.admin.customer.service.AppCustomerService;
import hry.admin.customer.service.AppPersonInfoService;
import hry.admin.exchange.ExchangeDataCache;
import hry.admin.exchange.model.ExCointoCoin;
import hry.admin.exchange.model.ExDigitalmoneyAccount;
import hry.admin.exchange.model.ExProduct;
import hry.admin.exchange.service.ExCointoCoinService;
import hry.admin.exchange.service.ExDigitalmoneyAccountService;
import hry.admin.exchange.service.ExProductService;

import hry.admin.licqb.platform.RulesConfig;
import hry.admin.web.model.AppConfig;
import hry.admin.web.service.AppConfigService;
import hry.bean.JsonResult;
import hry.core.constant.StringConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.core.thread.ThreadPool;
import hry.front.redis.model.UserRedis;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.Mapper;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.*;
import hry.util.idgenerate.IdGenerate;
import hry.util.properties.PropertiesUtils;
import hry.util.rsa.FXHParam;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p> ExProductService </p>
 * @author:         liushilei
 * @Date :          2018-06-12 15:44:37  
 */
@Service("exProductService")
public class ExProductServiceImpl extends BaseServiceImpl<ExProduct, Long> implements ExProductService{
    private final Logger log = Logger.getLogger(ExProductServiceImpl.class);

    private static Object openExAccount = new Object();

    public static String productKey = "HRY:EXCHANGE:PRODUCT";

	@Resource(name="exProductDao")
	@Override
	public void setDao(BaseDao<ExProduct, Long> dao) {
		super.dao = dao;
	}

	@Resource
    private AppCustomerService appCustomerService;

	@Resource
    private AppPersonInfoService appPersonInfoService;

	@Resource
    private AppConfigService appConfigService;

    @Resource
    private ExProductService exProductService;

    @Resource
    private ExCointoCoinService exCointoCoinService;

	@Resource
    private RedisService redisService;

	@Resource
    private C2cCoinService c2cCoinService;


	@Resource
    private ExDigitalmoneyAccountService exDigitalmoneyAccountService;


    /**
     * 拉取实时价格
     */
	@Override
	public void pullCoinPrice(){
	    /*List<ExProduct> list = this.find(new QueryFilter(ExProduct.class).addFilter("issueState=","1"));
        list.forEach(exProduct -> {
            new HryCurrentCoinPriceRunable(exProduct.getCoinCode()).run();
        });*/
        HuiLvUtil.getHuilvData();
        // 获取平台币
        String platCode = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "coinCode");
        // 获取usdt兑人名币价格
        String usdtPrice = redisService.hget("hry:coinPrice", "USDT");
        // 平台币兑USDT价格
        String issuePrice = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "issuePrice");
        // 平台币兑人名币价格
        if (!StringUtils.isEmpty(usdtPrice) && !StringUtils.isEmpty(issuePrice)) {
            BigDecimal cnyPlat = new BigDecimal(usdtPrice).multiply(new BigDecimal(issuePrice));
            redisService.hset("hry:coinPrice", platCode,cnyPlat.toString());
        }
	}
    @Override
    public void hrySaaSCoinInfo(){
        List<ExProduct> exProductList = this.find(new QueryFilter(ExProduct.class).addFilter("issueState=","1"));
        String coinCode="";
        for(int i=0;i<exProductList.size();i++ ){
            if(i == exProductList.size()-1){
                coinCode += exProductList.get(i).getCoinCode();
            }else{
                coinCode += exProductList.get(i).getCoinCode()+",";
            }
        }
        FXHParam fxhParam = new FXHParam();
        fxhParam.setCoinCode(coinCode);

        RedisService redisService = SpringUtil.getBean("redisService");
        JSONArray config = JSONObject.parseArray(redisService.get("configCache:realtimepriceinfoconfig"));

        config.forEach( app -> {
            AppConfig appConfig = JSONObject.parseObject(app.toString(),AppConfig.class);
            switch (appConfig.getConfigkey()){
                case "klinedataurl" : fxhParam.setPayUrl(appConfig.getValue()); break;
                case "businessNumber" : fxhParam.setCompanyCode(appConfig.getValue()); break;
                case "publickey" : fxhParam.setPublicKey(appConfig.getValue()); break;
                case "privatekey" : fxhParam.setPrivateKey(appConfig.getValue()); break;
            }
        });

        Map<String, Object> map = new HashMap<>(16);
        map.put("word", fxhParam.getCoinCode());

        try {
            String sign = RSAUtil.encryptByPrivateKey(JSONObject.toJSONString(map), fxhParam.getPrivateKey());
            Map<String, String> paramMap = new HashMap<>(16);
            paramMap.put("companyCode", fxhParam.getCompanyCode());
            paramMap.put("sign", sign);

            String returnMsg = hry.util.httpRequest.HttpConnectionUtil.doPostQuery(fxhParam.getPayUrl(), paramMap);
            if(StringUtils.isEmpty(returnMsg)){
                log.info("调用SaaS-->币种详情接口失败");
                System.out.println("调用SaaS-->币种详情接口失败");
            }else {
                JSONObject jsonObject = JSONObject.parseObject(returnMsg);
                if("8888".equals(jsonObject.getString("resultCode"))){
                    String data = RSAUtil.decryptByPublicKey(jsonObject.getString("data"), fxhParam.getPublicKey());
                    redisService.save("cn:hrySaaSCoinInfo",data);
                    //logger.info("调用SaaS-->币种详情接口成功");
                    //System.out.println("调用SaaS-->币种详情接口成功");
                }else {
                    log.info("调用SaaS-->币种详情接口失败，返回信息======"+jsonObject.getString("reason"));
                    System.out.println("调用SaaS-->币种详情接口失败，返回信息======"+jsonObject.getString("reason"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean findByCoinCode(String c) {
        QueryFilter filter = new QueryFilter(ExProduct.class);
        filter.addFilter("coinCode=", c);
        filter.addFilter("issueState!=", 3);
        // String sid = ContextUtil.getSaasId();
        String saasId = PropertiesUtils.APP.getProperty("app.saasId");
        filter.setSaasId(saasId);
        List<ExProduct> list = super.find(filter);
        if (list != null && list.size() > 0) {
            // logger.error("-------------------  " + list);
            return true;
        }
        return false;
    }



    @Override
    public ExProduct findByCoinCode(String c, String sassId) {
        QueryFilter filter = new QueryFilter(ExProduct.class);
        filter.addFilter("coinCode=", c);
        filter.addFilter("issueState=", 1);
        return this.get(filter);

    }

    @Override
    public ExProduct findByallCoinCode(String coinCode) {

        QueryFilter filter = new QueryFilter(ExProduct.class);
        filter.addFilter("coinCode=", coinCode);
        filter.addFilter("coinCode=", coinCode);
        List<ExProduct> list = this.find(filter);
        if (null != list && list.size() > 0) {
            for (ExProduct l : list) {
                if (l.getIssueState() == 1) {
                    return l;
                }
            }
            return list.get(list.size() - 1);
        } else {

            return null;
        }

    }

    @Override
    public Integer getkeepDecimalForRmb() {
        String keepDecimalForRmb=getFinanceByKey("keepDecimalForRmb");
        if(!StringUtils.isEmpty(keepDecimalForRmb)){
            return Integer.valueOf(keepDecimalForRmb);
        }
        return 2;
    }

    public String getFinanceByKey(String key) {
        String val="";
        String string=redisService.get(StringConstant.CONFIG_CACHE+":financeConfig");
        JSONArray obj=JSON.parseArray(string);
        for(Object o:obj){
            JSONObject oo=JSON.parseObject(o.toString());
            if(oo.getString("configkey").equals(key)){
                val=oo.getString("value");
            }
        }
        return val;
    }

    @Override
    public void initRedisCode() {
        QueryFilter queryFilter = new QueryFilter(ExProduct.class);
        queryFilter.addFilter("issueState=", 1);
        queryFilter.setOrderby("sort");
        List<ExProduct> list = super.find(queryFilter);
        ArrayList<String> codeList = new ArrayList<String>();
        for (ExProduct exProduct : list) {
            codeList.add(exProduct.getCoinCode());
        }

        // 缓存到所有的站点中productListKey值
        Map<String, String> mapLoadWeb = PropertiesUtils.getLoadWeb();
        for (String Website : mapLoadWeb.keySet()) {
            ExchangeDataCache.setStringData(Website + ":productList", JSON.toJSONString(codeList));
        }

        // 缓存到所有的站点中productinfoListall
        QueryFilter queryFilter1 = new QueryFilter(ExProduct.class);
        queryFilter1.setOrderby("sort");
        List<ExProduct> list1 = super.find(queryFilter);
        for (String Website : mapLoadWeb.keySet()) {
            ExchangeDataCache.setStringData(Website + ":productinfoListall", JSON.toJSONString(list1));
        }
    }


    @Override
    public JsonResult delishProduct(Long ids) {
	    JsonResult jsonResult = new JsonResult();
	    // 查询币种信息
        ExProduct exProduct = super.get(ids);
        if (exProduct != null) {
            // 获取该币种
            String coinCode = exProduct.getCoinCode();

            // 获取交易对数据
            QueryFilter coin2coin = new QueryFilter(ExCointoCoin.class);
            // 在回收站中的交易对过滤掉
            coin2coin.addFilter("state_NEQ", 2);
            List<ExCointoCoin> cointoCoinList = exCointoCoinService.find(coin2coin);
            if (cointoCoinList != null && cointoCoinList.size() > 0) {
                // 定价币
                int djb = 0;
                // 交易币
                int jyb = 0;
                for (ExCointoCoin coin : cointoCoinList) {
                    // 交易币
                    if (coinCode.equals(coin.getCoinCode())) {
                        jyb++;
                    }
                    if (coinCode.equals(coin.getFixPriceCoinCode())) {
                        djb++;
                    }
                }
                //（1）判断要下架币种，是否有在交易对中作为“交易币”的，如果有则不能下架。（条件1）
                //提示信息：因为XX币种在XX-XX交易对中作为交易币，所以不能执行下架操作。
                if (jyb > 0) {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg("因为" + coinCode + "币种在交易对中作为交易币，所以不能执行下架操作");
                    return jsonResult;
                }
                //（2）判断要下架币种，是否有在交易对中作为“定价币”的，如果有则不能下架。（条件2）
                //提示信息：因为XX币种在XX-XX交易对中作为定价币，所以不能执行下架操作。
                if (djb > 0) {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg("因为" + coinCode + "币种在交易对中作为定价币，所以不能执行下架操作");
                    return jsonResult;
                }
            }

            //（3）判断要下架币种，是否用户货币账户中可用、冻结均不为0，如果不为0则不能下架。（条件3）
            //提示信息：因为还有**个用户持有该币种，所以不能执行下架操作。
            // 查询所有用户
            QueryFilter person = new QueryFilter(AppPersonInfo.class);
            List<AppPersonInfo> personInfoList = appPersonInfoService.find(person);
            if (personInfoList != null && personInfoList.size() > 0) {
                int ishave = 0;
                for (AppPersonInfo app : personInfoList) {
                    RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
                    UserRedis userRedis = redisUtil.get(app.getCustomerId().toString());
                    if (userRedis != null) {
                        if (userRedis.getDmAccountId(coinCode) != null) {
                            ExDigitalmoneyAccountRedis exDigitalmoneyAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(), coinCode);
                            if (exDigitalmoneyAccount != null) {
                                BigDecimal hot = exDigitalmoneyAccount.getHotMoney();
                                BigDecimal cold = exDigitalmoneyAccount.getColdMoney();
                                if (hot.compareTo(BigDecimal.ZERO) == 1 || cold.compareTo(BigDecimal.ZERO) == 1) {
                                    ishave++;
                                }
                            }
                        }
                    }
                }
                if (ishave > 0) {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg("因为还有" + ishave + "个用户持有该币种，所以不能执行下架操作");
                    return jsonResult;
                }
            }

            //（4）判断要下架币种，是否作为C2C中的交易币种，如果有则不能下架
            //提示信息：因为XX币种在C2C交易区中作为交易币，所以不能执行下架操作。
            QueryFilter filter = new QueryFilter(C2cCoin.class);
            filter.addFilter("coinCode=",coinCode);
            filter.addFilter("isOpen=",1);
            List<C2cCoin> c2cList = c2cCoinService.find(filter);
            if (c2cList != null && c2cList.size() > 0) {
                int isc2c = 0;
                for (C2cCoin c2c : c2cList) {
                    if (coinCode.equals(c2c.getCoinCode())) {
                        isc2c++;
                    }
                }
                if (isc2c > 0) {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg("因为" + coinCode + "币种在C2C交易区中作为交易币，所以不能执行下架操作");
                    return jsonResult;
                }
            }


            // 修改币种信息
            exProduct.setIssueState(2);
            exProduct.setModified(new Date());
            super.update(exProduct);

            // 将用户的该币种信息更新为不可用状态
            delistForProduct(exProduct);
            // 更新缓存
            initRedisCode();
            this.updateProductToRedis("");
            jsonResult.setSuccess(true);
            return jsonResult;
        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg("未能获取到该币种信息");
            return jsonResult;
        }
    }

    public void delistForProduct(ExProduct exProduct) {
        ExDigitalmoneyAccountService exDigitalmoneyAccountService = (ExDigitalmoneyAccountService) ContextUtil.getBean("exDigitalmoneyAccountService");
        // 如果 是退市 将要将所有的对应的虚拟币的账号的状态标为不用状态
        QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
        filter.addFilter("coinCode=", exProduct.getCoinCode());
        filter.addFilter("status!=", 0);
        List<ExDigitalmoneyAccount> list2 = exDigitalmoneyAccountService.find(filter);
        for (ExDigitalmoneyAccount exDigitalmoneyAccount : list2) {
            exDigitalmoneyAccount.setStatus(0);
            exDigitalmoneyAccountService.update(exDigitalmoneyAccount);
        }
    }

    @Override
    public JsonResult setCoinStatus(Long id, Integer i) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(true);
        ExProduct product = super.get(id);
        if (product.getIssueState() != i) {
            product.setIssueState(i);
            super.update(product);
            jsonResult.setMsg("成功");
            return jsonResult;
        }
        jsonResult.setSuccess(false);
        jsonResult.setMsg("此产品已经是这个状态了");
        return jsonResult;
    }

    /**
     * 更新redis里的缓存 所传的coinCode 可以为 “” 也可以为具体的某个商品的 coinCode 然后返回coinCode
     * 否则返回null
     *
     * @param coinCode
     * @return
     */
    @Override
    public ExProduct updateProductToRedis(String coinCode) {
        ExProduct exProduct = null;

        Map<String, String> map = new HashMap<String, String>();
        List<ExProduct> list = this.findByIssueState(1, "");

        if (null != list && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ExProduct product = list.get(i);
                if (coinCode.equals(product.getCoinCode())) {
                    exProduct = product;
                }
                String objectToJson = Mapper.objectToJson(product);
                map.put(product.getCoinCode(), objectToJson);
            }
            redisService.saveMap(productKey, map);
        }

        return exProduct;

    }
    @Override
    public List<ExProduct> findByIssueState(Integer i, String saasId) {
        QueryFilter filter = new QueryFilter(ExProduct.class);
        filter.addFilter("issueState=", i);
        filter.setSaasId(saasId);
        List<ExProduct> list = this.find(filter);
        return list;
    }

	/**
	 * 发布一个产品同步给所有的用户
	 *
	 * 方法的返回值 "NULL" 说明这个所对应的product为空 "0_OK" 说明这个所对应的 发布成功 "3_OK" 说明这个所对应的 退市成功
	 *
	 * */
	@Override
	public JsonResult publishProduct(Long[] ids,String language) {

		for (int i = 0; i < ids.length; i++) {
			Long id = ids[i];
			ExProduct product = super.get(id);
			if (product != null) {
				/*if(product.getIssueState()==1){
					continue;
				}*/
				// 修改产品的状态
				product.setIssueState(1);
				super.update(product);
				product.setLanguage(language);
				// 同步给用户
                ThreadPool.exe(new PublishRunnable(product));

			}
		}

		JsonResult jsonResult = new JsonResult();
		jsonResult.setMsg("产品全部上线成功");
		jsonResult.setSuccess(true);
		return jsonResult;
	}

    @Override
    public void syncedUser(ExProduct exProduct) {

        String code = exProduct.getCoinCode();

        QueryFilter rFilter = new QueryFilter(AppCustomer.class);
        rFilter.setSaasId(ContextUtil.getSaasId());
        //rFilter.addFilter("isReal=", 1);
        List<AppCustomer> list = appCustomerService.find(rFilter);

        // 判断所有用户的虚拟账户是否有当前发布的虚拟币所对应的虚拟账户
        for (AppCustomer customer : list) {
            //查询是否已经存在币账户
            QueryFilter ff = new QueryFilter(ExDigitalmoneyAccount.class);
            ff.addFilter("customerId=", customer.getId());
            ff.addFilter("coinCode=", code);
            ExDigitalmoneyAccount old=exDigitalmoneyAccountService.get(ff);
            if(old!=null){
                log.info("已经存在：------"+customer.getId());
                continue;
            }
            // 没有币账户就开通
            ExDigitalmoneyAccount exDigitalmoneyAccount = this.pushExDigitalmoneyAccount(customer, exProduct);
            exDigitalmoneyAccountService.save(exDigitalmoneyAccount);
            log.info("虚拟币账户ID"+exDigitalmoneyAccount.getId());

            //查redis
            RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
            UserRedis userRedis = redisUtil.get(customer.getId().toString());
            if(userRedis!=null){
                Map<String, Long> dmAccountIdMap = userRedis.getDmAccountId();
                dmAccountIdMap.put(exDigitalmoneyAccount.getCoinCode(), exDigitalmoneyAccount.getId());
                redisUtil.put(userRedis, customer.getId().toString());
            }


            RedisUtil<ExDigitalmoneyAccountRedis>  a = new RedisUtil<ExDigitalmoneyAccountRedis>(ExDigitalmoneyAccountRedis.class);
            ExDigitalmoneyAccountRedis exDigitalmoneyAccountRedis = new ExDigitalmoneyAccountRedis();
            exDigitalmoneyAccountRedis.setId(exDigitalmoneyAccount.getId());
            exDigitalmoneyAccountRedis.setCustomerId(exDigitalmoneyAccount.getCustomerId());
            exDigitalmoneyAccountRedis.setHotMoney(exDigitalmoneyAccount.getHotMoney()==null ? new BigDecimal("0.00000000") :exDigitalmoneyAccount.getHotMoney());
            exDigitalmoneyAccountRedis.setColdMoney(exDigitalmoneyAccount.getColdMoney() ==null ? new BigDecimal("0.00000000") : exDigitalmoneyAccount.getColdMoney());
            exDigitalmoneyAccountRedis.setUserName(exDigitalmoneyAccount.getUserName());
            exDigitalmoneyAccountRedis.setCoinCode(exDigitalmoneyAccount.getCoinCode());
            a.put(exDigitalmoneyAccountRedis, exDigitalmoneyAccount.getId().toString());

        }



    }


    @Override
    public JsonResult openDmAccount(AppCustomer appCustomer, AppPersonInfo appPersonInfo, String website, String currencyType) {
        JsonResult jsonResult = new JsonResult();
        // 查出全部发行的产品例表
        List<ExProduct> listProducts = findByIssueState(Integer.valueOf(1));
        // 便利开通账户号
        log.info("==========【进入开通虚拟币账户】==========");
        //开通币账户同步锁
        synchronized (openExAccount) {
            for (ExProduct exProduct : listProducts) {
                QueryFilter filter = new QueryFilter(AppAccount.class);
                filter.addFilter("customerId=", appCustomer.getId());

                filter.addFilter("coinCode=", exProduct.getCoinCode());
                ExDigitalmoneyAccount _digitalmoneyAccount = exDigitalmoneyAccountService.get(filter);
                if (_digitalmoneyAccount == null) {
                    log.info("币账户币不存在-开始开通虚拟币账户" + appCustomer.getUsername() + "|" + exProduct.getCoinCode());
                    ExDigitalmoneyAccount digitalmoneyAccount = new ExDigitalmoneyAccount();
                    digitalmoneyAccount.setCurrencyType(currencyType);
                    digitalmoneyAccount.setWebsite(website);
                    digitalmoneyAccount.setSaasId(appCustomer.getSaasId());
                    digitalmoneyAccount.setAccountNum(IdGenerate.accountNum(appCustomer.getId().intValue(), exProduct.getCoinCode()));
                    digitalmoneyAccount.setCustomerId(appCustomer.getId());
                    digitalmoneyAccount.setUserName(appCustomer.getUsername());
                    digitalmoneyAccount.setCoinCode(exProduct.getCoinCode());
                    digitalmoneyAccount.setPublicKey("");
                    digitalmoneyAccount.setCoinName(exProduct.getName());
                    digitalmoneyAccount.setPrivateKey(UUIDUtil.getUUID());
                    digitalmoneyAccount.setStatus(Integer.valueOf(1));
                    // 保存
                    exDigitalmoneyAccountService.save(digitalmoneyAccount);

                    log.info("初始化虚拟币账户缓存---------->>>:"+digitalmoneyAccount.getId().toString());
                    RedisUtil<ExDigitalmoneyAccount> redisUtil = new RedisUtil<ExDigitalmoneyAccount>(ExDigitalmoneyAccount.class);
                    redisUtil.put(digitalmoneyAccount, digitalmoneyAccount.getId().toString());
                }else{
                    log.info("币账户已存在    用户名：" + appCustomer.getUsername()+"--币账户："+_digitalmoneyAccount.getAccountNum()+"-coinCode:"+_digitalmoneyAccount.getCoinCode());
                }
            }
        }

        return jsonResult;
    }

    @Override
    public List<ExProduct> findByIssueState(Integer i) {
        QueryFilter filter = new QueryFilter(ExProduct.class);
        filter.addFilter("issueState=", i);
        filter.setOrderby("isRecommend DESC");
        List<ExProduct> list = find(filter);
        return list;
    }

    // 传入一个用户 返回一个虚拟账户对象(瞬时态)
    public ExDigitalmoneyAccount pushExDigitalmoneyAccount(AppCustomer customer, ExProduct exProduct) {
        ExDigitalmoneyAccount exDigitalmoneyAccount = new ExDigitalmoneyAccount();
        exDigitalmoneyAccount.setCustomerId(customer.getId());
        exDigitalmoneyAccount.setUserName(customer.getUsername());


        exDigitalmoneyAccount.setAccountNum(IdGenerate.accountNum(customer.getId().intValue(), exProduct.getCoinCode()));
        exDigitalmoneyAccount.setCoinCode(exProduct.getCoinCode());
        exDigitalmoneyAccount.setWebsite("cn");
        exDigitalmoneyAccount.setCurrencyType("USD");
        exDigitalmoneyAccount.setCoinName(exProduct.getName());
        exDigitalmoneyAccount.setStatus(1);
        exDigitalmoneyAccount.setPrivateKey(UUIDUtil.getUUID());

        exDigitalmoneyAccount.setSaasId(customer.getSaasId());
        return exDigitalmoneyAccount;
    }

    @Override
    public void initToRedis(){
        findByIssueState(1).forEach(exProduct -> {
            redisService.hset("ex:Product",exProduct.getCoinCode(),JSONObject.toJSONString(exProduct));
        });
    }

}
