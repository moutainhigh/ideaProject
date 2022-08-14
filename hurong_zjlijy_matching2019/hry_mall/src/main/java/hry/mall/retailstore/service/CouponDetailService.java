/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-24 18:40:03 
 */
package hry.mall.retailstore.service;


import java.util.List;
import java.util.Map;
import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.mall.retailstore.model.CouponDetail;
import hry.mall.retailstore.model.vo.CouponVo;


/**
 * <p> CouponDetailService </p>
 * @author:         zhouming
 * @Date :          2019-05-24 18:40:03 
 */
public interface CouponDetailService  extends BaseService<CouponDetail, Long> {
	public CouponDetail saveDetail(Map<String, String> map); 
	
	public JsonResult isCanUse(Map<String, String> map);

	// 我的优惠券集合
	public List<CouponVo> findMyCouponList(Map<String, String> map);



}
