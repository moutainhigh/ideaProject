package hry.social.runnable;/**
 * @title: MessageRunnable
 * @projectName hurong_matching2019
 * @description: TODO
 * @author javal
 * @date 2019/7/1116:18
 */

import com.alibaba.fastjson.JSON;
import hry.manage.remote.model.User;
import hry.social.manage.remote.api.friend.RemoteFriendService;
import hry.social.manage.remote.model.message.AppMessage;
import hry.social.manage.remote.model.message.AppMessageCategory;
import hry.social.manage.remote.model.message.MessageAsCustomer;
import hry.social.message.service.AppMessageCategoryService;
import hry.social.message.service.AppMessageService;
import hry.social.message.service.MessageAsCustomerService;
import hry.util.HttpClientNodeUtil;
import hry.util.NoticeTemplateUtil;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 通知发送
 *
 * @author javal
 * @title: MessageRunnable
 * @projectName hurong_matching2019
 * @description: TODO
 * @date 2019/7/1116:18
 */
public class MessageRunnable implements Runnable {

    private final Logger log = Logger.getLogger(MessageRunnable.class);

    /**
     * 用户信息
     */
    private User user;

    /**
     * 模版KEY
     */
    private int templateKey;

    /**
     * 消息参数
     */
    private Map<String,String> params;

    public MessageRunnable(User user, int templateKey, Map<String,String> params) {
        this.user = user;
        this.templateKey = templateKey;
        this.params = params;
    }


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            QueryFilter queryFilter1 = new QueryFilter(AppMessageCategory.class);
            String commonLanguage = user.getCommonLanguage();
            commonLanguage = commonLanguage != null ? commonLanguage : "zh_CN";
            queryFilter1.addFilter("messageCategory=", commonLanguage);
            queryFilter1.addFilter("triggerPoint=", templateKey);
            AppMessageCategoryService appMessageCategoryService = (AppMessageCategoryService) ContextUtil.getBean("appMessageCategoryService");
            AppMessageCategory appMessageCategory = appMessageCategoryService.get(queryFilter1);
            if (appMessageCategory == null) {
                return;
            }
            AppMessageService appMessageService = (AppMessageService) ContextUtil.getBean("appMessageService");
            MessageAsCustomerService messageAsCustomerService = (MessageAsCustomerService) ContextUtil.getBean("messageAsCustomerService");
            AppMessage appMessage = new AppMessage();
            appMessage.setSendDate(new Date());
            appMessage.setSendUserName("System");
            appMessage.setIsSend(1);    // 是否已发送(0 : 表示没有    1  表示已发送)
            appMessage.setIsAuto(1);    //0 手动发送 ，1 自动发送
            appMessage.setStatus(0);  // 状态 0：未阅，1：已阅，2：用户删除，3：系统删除
            appMessage.setType(String.valueOf(templateKey));
            appMessage.setMessageCategory(commonLanguage);
            //替换模版中的值
            String describes = appMessageCategory.getDescribes();
            String content = NoticeTemplateUtil.replaceKey(describes, params);
            appMessage.setContent(content);
            appMessage.setTitle(appMessageCategory.getName());
            Serializable save = appMessageService.save(appMessage);
            System.out.println(save);
            // 给一个人保存一条消息
            MessageAsCustomer messageAsCustomer = new MessageAsCustomer();
            messageAsCustomer.setCustomerName(user.getMobile());
            Long customerId = user.getCustomerId();
            messageAsCustomer.setCustomerId(customerId);
            messageAsCustomer.setMessageId(appMessage.getId());
            Serializable save1 = messageAsCustomerService.save(messageAsCustomer);
            System.out.println(save1);
            //推送通知消息
            Map<String,Object> map = new HashMap();
            map.put("destination", customerId);
            map.put("pushData", appMessage);
            String pushUrl = NoticeTemplateUtil.getPushUrl(customerId);
            if (!StringUtils.isEmpty(pushUrl)) {
                pushUrl += "/noticePush";
                String post = HttpClientNodeUtil.sendHttpPost(pushUrl, JSON.toJSONString(map));
                System.out.println("the result of notice push : " + post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
