/**
 * Copyright:    
 * @author:      HeC
 * @version:     V1.0 
 * @Date:        2018-10-18 14:47:26 
 */
package hry.admin.lend.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.lend.model.ExLendConfig;

import java.util.List;
import java.util.Map;


/**
 * <p> ExLendConfigDao </p>
 * @author:         HeC
 * @Date :          2018-10-18 14:47:26  
 */
public interface ExLendConfigDao extends  BaseDao<ExLendConfig, Long> {

    List<String> getCoinCodes();

    List<ExLendConfig> findPageBySql(Map<String, Object> map);
}
