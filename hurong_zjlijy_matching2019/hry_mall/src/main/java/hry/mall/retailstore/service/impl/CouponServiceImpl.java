/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-24 18:39:11 
 */
package hry.mall.retailstore.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.retailstore.dao.CouponDao;
import hry.mall.retailstore.model.Coupon;
import hry.mall.retailstore.service.CouponService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p> CouponService </p>
 * @author:         zhouming
 * @Date :          2019-05-24 18:39:11  
 */
@Service("couponService")
public class CouponServiceImpl extends BaseServiceImpl<Coupon, Long> implements CouponService {
	
	@Resource(name="couponDao")
	@Override
	public void setDao(BaseDao<Coupon, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<Coupon> couponGoodsList(Map<String, Object> map) {
		return ((CouponDao)dao).couponGoodsList(map);
	}

	@Override
	public List<Coupon> findCouponList(Map<String, Object> map) {
		return ((CouponDao)dao).findCouponList(map);
	}
}
