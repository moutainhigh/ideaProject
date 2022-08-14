/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0
 * @Date:      2015年11月06日  14:57:13
 */
package hry.trade.entrust.dao;

import hry.account.fund.model.AppAccount;
import hry.core.mvc.dao.base.BaseDao;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.user.model.AppCustomer;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.product.model.ExCointoCoin;
import hry.trade.redis.model.EntrustTrade;
import hry.trade.robot.model.ExRobot;
import hry.trade.robot.model.ExRobotDeep;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 *
 * <p>
 *
 * @author: Wu Shuiming
 * @Date : 2016年3月24日 下午1:34:18
 */
public interface CommonDao extends BaseDao<EntrustTrade, Long> {
	AppCustomer getAppUserByuserName(@Param(value="userName")String userName);
	List<ExCointoCoin> getExointocoinValid();
	 List<ExCointoCoin> getExointocoinAll();
	List<AppCustomer> getAppUserAll();
	AppAccount getAppAccount(@Param(value="customerId")Long customerId);
	List<ExDigitalmoneyAccount> getListExDigitalmoneyAccount(@Param(value="customerId")Long customerId);
	List<ExRobot> getExRobotPriceSource();
	List<ExRobot> getExRobotList(@Param(value="robotType")Integer robotType);
	AppPersonInfo getAppPersonInfoBycustomerId(@Param(value="customerId")Long customerId);
	List<ExRobotDeep> getExRobotDeepList(@Param(value="robotType")Integer robotType);
}
