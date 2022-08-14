package hry.exchange.websocketclient;

import hry.exchange.websocketclient.clienthandler.WebSocketClientAll;

public class CopyOfindexClient {
	private static WebSocketClientAll _instance = null;

	private CopyOfindexClient() {
	}

	public static WebSocketClientAll getSocketClient(String name) {
		if (_instance == null) {
			synchronized (CopyOfindexClient.class) {
				if (_instance == null) {
					_instance = WebSocketClientAll.getClient(name);
				}
			}
		}
		return _instance;
	}
}
