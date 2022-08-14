/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-12 15:52:02 
 */
package hry.admin.exchange.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.exchange.model.ExCointoCoin;

import java.util.List;


/**
 * <p> ExCointoCoinDao </p>
 * @author:         liushilei
 * @Date :          2018-06-12 15:52:02  
 */
public interface ExCointoCoinDao extends  BaseDao<ExCointoCoin, Long> {

    List<ExCointoCoin> findCoinCodes();
}
