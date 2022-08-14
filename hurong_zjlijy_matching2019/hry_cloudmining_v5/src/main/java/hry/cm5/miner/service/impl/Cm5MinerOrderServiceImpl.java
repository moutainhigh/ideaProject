/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-01-08 14:05:03 
 */
package hry.cm5.miner.service.impl;

import hry.bean.JsonResult;
import hry.cm5.deal.CmDealUtil;
import hry.cm5.dto.Accountadd;
import hry.cm5.dto.CmAccountRedis;
import hry.cm5.enums.CmAccountRemarkEnum;
import hry.cm5.miner.model.Cm5MinerGoods;
import hry.cm5.miner.model.Cm5MinerOrder;
import hry.cm5.miner.service.Cm5MinerGoodsService;
import hry.cm5.miner.service.Cm5MinerOrderService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mq.producer.DealMsgUtil;
import hry.util.idgenerate.IdGenerate;
import hry.utils.BaseConfUtil;
import hry.utils.CmConfigUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p> Cm5MinerOrderService </p>
 * @author:         zhouming
 * @Date :          2020-01-08 14:05:03  
 */
@Service("cm5MinerOrderService")
public class Cm5MinerOrderServiceImpl extends BaseServiceImpl<Cm5MinerOrder, Long> implements Cm5MinerOrderService{

	@Resource
	private Cm5MinerGoodsService cm5MinerGoodsService;

	@Resource(name="cm5MinerOrderDao")
	@Override
	public void setDao(BaseDao<Cm5MinerOrder, Long> dao) {
		super.dao = dao;
	}

	@Override
	public JsonResult saveMinerOrder(Long customerId, Long buyMinerId, Cm5MinerOrder cm5MinerOrder) {
		// 将要购买的矿机
		Cm5MinerGoods buyMinerGoods = cm5MinerGoodsService.get(buyMinerId);
		// 继承码
		String inheritNum = IdGenerate.transactionNum("ex");
		// 订单实际金额
		BigDecimal orderActualPrice = buyMinerGoods.getMinerPrice();
		if (cm5MinerOrder != null) {
			// 矿机升级---订单继承
			inheritNum = cm5MinerOrder.getInheritNum();
			// OrderType 订单类型 1 购买 2 升级
			cm5MinerOrder.setOrderType(2);
			// status 矿机状态 1：运行中 2：已升级(低版本升高版本) 3:已结束(已出局)
			cm5MinerOrder.setStatus(2);
			cm5MinerOrder.setEndDate(new Date());
			// 矿机订单修改
			super.update(cm5MinerOrder);
		}
		/** 币种换算 --- start --- **/
		// 支付币种
		String paycoinCode = CmConfigUtil.getConfigCoinCode(CmConfigUtil.payCoinCode);
		// 支付币种市价---单位：USDT
		BigDecimal payCoinPrice = CmConfigUtil.getPricingPrice(paycoinCode);
		// 矿机定价币种
		String pricingCoinCode = buyMinerGoods.getPricingCoinCode();
		// 矿机定价币种市价
		BigDecimal pricingCoinPrice = CmConfigUtil.getPricingPrice(pricingCoinCode);
		// ----支付币种汇率
		BigDecimal payRate = payCoinPrice.divide(pricingCoinPrice, 10, BigDecimal.ROUND_DOWN);
		// 支付金额
		BigDecimal payMoney = payRate.multiply(orderActualPrice).setScale(10, BigDecimal.ROUND_DOWN);
		/** 币种换算 --- end --- **/

		/** 查询用户支付币种账户信息，判断是否充足 */
		CmAccountRedis cmAccountRedis = CmDealUtil.getCmAccount(customerId, paycoinCode);
		BigDecimal hotMoney = cmAccountRedis.getHotMoney();
		if (hotMoney.compareTo(payMoney) < 0) {
			return new JsonResult(false).setMsg("cm_zhanghuzijinbuzu");//账户资金不足
		}
		// 保存矿机订单
		Cm5MinerOrder buyMinerOrder = new Cm5MinerOrder();
		// 继承码 用于收益记录查询
		buyMinerOrder.setInheritNum(inheritNum);
		buyMinerOrder.setCustomerId(customerId);
		buyMinerOrder.setMinerId(buyMinerId);
		buyMinerOrder.setOrderNum(IdGenerate.transactionNum("CM"));
		// 定价币种
		buyMinerOrder.setPricingCoinCode(pricingCoinCode);
		// 定价币种市价--单位 ：USDT
		buyMinerOrder.setPricingMarketPrice(pricingCoinPrice);
		// 支付币种
		buyMinerOrder.setPayCoinCode(paycoinCode);
		// 支付币种市价--单位 ：USDT
		buyMinerOrder.setPayMarketPrice(payCoinPrice);
		buyMinerOrder.setMinerName(buyMinerGoods.getMinerName());
		buyMinerOrder.setMinerCode(buyMinerGoods.getMinerCode());
		buyMinerOrder.setMinerPrice(buyMinerGoods.getMinerPrice());
		buyMinerOrder.setTotalProfit(buyMinerGoods.getMinerPrice().multiply(new BigDecimal("2")));
		buyMinerOrder.setOrderPrice(buyMinerGoods.getMinerPrice());
		buyMinerOrder.setOrderActualPrice(orderActualPrice);
		buyMinerOrder.setStartDate(new Date());
		buyMinerOrder.setRunDays(1L);
		// 订单类型 1 购买 2 升级
		buyMinerOrder.setOrderType(1);
		// 矿机状态 1：运行中 2：已升级(低版本升高版本) 3:已结束(已出局)
		buyMinerOrder.setStatus(1);
		super.save(buyMinerOrder);
		/** 币账户操作 ---减少 */
		List<Accountadd> accountaddList = new ArrayList<>();
		Accountadd accountadd = new Accountadd(customerId, paycoinCode, cmAccountRedis.getId(),
				new BigDecimal("-" + payMoney), 1, CmAccountRemarkEnum.TYPE3.getIndex(),
				buyMinerOrder.getOrderNum());
		accountaddList.add(accountadd);
		DealMsgUtil.sendAccountaddList(accountaddList);
		return new JsonResult(true).setMsg("购买成功");
	}
}
