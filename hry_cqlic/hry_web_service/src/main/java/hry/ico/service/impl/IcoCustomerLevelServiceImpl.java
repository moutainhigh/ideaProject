/**
 * Copyright:   
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-16 21:09:20 
 */
package hry.ico.service.impl;


import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.ico.dao.IcoCustomerLevelDao;
import hry.ico.model.IcoCustomerLevel;
import hry.ico.service.IcoCustomerLevelService;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.RemoteResult;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p> IcoCustomerLevelService </p>
 * @author:         houz
 * @Date :          2019-01-16 21:09:20  
 */
@Service("icoCustomerLevelService")
public class IcoCustomerLevelServiceImpl extends BaseServiceImpl<IcoCustomerLevel, Long> implements IcoCustomerLevelService {
	//扣除经验比率 10%
	private static  String deductionRatio="0.1";
	@Resource(name="icoCustomerLevelDao")
	@Override
	public void setDao(BaseDao<IcoCustomerLevel, Long> dao) {
		super.dao = dao;
	}

	/**
	 * 月末扣除用户当前经验10%
 	 */
	@Override
	public void deductionExperience() {
		List<IcoCustomerLevel> levels=super.find(new QueryFilter(IcoCustomerLevel.class).addFilter("experience>","0"));
		if(levels!=null&&levels.size()>0){
			RemoteManageService remoteManageService = SpringUtil.getBean("remoteManageService");
			for (IcoCustomerLevel lev:levels) {
				Long customerId=lev.getCustomer_id();//0203月末扣除10%
				Long experience=lev.getExperience();//当前经验
				BigDecimal deduction=new BigDecimal(deductionRatio).multiply(new BigDecimal(experience));
				Long deExp=getDedExp(deduction,experience);
				remoteManageService.clearingExperience(customerId,"0203",deExp,null,"月末扣除经验");
			}
		}
	}
	//假如只有1点经验，扣除10%后，剩0.9，不显示为0.9，显示为0
	private static Long getDedExp(BigDecimal decimal ,Long nowExperience){
		boolean isd=decimal.stripTrailingZeros().toPlainString().contains(".");//
		if(isd){
			decimal=decimal.add(new BigDecimal(1)).setScale(0,BigDecimal.ROUND_DOWN);
			if(nowExperience<decimal.longValue()){
				return nowExperience;
			}
		}
		return  decimal.longValue();
	}

	public static void main(String[] args) {
		long a=100;
		BigDecimal deduction=new BigDecimal(deductionRatio).multiply(new BigDecimal(a)).setScale(1,BigDecimal.ROUND_HALF_UP);
		System.out.println(getDedExp(deduction,200l));

	}
}
