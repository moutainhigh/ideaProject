package hry.exchange.websocketclient;

import hry.exchange.websocketclient.clienthandler.MyWebSocketClient2;

public class takeClient {
	private static MyWebSocketClient2 _instance = null;

	private takeClient() {
	}

	public static MyWebSocketClient2 getSocketClient() {
		if (_instance == null) {
			synchronized (soleClient.class) {
				if (_instance == null) {
					_instance = MyWebSocketClient2.getClient();
				}
			}
		}
		return _instance;
	}
}
