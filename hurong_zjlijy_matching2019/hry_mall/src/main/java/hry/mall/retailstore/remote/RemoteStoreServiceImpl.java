package hry.mall.retailstore.remote;


import com.github.pagehelper.Page;
import hry.bean.ApiJsonResult;
import hry.bean.FrontPage;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.goods.model.Goods;
import hry.mall.goods.model.SearchGoods;
import hry.mall.retailstore.model.CustomerGrowth;
import hry.mall.retailstore.model.CutomerStore;
import hry.mall.retailstore.model.MemberLevel;
import hry.mall.retailstore.model.StoreGoods;
import hry.mall.retailstore.service.CustomerGrowthService;
import hry.mall.retailstore.service.CutomerStoreService;
import hry.mall.retailstore.service.MemberLevelService;
import hry.mall.retailstore.service.StoreGoodsService;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @auther zhouming
 * @date 2019/6/10 15:31
 * @Version 1.0
 */
public class RemoteStoreServiceImpl extends BaseServiceImpl<Goods, Long> implements RemoteStoreService {

    @Resource
    private CutomerStoreService cutomerStoreService;

    @Resource
    private StoreGoodsService storeGoodsService;

    @Resource
    private CustomerGrowthService customerGrowthService;

    @Resource
    private MemberLevelService memberLevelService;

    @Override
    public void setDao(BaseDao<Goods, Long> baseDao) {

    }

    @Override
    public ApiJsonResult handleStoreGoods(Map<String, String> params) {
        String memberId = params.get("memberId");
        String goodsId = params.get("goodsId");
        String isShow = params.get("isShow");
        QueryFilter filter = new QueryFilter(CutomerStore.class);
        filter.addFilter("memberId=", memberId);
        CutomerStore cutomerStore = cutomerStoreService.get(filter);
        if (cutomerStore != null) {
            QueryFilter filter1 = new QueryFilter(StoreGoods.class);
            filter1.addFilter("storeId=", cutomerStore.getId());
            filter1.addFilter("goodsId=", goodsId);
            StoreGoods storeGoods = storeGoodsService.get(filter1);
            if (storeGoods != null) {
                // isShow : 1:上架、2: 下架
                storeGoods.setGoodsShow(Integer.parseInt(isShow));
                storeGoodsService.update(storeGoods);
            } else {
                storeGoods = new StoreGoods();
                storeGoods.setMemberId(Long.parseLong(memberId));
                storeGoods.setGoodsId(Long.parseLong(goodsId));
                storeGoods.setStoreId(cutomerStore.getId());
                // GoodsShow : 1 上架 2： 下架
                storeGoods.setGoodsShow(1);
                storeGoodsService.save(storeGoods);
            }
        }
        return new ApiJsonResult().setSuccess(true).setMsg("caozuochenggong");
    }

    @Override
    public ApiJsonResult getUserStore(Long memberId) {
        QueryFilter filter = new QueryFilter(CustomerGrowth.class);
        filter.addFilter("memberId=",memberId);
        // 获取用户店铺等级信息
        CustomerGrowth customerGrowth = customerGrowthService.get(filter);
        if (customerGrowth != null) {
            // 获取等级列表
            QueryFilter filter1 = new QueryFilter(MemberLevel.class);
            filter1.setOrderby("minGrowth ASC");
            List<MemberLevel> memberLevelList = memberLevelService.find(filter1);
            // 获取用户下一等级
            if (memberLevelList != null && memberLevelList.size() > 0){
                for (int i=0; i < memberLevelList.size(); i++) {
                    MemberLevel memberLevel =  memberLevelList.get(i);
                    // 确认当前等级
                    if (customerGrowth.getLevelId() == memberLevel.getId()) {
                        // 判断目前用户等级是否为最高等级
                        if (i == (memberLevelList.size()-1)) {
                            customerGrowth.setNextLevelId(customerGrowth.getLevelId());
                            customerGrowth.setNextLevelName(customerGrowth.getLevelName());
                        } else {
                            customerGrowth.setNextLevelId(memberLevel.getId());
                            customerGrowth.setNextLevelName(memberLevel.getName());
                        }
                    }
                }
            }
            // 获取店铺信息
            QueryFilter filter2 = new QueryFilter(CutomerStore.class);
            filter2.addFilter("memberId=", memberId);
            CutomerStore cutomerStore = cutomerStoreService.get(filter2);
            if (cutomerStore != null) {
                customerGrowth.setStoreId(cutomerStore.getId());
                customerGrowth.setStoreName(cutomerStore.getName());
                customerGrowth.setStoreImg(cutomerStore.getImage());
                customerGrowth.setDescription(cutomerStore.getDescription());
            }
        }
        return new ApiJsonResult().setSuccess(true).setObj(customerGrowth);
    }

    @Override
    public ApiJsonResult editStoreInfo(Map<String, String> params) {
        CutomerStore store = new CutomerStore();
        store.setId(Long.parseLong(params.get("storeId")));
        if (!"".equals(params.get("storeName"))) {
            store.setName(params.get("storeName"));
        }
        if (!"".equals(params.get("description"))) {
            store.setDescription(params.get("description"));
        }
        if (!"".equals(params.get("storeImg"))) {
            store.setImage(params.get("storeImg"));
        }
        cutomerStoreService.update(store);
        return new ApiJsonResult().setSuccess(true).setMsg("caozuochenggong");
    }

    @Override
    public FrontPage findStoreGoodsList(Map<String, String> params) {
        Page<SearchGoods> page = PageFactory.getPage(params);
        List<SearchGoods> storeGoodsList = storeGoodsService.findStoreGoodsList(params);
        return new FrontPage(storeGoodsList, page.getTotal(), page.getPages(), page.getPageSize());
    }
}
