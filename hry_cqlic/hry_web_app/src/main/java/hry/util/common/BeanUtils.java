package hry.util.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map.Entry;

/**
 * 
 * <p> TODO</p>
 * @author:         Shangxl 
 * @Date :          2017年7月21日 下午7:41:46
 */
public class BeanUtils {
	/**
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param obj1 接收对象
	 * @param:    @param obj2 传值对象
	 * @param:    @return
	 * @return: JSONObject 
	 * @Date :          2017年7月21日 下午7:56:04   
	 * @throws:
	 */
	public static Object convert(Object obj1, Object obj2) {
		try {
			JSONObject b1 = JSON.parseObject(JSON.toJSONString(obj1));
			JSONObject b2 = JSON.parseObject(JSON.toJSONString(obj2));
			for (Entry<String, Object> entry : b2.entrySet()) {
				b1.put(entry.getKey(), entry.getValue());
			}
			return JSON.parseObject(JSON.toJSONString(b1), obj1.getClass());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
