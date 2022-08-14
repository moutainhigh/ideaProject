/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-12 18:38:38 
 */
package hry.admin.ico.rulesconfig.service.impl;

import hry.admin.ico.rulesconfig.model.IcoRuleDetailed;
import hry.admin.ico.rulesconfig.model.RulesConfig;
import hry.admin.ico.rulesconfig.service.IcoRuleDetailedService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p> IcoRuleDetailedService </p>
 * @author:         lzy
 * @Date :          2019-01-12 18:38:38  
 */
@Service("icoRuleDetailedService")
public class IcoRuleDetailedServiceImpl extends BaseServiceImpl<IcoRuleDetailed, Long> implements IcoRuleDetailedService{
	@Resource
	private RedisService redisService;

	@Resource(name="icoRuleDetailedDao")
	@Override
	public void setDao(BaseDao<IcoRuleDetailed, Long> dao) {
		super.dao = dao;
	}


	//定时同步数据库平台币数量
	@Override
	public void timingPlatformCurrencySyn() {
		System.out.println("定时同步数据库平台币数量");
		String NUMBER=redisService.get(RulesConfig.PLATFORM_NUMBER);//平台币剩余数量
		String CIRCULATION=redisService.get(RulesConfig.PLATFORM_CIRCULATION);//发行数量
		if(NUMBER==null||"".equals(NUMBER)){
			NUMBER="0";
		}if(CIRCULATION==null||"".equals(CIRCULATION)){
			CIRCULATION="0";
		}
		BigDecimal num=new BigDecimal(CIRCULATION).subtract(new BigDecimal(NUMBER));
		QueryFilter queryFilter=new QueryFilter(IcoRuleDetailed.class);
		queryFilter.addFilter("state=",1);
		queryFilter.setOrderby("created asc");
		//queryFilter.addFilter("saleSurplusNum>",0);
		List<IcoRuleDetailed> list= this.find(queryFilter);
		if(list!=null && list.size()>0){
			for (IcoRuleDetailed info:list) {
				BigDecimal bigDecimal=info.getSaleNum();//剩余数量
				BigDecimal saleSurplusNum=info.getSaleSurplusNum();//剩余数量
				if(saleSurplusNum.compareTo(num)==0){
					break;
				}
				if(saleSurplusNum.compareTo(BigDecimal.ZERO)==0){
					num=num.subtract(bigDecimal);
						break;
				}
				if(bigDecimal.compareTo(num)==-1){
					num=num.subtract(bigDecimal);
					bigDecimal=new BigDecimal(0);
				}else{
					bigDecimal=bigDecimal.subtract(num);
				}
				info.setSaleSurplusNum(bigDecimal);
				this.update(info);
			}
		}
	}
}
