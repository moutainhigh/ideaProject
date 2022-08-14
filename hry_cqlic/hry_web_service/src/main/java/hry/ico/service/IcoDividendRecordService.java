/**
 * Copyright:   
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-14 20:56:02 
 */
package hry.ico.service;

import hry.bean.FrontPage;
import hry.core.mvc.service.base.BaseService;
import hry.ico.model.IcoDividendRecord;

import java.util.Map;


/**
 * <p> IcoDividendRecordService </p>
 * @author:         houz
 * @Date :          2019-01-14 20:56:02 
 */
public interface IcoDividendRecordService extends BaseService<IcoDividendRecord, Long>{
    //获取用户分红记录
    FrontPage queryDividendRecord(Map<String ,String> map);


    /**
     * 发放奖励分红
     */
    void issueDividend();

}
