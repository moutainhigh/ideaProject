/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2019-01-15 14:33:22 
 */
package hry.ico.service;

import hry.bean.FrontPage;
import hry.core.mvc.service.base.BaseService;
import hry.ico.model.IcoTransactionRecord;

import java.util.List;
import java.util.Map;


/**
 * <p> IcoTransactionRecordService </p>
 * @author:         denghf
 * @Date :          2019-01-15 14:33:22 
 */
public interface IcoTransactionRecordService  extends BaseService<IcoTransactionRecord, Long>{

  //定时拉取充币记录入流水
    public void timingSynChargeCoinRecord();

    public FrontPage findPageBySql(Map<String,String> map);

    //根据ex_dm_transaction 获取充币流水
    public IcoTransactionRecord getCBTransaction(Map<String,String> map);
}
