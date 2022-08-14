package hry.mall.goods.remote;

import hry.bean.ApiJsonResult;
import hry.bean.FrontPage;
import java.util.Map;

public interface RemoteFavoriteService {
	/**
	 * 新增商品收藏
	 * @param map
	 * @return
	 */
	public ApiJsonResult saveFavorite(Map<String, String> map);
	/**
	 * 查询收藏列表
	 * @param map
	 * @return
	 */
	 public FrontPage findFavoriteList(Map<String, String> map);
	 /**
	  * 根据id删除收藏
	  * @param map
	  * @return
	  */
	 public ApiJsonResult deleteFavorite(Map<String, String> map);
}
