/**
 * Copyright:    
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-09-28 21:20:08 
 */
package hry.admin.web.controller;


import com.alibaba.fastjson.JSONObject;
import hry.admin.web.model.AppConfig;
import hry.admin.web.model.AppFileUpload;
import hry.admin.web.service.AppConfigService;
import hry.admin.web.service.AppFileUploadService;
import hry.bean.BaseManageUser;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Copyright:   互融云
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-09-28 21:20:08 
 */
@Controller
@RequestMapping("/web/appfileupload")
public class AppFileUploadController extends BaseController<AppFileUpload, Long> {
	
	@Resource(name = "appFileUploadService")
	@Override
	public void setService(BaseService<AppFileUpload, Long> service) {
		super.service = service;
	}

	@Resource(name = "appConfigService")
	private AppConfigService appConfigService;

	@Resource
	private RedisService redisService;
	

	
	@RequestMapping(value="/add")
	@ResponseBody
	@CommonLog(name = "app新版本上传")
	public JsonResult add(HttpServletRequest request,AppFileUpload appFileUpload){
		BaseManageUser user = (BaseManageUser) SecurityUtils.getSubject().getSession().getAttribute("user");
		appFileUpload.setOperationUser(user.getUsername());
		//修改旧版本的状态，备注
		AppFileUploadService appFileUploadService = (AppFileUploadService) service;
		AppFileUpload appFileUpload1=new AppFileUpload();
		appFileUpload1.setAppType(appFileUpload.getAppType());
		appFileUploadService.uploadOther(appFileUpload1);
		//修改appconfig和redis中的值
		updateRedisRQcode(appFileUpload);
		return super.save(appFileUpload);
	}

	/**
	 * //修改appconfig和redis中的值
	 * @param appFileUpload
	 */
	private void updateRedisRQcode(AppFileUpload appFileUpload) {

		QueryFilter queryFilter = new QueryFilter(AppConfig.class);
		String configKey="android_RQCode";
		String appfileKey="android";
		if("1".equals(appFileUpload.getAppType())){
			configKey="ios_RQCode";
			appfileKey="ios";
		}
		redisService.save("AppFile:"+appfileKey,JSONObject.toJSONString(appFileUpload));
		queryFilter.addFilter("configKey=", configKey);
		AppConfig appConfig = appConfigService.get(queryFilter);
		if(appConfig!=null){
			appConfig.setValue(appFileUpload.getAppCodePath());
			appConfigService.update(appConfig);
			//修改完后更新缓存
			appConfigService.initCache();
		}
	}



	@RequestMapping(value="/modify/{id}")
	@ResponseBody
    @CommonLog(name = "app旧版本还原")
	public JsonResult modify(HttpServletRequest request,@PathVariable Long id){
        String remark = request.getParameter("remark");
        AppFileUpload appFileUpload = service.get(id);
        BaseManageUser user = (BaseManageUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        appFileUpload.setOperationUser(user.getUsername());
        appFileUpload.setAppStatus(1);
        appFileUpload.setRemark(remark);
        //修改旧版本的状态，备注
        AppFileUploadService appFileUploadService = (AppFileUploadService) service;
        AppFileUpload appFileUpload1=new AppFileUpload();
        appFileUpload1.setAppType(appFileUpload.getAppType());
        appFileUploadService.uploadOther(appFileUpload1);
        //修改appconfig和redis中的值
        updateRedisRQcode(appFileUpload);
		return super.update(appFileUpload);
	}
	@RequestMapping(value="/modifyVeision/{id}")
	@ResponseBody
	@CommonLog(name = "app修改版本号")
	public JsonResult modifyVeision(HttpServletRequest request,@PathVariable Long id){
		String appVersion = request.getParameter("appVersion");
		AppFileUpload appFileUpload = service.get(id);
		BaseManageUser user = (BaseManageUser) SecurityUtils.getSubject().getSession().getAttribute("user");
		appFileUpload.setOperationUser(user.getUsername());
		appFileUpload.setAppVersion(appVersion);

		//修改appconfig和redis中的值
		updateRedisRQcode(appFileUpload);
		return super.update(appFileUpload);
	}
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppFileUpload.class,request);
		filter.setOrderby("created desc");
		return super.findPage(filter);
	}
	
	
	
	
}
