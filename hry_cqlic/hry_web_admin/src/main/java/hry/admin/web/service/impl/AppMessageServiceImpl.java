/**
 * Copyright:
 * @author:      liuchenghui
 * @version:     V1.0
 * @Date:        2018-07-05 10:21:54
 */
package hry.admin.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.admin.customer.model.AppCustomer;
import hry.admin.customer.model.AppPersonInfo;
import hry.admin.customer.service.AppPersonInfoService;
import hry.admin.web.dao.AppMessageDao;
import hry.admin.web.model.*;
import hry.admin.web.service.AppMessageCategoryService;
import hry.admin.web.service.AppMessageService;
import hry.admin.web.service.MessageAsCustomerService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.core.thread.ThreadPool;
import hry.util.MsgTypeEnum;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> AppMessageService </p>
 * @author:         liuchenghui
 * @Date :          2018-07-05 10:21:54
 */
@Service("appMessageService")
public class AppMessageServiceImpl extends BaseServiceImpl<AppMessage, Long> implements AppMessageService{

	@Resource(name="appMessageDao")
	@Override
	public void setDao(BaseDao<AppMessage, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private MessageAsCustomerService messageAsCustomerService;

	@Resource
    private AppPersonInfoService appPersonInfoService;

	@Resource
    private AppMessageCategoryService appMessageCategoryService;

	@Override
	public JsonResult saveMessage (AppMessage appMessage, String userNameArr) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(true);
        if(StringUtils.isEmpty(appMessage.getId())){
            this.save(appMessage);
        }else{
            appMessage.setIsAuto(0);
            this.update(appMessage);
        }
        // ?????????
        String[] userNames = userNameArr.split(",");
        // ????????????
        Long id = appMessage.getId();
        Integer all = appMessage.getIsAll();
        if (all == 1) {
            //????????????????????????????????????
            ThreadPool.exe(new MessageToAllRunnable(id));
        } else if (all == 0 && userNames.length > 0) {
            messageAsCustomerService.sendPartial(id, userNames);
        }
        jsonResult.setMsg("????????????");
        return jsonResult;
	}

    @Override
    public JsonResult sendMessage (Long[] ids) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(true);

        if (ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                AppMessage message = super.get(ids[i]);
                QueryFilter filter = new QueryFilter(MessageAsCustomer.class);
                filter.addFilter("messageId=", ids[i]);

                List<MessageAsCustomer> list = messageAsCustomerService.find(filter);
                if (list.size() > 0) {
                    message.setIsSend(1);
                    message.setSendDate(new Date());
                    message.setSendUserName(ContextUtil.getCurrentUserName());
                    super.update(message);
                    jsonResult.setMsg("??????????????????");
                }
            }
            jsonResult.setMsg("????????????????????????");
            return jsonResult;
        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg("??????????????????0");
            return jsonResult;
        }
    }

    @Override
    public PageResult selectMessageVoByPage (QueryFilter filter) {
        PageResult page = this.selectMessageByPage(filter);
        List list = page.getRows();
        if(list.size()>0){
            for(int i = 0; i<list.size();i++){
                MessageListVo messageVo = (MessageListVo)list.get(i);
                List<MessageAsCustomer> list2 = ((AppMessageDao)dao).selectMessageAsCustromer(messageVo.getId());
                messageVo.setList(list2);

                AppPersonInfo a = appPersonInfoService.get(messageVo.getReceiver());
                messageVo.setAppPersonInfo(a);

            }
        }
        return page;
    }

    @Override
    public void saveWorkOrderMessage(AppCustomer customer, AppMessageCategory appMessageCategory,
                                     AppMessageTemplate appMessageTemplate, String workOrderNo) {
        AppMessage appMessage = new AppMessage();

        appMessage.setContent(appMessageTemplate.getContent().replaceAll("workNum", workOrderNo));
        appMessage.setTitle(appMessageTemplate.getTitle());
        appMessage.setSortTitle("???????????????");

        setAppMessage(customer, appMessageCategory, appMessage);

    }

    @Override
    public JsonResult saveWorkOrderMessage(AppCustomer customer, AppWorkOrder appWorkOrder){
        JsonResult jsonResult = new JsonResult();
        AppMessage appMessage = new AppMessage();
        appMessage.setSendDate(new Date());
        appMessage.setSendUserName("System");
        appMessage.setIsSend(1);
        appMessage.setIsAuto(1);
        appMessage.setMessageCategory(customer.getCommonLanguage());
        appMessage.setContent(appWorkOrder.getReplyContent());
        appMessage.setTitle("????????????");
        this.save(appMessage);

        try {
            messageAsCustomerService.sendPartialName(appMessage.getId(), customer.getUsername());
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setSuccess(false).setMsg("???????????????");
        }
        return jsonResult;
    }

    public PageResult selectMessageByPage(QueryFilter filter){
        Map<String,Object> map = new HashMap<String,Object>();

        Page<MessageListVo> page = PageFactory.getPage(filter);

        // ????????????
        String messageCategory = filter.getRequest().getParameter("messageCategory");
        if(!StringUtils.isEmpty(messageCategory)){
            map.put("messageCategory", messageCategory);
        }
        String isAuto = filter.getRequest().getParameter("isAuto");
        if(!StringUtils.isEmpty(isAuto)){
            map.put("isAuto", isAuto);
        }
        String title = filter.getRequest().getParameter("title_like");
        if(!StringUtils.isEmpty(title)){
            map.put("title", title);
        }
        String categoryName = filter.getRequest().getParameter("categoryName_like");
        if(!StringUtils.isEmpty(categoryName)){
            map.put("categoryName", categoryName);
        }
        String sendDate_GT = filter.getRequest().getParameter("sendDate_GT");
        if(!StringUtils.isEmpty(sendDate_GT)){
            map.put("sendDate_GT", sendDate_GT);
        }
        String sendDate_LT = filter.getRequest().getParameter("sendDate_LT");
        if(!StringUtils.isEmpty(sendDate_LT)){
            map.put("sendDate_LT", sendDate_LT);
        }
        String content_like = filter.getRequest().getParameter("content_like");
        if(!StringUtils.isEmpty(content_like)){
            map.put("content_like", content_like);
        }
        String isSend = filter.getRequest().getParameter("isSend");
        if(!StringUtils.isEmpty(isSend)){
            map.put("isSend", isSend);
        }

        ((AppMessageDao)dao).selectMessageVo(map);


        PageResult pageResult = new PageResult(page, filter.getPage(),filter.getPageSize());

        return pageResult ;
    }

    private void setAppMessage(AppCustomer customer, AppMessageCategory appMessageCategory, AppMessage appMessage) {
        appMessage.setCategoryName(appMessageCategory.getName());	//??????????????????
        appMessage.setCategoryId(appMessageCategory.getId());	//??????????????????id
        appMessage.setIsSend(1);	//???????????????(0 : ????????????    1  ???????????????)
        appMessage.setSendDate(new Date());
        super.save(appMessage);
        Long id = appMessage.getId();
        messageAsCustomerService.saveMessageAsCustomer(customer, id);
    }

    /**
     * ??????????????????/???????????????
     */
    @Override
    public void saveMessage(AppCustomer customer,AppMessageCategory appMessageCategory, AppMessageTemplate appMessageTemplate,
                            Boolean flag) {
        AppMessage appMessage = new AppMessage();
        if(flag){	//????????????
            appMessage.setContent(appMessageTemplate.getContent().replaceAll("??????/??????", "??????"));
            appMessage.setTitle(appMessageTemplate.getTitle().replaceAll("??????/??????", "??????"));
            appMessage.setSortTitle("?????????");
        }else{
            appMessage.setContent(appMessageTemplate.getContent().replace("??????/??????", "?????????"));
            appMessage.setTitle(appMessageTemplate.getTitle().replaceAll("??????/??????", "??????"));
            appMessage.setSortTitle("?????????");
        }
        setAppMessage(customer, appMessageCategory, appMessage);
    }

    public JsonResult sysSendMsg(AppCustomer customer, MsgTypeEnum msgTypeEnum){
        Integer trigger = null ;
        switch (msgTypeEnum){
            case REGIST: trigger=1; break;
            case MODIFYPWD: trigger=2; break;
            case REALNAME: trigger=3; break;
            case POSTMONEY: trigger=4; break;
            case GETMONEY: trigger =5; break;
            case C2C: trigger =6; break;
            case OTC: trigger =7; break;
            case WORKORDER: trigger = 8; break;
            default: trigger=1;

        }

        String commonLang = customer.getCommonLanguage();
        if (StringUtils.isEmpty(commonLang)) {
            // ?????????????????????????????????
            commonLang = "zh_CN";
        }
        QueryFilter queryFilter1 = new QueryFilter(AppMessageCategory.class);
        queryFilter1.addFilter("triggerPoint=",trigger);
        queryFilter1.addFilter("messageCategory=", commonLang);
        AppMessageCategory appMessageCategory = appMessageCategoryService.get(queryFilter1);

        if (appMessageCategory != null) {
            AppMessage appMessage = new AppMessage();
            appMessage.setSendDate(new Date());
            appMessage.setSendUserName("System");
            appMessage.setIsSend(1);
            appMessage.setIsAuto(1);
            appMessage.setMessageCategory(commonLang);
            appMessage.setContent(appMessageCategory.getDescribes().replace("", ""));
            appMessage.setTitle(appMessageCategory.getName());
            this.save(appMessage);

            messageAsCustomerService.sendPartialName(appMessage.getId(), customer.getUsername());
            return new JsonResult().setSuccess(true);
        }
        return new JsonResult().setSuccess(false);
    }
}
