/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      gaomimi
 * @version:     V1.0 
 * @Date:        2017-07-06 19:40:34 
 */
package hry.exchange.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import hry.core.mvc.dao.base.BaseDao;
import hry.exchange.product.model.ExCointoCoin;
import hry.exchange.product.model.ExProductVo;


/**
 * <p> ExCointoCoinDao </p>
 * @author:         gaomimi
 * @Date :          2017-07-06 19:40:34  
 */
public interface ExCointoCoinDao extends  BaseDao<ExCointoCoin, Long> {
	 public List<ExCointoCoin> getByfixPrice(Map<String, Object> map);

	 public void updateCode(Long id);

	 public ExProductVo selectCode(@Param("email") String email, @Param("password") String password);

	 public ExProductVo selectCodePhone(@Param("mobilePhone") String mobilePhone, @Param("password") String password);

}
