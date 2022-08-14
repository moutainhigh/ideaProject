/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2015年11月4日 下午1:22:49
 */
package hry.util;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Liu Shilei
 * @Date : 2015年11月4日 下午1:22:49
 */
public class PropertiesUtils {

	public static Properties APP = null;
	
	
	
	static {
		APP = new Properties();
		try {
			APP.load(new FileReader(PropertiesUtils.class
					.getClassLoader()
					.getResource("app.properties")
					.getPath()));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * front_oauth 获得sso同步地址
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @return
	 * @return: ArrayList<String> 
	 * @Date :          2016年7月5日 上午11:15:57   
	 * @throws:
	 */
	public static String getOuathStr(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		Set<Object> keySet = APP.keySet();
		Iterator<Object> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			if(key.contains("oauth.filter")){
				arrayList.add(APP.getProperty(key));
			}
		}
		
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0 ; i < arrayList.size() ;i++){
			sb.append(arrayList.get(i));
			if(i!=arrayList.size()-1){
				sb.append(",");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 返回网站维护页面
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @return
	 * @return: String 
	 * @Date :          2017年7月15日 下午6:18:47   
	 * @throws:
	 */
	public static String getfixUrl(){
		
		Set<Object> keySet = APP.keySet();
		Iterator<Object> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			if(key.contains("site.fix")){
				return APP.getProperty(key);
			}
		}
		return APP.getProperty("app.url");
	}
	
	
}
