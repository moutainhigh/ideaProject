package hry.app.socket.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.app.jwt.TokenUtil;
import hry.manage.remote.model.User;
import hry.otc.manage.remote.api.RemoteNewAdvertisementService;
import hry.otc.manage.remote.model.OtcAppTransactionRemote;
import hry.otc.manage.remote.model.OtcChatMessageRemote;
import hry.util.common.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.websocket.server.ServerEndpoint;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">HeC</a>
 * @date 2019/1/17 11:14
 */
@Component
@ServerEndpoint("/otcchat")
public class OtcChatHallHandler  extends TextWebSocketHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(OtcChatHallHandler.class);

    /* 以用户id作为key值 保存用户的session对象 */
    private static Map<String, WebSocketSession> map = new HashMap<String, WebSocketSession>(); // 保存用户和对应的session

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //已建立连接的用户
    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();

    /**
     * 处理前端发送的文本信息
     * js调用websocket.send时候，会调用该方法
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String content = message.getPayload();
        LOGGER.info("otcChat WebSocket收到的消息:" +content);
        if(!StringUtils.isEmpty(content)){

            JSONObject jsStr = JSONObject.parseObject(content);// 转换成json数据
            String cmd = jsStr.getString("cmd");
            String token = jsStr.getString("token");
            String to = jsStr.getString("to");
            String chatContent = jsStr.getString("chatContent");
            String type = jsStr.getString("type");

            User user = TokenUtil.getUser(token);
            String userName = "";
            if(user != null){
                userName = StringUtils.isEmpty(user.getNickNameOtc()) ? user.getEmail() : user.getNickNameOtc();
            }
            if("enter_chatting".equals(cmd)){ //进入聊天统计人数
                map.put(user.getCustomerId().toString(),session);
            }else if("ping".equals(cmd)){ //进入聊天统计人数
               System.out.println("otcchat ping");
            }else if ("chatting".equals(cmd)) { //聊天
                System.out.println("聊天");

                RemoteNewAdvertisementService remoteNewAdvertisementService = SpringUtil.getBean("remoteNewAdvertisementService");
                OtcAppTransactionRemote otcAppTransaction = remoteNewAdvertisementService.getOtcTransactionByNum(to);
                String  otherId= "";
                Long buyUserId = otcAppTransaction.getBuyUserId();
                Long sellUserId = otcAppTransaction.getSellUserId();
                if(buyUserId.equals(user.getCustomerId())){
                    otherId = sellUserId.toString();
                }else{
                    otherId = buyUserId.toString();
                }
                Map msgmap = new HashMap();
                msgmap.put("fromName", userName);
                msgmap.put("fromID", user.getCustomerId());
                msgmap.put("content", chatContent);
                msgmap.put("created", sdf.format(new Date()));
                msgmap.put("type", type);

                session.sendMessage(new TextMessage(JSONObject.toJSONString(msgmap)));
                if(map.get(otherId)!= null){
                    map.get(otherId).sendMessage(new TextMessage(JSONObject.toJSONString(msgmap)));
                }
            }else if("out_chatting".equals(cmd)){ //退出聊天室,统计人数
                map.remove(user.getCustomerId().toString(),session);
            }

        }

    }


    /**
     * 当新连接建立的时候，被调用
     * 连接成功时候，会触发页面上onOpen方法
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
      // users.add(session);
        //连接时推送数据
        JSONObject root = new JSONObject();
        root.put("type","hello");
        root.put("data","hello otc");
     //   session.sendMessage(new TextMessage(JSON.toJSONString(root)));
    }

    /**
     * 当连接关闭时被调用
     *
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if(map != null && !map.isEmpty()){
            for (Object key: map.keySet()) {
                if(map.get(key).equals(session)){
                    LOGGER.info("用户id " + key + " Connection closed. Status: " + status);
                    map.remove(key);
                }
            }
        }
    }

    /**
     * 传输错误时调用
     *
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        String username = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        if (session.isOpen()) {
            session.close();
        }
        LOGGER.debug("用户: " + username + " websocket connection closed......");
        users.remove(session);
    }

    /**
     * 广播发送消息方法。
     * @param message
     */
    public void sendAllMessage(String message) {
        try {
            OtcChatMessageRemote chat = JSON.parseObject(message, OtcChatMessageRemote.class);
            RemoteNewAdvertisementService remoteNewAdvertisementService = SpringUtil.getBean("remoteNewAdvertisementService");
            OtcAppTransactionRemote otcAppTransaction = remoteNewAdvertisementService.getOtcTransactionById(chat.getOrderId().toString());
            String  otherId= "";
            Long buyUserId = otcAppTransaction.getBuyUserId();
            Long sellUserId = otcAppTransaction.getSellUserId();
            if(buyUserId.equals(chat.getFromID())){
                otherId = sellUserId.toString();
            }else{
                otherId = buyUserId.toString();
            }
            Map msgmap = new HashMap();
            msgmap.put("fromName", chat.getFromName());
            msgmap.put("fromID", chat.getFromID());
            msgmap.put("content", chat.getContent());
            msgmap.put("created", sdf.format(new Date()));
            msgmap.put("type", 3);

            if(map.get(chat.getFromID())!= null) {
                map.get(chat.getFromID()).sendMessage(new TextMessage(JSONObject.toJSONString(msgmap)));
            }
            if(map.get(otherId)!= null){
                map.get(otherId).sendMessage(new TextMessage(JSONObject.toJSONString(msgmap)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}