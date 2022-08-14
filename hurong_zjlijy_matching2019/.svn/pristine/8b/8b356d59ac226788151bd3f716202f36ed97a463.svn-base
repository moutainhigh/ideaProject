package hry.mall.goods.remote;

import com.github.pagehelper.Page;
import hry.bean.ApiJsonResult;
import hry.bean.FrontPage;
import hry.common.enums.RedisKeyEnum;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.goods.model.Cart;
import hry.mall.goods.model.Goods;
import hry.mall.goods.model.GoodsSpec;
import hry.mall.goods.service.CartService;
import hry.mall.goods.service.GoodsService;
import hry.mall.goods.service.GoodsSpecService;
import hry.mall.goods.service.ShopLanguageService;
import hry.mall.order.service.OrderGoodsService;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.StringUtil;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class RemoteCartServiceImpl extends BaseServiceImpl<Cart, Long> implements RemoteCartService{

    @Resource
    private CartService cartService;

    @Resource
    private GoodsService goodsService;

    @Resource
    private GoodsSpecService goodsSpecService;

    @Resource
    private ShopLanguageService shopLanguageService;

    @Resource
    private OrderGoodsService orderGoodsService;

    @Override
    public void setDao(BaseDao<Cart, Long> baseDao) {

    }

    @Override
    public ApiJsonResult saveCart(Map<String, Object> params) {
        String goodsId = params.get("goodsId").toString();
        String specId = params.get("specId").toString();
        String count = params.get("count").toString();
        String memberId = params.get("memberId").toString();

        //获取商品
        Goods goods = goodsService.get(Long.parseLong(goodsId));

        //获取商品规格
        GoodsSpec goodsSpec = goodsSpecService.get(Long.parseLong(specId));

        //定义购物车
        Cart cart = new Cart();
        cart.setGoodsId(Long.parseLong(goodsId));
        cart.setGoodsName(goods.getGoodsName());
        cart.setMemberId(Long.parseLong(memberId));
        cart.setGoodsNum(Integer.parseInt(count));

        // 如商品有活动 则购物测保存商品活动id
        if (null!= params.get("activityGoodsId") && !"".equals(params.get("activityGoodsId"))) {
            cart.setActivityGoodsId(Long.parseLong(params.get("activityGoodsId").toString()));
        }

        //存储商品默认图片
        cart.setGoodsImagesOne(goods.getGoodsImageMore().split(",")[0]);
        cart.setGoodsStorePrice(goodsSpec.getSpecGoodsPrice());
        cart.setSpecId(Long.parseLong(specId));
        if (StringUtil.isNull(goodsSpec.getSpecName()) && StringUtil.isNull(goodsSpec.getSpecNameValue())) {
            //获取商品新拼装规格
            String specInfo = orderGoodsService.specInfo(goodsSpec.getSpecName(),goodsSpec.getSpecNameValue());
            cart.setSpecInfo(specInfo);
        }
        //获取该用户下的购物车信息
        QueryFilter filter = new QueryFilter(Cart.class);
        filter.addFilter("memberId=",memberId);
        filter.addFilter("goodsId=",goodsId);
        filter.addFilter("specId=",specId);
        List<Cart> carts = cartService.find(filter);
        if (carts != null && carts.size() >0) {
            for (Cart cart1 : carts) {
                Integer num = 0;
                //加入购物车 直接数数量加一
                num = cart1.getGoodsNum()+Integer.parseInt(count);
                //加入购物车同一商品数量不能超过100
                if (num <= 100){
                    cart.setGoodsNum(num);
                } else {
                    return new ApiJsonResult().setSuccess(false).setMsg("gouwuchetongjianshangpinguoduo").setCode("1");
                }
                cart.setId(cart1.getId());
                cartService.update(cart);
            }
        } else {
            cartService.save(cart);
        }
        return new ApiJsonResult().setSuccess(true).setMsg("gouwuchetianjiachenggong").setCode("8888");
    }

    @Override
    public ApiJsonResult updateCartCount(Map<String, Object> params) {
        String specId = params.get("specId").toString();
        String cartId = params.get("cartId").toString();
        String count = params.get("count").toString();
        //获取规格商品
        GoodsSpec goodsSpec = goodsSpecService.get(Long.parseLong(specId));
        //获取购物车商品
        Cart cart = cartService.get(Long.parseLong(cartId));
        if ( null == goodsSpec || goodsSpec.getSpecGoodsPrice().compareTo(cart.getGoodsStorePrice())!=0) {
            return new ApiJsonResult().setSuccess(false).setMsg("jiagebiandong").setCode("1");
        }
        if (Integer.valueOf(count) > goodsSpec.getSpecGoodsStorage()) {
            return new ApiJsonResult().setSuccess(false).setMsg("shangpinkucunbuzu").setCode("2");
        }
        //修改购物车数量
        cart.setGoodsNum(Integer.parseInt(count));
        cartService.update(cart);
        return new ApiJsonResult().setSuccess(true).setMsg("xiugaichenggong").setCode("8888");
    }

    @Override
    public FrontPage findCartList(Map<String, String> params) {
        Page<Cart> page = PageFactory.getPage(params);
        String memberId = params.get("memberId").toString();
        QueryFilter filter = new QueryFilter(Cart.class);
        filter.addFilter("memberId=",memberId);
        filter.setOrderby("created asc");
        List<Cart> cartList = cartService.find(filter);
        for (Cart cart : cartList) {
            if (!"zh_CN".equals(params.get("locale"))) {
                String goodsName = shopLanguageService.getLanguageValue(RedisKeyEnum.KeyEnum.GOODS_NAME.getIndex(), cart.getGoodsId(), params.get("locale").toString());
                cart.setGoodsName(goodsName != "" ? goodsName : cart.getGoodsName());
            }
        }
        return  new FrontPage(cartList, page.getTotal(), page.getPages(), page.getPageSize());
    }

    @Override
    public ApiJsonResult deleteCarts(String cartIds) {
        String[] cartIdList = cartIds.split(",");
        for (String cartId : cartIdList) {
             Long id = Long.parseLong(cartId);
            cartService.delete(id);
        }
        return new ApiJsonResult().setSuccess(true).setMsg("shanchuchenggong").setCode("8888");
    }
}
