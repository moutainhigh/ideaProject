/**
 * Copyright:   
 * @author:      kongdb
 * @version:     V1.0 
 * @Date:        2019-01-07 14:42:27 
 */
package hry.mall.integral.service.impl;

import hry.bean.ApiJsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.integral.dao.IntegralConfigDao;
import hry.mall.integral.model.CustomerIntegral;
import hry.mall.integral.model.IntegralConfig;
import hry.mall.integral.service.CustomerIntegralService;
import hry.mall.integral.service.IntegralConfigService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p> IntegralConfigService </p>
 * @author:         kongdb
 * @Date :          2019-01-07 14:42:27  
 */
@Service("integralConfigService")
public class IntegralConfigServiceImpl extends BaseServiceImpl<IntegralConfig, Long> implements IntegralConfigService {

	@Resource
	private CustomerIntegralService customerIntegralService;

	@Resource(name="integralConfigDao")
	@Override
	public void setDao(BaseDao<IntegralConfig, Long> dao) {
		super.dao = dao;
	}

	@Override
	public String findIntegralCode() {
		String code="";
		List<IntegralConfig> clist = super.findAll();
		if(null!=clist && clist.size()>0){
			IntegralConfig config=clist.get(0);
			code=config.getIntegralCode();
		}
		return code;
	}

	@Override
	public CustomerIntegral findCustomerIntegral(Map<String, String> map) {
		// TODO Auto-generated method stub
		String memberId=map.get("memberId");
		String integralType=map.get("integralType");
		QueryFilter filter=new QueryFilter(CustomerIntegral.class);
		filter.addFilter("memberId=", Long.valueOf(memberId));
		filter.addFilter("integralType=", integralType);
		filter.setOrderby("id desc");
		List<CustomerIntegral> list=customerIntegralService.find(filter);
		if(null!=list && list.size()>0){
			CustomerIntegral i=list.get(0);
			return i;
		}
		return null;
	}


	@Override
	public ApiJsonResult getUserRecommendedNumber(Long customerId, Integer series) {
		if(series<1){
			return new ApiJsonResult().setSuccess(false).setMsg("参数不合规");
		}
		if (customerId==null || customerId==0){
			return new ApiJsonResult().setSuccess(false).setMsg("用户id不能为空");
		}
		//根据层级拼接参数
		Long count=0L;
		for (int i = 1; i <= series; i++) {
			String param="%,"+i+"-"+customerId+"%";
			Long num = ((IntegralConfigDao) dao).getUserRecommendedNumber(param);//符合团队等级要求的直推人数
			count =count+ num;

		}
		return new ApiJsonResult().setSuccess(true).setObj(count);
	}
}
