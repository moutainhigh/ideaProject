/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.exchange.entrust.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hry.core.constant.StringConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.util.date.DateUtil;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import hry.exchange.entrust.service.ExExEntrustPlanService;


import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExEntrustPlan;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;




/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
@Service("exExEntrustPlanService")
public class ExExEntrustPlanServiceImpl extends BaseServiceImpl<ExEntrustPlan, Long>
		implements ExExEntrustPlanService {

	@Resource(name = "exExEntrustPlanDao")
	@Override
	public void setDao(BaseDao<ExEntrustPlan, Long> dao) {
		super.dao = dao;
	}

}
