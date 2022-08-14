package hry.app.kline.netty.controller;

import hry.app.kline.netty.SslUtil;
import hry.util.PropertiesUtils;
import hry.util.properties.PropertiesUtil;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.util.StringUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import java.util.concurrent.TimeUnit;

/**
 * 初始化链接时候的组件
 */
public class TradingWebSocketChannelHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel e) throws Exception {
        if(!StringUtils.isEmpty(PropertiesUtils.APP.get("hasWss")) && "true".equals(PropertiesUtils.APP.get("hasWss"))){
            SSLContext sslContext = SslUtil.createSSLContext("JKS","/bin/keystore.jks","china123");
            //SSLEngine 此类允许使用ssl安全套接层协议进行安全通信
            SSLEngine engine = sslContext.createSSLEngine();
            engine.setUseClientMode(false);
            e.pipeline().addLast(new SslHandler(engine));
        }

        e.pipeline().addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS));
        e.pipeline().addLast("http-codec", new HttpServerCodec());
        e.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
        e.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
        e.pipeline().addLast("handler", new TradingWebSockeHandler());
    }
}
