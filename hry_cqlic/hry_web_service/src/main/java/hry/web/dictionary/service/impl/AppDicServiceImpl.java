/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-28 15:48:48 
 */
package hry.web.dictionary.service.impl;

import hry.util.QueryFilter;
import hry.web.dictionary.model.AppDic;
import hry.web.dictionary.service.AppDicService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> AppDicService </p>
 * @author:         liuchenghui
 * @Date :          2018-06-28 15:48:48  
 */
@Service("appDicService")
public class AppDicServiceImpl extends BaseServiceImpl<AppDic, Long> implements AppDicService{
	
	@Resource(name="appDicDao")
	@Override
	public void setDao(BaseDao<AppDic, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<AppDic> findByPkey(String pkey){
		QueryFilter filter = new QueryFilter(AppDic.class);
		if(!StringUtils.isEmpty(pkey)){
			filter.addFilter("pkey=", pkey);
		}

		List<AppDic> appDics = this.find(filter);
		return appDics;
	}

	@Override
	public List<String> findValByPkey(String pkey){

		List<AppDic> appDics = this.findByPkey(pkey);
		List<String> list1 = new ArrayList<>();
		if(appDics!=null && appDics.size()>0){
			appDics.forEach(app ->{
				list1.add(app.getValue());
			});
		}
		return list1;
	}

}
