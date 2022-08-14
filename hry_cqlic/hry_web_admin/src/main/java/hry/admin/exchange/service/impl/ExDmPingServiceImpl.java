/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-21 14:45:05 
 */
package hry.admin.exchange.service.impl;

import hry.admin.exchange.model.ExDmPing;
import hry.admin.exchange.service.ExDmPingService;
import hry.core.constant.CodeConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.QueryFilter;
import hry.util.properties.PropertiesUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> ExDmPingService </p>
 * @author:         tianpengyu
 * @Date :          2018-06-21 14:45:05  
 */
@Service("exDmPingService")
public class ExDmPingServiceImpl extends BaseServiceImpl<ExDmPing, Long> implements ExDmPingService{
	
	@Resource(name="exDmPingDao")
	@Override
	public void setDao(BaseDao<ExDmPing, Long> dao) {
		super.dao = dao;
	}

	@Override
	public String[] checkPing(Long customerId) {
		String[] rt = new String[2];
		Boolean isPinging = this.isPinging(customerId, null, null, null);
		if (isPinging) {
			rt[0] = CodeConstant.CODE_FAILED;
			rt[1] = "goingPing";
			return rt;
		} else {
			rt[0] = CodeConstant.CODE_SUCCESS;
			return rt;

		}
	}

	@Override
	public Boolean isPinging(Long customerId, String userCode, String currencyType, String website) {

		String isLend = PropertiesUtils.APP.getProperty("app.isLend");//做成配置文件,如果是坐市商的话就用theSeat
		if (isLend != null && !"".equals(isLend) && isLend.equals("true")) {
			List<ExDmPing> list = getBycustomerid(customerId, userCode, 1, currencyType, website);
			if (null != list && list.size() != 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	@Override
	public List<ExDmPing> getBycustomerid(Long customerId, String userCode, Integer status, String currencyType, String website) {
		QueryFilter filter = new QueryFilter(ExDmPing.class);
		filter.addFilter("customerId=", customerId);
		filter.addFilter("status=", status);
		filter.setSaasId(PropertiesUtils.APP.getProperty("app.saasId"));
		return this.find(filter);
	}
}
