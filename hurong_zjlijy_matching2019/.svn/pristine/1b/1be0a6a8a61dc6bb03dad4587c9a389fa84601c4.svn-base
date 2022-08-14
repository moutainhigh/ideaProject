package hry.cm.remote;

import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.bean.ObjectUtil;
import hry.cm.customerminer.model.CmCustomerMiner;
import hry.cm.customerminer.model.CmCustomerMinerProfit;
import hry.cm.customerminer.service.CmCustomerMinerProfitService;
import hry.cm.customerminer.service.CmCustomerMinerService;
import hry.cm.deal.CmDealUtil;
import hry.cm.dto.Accountadd;
import hry.cm.dto.CmAccountRedis;
import hry.cm.enums.CmAccountRemarkEnum;
import hry.cm.remote.model.CmCustomerMinerRemote;
import hry.mq.producer.DealMsgUtil;
import hry.redis.common.utils.RedisService;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.utils.BaseConfUtil;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RemoteCmCustomerMinerServiceImpl implements RemoteCmCustomerMinerService {
	
	@Resource
	private CmCustomerMinerService cmCustomerMinerservice;
	@Resource
	private RedisService redisService;
	@Resource
	private CmCustomerMinerProfitService cmCustomerMinerProfitService;

	@Override
	public FrontPage findCustomerMinerlist(Map<String, String> params) {
		// TODO Auto-generated method stub
		Long customerId = Long.valueOf(params.get("customerId"));//用户id
		Page page = PageFactory.getPage(params);
		QueryFilter filter = new QueryFilter(CmCustomerMiner.class);
		filter.addFilter("customerId=", customerId);
		if(!StringUtils.isEmpty(params.get("status"))){
			filter.addFilter("status=", Integer.valueOf(params.get("status")));
		}
		List<CmCustomerMiner> list = cmCustomerMinerservice.find(filter);
		List<CmCustomerMinerRemote> beanList = ObjectUtil.beanList(list, CmCustomerMinerRemote.class);
		return new FrontPage(beanList, page.getTotal(), page.getPages(), page.getPageSize());
	}

	@Override
	public List<CmCustomerMinerRemote> findMinerOutputList(Map<String, String> params) {
		Long customerId = Long.valueOf(params.get("customerId"));
		// 获取平台币
		String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");
		QueryFilter filter = new QueryFilter(CmCustomerMiner.class);
		filter.addFilter("customerId=",customerId);
		filter.addFilter("minerProfitType=", 2);
		filter.addFilter("profitCoinCode=",platCoin);
		filter.addFilter("profit2 > ", 0);
		List<CmCustomerMiner> minerList = cmCustomerMinerservice.find(filter);
		List<CmCustomerMinerRemote> beanList = ObjectUtil.beanList(minerList,CmCustomerMinerRemote.class);
		return beanList;
	}

	@Override
	public JsonResult giveMinerOutput(Map<String, String> params) {
		// 用户矿机id
		Long customerMinerId = Long.parseLong(params.get("customerMinerId"));
		CmCustomerMiner cmCustomerMiner = cmCustomerMinerservice.get(customerMinerId);
		// redis异步锁
		Boolean lock = redisService.lock("CM:CUSTOMERMINER:"+customerMinerId);
		if (lock) {
			// 更新用户矿机表
			BigDecimal profit1 = cmCustomerMiner.getProfit1().add(cmCustomerMiner.getProfit2());
			cmCustomerMinerservice.updateMinerProfit(customerMinerId,profit1,null, cmCustomerMiner.getProfit3());
			/** 查询用户平台币账户信息 */
			CmAccountRedis cmAccountRedis = CmDealUtil.getCmAccount(cmCustomerMiner.getCustomerId(), cmCustomerMiner.getProfitCoinCode());
			List<Accountadd> accountaddList = new ArrayList<>();
			Accountadd accountadd = new Accountadd(cmCustomerMiner.getCustomerId(), cmCustomerMiner.getProfitCoinCode(), cmAccountRedis.getId(),
					cmCustomerMiner.getProfit2(), 1, CmAccountRemarkEnum.TYPE5.getIndex(),
					cmCustomerMiner.getTransactionNum());
			accountaddList.add(accountadd);
			DealMsgUtil.sendAccountaddList(accountaddList);
			redisService.unLock("CM:CUSTOMERMINER:"+customerMinerId);
		}

		// 更新产出记录
		QueryFilter filter = new QueryFilter(CmCustomerMinerProfit.class);
		filter.addFilter("customerMinerId=",customerMinerId);
		List<CmCustomerMinerProfit> profitList = cmCustomerMinerProfitService.find(filter);
		for (CmCustomerMinerProfit cmCustomerMinerProfit : profitList) {
			cmCustomerMinerProfit.setStatus(2);
			cmCustomerMinerProfitService.update(cmCustomerMinerProfit);
		}
		return new JsonResult(true).setMsg("领取成功");
	}
}
