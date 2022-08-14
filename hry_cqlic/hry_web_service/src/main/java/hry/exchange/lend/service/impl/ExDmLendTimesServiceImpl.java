/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gaomm
 * @version:     V1.0 
 * @Date:        2017-11-29 18:36:30 
 */
package hry.exchange.lend.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.person.service.AppPersonInfoService;
import hry.exchange.lend.model.ExDmLendTimes;
import hry.exchange.lend.service.ExDmLendTimesService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;


/**
 * <p> ExDmLendTimesService </p>
 * @author:         Gaomm
 * @Date :          2017-11-29 18:36:30  
 */
@Service("exDmLendTimesService")
public class ExDmLendTimesServiceImpl extends BaseServiceImpl<ExDmLendTimes, Long> implements ExDmLendTimesService{
	
	@Resource(name="exDmLendTimesDao")
	@Override
	public void setDao(BaseDao<ExDmLendTimes, Long> dao) {
		super.dao = dao;
	}
	@Resource
	public AppPersonInfoService appPersonInfoService;
	@Override
	public String[] confirm(String id,String lengPing,String lengRiskRate) {
    	if(!StringUtils.isEmpty(id)){
    		ExDmLendTimes exDmLendTimes=this.get(Long.valueOf(id));
    		exDmLendTimes.setStatus(2);
    		exDmLendTimes.setLendTimesStatus(1);
    		this.update(exDmLendTimes);
    		AppPersonInfo appPersonInfo=appPersonInfoService.getByCustomerId(exDmLendTimes.getCustomerId());
    		appPersonInfo.setLendTimes(exDmLendTimes.getLendTimes());
    		if(!StringUtils.isEmpty(lengPing)){
    			appPersonInfo.setLengPing(new BigDecimal(lengPing));
    		}
    		if(!StringUtils.isEmpty(lengRiskRate)){
    			appPersonInfo.setLengRiskRate(new BigDecimal(lengRiskRate));
    		}
    		appPersonInfoService.update(appPersonInfo);
    	}else{
    	}
		return null;
	}

	@Override
	public String[] invalid(String id,String description) {
    	if(!StringUtils.isEmpty(id)){
    		ExDmLendTimes exDmLendTimes=this.get(Long.valueOf(id));
    		exDmLendTimes.setStatus(2);
    		exDmLendTimes.setLendTimesStatus(2);
    		exDmLendTimes.setDescription(description);
    		this.update(exDmLendTimes);
    	}else{
    	}
		return null;
	}

	@Override
	public String[] invalids(String post_id) {
    	if(!StringUtils.isEmpty(post_id)){
    		String[] ids=post_id.split("_");
    		for (int i = 0; i < ids.length; i++) {
    			String id=ids[i];
    			ExDmLendTimes exDmLendTimes=this.get(Long.valueOf(id));
        		exDmLendTimes.setStatus(2);
        		exDmLendTimes.setLendTimesStatus(2);
        		this.update(exDmLendTimes);
    		}
    	}
		return null;
	}
	

}
