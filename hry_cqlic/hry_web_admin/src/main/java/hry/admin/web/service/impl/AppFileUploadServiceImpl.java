/**
 * Copyright:   
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-09-28 21:20:08 
 */
package hry.admin.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import hry.admin.web.dao.AppFileUploadDao;
import hry.admin.web.model.AppFileUpload;
import hry.admin.web.service.AppFileUploadService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p> appFileUploadService </p>
 * @author:         sunyujie
 * @Date :          2018-09-28 21:20:08  
 */
@Service("appFileUploadService")
public class AppFileUploadServiceImpl extends BaseServiceImpl<AppFileUpload, Long> implements AppFileUploadService {
	
	@Resource(name="appFileUploadDao")
	@Override
	public void setDao(BaseDao<AppFileUpload, Long> dao) {
		super.dao = dao;
	}
	@Resource
	private RedisService redisService;

	@Override
	public void uploadOther(AppFileUpload appFileUpload) {
		AppFileUploadDao appFileUploadDao=(AppFileUploadDao)dao;
		appFileUploadDao.uploadOther(appFileUpload);
	}

	@Override
	public void initRedis() {

		QueryFilter filter = new QueryFilter(AppFileUpload.class);
		filter.addFilter("appStatus=",1);
		List<AppFileUpload> appFileUploadList = find(filter);
		for (AppFileUpload  appfile:appFileUploadList) {
			String appfileKey="android";
			if("1".equals(appfile.getAppType())){
				appfileKey="ios";
			}
			redisService.save("AppFile:"+appfileKey,JSONObject.toJSONString(appfile));
		}
	}
}
