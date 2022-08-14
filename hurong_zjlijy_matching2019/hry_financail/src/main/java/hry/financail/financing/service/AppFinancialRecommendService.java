package hry.financail.financing.service;

import hry.bean.FrontPage;
import hry.core.mvc.service.base.BaseService;
import hry.financail.financing.model.AppFinancialRecommend;
import hry.remote.model.AppFinancialRecommendTransaction;
import hry.remote.model.AppFinancialRecommendVo;
import hry.remote.model.RemoteResult;

import java.util.List;
import java.util.Map;


/**
 * <p> AppFinancialRecommendService </p>
 * @author:         jidn
 * @Date :          2019-04-16 14:03:55 
 */
public interface AppFinancialRecommendService extends BaseService<AppFinancialRecommend, Long>{

    /**
     * 查询理财推荐人关系
     * @param paramMap
     * @return
     */
    public FrontPage findFinancialRecommendList(Map<String, String> paramMap);

    /**
     * 查询理财奖励
     * @param paramMap
     * @return
     */
    public List<AppFinancialRecommendTransaction> findFinancialRecommendTransactionList(Map<String, String> paramMap);

    /**
     * 根据customeriD 查找用户
     * @param customerId
     * @return
     */
    public hry.remote.model.AppFinancialRecommend findOne(Long customerId);

    /**
     * 查找团队业绩
     * @param customerId
     * @return
     */
    public List<AppFinancialRecommendVo> findTeamFinancialList(Long customerId);

    /**
     * 统计会员等级
     */
    public void statisVipLevel();

    /**
     * 会员推荐奖励
     */
    public void issueFinancial();

    /**
     * 生成理财推荐关系
     */
    public void generateFinancialRecommendationRelationship();

    /**
     * todo 超越 理财增加推荐关系
     * @param customerId
     * @param code
     * @return
     */
    public RemoteResult addFinancialRecommendShip(Long customerId,String code);

    /**
     * todo 重新统计理财推荐关系
     */
    public void renewFinancialRecommend();

    /**
     *
     * @param customerId
     * @return
     */
    public int findShipCount(Long customerId);
}
