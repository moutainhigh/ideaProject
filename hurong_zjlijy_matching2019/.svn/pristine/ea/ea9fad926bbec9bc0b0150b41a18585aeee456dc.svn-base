/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2018-12-12 16:09:18 
 */
package hry.mall.goods.service.impl;

import hry.common.enums.RedisKeyEnum;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.goods.model.Goods;
import hry.mall.goods.model.ShopLanguage;
import hry.mall.goods.service.ShopLanguageService;
import hry.redis.common.utils.RedisService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p> ShopLanguageService </p>
 * @author:         zhouming
 * @Date :          2018-12-12 16:09:18  
 */
@Service("shopLanguageService")
public class ShopLanguageServiceImpl extends BaseServiceImpl<ShopLanguage, Long> implements ShopLanguageService {

	@Resource
	private RedisService redisService;

	@Resource(name="shopLanguageDao")
	@Override
	public void setDao(BaseDao<ShopLanguage, Long> dao) {
		super.dao = dao;
	}

	@Override
	public String getLanguageValue(String langKey, Long bindId, String langCode) {
		String redisKey = RedisKeyEnum.KeyEnum.MALL_DATE.getIndex()+":"+langKey+"_"+bindId;
		String mapStr = redisService.get(redisKey);
		String resultLang = "";
		if (mapStr != null) {
			Map<String, String> jsonMap = (Map<String, String>) JSONObject.fromObject(mapStr);
			if (!"".equals(jsonMap.get(langCode)))
				resultLang = jsonMap.get(langCode) != null ? jsonMap.get(langCode) : "";
		}
		return resultLang;
	}

	@Override
	public Goods getGoodsLanguage(String bindId, String langCode) {
		Goods goods = new Goods();
		goods.setId(Long.parseLong(bindId));
		String redisKey = RedisKeyEnum.KeyEnum.MALL_DATE.getIndex()+":goods_"+bindId+"_"+langCode;
		Map<String,String> redisGoods = redisService.getMap(redisKey);
		if (redisGoods != null && redisGoods.size() > 0) {
			goods.setGoodsName(redisGoods.get("goodsName"));
			goods.setGoodsSubtitle(redisGoods.get("goodsSubtitle"));
			goods.setGoodsImageMore(redisGoods.get("goodsImageMore"));
			goods.setGoodsVideo(redisGoods.get("goodsVideo"));
			goods.setGoodsBodyPc(redisGoods.get("goodsBodyPc"));
			goods.setGoodsBodyApp(redisGoods.get("goodsBodyApp"));
		} else {
			goods = null;
		}
		return goods;
	}
}
