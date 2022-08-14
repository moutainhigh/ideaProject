package hry.app.otc.remote;

import hry.app.chatTemplate.service.AppOtcTemplateService;
import hry.app.chatmessage.service.OtcChatMessageService;
import hry.bean.ObjectUtil;
import hry.manage.remote.model.RemoteResult;
import hry.otc.manage.remote.api.RemoteOtcChatMessageService;
import hry.otc.manage.remote.model.OtcChatMessageRemote;
import hry.otc.manage.remote.model.message.AppOtcTemplate;
import hry.otc.manage.remote.model.message.OtcChatMessage;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class RemoteOtcChatMessageServiceImpl implements RemoteOtcChatMessageService {

	@Resource
	private OtcChatMessageService otcChatMessageService;
	@Resource
	private AppOtcTemplateService appOtcTemplateService;

	@Override
	public RemoteResult saveOtcChatMessageRecord (OtcChatMessageRemote otcChatMessage) {
		OtcChatMessage chat = new OtcChatMessage();
		chat.setOrderId(otcChatMessage.getOrderId());
		chat.setContent(otcChatMessage.getContent());
		chat.setType(otcChatMessage.getType());
		chat.setFromID(otcChatMessage.getFromID());
		chat.setToID(otcChatMessage.getToID());
		chat.setFromName(otcChatMessage.getFromName());
		chat.setToName(otcChatMessage.getToName());
		chat.setCreated(new Date());
		otcChatMessageService.save(chat);

		return new RemoteResult().setSuccess(true);
	}

	@Override
	public RemoteResult initChatData (Long id, Long customerId) {
		QueryFilter filter = new QueryFilter(OtcChatMessage.class);
		filter.addFilter("orderId=",id);
		List<OtcChatMessage> messageList = otcChatMessageService.find(filter);
		if(messageList != null && messageList.size() > 0){
			List<OtcChatMessageRemote> beanList = ObjectUtil.beanList(messageList, OtcChatMessageRemote.class);
			for (OtcChatMessageRemote message : beanList) {
				if(message.getFromID().equals(customerId)){
					message.setChatType(2);
				}else{
					message.setChatType(1);
				}
			}
			return new RemoteResult().setSuccess(true).setObj(beanList);
		}
		return new RemoteResult().setSuccess(false);
	}

    @Override
    public List<OtcChatMessage> getNoReadRecords (Long id, Long customerId) {
        QueryFilter filter = new QueryFilter(OtcChatMessage.class);
        filter.addFilter("orderId=",id);
        filter.addFilter("isRead=",0);
        filter.addFilter("toID=",customerId);

        List<OtcChatMessage> messageList = otcChatMessageService.find(filter);
        return messageList;
    }

    @Override
    public RemoteResult readRecords (Long id, Long customerId) {
        QueryFilter filter = new QueryFilter(OtcChatMessage.class);
        filter.addFilter("orderId=",id);
        filter.addFilter("isRead=",0);
        filter.addFilter("toID=",customerId);

        List<OtcChatMessage> messageList = otcChatMessageService.find(filter);
        if(messageList != null && messageList.size() > 0){
            for (OtcChatMessage message : messageList) {
                message.setIsRead(1);
                otcChatMessageService.update(message);
            }
        }
        return new RemoteResult().setSuccess(true);
    }

	@Override
	public List<AppOtcTemplate> findOtcTemplate(String type, String lang) {
		QueryFilter filter = new QueryFilter(AppOtcTemplate.class);
		filter.addFilter("tempType=", type);
		if (!"3".equals(type)) {
			filter.addFilter("tempLang=", lang);
		}
		return appOtcTemplateService.find(filter);
	}


}
