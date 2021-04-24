package src;
import java.net.URI;
import java.nio.ByteBuffer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

// https://github.com/TooTallNate/Java-WebSocket/wiki#client-example
public class WSClient extends WebSocketClient {
	
	public static MessageHandler MH;
	private static Gson g;

	public WSClient(URI serverUri, Draft draft) {
		super(serverUri, draft);
		g = new Gson();
		MH = new MessageHandler();
	}

	public WSClient(URI serverURI) {
		super(serverURI);
		g = new Gson();
		MH = new MessageHandler();
	}
	
	public void status(String key, String value) {
		String clientMessage = "{ \"ClientID\":\"" + AutoM8.client.ClientID.toString() + "\", \"type\": " + 1 + ", \"payload\": { \"key\":\"" + key + "\", \"value\":\"" + value + "\"}}";
		send(clientMessage);
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		String clientString = g.toJson(AutoM8.client);
		String clientMessage = "{ \"type\": " + -1 + ", \"payload\":" + clientString + "}";
		send(clientMessage);
		System.out.println("Connected to AutoM8 Server");
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		System.out.println("closed with exit code " + code + " additional info: " + reason);
	}

	@Override
	public void onMessage(String message) {
		MH.handle(g.fromJson(message, JsonObject.class));
	}

	@Override
	public void onMessage(ByteBuffer message) {
		System.out.println("received ByteBuffer");
	}

	@Override
	public void onError(Exception ex) {
		System.err.println("an error occurred:" + ex);
	}
}