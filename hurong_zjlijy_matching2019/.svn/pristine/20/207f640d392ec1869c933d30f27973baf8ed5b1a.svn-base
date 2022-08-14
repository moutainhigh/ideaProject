/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-07 11:10:09 
 */
package hry.mall.retailstore.service.impl;

import hry.bean.JsonResult;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.order.model.Order;
import hry.mall.order.service.OrderService;
import hry.mall.retailstore.model.*;
import hry.mall.retailstore.service.*;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.User;
import hry.util.QueryFilter;
import hry.util.SpringUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> GrowthDetailService </p>
 * @author:         luyue
 * @Date :          2019-05-07 11:10:09  
 */
@Service("growthDetailService")
public class GrowthDetailServiceImpl extends BaseServiceImpl<GrowthDetail, Long> implements GrowthDetailService {
	@Resource
	public GrowthTaskService growthTaskService;
	@Resource
	public GrowthBuytaskService growthBuytaskService;
	@Resource
	public OrderService orderService;
	@Resource
	public CustomerGrowthService customerGrowthService;
	@Resource
	public MemberLevelService memberLevelService;
	@Resource
	public CutomerStoreService cutomerStoreService;
	
	@Resource(name="growthDetailDao")
	@Override
	public void setDao(BaseDao<GrowthDetail, Long> dao) {
		super.dao = dao;
	}

	@Override
	public JsonResult handleGrowth(Map<String, Object> map) {
		// TODO Auto-generated method stub
		JsonResult jsonObject = new JsonResult();
	
		String memberId = map.get("memberId").toString();//用户id
		String isShop = map.get("isShop").toString();//是否购物任务
		String detailType = map.get("detailType").toString();//加减类型，1增加，2减少，3冻结
		
		QueryFilter  fiter1=new QueryFilter(CustomerGrowth.class);
		fiter1.addFilter("memberId=", memberId);
		CustomerGrowth customerGrowth=customerGrowthService.get(fiter1);
		if(null==customerGrowth){
			jsonObject.setSuccess(false).setMsg("无成长值账户");
			return jsonObject;
		}
		map.put("customerGrowthId", customerGrowth.getId());
		
		BigDecimal count=new BigDecimal("0");
		
		//2为非购物任务
		if("2".equals(isShop)){
			String taskKey = map.get("taskKey").toString();//成长值任务key
			//1、查询该任务
			QueryFilter queryTask =  new QueryFilter(GrowthTask.class);
			queryTask.addFilter("taskKey=",taskKey);
			GrowthTask task = growthTaskService.get(queryTask);
			if(null!=task && !"".equals(task)){
				//2、任务开启
				if(1==task.getTaskStatus()){
					//3、是否是单次任务
					if(0==task.getTaskType()){
						map.put("taskId", task.getId());
						map.put("taskName", task.getTaskName());
						map.put("count", task.getCount());
						this.handleGrowthDetail(map);
					}
				}
			}
			
		}else if("1".equals(isShop)){//购物返成长值
			String orderId = map.get("orderId").toString();//成长值任务key
			QueryFilter queryTask =  new QueryFilter(GrowthBuytask.class);
			Order order=orderService.get(Long.valueOf(orderId));
			BigDecimal orderAmount=order.getOrderAmount();
			queryTask.addFilter("minMoney<=",orderAmount);
			queryTask.addFilter("maxMoney>=",orderAmount);
			queryTask.setOrderby("id desc");
			List<GrowthBuytask> list=growthBuytaskService.find(queryTask);
			if(null!=list && list.size()>0){
				GrowthBuytask task=list.get(0);
				map.put("count", task.getCount());
				map.put("taskId", task.getId());
				map.put("taskName", "购物返成长值");
				this.handleGrowthDetail(map);
			}
		}
		return jsonObject.setSuccess(true).setMsg("成功");
	}

	@Override
	public Boolean handleGrowthDetail(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String memberId = map.get("memberId").toString();//用户id
		String isShop = map.get("isShop").toString();//是否购物任务
		String detailType = map.get("detailType").toString();//加减类型，1增加，2减少，3冻结
		String customerGrowthId = map.get("customerGrowthId").toString();//成长值账户id
		String count = map.get("count").toString();//成长值数量
		String taskId = map.get("taskId").toString();//任务id
		BigDecimal dcount=new BigDecimal(count);
		String taskName = map.get("taskName").toString();//任务名称
		CustomerGrowth customerGrowth=customerGrowthService.get(Long.valueOf(customerGrowthId));
		//1、保存明细
		GrowthDetail detail=new GrowthDetail();
		detail.setMemberId(Long.valueOf(memberId));
		detail.setAccountId(customerGrowth.getId());
		detail.setRewardId(Long.valueOf(taskId));
		detail.setRewardType(Integer.valueOf(isShop));
		detail.setDetailType(Integer.valueOf(detailType));
		detail.setCount(new BigDecimal(count));
		detail.setBalanceCount(customerGrowth.getTotalGrowth());
		detail.setStatus(3);
		detail.setName(taskName);
		detail.setNumber(this.createNumber());
		if("1".equals(isShop)){
			String orderId = map.get("orderId").toString();//订单id
			Order order=orderService.get(Long.valueOf(orderId));
			detail.setOrderId(Long.valueOf(orderId));
			detail.setOrderSn(order.getOrderSn());
		}
		this.save(detail);
		//2、更改账户
		if("1".equals(detailType)){
			customerGrowth.setTotalGrowth(customerGrowth.getTotalGrowth().add(dcount));
			customerGrowth.setAvailablelGrowth(customerGrowth.getAvailablelGrowth().add(dcount));
		}
		else if("2".equals(detailType)){//减少
			customerGrowth.setTotalGrowth(customerGrowth.getTotalGrowth().subtract(dcount));
			customerGrowth.setAvailablelGrowth(customerGrowth.getAvailablelGrowth().subtract(dcount));
			
		}else if("3".equals(detailType)){//冻结
			customerGrowth.setFreezeGrowth(customerGrowth.getFreezeGrowth().add(dcount));
			customerGrowth.setAvailablelGrowth(customerGrowth.getAvailablelGrowth().subtract(dcount));
		}

		//3、判断是否升级
		this.promote(customerGrowth);
		customerGrowthService.update(customerGrowth);
		return true;
	}

	@Override
	public String createNumber() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmsss");
        String newDate=sdf.format(new Date());
         int i = (int)(Math.random()*900 + 100);
        String myStr = Integer.toString(i);
		return (newDate+myStr);
	}

	@Override
	public CustomerGrowth promote(CustomerGrowth customerGrowth) {
		// TODO Auto-generated method stub
		MemberLevel memberLevel=memberLevelService.get(customerGrowth.getId());
		//查询比当前成长值更高的一级别
		QueryFilter filter =  new QueryFilter(MemberLevel.class);
		filter.addFilter("minGrowth>",memberLevel.getMinGrowth());
		filter.setOrderby("minGrowth  asc");
		List<MemberLevel> list=memberLevelService.find(filter);
		if(null!=list && list.size()>0){
			MemberLevel  level=list.get(0);
			//判断现有积分是否满足更高一等级
			if(customerGrowth.getTotalGrowth().compareTo(level.getMinGrowth())>=0){
				customerGrowth.setLevelId(level.getId());
				customerGrowth.setLevelName(level.getName());
				//用户不具有店主权限，并且升级后的等级具有店主权限
				if(customerGrowth.getIsStore()!=1 && 1==level.getIsStore()){
					QueryFilter filter1=new QueryFilter(CutomerStore.class);
					filter1.addFilter("memberId=", customerGrowth.getMemberId());
					List<CutomerStore> list1=cutomerStoreService.find(filter1);
					//判断是否已有店铺信息
					if(null!=list1 && list1.size()>0){
					}else{
						// 没有店铺信息则新建店铺信息
						CutomerStore store=new CutomerStore();
						store.setMemberId(customerGrowth.getMemberId());
						RemoteManageService remoteManageService = SpringUtil.getBean("remoteManageService");
						User user=remoteManageService.findUserById(customerGrowth.getMemberId());
						store.setImage(user.getUserFace());
						cutomerStoreService.save(store);
					}
					customerGrowth.setIsStore(Short.valueOf("1"));
				}
			}
				
			}
		return customerGrowth;
	}
	

}
