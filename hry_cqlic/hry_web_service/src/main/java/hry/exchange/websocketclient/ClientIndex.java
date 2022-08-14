package hry.exchange.websocketclient;

import hry.exchange.websocketclient.clienthandler.WebSocketClient4;

public class ClientIndex {
	private static WebSocketClient4 _instance = null;

	private ClientIndex() {
	}

	public static WebSocketClient4 getSocketClient() {
		if (_instance == null) {
			synchronized (ClientIndex.class) {
				if (_instance == null) {
					_instance = WebSocketClient4.getClient();
				}
			}
		}
		return _instance;
	}
}
