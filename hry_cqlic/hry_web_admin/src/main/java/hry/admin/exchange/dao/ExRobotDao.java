/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-12 16:33:44 
 */
package hry.admin.exchange.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.exchange.model.ExRobot;
import org.apache.ibatis.annotations.Param;


/**
 * <p> ExRobotDao </p>
 * @author:         liushilei
 * @Date :          2018-06-12 16:33:44  
 */
public interface ExRobotDao extends  BaseDao<ExRobot, Long> {
    /**
     * 开启交易
     * @param id
     */
    void startAuto(@Param("id") Long id , @Param("isSratAuto") Integer isSratAuto);

    void closeAutoByIds(@Param("ids")Long[] ids,@Param("isSratAuto")Integer isSratAuto);
}
