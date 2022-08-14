/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2018-12-12 16:09:18 
 */
package hry.mall.goods.service;
import hry.core.mvc.service.base.BaseService;
import hry.mall.goods.model.Goods;
import hry.mall.goods.model.ShopLanguage;

/**
 * <p> ShopLanguageService </p>
 * @author:         zhouming
 * @Date :          2018-12-12 16:09:18 
 */
public interface ShopLanguageService extends BaseService<ShopLanguage, Long> {
    /***
     * 获取语种对应的value
     * @param langKey 类型key
     *                商品key： goodsName 商品名称  goodsSubtitle 商品副标题
     *                          goodsClass 商品分类名称  shopFloor 楼层名称
     * @param bindId 绑定对应的id
     * @param langCode 语种
     * @return
     */
    public String getLanguageValue(String langKey, Long bindId, String langCode);

    //查询商品Redis value值
    Goods getGoodsLanguage(String bindId, String langCode);
}
