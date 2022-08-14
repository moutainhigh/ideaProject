/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2017-03-01 14:17:02 
 */
package hry.admin.dic.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.admin.dic.model.AppDic;
import hry.admin.dic.service.AppDicService;
import hry.core.constant.StringConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> SysDicService </p>
 * @author:         liushilei
 * @Date :          2017-03-01 14:17:02  
 */
@Service("appDicService")
public class AppDicServiceImpl extends BaseServiceImpl<AppDic, Long> implements AppDicService{
	
	@Resource(name="appDicDao")
	@Override
	public void setDao(BaseDao<AppDic, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private RedisService redisService;

	@Override
	public String getNameByPkeyAndValue(String pkey, String value) {
		if(!StringUtils.isEmpty(pkey)&&!StringUtils.isEmpty(value)){
			QueryFilter filter = new QueryFilter(AppDic.class);
			filter.addFilter("pkey=", pkey);
			filter.addFilter("value=", value);
			
			AppDic sysDic = get(filter);
			if(sysDic!=null){
				return sysDic.getName();
			}
		}
		return "";
	}

	@Override
	public void flushRedis() {
		QueryFilter filter = new QueryFilter(AppDic.class);
		filter.addFilter("type=", 2);
		filter.addFilter("pkey!=","addressarea");
		List<AppDic> list = find(filter);

		if(list!=null&&!list.isEmpty()){
			for(AppDic appDic : list){
				QueryFilter f = new QueryFilter(AppDic.class);
				f.addFilter("pkey=",appDic.getMkey());
				List<AppDic> appDics = find(f);
				if(appDics!=null&&!appDics.isEmpty()) {
					redisService.hset("new_app_dic", appDic.getMkey(), JSON.toJSONString(appDics));
				}
			}
		}



	}

	@Override
	public void initAreaCache() {
		List<Map>  l=new ArrayList<Map>();
		QueryFilter filter2 = new QueryFilter(AppDic.class);
		filter2.addFilter("type=", 2);
		filter2.addFilter("pkey=","addressarea");
		List<AppDic> list = find(filter2);
		for(AppDic  pdic:list){
			Map<String,Object> pMap=new HashMap<String, Object>();
			pMap.put("key", pdic.getValue());
			pMap.put("province", pdic.getName());
			QueryFilter f = new QueryFilter(AppDic.class);
			f.addFilter("pkey=",pdic.getMkey());
			List<AppDic> appDics = find(f);

			List<Map>  cl=new ArrayList<Map>();
			if(appDics.size()==0){
				pMap.put("cities", null);
			}else{
				for(AppDic  dic    :appDics){
					Map<String,Object> map=new HashMap<String, Object>();
					map.put("city",dic.getName());
					cl.add(map);
				}
				pMap.put("cities", cl);
			}

			l.add(pMap);
		}

		String data=JSON.toJSONString(l);
		redisService.save(StringConstant.AREA_CACHE, data);


	}


}
