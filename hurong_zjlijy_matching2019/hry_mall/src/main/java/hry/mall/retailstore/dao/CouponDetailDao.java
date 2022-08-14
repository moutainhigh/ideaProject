/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-24 18:40:03 
 */
package hry.mall.retailstore.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.mall.retailstore.model.CouponDetail;
import hry.mall.retailstore.model.vo.CouponVo;

import java.util.List;
import java.util.Map;

/**
 * <p> CouponDetailDao </p>
 * @author:         zhouming
 * @Date :          2019-05-24 18:40:03  
 */
public interface CouponDetailDao extends BaseDao<CouponDetail, Long> {

    // 我的优惠券集合
    public List<CouponVo> findMyCouponList(Map<String, String> map);

}
