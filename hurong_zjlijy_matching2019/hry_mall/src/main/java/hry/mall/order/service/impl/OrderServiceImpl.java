/**
 * Copyright:
 *
 * @author: kongdebiao
 * @version: V1.0
 * @Date: 2018-11-16 10:31:38
 */
package hry.mall.order.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import hry.bean.PageResult;
import hry.common.util.DateUtils;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.goods.model.CoinPay;
import hry.mall.goods.model.CoinRebate;
import hry.mall.goods.model.Goods;
import hry.mall.goods.model.SaleGoodsConfig;
import hry.mall.goods.service.CoinPayService;
import hry.mall.goods.service.CoinRebateService;
import hry.mall.goods.service.GoodsService;
import hry.mall.goods.service.SaleGoodsConfigService;
import hry.mall.order.dao.OrderDao;
import hry.mall.order.model.Order;
import hry.mall.order.model.OrderCoin;
import hry.mall.order.model.vo.OrderBalanceVo;
import hry.mall.order.model.vo.OrderRefundVo;
import hry.mall.order.service.OrderCoinService;
import hry.mall.order.service.OrderService;
import hry.mall.platform.model.MallConfig;
import hry.mall.platform.model.Payment;
import hry.mall.platform.service.MallConfigService;
import hry.mall.platform.service.PaymentService;
import hry.mall.retailstore.model.CouponDetail;
import hry.mall.retailstore.service.CouponDetailService;
import hry.manage.remote.RemoteMallService;
import hry.manage.remote.model.mall.ExProductVo;
import hry.redis.common.utils.RedisService;
import hry.util.BaseConfUtil;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.SpringUtil;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> OrderService </p>
 * @author: kongdebiao
 * @Date :          2018-11-16 10:31:38  
 */
@Service("orderService")
public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements OrderService {

    @Resource(name = "orderDao")
    @Override
    public void setDao(BaseDao<Order, Long> dao) {
        super.dao = dao;
    }
    @Resource
    public GoodsService goodsService;
    @Resource
    public PaymentService paymentService;
    @Resource
    public MallConfigService mallConfigService;
    @Resource
    public CouponDetailService couponDetailService;
	@Resource
	private CoinPayService coinPayService;
	@Resource
	private CoinRebateService coinRebateService;
	@Resource
	private OrderCoinService orderCoinService;
	@Resource
	private SaleGoodsConfigService saleGoodsConfigService;

    @Override
    public PageResult findPageBySql(QueryFilter filter) {

        PageResult pageResult = new PageResult();
        Page<Order> page = PageFactory.getPage(filter);
        String orderSn = filter.getRequest().getParameter("orderSn");
        String orderState = filter.getRequest().getParameter("orderState");
        String userName = filter.getRequest().getParameter("userName");
        String goodsName = filter.getRequest().getParameter("goodsName");
        String receivePerson = filter.getRequest().getParameter("receivePerson");
        String created = filter.getRequest().getParameter("created");
        Map<String, Object> map = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(orderSn)) {
            map.put("orderSn", orderSn);
        }
        if (!StringUtils.isEmpty(orderState)) {
            map.put("orderState", orderState);
        }
        if (!StringUtils.isEmpty(userName)) {
            map.put("userName", userName);
        }
        if (!StringUtils.isEmpty(goodsName)) {
            map.put("goodsName", goodsName);
        }
        if (!StringUtils.isEmpty(receivePerson)) {
            map.put("receivePerson", "%" + receivePerson + "%");
        }
        if (!StringUtils.isEmpty(created)) {
            map.put("created", created + "00:00:00");
        }
        if (!StringUtils.isEmpty(created)) {
            map.put("creatend", created + "59:59:59");
        }
        ((OrderDao) dao).findPageBySql(map);
        return new PageResult(page, filter.getPage(), filter.getPageSize());
    }


	@Override
	public String createNumber() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        QueryFilter filter = new QueryFilter(Order.class);
        filter.addFilter("orderSn_LIKE", "%"+newDate+"%");
        filter.setOrderby("id desc");
        List<Order> list=this.find(filter);
        Order order=null;
        if(null!=list && list.size()>0){
        	order=list.get(0);
        }
        String orderSn = new String(""); //订单编号
        if(null!=order){
        	StringBuffer number = new StringBuffer("");
			String proNum = order.getOrderSn();
			String ss = proNum.substring(proNum.length()-3);
			int num = new Integer(ss);
			num+=1;
			if(num<10){
				number = number.append("00").append(num);
			}else if(num>=10&&num<100){
				number = number.append("0").append(num);
			}else {
				number = number.append(num);
			}
			orderSn=newDate+number.toString();
        	
        }else{
        	orderSn=newDate+"001";
        }
		return orderSn;
	}


	@Override
	public Order saveOrder(Map<String, String> map) {
		// TODO Auto-generated method stub
		Order order =new Order();
		String number=this.createNumber();
		order.setOrderSn(number);
		String memberId=map.get("memberId");
		order.setMemberId(Long.valueOf(memberId));
		String mobile=map.get("mobile");
		order.setUserName(mobile);
		String trueName=map.get("trueName");
		String surname=map.get("surname");
		order.setMemberName(surname+"*"+trueName);
		order.setOrderState(10);
   		BigDecimal ling=new BigDecimal("0");
   		BigDecimal sumMoney=new BigDecimal(map.get("sumMoney"));
   		BigDecimal discount=new BigDecimal("0");
   		String couponDetailId=map.get("couponDetailId");//优惠券id
   		if(null!=couponDetailId && !"".equals(couponDetailId)){
   			CouponDetail detail= couponDetailService.get(Long.valueOf(couponDetailId));
   			if(null!=detail && !"".equals(detail)){
   				discount=detail.getFaceValue();
   				detail.setStatus(1);
   				detail.setOrderId(order.getId());
   				detail.setOrderSn(order.getOrderSn());
   				detail.setUseTime(new Date());
   				couponDetailService.update(detail);
   			}
   		}
   		order.setGoodsAmount(sumMoney); //商品总价格
   		order.setDiscount(discount);//订单优惠金额
   		order.setOrderAmount(sumMoney.subtract(discount));//订单实际金额
   		order.setOrderTotalPrice(sumMoney);//订单总金额
   		order.setPaymentState(Integer.valueOf("0"));
   		//订单类型类型
   		String orderSort=map.get("orderSort");
   		order.setOrderSort(Integer.valueOf(orderSort));
   		order.setOrderSource("APP");
   		order.setShippingFee(new BigDecimal(map.get("fee")));
   		String payType=map.get("payType");
   		order.setPayType(payType);//支付方式,人民币:1,数字币：2,混合支付：3,消费账户：4
   		BigDecimal rmbMoney=new BigDecimal(map.get("rmbMoney"));//人民币支付金额
   		order.setRmbMoney(rmbMoney);
   		order.setRmbFeeMoney(ling);
   		order.setRmbFeeRate(ling);
   		//如果是人民币支付方式，则保存支付通道的id,手续费率
   		if("1".equals(payType)){
   			order.setRmbMoney(sumMoney.add(new BigDecimal(map.get("fee"))).subtract(discount));
   			//支付通道id在人民币支付页面再获取-----2019-01-14注释掉
   			String paymentId=map.get("paymentId");
   			if(null!=paymentId && !"".equals(paymentId)){
   				order.setPaymentId(Long.valueOf(paymentId));
   				Payment payment=paymentService.get(Long.valueOf(paymentId));
   				order.setPaymentName(payment.getPaymentName());
   				order.setCoinCount(ling);//虚拟币数量
   		   		order.setCoinFeeRate(ling);
   	   	   		order.setCoinFeeCount(ling);
   				if(null!=payment &&null!=payment.getPayPoundage() && !"".equals(payment.getPayPoundage())){
   					BigDecimal rate=new BigDecimal(payment.getPayPoundage());
   					order.setRmbFeeRate(rate);
   					order.setRmbFeeMoney((sumMoney.subtract(discount)).multiply(rate).divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP));
   				}
   			}
   		}
   		String coinCode=map.get("coinCode");
   		//如果支付方式为消费账户，则币种默认为平台币
   		if("4".equals(payType)){
   		 // 获取 平台币
   			coinCode = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");
   		}
   		//币种不为空，则使用虚拟币进行支付了
   		if(null!=coinCode && !"".equals(coinCode)){
   			//如果是积分订单，则单独处理
	   		if(null!=orderSort && "2".equals(orderSort)){
	   		 order.setCoinRate(ling);
	   		 order.setCoinCode(coinCode);
	   		 order.setCoinCount(new BigDecimal(map.get("coinCount")));//虚拟币数量
	   		 order.setCoinFeeRate(ling);
   	   		 order.setCoinFeeCount(ling);
	   		}else{
	   		 BigDecimal coinMoney=sumMoney.subtract(rmbMoney).subtract(discount).add(new BigDecimal(map.get("fee")));
	   		 BigDecimal coinRate=goodsService.getCoinRate(coinCode);
	   		 order.setCoinRate(coinRate.setScale(10, BigDecimal.ROUND_HALF_UP));
	   		 order.setCoinCode(coinCode);
	   		 order.setCoinCount(coinMoney.divide(coinRate, 10, BigDecimal.ROUND_HALF_UP));//虚拟币数量
	   		 RemoteMallService remoteMallService = SpringUtil.getBean("remoteMallService");  
	   		 ExProductVo vo=remoteMallService.findByCoinCode(coinCode);
	   		 if(null!=vo && null!=vo.getBuyGoodsRate()){
	   	   		 order.setCoinFeeRate(vo.getBuyGoodsRate());
	   	   		 order.setCoinFeeCount(order.getCoinCount().multiply(vo.getBuyGoodsRate()).divide(new BigDecimal("100")).setScale(10, BigDecimal.ROUND_HALF_UP));
	   		 }
	   		}
   		}
   		//	//获得系统的配置的订单失效时长
		Integer validTime=new Integer("0");
		List<MallConfig> list=mallConfigService.findAll();
		if(null!=list && list.size()>0){
			MallConfig  config=	list.get(0);
			validTime= config.getOrderTime(); 
		}
   		Date payEndTime= DateUtils.addDateMinut(new Date(), validTime);
   		order.setPayEndTime(payEndTime);
   		this.save(order);
		return order;
	}
	@Override
	public List<OrderRefundVo> findUnionBySql(Map<String, String> map) {
		// TODO Auto-generated method stub
		List<OrderRefundVo> list=((OrderDao)dao).findUnionBySql(map);
		return list;
	}
	/**
	 *李金沅定制保存订单信息
	 * */
	@Override
	public Order saveOrder1(Map<String, String> map) {
		// TODO Auto-generated method stub
		Order order =new Order();
		String number=this.createNumber();
		order.setOrderSn(number);
		String memberId=map.get("memberId");
		order.setMemberId(Long.valueOf(memberId));
		String mobile=map.get("mobile");
		order.setUserName(mobile);
		String trueName=map.get("trueName");
		String surname=map.get("surname");
		order.setMemberName(surname+"*"+trueName);
		order.setOrderState(10);
		Short isAdvance=Short.valueOf(map.get("isAdvance"));
		order.setIsAdvance(isAdvance);//是否预售
		BigDecimal ling=new BigDecimal("0");
		BigDecimal sumMoney=new BigDecimal(map.get("sumMoney"));
		BigDecimal discount=new BigDecimal("0");
		String couponDetailId=map.get("couponDetailId");//优惠券id
		if(null!=couponDetailId && !"".equals(couponDetailId)){
			CouponDetail detail= couponDetailService.get(Long.valueOf(couponDetailId));
			if(null!=detail && !"".equals(detail)){
				discount=detail.getFaceValue();
				detail.setStatus(1);
				detail.setOrderId(order.getId());
				detail.setOrderSn(order.getOrderSn());
				detail.setUseTime(new Date());
				couponDetailService.update(detail);
			}
		}
		order.setGoodsAmount(sumMoney); //商品总价格
		order.setDiscount(discount);//订单优惠金额
		order.setOrderAmount(sumMoney.subtract(discount));//订单实际金额
		order.setOrderTotalPrice(sumMoney);//订单总金额
		order.setPaymentState(Integer.valueOf("0"));
		//订单类型类型
		String orderSort=map.get("orderSort");
		order.setOrderSort(Integer.valueOf(orderSort));
		order.setOrderSource("APP");
		order.setShippingFee(new BigDecimal(map.get("fee")));
		String payType=map.get("payType");
		order.setPayType(payType);//支付方式,,数字币-单币种：2,数字币-多币种：5
		order.setRmbMoney(ling);
		order.setRmbFeeMoney(ling);
		order.setRmbFeeRate(ling);
		//2、根据规格id查询商品的支付方式及个数
		String goodsSpecIds=map.get("goodsSpecIds");
		String [] specArr =goodsSpecIds.split(",");
		String specId=specArr[0];
		QueryFilter filter=new QueryFilter(CoinPay.class);
		filter.addFilter("goodsSpecId=",specId);
		CoinPay pay=coinPayService.get(filter);//支付配置
		QueryFilter filter1=new QueryFilter(CoinRebate.class);
		filter1.addFilter("goodsSpecId=",specId);
		CoinRebate rebate= coinRebateService.get(filter1);//返佣配置


		//3、获得系统的配置的订单失效时长
		Integer validTime=new Integer("0");
		List<MallConfig> list=mallConfigService.findAll();
		if(null!=list && list.size()>0){
			MallConfig  config=	list.get(0);
			validTime= config.getOrderTime();
		}
		Date payEndTime= DateUtils.addDateMinut(new Date(), validTime);
		order.setPayEndTime(payEndTime);
		this.save(order);
		String toPayCoin="";
		//4、根据规格id查询商品的支付方式及个数
		String goodsIds=map.get("goodsIds");
		String [] goodsArr =goodsIds.split(",");
		Goods good=goodsService.get(Long.valueOf(goodsArr[0]));
		order.setMerchantId(good.getMerchantId());//商户id
		order.setMerchantName(good.getMerchantName());//商户名称
		BigDecimal rate=new BigDecimal("1");//支付比例，默认为1
		//5、如果是预支付商品，要计算定金的数量
		if(null!=good.getIsAdvance() && 1==good.getIsAdvance()){
			QueryFilter f=new QueryFilter(SaleGoodsConfig.class);
			f.addFilter("goodsId=",good.getId());
			SaleGoodsConfig sale=saleGoodsConfigService.get(f);
			rate=sale.getDepositRatio().divide(new BigDecimal("100"));
		}
		//6、获得购买数量
		String counts=map.get("counts");
		String [] countArr =counts.split(",");
		BigDecimal buycount=new BigDecimal(countArr[0]);
		//单币种支付
		if("2".equals(payType)){
			String coinCode=map.get("coinCode");
			BigDecimal count=this.findCount(coinCode,pay.getSinglePayMent()).multiply(buycount);//要乘以购买数量
			order.setCoinCode(coinCode);
			order.setCoinCount(count);
			order.setPayCoin(coinCode+"_"+count.toString());
			toPayCoin=coinCode+"_"+count.toString();//待支付币种串
			OrderCoin coin=new OrderCoin();
			coin.setOrderId(order.getId());
			coin.setCoinCode(coinCode);
			coin.setCoinCount(count);
			coin.setGoodsSpecId(Long.valueOf(specId));
			coin.setPayId(pay.getId());
			coin.setPaidCount(ling);
			coin.setUnpaidCount(count);
			coin.setBalanceCode(coinCode);
			coin.setAdvanceCount(count);//全款时，定金就是全部数量
			BigDecimal pcount=ling;//平台抽成数量
			BigDecimal ocount=ling;//一级返佣数量
			BigDecimal tcount=ling;//二级返佣数量
			if(null!=rebate && !"".equals(rebate)){
				coin.setRebateId(rebate.getId());
				 pcount=this.findCount(coinCode,rebate.getSingleTake()).multiply(buycount);//平台抽成数量
				 ocount=this.findCount(coinCode,rebate.getSingleOneRebate()).multiply(buycount);//一级返佣数量
				 tcount=this.findCount(coinCode,rebate.getSingleTwoRebate()).multiply(buycount);//二级返佣数量
			}
			coin.setPlatCount(pcount);
			coin.setOneLevelCount(ocount);
			coin.setTwoLevelCount(tcount);
			coin.setStoreCount(count.subtract(pcount).subtract(ocount).subtract(tcount));//商户结算数量
			/**
			 * 根据是否预售保存预付款的金额
			 */
			if(1==isAdvance){//如果是预售商品，则计算下定金和尾款数量
				toPayCoin=coinCode+"_"+count.multiply(rate).toString();//待支付币种串,定金按比例收取
				coin.setAdvanceCount(count.multiply(rate));//定金金额
				coin.setTailCount(count.subtract(count.multiply(rate)));//尾款金额
			}
			orderCoinService.save(coin);
		} else if("5".equals(payType)){//混合币种支付
			String tailPayCoin="";
			order.setPayCoin(pay.getMorePayMent());
			String [] arry=pay.getMorePayMent().split(",");//币种_个数,币种_个数,...
			StringBuffer buffer=new StringBuffer();
			toPayCoin=pay.getMorePayMent();
			for(int i=0;i<arry.length;i++){
				String [] sarry=arry[i].split("_");
				String coinCode=sarry[0];
				BigDecimal count=new BigDecimal(sarry[1]).multiply(buycount);//乘以购买数量
				OrderCoin coin=new OrderCoin();
				coin.setOrderId(order.getId());
				coin.setCoinCode(coinCode);
				coin.setCoinCount(count);
				coin.setGoodsSpecId(Long.valueOf(specId));
				coin.setPayId(pay.getId());
				coin.setPaidCount(ling);
				coin.setUnpaidCount(count);
				BigDecimal pcount=ling;//平台抽成数量
				BigDecimal ocount=ling;//一级返佣数量
				BigDecimal tcount=ling;//二级返佣数量
				//如果是混合支付法人返利币种
				if(null!=rebate){
					coin.setRebateId(rebate.getId());
					coin.setBalanceCode(rebate.getMorePayRebateCoin());//返佣币种
					if(coinCode.equals(rebate.getMorePayRebateCoin())){
						pcount=rebate.getTakeCoinNum().multiply(buycount);//平台抽成数量*购买数量
						ocount=rebate.getOneRebateCoinNum().multiply(buycount);//一级返佣数量*购买数量
						tcount=rebate.getTwoRebateCoinNum().multiply(buycount);//二级返佣数量*购买数量
					}
				}
				coin.setPlatCount(pcount);
				coin.setOneLevelCount(ocount);
				coin.setTwoLevelCount(tcount);
				coin.setStoreCount(count.subtract(pcount).subtract(ocount).subtract(tcount));//商户结算数量
				coin.setAdvanceCount(count.multiply(rate));//定金金额---全款的定金金额就是全部金额
				/**
				 * 根据是否预售保存预付款的金额
				 */
				if(1==isAdvance){//如果是预售商品，则计算下定金和尾款数量
					toPayCoin=coinCode+"_"+count.multiply(rate).toString()+",";//待支付币种串,定金按比例收取
					coin.setAdvanceCount(count.multiply(rate));//定金金额
					coin.setTailCount(count.subtract(count.multiply(rate)));//尾款金额
					tailPayCoin=coinCode+"_"+count.subtract(count.multiply(rate)).toString()+",";//尾款币种拼串
				}
				orderCoinService.save(coin);
			}
			if(1==isAdvance){
				tailPayCoin=tailPayCoin.substring(0,tailPayCoin.length()-1);
			}
			toPayCoin=toPayCoin.substring(0,toPayCoin.length()-1);
			order.setTailPayCoin(tailPayCoin);
		}
		order.setAdvancePayCoin(toPayCoin);
        this.update(order);
        order.setToPayCoin(toPayCoin);
		return order;
	}

	@Override
	public BigDecimal findCount(String coinCode, String content) {
		BigDecimal counnt=new BigDecimal("0");
		String [] arry=content.split(",");
		for (int i=0;i<arry.length;i++){
			String [] sarry=arry[i].split("_");
			if(sarry[0].equals(coinCode)){
				counnt=new BigDecimal(sarry[1]);
			}
		}
		return counnt;
	}

	@Override
	public String findPicturePath(String coinCode) {
		RedisService redisService = SpringUtil.getBean("redisService");
		Map<String, String> map = redisService.getMap("HRY:EXCHANGE:PRODUCT");
		String value=map.get(coinCode);
	//	JSONObject jsonObject= JSONObject.fromObject(value);
	//	ExProductVo vo = (ExProductVo)JSONObject.toBean(jsonObject,ExProductVo.class);//
		ExProductVo vo = JSONArray.parseObject(value, ExProductVo.class);
		return vo.getPicturePath();
	}

	@Override
	public List<OrderBalanceVo> findBalanceBySql(Map<String, String> map) {
		List<OrderBalanceVo> list=((OrderDao)dao).findBalanceBySql(map);
		return list;
	}
}
