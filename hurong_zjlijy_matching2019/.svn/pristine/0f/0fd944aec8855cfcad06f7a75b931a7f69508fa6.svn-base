/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:44:42 
 */
package hry.mall.goods.service;

import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.mall.goods.model.Goods;
import hry.mall.goods.model.SearchGoods;
import hry.util.QueryFilter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * <p> GoodsService </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:44:42 
 */
public interface GoodsService  extends BaseService<Goods, Long> {

    //多表分页查询
    public PageResult findPageBySql(QueryFilter filter);

    //获取solr说需要商品集合
    public List<SearchGoods> findSearchGoodsList();

    //根据Id获取solr商品
    SearchGoods findSearchGoodsById(Long goodsId);

    //根据goodsId查询商品
    Goods findGoodsById(Long goodsId);

    FrontPage findGoodsList(Map<String, String> params);

    //根据coinCode获取币种汇率
    BigDecimal getCoinRate(String coinCode);

    /***
     * 商品添加---规格添加
     * **/
    public JsonResult addGoodsSpec(Map<String, Object> paramMap);

    /***
     * 商品添加---规格值添加
     * **/
    public JsonResult addGoodsSpecValue(Map<String, Object> paramMap);

    /***
     * 商品添加---规格删除
     * **/
    public JsonResult delGoodsSpec(Map<String, Object> paramMap);
    /***
     * 商品添加---规格值删除
     * **/
    public JsonResult delGoodsSpecValue(Map<String, Object> paramMap);

    /**
     * 商品添加---保存
     * */
    public JsonResult saveGoods(Goods goods, Map<String, Object> paramMap);

    /**
     * 商品添加---第三部保存
     * */
    public JsonResult saveGoodsThree(Goods goods, Map<String, Object> paramMap);

    /**
     * 保存规格集
     * **/

    public JsonResult saveGoodsSpec(Goods goods, String goodsSpecList);
    /**
     * 查询商品列表
     * @param params
     * @return
     */
    public List<Goods>   findIntegralGoodsList(Map<String, Object> params);

    /**
     * 查询活动商品列表
     * */
    public List<SearchGoods> findActivityGoodsList(Map<String, Object> params);

    /**
     * 查询所有活动商品 （进行活动的商品）
     * */
    public List<SearchGoods> findActivityGoodsAllList(Map<String, Object> params);
    
}
