/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.trade.remote.exEntrust;

import hry.change.remote.exEntrust.RemoteExEntrustService;
import hry.change.remote.exEntrust.RemoteExExEntrustService;
import hry.bean.PageResult;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.util.properties.PropertiesUtils;
import hry.util.serialize.Mapper;
import hry.exchange.product.model.ProductCommon;
import hry.trade.entrust.ExchangeDataCache;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExEntrustPlan;
import hry.trade.entrust.model.ExOrder;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.entrust.service.ExEntrustPlanService;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.entrust.service.ExOrderInfoService;
import hry.trade.entrust.service.ExOrderService;
import hry.trade.redis.model.EntrustTrade;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月24日 下午2:04:29
 */

public class RemoteExEntrustServiceImpl implements RemoteExEntrustService {

	@Resource
	private ExEntrustService entrustService;
	@Resource
	private ExOrderInfoService exOrderInfoService;
	@Resource
	private ExOrderService exOrderService;
	@Resource
	private ExEntrustPlanService exEntrustPlanService;
	@Resource
	private RemoteExExEntrustService remoteExExEntrustService;

	@Override
	public String[] saveExEntrust(ExEntrust exEntrust) {
		return entrustService.saveExEntrust(exEntrust);
	}

	@Override
	public List<ExEntrust> findByCustomerId(Long customerId, Integer status) {
		String saasId = RpcContext.getContext().getAttachment("saasId");
		QueryFilter filter = new QueryFilter(ExEntrust.class);
		filter.setSaasId(saasId);
		filter.addFilter("customerId=", customerId);
		if (status.equals(10)) {
			filter.addFilter("status<", 2);
		} else if (status.equals(20)) {
			filter.addFilter("status_in", "2,3,4");

		}
		return entrustService.find(filter);

	}

	@Override
	public PageResult orderFindByCustomerId(RemoteQueryFilter remoteQueryFilter) {

		return exOrderInfoService.findPageResult(remoteQueryFilter.getQueryFilter());

	}

	@Override
	public String[] cancelExEntrust(EntrustTrade entrustTrade) {

		return entrustService.cancelExEntrust(entrustTrade, "手动撤单");
	}

	@Override
	public String getExEntrustChange(String coinCode, Integer type, String depth, Integer n, String customerType) {
		return entrustService.getExEntrustChange(coinCode, type, depth, n, customerType);
	}

	@Override
	public String getNewList(String coinCode, Integer count) {
		return exOrderService.getNewList(coinCode, count);
	}

	@Override
	public String getTotalData(String coinCode) {
		// TODO Auto-generated method stub
		return exOrderService.getTotal(coinCode);
	}

	@Override
	public ExEntrust getExEntrust(String entrustNums, String coinCode) {
		String saasId = RpcContext.getContext().getAttachment("saasId");
		if (null == saasId) {
			saasId = PropertiesUtils.APP.getProperty("app.saasId");
		}
		return entrustService.getExEntrust(entrustNums, coinCode, saasId);
	}

	public List<ExEntrust> listExEntrust(QueryFilter filter) {
		List<ExEntrust> list = entrustService.find(filter);
		return list;
	}

	@Override
	public PageResult listExEntrust(RemoteQueryFilter filter) {

		return entrustService.findPageResult(filter.getQueryFilter());
	}

	@Override
	public List<ExOrder> findExOrderList(RemoteQueryFilter remoteQueryFilter) {

		QueryFilter queryFilter = remoteQueryFilter.getQueryFilter();
		return exOrderService.find(queryFilter);
	}

	@Override
	public String getTotalData(String currencyType, String Website) {
		String productListStr = ExchangeDataCache.getStringData(Website + ":productList");
		if (!StringUtils.isEmpty(productListStr)) {
			List<String> productList = JSON.parseArray(productListStr, String.class);
			StringBuffer sb = new StringBuffer("{\"total\":" + (null != productList ? productList.size() : 0) + ",");
			sb.append("\"result\":[");
			int i = 0;
			for (String coinCode : productList) {
				ProductCommon productCommon = remoteExExEntrustService.getProductCommon(coinCode);
				String header = Website + ":" + currencyType + ":" + productCommon.getCoinCode();
				String transtionjson = getTotalData(header);
				sb.append("{\"coinCode\":\"" + productCommon.getCoinCode() + "\",\"name\":\"" + productCommon.getName()
						+ "\",\"isRecommend\":\"" + productCommon.getIsRecommend() + "\",\"data\":" + transtionjson
						+ "}");
				if (i != productList.size() - 1) {
					sb.append(",");
				}
				i++;
			}
			sb = sb.append("]}");
			return sb.toString();
		}
		return "{}";
	}

	@Override
	public BigDecimal getCurrentExchangPrice(String coinCode) {
		String currentExchangPrice = ExchangeDataCache.getStringData(coinCode + ":" + "currentExchangPrice");

		if (!StringUtils.isEmpty(currentExchangPrice)) {

			return new BigDecimal(currentExchangPrice);
		} else {
			return null;

		}
	}

	@Override
	public List<ExOrderInfo> getMatchDetail(String entrustNum, String coinCode,String type) {
		// TODO Auto-generated method stub
		return entrustService.getMatchDetail(entrustNum, coinCode,type);
	}

	@Override
	public String getExEntrustChangeMarket(String coinCode, String depth, Integer n, String customerType) {

		return entrustService.getExEntrustChangeMarket(coinCode, depth, n, customerType);
	}

	@Override
	public Map<String, BigDecimal> getExEntrustmMostPrice(String coincode, int type, List<Integer> customerType) {
		return entrustService.getExEntrustmMostPrice(coincode, type, customerType);
	}

	@Override
	public List<ExEntrust> getExEntrustByPrice(String coincode, int type, String customerType, BigDecimal entrustPrice) {
		// TODO Auto-generated method stub
		return entrustService.getExEntrustByPrice(coincode, type, customerType, entrustPrice);
	}

	@Override
	public Boolean isAccordwithMarket(ExEntrustPlan p) {
		// TODO Auto-generated method stub
		return exEntrustPlanService.isAccordwithMarket(p);
	}

	@Override
	public void cancelAlltypeExEntrust(Integer type, String currencyType, String website, String customerType,
			Long customerId) {
		entrustService.cancelAlltypeExEntrust(type, currencyType, website, customerType, customerId);
	}

	@Override
	public void cancelConditionExEntrust(Map<String, String> p) {
		entrustService.cancelConditionExEntrust(p);
	}

	@Override
	public void saveExEntrustPlan(ExEntrustPlan p) {
		p.setPingCondition(exEntrustPlanService.pingCondition(p));
		exEntrustPlanService.save(p);
	}

	@Override
	public PageResult getexEntrustPlanPage(RemoteQueryFilter remoteQueryFilter) {
		return exEntrustPlanService.findPageResult(remoteQueryFilter.getQueryFilter());
	}

	@Override
	public List<ExEntrustPlan> getEntrustPlanlist(RemoteQueryFilter remoteQueryFilter) {
		return exEntrustPlanService.find(remoteQueryFilter.getQueryFilter());
	}

	/*
	 * @Override public void createExEntrustByPlan(ExEntrustPlan p) {
	 * 
	 * exEntrustPlanService.saveExEntrustPlan(p); }
	 */
	@Override
	public void removeExEntrustPlan(Long id) {
		exEntrustPlanService.delete(id);

	}

	@Override
	public List<ExEntrust> getExEntrustlist(String entrustNums) {
		// TODO Auto-generated method stub
		return entrustService.getExEntrustlist(entrustNums);
	}

	@Override
	public void cancelCustAllExEntrust(Long custtomerId) {
	//	entrustService.cancelCustAllExEntrust(custtomerId);

	}

	@Override
	public void cancelCustAllCoinCodeExEntrust(Long custtomerId, String currencyType, String website, String CoinCode) {
		entrustService.cancelCustAllCoinCodeExEntrust(custtomerId, currencyType, website, CoinCode);

	}

	@Override
	public void cancelAllExEntrust() {
		entrustService.cancelAllExEntrust();

	}

	@Override
	public List<ExEntrust> find(RemoteQueryFilter remoteQueryFilter) {
		return entrustService.find(remoteQueryFilter.getQueryFilter());

	}

	@Override
	public BigDecimal getTransactionFeeing(ExEntrust exEntrust) {
		return entrustService.getTransactionFeeing(exEntrust);
	}

}
