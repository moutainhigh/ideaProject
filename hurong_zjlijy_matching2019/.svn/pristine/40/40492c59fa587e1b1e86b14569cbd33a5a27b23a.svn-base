/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-03-19 15:25:34 
 */
package hry.mall.integral.service.impl;

import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.integral.model.*;
import hry.mall.integral.service.*;
import hry.mall.lend.person.model.AppPersonInfo;
import hry.mall.lend.person.service.AppPersonInfoService;
import hry.mall.member.commend.model.AppCommendUser;
import hry.mall.member.commend.service.AppCommendUserService;
import hry.util.QueryFilter;
import hry.util.SNUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> IntegralLevelService </p>
 * @author:         luyue
 * @Date :          2019-03-19 15:25:34  
 */
@Service("integralLevelService")
public class IntegralLevelServiceImpl extends BaseServiceImpl<IntegralLevel, Long> implements IntegralLevelService{
	
	@Resource(name="integralLevelDao")
	@Override
	public void setDao(BaseDao<IntegralLevel, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private CustomerIntegralService customerIntegralService;

	@Resource
	private AppCommendUserService appCommendUserService;
	@Resource
	private AppPersonInfoService appPersonInfoService;
	@Resource
	private LevelRecordService levelRecordService;
	@Resource
	private IntegralCommissionService integralCommissionService;
	@Resource
	private ShopMemberPerformanceService shopMemberPerformanceService;
	@Resource
	private ShopTeamLevelService shopTeamLevelService;
	@Resource
	private ShopMqExceptionLogService shopMqExceptionLogService;

	@Override
	public  BigDecimal calculationPrice(Long memberId, Long levelId){
		//查询用户账户
		QueryFilter queryFilter = new QueryFilter(CustomerIntegral.class);
		queryFilter.addFilter("memberId=", memberId);
		List<CustomerIntegral> customerIntegrals = customerIntegralService.find(queryFilter);
		//与用户一对一
		CustomerIntegral customerIntegral = customerIntegrals.get(0);
		BigDecimal price = new BigDecimal("0");
		//判断用户是否已有会员
		if (null != customerIntegral.getLevelId() ){
			IntegralLevel integralLevel = this.get(customerIntegral.getLevelId());

			price=integralLevel.getPrice();
		}
		IntegralLevel integralLevel = this.get(levelId);
		//计算差价
		price=integralLevel.getPrice().subtract(price);
		return  price;
	}

	@Override
	public Boolean giveRecommendQuota(Long memberId){
		//只返添加1次推广额度
		QueryFilter queryFilterToLevelRecord = new QueryFilter(LevelRecord.class);
		queryFilterToLevelRecord.addFilter("memberId=",memberId);
		Long count = levelRecordService.count(queryFilterToLevelRecord);
		//在之前已经为本次购买会员添加过记录了。。
		if(count<2L){
			//用户推荐关系
			QueryFilter queryFilter = new QueryFilter(AppCommendUser.class);
			queryFilter.addFilter("uid=",memberId);
			List<AppCommendUser> appCommendUsers = appCommendUserService.find(queryFilter);
			if(null != appCommendUsers && appCommendUsers.size()>0){
				AppCommendUser appCommendUser = appCommendUsers.get(0);
				//判断用户是否有上级
				if(null!=appCommendUser.getPid()){
					//查询用户账户
					QueryFilter queryFilter2 = new QueryFilter(CustomerIntegral.class);
					queryFilter2.addFilter("memberId=", appCommendUser.getPid());
					//与用户一对一
					CustomerIntegral customerIntegral = customerIntegralService.find(queryFilter2).get(0);

					//查询上级的等级
					IntegralLevel integralLevel = this.get(customerIntegral.getLevelId());
					//根据上级用户等级添加推广额度
					BigDecimal recommendQuota = customerIntegral.getRecommendQuota();//推广额度
					BigDecimal totalQuota = customerIntegral.getTotalQuota();//总额度
					if(null==recommendQuota){
						recommendQuota=new BigDecimal("0");
					}
					if(null==totalQuota){
						totalQuota=new BigDecimal("0");
					}
					customerIntegral.setRecommendQuota(recommendQuota.add(integralLevel.getRecommendQuota()));
					customerIntegral.setTotalQuota(totalQuota.add(integralLevel.getRecommendQuota()));
					customerIntegralService.update(customerIntegral);
				}
			}
		}
		return true;
	}




	@Override
	public JsonResult activateRebate(Long memberId, Long levelId, BigDecimal money, Integer series){
		JsonResult jsonResult = new JsonResult();
		List<Long> commissionIds = new ArrayList<>();
		try{
			//零元的不走
			int m = money.compareTo(new BigDecimal("0"));
			if (m!=1){
				return  jsonResult.setSuccess(true).setObj(commissionIds);
			}
			//用户推荐关系
			QueryFilter queryFilter = new QueryFilter(AppCommendUser.class);
			queryFilter.addFilter("uid=",memberId);
			List<AppCommendUser> appCommendUsers = appCommendUserService.find(queryFilter);
			AppCommendUser appCommendUser = appCommendUsers.get(0);
			String sid = appCommendUser.getSid();
			//判断是否有上级
			if (null != sid && !sid.equals("")){
				//获取上级数组
				String[] split = sid.split(",");
				//如果有推荐关系，那么下标为0的数据为空
				for (int i = 1; i <split.length ; i++) {
					String s = split[i];
					String[] split1 = s.split("-");
					Integer s1 = Integer.valueOf(split1[0]);
					Long pid = Long.valueOf(split1[1]);
					//判断当前等级是否还在范围内
					if(s1<=series){
						IntegralLevel integralLevel = this.get(levelId);
						//查询上级用户账户
						QueryFilter queryFilter2 = new QueryFilter(CustomerIntegral.class);
						queryFilter2.addFilter("memberId=", pid);
						//上级的账户信息（与用户一对一）
						CustomerIntegral customerIntegral = customerIntegralService.find(queryFilter2).get(0);
						//被推荐人的账号信息
						QueryFilter queryFilter1 = new QueryFilter(AppPersonInfo.class);
						queryFilter1.addFilter("customerId=",memberId);
						AppPersonInfo appPersonInfo = appPersonInfoService.find(queryFilter1).get(0);
						//判断是否为默认等级---查看等级升级记录
						if(this.isEffectiveLevel(pid)){
							//查询上级的等级
							IntegralLevel integralLevelP = this.get(customerIntegral.getLevelId());
							//生成返佣记录
							IntegralCommission integralCommission = new IntegralCommission();
							integralCommission.setMemberId(customerIntegral.getMemberId());
							integralCommission.setLevelId(customerIntegral.getLevelId());
							integralCommission.setLevelNumber(customerIntegral.getLevelNumber());
							integralCommission.setTeamId(customerIntegral.getTeamId());
							integralCommission.setTeamNumber(customerIntegral.getTeamNumber());
							integralCommission.setRecommendId(memberId);
							integralCommission.setRecommendPhone(appPersonInfo.getMobilePhone());
							integralCommission.setRecommendLevelId(levelId);
							integralCommission.setRecommendLevelNumber(integralLevel.getNumber());
							BigDecimal directReward=null;
							//判断发放的奖励类型。1为直推，其他为拓展
							if(i==1){
								integralCommission.setType("1");//1直推奖励，2拓展(作为二级推荐人奖励)，3服务奖励(组长的团奖)，4服务奖励(组长的团奖)，5静态分红
								//发放金额=上级等级的直推奖励*开通会员的金额
								directReward = money.multiply(integralLevelP.getDirectReward()).divide(new BigDecimal("100"));
								integralCommission.setRate(integralLevelP.getDirectReward());
								integralCommission.setRemark("用户id为："+memberId +"等级id为："+levelId+"  激活金额为："+money+"  上级id为："+pid+"  的直推奖励");
							}else if(i==2){
								integralCommission.setType("2");//1直推奖励，2拓展(作为二级推荐人奖励)，3服务奖励(组长的团奖)，4服务奖励(组长的团奖)，5静态分红
								//发放金额=上级等级的拓展奖励*开通会员的金额
								directReward = money.multiply(integralLevelP.getExpansionReward()).divide(new BigDecimal("100"));
								integralCommission.setRemark("用户id为："+memberId +"等级id为："+levelId+"  激活金额为："+money+"  上级id为："+pid+"  的2级拓展奖励");
								integralCommission.setRate(integralLevelP.getExpansionReward());
							}else if(i==3){
								integralCommission.setType("2");//1直推奖励，2拓展(作为二级推荐人奖励)，3服务奖励(组长的团奖)，4服务奖励(组长的团奖)，5静态分红
								//发放金额=上级等级的拓展奖励*开通会员的金额
								directReward = money.multiply(integralLevelP.getExpansionRewardThree()).divide(new BigDecimal("100"));
								integralCommission.setRemark("用户id为："+memberId +"等级id为："+levelId+"  激活金额为："+money+"  上级id为："+pid+"  的3级拓展奖励");
								integralCommission.setRate(integralLevelP.getExpansionReward());
							}
							integralCommission.setTransType("1");
							integralCommission.setCount(directReward);
							integralCommission.setMoney(money);
							String requestNo = SNUtil.create15();
							integralCommission.setNumber(requestNo);
							integralCommission.setStatus(1);
							integralCommissionService.save(integralCommission);
							commissionIds.add(integralCommission.getId());
							//发放电子劵
							Map<String, String> map1 = new HashMap<>();
							map1.put("id",customerIntegral.getId().toString());
							map1.put("type", CustomerIntegral.TYPE_ADD);
							map1.put("integralCount", directReward.toString());
							if(i==1){
								map1.put("tradingDetail", "分享链接");
							}else {
								map1.put("tradingDetail", "拓展链接");
							}
							map1.put("businessType", "6");//返利
							map1.put("requestNo", requestNo);//流水号
							boolean flag1 = customerIntegralService.updateInteger(map1);
							if (!flag1) {
								System.out.println("用户id为："+memberId +"等级id为："+levelId+"  激活金额为："+money+"  上级id为："+pid+"  的奖励发放失败");
								return jsonResult.setSuccess(false).setMsg("用户id为："+memberId +"等级id为："+levelId+"  激活金额为："+money+"  上级id为："+pid+"  的奖励发放失败");
							}
							//给3级内的上级添加团队业绩和团队收益（推荐奖励也算团队收益）
							QueryFilter queryFilter3 = new QueryFilter(ShopMemberPerformance.class);
							queryFilter3.addFilter("memberId=",pid);
							ShopMemberPerformance shopMemberPerformance = shopMemberPerformanceService.get(queryFilter3);
							shopMemberPerformance.setTeamAllPerformance(shopMemberPerformance.getTeamAllPerformance().add(money));//团队业绩
							shopMemberPerformance.setTeamIncome(shopMemberPerformance.getTeamIncome().add(directReward));//团队收益
							shopMemberPerformance.setIncome(shopMemberPerformance.getIncome().add(directReward));//个人收益
							shopMemberPerformanceService.update(shopMemberPerformance);
							//发放团队分红
							JsonResult jsonResult3 = integralCommissionService.grantTeamDividend(integralCommission);
							if(!jsonResult3.getSuccess()){
								ShopMqExceptionLog shopMqExceptionLog = new ShopMqExceptionLog();
								shopMqExceptionLog.setCustomerId(integralCommission.getMemberId().toString());
								shopMqExceptionLog.setParam("发放团队分红失败，参数是：integralCommissionId："+integralCommission.getId());
								shopMqExceptionLog.setRemark(jsonResult.getMsg());
								shopMqExceptionLog.setFunctionName("integralCommissionService.grantTeamDividend");
								shopMqExceptionLogService.save(shopMqExceptionLog);
							}
						}
					}else if(i<=10){
						//给10级内的上级添加团队业绩
						QueryFilter queryFilter3 = new QueryFilter(ShopMemberPerformance.class);
						queryFilter3.addFilter("memberId=",pid);
						ShopMemberPerformance shopMemberPerformance = shopMemberPerformanceService.get(queryFilter3);
						shopMemberPerformance.setTeamAllPerformance(shopMemberPerformance.getTeamAllPerformance().add(money));//团队业绩
						shopMemberPerformanceService.update(shopMemberPerformance);

					}
					//维护上级id
					JsonResult jsonResult1 = shopTeamLevelService.updateUserTeamLevel(pid);
					if(!jsonResult1.getSuccess()){
						ShopMqExceptionLog shopMqExceptionLog = new ShopMqExceptionLog();
						shopMqExceptionLog.setCustomerId(pid.toString());
						shopMqExceptionLog.setParam("发放团队分红失败，参数是：pid："+pid);
						shopMqExceptionLog.setRemark(jsonResult.getMsg());
						shopMqExceptionLog.setFunctionName("shopTeamLevelService.updateUserTeamLevel");
						shopMqExceptionLogService.save(shopMqExceptionLog);
					}
				}
			}
			return  jsonResult.setSuccess(true).setObj(commissionIds);
		}catch (Exception e){
			e.printStackTrace();
			return  jsonResult.setSuccess(false).setMsg(e.toString());
		}
	}


	@Override
	public Boolean  isEffectiveLevel(Long memberId){
		//查询用户账户
		QueryFilter queryFilter = new QueryFilter(CustomerIntegral.class);
		queryFilter.addFilter("memberId=", memberId);
		List<CustomerIntegral> customerIntegrals = customerIntegralService.find(queryFilter);
		//与用户一对一
		CustomerIntegral customerIntegral = customerIntegrals.get(0);
		QueryFilter queryFilter1 = new QueryFilter(IntegralLevel.class);
		queryFilter1.setOrderby("number asc");
		List<IntegralLevel> list=this.find(queryFilter1);
		if(null != list && list.size()>0){
			IntegralLevel integralLevel1 = list.get(0);
			//判断用户是否已有非默认会员
			if (null != customerIntegral.getLevelId()&& customerIntegral.getLevelId()!=integralLevel1.getId()){
				return  true;
			}
		}
		return  false;
	}



	@Override
	public IntegralLevel getLevelByAccount(BigDecimal account){
		if(null==account){
			return null;
		}
		QueryFilter queryFilter = new QueryFilter(IntegralLevel.class);
		queryFilter.addFilter("number>",2);//舍弃默认的和入门的
		queryFilter.setOrderby("number desc");//降序
		List<IntegralLevel> integralLevels = this.find(queryFilter);
		if (null!=integralLevels  && integralLevels.size()>0){
			for (IntegralLevel integralLevel:integralLevels) {
				int i = account.compareTo(integralLevel.getLowestPrice());//最低
				int i1 = account.compareTo(integralLevel.getHighestPrice());//最高
				if(i>-1 && i1 <1 ){
					return integralLevel;
				}
			}
		}

		return  null;
	}


}
