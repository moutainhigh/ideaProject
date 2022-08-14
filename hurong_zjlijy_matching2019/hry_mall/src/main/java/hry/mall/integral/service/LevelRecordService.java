/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-31 14:56:41 
 */
package hry.mall.integral.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.mall.integral.model.LevelRecord;

import java.math.BigDecimal;


/**
 * <p> LevelRecordService </p>
 * @author:         luyue
 * @Date :          2019-05-31 14:56:41 
 */
public interface LevelRecordService  extends BaseService<LevelRecord, Long>{

    /**
     *  为没有到期时间和分红时间的购买记录添加时间
     * @return
     */
    JsonResult updateLevelRecord();

    /**
     * 为导入的账户添加购买记录
     * @return
     */
    JsonResult addLevelRecord();


    /**
     * 获取用户的全部个人业绩
     * @param customerId   用户id
     * @return
     */
    BigDecimal getUserPerformance(Long customerId);


    /**
     * 获取用户的团队业绩(不包含自身)
     * @param customerId   用户id
     * @param series 层级
     * @return
     */
    BigDecimal getUserTeamPerformance(Long customerId, Integer series);

}
