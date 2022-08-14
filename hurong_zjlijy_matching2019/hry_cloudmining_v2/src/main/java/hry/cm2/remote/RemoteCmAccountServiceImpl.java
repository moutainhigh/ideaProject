package hry.cm2.remote;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.util.StringUtil;
import hry.bean.ApiJsonResult;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.bean.ObjectUtil;
import hry.cm2.account.model.Cm2Account;
import hry.cm2.account.model.Cm2AccountRecord;
import hry.cm2.account.service.Cm2AccountRecordService;
import hry.cm2.account.service.Cm2AccountService;
import hry.cm2.account.service.ContractMainAccountRecordService;
import hry.cm2.constant.ExchangeDataCacheRedis;
import hry.cm2.deal.CmCustomerUtil;
import hry.cm2.deal.CmDealUtil;
import hry.cm2.dto.CmAccountRedis;
import hry.cm2.remote.model.CmAccountRecordRemote;
import hry.cm2.util.AccountDeEnUtil;
import hry.cm2.util.CmRedis;
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
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

public class RemoteCmAccountServiceImpl implements RemoteCmAccountService {
	
	@Resource
    public Cm2AccountService cmAccountService;

    @Resource
    public Cm2AccountRecordService cmAccountRecordService;

    @Resource
    public ContractMainAccountRecordService contractMainAccountRecordService;


    @Override
    public synchronized String[] accountSubTomain(Long customerId, Long mainAccountId, String coinCode, BigDecimal count) {
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
            rt[1] = "余额不足,最大能提"+maxWithdrawal;
            return rt;
        }
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
        QueryFilter accountQf = new QueryFilter(Cm2Account.class);
        accountQf.addFilter("customerId=", customerId);
        List<Cm2Account> accountList = cmAccountService.find(accountQf);
        if (accountList != null && accountList.size() > 0) {
            // 循环判断该账户在redis中是否存在，不存在添加，存在跳过
            try (Jedis jedis = CmRedis.JEDIS_POOL.getResource()){
                for (Cm2Account account : accountList) {
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
		QueryFilter filterCmAccountRecord = new QueryFilter(Cm2AccountRecord.class);
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
		List<Cm2AccountRecord> list = cmAccountRecordService.find(filterCmAccountRecord);
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
            QueryFilter qf = new QueryFilter(Cm2Account.class);
            qf.addFilter("customerId=", customerId);
            List<Cm2Account> list = cmAccountService.find(qf);
            List<CmAccountRedis> listaccount = new ArrayList<CmAccountRedis>();
            for (Cm2Account c : list) {
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
    
}
