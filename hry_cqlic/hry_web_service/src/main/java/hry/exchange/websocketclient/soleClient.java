package hry.exchange.websocketclient;

import hry.exchange.websocketclient.clienthandler.MyWebSocketClient;

public class soleClient {

	private static MyWebSocketClient _instance;

	private soleClient() {
	}

	public static MyWebSocketClient getSocketClient() {
		if (_instance == null) {
			synchronized (soleClient.class) {
				if (_instance == null) {
					_instance = MyWebSocketClient.getClient();
				}
			}
		}
		return _instance;
	}

}
