/**
 * Copyright:   
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-14 10:10:45 
 */
package hry.admin.ico.experience.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.ico.experience.model.IcoExperience;
import hry.util.QueryFilter;

import java.util.List;


/**
 * <p> IcoExperienceService </p>
 * @author:         houz
 * @Date :          2019-01-14 10:10:45 
 */
public interface IcoExperienceService  extends BaseService<IcoExperience, Long>{

    public PageResult findPageBySql(QueryFilter filter);

}
