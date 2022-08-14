package hry.financail.financing.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.financail.financing.model.AppFinancialRecommend;
import hry.remote.model.AppFinancialRecommendVo;

import java.util.List;
import java.util.Map;


/**
 * <p> AppFinancialRecommendDao </p>
 * @author:         jidn
 * @Date :          2019-04-16 14:03:55  
 */
public interface AppFinancialRecommendDao extends  BaseDao<AppFinancialRecommend, Long> {


    /**
     * 查询vip等级用户
     * @param vipLevel
     * @return
     */
    public Integer findVipLevelUserCount(Integer vipLevel);
    /**
     * 查询vip等级用户
     * @param paramMap
     * @return
     */
    public List<AppFinancialRecommend> findVipLevelUser(Map<String,Object> paramMap);

    /**
     *
     * @param map
     * @return
     */
    public List<AppFinancialRecommend> findPageBySql(Map<String,String> map);

    /**
     * 查询 一级用户
     * @return
     */
    public int findPidIsNullListCount();

    /**
     * 查询 一级用户
     * @return
     */
    public List<AppFinancialRecommend> findPidIsNullList(Map<String,Object> paramMap);

    /**
     * 查询一级推荐人
     * @param paramMap
     * @return
     */
    public List<AppFinancialRecommend> findOneLevelList(Map<String,Object> paramMap);

    /**
     * 查找用户理财收益
     * @param pid
     * @return
     */
    public List<AppFinancialRecommendVo> findUserFinancialOrderList(String pid);

    /**
     * 理财推荐人关系
     * @param paramMap
     * @return
     */
    public List<hry.remote.model.AppFinancialRecommend> findFinancialRecommendList(Map<String, Object> paramMap);

    /**
     * 查询理财降级
     * @param paramMap
     * @return
     */
    public List<hry.remote.model.AppFinancialRecommendTransaction> findFinancialRecommendTransactionList(Map<String, Object> paramMap);


    /**
     * 理财推荐人关系
     * @param customerId
     * @return
     */
    public hry.remote.model.AppFinancialRecommend findOne(Long customerId);

    /**
     * 查找团队业绩
     * @param customerId
     * @return
     */
    public List<AppFinancialRecommendVo> findTeamFinancialList(String customerId);

    /**
     * 查询团长
     * @param paramMap
     * @return
     */
    public List<AppFinancialRecommendVo> findTopOneList(Map<String,Object> paramMap);

    /**
     * 查询用户上级有多少人
     * @param customerId
     * @return
     */
    public int findUserSuperiorNumber(Long customerId);

    /**
     * 查询所有下级
     * @param customerId
     * @return
     */
    public List<AppFinancialRecommend> findAllChilds(String customerId);

    /**
     *
     * @param customerId
     * @return
     */
    public String findAllChilds_Str(Long customerId);

    /**
     *
     * @param customerId
     * @return
     */
    public AppFinancialRecommend findOne2(Long customerId);

    /**
     *
     * @param paramMap
     * @return
     */
    public String findAllChildsLevel_Str(Map<String,Object> paramMap);

    /**
     *
     * @param customerId
     * @return
     */
    public int findShipCount(String customerId);
    /**
     * 重新统计理财推荐人关系
     * @return
     */
    public int findPidIsNullListCountRenew();

    /**
     * 重新统计理财推荐人关系
     * @return
     */
    public List<AppFinancialRecommend> findPidIsNullListRenew(Map<String,Object> paramMap);

    public int findBetweenProduct();

}
