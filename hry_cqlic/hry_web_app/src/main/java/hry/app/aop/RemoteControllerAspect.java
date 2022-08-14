package hry.app.aop;

import hry.bean.JsonResult;
import hry.util.StringUtil;
import hry.util.common.SpringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RemoteControllerAspect.java
 * lzy
 * 2018年9月13日14点20分
 */
@Aspect
@Component
public class RemoteControllerAspect {
	Logger logger = LoggerFactory.getLogger(RemoteControllerAspect.class);
	@Around("execution(hry.bean.JsonResult hry.*.remote.*.*(..))")
    public Object  doJsonResult(ProceedingJoinPoint  pdj) throws NoSuchMethodException, SecurityException {
		  Object result = null;
	        try {
	            result = pdj.proceed();
	    		JsonResult jsonResult = (JsonResult)result;
	    			if(jsonResult!=null){
	    				String msg=jsonResult.getMsg();
    		        	if(StringUtil.isNull(msg)){
    		        		boolean flag=isContainChinese(msg);
    		        		if(!flag){
    		        			jsonResult.setMsg(SpringUtil.diff(msg));
    		        		}
    		        	}
	    			}
	        } catch (Throwable e) {
	        }
	        return result;
    }

    /**
     * 判断字符串中是否包含中文
     * @param str
     * 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
}
