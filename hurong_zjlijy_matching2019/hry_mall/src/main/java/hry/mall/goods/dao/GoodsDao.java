/**
 * Copyright:    
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:44:42 
 */
package hry.mall.goods.dao;
import hry.core.mvc.dao.base.BaseDao;
import hry.mall.goods.model.Goods;
import hry.mall.goods.model.SearchGoods;
import java.util.List;
import java.util.Map;


/**
 * <p> GoodsDao </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:44:42  
 */
public interface GoodsDao extends BaseDao<Goods, Long> {
    /**
     * 新sql分页
     * @param map
     * @return
     */
    List<Goods> findPageBySql(Map<String, Object> map);

    //获取solr说需要商品集合
    public List<SearchGoods> findSearchGoodsList(Map<String, Object> map);
    
    List<Goods> findIntegralGoods(Map<String, Object> map);

    /**
     * 查询活动商品列表
     * */
    public List<SearchGoods> findActivityGoodsList(Map<String, Object> params);

    /**
     * 查询所有活动商品 （进行活动的商品）
     * */
    public List<SearchGoods> findActivityGoodsAllList(Map<String, Object> params);

}
