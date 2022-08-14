/**
 * Copyright:   
 * @author:      houzhen
 * @version:     V1.0 
 * @Date:        2019-06-19 19:39:19 
 */
package hry.mall.integral.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.mall.integral.model.ShopWithdrawRecord;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * <p> ShopWithdrawRecordService </p>
 * @author:         houzhen
 * @Date :          2019-06-19 19:39:19 
 */
public interface ShopWithdrawRecordService extends BaseService<ShopWithdrawRecord, Long>{

    List<Map<String, Object>> getAppDic(String pKey);

    JsonResult isWithdraw(Long memberId, BigDecimal applicationAmount);


}
