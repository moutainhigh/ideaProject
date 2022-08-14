package hry.mall.goods.remote;

import hry.bean.ApiJsonResult;
import hry.bean.FrontPage;
import java.util.Map;
public interface RemoteCartService {

    /**
     * 购物车添加
     * */
    ApiJsonResult saveCart(Map<String, Object> params);

    /**
     * 修改购物车数量
     * **/
    ApiJsonResult updateCartCount(Map<String, Object> params);

    /**
     * 购物车列表
     * **/
    FrontPage findCartList(Map<String, String> params);

    /**
     * 购物车---多商品删除
     */
    ApiJsonResult deleteCarts(String cartIds);
}
