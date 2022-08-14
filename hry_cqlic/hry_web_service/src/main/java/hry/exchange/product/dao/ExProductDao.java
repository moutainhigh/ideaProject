/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2015年11月06日  14:57:13
 */
package hry.exchange.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import hry.core.mvc.dao.base.BaseDao;
import hry.exchange.product.model.ExProduct;

/**
 * 
 * <p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月24日 下午1:34:18
 */
public interface ExProductDao extends BaseDao<ExProduct, Long> {
	
	public List<ExProduct> selectProductByCustomerId(@Param(value = "customerId") Long customerId);

}
