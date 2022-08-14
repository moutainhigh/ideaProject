/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      shangxl
 * @version:     V1.0 
 * @Date:        2017-11-13 19:17:02 
 */
package hry.web.ExDmTransfColdDetail.dao;

import java.util.List;
import java.util.Map;

import hry.core.mvc.dao.base.BaseDao;
import hry.manage.remote.model.ExDmTransfColdDetailManage;
import hry.web.ExDmTransfColdDetail.model.ExDmTransfColdDetail;
import org.apache.ibatis.annotations.Param;


/**
 * <p> ExDmTransfColdDetailDao </p>
 * @author:         shangxl
 * @Date :          2017-11-13 19:17:02  
 */
public interface ExDmTransfColdDetailDao extends  BaseDao<ExDmTransfColdDetail, Long> {

	List<ExDmTransfColdDetail> findFrontPageBySql(Map<String, String> params);

	ExDmTransfColdDetailManage selectByPId(@Param("id") Long id);

}
