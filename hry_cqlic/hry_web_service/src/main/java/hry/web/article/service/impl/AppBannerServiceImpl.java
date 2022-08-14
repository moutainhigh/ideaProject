/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年8月20日 下午5:08:04
 */
package hry.web.article.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hry.core.constant.StringConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.model.AppConfig;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.redis.common.utils.RedisService;
import hry.web.app.dao.AppConfigDao;
import hry.web.app.model.AppBanner;
import hry.web.app.service.AppConfigService;
import hry.web.article.dao.AppBannerDao;
import hry.web.article.service.AppBannerService;
import hry.web.cache.CacheManageCallBack;
import hry.web.cache.CacheManageService;

import javax.annotation.Resource;
import javax.management.Query;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;


/**
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年8月20日 下午5:08:04 
 */
@Service("appBannerService")
public class AppBannerServiceImpl extends BaseServiceImpl<AppBanner, Long>
implements AppBannerService,CacheManageService{

	@Resource(name="redisService")
	private RedisService redisService;
	@Resource(name = "appBannerDao")
	@Override
	public void setDao(BaseDao<AppBanner, Long> dao) {
		super.dao = dao;
	}

	@Override
	public void initCache(CacheManageCallBack callback) {
		QueryFilter filter=new QueryFilter(AppBanner.class);
		filter.addFilter("status=", 0);
		filter.addFilter("isShow=", 1);
		List<AppBanner> list=this.find(filter);
		if(list!=null&&list.size()>0){
			redisService.save(StringConstant.CACHE_BANNER, JSON.toJSONString(list));
		}
		callback.callback(AppBannerService.class, StringConstant.CACHE_BANNER, "banner缓存");
		
	}

}
