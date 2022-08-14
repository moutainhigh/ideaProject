/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.app.exchange.product.service.impl;

import hry.app.account.service.ExDigitalmoneyAccountService;
import hry.app.exchange.product.dao.ExProductDao;
import hry.app.exchange.product.service.ExProductParameterService;
import hry.app.exchange.product.service.ExProductService;
import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.otc.manage.remote.api.RemoteAppConfigService;
import hry.otc.manage.remote.model.exchange.product.ExProduct;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import hry.util.properties.PropertiesUtils;
import hry.util.serialize.Mapper;
import hry.util.sys.ContextUtil;
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

	public static String productKey = "hry.app.exchange.product";

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
	 * hry.app.exchange.product.service.ExProductService#findByCoinCode(java.lang
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
	}


	@Override
	public String detection() {
		return null;

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
