/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 16:13:03 
 */
package hry.cm2.team.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.cm2.team.model.Cm2Teamlevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p> Cm2TeamlevelDao </p>
 * @author:         yaozh
 * @Date :          2019-10-14 16:13:03  
 */
public interface Cm2TeamlevelDao extends  BaseDao<Cm2Teamlevel, Long> {

    /**
     * 查询用户团队信息
     * @param customerId
     * @return
     */
    public List<Cm2Teamlevel> findTeamByCustomerId(@Param("customerId") Long customerId, @Param("level") Integer level);


}
