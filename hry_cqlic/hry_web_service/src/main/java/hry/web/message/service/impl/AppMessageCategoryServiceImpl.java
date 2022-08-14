/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年5月30日 下午5:45:20
 */
package hry.web.message.service.impl;


import hry.core.mvc.dao.base.BaseDao;
import hry.bean.JsonResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.web.app.model.AppMessageCategory;
import hry.web.message.service.AppMessageCategoryService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年5月30日 下午5:45:20
 */
@Service("appMessageCategoryService")
public class AppMessageCategoryServiceImpl extends BaseServiceImpl<AppMessageCategory, Long> implements AppMessageCategoryService{
	
	@Resource(name = "appMessageCategoryDao")
	@Override
	public void setDao(BaseDao<AppMessageCategory, Long> dao) {
		super.dao = dao;
	}

	
	/**
	 * 
	 * 同时删除多个对象
	 * 
	 */
	@Override
	public JsonResult removeCategory(Long[] ids) {
		
		JsonResult result = new JsonResult();
		result.setSuccess(true);
		
		for(Long id : ids){
			AppMessageCategory category = super.get(id);
			category.setState(0);
			super.update(category);
		}
		result.setMsg("全部修改成功");
		return result;
	}


	@Override
	public JsonResult switchOpen(Long[] ids ) {
		JsonResult result = new JsonResult();
		result.setSuccess(true);
		
		for(Long id : ids){
			AppMessageCategory category = super.get(id);
			if(category.getIsOpen() == 0){
				category.setIsOpen(1);
			}else {
				category.setIsOpen(0);
			}
			super.update(category);
		}
		result.setMsg("全部修改成功");
		return result;
	}



	

	
}