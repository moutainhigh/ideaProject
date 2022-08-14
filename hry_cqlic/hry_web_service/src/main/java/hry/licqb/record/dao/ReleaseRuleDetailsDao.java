/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-22 10:39:08 
 */
package hry.licqb.record.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.licqb.record.model.ReleaseRuleDetails;

import java.util.List;


/**
 * <p> ReleaseRuleDetailsDao </p>
 * @author:         zhouming
 * @Date :          2019-08-22 10:39:08  
 */
public interface ReleaseRuleDetailsDao extends  BaseDao<ReleaseRuleDetails, Long> {

    public List<ReleaseRuleDetails> findReleaseRuleDetailsList();
}
