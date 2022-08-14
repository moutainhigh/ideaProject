package hry.mall.goods.service;

import hry.bean.JsonResult;
import hry.mall.goods.model.SearchGoods;
import java.util.List;
import java.util.Map;

public interface SearchGoodsService {

    /**
     *根据条件搜索
     *  keyword : 商品名称
     *  gcId : 商品分类
     *  brandId : 商品品牌
     *  goodsClick（人气） : asc 正序 desc 倒叙
     *  goodsPrice（价格） : asc 正序 desc 倒叙
     *  saleNum（销量）: asc 正序 desc 倒叙
     */
    List<SearchGoods> findSearchGoodsList(Map<String, String> searchMap, int page, int rows);

    public void updateSolrGoods(Long goodsId);

    // 更新solr字段
    public JsonResult updateMultiData(String goodsId, Map<String, Object> maps);

}
