/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-24 18:39:43 
 */
package hry.mall.retailstore.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.retailstore.model.CouponGoods;
import hry.mall.retailstore.service.CouponGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> CouponGoodsService </p>
 * @author:         zhouming
 * @Date :          2019-05-24 18:39:43  
 */
@Service("couponGoodsService")
public class CouponGoodsServiceImpl extends BaseServiceImpl<CouponGoods, Long> implements CouponGoodsService {
	
	@Resource(name="couponGoodsDao")
	@Override
	public void setDao(BaseDao<CouponGoods, Long> dao) {
		super.dao = dao;
	}
	

}
