package hry.mall.goods.remote;

import hry.bean.ApiJsonResult;
import hry.bean.FrontPage;
import java.util.Map;
public interface RemoteGoodsService {

    /**
     * 分页查询楼层信息
     * */
    FrontPage findFloorGoodsList(Map<String, String> params);

    FrontPage findGoodsClassList(Map<String, String> params);

    ApiJsonResult getGoodsDetail(Map<String, Object> params);

    //根据coinCode获取币种汇率
    ApiJsonResult getCoinRate(String coinCode);

    //获取规格商品
    ApiJsonResult getGoodsSpec(Long goodsId, String specNameValue, Integer goodsSum, String locale);

    //分页查询商品评价
    FrontPage findEvaluateList(Map<String, String> params);

    //统计数量
    public ApiJsonResult countEvaluateGoods(Map<String, Object> params);

    /**
     * 商品搜索service
     * */
    public FrontPage findSearchGoodsList(Map<String, String> params);

    public ApiJsonResult findPortionGoods(String goodsType, String locale, String memberId);
    /**
     * 推荐或者人气查询更多
     * @param params
     * @return
     */
    public FrontPage findPagePortionGoods(Map<String, String> params);
    
    /**
     * 分页查询商品信息
     * */
    FrontPage findIntegralGoodsList(Map<String, String> params);
    
    public ApiJsonResult findBlendPay(String locale);

    /**
     * 查看活动类表
     * */
    public ApiJsonResult findActivityList(Map<String, String> params);

    /**
     * 根据位置key获取对应位置信息
     * */
    public ApiJsonResult findSiteActivityList(Map<String, String> params);

    /**
     * 团购商品
     * */
    public FrontPage findGroupGoodsList(Map<String, String> params);

    /**
     * 抢购商品
     * */
    public FrontPage findVieGoodsList(Map<String, String> params);

    /**
     * 抢购活动时段模板列表
     * **/
    public ApiJsonResult findTimeTempList(Map<String, String> params);

    /**
     * 获取商户信息
     * **/
    public ApiJsonResult getMerchant(String merchantId);
}
