/**
 * Copyright:    
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-14 10:10:45 
 */
package hry.ico.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.ico.model.IcoExperience;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> IcoExperienceDao </p>
 * @author:         houz
 * @Date :          2019-01-14 10:10:45  
 */
public interface IcoExperienceDao extends BaseDao<IcoExperience, Long> {

    //获取用户经验明细
    List<IcoExperience> queryExperiencesDetail(Map<String ,String> map);

}
