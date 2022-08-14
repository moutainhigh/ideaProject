/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-11-16 14:31:31 
 */
package hry.mall.platform.service.impl;


import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.platform.dao.AddressDao;
import hry.mall.platform.model.Address;
import hry.mall.platform.service.AddressService;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * <p> AddressService </p>
 * @author:         luyue
 * @Date :          2018-11-16 14:31:31  
 */
@Service("addressService")
public class AddressServiceImpl extends BaseServiceImpl<Address, Long> implements AddressService {
	
	@Resource(name="addressDao")
	@Override
	public void setDao(BaseDao<Address, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<Address> findByCustomerId(Long customerId,Short isDefault) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("memberId",customerId);
		if(null!=isDefault && !"".equals(isDefault)){
			map.put("isDefault",isDefault);
		}
		List<Address> list=  ((AddressDao)dao).findByCustomerId(map);
		return list;
	}
	

}
