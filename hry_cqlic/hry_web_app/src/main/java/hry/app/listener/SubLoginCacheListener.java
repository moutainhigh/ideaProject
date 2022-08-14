package hry.app.listener;

import hry.app.kline.netty.config.NettyConfig;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import redis.clients.jedis.JedisPubSub;

/**
 * @author zhouming
 * @date 2020/7/28 15:14
 * @Version 1.0
 */
public class SubLoginCacheListener extends JedisPubSub {
    @Override
    public void onMessage(String channel, String message) {
        NettyConfig.tradingGroup.iterator().forEachRemaining(ca -> {
            // 强制退出
            TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame("FORBIDDEN_"+message);
            ca.writeAndFlush(textWebSocketFrame);
            System.out.println("++++++++:后台禁用推送成功");
        });
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {

    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {

    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {

    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {

    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {

    }
}
