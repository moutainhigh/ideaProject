package hry.util.common;

import com.alibaba.dubbo.rpc.RpcContext;
import hry.util.HryCache;
import hry.util.properties.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public class ContextUtil {

	/**
	 * //国际站  中国站区别
	 */
	public static final String CN = "cn";  //中国站
	public static final String EN = "en";  //国际站

	public static final String CNY = "cny";//人民币账户
	public static final String USD = "usd";//美民币账户

	public static final String USER = "user";//redis的user对象

	/**
	 * 获得域名加应用数据
	 * @param request
	 * @return
	 */
	public static String getAppPath(HttpServletRequest request){
		String path = request.getContextPath();
		String basePath = getBasePath(request) + path;
		return basePath;
	}

	/**
	 * 获得域名路径
	 * @param request
	 * @return
	 */
	public static String getBasePath(HttpServletRequest request){
		String scheme = request.getScheme();
		String serverName = request.getServerName();
		int port = request.getServerPort();
		String basePath = scheme + "://" + serverName + ":" + port ;
		return basePath;
	}

	/**
	 * 获得默认交易账户类型
	 * @return
	 */
	public static String getCurrencyType(){
		if(CN.equals(getWebsite())){
			return CNY;
		}
		return USD;
	}

	/**
	 * 获得站点类型
	 * @return
	 */
	public static String getWebsite() {
		try {
			HttpServletRequest request = getRequest();
			if(request!=null){
				String host = request.getHeader("HOST");
				if(!StringUtils.isEmpty(host)){
					//如果host不为空并且host包含app.website_en的值，则说明访问的是国际站
					if(host.contains(PropertiesUtils.APP.getProperty("app.website_en"))){
						return EN;
					}else{
						return CN;
					}
				}else{
					return CN;
				}
			}
			return CN;
		} catch (Exception e) {
		}
		return CN;
	}

	/**
	 * 获得httpservletrequest
	 * <p>
	 * TODO
	 * @return: HttpServletRequest
	 * @throws:
	 */
	public static HttpServletRequest getRequest() {
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
			return request;
		} catch (Exception e) {
		}
		return null;

	}




	/**
	 * 设置远程调用时传的账户类型参数
	 */
	public static void setRemoteCurrencyType(){
		RpcContext.getContext().setAttachment("currencyType", getCurrencyType());
	}

	/**
	 * 设置远程调用时传的账户类型参数
	 */
	public static void setRemoteWebsite(){
		RpcContext.getContext().setAttachment("website", getWebsite());
	}

	/**
	 * 获得多语言词条
	 * @param locale  语种
	 * @param key
	 * @return
	 */
	public static String getLanguage(String locale,String key) {
		String value ;
		Map<String, String> map = HryCache.pc_cache_language.get(locale);
		if(map!=null) {
			if(!StringUtils.isEmpty(map.get(key))) {
				value = map.get(key);
			}else {
				value = "未找到词条"+locale+"_"+key;
			}
		}else {
			value = "未找到词条"+locale+"_"+key;
		}
		return value;
	}

}
