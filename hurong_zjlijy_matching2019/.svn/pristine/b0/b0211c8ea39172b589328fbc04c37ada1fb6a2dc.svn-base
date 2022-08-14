/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-01-08 14:06:05 
 */
package hry.cm5.miner.service;

import hry.cm5.miner.model.Cm5MinerOrder;
import hry.core.mvc.service.base.BaseService;
import hry.cm5.miner.model.Cm5AwardRecord;

import java.math.BigDecimal;


/**
 * <p> Cm5AwardRecordService </p>
 * @author:         zhouming
 * @Date :          2020-01-08 14:06:05 
 */
public interface Cm5AwardRecordService  extends BaseService<Cm5AwardRecord, Long>{

    /**
     * 统计对应收益数量
     * */
    public BigDecimal getAwardTotal(String awardCode, String inheritNum, String awardType);

    /**
     * 发放收益
     * */
    public void giveOutAward(Cm5MinerOrder minerOrder);

}
