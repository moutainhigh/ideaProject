/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      songb
 * @version:     V1.0 
 * @Date:        2018-05-04 11:37:43 
 */
package hry.exchange.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hry.core.mvc.dao.base.BaseDao;
import hry.exchange.product.model.ExRobot;


/**
 * <p> ExRobotDao </p>
 * @author:         songb
 * @Date :          2018-05-04 11:37:43  
 */
public interface ExRobotDao extends  BaseDao<ExRobot, Long> {

	/**
	 * 开启交易
	 * @param id
	 */
	void startAuto(@Param("id") Long id, @Param("isSratAuto") Integer isSratAuto);

	void closeAutoByIds(@Param("ids") Long[] ids, @Param("isSratAuto") Integer isSratAuto);


}
