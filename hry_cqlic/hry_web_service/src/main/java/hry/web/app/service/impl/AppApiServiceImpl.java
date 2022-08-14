/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Gao Mimi
 * @version:   V1.0 
 * @Date:      2015年10月10日  18:41:55
 */
package hry.web.app.service.impl;


import hry.core.constant.StringConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.model.AppConfig;
import hry.bean.PageResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.util.serialize.Mapper;
import hry.redis.common.utils.RedisService;
import hry.web.app.dao.AppConfigDao;
import hry.web.app.model.AppApi;
import hry.web.app.service.AppApiService;
import hry.web.app.service.AppConfigService;
import hry.web.cache.CacheManageCallBack;
import hry.web.cache.CacheManageService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年8月22日 下午4:23:55
 */
@Service("appApiService")
public class AppApiServiceImpl extends BaseServiceImpl<AppApi, Long> implements AppApiService{
	
	
	@Resource(name = "appApiDao")
	@Override
	public void setDao(BaseDao<AppApi, Long> dao) {
		super.dao = dao;
	}

	
	
	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		
		return null;
	}

	
	

}