/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-11-28 17:24:59 
 */
package hry.mall.rebate.service.impl;

import hry.bean.JsonResult;
import hry.mall.goods.model.CoinRebate;
import hry.mall.goods.model.Goods;
import hry.mall.goods.service.CoinRebateService;
import hry.mall.goods.service.GoodsService;
import hry.mall.integral.model.IntegralRebateConfig;
import hry.mall.integral.service.CustomerIntegralService;
import hry.mall.integral.service.IntegralRebateConfigService;
import hry.mall.merchant.model.Merchant;
import hry.mall.merchant.service.MerchantService;
import hry.mall.order.model.Order;
import hry.mall.order.model.OrderCoin;
import hry.mall.order.model.OrderGoods;
import hry.mall.order.service.OrderCoinService;
import hry.mall.order.service.OrderGoodsService;
import hry.mall.order.service.OrderService;
import hry.mall.rebate.model.RebateDetail;
import hry.mall.rebate.service.RebateDetailService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.mall.team.model.AppTeamlevel;
import hry.mall.team.service.AppTeamlevelService;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.User;
import hry.util.DateUtils;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> RebateDetailService </p>
 * @author:         luyue
 * @Date :          2019-11-28 17:24:59  
 */
@Service("rebateDetailService")
public class RebateDetailServiceImpl extends BaseServiceImpl<RebateDetail, Long> implements RebateDetailService{
	
	@Resource(name="rebateDetailDao")
	@Override
	public void setDao(BaseDao<RebateDetail, Long> dao) {
		super.dao = dao;
	}
	@Resource
	public OrderService orderService;
	@Resource
	public MerchantService  merchantService;
	@Resource
	public OrderCoinService orderCoinService;
	@Resource
	public IntegralRebateConfigService integralRebateConfigService;
	@Resource
	public RebateDetailService rebateDetailService;
	@Resource
	public CoinRebateService coinRebateService;
	@Resource
	public OrderGoodsService orderGoodsService;
	@Resource
	public CustomerIntegralService customerIntegralService;
	@Resource
	public GoodsService  goodsService;
	@Resource
	public AppTeamlevelService appTeamlevelService;

	@Override
	public JsonResult addDetail(long orderId) {
		JsonResult jsonObject = new JsonResult();
		try{
			BigDecimal ling=new BigDecimal("0");
			//1、查询订单信息
			Order order=orderService.get(orderId);
			//查询商户信息
			Merchant merchant=merchantService.get(order.getMerchantId());
			//2、查询冻结时间
			IntegralRebateConfig integralRebateConfig = integralRebateConfigService.get((long) 1);
			Integer shopAfter = integralRebateConfig.getShopAfter();
			String s= DateUtils.getDateAddDays(shopAfter);
			String strDateFormat = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
			Date gdate=sdf.parse(s);//解冻时间
            //3、查询是否有一二级推荐人
			QueryFilter filter1=new QueryFilter(AppTeamlevel.class);
			filter1.addFilter("customerId=",order.getMemberId());
			filter1.addFilter("level=",1);
			AppTeamlevel level1= appTeamlevelService.get(filter1);
            //二级推荐人
			filter1.addFilter("level=",2);
			AppTeamlevel level2= appTeamlevelService.get(filter1);

			//查询币支付的汇总记录
			QueryFilter filter=new QueryFilter(OrderCoin.class);
			filter.addFilter("orderId=",orderId);
			List<OrderCoin> list=orderCoinService.find(filter);
			//3、保存返佣、结算等数据
			for(OrderCoin coin:list){
				BigDecimal jcount=coin.getStoreCount();//商户结算数量
				BigDecimal ocount=coin.getOneLevelCount();//一级返佣数量
				BigDecimal tcount=coin.getTwoLevelCount();//二级返佣数量
				if(null!=level1 && !"".equals(level1)){
					//保存一级返佣
					this.saveDetail(order,coin,gdate,3,"1",ocount,level1.getParentId());//1商户结算，2购物返利，3推荐返佣，4创新商品奖励
				}else{
					jcount=jcount.add(ocount);
					coin.setOneLevelCount(ling);
				}
				if(null!=level2 && !"".equals(level2)){
					//保存一级返佣
					this.saveDetail(order,coin,gdate,3,"1",tcount,level2.getParentId());//1商户结算，2购物返利，3推荐返佣，4创新商品奖励
				}else{
					jcount=jcount.add(tcount);
					coin.setTwoLevelCount(ling);
				}
				coin.setStoreCount(jcount);
				//保存结算记录
				this.saveDetail(order,coin,gdate,1,"",jcount,merchant.getMemberId());//1商户结算，2购物返利，3推荐返佣，4创新商品奖励
				orderCoinService.update(coin);

			}
			/**
			 * 4、确定购物返利的逻辑后再添加购物返利记录
			 */
			this.grantShop(order,gdate);//发放购物返利

			/**
			 * 5、创新商品立即返利到账
			 */
			if(8==order.getOrderState()){
               this.grantInnovate(order);//发放创新奖励
			}
			jsonObject.setSuccess(true).setMsg("处理成功");

		}catch (Exception e){
			e.printStackTrace();
		}
		return jsonObject;
	}
	public void saveDetail(Order order,OrderCoin coin,Date estimateDate,int type,String level,BigDecimal count,Long memberId){
		RemoteManageService remoteManageService = SpringUtil.getBean("remoteManageService");
		User user=remoteManageService.findUserById(memberId);
		//保存结算记录
		RebateDetail detail=new RebateDetail();
		detail.setMemberId(memberId);
		String userName=null!=user.getMobile().toString() && !"".equals(user.getMobile().toString())?user.getMobile().toString():user.getEmail().toString();
		detail.setMemberName(userName);
		detail.setCoinId(coin.getId());
		detail.setType(type);//1商户结算，2购物返利，3推荐返佣，4创新商品奖励
		detail.setRecordType(1);//1增加 2减少
		detail.setCoinCode(coin.getCoinCode());
		detail.setCoinCount(count);
		detail.setOrderId(order.getId());
		detail.setOrderMoney(order.getOrderAmount());
		detail.setOrderSn(order.getOrderSn());
		detail.setOrderDate(order.getCreated());
		detail.setEstimateDate(estimateDate);
		detail.setRewardLevel(level);//返佣级别
		detail.setStatus(0);
		rebateDetailService.save(detail);
	}

	/**
	 * 发放创新奖励
	 */
	public void  grantInnovate(Order order){

		//1、查询下单商品
		QueryFilter filter = new QueryFilter(OrderGoods.class);
		filter.addFilter("orderId=", order.getId());
		List<OrderGoods>  list=orderGoodsService.find(filter);
		//2、查询返佣配置，即创新返利配置
		QueryFilter filter1=new QueryFilter(CoinRebate.class);
		filter1.addFilter("goodsSpecId=",list.get(0).getGoodsSpecId());
		CoinRebate rebate= coinRebateService.get(filter1);//返佣配置
		if(null!=rebate && !"".equals(rebate)){
			//3、保留明细
			BigDecimal buycount= new BigDecimal(list.get(0).getGoodsNum());//购买商品数量
			BigDecimal count=rebate.getInnovateRebateSum();//奖励个数
			String coinCode=rebate.getInnovateRebateCoin();//奖励币种
            if(null!=count && count.compareTo(new BigDecimal("0"))>0){//配置的奖励个数大于0 才发放创新奖励
            	count=count.multiply(buycount);//一件商品奖励个数*购买商品件数
				//4、保存结算记录
				RebateDetail detail=new RebateDetail();
				detail.setMemberId(order.getMemberId());
				detail.setMemberName(order.getUserName());
				detail.setCoinId(rebate.getId());//返佣配置id
				detail.setType(4);//1商户结算，2购物返利，3推荐返佣，4创新商品奖励
				detail.setRecordType(1);//1增加 2减少
				detail.setCoinCode(coinCode);
				detail.setCoinCount(count);
				detail.setOrderId(order.getId());
				detail.setOrderMoney(order.getOrderAmount());
				detail.setOrderSn(order.getOrderSn());
				detail.setOrderDate(order.getCreated());
				detail.setEstimateDate(new Date());
				detail.setRewardLevel(null);//返佣级别
				detail.setStatus(1);
				rebateDetailService.save(detail);
				//5、发消息，账户加币
				//封装参数，操作币业务流水和账户
				Map<String,String> map2=new HashMap<String,String>();
				map2.put("coinCode", detail.getCoinCode());
				map2.put("memberId", detail.getMemberId().toString());
				map2.put("transactionType", "1");// 交易类型(1币收入 ，2币支出)
				map2.put("coinCount", detail.getCoinCount().toString());
				map2.put("OrderNo", "SCJL"+detail.getId().toString());
				map2.put("optType", "513");//商城奖励
				customerIntegralService.handCoin(map2);
			}

		}

	}
	/**
	 *发放购物奖励
	 */
	public void grantShop(Order order,Date gdate){

		//1、查询下单商品
		QueryFilter filter = new QueryFilter(OrderGoods.class);
		filter.addFilter("orderId=", order.getId());
		List<OrderGoods>  list=orderGoodsService.find(filter);
        Goods goods=goodsService.get(list.get(0).getGoodsId());
        //2、查询返币个数及币种
		BigDecimal buycount= new BigDecimal(list.get(0).getGoodsNum());//购买商品数量
		BigDecimal count=goods.getRebatePrice().multiply(buycount);//奖励个数
		String coinCode=goods.getRebateCoin();//奖励币种
		Integer isCochain=goods.getIsCochain();//是否上链
		//3、保存明细
		//保存结算记录
		if(count.compareTo(new BigDecimal("0"))>0){//数量大于0再保存返利明细
			RebateDetail detail=new RebateDetail();
			detail.setMemberId(order.getMemberId());
			detail.setMemberName(order.getUserName());
			detail.setCoinId(goods.getId());
			detail.setType(2);//1商户结算，2购物返利，3推荐返佣，4创新商品奖励
			detail.setRecordType(1);//1增加 2减少
			detail.setCoinCode(coinCode);
			detail.setCoinCount(count);
			detail.setOrderId(order.getId());
			detail.setOrderMoney(order.getOrderAmount());
			detail.setOrderSn(order.getOrderSn());
			detail.setOrderDate(order.getCreated());
			detail.setEstimateDate(gdate);
			detail.setRewardLevel(null);//返佣级别
			detail.setStatus(0);
			detail.setIsCochain(isCochain);//是否上链
			rebateDetailService.save(detail);
		}

	}
}
