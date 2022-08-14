/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-14 15:21:47 
 */
package hry.licqb.record.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.licqb.record.model.CommendInfo;
import hry.licqb.record.model.DealRecord;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * <p> DealRecordDao </p>
 * @author:         zhouming
 * @Date :          2019-08-14 15:21:47  
 */
public interface DealRecordDao extends  BaseDao<DealRecord, Long> {
    public List<DealRecord> findNewDealRecord(Map<String, Object> map);

    public int getCountNewDealRecord(Map<String, Object> map);

    public List<DealRecord> findNewlyTeamAsset(Map<String, String> map);
    
    public List<DealRecord> findDateTeamAsset(Map<String, String> map);

    public List<DealRecord> findDateTeamAssetTwo(Map<String, String> map);

    public BigDecimal countDealMoney(Map<String, Object> map);

    public BigDecimal countActualMoney(Map<String, Object> map);

    public List<DealRecord> findPageBySql(Map<String, Object> map);
    /**
     * 统计社区人数对应人数的资产
     * */
    public List<Map<String,Object>> countTeamUser(Map<String, String> map);

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

    /**~~~~~~~ lc_deal_record 分表操作方法 ~~~~~~~**/
    /**
     * 获取created月份分组
     * */
    public List<String> findGroupByCreated();
    /*
    * 校验表是否存在
    * */
    public Integer existTable(String tableName);

    /*
    * 创建表
    * */
    public void createNewTable(@Param("tableName") String tableName);

    /**
     * 为备份表插入数据
     * */
    public void backupsData(@Param("tableName") String tableName, @Param("sTime")String sTime, @Param("eTime")String eTime);

    /**
     * 对按月备份的数据进行按类型统计
     * */
    public void totalMonthsData(@Param("sTime")String sTime, @Param("eTime")String eTime);

    /**
     * 清空二次统计表
     * */
    public void delTotalAllData();
    /*
    * 对备份数据再一次进行统计
    * */
    public void totalAllData();
    /*
    * 批量删除
    * */
    public void delBatch(@Param("sTime")String sTime, @Param("eTime")String eTime);
}
