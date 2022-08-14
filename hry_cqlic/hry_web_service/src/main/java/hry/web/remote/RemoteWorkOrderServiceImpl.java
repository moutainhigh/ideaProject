package hry.web.remote;

import hry.bean.JsonResult;
import hry.manage.remote.RemoteWorkOrderService;
import hry.manage.remote.model.AppWorkOrder;
import hry.manage.remote.model.AppWorkOrderCategory;
import hry.manage.remote.model.base.FrontPage;
import hry.util.idgenerate.IdGenerate;
import hry.web.message.service.AppWorkOrderCategoryService;
import hry.web.message.service.AppWorkOrderService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 我的工单
 * @author tzw
 *
 * 2017年7月19日
 */
public class RemoteWorkOrderServiceImpl implements RemoteWorkOrderService{

	@Resource
	private AppWorkOrderService appWorkOrderService;
	@Resource
	private AppWorkOrderCategoryService appWorkOrderCategoryService;
	
	

	@Override
	public FrontPage findWorkOrder(Map<String, String> params) {
		return appWorkOrderService.findWorkOrder(params);
	}


	@Override
	public AppWorkOrder get(Long sid) {
		AppWorkOrder appWorkOrderNew=new AppWorkOrder();
		hry.web.message.model.AppWorkOrder appWorkOrder = appWorkOrderService.get(sid);
		appWorkOrderNew.setCategoryId(appWorkOrder.getCategoryId());
		appWorkOrderNew.setCategoryName(appWorkOrder.getCategoryName());
		appWorkOrderNew.setLanguage(appWorkOrder.getLanguage());
		appWorkOrderNew.setWorkOrderNo(appWorkOrder.getWorkOrderNo());
		appWorkOrderNew.setWorkContent(appWorkOrder.getWorkContent());
		appWorkOrderNew.setCustomerId(appWorkOrder.getCustomerId());
		appWorkOrderNew.setReplyContent(appWorkOrder.getReplyContent());
		appWorkOrderNew.setReplyMode(appWorkOrder.getReplyMode());
		appWorkOrderNew.setState(appWorkOrder.getState());
		return appWorkOrderNew;
	}


	@Override
	public List<AppWorkOrderCategory> findWorkOrderCategoryList(Map<String, String> params) {
		return appWorkOrderCategoryService.findWorkOrderCategoryList(params);
	}

	@Override
	public JsonResult deleteBatch(String ids) {
		
		return appWorkOrderService.removeByIds(ids);
	}


	@Override
	public JsonResult add(AppWorkOrder appWorkOrder) {
		hry.web.message.model.AppWorkOrder appWorkOrderAdd=new hry.web.message.model.AppWorkOrder();
		appWorkOrderAdd.setCategoryId(appWorkOrder.getCategoryId());
		appWorkOrderAdd.setCategoryName(appWorkOrder.getCategoryName());
		appWorkOrderAdd.setLanguage(appWorkOrder.getLanguage());
		//TODO
		appWorkOrderAdd.setWorkOrderNo(IdGenerate.transactionNum("15"));
		appWorkOrderAdd.setWorkContent(appWorkOrder.getWorkContent());
		appWorkOrderAdd.setCustomerId(appWorkOrder.getCustomerId());
		appWorkOrderAdd.setLanguageDic(appWorkOrder.getLanguageDic());
		JsonResult arg1 = new JsonResult();
		try {
			appWorkOrderService.save(appWorkOrderAdd);
			arg1.setMsg("添加成功");
			arg1.setSuccess(Boolean.valueOf(true));
			arg1.setObj(appWorkOrderAdd);
			return arg1;
		} catch (Exception arg2) {
			arg2.printStackTrace();
			arg1.setMsg("添加失败");
			arg1.setSuccess(Boolean.valueOf(false));
			return arg1;
		}
	}


	
}
