package hry.cm.remote;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import hry.util.decimal.DecimalUtil;
import hry.cmson.account.model.CmSonAccount;
import hry.cmson.account.model.CmSonAccountRecord;
import hry.cmson.account.service.CmSonAccountRecordService;
import hry.cmson.account.service.CmSonAccountService;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.util.StringUtil;

import hry.bean.ApiJsonResult;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.bean.ObjectUtil;
import hry.cm.account.model.CmAccount;
import hry.cm.account.model.CmAccountRecord;
import hry.cm.account.service.CmAccountRecordService;
import hry.cm.account.service.CmAccountService;
import hry.cm.account.service.ContractMainAccountRecordService;
import hry.cm.constant.ExchangeDataCacheRedis;
import hry.cm.deal.CmCustomerUtil;
import hry.cm.deal.CmDealUtil;
import hry.cm.dto.CmAccountRedis;
import hry.cm.remote.model.CmAccountRecordRemote;
import hry.cm.util.AccountDeEnUtil;
import hry.cm.util.CmRedis;
import hry.core.constant.CodeConstant;
import hry.front.redis.model.UserRedis;
import hry.manage.remote.model.FeixiaohaoPriceVo;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import hry.utils.BaseConfUtil;
import hry.utils.CmConfigUtil;
import hry.utils.UserRedisUtils;
import redis.clients.jedis.Jedis;

public class RemoteCmAccountServiceImpl implements RemoteCmAccountService {
	
	@Resource
    public CmAccountService cmAccountService;

	@Resource
    public CmSonAccountService cmSonAccountService;

    @Resource
    public CmAccountRecordService cmAccountRecordService;
    @Resource
    public CmSonAccountRecordService cmSonAccountRecordService;

    @Resource
    public ContractMainAccountRecordService contractMainAccountRecordService;


    @Override
    public synchronized String[] accountSubTomain(Long customerId, Long mainAccountId, String coinCode, BigDecimal count) {
        RedisService redisService = SpringUtil.getBean("redisService");
        //币种保留位数
        Integer retentionNumber = Integer.valueOf(redisService.get("retentionNumber:"+ coinCode));
        String[] rt = new String[2];
        CmAccountRedis tcbyAccountRedis = CmDealUtil.getCmAccount( customerId,  coinCode);
        Long binaryAccountId = tcbyAccountRedis.getId();
        //最大转币数
        BigDecimal maxWithdrawal = CmCustomerUtil.getMaxWithdrawal(customerId,coinCode);
        if (count.compareTo(new BigDecimal("0")) > 0 && maxWithdrawal.compareTo(count) >= 0) {
            contractMainAccountRecordService.contractTomain(customerId, mainAccountId, binaryAccountId, coinCode, count);
            rt[0] = CodeConstant.CODE_SUCCESS;
            rt[1] = "转账成功";
            return rt;
        } else {
            rt[0] = CodeConstant.CODE_FAILED;
            rt[1] = "余额不足,最大能提"+ DecimalUtil.getPrettyNumber(maxWithdrawal.setScale(retentionNumber,BigDecimal.ROUND_DOWN).toString());
            return rt;
        }
    }

    //处理小数位有效位数
    public BigDecimal getPrettyNumber(String dec){
        BigDecimal decimal = new BigDecimal(BigDecimal.valueOf(Double.parseDouble(dec)).stripTrailingZeros().toPlainString());
        return decimal;
    }

    @Override
    public String[] accountMainToSub(Long customerId, Long mainAccountId, String coinCode, BigDecimal count) {
        //余额判断在front判断，这里获取不到主账户的余额
        String[] rt = new String[2];
        CmAccountRedis contractAccountRedis = CmDealUtil.getCmAccount( customerId,  coinCode);
        Long contractAccountId = contractAccountRedis.getId();
        contractMainAccountRecordService.mainTocontract(customerId, mainAccountId, contractAccountId, coinCode, count);
        rt[0] = CodeConstant.CODE_SUCCESS;
        rt[1] = "转账成功";
        return rt;
    }
    
    @Override
    public synchronized String[] beforaccount(Long customerId, String coinCode) {
        //余额判断在front判断，这里获取不到主账户的余额
        String[] rt = new String[2];
        CmAccountRedis contractAccountRedis = CmDealUtil.getCmAccount( customerId,  coinCode);

        rt[0] = CodeConstant.CODE_SUCCESS;
        return rt;
    }

    @Override
    public Boolean initAccount(Long customerId) {
        //出数据库查询，然后对照redis如果redis有账户户缓存，就不要再缓存，如果没有的就加上去
        // 查询该用户的二元期权账户数据
        QueryFilter accountQf = new QueryFilter(CmAccount.class);
        accountQf.addFilter("customerId=", customerId);
        List<CmAccount> accountList = cmAccountService.find(accountQf);
        if (accountList != null && accountList.size() > 0) {
            // 循环判断该账户在redis中是否存在，不存在添加，存在跳过
            try (Jedis jedis = CmRedis.JEDIS_POOL.getResource()){
                for (CmAccount account : accountList) {
                    String hget1 = jedis.hget(ExchangeDataCacheRedis.getAccountKey(account.getCoinCode()), account.getCustomerId().toString());
                    if (StringUtils.isEmpty(hget1)) {
                        CmAccountRedis coinaccount = new CmAccountRedis();
                        coinaccount.setCoinCode(account.getCoinCode());
                        coinaccount.setColdMoney(account.getColdMoney());
                        coinaccount.setCustomerId(account.getCustomerId());
                        coinaccount.setHotMoney(account.getHotMoney());
                        coinaccount.setId(account.getId());
                        jedis.hset(ExchangeDataCacheRedis.getAccountKey(account.getCoinCode()), account.getCustomerId().toString(), AccountDeEnUtil.encode(JSON.toJSONString(coinaccount)));
                    }
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
    
	@Override
	public JsonResult myAccount(Map<String, String> params) {
		// TODO Auto-generated method stub
		JsonResult jsonResult = new JsonResult();

		Long customerId = Long.valueOf(params.get("customerId"));
		/** 查询平台币Code */
		String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");

		/** 查询用户平台币账户信息 */
		CmAccountRedis CmAccountRedis = CmDealUtil.getCmAccount(customerId, platCoin);
		
		return jsonResult.setSuccess(true).setObj(CmAccountRedis);
	}

	@Override
	public FrontPage findMyAccountRecordList(Map<String, String> params) {
		// TODO Auto-generated method stub
		Long customerId = Long.valueOf(params.get("customerId"));
		String remarkkey = params.get("remarkkey");//交易类型
		String dateStart = params.get("dateStart");
		String dateEnd = params.get("dateEnd");
		String coinCode = params.get("coinCode");
		
		Page page = PageFactory.getPage(params);
		QueryFilter filterCmAccountRecord = new QueryFilter(CmAccountRecord.class);
		filterCmAccountRecord.addFilter("customerId=", customerId);
		if (!StringUtils.isEmpty(remarkkey)) {
			filterCmAccountRecord.addFilter("remarkkey=", Integer.valueOf(remarkkey));
		}
		if (!StringUtils.isEmpty(coinCode)) {
			filterCmAccountRecord.addFilter("coinCode=", coinCode);
		}
		if (!StringUtils.isEmpty(dateStart)) {
			filterCmAccountRecord.addFilter("created>=", dateStart);
		}
		if (!StringUtils.isEmpty(dateEnd)) {
			filterCmAccountRecord.addFilter("created<=", dateEnd);
		}
		filterCmAccountRecord.setOrderby("created desc");
		List<CmAccountRecord> list = cmAccountRecordService.find(filterCmAccountRecord);
		List<CmAccountRecordRemote> beanList = ObjectUtil.beanList(list, CmAccountRecordRemote.class);
		return new FrontPage(beanList, page.getTotal(), page.getPages(), page.getPageSize());
	}

	@Override
	public JsonResult mainAccount(Map<String, String> params) {
		// TODO Auto-generated method stub
		JsonResult jsonResult = new JsonResult();

		Long customerId = Long.valueOf(params.get("customerId"));
		/** 查询平台币Code */
		String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");

		/** 查询用户平台币主账户信息 */
		RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
		UserRedis userRedis = redisUtil.get(customerId.toString());
		// 获得币账户
		ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(platCoin).toString(),
				platCoin);
		
		return jsonResult.setSuccess(true).setObj(exaccount);
	}

	@Override
	public List<CmAccountRedis> getAccoutList(Long customerId) {
		// TODO Auto-generated method stub
		try (Jedis jedis = CmRedis.JEDIS_POOL.getResource()) {
            Set<String> coinKeys = CmConfigUtil.getAllConskeysList();
            QueryFilter qf = new QueryFilter(CmAccount.class);
            qf.addFilter("customerId=", customerId);
            List<CmAccount> list = cmAccountService.find(qf);
            List<CmAccountRedis> listaccount = new ArrayList<CmAccountRedis>();
            for (CmAccount c : list) {
                coinKeys.remove(c.getCoinCode());
                CmAccountRedis account = new CmAccountRedis();
                account.setId(c.getId());
                account.setCoinCode(c.getCoinCode());
                account.setCustomerId(customerId);
                account.setHotMoney(c.getHotMoney());
                account.setColdMoney(c.getColdMoney());

                //折算成人民币BTC_CNY,易租云接口
                String price = jedis.hget("hry:coinPrice", c.getCoinCode());
                if (StringUtil.isNotEmpty(price)) {
                    account.setValuation((account.getHotMoney().add(account.getColdMoney())).multiply(new BigDecimal(price)));
                }
                listaccount.add(account);
            }
            //没有实际发生过业务的，是没有账户的
            Iterator<String> coinKeysIter = coinKeys.iterator();
            while (coinKeysIter.hasNext()) {
                String coinCode = coinKeysIter.next();
                CmAccountRedis account = new CmAccountRedis();
                account.setCoinCode(coinCode);
                account.setCustomerId(customerId);
                account.setHotMoney(BigDecimal.ZERO);
                account.setColdMoney(BigDecimal.ZERO);
                listaccount.add(account);
            }
            return listaccount;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //总资产，循环把估值相加就是
        return null;
	}
	
    @Override
    public BigDecimal getHotMoneyByCoin (String coinCode, Long customerId) {
        CmAccountRedis cmAccountRedis = CmDealUtil.getCmAccount( customerId,  coinCode);
        if(cmAccountRedis !=null){
            return cmAccountRedis.getHotMoney();
        }
        return BigDecimal.ZERO;
    }

	@Override
	public FrontPage getAccountRecord(Map<String, String> params) {
		// TODO Auto-generated method stub
		return cmAccountRecordService.getAccountRecord(params);
	}

	@Override
	public ApiJsonResult getCoinRate(String coinCode) {
		// TODO Auto-generated method stub
		// 获取 平台币
        String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");
        // 获取 平台币市价(RMB)
        String platformMarkPriceStr = BaseConfUtil.getConfigSingle("platformMarkPrice", "configCache:basefinanceConfig");
        BigDecimal platformMarkPrice = new BigDecimal(platformMarkPriceStr);
        BigDecimal codePrice = BigDecimal.ZERO;
        // 如果是平台币，取平台币市价(RMB)
        if (coinCode.equals(platCoin)) {
            codePrice = platformMarkPrice;
        } else {
            // 如果是其它币，取非小号
            RedisService redisService = SpringUtil.getBean("redisService");
            String result = redisService.get("cn:newFeixiaohaoPrice");
            if (hry.util.StringUtil.isNull(result)) {
                List<FeixiaohaoPriceVo> list = JSON.parseArray(result, FeixiaohaoPriceVo.class);
                for (FeixiaohaoPriceVo feixiaohaoPriceVo : list) {
                    String name = feixiaohaoPriceVo.getName();
                    // 如果是自定义币，取非小号的Price为空会报错，做个判断
                    if (name.equals(coinCode) && feixiaohaoPriceVo.getPrice() != null) {
                        codePrice = new BigDecimal(feixiaohaoPriceVo.getPrice());
                        return new ApiJsonResult().setSuccess(true).setObj(codePrice);
                    }
                }
            } else {
                System.out.println("未查询到redis中" + coinCode + "行情信息");
                return new ApiJsonResult().setSuccess(true).setObj(codePrice);
            }

        }
        return new ApiJsonResult().setSuccess(true).setObj(codePrice);
	}

    @Override
    public JsonResult myCmSonAccount(Map<String, String> params) {
        // TODO Auto-generated method stub
        JsonResult jsonResult = new JsonResult();

        Long customerId = Long.valueOf(params.get("customerId"));
        /** 查询平台币Code */
        String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");

        /** 查询用户平台币账户信息 */
        hry.cmson.dto.CmAccountRedis CmAccountRedis = hry.cmson.deal.CmDealUtil.getCmAccount(customerId, platCoin);

        return jsonResult.setSuccess(true).setObj(CmAccountRedis);
    }

    @Override
    public FrontPage findMyCmSonAccountRecordList(Map<String, String> params) {
        // TODO Auto-generated method stub
        Long customerId = Long.valueOf(params.get("customerId"));
        String remarkkey = params.get("remarkkey");//交易类型
        String dateStart = params.get("dateStart");
        String dateEnd = params.get("dateEnd");
        String coinCode = params.get("coinCode");

        Page page = PageFactory.getPage(params);
        QueryFilter filterCmAccountRecord = new QueryFilter(CmSonAccountRecord.class);
        filterCmAccountRecord.addFilter("customerId=", customerId);
        if (!StringUtils.isEmpty(remarkkey)) {
            filterCmAccountRecord.addFilter("remarkkey=", Integer.valueOf(remarkkey));
        }
        if (!StringUtils.isEmpty(coinCode)) {
            filterCmAccountRecord.addFilter("coinCode=", coinCode);
        }
        if (!StringUtils.isEmpty(dateStart)) {
            filterCmAccountRecord.addFilter("created>=", dateStart);
        }
        if (!StringUtils.isEmpty(dateEnd)) {
            filterCmAccountRecord.addFilter("created<=", dateEnd);
        }
        filterCmAccountRecord.setOrderby("created desc");
        List<CmSonAccountRecord> list = cmSonAccountRecordService.find(filterCmAccountRecord);
        List<CmAccountRecordRemote> beanList = ObjectUtil.beanList(list, CmAccountRecordRemote.class);
        return new FrontPage(beanList, page.getTotal(), page.getPages(), page.getPageSize());
    }

    @Override
    public List<hry.cmson.dto.CmAccountRedis> getCmSonAccoutList(Long customerId) {
        // TODO Auto-generated method stub
        try (Jedis jedis = hry.cmson.util.CmRedis.JEDIS_POOL.getResource()) {
            Set<String> coinKeys = CmConfigUtil.getAllConskeysList();
            QueryFilter qf = new QueryFilter(CmSonAccount.class);
            qf.addFilter("customerId=", customerId);
            List<CmSonAccount> list = cmSonAccountService.find(qf);
            List<hry.cmson.dto.CmAccountRedis> listaccount = new ArrayList<hry.cmson.dto.CmAccountRedis>();
            for (CmSonAccount c : list) {
                coinKeys.remove(c.getCoinCode());
                hry.cmson.dto.CmAccountRedis account = new hry.cmson.dto.CmAccountRedis();
                account.setId(c.getId());
                account.setCoinCode(c.getCoinCode());
                account.setCustomerId(customerId);
                account.setHotMoney(c.getHotMoney());
                account.setColdMoney(c.getColdMoney());

                //折算成人民币BTC_CNY,易租云接口
                String price = jedis.hget("hry:coinPrice", c.getCoinCode());
                if (StringUtil.isNotEmpty(price)) {
                    account.setValuation((account.getHotMoney().add(account.getColdMoney())).multiply(new BigDecimal(price)));
                }
                listaccount.add(account);
            }
            //没有实际发生过业务的，是没有账户的
            Iterator<String> coinKeysIter = coinKeys.iterator();
            while (coinKeysIter.hasNext()) {
                String coinCode = coinKeysIter.next();
                hry.cmson.dto.CmAccountRedis account = new hry.cmson.dto.CmAccountRedis();
                account.setCoinCode(coinCode);
                account.setCustomerId(customerId);
                account.setHotMoney(BigDecimal.ZERO);
                account.setColdMoney(BigDecimal.ZERO);
                listaccount.add(account);
            }
            return listaccount;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //总资产，循环把估值相加就是
        return null;
    }

    @Override
    public BigDecimal getCmSonHotMoneyByCoin (String coinCode, Long customerId) {
        hry.cmson.dto.CmAccountRedis cmAccountRedis = hry.cmson.deal.CmDealUtil.getCmAccount( customerId,  coinCode);
        if(cmAccountRedis !=null){
            return cmAccountRedis.getHotMoney();
        }
        return BigDecimal.ZERO;
    }

    @Override
    public hry.cmson.dto.CmAccountRedis cmSonAccount(Long customerId) {
        /** 查询平台币Code */
        String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");
        /** 查询用户平台币账户信息 */
        hry.cmson.dto.CmAccountRedis cmAccountRedis = hry.cmson.deal.CmDealUtil.getCmAccount(customerId, platCoin);
        return cmAccountRedis;
    }


}
