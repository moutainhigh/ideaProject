package hry.chat.chat.remote;

import java.util.*;

import javax.annotation.Resource;

import hry.chat.chat.model.ChatRecord;
import hry.chat.chat.model.ChatSensitive;
import hry.chat.chat.service.ChatRecordService;
import hry.chat.chat.service.ChatSensitiveService;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.service.AppCustomerService;
import hry.manage.remote.RemoteChatService;
import hry.manage.remote.model.RemoteResult;

public class RemoteChatServiceImpl implements RemoteChatService{

	@Resource
	private ChatRecordService chatRecordService;
	@Resource
	private ChatSensitiveService chatSensitiveService;
	@Resource
	private AppCustomerService appCustomerService;
	
	@Override
	public void saveChatRecord(Long id, String username, String content){
		try {
			ChatRecord cr = new ChatRecord();
			cr.setCustomerId(id);
			cr.setUserName(username);
			cr.setContent(content);
			cr.setCreated(new Date());
			chatRecordService.save(cr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public RemoteResult sensitiveWords(){
		List<ChatSensitive> list = chatSensitiveService.findAll();
		if(list.size()>0){
			String[] str = new String[list.size()];
			for(int i=0;i<list.size();i++){
				str[i] = list.get(i).getSensitiveWords();
			}
			return new RemoteResult().setSuccess(true).setObj(str);
		}
		return new RemoteResult().setSuccess(false);
	}
	
	@Override
	public RemoteResult isAdmin(Long id, String type){
		AppCustomer app = appCustomerService.get(id);
		if(app!=null){
			app.setIsAdmin(Integer.valueOf(type));
			appCustomerService.update(app);
			return new RemoteResult().setSuccess(true);
		}
		return new RemoteResult().setSuccess(false);
	}

	public List<Map<String, Object>> get3daychat(Long id){
		List<ChatRecord> list = chatRecordService.get3daychat(id);
		if(list.size()>0){
			List<Map<String, Object>> listm = new ArrayList<>();
			for(ChatRecord c : list){
				Map<String, Object> map = new HashMap<>();
				map.put("userName", c.getUserName());
				map.put("content", c.getContent());
				map.put("created", c.getCreated());
				listm.add(map);
			}
			return listm;
		}
		 return null;
	}

	@Override
	public void releaseGag(Long customerId) {
		AppCustomer appCustomer = appCustomerService.get(customerId);
		appCustomer.setIsGag(0);
		appCustomer.setGagTime(0);
		appCustomerService.update(appCustomer);
	}
}
