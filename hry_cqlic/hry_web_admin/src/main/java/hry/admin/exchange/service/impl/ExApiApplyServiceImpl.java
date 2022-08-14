/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年5月12日 上午10:15:40
 */
package hry.admin.exchange.service.impl;

import hry.admin.exchange.model.ExApiApply;
import hry.admin.exchange.service.ExApiApplyService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.EncryptUtil;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年5月12日 上午10:15:40 
 */
@Service("exApiApplyService")
public class ExApiApplyServiceImpl extends BaseServiceImpl<ExApiApply, Long> implements ExApiApplyService {

	
	@Resource(name="exApiApplyDao")
	@Override
	public void setDao(BaseDao<ExApiApply, Long> dao) {
		// TODO Auto-generated method stub
		super.dao=dao;
	}

	
	
	@Override
	public List<ExApiApply> findList(Long customerId) {
		QueryFilter filter=new QueryFilter(ExApiApply.class);
		filter.addFilter("customerId=", customerId);
		return super.find(filter);
	}

	@Override
	public Map<String,String> createKey(ExApiApply exApiApply) {
		Map<String,String> map= EncryptUtil.getKeys(exApiApply.getCustomerId().toString());
		
		exApiApply.setCustomerId(exApiApply.getCustomerId());
		exApiApply.setAccessKey(map.get("pubKey"));
		
        super.save(exApiApply);
       
		return map;
	}

}
