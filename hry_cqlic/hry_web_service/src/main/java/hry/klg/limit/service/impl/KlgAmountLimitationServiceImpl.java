/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-19 17:06:43 
 */
package hry.klg.limit.service.impl;

import hry.calculate.util.DateUtil;
import hry.klg.limit.dao.KlgAmountLimitationDao;
import hry.klg.limit.model.AmountLimitType;
import hry.klg.limit.model.KlgAmountLimitation;
import hry.klg.limit.service.KlgAmountLimitationService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p> KlgAmountLimitationService </p>
 * @author:         lzy
 * @Date :          2019-04-19 17:06:43  
 */
@Service("klgAmountLimitationService")
public class KlgAmountLimitationServiceImpl extends BaseServiceImpl<KlgAmountLimitation, Long> implements KlgAmountLimitationService{

	private static  final  String endDate="23:59:59";

	@Resource(name="klgAmountLimitationDao")
	@Override
	public void setDao(BaseDao<KlgAmountLimitation, Long> dao) {
		super.dao = dao;
	}

	@Override
	public boolean isCheckNum(AmountLimitType limitType, BigDecimal money) {
		String type=limitType.getKey();//类型 1抢单 2新人3 预约
		KlgAmountLimitation klgAmountLimitation=super.get(new QueryFilter(KlgAmountLimitation.class).addFilter("type=",type));
		if(klgAmountLimitation!=null){
			Integer state=klgAmountLimitation.getState();//是否限额   0否 1 是
			if(state.intValue()==0){//不限制
				return true;
			}
			BigDecimal num=klgAmountLimitation.getMoney();//金额 剩余
			if("3".equals(type)){//预约时候分上午时段额度和下午时段额度
				String starDate=klgAmountLimitation.getPmTime();//下午开盘时间
				boolean flg= DateUtil.isCheackTime(starDate,endDate);
				if(!flg){//当前属于上午
					BigDecimal sold=klgAmountLimitation.getTotalMoney().subtract(num);//已卖出额度
					num=klgAmountLimitation.getMorningLimit().subtract(sold);
				}
			}
			if(num.compareTo(money)==-1){
				return false;
			}else{
				return true;
			}
		}
		return false;
	}

	/**
	 * 减少限制额度【抢单/新人/预约】
	 * @param limitType
	 * @param money
	 * @return
	 */
	@Override
	public boolean reduceLimitQuota(AmountLimitType limitType, BigDecimal money) {
		String type=limitType.getKey();//类型 1抢单 2新人3 预约
		KlgAmountLimitation klgAmountLimitation=super.get(new QueryFilter(KlgAmountLimitation.class).addFilter("type=",type));
		if(klgAmountLimitation!=null){
			Integer state=klgAmountLimitation.getState();//是否限额   0否 1 是
			if(state.intValue()==0){//不限制
				return true;
			}
			int count=((KlgAmountLimitationDao)dao).reduceLimitQuota(type,money);
			if(count>0){
				return  true;
			}
		}
		return false;
//		throw new RuntimeException();//false;
	}

	/**
	 * 定时额度清0
	 */
	@Override
	public void resetToZero() {
		System.out.println("购买额度清0");
		Integer type=Integer.parseInt(AmountLimitType.GrabSheet.getKey());//类型 1抢单 2新人3 预约
//		Integer type1=Integer.parseInt(AmountLimitType.NewPeople.getKey());//类型 1抢单 2新人3 预约
		Integer type2=Integer.parseInt(AmountLimitType.Subscribe.getKey());//类型 1抢单 2新人3 预约
		List<KlgAmountLimitation> list=super.findAll();
		if(list!=null&&list.size()>0){
			for (KlgAmountLimitation limitation:list) {
				Integer t=limitation.getType();
				if(t==type2){//预约
					limitation.setTotalMoney(BigDecimal.ZERO);
					limitation.setAfternoonLimit(BigDecimal.ZERO);
					limitation.setMorningLimit(BigDecimal.ZERO);
				}
				limitation.setMoney(BigDecimal.ZERO);
				if(t!=type){
					limitation.setState(0);
				}
				super.update(limitation);
			}
		}
	}
}
