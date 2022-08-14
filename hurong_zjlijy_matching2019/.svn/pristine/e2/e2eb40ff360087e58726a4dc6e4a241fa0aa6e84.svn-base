/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:43:16 
 */
package hry.cm4.team.dao;

import hry.cm4.team.model.Cm4Teamlevel;
import hry.core.mvc.dao.base.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p> Cm4TeamlevelDao </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:43:16  
 */
public interface Cm4TeamlevelDao extends  BaseDao<Cm4Teamlevel, Long> {
    /**
     * 查询用户团队信息
     * @param customerId
     * @return
     */
    public List<Cm4Teamlevel> findTeamByCustomerId(@Param("customerId") Long customerId, @Param("level") Integer level);

}
