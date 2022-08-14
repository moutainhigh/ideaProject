/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-13 13:53:38 
 */
package hry.licqb.award.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.licqb.award.model.OutInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * <p> OutInfoDao </p>
 * @author:         zhouming
 * @Date :          2019-08-13 13:53:38  
 */
public interface OutInfoDao extends  BaseDao<OutInfo, Long> {
    /**
     * 分页查询出局信息表
     * */
    public List<OutInfo> findPageInfo(int start, int limit);

    /**
     * 统计出局信息表
     * */
    public int getOutInfoCount();

    /**
     * 获取该用户父级有效用户数
     * @param customerId 用户id
     *        tierNum 层级数
     * */
    public List<OutInfo> findParentUserList(Map<String, String> paramMap);

    public List<OutInfo> findParentUserListTwo(Map<String, String> paramMap);
    /**
     * 获取该用户子级有效用户数
     * @param customerId 用户id
     *        tierNum 层级数
     * */
    public List<OutInfo> findSonUserList(Map<String, String> paramMap);
    /**
     * 获取用户子数量
     * */
    public int getSonUserCount(Map<String, String> map);

    /**
     * 获取可以释放的用户
     * */
    public List<OutInfo> findGiveOutTeamAwardList();

    /**
     * 查询用户有效直推人数
     * */
    public Integer findDirecUserNum(Long customerId);

    /**
     * 查询用户有效直推人数
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

    /**
     * 查询用户团队中符合等级条件用户数量
     * */
    public Integer findWireNumTwo(Map<String, String> map);

    /**
     * 查询用户直推有效用户
     * */
    public List<OutInfo> findDirectUser(Long customerId);

    /**
     * 根据用户id查询投资信息
     * */
    public List<OutInfo> findOutInfoList(Map<String,String> map);
}
