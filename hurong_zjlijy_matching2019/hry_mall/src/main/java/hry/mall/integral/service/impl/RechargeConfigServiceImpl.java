/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-03-19 15:31:58 
 */
package hry.mall.integral.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.integral.model.RechargeConfig;
import hry.mall.integral.service.RechargeConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> RechargeConfigService </p>
 * @author:         luyue
 * @Date :          2019-03-19 15:31:58  
 */
@Service("rechargeConfigService")
public class RechargeConfigServiceImpl extends BaseServiceImpl<RechargeConfig, Long> implements RechargeConfigService{
	
	@Resource(name="rechargeConfigDao")
	@Override
	public void setDao(BaseDao<RechargeConfig, Long> dao) {
		super.dao = dao;
	}
	

}
