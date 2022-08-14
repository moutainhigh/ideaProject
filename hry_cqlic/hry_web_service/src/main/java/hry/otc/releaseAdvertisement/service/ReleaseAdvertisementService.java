/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-22 11:39:46 
 */
package hry.otc.releaseAdvertisement.service;

import java.util.Map;

import hry.bean.FrontPage;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.otc.releaseAdvertisement.model.ReleaseAdvertisement;
import hry.util.QueryFilter;



/**
 * <p> ReleaseAdvertisementService </p>
 * @author:         denghf
 * @Date :          2018-06-22 11:39:46  
 */
public interface ReleaseAdvertisementService  extends BaseService<ReleaseAdvertisement, Long>{


	public FrontPage findPageHall(Map<String, String> map);
	
	public FrontPage findPageBySql(Map<String, String> params);
	
	public String avgTime(Long customerId);

	/**
	 * 后台广告查询
	 * @param filter
	 * @return
	 */
	PageResult findPageAll (QueryFilter filter);
}
