/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:52:11
 */
package hry.account.fund.service;

import java.util.List;
import java.util.Map;

import hry.account.fund.model.AppBankCard;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.manage.remote.model.base.FrontPage;
import hry.util.QueryFilter;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月31日 下午6:52:11 
 */
public interface AppBankCardService extends BaseService<AppBankCard, Long>{
	
	/**
	 * sql分页
	 * @param filter
	 * @return
	 */
	PageResult findPageBySql(QueryFilter filter);

	/**
	 * 新sql分页查询
	 * @param filter
	 * @return
	 */
	PageResult findPageBySqlList(QueryFilter filter);


	int findSaveFlag(String cardNumber, Integer type);
	
	FrontPage findPageBySql1(Map<String, String> map);

	List<AppBankCard> getPersonalAsset(Long customerId, String type, String isDefault);
	
	void updateIsDefault(Map<String, String> map);

    void updateIsDeleteById (Long id);
}
