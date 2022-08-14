/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      menwei
 * @version:     V1.0 
 * @Date:        2017-11-28 15:30:38 
 */
package hry.customer.commend.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.customer.commend.model.AppCommendUser;
import hry.customer.user.model.AppCustomer;
import hry.manage.remote.model.base.FrontPage;



/**
 * <p> AppCommendUserService </p>
 * @author:         menwei
 * @Date :          2017-11-28 15:30:38  
 */
public interface AppCommendUserService  extends BaseService<AppCommendUser, Long>{

	void saveObj(AppCustomer customer);
	
	List<AppCommendUser> saveAppTradeFactor(AppCustomer buyCustomer, BigDecimal transactionBuyFee,
                                            String fixPriceCoinCode, Integer fixPriceType);
	int findLen(String id);

	JsonResult forzen(String ids);

	JsonResult noforzen(String ids);


    List<AppCommendUser> findLikeBySid(String pid);

    List<AppCommendUser> findByAloneMoneyIsNotZero(AppCommendUser appCommendUser);
    
    
    /**
     * SQL 分页
     * @param filter
     * @return
     */
	PageResult findPageBySql(QueryFilter filter);
	/**
	 * 计算一级推荐个数，二级推荐个数，三级推荐个数，四级推荐个数
	 * @param pid
	 * @return
	 */
	 List<AppCommendUser> culCommendCount(Map<String, String> map);
	 
	 /**
	  * 定时执行 增加一级推荐个数，二级推荐个数，三级推荐个数，四级推荐个数
	  * @throws Exception
	  */
	 void addNumber()throws Exception ;

	 /**
		 * 后台查询一级推荐个数，二级推荐个数，三级推荐个数，四级推荐个数
		 * @param 
		 * @return
	*/
	PageResult findConmmendPageBySql(QueryFilter filter, Long id, Integer number, Integer start, Integer length)throws Exception;

	 /**
	 * 前台查询一级推荐个数，二级推荐个数，三级推荐个数，四级推荐个数
	 * @param 
	 * @return
	 */
	FrontPage findConmmendForntPageBySql(Map<String, String> params)throws Exception;
	
	/**
	 * 统计用户推荐人数
	 * @param sid
	 * @return
	 */
	int countLen(String sid);

	/**
	 * 统计直推人数
	 * @param pid
	 * @return
	 */
	 int countOneLevel(String pid);
}
