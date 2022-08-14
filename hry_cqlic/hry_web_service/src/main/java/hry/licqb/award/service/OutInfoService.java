/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-13 13:53:38 
 */
package hry.licqb.award.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.licqb.award.model.OutInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * <p> OutInfoService </p>
 * @author:         zhouming
 * @Date :          2019-08-13 13:53:38 
 */
public interface OutInfoService  extends BaseService<OutInfo, Long>{

    /**
     * 投入存储量
     * @param map
     *
     * */
    public void putIntoStorageMoney(Map<String,Object> map);

    /**
     * 分页查询出局信息表
     * */
    public List<OutInfo> findPageInfo(int offset, int limit);

    /**
     * 统计出局信息表
     * */
    public int getOutInfoCount();

    /**
     * 收益达到顶峰--用户出局
     * */
    public void userOut(Long outInfoId, Long customerId, String coinCode);

    /**
     * 获取能收到见点奖的用户
     * @param customerId 用户id
     *        tierNum 层级数
     * */
    public List<OutInfo> findGiveSeeAward(String customerId,String tierNum);

    /**
     * 查询用户所有的父
     * */
    public List<OutInfo> findParentUserList (Map<String, String> map);

    public List<OutInfo> findParentUserListTwo(Map<String, String> paramMap);
    /**
     * 查询用户所有的子
     * */
    public List<OutInfo> findSonUserList (Map<String, String> map);

    /**
     * 查询用户子的数量
     * */
    public int getSonUserCount(Map<String, String> map);

    /**
     * 统一生产收益记录、扣除额度、出局 (重要)
     * @param outInfo 出局信息实体
     * @param predictMoney 预计基数---存储本金(静态收益)、投资金额(见点奖)
     * @param awardRatio 收益比例
     * @param site 收益产出位置标识
     * @param type 收益类型
     * */
    public JsonResult createBusiness(OutInfo outInfo, BigDecimal predictMoney,
                                     BigDecimal awardRatio, String site, String type);

    /**
     * 获取可以释放的用户
     * */
    public List<OutInfo> findGiveOutTeamAwardList();


    /**
     * 查询用户有效直推人数
     * */
    public Integer findDirecUserNum(Long customerId);

    /**
     * 查询用户有效直推人数--新
     * */
    public Integer findDirecUserNumTwo(Long customerId);

    /**
     * 查询用户伞下业绩（不包含本身）
     * */
    public BigDecimal findTeamAsset(Long customerId);

    /**
     * 查询用户伞下业绩（不包含本身）---新
     * */
    public BigDecimal findTeamAssetTwo(String customerId);

    /**
     * 查询用户伞下用户符合等级条件数量
     * */
    public Integer findWireNum(Map<String, String> map);

    public Integer findWireNumTwo(Map<String, String> map);

    public List<OutInfo> findDirectUser(Long customerId);


    /**
     * 根据用户id查询投资信息
     * */
    public OutInfo getOutInfo(Long customerId);
}
