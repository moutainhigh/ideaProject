package hry.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * 
 * @name:BeanToBean.class
 * @description:实体管理工具
 * @author:闫帅兵
 * @date:2018年4月3日 上午9:40:27
 * @location:util.BeanToBean.class
 * Copyright 2015 by hurongsoft.com. All Right Reserved
 */
public class BeanToBean {
	/**
	 * 
	 * @title: convertBean2Bean  
	 * @description: 实体属性赋值
	 * @author: 闫帅兵
	 * @date:2018年4月3日 上午9:40:48  
	 * @param from 赋值实体
	 * @param to  被赋值实体
	 * @return
	 */
	public static Object convertBean2Bean(Object from, Object to) {
		try {
		BeanInfo beanInfo = Introspector.getBeanInfo(to.getClass());
		PropertyDescriptor[] ps = beanInfo.getPropertyDescriptors();
		Class<?> classType = from.getClass();
		for (PropertyDescriptor p : ps) {
		Method getMethod = p.getReadMethod();
		Method setMethod = p.getWriteMethod();
		if (getMethod != null && setMethod != null) {
		try {
		Method fromGetMethod = classType.getMethod(getMethod.getName());
		Object result = fromGetMethod.invoke(from);
		setMethod.invoke(to, result);
		} catch (Exception e) {
		continue;
		}
		}
		}


		} catch (Exception e) {
		e.printStackTrace();
		}
		return to;
		}
}
