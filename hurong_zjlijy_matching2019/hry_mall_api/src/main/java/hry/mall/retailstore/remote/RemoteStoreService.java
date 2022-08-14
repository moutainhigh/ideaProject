package hry.mall.retailstore.remote;

import hry.bean.ApiJsonResult;
import hry.bean.FrontPage;
import java.util.Map;

/**
 * @auther zhouming
 * @date 2019/6/10 15:26
 * @Version 1.0
 */
public interface RemoteStoreService {

    /**
     * 商品上架下架
     * */
    ApiJsonResult handleStoreGoods(Map<String, String> params);

    /**
     * 获取用户店铺信息
     * */
    ApiJsonResult getUserStore(Long memberId);

    /**
     * 编辑用户店铺信息
     * */
    ApiJsonResult editStoreInfo(Map<String, String> params);

    /**
     * 获取用户对应店铺的商品信息
     * */
    public FrontPage findStoreGoodsList(Map<String, String> params);

}
