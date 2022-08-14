/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-09 17:55:31 
 */
package hry.mall.retailstore.service;
import hry.core.mvc.service.base.BaseService;
import hry.mall.platform.model.Address;
import hry.mall.retailstore.model.Group;
import hry.mall.retailstore.model.GroupDetail;
import java.util.Map;



/**
 * <p> GroupDetailService </p>
 * @author:         luyue
 * @Date :          2019-05-09 17:55:31 
 */
public interface GroupDetailService  extends BaseService<GroupDetail, Long> {
	public GroupDetail saveGroupDetail(Map<String, String> map, Group group, Address address);

}
