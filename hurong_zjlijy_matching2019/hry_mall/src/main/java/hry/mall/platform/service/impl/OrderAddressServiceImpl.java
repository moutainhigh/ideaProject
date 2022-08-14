/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-11-16 14:44:03 
 */
package hry.mall.platform.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.platform.model.Address;
import hry.mall.platform.model.OrderAddress;
import hry.mall.platform.service.OrderAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> OrderAddressService </p>
 * @author:         luyue
 * @Date :          2018-11-16 14:44:03  
 */
@Service("orderAddressService")
public class OrderAddressServiceImpl extends BaseServiceImpl<OrderAddress, Long> implements OrderAddressService {
	
	@Resource(name="orderAddressDao")
	@Override
	public void setDao(BaseDao<OrderAddress, Long> dao) {
		super.dao = dao;
	}

	@Override
	public OrderAddress saveOrderAddress(Address address, Long orderId) {
		// TODO Auto-generated method stub
		OrderAddress  orderAddress=new OrderAddress();
		orderAddress.setMemberId(address.getMemberId());
		orderAddress.setOrderId(orderId);
		orderAddress.setProvinceId(address.getProvinceId());
		orderAddress.setCityId(address.getCityId());
		orderAddress.setCountyId(address.getCountyId());
		orderAddress.setStreet(address.getStreet());
		orderAddress.setDetailAddress(address.getDetailAddress());
		orderAddress.setCellphone(address.getCellphone());
		orderAddress.setReceiveName(address.getReceiveName());
		orderAddress.setZipCode(address.getZipCode());
		this.save(orderAddress);
		return orderAddress;
	}
	

}
