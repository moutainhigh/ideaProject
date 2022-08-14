/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-22 10:39:08 
 */
package hry.licqb.record.service;

import hry.core.mvc.service.base.BaseService;
import hry.licqb.record.model.ReleaseRuleDetails;

import java.util.List;


/**
 * <p> ReleaseRuleDetailsService </p>
 * @author:         zhouming
 * @Date :          2019-08-22 10:39:08 
 */
public interface ReleaseRuleDetailsService  extends BaseService<ReleaseRuleDetails, Long>{

    public List<ReleaseRuleDetails> findReleaseRuleDetailsList();
}
