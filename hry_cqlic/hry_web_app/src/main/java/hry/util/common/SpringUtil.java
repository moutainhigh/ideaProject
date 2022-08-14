package hry.util.common;

import hry.util.HryCache;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;

import java.util.Locale;
import java.util.Map;

/**
 * <p>
 * 此类不供业务系统调用
 * </p>
 *
 * @author: Liu Shilei
 * @Date : 2015年9月17日 上午11:52:41
 */

public class SpringUtil implements ApplicationContextAware {

	// Spring应用上下文环境
	private static ApplicationContext applicationContext;

	/**
	 * 实现ApplicationContextAware接口的回调方法，设置上下文环境
	 *
	 * @param applicationContext
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringUtil.applicationContext = applicationContext;
	}

	/**
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取注入对象
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @author: Liu Shilei
	 * @param: @param
	 *             name
	 * @param: @return
	 * @return: Object
	 * @Date : 2015年9月17日 上午11:56:57
	 * @throws:
	 */
	public static <T> T getBean(String name) {
		try {
			return (T) applicationContext.getBean(name);
		} catch (NoSuchBeanDefinitionException e) {
		}
		return null;
	}

	/**
	 * 国际化
	 *
	 * @param language
	 *            当前语言
	 * @param code
	 *            key值
	 * @return
	 */
	public static String diff(String code) {

		Locale locale = LocaleContextHolder.getLocale();

		String value = "";
		Map<String, String> map = HryCache.pc_cache_language.get(locale.toString());
		if(map!=null) {
			if(!StringUtils.isEmpty(map.get(code))) {
				value = map.get(code);
			}else {
				value = "未找到词条"+locale.toString()+"_"+code;
			}
		}else {
			value = "未找到词条"+locale.toString()+"_"+code;
		}
		return value;
//     String message = "";
//		try {
//			String[] split = code.split("~");
//			if (split.length > 1) {
//				message = applicationContext.getMessage(split[0], new Object[] { split[1] }, locale);
//				if (message == null || "".equals(message)) {
//					return "错误001";
//				}
//			} else {
//				message = applicationContext.getMessage(code, null, locale);
//				if (message == null || "".equals(message)) {
//					return "错误001";
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "错误002";
//
//		}
//		return message;
	}

	/**
	 * 国际化
	 *
	 * @param language
	 *            当前语言
	 * @param code
	 *            key值
	 * @return
	 */
	public static String diff(String code, String language) {
		String value = "";
		Map<String, String> map = HryCache.pc_cache_language.get(language);
		if(map!=null) {
			if(!StringUtils.isEmpty(map.get(code))) {
				value = map.get(code);
			}else {
				value = "未找到词条"+language+"_"+code;
			}
		}else {
			value = "未找到词条"+language+"_"+code;
		}
		return value;
	}
}
