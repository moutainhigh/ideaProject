/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2019-01-14 13:38:06 
 */
package hry.ico.service;

import hry.core.mvc.service.base.BaseService;
import hry.ico.model.IcoAccount;
import hry.ico.model.util.IcoAccountAtioPo;

import java.math.BigDecimal;
import java.util.List;


/**
 * <p> IcoAccountService </p>
 * @author:         denghf
 * @Date :          2019-01-14 13:38:06 
 */
public interface IcoAccountService  extends BaseService<IcoAccount, Long>{


     public boolean updateUserAccount(Long customerId, BigDecimal number);

     public boolean updateByAccountId(Long accountId,Integer version,BigDecimal number);

     public String  recommendedLockSum(Long customerId);

     //定时发奖励
     public boolean timingToAward();


     //所有用户总锁仓数量
     public  String allMemberLockSum();

     //获取用户锁仓比率 （自身锁仓数/所有用户的总锁仓数）
     public   String getMemberLockAtio( Long customerId);
     //获取用户的锁仓占比
     public List<IcoAccountAtioPo> getAccountAtioBylevelSort(Integer levelSort);
}
