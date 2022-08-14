/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年8月1日 下午8:58:57
 */
package hry.web.cache.area.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;






import com.alibaba.fastjson.JSON;

import hry.core.constant.StringConstant;
import hry.redis.common.utils.RedisService;
import hry.web.cache.CacheManageCallBack;
import hry.web.cache.CacheManageService;
import hry.web.dictionary.model.AppDicMultilevel;
import hry.web.dictionary.service.AppDicMultilevelService;
import org.apache.log4j.Logger;


/**
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年8月1日 下午8:58:57 
 */

public class AppCaheAreaServiceImpl implements  CacheManageService{
	private static Logger logger = Logger.getLogger(AppCaheAreaServiceImpl.class);
	@Resource(name = "redisService")
	private RedisService redisService;

	@Resource(name = "appDicMultilevelService")
	private AppDicMultilevelService appDicMultilevelService;
	@Override
	public void initCache(CacheManageCallBack callback) {
           
  
         List<Map>  l=new ArrayList<Map>();
         List<AppDicMultilevel>  list=appDicMultilevelService.findListBypDicKey("area");
		 for(AppDicMultilevel  pdic:list){
			 Map<String,Object> pMap=new HashMap<String, Object>();
			 pMap.put("key", pdic.getDicKey());
			 pMap.put("province", pdic.getItemName());
			List<AppDicMultilevel>	cityList=appDicMultilevelService.findListBypDicKey(pdic.getDicKey());
			
			List<Map>  cl=new ArrayList<Map>();
		    if(cityList.size()==0){
			   pMap.put("cities", null);
	     	}else{
			      for(AppDicMultilevel  dic    :cityList){
				    Map<String,Object> map=new HashMap<String, Object>();
				    map.put("city",dic.getItemName());
				    cl.add(map);
		    	}
			 pMap.put("cities", cl);
		   }
		
		 l.add(pMap);
		 }
		
		 String data=JSON.toJSONString(l);
		 redisService.save(StringConstant.AREA_CACHE, data);
		 callback.callback(AppCaheAreaServiceImpl.class,StringConstant.AREA_CACHE,"地区信息缓存");
		
		logger.error("====="+JSON.toJSONString(l));;
	
	}

}
