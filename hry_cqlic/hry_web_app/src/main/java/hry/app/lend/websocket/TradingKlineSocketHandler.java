package hry.app.lend.websocket;

import com.alibaba.fastjson.JSON;
import hry.app.lend.model.LendRedis;
import hry.app.lend.websocket.model.kline.MessageStatus;
import hry.app.lend.websocket.util.ThreadPoolPushData;
import hry.lend.constant.ExchangeLendKey;
import hry.lend.constant.LendConfig;
import hry.lend.model.kline.KlineData;
import hry.lend.model.kline.KlineTimes;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @Date 2018/11/16 13:54
 * 1 后期改为交易通知 独推 redis通知
 * 2 切换频率限制 同改vue
 */
@Service
public class TradingKlineSocketHandler implements WebSocketHandler {

    /**
     * 推送对象 coinCode=time=token=user
     */
    private static Map<String, Map<String, Map<String,WebSocketSession>>> pushMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String coinCode = String.valueOf(session.getAttributes().get("coinCode"));
        Object timeObj = session.getAttributes().get("time");
        String time;
        if(null==timeObj){
            time = KlineTimes.ONE_MIN.getMsg();
            session.getAttributes().put("time",time);
        }else{
            time = String.valueOf(timeObj);
        }
        saveSession(session, coinCode, time);
        session.getAttributes().put("pushNode",false);
    }

    private void saveSession(WebSocketSession session, String coinCode, String time) {
        Map<String, Map<String,WebSocketSession>> stringListMap = pushMap.get(coinCode);
        String marginToken = String.valueOf(session.getAttributes().get("marginToken"));
        if(null!=stringListMap){
            Map<String,WebSocketSession> sessions = stringListMap.get(time);
            if(null!=sessions){
                sessions.put(marginToken,session);
            }else{
                sessions = new ConcurrentHashMap<>();
                sessions.put(marginToken,session);
                stringListMap.put(time,sessions);
            }
        }else{
            stringListMap = new ConcurrentHashMap<>();
            Map<String,WebSocketSession> sessions = new ConcurrentHashMap<>();
            sessions.put(marginToken,session);
            stringListMap.put(time,sessions);
            pushMap.put(coinCode,stringListMap);
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if(message.getPayloadLength()==0){
            return;
        }
        MessageStatus messageStatus = JSON.parseObject(message.getPayload().toString(), MessageStatus.class);
        // 数据推送的三个状态
        switch (messageStatus.getCmd()){
            case "unsub":
                //unpush one
                //暂停持续推送
                session.getAttributes().put("pushNode",false);
                break;
            case "sub":
                //push one
                //持续推送
                session.getAttributes().put("pushNode",true);
                break;
            case "req":
                //push all data
                //首推
                String coinCode = String.valueOf(session.getAttributes().get("coinCode"));
                String time = String.valueOf(session.getAttributes().get("time"));
                if(null!=messageStatus.getCoinCode()&&!messageStatus.getCoinCode().equals(coinCode)){
                    String marginToken = String.valueOf(session.getAttributes().get("marginToken"));
                    pushMap.get(coinCode).get(time).remove(marginToken);

                    coinCode = messageStatus.getCoinCode();
                    session.getAttributes().put("coinCode",coinCode);
                    saveSession(session,coinCode,time);
                } else
                if(null!=messageStatus.getInterval()&&!messageStatus.getInterval().equals(time)){
                    String marginToken = String.valueOf(session.getAttributes().get("marginToken"));
                    pushMap.get(coinCode).get(time).remove(marginToken);

                    session.getAttributes().put("time",messageStatus.getInterval());
                    time = messageStatus.getInterval();
                    saveSession(session,coinCode,time);
                }
                pushData(coinCode,session,time);
                break;
            default:
                break;
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String coinCode = String.valueOf(session.getAttributes().get("coinCode"));
        String time = String.valueOf(session.getAttributes().get("time"));
        String marginToken = String.valueOf(session.getAttributes().get("marginToken"));
        try{
            pushMap.get(coinCode).get(time).remove(marginToken);
        }catch(Exception ignored){
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 单节点立推
     */
    public void pushOneNode(String coinCode,WebSocketSession session){


    }

    /**
     * 单节点全推
     * 后期改 独推
     */
    public void pushOneNodeAll(){
        for (Map.Entry<String, Map<String, Map<String,WebSocketSession>>> entry : pushMap.entrySet()) {
            ThreadPoolPushData.exe(()->{
                for (Map.Entry<String, Map<String,WebSocketSession>> timer : entry.getValue().entrySet()) {
                    try(Jedis jedis = LendRedis.JEDIS_POOL.getResource()){
                        List<String> currentNode = jedis.lrange(ExchangeLendKey.getKlineData(entry.getKey(), timer.getKey()), 0, 0);
                        if(null!=currentNode&&currentNode.size()>0){
                            KlineData klineData = JSON.parseObject(currentNode.get(0), KlineData.class);
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            klineData.setTime(format.parse(klineData.getTransactionTime()).getTime());
                            klineData.setType("node");
                            ThreadPoolPushData.exe(()->{
                                Map<String,WebSocketSession> sessions = timer.getValue();
                                for (WebSocketSession session : sessions.values()) {
                                    if("true".equals(session.getAttributes().get("pushNode").toString())) {
                                        synchronized (session.getId()) {
                                            if (session.isOpen()) {
                                                try {
                                                    session.sendMessage(new TextMessage(JSON.toJSONString(klineData)));
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }
            });
        }

    }

    /**
     * 首推k线数据
     */
    private void pushData(String coinCode, WebSocketSession session, String time){
        try(Jedis jedis = LendRedis.JEDIS_POOL.getResource()){
            String key = ExchangeLendKey.getKlineData(coinCode, time);
            List<String> klineDataAll = jedis.lrange(key, 0, LendConfig.KLINE_NODE_LOAD-1);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            if(null!=klineDataAll) {
                List<KlineData> dataList = new ArrayList<>();
                Map<String,Object> param = new HashMap<>(2);
                for (int i = klineDataAll.size()-1; i >= 0 ; i--) {
                    KlineData klineData = JSON.parseObject(klineDataAll.get(i), KlineData.class);
                    klineData.setTime(format.parse(klineData.getTransactionTime()).getTime());
                    dataList.add(klineData);
                }
                param.put("type","one");
                param.put("data",dataList);
                synchronized (session.getId()){
                    if(session.isOpen()){
                        session.sendMessage(new TextMessage(JSON.toJSONString(param)));
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}