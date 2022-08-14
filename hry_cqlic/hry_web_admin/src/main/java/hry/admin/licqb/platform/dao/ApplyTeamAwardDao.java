/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-12 17:44:07 
 */
package hry.admin.licqb.platform.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.licqb.platform.model.ApplyTeamAward;

import java.util.List;
import java.util.Map;


/**
 * <p> ApplyTeamAwardDao </p>
 * @author:         zhouming
 * @Date :          2019-08-12 17:44:07  
 */
public interface ApplyTeamAwardDao extends  BaseDao<ApplyTeamAward, Long> {
    public List<ApplyTeamAward> findApplyPage(Map<String, String> map);
}
