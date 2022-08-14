/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      menwei
 * @version:     V1.0 
 * @Date:        2017-11-28 16:07:54 
 */
package hry.customer.commend.service.impl;

import hry.core.constant.StringConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.sys.ContextUtil;
import hry.customer.commend.model.AppCommendDeploy;
import hry.customer.commend.service.AppCommendDeployService;
import hry.redis.common.utils.RedisService;
import hry.web.app.service.AppConfigService;
import hry.web.remote.RemoteAppConfigService;

import java.math.BigDecimal;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * <p> AppCommendDeployService </p>
 * @author:         menwei
 * @Date :          2017-11-28 16:07:54  
 */
@Service("appCommendDeployService")
public class AppCommendDeployServiceImpl extends BaseServiceImpl<AppCommendDeploy, Long> implements AppCommendDeployService{
	
	@Resource(name="appCommendDeployDao")
	@Override
	public void setDao(BaseDao<AppCommendDeploy, Long> dao) {
		super.dao = dao;
	}

	@Override
	public AppCommendDeploy getAppCommendDeploy() {
		AppCommendDeploy appCommendDeploy=new AppCommendDeploy();
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String string=redisService.get(StringConstant.CONFIG_CACHE+":commendConfig");
		JSONArray obj=JSON.parseArray(string);
		for(Object o:obj){
			JSONObject	 oo=JSON.parseObject(o.toString());
			if(oo.getString("configkey").equals("maxHierarchy")){
				String val=oo.getString("value");
				appCommendDeploy.setMaxHierarchy(null!=val?Integer.valueOf(val):1000);
			}
			if(oo.getString("configkey").equals("minHierarchy")){
				String val=oo.getString("value");
				appCommendDeploy.setMinHierarchy(null!=val?Integer.valueOf(val):1);
			}
			if(oo.getString("configkey").equals("reserveMoney")){
				String val=oo.getString("value");
				appCommendDeploy.setReserveMoney(null!=val?new BigDecimal(val):new BigDecimal("0"));
			}
			if(oo.getString("configkey").equals("standardValue")){
				String val=oo.getString("value");
				appCommendDeploy.setStandardValue(null!=val?new BigDecimal(val):new BigDecimal("0"));
			}
			if(oo.getString("configkey").equals("rankRatio")){
				String val=oo.getString("value");
				appCommendDeploy.setRankRatio(null!=val?new BigDecimal(val):new BigDecimal("0"));
			}
			if(oo.getString("configkey").equals("transactionNumber")){
				String val=oo.getString("value");
				appCommendDeploy.setTransactionNumber(null!=val?Integer.valueOf(val):999999);
			}
		
			
		}
		
		return appCommendDeploy;
	}
	

}
