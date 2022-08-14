package hry.financail.customer.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.financail.customer.model.AppCommendUser;

import java.util.List;
import java.util.Map;


/**
 * <p> AppCommendUser </p>
 * @author:         jidn
 * @Date :          2019-05-13 20:10:54
 */
public interface AppCommendUserDao extends  BaseDao<AppCommendUser, Long> {

    //todo 理财推荐关系

    /**
     * 查询 推荐关系表 有多少数据
     * @return
     */
    public int generateFinancialRecommendationRelationshipCount();

    /**
     * 分页查询
     * @param paramMap
     * @return
     */
    public List<AppCommendUser> generateFinancialRecommendationRelationship(Map<String,Object> paramMap);

    public AppCommendUser findByUid(Long uid);


}
