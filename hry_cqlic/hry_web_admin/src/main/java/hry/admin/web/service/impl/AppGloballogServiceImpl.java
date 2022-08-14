/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-09-20 17:47:17 
 */
package hry.admin.web.service.impl;

import hry.admin.web.model.AppGloballog;
import hry.admin.web.service.AppGloballogService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * <p> AppGloballogService </p>
 * @author:         tianpengyu
 * @Date :          2018-09-20 17:47:17  
 */
@Service("appGloballogService")
public class AppGloballogServiceImpl extends BaseServiceImpl<AppGloballog, Long> implements AppGloballogService{
	
	@Resource(name="appGloballogDao")
	@Override
	public void setDao(BaseDao<AppGloballog, Long> dao) {
		super.dao = dao;
	}
	@Override
	public void deleteOnTime() {
		Calendar cal = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
		cal.add(Calendar.DAY_OF_MONTH, -3);//取当前日期的前一天.

		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println("正在删除过期日志（3天前）");

		System.out.println("yesterday is:"+format.format(cal.getTime()));
		QueryFilter queryFilter = new QueryFilter(AppGloballog.class);
		queryFilter.addFilter("created<",format.format(cal.getTime()));
		delete(queryFilter);
	}

}
