/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2019-01-12 19:48:59 
 */
package hry.ico.service;

import hry.bean.FrontPage;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.ico.model.IcoExperience;
import hry.util.QueryFilter;

import java.util.List;
import java.util.Map;

/**
 * <p> IcoUpgradeConfigService </p>
 * @author:         denghf
 * @Date :          2019-01-12 19:48:59 
 */
public interface IcoExperienceService extends BaseService<IcoExperience, Long>{

    //获取用户经验明细
    FrontPage queryExperiencesDetail(Map<String ,String> map);

}
