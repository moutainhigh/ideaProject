package hry.exchange.websocketclient.clienthandler;

import hry.util.properties.PropertiesUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

import java.net.URI;
import java.nio.channels.UnsupportedAddressTypeException;

public class MyWebSocketClient {
	private String url =PropertiesUtils.APP.getProperty("app.socketUrl")
			+ "/websocket";
//	private String url ="ws://127.0.0.1:6062/hurong_socketserver/chatsocket";
	private EventLoopGroup group;
	private Channel channel;

	public MyWebSocketClient() {

	}

	public static MyWebSocketClient getClient() {
		return new MyWebSocketClient();
	}

	public void connect() throws Exception {
		if (url == null || "".equals(url.trim())) {
			throw new NullPointerException("警告这个url 不能为空");
		}
		URI uri = new URI(url);
		String scheme = uri.getScheme() == null ? "http" : uri.getScheme();
		final String host = uri.getHost() == null ? "127.0.0.1" : uri.getHost();
		final int port;
		if (uri.getPort() == -1) {
			if ("http".equalsIgnoreCase(scheme)) {
				port = 80;
			} else if ("https".equalsIgnoreCase(scheme)) {
				port = 443;
			} else {
				port = -1;
			}
		} else {
			port = uri.getPort();
		}

		if (!"ws".equalsIgnoreCase(scheme) && !"wss".equalsIgnoreCase(scheme)) {
			System.err.println("只有ws或wss协议才被支持");
			throw new UnsupportedAddressTypeException();
		}
		final boolean ssl = "wss".equalsIgnoreCase(scheme);
		final SslContext sslCtx;
		if (ssl) {
			sslCtx = SslContextBuilder.forClient()
					.trustManager(InsecureTrustManagerFactory.INSTANCE).build();
		} else {
			sslCtx = null;
		}
		group = new NioEventLoopGroup();
		try {
			final WebSocketClientHandler handler = new WebSocketClientHandler(
					WebSocketClientHandshakerFactory.newHandshaker(uri,
							WebSocketVersion.V13, null, false,
							new DefaultHttpHeaders())) {
				@Override
				public void onReceive(String msg) {
					// 接收到消息执行此处
					// System.out.println("channel获取消息：" + msg);
				}
			};
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) {
							ChannelPipeline p = ch.pipeline();
							if (sslCtx != null) {
								p.addLast(sslCtx.newHandler(ch.alloc(), host,
										port));
							}
							p.addLast(new HttpClientCodec(),
									new HttpObjectAggregator(8192), handler);
						}
					});

			channel = b.connect(uri.getHost(), port).sync().channel();
			// ChannelFuture f = channel.closeFuture().await();
			handler.handshakeFuture().sync();
		} catch (Exception e) {
			this.cancel();
			throw e;
		}
	}

	/**
	 * 向服务器发送消息
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @author: Wu Shuiming
	 * @param: @param s
	 * @return: void
	 * @Date : 2016年4月21日 下午3:26:11
	 * @throws:
	 */
	public void sendMassage(String s) {
		if (!isAlive())
			try {
				this.connect();
			} catch (Exception e) {
				// System.out.println("连接报错  socket 服务报错  ");
				throw new ChannelException("sorry :  这个连接掉线了 ");
			}

		this.channel.writeAndFlush(new TextWebSocketFrame(s));
	}

	/**
	 * 注销客户端
	 */
	public void cancel() {
		if (group != null)
			group.shutdownGracefully();
	}

	/**
	 * 判断客户端是否保持激活状态
	 * 
	 * @return
	 */
	public boolean isAlive() {
		return this.channel != null && this.channel.isActive() ? true : false;
	}

}
