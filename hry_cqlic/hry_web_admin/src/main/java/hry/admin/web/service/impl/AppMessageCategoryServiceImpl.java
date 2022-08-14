/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-07-05 10:20:33 
 */
package hry.admin.web.service.impl;

import hry.admin.web.model.AppMessageCategory;
import hry.admin.web.service.AppMessageCategoryService;
import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.QueryFilter;
import hry.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p> AppMessageCategoryService </p>
 * @author:         liuchenghui
 * @Date :          2018-07-05 10:20:33  
 */
@Service("appMessageCategoryService")
public class AppMessageCategoryServiceImpl extends BaseServiceImpl<AppMessageCategory, Long> implements AppMessageCategoryService{
	
	@Resource(name="appMessageCategoryDao")
	@Override
	public void setDao(BaseDao<AppMessageCategory, Long> dao) {
		super.dao = dao;
	}

	@Override
	public JsonResult switchOpen (Long[] ids, String type) {
		JsonResult result = new JsonResult();
		result.setSuccess(true);



		for(Long id : ids){
			AppMessageCategory category = super.get(id);
			if("open".equals(type)){
				QueryFilter queryFilter = new QueryFilter(AppMessageCategory.class);
				queryFilter.addFilter("isOpen=","1");
				queryFilter.addFilter("triggerPoint=",category.getTrigger());
				queryFilter.addFilter("messageCategory=",category.getMessageCategory());
				List<AppMessageCategory> list = super.find(queryFilter);
				if(list.size()>0){
					result.setSuccess(false);
					result.setMsg("同一触发点只能开启一个");
					return result;
				}

				category.setIsOpen(1);
			} else {
				category.setIsOpen(0);
			}
			super.update(category);
		}
		result.setMsg("修改成功");
		return result;
	}

	@Override
	public JsonResult removeCategory (String ids) {
		JsonResult result = new JsonResult();
		result.setSuccess(true);
		if (!StringUtils.isEmpty(ids)) {
			String[] idArr = ids.split(",");

			for(String id : idArr){
				AppMessageCategory category = super.get(new Long(id));
				category.setState(0);
				super.update(category);
			}
			result.setMsg("删除成功");
		}
		return result;
	}
}
