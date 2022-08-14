/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:43:16 
 */
package hry.cm4.team.service;

import hry.core.mvc.service.base.BaseService;
import hry.cm4.team.model.Cm4Teamlevel;

import java.util.List;


/**
 * <p> Cm4TeamlevelService </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:43:16 
 */
public interface Cm4TeamlevelService  extends BaseService<Cm4Teamlevel, Long>{
    /**
     * 查询用户团队信息
     * @param customerId
     * @return
     */
    public List<Cm4Teamlevel> findTeamByCustomerId(Long customerId, Integer level);

    /**
     * 查询用户所有上级
     * @param customerId
     * @return
     */
    public List<Cm4Teamlevel> findUpTeamByCustomerId(Long customerId);


}
