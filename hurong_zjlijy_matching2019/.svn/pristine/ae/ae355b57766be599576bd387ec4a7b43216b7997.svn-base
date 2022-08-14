package hry.core.mvc.model;

import java.io.FileReader;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;






import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.dubbo.rpc.RpcContext;

public class ModelUtil {
	
	/**
	 * //国际站  中国站区别
	 */
	public static final String CN = "cn";  //中国站
	public static final String EN = "en";  //国际站
	
	public static final String CNY = "cny";//人民币账户
	public static final String USD = "usd";//美民币账户
	
	public static Properties APP = null;
	
	public final static String SSOKEY = "app.sso";
	
	static {
		APP = new Properties();
		try {
			APP.load(new FileReader(ModelUtil.class
					.getClassLoader()
					.getResource("app.properties")
					.getPath()));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		HttpServletRequest request = getRequest();
		if(request!=null){
			String host = request.getHeader("HOST");
			if(!StringUtils.isEmpty(host)){
				//如果host不为空并且host包含app.website_en的值，则说明访问的是国际站
				if(host.contains(APP.getProperty("app.website_en"))){
					return EN;
				}
			}
		}else{
			String website = RpcContext.getContext().getAttachment("website");
			if(!StringUtils.isEmpty(website)){
				return website;
			}
		}
		return CN;
	}
	
	/**
	 * 获得httpservletrequest
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @author: Liu Shilei
	 * @param: @return
	 * @return: HttpServletRequest
	 * @Date : 2015年10月27日 下午7:02:55
	 * @throws:
	 */
	private static HttpServletRequest getRequest() {
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
			return request;
		} catch (Exception e) {
		}
		return null;
	}
	
}
