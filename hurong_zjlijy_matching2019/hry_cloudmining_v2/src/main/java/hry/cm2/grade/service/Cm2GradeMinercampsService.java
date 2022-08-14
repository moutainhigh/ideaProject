/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 17:08:11 
 */
package hry.cm2.grade.service;

import hry.core.mvc.service.base.BaseService;
import hry.cm2.grade.model.Cm2GradeMinercamps;



/**
 * <p> Cm2GradeMinercampsService </p>
 * @author:         yaozh
 * @Date :          2019-10-14 17:08:11 
 */
public interface Cm2GradeMinercampsService  extends BaseService<Cm2GradeMinercamps, Long>{

    /**
     * 根据等级 查询等级信息
     * @param grade
     * @return
     */
    public Cm2GradeMinercamps getCmGradeMinercamps(int grade);

}
