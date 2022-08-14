/**
 * Copyright:   
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-07-02 09:48:18 
 */
package hry.admin.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.admin.customer.model.AppCustomer;
import hry.admin.customer.service.AppCustomerService;
import hry.admin.web.dao.AppWorkOrderDao;
import hry.admin.web.model.AppWorkOrder;
import hry.admin.web.service.AppMessageCategoryService;
import hry.admin.web.service.AppMessageService;
import hry.admin.web.service.AppMessageTemplateService;
import hry.admin.web.service.AppWorkOrderService;
import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import hry.bean.PageResult;
import hry.util.MsgTypeEnum;
import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;
import org.springframework.stereotype.Service;

/**
 * <p> appWorkOrderService </p>
 * @author:         sunyujie
 * @Date :          2018-07-02 09:48:18
 */
@Service("appWorkOrderService")
public class AppWorkOrderServiceImpl extends BaseServiceImpl<AppWorkOrder, Long> implements AppWorkOrderService {
	
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
	public PageResult queryByPage(QueryFilter filter, AppWorkOrder appWorkOrder) {
		PageResult pageResult = new PageResult();
		Page<AppWorkOrder> page = null;
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
		pageResult.setTotal(page.getTotal());//这个在新版里面表示总记录数
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());

		return pageResult ;
	}
	@Override
	public JsonResult sendMessageToCustomer(AppWorkOrder appWorkOrder) {
		//回复方式  0邮件回复  1系统消息  2短信回复  3电话回复
		Integer replyMode = appWorkOrder.getReplyMode();
		if( appWorkOrder.getCustomerId() != null ){
			AppCustomerService appCustomerService = (AppCustomerService) ContextUtil.getBean("appCustomerService");
			AppCustomer appCustomer = appCustomerService.get(appWorkOrder.getCustomerId());
			if( appCustomer != null){
				/*switch (replyMode){
					//给用户发送邮件告知工单处理结果
					case 0 :
						break;
					//给用户发送系统消息告知工单处理结果
					case 1 :
						//封装通知的消息
						appMessageService.saveWorkOrderMessage(appCustomer, appWorkOrder);
						break;
					//给用户发送短信告知工单处理结果
					case 2 :
						break;
					default :
						break;
				}*/
				//发送站内信
				appMessageService.sysSendMsg(appCustomer, MsgTypeEnum.WORKORDER);
				return appMessageService.saveWorkOrderMessage(appCustomer, appWorkOrder);
			}
		}

		return new JsonResult().setSuccess(false);
	}
}
