/**
 * Copyright:    
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-14 10:10:45 
 */
package hry.admin.ico.experience.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.ico.experience.model.IcoExperience;

import java.util.List;
import java.util.Map;


/**
 * <p> IcoExperienceDao </p>
 * @author:         houz
 * @Date :          2019-01-14 10:10:45  
 */
public interface IcoExperienceDao extends  BaseDao<IcoExperience, Long> {
    List<IcoExperience> findPageBySql(Map<String, Object> map);
}
