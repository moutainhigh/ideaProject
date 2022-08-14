/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-09 17:40:23 
 */
package hry.mall.retailstore.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.mall.goods.model.SearchGoods;
import hry.mall.retailstore.model.StoreGoods;
import java.util.List;
import java.util.Map;


/**
 * <p> StoreGoodsDao </p>
 * @author:         zhouming
 * @Date :          2019-05-09 17:40:23  
 */
public interface StoreGoodsDao extends BaseDao<StoreGoods, Long> {

    public List<SearchGoods> findStoreGoodsList(Map<String, String> params);

}
