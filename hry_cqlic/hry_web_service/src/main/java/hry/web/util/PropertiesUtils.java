package hry.web.util;

import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


public class PropertiesUtils {


	public static Properties APP = null;
	public static Properties RMI = null;
	public static Properties MULTIPLTEMAIL = null;
	
	public final static String SSOKEY = "app.sso";
	
	public final static String LOADWEBKEY = "app.loadweb";
	
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
		
		MULTIPLTEMAIL = new Properties();
		try {
			InputStream ins = PropertiesUtils.class.getClassLoader().getResourceAsStream("multipleEmail.properties");
			MULTIPLTEMAIL.load(ins);
			
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
	public static ArrayList<String> getAppSSO(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		Set<Object> keySet = APP.keySet();
		Iterator<Object> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			if(key.contains(SSOKEY)){
				arrayList.add(APP.getProperty(key));
			}
		}
		return arrayList;
	}
	
	
	/**
	 * 获得加载了多少个站点
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @return
	 * @return: ArrayList<String> 
	 * @Date :          2016年7月5日 上午11:15:57   
	 * @throws:
	 */
	public static Map<String,String> getLoadWeb(){
		
		Map<String,String> map = new HashMap<String,String>();
		Set<Object> keySet = APP.keySet();
		Iterator<Object> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			if(key.contains(LOADWEBKEY)){
				String[] split = APP.getProperty(key).split("=");
				map.put(split[0], split[1]);
			}
		}
		return map;
	}
	

}
