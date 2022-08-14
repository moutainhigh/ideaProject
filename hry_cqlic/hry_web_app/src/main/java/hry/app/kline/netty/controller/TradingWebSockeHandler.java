package hry.app.kline.netty.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.app.jwt.JWTUtil;
import hry.app.jwt.TokenUtil;
import hry.app.kline.model.LastKLine;
import hry.app.kline.model.LastKLinePayload;
import hry.app.kline.netty.config.NettyConfig;
import hry.app.kline.netty.model.RequestData;
import hry.app.kline.netty.model.ResponseData;
import hry.app.lend.websocket.model.kline.MessageStatus;
import hry.app.user.model.AppPersonInfo;
import hry.manage.remote.RemoteChatService;
import hry.manage.remote.model.User;
import hry.redis.common.utils.RedisService;
import hry.util.PropertiesUtils;
import hry.util.SpringUtil;
import hry.util.klinedata.KlineDataUtil;
import hry.util.klinedata.TransactionOrder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaders.Names.SEC_WEBSOCKET_PROTOCOL;

/**
 * 接受/处理/响应客户端websocke请求的核心业务处理类
 */
public class TradingWebSockeHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker webSocketServerHandshaker;
    private static final String WEB_SOCKET_URL = "ws://"+PropertiesUtils.APP.get("web.socket.url")+"/deep";
    public static Map<Channel, MessageStatus> tradingInfo = new ConcurrentHashMap();
    public static Map<Channel, String> chattingInfo = new ConcurrentHashMap();

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    @Resource
    private RedisService redisService;


    //客户端与服务端创建链接的时候调用
    @Override
    public void channelActive(ChannelHandlerContext context) throws Exception {
        NettyConfig.tradingGroup.add(context.channel());
        //info.put(context.channel(),"");
        System.out.println("客户端与服务端连接开启");
    }

    //客户端与服务端断开连接的时候调用
    @Override
    public void channelInactive(ChannelHandlerContext context) throws Exception {
        if(tradingInfo.containsKey(context.channel())){
            tradingInfo.remove(context.channel());
        }
        if(chattingInfo.containsKey(context.channel())){
            chattingInfo.remove(context.channel());
        }

        NettyConfig.tradingGroup.remove(context.channel());
        System.out.println("客户端与服务端连接断开");
    }

    //服务端接收客户端发送过来的数据结束之后调用
    @Override
    public void channelReadComplete(ChannelHandlerContext context) throws Exception {
        context.flush();
    }

    //工程出现异常的时候调用
    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable) throws Exception {
        throwable.printStackTrace();
        if(tradingInfo.containsKey(context.channel())){
            tradingInfo.remove(context.channel());
        }
        if(chattingInfo.containsKey(context.channel())){
            chattingInfo.remove(context.channel());
        }
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
        //response.headers().set(SEC_WEBSOCKET_PROTOCOL, "text/xml; charset=UTF-8");
        //context.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        context.close();
    }

    //服务端处理客户端websocke请求的核心方法
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        //处理客户端向服务端发起的http握手请求
        if (o instanceof FullHttpRequest) {
            handHttpRequest(channelHandlerContext, (FullHttpRequest) o);
        } else if (o instanceof WebSocketFrame) {//处理websocket链接业务
            handWebSocketFrame(channelHandlerContext, (WebSocketFrame) o);
        }
    }

    /**
     * 处理客户端与服务端之间的websocket业务
     *
     * @param context
     * @param webSocketFrame
     */
    private void handWebSocketFrame(ChannelHandlerContext context, WebSocketFrame webSocketFrame) {
        if (webSocketFrame instanceof CloseWebSocketFrame) {//判断是否是关闭websocket的指令
            webSocketServerHandshaker.close(context.channel(), (CloseWebSocketFrame) webSocketFrame.retain());
        }
        if (webSocketFrame instanceof PingWebSocketFrame) {//判断是否是ping消息
            context.channel().write(new PongWebSocketFrame(webSocketFrame.content().retain()));
            return;
        }
        /*if (!(webSocketFrame instanceof TextWebSocketFrame)){//判断是否是二进制消息
            System.out.println("不支持二进制消息");
            throw new RuntimeException(this.getClass().getName());
        }*/

        if (webSocketFrame instanceof TextWebSocketFrame) {

            String text = ((TextWebSocketFrame) webSocketFrame).text();
            /*if (text.contains("cmd")) {
                System.out.println(text);
                MessageStatus messageStatus = JSONObject.parseObject(text, MessageStatus.class);
                if ("req".equals(messageStatus.getCmd())) {
                    handlerReq(context, messageStatus, "one");
                } else if ("history".equals(messageStatus.getCmd()) && !StringUtils.isEmpty(messageStatus.getTimeDL())) {
                    handlerReq(context, messageStatus, "history");
                } else if ("chatting".equals(messageStatus.getCmd())) { //聊天
                    sendMessageToChat(chattingInfo.get(context.channel()), messageStatus.getChatContent());//给所有用户发送消息
                } else if ("trading".equals(messageStatus.getCmd()) || "sub".equals(messageStatus.getCmd())) {
                    tradingInfo.put(context.channel(), messageStatus);
                }

            } else {
                System.out.println("错误请求:" + text);
            }*/
            if (text != null && !"".equals(text)) {
                String[] msg = text.split("_");
                if ("login".equals(msg[0])) {
                    String customerId = msg[1];
                    // 获取连接Id
                    String id = context.channel().id().asShortText();
                    // 将登录标识存入到redis中
                    RedisService redisService = hry.util.common.SpringUtil.getBean("redisService");
                    String redisId = redisService.get("SOCKET:PUSHURL:"+customerId);
                    // 获取app用户token
                    String token = redisService.get("JWT:token:app:sign:" + msg[2]);
                    if (token != null && !"".equals(token)) {
                        if (redisId != null) {
                            // 获取登录信息
                            NettyConfig.tradingGroup.iterator().forEachRemaining(channel -> {
                                if (!redisId.isEmpty()
                                        && !id.equals(channel.id().asShortText())
                                        && redisId.equals(channel.id().asShortText())) {
                                    // 强制退出
                                    TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(token);
                                    channel.writeAndFlush(textWebSocketFrame);
                                    System.out.println("++++++++:推送成功");
                                }
                            });
                        }
                        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(token);
                        context.channel().writeAndFlush(textWebSocketFrame);
                        redisService.save("SOCKET:PUSHURL:"+customerId,id);
                    }
                } else if("out".equals(msg[0])) {
                    // 获取app用户token
                    RedisService redisService = hry.util.common.SpringUtil.getBean("redisService");
                    String token = redisService.get("JWT:token:app:sign:" + msg[2]);
                    TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(token);
                    context.channel().writeAndFlush(textWebSocketFrame);
                }
            } else {
                System.out.println("客户端请求异常！");
            }

        }

    }

    private void handlerReq(ChannelHandlerContext context, MessageStatus messageStatus, String one) {
        String from = (new Date().getTime() / 1000 - 60 * 10) + "";
        String to = new Date().getTime() / 1000 + "";
        if (!StringUtils.isEmpty(messageStatus.getFrom())) {
            from = messageStatus.getFrom();
        }
        String timeDL = "";
        if (!StringUtils.isEmpty(messageStatus.getTo())) {
            to = messageStatus.getTo();

        }
        if (!StringUtils.isEmpty(messageStatus.getTimeDL())) {
            timeDL = messageStatus.getTimeDL();
        }

        Map map = history(messageStatus.getCoinCode(), messageStatus.getInterval().toString(), to, from, timeDL);
        map.put("type", one);
        String str = JSONObject.toJSONString(map);
        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(str);
        context.channel().writeAndFlush(textWebSocketFrame);
    }

    /**
     * 处理客户端向服务端发起http握手请求业务
     *
     * @param context
     * @param fullHttpRequest
     */
    private void handHttpRequest(ChannelHandlerContext context, FullHttpRequest fullHttpRequest) {
        if (!fullHttpRequest.getDecoderResult().isSuccess() || !("websocket".equals(fullHttpRequest.headers().get("Upgrade")))) {//判断是否http握手请求
            sendHttpResponse(context, fullHttpRequest, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
       /* //获取token
        String token = fullHttpRequest.headers().get("Sec-WebSocket-Protocol");
        if (!StringUtils.isEmpty(token)) {
            User user = TokenUtil.getUser(token);
            chattingInfo.put(context.channel(), StringUtils.isEmpty(user.getMobile()) ? user.getEmail() : user.getMobile());
            fullHttpRequest.headers().add(SEC_WEBSOCKET_PROTOCOL,token);
        }*/

        if(fullHttpRequest.getUri().contains("chatting")){
            //获取token
            String cookieStr = fullHttpRequest.headers().get("Cookie");
            if(!StringUtils.isEmpty(cookieStr)){
                String[] cookies = cookieStr.split(";");
                for (String cookie : cookies) {
                    if(!StringUtils.isEmpty(cookie) && "token".equals(cookie.split("=")[0].trim())){
                        String token = cookie.split("=")[1];
                        String username = JWTUtil.getUsername(token);
                        if(!StringUtils.isEmpty(username)){
                            chattingInfo.put(context.channel(),username);
                        }
                    }
                }


            }
        }



        WebSocketServerHandshakerFactory webSocketServerHandshakerFactory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL, null, false);
        webSocketServerHandshaker = webSocketServerHandshakerFactory.newHandshaker(fullHttpRequest);
        if (webSocketServerHandshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(context.channel());
        } else {
            webSocketServerHandshaker.handshake(context.channel(), fullHttpRequest);
        }
    }

    /**
     * 服务端想客户端发送响应消息
     *
     * @param context
     * @param fullHttpRequest
     * @param defaultFullHttpResponse
     */
    private void sendHttpResponse(ChannelHandlerContext context, FullHttpRequest fullHttpRequest, FullHttpResponse defaultFullHttpResponse) {
        //defaultFullHttpResponse.headers().add(SEC_WEBSOCKET_PROTOCOL,fullHttpRequest.headers().get("Sec-WebSocket-Protocol"));
        if (defaultFullHttpResponse.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(defaultFullHttpResponse.getStatus().toString(), CharsetUtil.UTF_8);
            defaultFullHttpResponse.content().writeBytes(buf);
            buf.release();
        }

        //服务端向客户端发送数据
        ChannelFuture future = context.channel().writeAndFlush(defaultFullHttpResponse);
        if (defaultFullHttpResponse.getStatus().code() != 200) {
            future.addListener(ChannelFutureListener.CLOSE);
        }


    }

    /**
     * @param userName 发送人
     */
    public void sendMessageToChat(String userName, String text) {
        Map map = new HashMap();
        map.put("userName", userName);
        map.put("text", text);
        map.put("number", chattingInfo.size());
        chattingInfo.forEach((channel, user) -> {
            TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(JSONObject.toJSONString(map));
            channel.writeAndFlush(textWebSocketFrame);
        });
    }

     /**
      * 保存聊天记录
     * @param userName 发送人
     */
    public void saveChat(String userName, String text) {
        RemoteChatService remoteChatService = SpringUtil.getBean("remoteChatService");
        remoteChatService.saveChatRecord(System.currentTimeMillis(),userName,text);

    }



    /**
     * k线历史数据
     *
     * @param symbol
     * @param resolution
     * @param to
     * @param from
     * @param timeDL
     * @return
     */
    public Map<String, Object> history(String symbol, String resolution, String to, String from, String timeDL) {
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println("币种：" + symbol);

        //图表是否拖动过
        boolean firstLoad = false;
        try {
            int period = 1;
            if ("5".equals(resolution)) {
                period = 5;
            } else if ("15".equals(resolution)) {
                period = 15;
            } else if ("30".equals(resolution)) {
                period = 30;
            } else if ("60".equals(resolution)) {
                period = 60;
            } else if ("1D".equals(resolution)) {
                period = 1440;
            } else if ("1W".equals(resolution)) {
                period = 1400;
            }
            List<ResponseData> list = new ArrayList();
            List<TransactionOrder> dataList = KlineDataUtil.findData(symbol, period, from, to, timeDL);
            if (dataList != null && dataList.size() > 0) {
                dataList.forEach(data -> {
                    ResponseData klineData = new ResponseData();
                    klineData.setClose(data.getEndPrice());
                    klineData.setCount(data.getTransactionCount());
                    klineData.setHigh(data.getMaxPrice());
                    klineData.setLow(data.getMinPrice());
                    klineData.setOpen(data.getStartPrice());
                    klineData.setQuote_vol(data.getTransactionCount());

                    klineData.setType("candle.M5.btcusdt");
                    try {
                        Date date = sdf.parse(data.getTransactionTime());
                        klineData.setSeq(new BigDecimal(date.getTime()));
                        klineData.setId(new BigDecimal(date.getTime() / 1000));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    list.add(klineData);
                });

            }
            //添加最后一个节点
            ResponseData responseData = lastKLine(symbol);
            if(!StringUtils.isEmpty(responseData)){
                list.add(responseData);
            }
            

            list.sort((a, b) -> {
                if (StringUtils.isEmpty(a.getId())) {
                    return 1;
                }
                return a.getId().subtract(b.getId()).compareTo(new BigDecimal(0));
            });
            map.put("data", list);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 补一个最新节点
     */
    public  ResponseData lastKLine(String coinCode) {
        ResponseData responseData = new ResponseData();

        RedisService redisService = hry.util.common.SpringUtil.getBean("redisService");
        String periodLastKLineListStr = redisService.get(coinCode + ":PeriodLastKLineList");
        if (!org.apache.commons.lang3.StringUtils.isEmpty(periodLastKLineListStr)) {
            List<LastKLinePayload> periodList = JSON.parseArray(periodLastKLineListStr, LastKLinePayload.class);
            if (periodList != null) {
                for (LastKLinePayload l : periodList) {

                    responseData.setOpen(l.getPriceOpen());
                    responseData.setLow(l.getPriceLow());
                    responseData.setHigh(l.getPriceHigh());
                    responseData.setCount(l.getAmount());
                    responseData.setClose(l.getPriceLast());
                    responseData.setBase_vol(l.getAmount());
                    responseData.setQuote_vol(l.getVolume());
                    responseData.setSeq(new BigDecimal(l.getTime()));
                    responseData.setId(new BigDecimal(l.getTime()));
                    responseData.setType("node");
                    return responseData;
                }
            }
        }

        return null;
    }


}
