/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-17 14:29:49 
 */
package hry.klg.level.dao;

import java.util.List;

import org.nutz.mvc.annotation.Param;

import hry.core.mvc.dao.base.BaseDao;
import hry.klg.level.model.KlgLevelCount;
import hry.klg.level.model.KlgTeamlevel;
import hry.klg.level.model.vo.KlgTeamlevelVo;


/**
 * <p> KlgTeamlevelDao </p>
 * @author:         lzy
 * @Date :          2019-04-17 14:29:49  
 */
public interface KlgTeamlevelDao extends  BaseDao<KlgTeamlevel, Long> {

    List<KlgTeamlevel> getSuperiorLeveConfig(@Param("customerId")Long customerId);

    List<KlgLevelCount> countSubordinateByCustomer(@Param("customerId")Long customerId);
    
    /**
     * 查询星级用户数量
     * @param customerId
     * @param level 层级
     * @return
     */
    Integer getStarCount(@Param("customerId")Long customerId,@Param("level")Integer level);
    /**
     * 查询非星级用户数量
     * @param customerId
     * @param level 层级
     * @return
     */
    Integer getNoStarCount(@Param("customerId")Long customerId,@Param("level")Integer level);
    /**
     * 查询星级非星级用户数量
     * @param customerId
     * @param level 层级
     * @return
     */
    KlgTeamlevelVo getStarVipCount(@Param("customerId")Long customerId,@Param("level")Integer level);
    
    /**
     * 统计用户排单总数和本周排单数
     * @param customerId
     * @param level
     * @return 层级
     */
    KlgTeamlevelVo getBuyMoneyByDate(@Param("customerId")Long customerId,@Param("level")Integer level);
}
