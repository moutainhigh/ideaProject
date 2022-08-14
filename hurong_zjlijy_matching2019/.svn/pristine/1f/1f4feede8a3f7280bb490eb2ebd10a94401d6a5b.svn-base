/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 16:13:03 
 */
package hry.cm2.team.service;

import hry.core.mvc.service.base.BaseService;
import hry.cm2.team.model.Cm2Teamlevel;

import java.util.List;


/**
 * <p> Cm2TeamlevelService </p>
 * @author:         yaozh
 * @Date :          2019-10-14 16:13:03 
 */
public interface Cm2TeamlevelService  extends BaseService<Cm2Teamlevel, Long>{

    /**
     * 查询用户团队信息
     * @param customerId
     * @return
     */
    public List<Cm2Teamlevel> findTeamByCustomerId(Long customerId, Integer level);

    /**
     * 查询用户所有上级
     * @param customerId
     * @return
     */
    public List<Cm2Teamlevel> findUpTeamByCustomerId(Long customerId);


}
