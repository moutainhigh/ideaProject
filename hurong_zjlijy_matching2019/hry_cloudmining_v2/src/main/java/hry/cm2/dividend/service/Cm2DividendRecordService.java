/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 16:27:21 
 */
package hry.cm2.dividend.service;

import hry.bean.JsonResult;
import hry.cm2.customer.model.Cm2Customer;
import hry.core.mvc.service.base.BaseService;
import hry.cm2.dividend.model.Cm2DividendRecord;

import java.util.List;


/**
 * <p> Cm2DividendRecordService </p>
 * @author:         yaozh
 * @Date :          2019-10-14 16:27:21 
 */
public interface Cm2DividendRecordService  extends BaseService<Cm2DividendRecord, Long>{

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
    public List<Cm2Customer> findCmCustomer();


}
