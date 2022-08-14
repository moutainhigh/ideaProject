/**
 * Copyright:    
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2019-01-14 13:38:06 
 */
package hry.ico.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.ico.model.IcoAccount;
import hry.ico.model.util.IcoAccountAtioPo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;


/**
 * <p> IcoAccountDao </p>
 * @author:         denghf
 * @Date :          2019-01-14 13:38:06  
 */
public interface IcoAccountDao extends  BaseDao<IcoAccount, Long> {

    int updateByVersion(@Param("id") Long id, @Param("version") Integer version,@Param("number") BigDecimal number);

    //推荐锁仓统计(SUM)
    String recommendedLockSum(@Param("customerId") Long customerId);

    //所有用户总锁仓数量
    public  String allMemberLockSum();

    //获取用户锁仓比率 （自身锁仓数/所有用户的总锁仓数）
    public   String getMemberLockAtio(@Param("customerId") Long customerId);

    //获取用户的锁仓占比
    public List<IcoAccountAtioPo> getAccountAtioBylevelSort(@Param("levelSort") Integer levelSort);

}