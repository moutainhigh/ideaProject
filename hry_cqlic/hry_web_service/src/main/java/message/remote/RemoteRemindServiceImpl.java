package message.remote;

import com.alibaba.fastjson.JSON;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.person.service.AppPersonInfoService;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.service.AppCustomerService;
import hry.manage.remote.RemoteSmsService;
import hry.message.model.MessageType;
import hry.message.model.RemoteMessage;
import hry.message.remote.RemoteRemindService;
import hry.util.QueryFilter;
import hry.util.StringUtil;
import hry.web.message.service.AppMessageService;

import javax.annotation.Resource;

public class RemoteRemindServiceImpl implements RemoteRemindService {
    @Resource
    private AppCustomerService appCustomerService;
    @Resource
    private AppPersonInfoService appPersonInfoService;
    @Resource
    private RemoteSmsService remoteSmsService;
    @Resource
    private AppMessageService appMessageService;
    @Override
    public boolean sendAllRemind(String param) {
      try {
          RemoteMessage remoteMessage = JSON.parseObject(param, RemoteMessage.class);
          String msgType=remoteMessage.getMsgType();//消息类型 1.站内信，2.短信,
          String msgKey=remoteMessage.getMsgKey();//模板key
          if(msgKey==null||msgKey.equals("")){
            return  false;
          } if(StringUtil.isNull(msgType)){
              String userId=remoteMessage.getUserId();//发送人的Id
              if(StringUtil.isNull(userId)){
                  AppCustomer appCustomer=appCustomerService.get(Long.valueOf(userId));
                  AppPersonInfo personInfo=appPersonInfoService.get(new QueryFilter(AppPersonInfo.class)
                          .addFilter("customerId=",userId));
                  String[] type=msgType.split(",");
                  if(type!=null&&type.length>0){
                      for (String t:type) {
                          if(t.equals("1")){//1.站内信
                              appMessageService.sysSendMsg(appCustomer,remoteMessage.getParam(), MessageType.getIndex(msgKey));
                          }if(t.equals("2")){//2.短信
                              String locale=appCustomer.getCommonLanguage();
                              String telephone=personInfo.getMobilePhone();
                              remoteSmsService.sendSmsRemind( telephone,  msgKey, remoteMessage.getParam(), locale);
                          }
                      }
                      return  true;
                  }
              }
          }
          return false;
      }catch (Exception e){
          e.printStackTrace();
          return false;
      }
    }
}
