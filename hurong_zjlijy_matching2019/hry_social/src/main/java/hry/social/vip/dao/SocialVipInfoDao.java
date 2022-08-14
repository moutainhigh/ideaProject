/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-12 11:24:44
 */
package hry.social.vip.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.vip.SocialVipInfo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;


/**
 * <p> SocialVipInfoDao </p>
 * @author: javalx
 * @Date :          2019-06-12 11:24:44  
 */
public interface SocialVipInfoDao extends BaseDao<SocialVipInfo,Long> {

    /**
     * 查询可购买的会员信息
     *
     * @param additionRatio
     * @return
     */
    List<SocialVipInfo> getAvailableVip(@Param("additionRatio") BigDecimal additionRatio);
}
