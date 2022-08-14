package hry.mall.goods.service.impl;

import hry.bean.JsonResult;
import hry.mall.goods.model.SearchGoods;
import hry.mall.goods.service.GoodsService;
import hry.mall.goods.service.SearchGoodsService;
import hry.util.PropertiesUtils;
import hry.util.StringUtil;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service("searchGoodsService")
public class SearchGoodsServiceImpl implements SearchGoodsService {

    @Resource
    private GoodsService goodsService;

    @Override
    public List<SearchGoods> findSearchGoodsList(Map<String, String> searchMap, int page, int rows) {
        SolrQuery query = new SolrQuery();
        String solrUrl = PropertiesUtils.APP.getProperty("solr.url");
        HttpSolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();
        //设置查询条件
        if(StringUtil.isNull(searchMap.get("keyword")))
            query.setQuery(searchMap.get("keyword"));
        else
            query.setQuery("*:*");

        // 添加商品类型查询
        if(StringUtil.isNull(searchMap.get("specialType"))){
            query.addFilterQuery("special_type:" + searchMap.get("specialType"));
        }
        //增加分类查询
        if(StringUtil.isNull(searchMap.get("gcId"))){
            query.addFilterQuery("gc_id:" + searchMap.get("gcId"));
        }
        //增加品牌查询
        if(StringUtil.isNull(searchMap.get("brandId"))){
            query.addFilterQuery("brand_id:" + searchMap.get("brandId"));
        }
        //增加商户查询
        if(StringUtil.isNull(searchMap.get("merchantId"))){
            query.addFilterQuery("merchant_id:" + searchMap.get("merchantId"));
        }
        //设置人气排序
        if(StringUtil.isNull(searchMap.get("goodsClick"))){
            if("asc".equals(searchMap.get("goodsClick"))){
                query.setSort("goods_click", SolrQuery.ORDER.asc);
            } else if("desc".equals(searchMap.get("goodsClick"))){
                query.setSort("goods_click", SolrQuery.ORDER.desc);
            }
        }
        //设置商品价格排序
        if(StringUtil.isNull(searchMap.get("goodsPrice"))){
            if("asc".equals(searchMap.get("goodsPrice"))){
                query.setSort("spec_goods_price", SolrQuery.ORDER.asc);
            } else if("desc".equals(searchMap.get("goodsPrice"))){
                query.setSort("spec_goods_price", SolrQuery.ORDER.desc);
            }
        }
        //设置销量排序
        if(StringUtil.isNull(searchMap.get("saleNum"))){
            if("asc".equals(searchMap.get("saleNum"))){
                query.setSort("sale_num", SolrQuery.ORDER.asc);
            } else if("desc".equals(searchMap.get("saleNum"))){
                query.setSort("sale_num", SolrQuery.ORDER.desc);
            }
        }
        //设置分页条件
        if(page < 0){
            page = 0;
        }
        query.setStart(page);
        query.setRows(rows);
        //设置默认搜索域
        query.set("df", "goods_name");

        //开启高亮显示，显示颜色为红色
        query.setHighlight(true);
        query.setHighlightSimplePre("<em style='color:red'>");
        query.setHighlightSimplePost("</em>");
        List<SearchGoods> searchGoodsList = new ArrayList<>();

        try {
            // 查询结果
            QueryResponse response = solrClient.query(query);
            SolrDocumentList results = response.getResults();	// 结果集
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();//高亮结果集
            // 遍历查询结果
            for (SolrDocument doc : results) {
                SearchGoods goods = new SearchGoods();
                goods.setId(Long.parseLong(doc.get("id").toString()));
                goods.setGcId(Long.parseLong(doc.get("gc_id").toString()));
                goods.setGoodsSubtitle(doc.get("goods_subtitle").toString());
                if (doc.get("brand_id") != null) {
                    goods.setBrandId(Long.parseLong(doc.get("brand_id").toString()));
                }
                goods.setGoodsImageMore(doc.get("goods_image_more").toString());
                goods.setGoodsClick(Integer.parseInt(doc.get("goods_click").toString()));
                goods.setSaleNum(Integer.parseInt(doc.get("sale_num").toString()));
                goods.setSpecGoodsPrice(new BigDecimal(doc.get("spec_goods_price").toString()));
                goods.setSpecialType(Integer.parseInt(doc.get("special_type").toString()));
                //取高亮
                List<String> list = highlighting.get(doc.get("id")).get("goods_name");
                String goodsName = "";
                if( list != null && list.size() > 0){
                    goodsName = list.get(0);
                }else{
                    goodsName = (String)doc.get("goods_name");
                }
                goods.setGoodsName(goodsName);
                searchGoodsList.add(goods);
            }
            searchMap.put("total", String.valueOf(results.getNumFound()) );
        } catch (Exception e){
            System.out.println("solr查询异常："+e.getMessage());
        }
        return searchGoodsList;
    }

    @Override
    public void updateSolrGoods(Long goodsId) {
        SearchGoods searchGoods = goodsService.findSearchGoodsById(goodsId);
        try {
            String solrUrl = PropertiesUtils.APP.getProperty("solr.url");
            HttpSolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();
            solrClient.deleteById(goodsId.toString());
            //创建文档对象
            SolrInputDocument document = new SolrInputDocument();
            //向文档对象中添加域
            document.addField("id", searchGoods.getId());
            document.addField("gc_id", searchGoods.getGcId());
            document.addField("goods_name", searchGoods.getGoodsName());
            document.addField("goods_subtitle", searchGoods.getGoodsSubtitle());
            document.addField("brand_id", searchGoods.getBrandId());
            document.addField("goods_image_more", searchGoods.getGoodsImageMore());
            document.addField("goods_click", searchGoods.getGoodsClick());
            document.addField("sale_num", searchGoods.getSaleNum());
            document.addField("spec_goods_price", Float.parseFloat(searchGoods.getSpecGoodsPrice().toString()));
            document.addField("special_type", searchGoods.getSpecialType());
            document.addField("merchant_id", searchGoods.getMerchantId());
            //把文档对象写入索引库
            solrClient.add(document);
            //提交修改
            solrClient.commit();
        } catch (Exception e){
            System.out.println("solr加入异常："+e.getMessage());
        }
    }

    @Override
    public JsonResult updateMultiData(String goodsId, Map<String, Object> maps) {
        String solrUrl = PropertiesUtils.APP.getProperty("solr.url");
        HttpSolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();
        try {
            Set<String> keys = maps.keySet();
            //创建文档对象
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", goodsId);
            for (String key : keys) {
                HashMap<String, Object> oper = new HashMap<>();
                oper.put("set", maps.get(key));
                document.addField(key, oper);
            }
            solrClient.add(document);
            solrClient.commit();
            System.out.println("solr更新成功");
        } catch (Exception e){
            System.out.println("solr更新异常："+e.getMessage());
        }
        return new JsonResult().setSuccess(true);
    }
}
