/**
 * Copyright:   
 * @author:      houzhen
 * @version:     V1.0 
 * @Date:        2019-06-26 21:03:57 
 */
package hry.mall.integral.service.impl;

import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.integral.dao.ShopMemberPerformanceDao;
import hry.mall.integral.model.CustomerIntegral;
import hry.mall.integral.model.ShopMemberPerformance;
import hry.mall.integral.service.IntegralCommissionService;
import hry.mall.integral.service.LevelRecordService;
import hry.mall.integral.service.ShopMemberPerformanceService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p> ShopMemberPerformanceService </p>
 * @author:         houzhen
 * @Date :          2019-06-26 21:03:57  
 */
@Service("shopMemberPerformanceService")
public class ShopMemberPerformanceServiceImpl extends BaseServiceImpl<ShopMemberPerformance, Long> implements ShopMemberPerformanceService {
	
	@Resource(name="shopMemberPerformanceDao")
	@Override
	public void setDao(BaseDao<ShopMemberPerformance, Long> dao) {
		super.dao = dao;
	}
	@Resource
	private IntegralCommissionService integralCommissionService;

	@Resource
	private LevelRecordService levelRecordService;



	/**
	 * 维护用户业绩
	 * @param customerIntegral
	 * @return
	 */
	@Override
	public JsonResult updateMemberPerformance(CustomerIntegral customerIntegral){
		ShopMemberPerformance shopMemberPerformance;
		Long memberId = 0L;
		try {
			memberId=customerIntegral.getMemberId();
//			//个人新增
//			BigDecimal userNewPerformance = integralCommissionService.getUserNewPerformance(memberId);
//			if (null == userNewPerformance){
//				userNewPerformance=new BigDecimal("0");
//			}
			//个人业绩
			BigDecimal userPerformance = levelRecordService.getUserPerformance(memberId);
			if (null == userPerformance){
				userPerformance=new BigDecimal("0");
			}
//			//团队新增
//			BigDecimal userNewTeamPerformance = integralCommissionService.getUserNewTeamPerformance(memberId,10);
//			if (null == userNewTeamPerformance){
//				userNewTeamPerformance=new BigDecimal("0");
//			}
			//该用户的团队业绩  团队为10级
			BigDecimal userTeamPerformance = levelRecordService.getUserTeamPerformance(memberId, 10);
			if (null == userTeamPerformance){
				userTeamPerformance=new BigDecimal("0");
			}
			BigDecimal add = userPerformance.add(userTeamPerformance);

			QueryFilter queryFilter = new QueryFilter(ShopMemberPerformance.class);
			queryFilter.addFilter("memberId=",memberId);
			List<ShopMemberPerformance> shopMemberPerformances = this.find(queryFilter);
			if (null == shopMemberPerformances || shopMemberPerformances.size()==0){
				shopMemberPerformance=new ShopMemberPerformance();
			}else {
				shopMemberPerformance=shopMemberPerformances.get(0);
			}
			shopMemberPerformance.setMemberId(memberId);
			shopMemberPerformance.setNewPerformance(new BigDecimal("0"));//个人昨日新增
			shopMemberPerformance.setAllPerformance(userPerformance);//个人总业绩
			shopMemberPerformance.setTeamNewPerformance(new BigDecimal("0"));//团队新增
			shopMemberPerformance.setTeamAllPerformance(add);//包含自身的团队业绩
			if (null == shopMemberPerformances || shopMemberPerformances.size()==0){
				this.save(shopMemberPerformance);
			}else {
				this.update(shopMemberPerformance);
			}
			System.out.println("用户id："+memberId+"的团队总业绩为："+add);
			return  new JsonResult().setSuccess(true).setMsg("维护业绩成功");
		}catch (Exception e){
			e.printStackTrace();
			return  new JsonResult().setSuccess(false).setMsg("维护业绩异常");
		}
	}


	/**
	 * 获取未生成业绩账户的用户集合
	 * */
	public List<Long> findNoMemberPerformanceUser(){
		return ((ShopMemberPerformanceDao)dao).findNoMemberPerformanceUser();
	}

}
