/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      zhangcb
 * @version:     V1.0 
 * @Date:        2016-11-22 18:25:52 
 */
package hry.customer.person.service;

import java.util.List;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.customer.person.model.AppPersonInfo;



/**
 * <p> AppPersonInfoService </p>
 * @author:         zhangcb
 * @Date :          2016-11-22 18:25:52  
 */
public interface AppPersonInfoService  extends BaseService<AppPersonInfo, Long>{
	/**
	 * 列表查询
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param filter
	 * @param:    @return
	 * @return: PageResult 
	 * @Date :          2016年11月24日 下午12:00:44   
	 * @throws:
	 */
	PageResult findPageBySql(QueryFilter filter);

	/**
	 * 查询单个对象
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param id
	 * @param:    @return
	 * @return: AppPersonInfo 
	 * @Date :          2016年11月24日 下午6:24:25   
	 * @throws:
	 */
	List<AppPersonInfo> getById(Long id);
	/**
	 * 
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param id
	 * @param:    @return
	 * @return: List<AppPersonInfo> 
	 * @Date :          2017年3月8日 下午3:00:00   
	 * @throws:
	 */
	AppPersonInfo getByCustomerId(Long customerId);

	/**
	 * 注册用户审核
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param ids
	 * @param:    @return
	 * @return: JsonResult 
	 * @Date :          2016年11月25日 上午11:31:41   
	 * @throws:
	 */
	boolean updateExamine(AppPersonInfo appPersonInfo);

	/**
	 * 代理商申请列表
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param filter
	 * @param:    @return
	 * @return: PageResult 
	 * @Date :          2017年3月13日 下午5:22:59   
	 * @throws:
	 */
	PageResult findAgentApplyList(QueryFilter filter);

	/**
	 * 判断申请的该地区的代理是否已存在
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param agentLevel
	 * @param:    @param provinceId
	 * @param:    @param cityId
	 * @param:    @param countyId
	 * @param:    @return
	 * @return: boolean 
	 * @Date :          2017年3月22日 下午2:19:54   
	 * @throws:
	 */
	boolean isAgentExist(String agentLevel, String provinceId, String cityId, String countyId);

	/**
	 * 获取推荐人的personinfo对象
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @return
	 * @return: AppPersonInfo 
	 * @Date :          2017年3月30日 下午4:03:18   
	 * @throws:
	 */
	AppPersonInfo getAgentPerson(Long customerid);

	
	/**
	 * 金科新加用户资金报表
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param request
	 * @param:    @return
	 * @return: PageResult 
	 * @Date :          2017年4月12日 上午11:21:19   
	 * @throws:
	 */
	PageResult findPageBySql1(QueryFilter filter);
	/**
	 * 通过邮箱 手机号查询用户信息
	 * @param list
	 * @return
	 */
	List<AppPersonInfo> getAppPersonInfoByEmailPhone(List<String> listId);

}
