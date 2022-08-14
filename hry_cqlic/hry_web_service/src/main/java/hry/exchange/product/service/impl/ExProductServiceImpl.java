/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.exchange.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.core.thread.ThreadPool;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.product.PublishRunnable;
import hry.exchange.product.dao.ExProductDao;
import hry.exchange.product.model.ExProduct;
import hry.exchange.product.service.ExProductParameterService;
import hry.exchange.product.service.ExProductService;
import hry.exchange.product.synced.SyncedUserForProduct;
import hry.exchange.purse.CoinInterfaceUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.entrust.ExchangeDataCache;
import hry.util.QueryFilter;
import hry.util.properties.PropertiesUtils;
import hry.util.serialize.Mapper;
import hry.util.sys.ContextUtil;
import hry.web.remote.RemoteAppConfigService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author: Wu Shuiming
 * @Date : 2016年3月24日 下午2:04:29
 */
@Service("exProductService")
public class ExProductServiceImpl extends BaseServiceImpl<ExProduct, Long> implements ExProductService{

	public static String productKey = "HRY:EXCHANGE:PRODUCT";

	@Resource(name = "exProductParameterService")
	public ExProductParameterService exProductParameterService;

	@Resource(name = "redisService")
	public RedisService redisService;

	@Resource
	public ExDigitalmoneyAccountService exDigitalmoneyAccountService;

	@Resource(name = "exProductDao")
	@Override
	public void setDao(BaseDao<ExProduct, Long> dao) {
		super.dao = dao;
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
			// System.out.println("-------------------  " + list);
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * hry.exchange.product.service.ExProductService#findByCoinCode(java.lang
	 * .String, java.lang.String)
	 */
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

	/**
	 * 从缓存中获取 product 如果没有将从数据库中查询并从新更新整个缓存
	 *
	 * @param coinCode
	 * @return
	 */
	@Override
	public ExProduct findProductByRedis(String coinCode) {

		Map<String, String> map = redisService.getMap(productKey);
		String productJson = map.get(coinCode);

		if (null != productJson) {
			ExProduct jsonToObj = (ExProduct) Mapper.JSONToObj(productJson, ExProduct.class);
			return jsonToObj;
		} else {
			ExProduct exProduct = this.updateProductToRedis(coinCode);
			return exProduct;
		}
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
	public JsonResult delishProduct(Long[] ids) {

		for (int i = 0; i < ids.length; i++) {
			Long id = ids[i];
			ExProduct product = super.get(id);
			SyncedUserForProduct syncedUserForProduct = new SyncedUserForProduct();
			if (product != null && product.getIssueState() != 3) {
				// 将商品的状态 先改为退市状态
				product.setIssueState(3);
				super.update(product);
				// 同步所有用户的的虚拟币账号并将它们改为不可用状态
				syncedUserForProduct.delistForProduct(product);

			}
		}

		// 更新缓存 code
		initRedisCode();
		this.updateProductToRedis("");
		JsonResult jsonResult = new JsonResult();
		jsonResult.setMsg("产品全部下线成功");
		jsonResult.setSuccess(true);
		return jsonResult;
	}

	/**
	 * 根据用户的id 查出跟用户有关的所有币相关的所有信息
	 *
	 */
	@Override
	public List<ExProduct> findProductByCustomerId(Long id) {

		ExProductDao exProductDao = (ExProductDao) dao;
		List<ExProduct> list = exProductDao.selectProductByCustomerId(id);
		return list;
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
	public String detection() {
		StringBuffer sb = new StringBuffer();
		QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
		filter.addFilter("publicKey=", "");
		List<ExDigitalmoneyAccount> list = exDigitalmoneyAccountService.find(filter);
		sb.append("【总条数】" + list.size());
		for (ExDigitalmoneyAccount ea : list) {
			sb.append("【id=" + ea.getId());
			sb.append("|");
			sb.append("userName=" + ea.getUserName());

			String ss = "";
			if (ContextUtil.EN.equals(ea.getWebsite())) {// 如果是国际站进来的,用户名拼接"-USD"
				ss = CoinInterfaceUtil.create(ea.getUserName() + "-USD", ea.getAccountNum(),ea.getCoinCode());
			} else {
				ss = CoinInterfaceUtil.create(ea.getUserName(),ea.getAccountNum(), ea.getCoinCode());
			}
			JSONObject parseObject = null;
			if (!StringUtils.isEmpty(ss)) {
				parseObject = JSONObject.parseObject(ss);
			}
			String address = "";
			if (parseObject != null) {
				address = parseObject.get("address").toString();
			}
			if (!address.equals("")) {
				sb.append("|");
				sb.append("address=" + address);
				ea.setPublicKey(address);
			} else {
				ea.setPublicKey("");
			}

			sb.append("】");
			// 保存
			exDigitalmoneyAccountService.update(ea);
		}
		return sb.toString();

	}

	/**
	 * i 1 表示 开市 2 表示 闭市
	 *
	 */
	@Override
	public JsonResult endProduct(Long id, Integer i) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setSuccess(true);
		ExProduct product = super.get(id);
		if (product.getOpenBell() != i) {
			product.setOpenBell(i);
			super.update(product);
			jsonResult.setMsg("币盘成功");
			return jsonResult;
		}
		jsonResult.setSuccess(false);
		jsonResult.setMsg("此产品已经闭盘");
		return jsonResult;
	}

	@Override
	public Integer getkeepDecimalForRmb() {
		 RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		 String keepDecimalForRmb=remoteAppConfigService.getFinanceByKey("keepDecimalForRmb");
		 if(!StringUtils.isEmpty(keepDecimalForRmb)){
			 return Integer.valueOf(keepDecimalForRmb);
		 }
		return 2;
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

	/*@Override
	public void initCache(CacheManageCallBack cacheManageCallBack) {
		updateProductToRedis("");
		cacheManageCallBack.callback(ExProductService.class, productKey, "货币信息缓存");
	}*/

}
