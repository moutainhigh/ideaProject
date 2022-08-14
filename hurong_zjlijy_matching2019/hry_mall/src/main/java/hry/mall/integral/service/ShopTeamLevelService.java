/**
 * Copyright:   
 * @author:      houzhen
 * @version:     V1.0 
 * @Date:        2019-05-31 09:52:20 
 */
package hry.mall.integral.service;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.mall.integral.model.ShopTeamLevel;
import hry.util.QueryFilter;


/**
 * <p> ShopTeamLevelService </p>
 * @author:         houzhen
 * @Date :          2019-05-31 09:52:20 
 */
public interface ShopTeamLevelService  extends BaseService<ShopTeamLevel, Long> {

    /**
     * sql分页查询
     * @param filter
     * @return
     */
    PageResult findPageBySql(QueryFilter filter);


    /**
     * 维护上级的团队等级
     * @param memberId  用户ID
     * @param series  级数
     * @return
     */
    JsonResult updateTeamLevel(Long memberId, Integer series);


    /**
     * 维护一个用户的团队等级（只升不降）
     * @param memberId  用户ID
     * @return
     */
    JsonResult updateUserTeamLevel(Long memberId);

}
