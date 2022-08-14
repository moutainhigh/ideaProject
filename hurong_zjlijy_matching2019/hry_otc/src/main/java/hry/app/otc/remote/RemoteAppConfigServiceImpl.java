/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年4月29日 下午7:02:06
 */
package hry.app.otc.remote;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.app.web.app.service.AppConfigService;
import hry.bean.JsonResult;
import hry.core.constant.StringConstant;
import hry.otc.manage.remote.api.RemoteAppConfigService;
import hry.otc.manage.remote.model.core.AppConfig;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年4月29日 下午7:02:06 
 */
public class RemoteAppConfigServiceImpl implements RemoteAppConfigService {

	@Resource
	private AppConfigService appConfigService;
	
	@Resource(name="redisService")
    private RedisService redisService;
	
	@Override
	public String getConfigInfo(String name, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<AppConfig> getConfigInfo(String type) {
		String saasId = RpcContext.getContext().getAttachment("saasId");
		QueryFilter filter=new QueryFilter(AppConfig.class);
		filter.addFilter("configkey=", type);
		filter.setSaasId(saasId);
		return appConfigService.find(filter);
	}


	
	@Override
	public List<AppConfig> getConfigInfoByKey(String key) {
		String saasId = RpcContext.getContext().getAttachment("saasId");
		QueryFilter filter=new QueryFilter(AppConfig.class);
		filter.addFilter("typekey=", key);
		filter.setSaasId(saasId);
		return appConfigService.find(filter);
	}


	
	@Override
	public String getValueByKey(String key) {
		
		 String value="";
		
		
		 if(null!=key&&!"".equals(key)){
			   String data="";
			   data=redisService.get(StringConstant.CONFIG_CACHE+":all");
			   if(null!=data&&!"".equals(data)){
				   JSONObject obj=JSON.parseObject(data);
				if(obj.containsKey(key)){
					 value=obj.get(key).toString();
				}
			   }
		 }
		if (null==value||"".equals(value)) {
			  QueryFilter filter=new QueryFilter(AppConfig.class);
				filter.addFilter("configkey=",key );
			
				List<AppConfig> list=appConfigService.find(filter);
				if(null!=list&&list.size()>0){
					value=list.get(0).getValue();
				}
		}
	
		return value;
	}
	


	
	@Override
	public String getFinanceByKey(String key) {
		String val="";
		String string=redisService.get(StringConstant.CONFIG_CACHE+":financeConfig");
		JSONArray obj=JSON.parseArray(string);
		for(Object o:obj){
		JSONObject	 oo=JSON.parseObject(o.toString());
		if(oo.getString("configkey").equals(key)){
			val=oo.getString("value");
		}
		}
		return val;
	}

	@Override
	public JsonResult dataByType(String type) {
		JsonResult jsonResult=new JsonResult();
	     String data="";
		 Map<String,String> map=new HashMap<String, String>();
		 if(null!=type&&!"".equals(type)){
			   data=redisService.get(StringConstant.CONFIG_CACHE+":"+type);
			   if(null!=data&&!"".equals(data)){
				   JSONArray obj=JSON.parseArray(data);
					for(Object o:obj){
					JSONObject	 oo=JSON.parseObject(o.toString());
					map.put(oo.getString("configkey"), oo.getString("value"));
			        }
			   }else{
				   QueryFilter filter=new QueryFilter(AppConfig.class);
					filter.addFilter("typekey=", type);
				
					List<AppConfig> list=appConfigService.find(filter);
					for (AppConfig config:list ) {
						map.put(config.getConfigkey(), config.getValue());
					}
			   }
				
				data=JSON.toJSONString(map);
				jsonResult.setObj(data);
	      }
	return jsonResult;

}





	@Override
	public AppConfig getAppConfigByConfigKey(String configkey) {
		QueryFilter filter=new QueryFilter(AppConfig.class);
		filter.addFilter("configkey=", configkey);
		return appConfigService.get(filter);
	}


	@Override
	public void updateAppConfig(AppConfig appConfig) {
		appConfigService.update(appConfig);
	}
	
	@Override
	public List<AppConfig> findByTypeKey(String typeKey) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("typekey", typeKey);
		return appConfigService.getConfig(map);
	}

	@Override
	public String getFinanceLendByKey(String key) {
		String val="";
		String string=redisService.get(StringConstant.CONFIG_CACHE+":financeLendConfig");
		try {
			JSONObject obj=JSON.parseObject(string);
			if(null!=key&&obj.get(key)!=null){
				val=obj.get(key).toString();
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		return val;
	}

	@Override
	public BigDecimal getCurrentCoinMoney(String coin1,String coin2) {
		String string=redisService.get(coin1+"_"+coin2+":currentExchangPrice");
		if(null!=string){
			return new BigDecimal(string);
		}
		return new BigDecimal(0);
	}

}
