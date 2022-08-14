/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-17 14:29:49 
 */
package hry.klg.level.service;

import java.util.List;

import org.nutz.mvc.annotation.Param;

import hry.core.mvc.service.base.BaseService;
import hry.customer.user.model.AppCustomer;
import hry.klg.level.model.KlgLevelCount;
import hry.klg.level.model.KlgTeamlevel;
import hry.klg.level.model.vo.KlgTeamlevelVo;


/**
 * <p> KlgTeamlevelService </p>
 * @author:         lzy
 * @Date :          2019-04-17 14:29:49 
 */
public interface KlgTeamlevelService  extends BaseService<KlgTeamlevel, Long>{

    public  void addUser(AppCustomer customer);


    /**
     * 根据用户ID 获取上级用户等级配置（奖励额度/见点代数/级差等）
     * @param customerId
     * @return
     */
    public List<KlgTeamlevel> getSuperiorLeveConfig(Long customerId);


    /**
     * 根据用户ID 获取下级 等级排序所有用户统计
     * @param customerId
     * @return
     */
    public List<KlgLevelCount> countSubordinateByCustomer(Long customerId);
    
    /**
     * 查询星级用户数量
     * @param customerId
     * @param level 层级
     * @return
     */
    Integer getStarCount(Long customerId,Integer level);
    /**
     * 查询非星级用户数量
     * @param customerId
     * @param level 层级
     * @return
     */
    Integer getNoStarCount(Long customerId,Integer level);
    
    /**
     * 查询星级非星级用户数量
     * @param customerId
     * @param level 层级
     * @return
     */
    KlgTeamlevelVo getStarVipCount(Long customerId,Integer level);
    
    /**
     * 统计用户排单总数和本周排单数
     * @param customerId
     * @param level
     * @return 层级
     */
    KlgTeamlevelVo getBuyMoneyByDate(Long customerId,Integer level);
}
