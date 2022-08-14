package hry.exchange.websocketclient;

import hry.exchange.websocketclient.clienthandler.MyWebSocketClient3;

public class indexClient {
	private static MyWebSocketClient3 _instance = null;

	private indexClient() {
	}

	public static MyWebSocketClient3 getSocketClient() {
		if (_instance == null) {
			synchronized (indexClient.class) {
				if (_instance == null) {
					_instance = MyWebSocketClient3.getClient();
				}
			}
		}
		return _instance;
	}
}
