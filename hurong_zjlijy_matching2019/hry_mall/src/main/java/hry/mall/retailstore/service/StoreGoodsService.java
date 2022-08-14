/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-09 17:40:23 
 */
package hry.mall.retailstore.service;

import hry.core.mvc.service.base.BaseService;
import hry.mall.goods.model.SearchGoods;
import hry.mall.retailstore.model.StoreGoods;

import java.util.List;
import java.util.Map;


/**
 * <p> StoreGoodsService </p>
 * @author:         zhouming
 * @Date :          2019-05-09 17:40:23 
 */
public interface StoreGoodsService  extends BaseService<StoreGoods, Long> {

    /**
     * 获取用户对应店铺的商品信息
     * */
    public List<SearchGoods> findStoreGoodsList(Map<String, String> params);
}
