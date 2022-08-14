/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-14 15:21:47 
 */
package hry.licqb.record.service;

import hry.core.mvc.service.base.BaseService;
import hry.licqb.record.model.CommendInfo;
import hry.licqb.record.model.DealRecord;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * <p> DealRecordService </p>
 * @author:         zhouming
 * @Date :          2019-08-14 15:21:47 
 */
public interface DealRecordService  extends BaseService<DealRecord, Long>{

    public List<DealRecord> findNewDealRecord(Map<String, Object> map);

    public int getCountNewDealRecord(Map<String, Object> map);

    public DealRecord findNewlyTeamAsset(Map<String, String> map);

    public DealRecord findDateTeamAsset(Map<String, String> map);

    public DealRecord findDateTeamAssetTwo(Map<String, String> map);

    /**
     * 更具不同条件统计收益
     * */
    public BigDecimal countDealMoney(Map<String, Object> map);

    /*
    * 统计消耗的额度
    * */
    public BigDecimal countActualMoney(Map<String, Object> map);

    public List<DealRecord> findPageBySql(Map<String, Object> map);

    /**
     * 统计社区人数对应人数的资产
     * */
    public Map<String, Object> countTeamUser(Map<String, String> map);

    /**
     * 统计社区奖励
     * */
    public BigDecimal countTeamDealMoney(Map<String, Object> map);

    /**
     * 获取推荐用户详细信息
     * */
    public List<CommendInfo> findCommendUserInfo(Map<String, String> map);

    /**
     * 获取用户下有多少对应等级的用户
     * */
    public Integer findUserLevelNum(Map<String, String> map);

    /**
     * 获取项目获利 前十用户
     * */
    public List<Map<String, String>> findEarningsTopTen();

    /**
     * 添加充币记录--手动充币,链上充币
     * **/
    public void addChargeRecord(Map<String,Object> map);


    /**~~~~~~~ lc_deal_record 分表操作方法 ~~~~~~~**/
    /**
     * 对lc_deal_record 按照月份分表
     * */
    public void separateTable();

}
