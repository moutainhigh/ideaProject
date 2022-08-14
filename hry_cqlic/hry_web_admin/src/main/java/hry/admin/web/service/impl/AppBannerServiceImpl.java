/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 14:34:44 
 */
package hry.admin.web.service.impl;

import com.alibaba.fastjson.JSON;
import hry.admin.web.model.AppBanner;
import hry.admin.web.service.AppBannerService;
import hry.core.constant.StringConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p> AppBannerService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 14:34:44  
 */
@Service("appBannerService")
public class AppBannerServiceImpl extends BaseServiceImpl<AppBanner, Long> implements AppBannerService{
	
	@Resource(name="appBannerDao")
	@Override
	public void setDao(BaseDao<AppBanner, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private RedisService redisService;

	@Override
	public void initCache() {
		QueryFilter filter=new QueryFilter(AppBanner.class);
		filter.addFilter("status=", 0);
		filter.addFilter("isShow=", 1);
		List<AppBanner> list=this.find(filter);
		if(list!=null&&list.size()>0){
			redisService.save(StringConstant.CACHE_BANNER, JSON.toJSONString(list));
		}
	}
}
