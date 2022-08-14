/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-04-25 17:55:39 
 */
package hry.web.message.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.customer.user.service.AppCustomerService;
import hry.manage.remote.model.base.FrontPage;
import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;
import hry.web.AppMessageTemplate.model.AppMessageTemplate;
import hry.web.AppMessageTemplate.service.AppMessageTemplateService;
import hry.web.app.model.AppMessageCategory;
import hry.web.app.model.vo.MessageListVo;
import hry.web.message.dao.AppWorkOrderDao;
import hry.web.message.model.AppWorkOrder;
import hry.web.message.service.AppMessageCategoryService;
import hry.web.message.service.AppMessageService;
import hry.web.message.service.AppWorkOrderService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p> AppWorkOrderService </p>
 * @author:         sunyujie
 * @Date :          2018-04-25 17:55:39  
 */
@Service("appWorkOrderService")
public class AppWorkOrderServiceImpl extends BaseServiceImpl<AppWorkOrder, Long> implements AppWorkOrderService{
	
	@Resource(name="appWorkOrderDao")
	@Override
	public void setDao(BaseDao<AppWorkOrder, Long> dao) {
		super.dao = dao;
	}
	
	@Resource
	public AppMessageCategoryService appMessageCategoryService;
	
	@Resource
	public AppMessageTemplateService appMessageTemplateService;
	
	@Resource
	public AppMessageService appMessageService;

	@Override
	public PageResult queryByPage(QueryFilter filter,AppWorkOrder appWorkOrder) {
		PageResult pageResult = new PageResult();
		Page<MessageListVo> page = null;
		if (filter.getPageSize().compareTo(Integer.valueOf(-1)) == 0) {
			// pageSize = -1 时
			// pageHelper传pageSize参数传0查询全部
			page = PageHelper.startPage(filter.getPage(), 0);
		} else {
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		// 查询数据
		((AppWorkOrderDao)dao).queryByPage(appWorkOrder);
		
		pageResult.setRows(page.getResult());
		// 设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		
		return pageResult ;
	}
	@Override
	public AppWorkOrder get(Long id) {
		return ((AppWorkOrderDao)dao).get(id);
	}

	@Override
	public int queryCount(Long categoryId) {
		// TODO Auto-generated method stub
		return ((AppWorkOrderDao)dao).queryCount(categoryId);
	}

	@Override
	public FrontPage findWorkOrder(Map<String, String> params) {
		Page page = getPage(params);
		// 查询方法
		
		List<AppWorkOrder> list = ((AppWorkOrderDao) dao).queryByList(params);

		return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
	}
	public static Page getPage(Map<String, String> map) {
		Page page = null;
		Integer offset = 0;
		Integer limit = 10;
		if (!StringUtils.isEmpty(map.get("offset"))) {
			offset = Integer.valueOf(map.get("offset"));
		}
		if (!StringUtils.isEmpty(map.get("limit"))) {
			limit = Integer.valueOf(map.get("limit"));
		}
		if (limit == -1) {
			page = PageHelper.startPage(offset / limit + 1, 0);
		} else {
			page = PageHelper.startPage(offset / limit + 1, limit);
		}
		return page;
	}

	@Override
	public JsonResult removeByIds(String ids) {
		JsonResult arg1 = new JsonResult();
		if (super.deleteBatch(ids)) {
			arg1.setSuccess(Boolean.valueOf(true));
			arg1.setMsg("删除成功");
			return arg1;
		} else {
			arg1.setSuccess(Boolean.valueOf(false));
			arg1.setMsg("删除失败");
			return arg1;
		}
	}

	@Override
	public void sendMessageToCustomer(AppWorkOrder appWorkOrder) {
		//回复方式  0邮件回复  1系统消息  2短信回复  3电话回复
		Integer replyMode = appWorkOrder.getReplyMode();
		if( appWorkOrder.getCustomerId() != null ){
			AppCustomerService appCustomerService = (AppCustomerService) ContextUtil.getBean("appCustomerService");
			hry.customer.user.model.AppCustomer appCustomer = appCustomerService.get(appWorkOrder.getCustomerId());
			if( appCustomer != null){
				switch (replyMode){ 
				//给用户发送邮件告知工单处理结果
				case 0 :  
					break; 
					
				//给用户发送系统消息告知工单处理结果
				case 1 : 
					// 发送提币短信通知
					//获取提币模板消息
					QueryFilter amcQueryFilter = new QueryFilter(AppMessageCategory.class);
					amcQueryFilter.addFilter( "keywords=", "3" );//5为提币
					AppMessageCategory appMessageCategory = appMessageCategoryService.get(amcQueryFilter);
					if(appMessageCategory == null){
						return;
					}
					QueryFilter amtQueryFilter = new QueryFilter(AppMessageTemplate.class);
					amtQueryFilter.addFilter( "templateId=", appMessageCategory.getId() );//5为提币
					AppMessageTemplate appMessageTemplate = appMessageTemplateService.get(amtQueryFilter);
					if(appMessageTemplate == null){
						return;
					}
					//封装通知的消息
					appMessageService.saveWorkOrderMessage(appCustomer,appMessageCategory,appMessageTemplate,appWorkOrder.getWorkOrderNo());
					break;
					
				//给用户发送短信告知工单处理结果
				case 2 : 
					
					break; 
				
				default : 
					break;
					
				} 
			}
		}
		
	}
}
