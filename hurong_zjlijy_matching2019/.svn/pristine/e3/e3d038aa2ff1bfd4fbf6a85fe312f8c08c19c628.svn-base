/**
 * Copyright:    
 * @author:      lixin
 * @version:     V1.0 
 * @Date:        2019-04-22 09:00:59 
 */
package hry.social.shake.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.shake.SocialShakeProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> SocialShakeProductDao </p>
 * @author:         lixin
 * @Date :          2019-04-22 09:00:59  
 */
public interface SocialShakeProductDao extends  BaseDao<SocialShakeProduct, Long> {

    List<SocialShakeProduct> findPageList(Map<String,String> params);

    void updateRecord(SocialShakeProduct ssp);

    Long lastProduct(@Param("customerId") Long customerId);

}
