package hry.admin.mq.producer;

import com.alibaba.fastjson.JSON;
import hry.admin.ico.account.model.IcoLockError;
import hry.admin.ico.account.service.IcoLockErrorService;
import hry.bean.JsonResult;
import hry.ico.remote.RemoteIcoService;
import hry.message.remote.RemoteRemindService;
import hry.util.SpringUtil;
import hry.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import javax.annotation.Resource;
import java.util.HashMap;


public class MessageToLockStorage implements MessageListener {
    private Logger log = LoggerFactory.getLogger(MessageToLockStorage.class);
    @Resource
    private IcoLockErrorService lockErrorService;
    @Override
    public void onMessage(Message message) {
        String str = new String(message.getBody());
        RemoteIcoService remoteIcoService= SpringUtil.getBean("remoteIcoService");
        HashMap<String,String> hashMap= JSON.parseObject(str,HashMap.class);
        JsonResult jsonResult= remoteIcoService.toLockDepot(hashMap);
        if(!jsonResult.getSuccess()){
            IcoLockError icoLockError=new IcoLockError();
            String customerId=hashMap.get("customerId");
            String orderById=hashMap.get("orderById");
            icoLockError.setErrorInfo(jsonResult.getMsg());
            if(StringUtil.isNull(customerId)){
                icoLockError.setCustomerId(Long.valueOf(customerId));
            }
            if(StringUtil.isNull(orderById)){
                icoLockError.setOrderBuyId(Long.valueOf(orderById));
            }
            icoLockError.setMqMessage(str);
            lockErrorService.save(icoLockError);
        }
    }
}
