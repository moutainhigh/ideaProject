/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-31 14:33:31 
 */
package hry.mall.integral.service.impl;

import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.integral.dao.IntegralCommissionDao;
import hry.mall.integral.model.CustomerIntegral;
import hry.mall.integral.model.IntegralCommission;
import hry.mall.integral.model.ShopMemberPerformance;
import hry.mall.integral.model.ShopTeamDividendConfig;
import hry.mall.integral.service.*;
import hry.mall.lend.person.model.AppPersonInfo;
import hry.mall.lend.person.service.AppPersonInfoService;
import hry.mall.member.commend.model.AppCommendUser;
import hry.mall.member.commend.service.AppCommendUserService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> IntegralCommissionService </p>
 * @author:         luyue
 * @Date :          2019-05-31 14:33:31  
 */
@Service("integralCommissionService")
public class IntegralCommissionServiceImpl extends BaseServiceImpl<IntegralCommission, Long> implements IntegralCommissionService {

	@Resource(name="integralCommissionDao")
	@Override
	public void setDao(BaseDao<IntegralCommission, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private AppCommendUserService appCommendUserService;
	@Resource
	private CustomerIntegralService customerIntegralService;

	@Resource
	private IntegralCoinchangeService integralCoinchangeService;

	@Resource
	private ShopTeamDividendConfigService shopTeamDividendConfigService;

	@Resource
	private AppPersonInfoService appPersonInfoService;
	@Resource
	private ShopMemberPerformanceService shopMemberPerformanceService;

	@Override
	public List<IntegralCommission> findRecord(Long memberId , Integer status) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("memberId",memberId);
		map.put("status",status);
		List<IntegralCommission> list=((IntegralCommissionDao)dao).findRecord(map);
		return list;
	}


	@Override
	public JsonResult grantTeamDividend(IntegralCommission integralCommission){
		JsonResult jsonResult = new JsonResult();
		try {
			Long memberId = integralCommission.getMemberId();
			System.out.println("用户id为："+memberId);
			BigDecimal count = integralCommission.getCount();
			Integer teamNumber = integralCommission.getTeamNumber();
			if(null !=teamNumber && teamNumber==2){
				//服务中心不向上返利
				return jsonResult.setSuccess(true);
			}

			// 查询用户推荐信息
			QueryFilter filter = new QueryFilter(AppCommendUser.class);
			filter.addFilter("uid=", memberId);
			AppCommendUser appCommendUser = appCommendUserService.get(filter);
			// 获取父级树
			String sid = appCommendUser.getSid();
			if (sid != null && !"".equals(sid)) {
				String[] pidList = sid.split(",");
				// 返利开始
				// 根据父级数存储的结构（,1-44,2-43,3-42,） 则数组从1开始
				for (int i=1; i<pidList.length; i++) {
					// 获取父级id
					String pid = pidList[i].split("-")[1];
					// 获取用有效期用户等级身份信息(注：1、组长 2、服务中心；只有是组长或者是服务中心才能获取对应的返利)
					QueryFilter filter1 = new QueryFilter(CustomerIntegral.class);
					filter1.addFilter("memberId=",pid);
					CustomerIntegral customerIntegral = customerIntegralService.get(filter1);
					if (customerIntegral != null) {
						//非团队等级不返。不比下级等级高不返
						if(null==customerIntegral.getTeamNumber() || customerIntegral.getTeamNumber()<0){
							continue;
						}
						// 判断父级用户是否为服务中心
						if (customerIntegral.getTeamNumber() == 2) {
							// 返利实现
							impRollRebate(customerIntegral,memberId.toString(),"4",count);
							// 根据需求当父节点有服务中心时，则停止该节点向上的返佣
							break;
						}
						// 判断父级用户是否为组长
						if (customerIntegral.getTeamNumber() == 1) {
							// 返利实现
							impRollRebate(customerIntegral,memberId.toString(),"3",count);
						}

					} else {
						System.out.println("#####----:+"+customerIntegral.getMemberId()+"该用户分红配置不存在或者会员过期！");
					}
					// 返利向上返10级
					if (i==10) {
						break;
					}
				}
			}
			return jsonResult.setSuccess(true);
		}catch (Exception e){
			e.printStackTrace();
			return jsonResult.setSuccess(false).setMsg(e.toString());
		}
	}


	/**
	 * 返利实现
	 * @param customerIntegral 父级用户账户对象
	 * @param memberId 用户id
	 * @param rebateType 返利类别 3：服务奖励(组长的团奖) 4：服务奖励(服务中心的团奖)
	 * @param integralCount 优惠券数量
	 * */
	private JsonResult impRollRebate(CustomerIntegral customerIntegral, String memberId, String rebateType, BigDecimal integralCount){
		// 获取对应用户等级的分红比例
		QueryFilter filter = new QueryFilter(ShopTeamDividendConfig.class);
		filter.addFilter("integralLevelId=", customerIntegral.getLevelId());
		filter.addFilter("teamLevelId=", customerIntegral.getTeamId());
		ShopTeamDividendConfig config = shopTeamDividendConfigService.get(filter);
		if (config != null) {
			// 分红比例
			BigDecimal ratio = config.getDividendRatio();
			// 所分红的电子卷
			BigDecimal rollCount = integralCount.multiply(ratio).multiply(new BigDecimal("0.01"));

			// 获取用户等级
			QueryFilter filter1 = new QueryFilter(CustomerIntegral.class);
			filter1.addFilter("memberId=", memberId);
			CustomerIntegral sonIntegral = customerIntegralService.get(filter1);
			// 获取用户基础信息
			QueryFilter qfAppPersonInfo = new QueryFilter(AppPersonInfo.class);
			qfAppPersonInfo.addFilter("customerId=",memberId);
			AppPersonInfo appPersonInfo = appPersonInfoService.get(qfAppPersonInfo);

			// 保存返利明细记录
			String number = integralCoinchangeService.createNumber();
			IntegralCommission comm = new IntegralCommission();
			comm.setMemberId(customerIntegral.getMemberId());
			comm.setLevelId(customerIntegral.getLevelId());
			comm.setLevelNumber(customerIntegral.getLevelNumber());
			comm.setTeamId(customerIntegral.getTeamId());
			comm.setTeamNumber(customerIntegral.getTeamNumber());
			comm.setRecommendId(Long.parseLong(memberId));
			comm.setRecommendPhone(appPersonInfo.getMobilePhone());
			comm.setRecommendLevelId(sonIntegral.getLevelId());
			comm.setRecommendLevelNumber(sonIntegral.getLevelNumber());
			comm.setType(rebateType);
			comm.setTransType("1");
			comm.setCount(rollCount);
			comm.setRate(ratio);
			comm.setNumber(number);
			comm.setStatus(1);
			comm.setMoney(integralCount);
			comm.setRemark("用户："+memberId+"给上级用户："+customerIntegral.getMemberId()+"发放推荐奖励的团队分红");
			this.save(comm);
			System.out.println("#####----:保存返利明细记录成功！");

			//维护业绩
			QueryFilter queryFilter3 = new QueryFilter(ShopMemberPerformance.class);
			queryFilter3.addFilter("memberId=",customerIntegral.getMemberId());
			ShopMemberPerformance shopMemberPerformance = shopMemberPerformanceService.get(queryFilter3);
			shopMemberPerformance.setTeamIncome(shopMemberPerformance.getTeamIncome().add(rollCount));//团队收益
			shopMemberPerformanceService.update(shopMemberPerformance);

			// 操作积分账户
			Map<String,String> map = new HashMap<>();
			map.put("id", customerIntegral.getId().toString());
			map.put("type", CustomerIntegral.TYPE_ADD);
			map.put("integralCount", rollCount.toString());
			if("3".equals(rebateType)){
				map.put("tradingDetail", "福利链接");
			}else {
				map.put("tradingDetail", "服务链接");
			}
			map.put("requestNo", number);
			map.put("businessType", "6");
			boolean flag1 = customerIntegralService.updateInteger(map);
			if(flag1){
				System.out.println("#####----:操作积分账户成功！");
			}
		} else {
			System.out.println("#####----:+"+customerIntegral.getMemberId()+"该用户分红配置不存在！");
		}
		return new JsonResult(true).setMsg("操作明细成功！");
	}




}
