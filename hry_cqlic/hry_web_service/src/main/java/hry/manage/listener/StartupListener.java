/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Yuan Zhicheng
 * @version:      V1.0 
 * @Date:        2015年9月16日 上午11:04:39
 */
package hry.manage.listener;

import hry.core.listener.StartLoad;
import hry.util.klinedata.KlineDataUtil;
import hry.util.mybatis.ClassUtil;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.AppUtils;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public class StartupListener extends ContextLoaderListener {
	
	public void contextInitialized(ServletContextEvent event) {


		// 获取特定包下所有的类(包括接口和类)
		List<Class<?>> clsList = ClassUtil.getAllClassByPackageName("hry");

		//AnnotationUtil.validAnnotation(clsList);

		super.contextInitialized(event);
		//初始化应用程序工具类
		AppUtils.init(event.getServletContext());
		
//		System.out.println("--------------------------------------------------------------------------------");
//		System.out.println("---------------------------------加载应用app----------------------------------------");
//		System.out.println("------------------"+PropertiesUtils.APP.getProperty("app.loadApp")+"-----------------");
		//加载每个应用的启动方法
		StartLoad.reflectLoad(PropertiesUtils.APP.getProperty("app.loadApp"));

		KlineDataUtil.createTable();


		//KlineDataUtil.deleteNewklinedata();
	}

}
