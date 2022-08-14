package hry.util;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringUtil.java
 * @author denghf
 * 2017年11月9日 下午6:19:59
 */
@Component
public class SpringUtil implements ApplicationContextAware {

	// Spring应用上下文环境  
    private static ApplicationContext applicationContext;  
  
    /** 
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境 
     *  
     * @param applicationContext 
     */  
    public void setApplicationContext(ApplicationContext applicationContext) {  
    	SpringUtil.applicationContext = applicationContext;
    }  
  
    /** 
     * @return ApplicationContext 
     */  
    public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }  
  
    public static <T>T getBean(String name)   {  
    	try {
    		return (T) applicationContext.getBean(name);
		} catch (NoSuchBeanDefinitionException e) {
		}
    	return null;
    }  
}
