/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-01 13:37:58 
 */
package hry.cm.dividend.service;

import java.util.List;

import hry.bean.JsonResult;
import hry.cm.customer.model.CmCustomer;
import hry.cm.dividend.model.CmDividendRecord;
import hry.core.mvc.service.base.BaseService;



/**
 * <p> CmDividendRecordService </p>
 * @author:         zhouming
 * @Date :          2019-08-01 13:37:58 
 */
public interface CmDividendRecordService  extends BaseService<CmDividendRecord, Long>{

    /**
    * 分红奖励发放---自动
    * */
    public void giveOutDividend();

    /**
     * 分红奖励发放---手动
     * */
    public JsonResult handelGiveOutDividend(Long dividendId);
    
    /**
     * 获取开启分红的用户
     * */
    public List<CmCustomer> findCmCustomer();
}
