/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.exchange.product.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.bean.JsonResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.exchange.product.model.ExProductParameter;
import hry.exchange.product.service.ExProductParameterService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月24日 下午2:04:29
 */
@Service("exProductParameterService")
public class ExProductParameterServiceImpl extends BaseServiceImpl<ExProductParameter, Long>
		implements ExProductParameterService {

//	private ExProductParameter exProductParameter2;

	@Resource(name = "exProductParameterDao")
	@Override
	public void setDao(BaseDao<ExProductParameter, Long> dao) {
		super.dao = dao;
	}

	/**
	 * 
	 * 添加一个参数 保证只有一个是开启状态 
	 * 
	 * typer  如果是 1 表示保存 如果是 2 表示 修改 
	 * 
	 */
	@Override
	public JsonResult saveExProductParameter(
			ExProductParameter exProductParameter,Integer type) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setSuccess(true);
		
		String name = exProductParameter.getName();
		if("" == name || null == name){
			jsonResult.setSuccess(false);
			jsonResult.setMsg("币的代码不能为空"); 
			return jsonResult;
		}
		
		QueryFilter filter = new QueryFilter(ExProductParameter.class);
		filter.addFilter("name=", name);
		filter.addFilter("state=", 1);
		List<ExProductParameter> list = super.find(filter);
		if(list.size()>0){
			for(ExProductParameter exProductParameter2 : list){
				exProductParameter2.setState(0);
				super.update(exProductParameter2);
			}
		}
		if(1 == type){
			super.save(exProductParameter);
			jsonResult.setSuccess(true);
			jsonResult.setMsg("保存成功"); 
			return jsonResult;
		}else if(2 == type){
			super.update(exProductParameter);
			
			jsonResult.setSuccess(true);
			jsonResult.setMsg("修改成功成功"); 
			return jsonResult;
		}else{
			// super.save(exProductParameter);
			jsonResult.setSuccess(false);
			jsonResult.setMsg("系统不知道怎么操作"); 
			return jsonResult;
		}
	}
	
	
}
