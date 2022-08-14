package hry.mall.goods.remote;

import com.github.pagehelper.Page;
import hry.bean.ApiJsonResult;
import hry.bean.FrontPage;
import hry.common.enums.RedisKeyEnum;
import hry.mall.goods.model.Favorite;
import hry.mall.goods.model.Goods;
import hry.mall.goods.model.GoodsSpec;
import hry.mall.goods.service.FavoriteService;
import hry.mall.goods.service.GoodsService;
import hry.mall.goods.service.GoodsSpecService;
import hry.mall.goods.service.ShopLanguageService;
import hry.mall.order.service.OrderGoodsService;
import hry.util.PageFactory;
import hry.util.QueryFilter;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class RemoteFavoriteServiceImpl implements RemoteFavoriteService {
    @Resource
    private GoodsService goodsService;
    @Resource
    public GoodsSpecService goodsSpecService;
    @Resource
    public OrderGoodsService orderGoodsService;
    @Resource
    public FavoriteService favoriteService;
    @Resource
    public ShopLanguageService shopLanguageService;
	@Override
	public ApiJsonResult saveFavorite(Map<String, String> map) {
		// TODO Auto-generated method stub
		String goodsId=map.get("goodsId");
		String memberId=map.get("memberId");
		String isFavorite=map.get("isFavorite");
		Goods good=goodsService.get(Long.valueOf(goodsId));
		if(good==null){
			return new  ApiJsonResult().setSuccess(false).setMsg("shangpinbucunzai");
		}
		if("0".equals(isFavorite)){
			Favorite favorite=new Favorite();
			favorite.setGoodsId(Long.valueOf(goodsId));
			favorite.setGoodsName(good.getGoodsName());
			favorite.setMemberId(Long.valueOf(memberId));
			favorite.setGoodsImagesOne(good.getGoodsImageMore().split(",")[0]);
			
			QueryFilter filter=new QueryFilter(GoodsSpec.class);
			filter.addFilter("goodsId=", Long.valueOf(goodsId));
			filter.setOrderby("specGoodsPrice asc");
			List<GoodsSpec> list =goodsSpecService.find(filter);
			if(null!=list && list.size()>0){
				GoodsSpec spec=list.get(0);
				favorite.setSpecId(spec.getId());
				favorite.setGoodsStorePrice(spec.getSpecGoodsPrice());
				if(null!=spec.getSpecName() && !"".equals(spec.getSpecName()) && null!=spec.getSpecNameValue() &&!"".equals(spec.getSpecNameValue())){
					 //获取商品新拼装规格
		            String specInfo = orderGoodsService.specInfo(spec.getSpecName(),spec.getSpecNameValue());
		            favorite.setSpecInfo(specInfo);
				}
			}
			favoriteService.save(favorite);
		}else{
			   QueryFilter filter = new QueryFilter(Favorite.class);
		        filter.addFilter("memberId=",Long.valueOf(memberId));
		        filter.addFilter("goodsId=",Long.valueOf(goodsId));
		        filter.setOrderby("created asc");
		        List<Favorite> list = favoriteService.find(filter);
			    for(Favorite f:list){
			    	favoriteService.delete(f.getId());
			    }
		}
		return new  ApiJsonResult().setSuccess(true).setMsg("tijiaochenggong");
	}
	@Override
	public FrontPage findFavoriteList(Map<String, String> map) {
		// TODO Auto-generated method stub
	      Page<FrontPage> page = PageFactory.getPage(map);
	        String memberId = map.get("memberId").toString();
	        QueryFilter filter = new QueryFilter(Favorite.class);
	        filter.addFilter("memberId=",memberId);
	        filter.setOrderby("created asc");
	        List<Favorite> favoriteList = favoriteService.find(filter);
	        for (Favorite favorite : favoriteList) {
	            if (!"zh_CN".equals(map.get("locale"))) {
	                String goodsName = shopLanguageService.getLanguageValue(RedisKeyEnum.KeyEnum.GOODS_NAME.getIndex(), favorite.getGoodsId(), map.get("locale").toString());
	                favorite.setGoodsName(goodsName != "" ? goodsName : favorite.getGoodsName());
	            }
	        }
	        return  new FrontPage(favoriteList, page.getTotal(), page.getPages(), page.getPageSize());
	}
	@Override
	public ApiJsonResult deleteFavorite(Map<String, String> map) {
		// TODO Auto-generated method stub
		String favoriteIds=map.get("favoriteIds");
		if(null!=favoriteIds && !"".equals(favoriteIds)){
			String [] farry=favoriteIds.split(",");
			for(int i=0;i<farry.length;i++){
				favoriteService.delete(Long.valueOf(farry[i]));
			}
		}
		return new ApiJsonResult().setSuccess(true).setMsg("shanchuchenggong");
	}
	
}
