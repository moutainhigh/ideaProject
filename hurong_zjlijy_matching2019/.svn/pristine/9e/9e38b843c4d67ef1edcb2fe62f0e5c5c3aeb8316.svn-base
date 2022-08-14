/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-09 17:54:25 
 */
package hry.mall.retailstore.service;

import hry.core.mvc.service.base.BaseService;
import hry.mall.order.model.Order;
import hry.mall.retailstore.model.ActivityGoods;
import hry.mall.retailstore.model.Group;
import hry.mall.retailstore.model.GroupDetail;

import java.util.List;
import java.util.Map;




/**
 * <p> GroupService </p>
 * @author:         luyue
 * @Date :          2019-05-09 17:54:25 
 */
public interface GroupService  extends BaseService<Group, Long> {
	/**
	 * 查询某活动商品的id查询开团信息
	 * @param map
	 * @return
	 */
    List<Group> findGroup(Map<String, String> map);
    
    /**
     * 保存开团信息
     * @param activityGoods
     * @param memberId
     * @return
     */
    Group saveGroup(ActivityGoods activityGoods, String memberId);
    /**
     * 拼团成功，进行下单操作
     * @param group
     */
    public void  groupSuccess(Group group);
    
    /**
     * 根据拼团信息，保存订单信息
     * @param group
     */
    public Order saveOrder(GroupDetail detail);
    
    
    

}
