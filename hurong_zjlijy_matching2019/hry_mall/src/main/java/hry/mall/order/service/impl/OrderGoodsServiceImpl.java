/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:35:23 
 */
package hry.mall.order.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.core.thread.ThreadPool;
import hry.mall.goods.model.Goods;
import hry.mall.goods.model.GoodsSpec;
import hry.mall.goods.service.GoodsService;
import hry.mall.order.dao.OrderGoodsDao;
import hry.mall.order.model.Order;
import hry.mall.order.model.OrderGoods;
import hry.mall.order.service.OrderGoodsService;
import hry.thread.GrowthRunnable;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * <p> OrderGoodsService </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:35:23  
 */
@Service("orderGoodsService")
public class OrderGoodsServiceImpl extends BaseServiceImpl<OrderGoods, Long> implements OrderGoodsService {
	@Resource
	public GoodsService goodsService;
	
	@Resource(name="orderGoodsDao")
	@Override
	public void setDao(BaseDao<OrderGoods, Long> dao) {
		super.dao = dao;
	}

	@Override
	public OrderGoods saveOrderGoods(Goods goods, GoodsSpec spec, Order order, Integer count, BigDecimal price) {
		// TODO Auto-generated method stub
		OrderGoods orderGoods=new OrderGoods();
		orderGoods.setOrderId(order.getId());
		orderGoods.setOrderSn(order.getOrderSn());
		orderGoods.setGoodsId(goods.getId());
		orderGoods.setGoodsName(goods.getGoodsName());
		orderGoods.setBrandId(goods.getBrandId());
		orderGoods.setBrandName(goods.getBrandName());
		orderGoods.setGoodsSpecId(spec.getId());
		orderGoods.setActivityGoodsId(goods.getActivityGoodsId());
		String specInfo="";
		if(null!=spec.getSpecName() && !"".equals(spec.getSpecName()) && null!=spec.getSpecNameValue() &&!"".equals(spec.getSpecNameValue())){
			specInfo=this.specInfo(spec.getSpecName(), spec.getSpecNameValue());
		}
		orderGoods.setSpecInfo(specInfo);
		orderGoods.setGoodsPrice(spec.getSpecGoodsPrice());
		orderGoods.setGoodsNum(count);
		if(null!=goods.getGoodsImageMore() && !"".equals(goods.getGoodsImageMore())){
			String [] imgArr=goods.getGoodsImageMore().split(",");
			orderGoods.setGoodsImage(imgArr[0]);
		}
		orderGoods.setEvaluationStatus(Integer.valueOf("0"));
		orderGoods.setReturnStatus(Integer.valueOf("0"));
		//抢购或者团购商品，购买价格和标价会不一致
	/*	if(4==order.getOrderSort() || null!=goods.getActivityGoodsId() || !"".equals(goods.getActivityGoodsId())){
			orderGoods.setGoodsPayPrice(price);
		}else{
			orderGoods.setGoodsPayPrice(spec.getSpecGoodsPrice());
		}*/
		orderGoods.setGoodsPayPrice(price);
		orderGoods.setMemberId(order.getMemberId());
		orderGoods.setGoodsAllPrice(orderGoods.getGoodsPayPrice().multiply(new BigDecimal(count.toString())));
		orderGoods.setSpecGoodsSerial(spec.getSpecGoodsSerial());
		orderGoods.setIntegralCount(spec.getIntegralCount());
		orderGoods.setRemark(goods.getRemark());//备注信息
		this.save(orderGoods);
		return orderGoods;

  }

	@Override
	public String specInfo(String specName, String specNameValue) {
		// TODO Auto-generated method stub
		//取得“,”分隔后的字符串
		String [] sname=specName.split(",");
		String [] snamevalue=specNameValue.split(",");
		StringBuffer info=new StringBuffer();
		for(int i=0;i<sname.length;i++){
			String [] sarry=sname[i].split(":");
			String [] varry=snamevalue[i].split(":");
			String s1="";
			String s2="";
			if(i==(sname.length-1)){
				 s1=sarry[1].substring(1, sarry[1].length()-2);
				 s2=varry[1].substring(1, varry[1].length()-2);
			}else{
				 s1=sarry[1].substring(1, sarry[1].length()-1);
				 s2=varry[1].substring(1, varry[1].length()-1);
			}
			info.append(s1).append(":").append(s2);
			info.append("&nbsp;");
		}
		return info.toString();
	}

	@Override
	public List<OrderGoods> findBySql(Map<String, String> map) {
		// TODO Auto-generated method stub
		List<OrderGoods> list= ((OrderGoodsDao) dao).findBySql(map);
		return list;
	}

	@Override
	public Boolean isPromote(Long orderId,Long memberId) {
		// TODO Auto-generated method stub
		QueryFilter filter = new QueryFilter(OrderGoods.class);
		filter.addFilter("orderId=", orderId);
		List<OrderGoods>  list=this.find(filter);
		Boolean flag=false;
		for(OrderGoods orderGoods:list){
			Goods goods=goodsService.get(orderGoods.getGoodsId());
			//如果是进阶商品，则单独返成长值
			if(null!=goods.getSpecialType() && 4==goods.getSpecialType()){
				    String gtaskKey = "buyPromote";
	                Map<String, Object> map= new HashMap<>();
	                map.put("taskKey", gtaskKey);
	                map.put("memberId", memberId);
	                map.put("isShop", 2);
	                map.put("detailType", 1);
	                ThreadPool.exe(new GrowthRunnable(map));
	                flag=true;
	               break; 
			}
		}
		
		return flag;
	}
}