/**
 * Copyright:
 * @author:      lzy
 * @version:     V1.0
 * @Date:        2019-04-17 14:01:32
 */
package hry.klg.level.service.impl;

import com.github.pagehelper.Page;
import hry.bean.JsonResult;
import hry.customer.user.model.AppCustomer;
import hry.klg.level.dao.KlgCustomerLevelDao;
import hry.klg.level.model.*;
import hry.klg.level.service.*;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.klg.remote.RemoteKlgService;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import hry.util.StringUtil;
import org.springframework.stereotype.Service;
import util.BigDecimalUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p> KlgCustomerLevelService </p>
 * @author:         lzy
 * @Date :          2019-04-17 14:01:32
 */
@Service("klgCustomerLevelService")
public class KlgCustomerLevelServiceImpl extends BaseServiceImpl<KlgCustomerLevel, Long> implements KlgCustomerLevelService {
	//升级
	private final static  String UPGRADE="UPGRADE:";

	private static Object rlock = new Object();

	@Resource
	private RedisService redisService;

	@Resource
	private KlgLevelConfigService klgLevelConfigService;

	@Resource
	private KlgUpgradeRecordService klgUpgradeRecordService;
	@Resource
	private KlgGradationService klgGradationService;
	@Resource
	private KlgTeamlevelService klgTeamlevelService;
	@Resource
	private MessageProducer messageProducer;

	@Resource(name="klgCustomerLevelDao")
	@Override
	public void setDao(BaseDao<KlgCustomerLevel, Long> dao) {
		super.dao = dao;
	}

	public void addUserLevel(AppCustomer customer){
		KlgCustomerLevel customerLevel=new KlgCustomerLevel();//开头用户等级
		customerLevel.setCustomerId(customer.getId());
		QueryFilter queryFilter=new QueryFilter(KlgLevelConfig.class);
		queryFilter.setOrderby("sort asc");
		List<KlgLevelConfig> levelConfigs=klgLevelConfigService.find(queryFilter);
		if(levelConfigs!=null&&levelConfigs.size()>0){
			KlgLevelConfig levelConfig=levelConfigs.get(0);
			customerLevel.setSort(levelConfig.getSort());//等级排序
			customerLevel.setLevelId(levelConfig.getId());//等级id
			customerLevel.setLevelName(levelConfig.getLevelName());//等级名称
			customerLevel.setPointAlgebra(levelConfig.getPointAlgebra());//见点代数
		}
		super.save(customerLevel);
	}

	/**
	 * 根据用户获取当前等级配置
	 * @param customerId
	 * @return
	 */
	@Override
	public KlgCustomerLevel getLevelConfigByCustomerId(Long customerId) {
		KlgCustomerLevel customerLevel=super.get(new QueryFilter(KlgCustomerLevel.class).addFilter("customerId=",customerId));
		if(customerLevel!=null){
			if(customerLevel.getLevelId()==null){
				QueryFilter queryFilter=new QueryFilter(KlgLevelConfig.class);
				queryFilter.setOrderby("sort asc");
				List<KlgLevelConfig> levelConfigs=klgLevelConfigService.find(queryFilter);
				if(levelConfigs!=null&&levelConfigs.size()>0){
					KlgLevelConfig levelConfig=levelConfigs.get(0);
					customerLevel.setSort(levelConfig.getSort());//等级排序
					customerLevel.setLevelId(levelConfig.getId());//等级id
					customerLevel.setLevelName(levelConfig.getLevelName());//等级名称
					customerLevel.setPointAlgebra(levelConfig.getPointAlgebra());//见点代数
				}
				super.update(customerLevel);
			}
		}
		return customerLevel;
	}

	/**
	 * 减少用户奖励额度
	 * @param customerId
	 * @param rewardNum
	 * @return
	 */
	@Override
	public JsonResult reduceReward(Long customerId,BigDecimal rewardNum) {
		System.out.println(customerId+"=========="+rewardNum);
		KlgCustomerLevel customerLevel=new KlgCustomerLevel();
		customerLevel.setCustomerId(customerId);
		customerLevel.setRewardNum(rewardNum.multiply(new BigDecimal(-1)));
		int count=((KlgCustomerLevelDao)dao).customerRewardAdd(customerLevel);
		if(count<0){
			return new JsonResult().setSuccess(false).setMsg("error");
		}
		return new JsonResult().setSuccess(true).setMsg("caozuochenggong");//操作成功
	}
	/**
	 *预约成功获得奖励额度
	 */
	@Override
	public void buyRewardQuotaAdd(Long customerId, BigDecimal buyNum) {
		KlgLevelConfig config=klgLevelConfigService.getLevelConfigByCustomerId(customerId);
		if(config!=null){
			BigDecimal bonusMultiple=config.getBonusMultiple();//奖金倍数
			BigDecimal rewardNum= BigDecimalUtil.bigDecimalScale8(buyNum.multiply(bonusMultiple));//奖金倍数*预约金额
			KlgCustomerLevel customerLevel=new KlgCustomerLevel();
			customerLevel.setCustomerId(customerId);
			customerLevel.setRewardNum(rewardNum);
			int count=((KlgCustomerLevelDao)dao).customerRewardAdd(customerLevel);
			if(count<1){
				System.out.println("预约成功获得奖励额度 error"+customerId+"==="+buyNum);
			}
		}
	}

	///预约获得20% 奖励额度
	@Override
	public void bookingRewardQuotaAdd(Long customerId, BigDecimal buyNum) {
		RemoteKlgService remoteKlgService=SpringUtil.getBean("remoteKlgService");
		String marginRatio=(String)remoteKlgService.getPlatformRule1sConfig("marginRatio");//保证金比例
		BigDecimal ratio=new BigDecimal(marginRatio).divide(new BigDecimal(100));
		buyRewardQuotaAdd(customerId,buyNum.multiply(ratio));
	}

	@Override
	public JsonResult upgradeUserLevel(Long leveId, Long customerId) {
		try {
			// 更改用户等级
			boolean flag=redisService.lock(UPGRADE+customerId);
			while (!flag){
				Thread.sleep(1000);
				flag=redisService.lock(UPGRADE+customerId);
			}
			System.out.println("升级升级升级升级升级升级升级");
			KlgLevelConfig  klgLevelConfig=klgLevelConfigService.getLevelConfigByLevelId(leveId);
			KlgCustomerLevel customerLevel =getLevelConfigByCustomerId(customerId);

			//获取用户升级得级差
			BigDecimal nowGradation=getMyUpgradeGradation(customerId);
			if(klgLevelConfig!=null){
				KlgUpgradeRecord upgradeRecord=new KlgUpgradeRecord();//升级记录
				upgradeRecord.setCustomerId(customerId);
				upgradeRecord.setOldLevel(customerLevel.getLevelName());
				upgradeRecord.setOldLevelId(customerLevel.getLevelId());
				upgradeRecord.setOldGradation(customerLevel.getGradation());
				upgradeRecord.setOldPointAlgebra(customerLevel.getPointAlgebra());

				upgradeRecord.setNewLevel(klgLevelConfig.getLevelName());
				upgradeRecord.setNewLevelId(klgLevelConfig.getId());
				upgradeRecord.setNowGradation(nowGradation);//
				upgradeRecord.setType("1");//升级
				upgradeRecord.setNowPointAlgebra(klgLevelConfig.getPointAlgebra());
				customerLevel.setSort(klgLevelConfig.getSort());//等级排序
				customerLevel.setLevelId(klgLevelConfig.getId());//等级id
				customerLevel.setGradation(nowGradation);//级差
				customerLevel.setLevelName(klgLevelConfig.getLevelName());//等级名称
				customerLevel.setPointAlgebra(klgLevelConfig.getPointAlgebra());//见点代数
				customerLevel.setNowSort(klgLevelConfig.getSort());//当前等级排序
				super.update(customerLevel);//升级
				klgUpgradeRecordService.save(upgradeRecord);
				messageProducer.toKlgGradation(customerId.toString());//触发上级升级级差
			}
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult().setSuccess(false).setMsg("upError");//升级失败
		}finally {
			redisService.unLock(UPGRADE+customerId);
		}
		return new JsonResult().setSuccess(true).setMsg("success");
	}

	/**
	 * 重置奖励额度
	 * 	//重置用户等级 [用于发级差奖/见点奖励]
	 */
	@Override
	public void resetRewardQuota() {
		synchronized (rlock) {
			RemoteKlgService klgService= SpringUtil.getBean("remoteKlgService");
			try {
				String limitday= (String) klgService.getPlatformRule1sConfig("downgradeDays");//间隔
				System.out.println("定时重置"+limitday+"天未预约的奖励额度");
				if(StringUtil.isNull(limitday)){
					List<Long> customerIds=((KlgCustomerLevelDao)dao).resetRewardQuota(limitday);
					if(customerIds!=null&&customerIds.size()>0){
						for (Long l:customerIds) {
							messageProducer.toKlgGradation(l.toString());
						}
						((KlgCustomerLevelDao)dao).updateResetRewardQuota(limitday);
					}
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	//用户下级星级用户
	@Override
	public Integer sumStarCount(Long customerId, Integer level) {
		List<KlgCustomerLevel> levels=((KlgCustomerLevelDao)dao).sumStarCount(customerId,level);
		return levels==null?0:levels.size();
	}

	//修改用户级差
	@Override
	public void updateCustomerRewardConfig(Long customerId) {
		//上级星级用户
		List<KlgTeamlevel>	list=klgTeamlevelService.getSuperiorLeveConfig(customerId);
		if(list!=null&&list.size()>0){
			for (KlgTeamlevel teamlevel:list) {
				Long parentId=teamlevel.getParentId();//我上级的用户ID
				BigDecimal gradation=getMyUpgradeGradation(parentId);
				KlgCustomerLevel level=new KlgCustomerLevel();
				level.setCustomerId(parentId);
				level.setGradation(gradation);//级差
				((KlgCustomerLevelDao)dao).customerRewardAdd(level);
			}
		}
	}
	//获取用户升级得级差
	private BigDecimal getMyUpgradeGradation(Long customerId){
		BigDecimal gradation=BigDecimal.ZERO;
		//推荐总人数  星级用户
		List<KlgLevelCount> klgLevelCounts=klgTeamlevelService.countSubordinateByCustomer(customerId);
		QueryFilter queryFilter=new QueryFilter(KlgGradation.class);
		queryFilter.setOrderby("gradation desc");
		List<KlgGradation> klgGradations=klgGradationService.find(queryFilter);//所有的级差
		if(klgGradations!=null&&klgGradations.size()>0){

			for (KlgGradation gradationCof:klgGradations) {
					Integer minLevelSort=gradationCof.getMinLevelSort();//最小会员等级
					Integer levelNum=gradationCof.getLevelNum();//星级个数
					int co=0;//
					for (KlgLevelCount levelCount:klgLevelCounts) {
						Integer count=levelCount.getCount();//个数
						Integer sort=levelCount.getSort()==null?0:levelCount.getSort();//等级排序
						if(sort>=minLevelSort){
							count=count+co;
							if(count>=levelNum){
								return gradationCof.getGradation();
							}
							co=count;
						}
					}
				}
		}
		return gradation;
	}


}
