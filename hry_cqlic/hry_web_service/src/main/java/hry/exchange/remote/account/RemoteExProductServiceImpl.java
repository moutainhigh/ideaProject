/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年4月6日 下午4:21:15
 */
package hry.exchange.remote.account;

import hry.account.fund.model.AppAccount;
import hry.account.remote.RemoteAppAccountService;
import hry.bean.JsonResult;
import hry.customer.agents.model.AppAgentsCustromer;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.remote.RemoteAppAgentsService;
import hry.customer.user.model.AppCustomer;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.product.model.ExCointoCoin;
import hry.exchange.product.model.ExProduct;
import hry.exchange.product.service.ExCointoCoinService;
import hry.exchange.product.service.ExProductService;
import hry.redis.common.dao.RedisUtil;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.util.UUIDUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年4月6日 下午4:21:15
 */
public class RemoteExProductServiceImpl implements RemoteExProductService {
	
	private final Logger logger = Logger.getLogger(RemoteExProductServiceImpl.class);
	
	private static Object openExAccount = new Object();

	@Resource
	public ExProductService exPproductService;

	@Resource
	public ExDigitalmoneyAccountService exDigitalmoneyAccountService;

	@Resource
	RemoteAppAccountService remoteAppAccountService;
	@Resource
	ExCointoCoinService exCointoCoinService;
	@Override
	public List<ExProduct> findByIssueState(Integer i) {
		QueryFilter filter = new QueryFilter(ExProduct.class);
		filter.addFilter("issueState=", i);
		filter.setOrderby("isRecommend DESC");
		List<ExProduct> list = exPproductService.find(filter);
		return list;
	}

	@Override
	public List<ExProduct> findByIssueState(RemoteQueryFilter rfilter) {
		QueryFilter filter = rfilter.getQueryFilter();
		List<ExProduct> list = exPproductService.find(filter);
		return list;
	}

	@Override
	public boolean findByCode(String s) {
		boolean b = exPproductService.findByCoinCode(s);
		return b;
	}

	@Override
	public ExProduct findByCoinCode(String c, String sassId) {
		return exPproductService.findByCoinCode(c, sassId);
	}

	@Override
	public ExProduct findByCoinCode(RemoteQueryFilter rfilter) {
		QueryFilter filter = rfilter.getQueryFilter();
		ExProduct product = exPproductService.get(filter);
		return product;
	}

	/**
	 * 保存代理商
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @author: Liu Shilei
	 * @param: @param agentsCustromer
	 * @param: @return
	 * @return: boolean
	 * @Date : 2016年10月17日 下午6:54:10
	 * @throws:
	 */
	public boolean saveAppAgents(AppAgentsCustromer agentsCustromer) {
		try {
			RemoteAppAgentsService remoteAppAgentsService = (RemoteAppAgentsService) ContextUtil.getBean("remoteAppAgentsService");
			JsonResult result = remoteAppAgentsService.saveAgents(agentsCustromer);
			return true;
		} catch (Exception e) {
			return false;
		}
	}



	@Override
	public JsonResult openDmAccount(AppCustomer appCustomer, AppPersonInfo appPersonInfo, AppAgentsCustromer agentsCustromer, String website, String currencyType) {
		JsonResult jsonResult = new JsonResult();
		// 查出全部发行的产品例表
		List<ExProduct> listProducts = findByIssueState(Integer.valueOf(1));
		// 便利开通账户号
		logger.info("==========【进入开通虚拟币账户】==========");
		//开通币账户同步锁
		synchronized (openExAccount) {
			for (ExProduct exProduct : listProducts) {
				QueryFilter filter = new QueryFilter(AppAccount.class);
				filter.addFilter("customerId=", appCustomer.getId());
				filter.addFilter("coinCode=", exProduct.getCoinCode());
				ExDigitalmoneyAccount _digitalmoneyAccount = exDigitalmoneyAccountService.get(filter);
				if (_digitalmoneyAccount == null) {
					logger.info("币账户币不存在-开始开通虚拟币账户" + appCustomer.getUserName() + "|" + exProduct.getCoinCode());
					ExDigitalmoneyAccount digitalmoneyAccount = new ExDigitalmoneyAccount();
					digitalmoneyAccount.setCurrencyType(currencyType);
					digitalmoneyAccount.setWebsite(website);
					digitalmoneyAccount.setSaasId(appCustomer.getSaasId());
					digitalmoneyAccount.setAccountNum(IdGenerate.accountNum(appCustomer.getId().intValue(), exProduct.getCoinCode()));
					digitalmoneyAccount.setCustomerId(appCustomer.getId());
					digitalmoneyAccount.setUserName(appCustomer.getUserName());
					digitalmoneyAccount.setCoinCode(exProduct.getCoinCode());
					digitalmoneyAccount.setPublicKey("");
					digitalmoneyAccount.setCoinName(exProduct.getName());
					digitalmoneyAccount.setPrivateKey(UUIDUtil.getUUID());
					digitalmoneyAccount.setStatus(Integer.valueOf(1));
					// 保存
					exDigitalmoneyAccountService.save(digitalmoneyAccount);
					
					logger.info("初始化虚拟币账户缓存---------->>>:"+digitalmoneyAccount.getId().toString());
					RedisUtil<ExDigitalmoneyAccount> redisUtil = new RedisUtil<ExDigitalmoneyAccount>(ExDigitalmoneyAccount.class);
					redisUtil.put(digitalmoneyAccount, digitalmoneyAccount.getId().toString());
				}else{
					logger.info("币账户已存在    用户名：" + appCustomer.getUserName()+"--币账户："+_digitalmoneyAccount.getAccountNum()+"-coinCode:"+_digitalmoneyAccount.getCoinCode());
				}
			}
		}

		return jsonResult;
	}

	/**
	 * 
	 * 通过用户的 id 返回用户跟用户有关系的所有币的对象
	 * 
	 * @author: Wu Shuiming
	 * @version: V1.0
	 * @date: 2016年7月26日 下午7:04:19
	 */
	@Override
	public List<ExProduct> findProduct(Long id) {
		List<ExProduct> list = exPproductService.findProductByCustomerId(id);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hry.exchange.remote.account.RemoteExProductService#findByIssueState(java
	 * .lang.Integer, java.lang.String)
	 */
	@Override
	public List<ExProduct> findByIssueState(Integer i, String saasId) {
		// TODO Auto-generated method stub
		return exPproductService.findByIssueState(i, saasId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hry.exchange.remote.account.RemoteExProductService#get(hry.util.
	 * RemoteQueryFilter)
	 */
	@Override
	public ExProduct get(RemoteQueryFilter filter) {
		return exPproductService.get(filter.getQueryFilter());
	}
	
	
	@Override
	public List<ExProduct> getSelectProduct(){
		List<ExProduct> list = exPproductService.findByIssueState(1, "");
		return list;
	}

	@Override
	public void addAveragePrice(ExProduct exProduct) {
		 exPproductService.update(exProduct);
	}

	
	/**
	 * 手动生成比地址
	 */
	@Override
	public void handToCoinAdress(String coin) {
		logger.error("进入后台：手动生成比地址方法。。。。。。。。注释啦！！！！！！！");
		/*logger.error("进入后台：手动生成比地址方法。。。。。。。。");
		QueryFilter filter=new QueryFilter(ExDigitalmoneyAccount.class);
		filter.addFilter("coinCode=", coin);
		List<ExDigitalmoneyAccount> list=exDigitalmoneyAccountService.find(filter);
		for (ExDigitalmoneyAccount exDigitalmoneyAccount : list) {
			if(exDigitalmoneyAccount.getPublicKey()!=null && !"".equals(exDigitalmoneyAccount.getPublicKey())){
			}else{
				logger.error(exDigitalmoneyAccount.getUserName()+"开始生成");
				ThreadPool.exe(new OpenCoin("cn", coin, exDigitalmoneyAccount.getUserName(), exDigitalmoneyAccount.getId()));
			}
		}*/
	}

	@Override
	public List<ExCointoCoin> getExCointoCoinlist(String toProductcoinCode, String fromProductcoinCode, Integer issueState) {
		List<ExCointoCoin> list=exCointoCoinService.getBylist(toProductcoinCode, fromProductcoinCode, issueState);
		return list;
	}
}
