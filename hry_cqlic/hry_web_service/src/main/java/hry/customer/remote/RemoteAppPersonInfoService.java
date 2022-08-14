/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午12:18:00
 */
package hry.customer.remote;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.util.RemoteQueryFilter;
import hry.customer.person.model.AppPersonInfo;
import hry.manage.remote.model.AppPersonInfoManage;

import java.util.List;


/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月28日 下午12:18:00 
 */
public interface RemoteAppPersonInfoService {
	
	/**
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param id
	 * @param:    @return
	 * @return: AppPersonInfo 
	 * @Date :          2016年3月30日 下午5:48:19   
	 * @throws:
	 */
	public AppPersonInfo getByCustomerId(Long id);

	/**
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param _appPersonInfo
	 * @return: void 
	 * @Date :          2016年3月31日 上午10:44:11   
	 * @throws:
	 */
	public void save(AppPersonInfo appPersonInfo);

	/**
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param _appPersonInfo
	 * @return: void 
	 * @Date :          2016年3月31日 上午10:49:05   
	 * @throws:
	 */
	public void update(AppPersonInfo appPersonInfo);
	
	
	public JsonResult addUserEmail(Long custromerId, String email);

	
	// 通过email 判断用户是否存在 如果存在 就返回true  如果不存在就返回false  
	public Boolean findCustromerByEmail(String email);

	// 用户的id 和激活码激活用户的邮箱
	public Boolean activationEmail(String eamilCode);

	/**
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param remoteQueryFilter
	 * @return: void 
	 * @Date :          2016年8月31日 下午8:10:33   
	 * @throws:
	 */
	public AppPersonInfo get(RemoteQueryFilter remoteQueryFilter);


	/**
	 * 通过ID获取
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param id
	 * @param:    @return
	 * @return: AppPersonInfo 
	 * @Date :          2017年3月17日 上午9:44:59   
	 * @throws:
	 */
	public AppPersonInfo get(Long id);
	/**
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param remoteQueryFilter
	 * @return: void 
	 * @Date :          2016年8月31日 下午8:10:33   
	 * @throws:
	 */
	public List<AppPersonInfo> find(RemoteQueryFilter remoteQueryFilter);

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
	public boolean isAgentExist(String agentLevel, String provinceId, String cityId, String countyId);

	/**
	 * 获取该用户的积分列表
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param long1 
	 * @param:    @param remoteQueryFilter
	 * @param:    @return
	 * @return: PageResult 
	 * @Date :          2017年4月20日 下午6:37:30   
	 * @throws:
	 */
	public PageResult findJifenRecord(RemoteQueryFilter filter, Long long1);
}
