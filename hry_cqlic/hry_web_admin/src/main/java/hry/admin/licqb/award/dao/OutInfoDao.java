/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-13 13:49:07 
 */
package hry.admin.licqb.award.dao;

import hry.admin.licqb.award.model.UserCommendAsset;
import hry.core.mvc.dao.base.BaseDao;
import hry.admin.licqb.award.model.OutInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> OutInfoDao </p>
 * @author:         zhouming
 * @Date :          2019-08-13 13:49:07  
 */
public interface OutInfoDao extends  BaseDao<OutInfo, Long> {

    public List<OutInfo> findPageBySql(Map<String, Object> map);

    public List<OutInfo> findSonPageBySql(Map<String, Object> map);

    public List<UserCommendAsset> findUserPerformance(@Param("customerId") String customerId);
}
