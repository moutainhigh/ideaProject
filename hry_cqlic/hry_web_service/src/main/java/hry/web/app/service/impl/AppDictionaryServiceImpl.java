/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2015年10月27日 下午6:17:27
 */
package hry.web.app.service.impl;

import com.alibaba.fastjson.JSON;
import hry.bean.JsonResult;
import hry.core.constant.CodeConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;
import hry.web.app.model.AppDictionary;
import hry.web.app.service.AppDictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2015年10月27日 下午6:17:27 
 */
@Service("appDictionaryService")
public class AppDictionaryServiceImpl extends BaseServiceImpl<AppDictionary, Long> implements AppDictionaryService{
	@Resource(name = "appDictionaryDao")
	@Override
	public void setDao(BaseDao<AppDictionary, Long> dao) {
		super.dao = dao;
	}
	@Resource(name = "redisService")
	private RedisService redisService;
	@Override
	public JsonResult removeDic(String id) {
		
		JsonResult jsonResult = new JsonResult();
		
		if(StringUtils.isEmpty(id)){
			jsonResult.setSuccess(false);
			jsonResult.setMsg("id不能为空");
		}else{
			diguiRemove(Long.valueOf(id));
			jsonResult.setSuccess(true);
		}
		
		return jsonResult;
	}

	public void diguiRemove(Long id){
		AppDictionary t = get(id);
		t.setIsOld(1);
		this.update(t);
		dicToredis(t.getpDicKey());//缓存到redis
		QueryFilter queryFilter = new QueryFilter(AppDictionary.class);
		queryFilter.addFilter("pDicKey=", t.getDicKey());
		List<AppDictionary> list = find(queryFilter);
		if(list!=null&&list.size()>0){
			for(AppDictionary appDictionary : list){
				diguiRemove(appDictionary.getId());
			}
		}
	}

	/* 添加数据字典
	 * 
	 */
	@Override
	public String addDic(AppDictionary appDictionary) {
		
		appDictionary.setIsOld(0);
		appDictionary.setLeaf(1);
		if(Long.valueOf("0").equals(appDictionary.getPid())){
			appDictionary.setPath("0");
			appDictionary.setPid(Long.valueOf("0"));
			appDictionary.setpDicKey("root");
			appDictionary.setIsOld(0);
			appDictionary.setOrderNo(1);
			save(appDictionary);
			
		}else{
			AppDictionary pappDictionary=super.get(appDictionary.getPid());
			appDictionary.setPath(pappDictionary.getPath()+"."+pappDictionary.getId());
			
			pappDictionary.setLeaf(0);
			super.save(appDictionary);
			super.update(pappDictionary);
			
		}
		dicToredis(appDictionary.getpDicKey());//缓存到redis
		return CodeConstant.CODE_SUCCESS;
	}
	@Override
	public void dicToredis(String pDicKey){
		
		QueryFilter filter = new QueryFilter(AppDictionary.class);
		 filter.addFilter("pDicKey=", pDicKey);
		 filter.addFilter("isOld=", "0");
		 filter.addFilter("saasId=", ContextUtil.getSaasId());
		 filter.setOrderby("orderNo asc");
		 List<AppDictionary> listr=this.find(filter);
		 String jsonString = JSON.toJSONString(listr); 
		 redisService.save("DIC:"+pDicKey, jsonString);
	}
	
	@Override
	public JsonResult move(String id, String type) {
		
		JsonResult jsonResult = new JsonResult();
		if(StringUtils.isEmpty(id)){
			jsonResult.setSuccess(false);
		}else if(StringUtils.isEmpty(type)){
			jsonResult.setSuccess(false);
		}else{
			AppDictionary appDictionary1 = this.get(Long.valueOf(id));
			
			QueryFilter filter = new QueryFilter(AppDictionary.class);
			filter.addFilter("pDicKey=",appDictionary1.getpDicKey());
			if("up".equals(type)){
				filter.addFilter("orderNo<", appDictionary1.getOrderNo());
				filter.setOrderby("orderNo desc");
			}else{
				filter.addFilter("orderNo>",  appDictionary1.getOrderNo());
				filter.setOrderby("orderNo asc");
			}
			AppDictionary appDictionary2 = get(filter);
			
			if(appDictionary2!=null){
				int orderNo = appDictionary1.getOrderNo();
				int orderNo2 = appDictionary2.getOrderNo();
				
				appDictionary1.setOrderNo(orderNo2);
				appDictionary2.setOrderNo(orderNo);
				update(appDictionary1);
				update(appDictionary2);
			}
			dicToredis(appDictionary1.getpDicKey());//缓存到redis
		}
		
		return jsonResult;
	}



	

}
