package src;
import java.net.URI;
import java.nio.ByteBuffer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import actions.*;

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
		String clientMessage = "{ \"ClientID\":\"" + AutoM8.client.ClientID.toString() + "\", \"type\": 1, \"payload\": { \"key\":\"" + key + "\", \"value\":" + value + "}}";
		send(clientMessage);
	}
	
	public void addJSONAction(String json, int offset) {
		String clientMessage = "{ \"ClientID\":\"" + AutoM8.client.ClientID.toString() + "\", \"type\": 9, \"payload\": " + json + ", \"offset\": " + offset + "}";
		send(clientMessage);
	}
	public void addAction(CLICK a, int offset) { addJSONAction(g.toJson(a), offset); }
	public void addAction(COPY a, int offset) { addJSONAction(g.toJson(a), offset); }
	public void addAction(DELAY a, int offset) { addJSONAction(g.toJson(a), offset); }
	public void addAction(DOWN a, int offset) { addJSONAction(g.toJson(a), offset); }
	public void addAction(DRAG a, int offset) { addJSONAction(g.toJson(a), offset); }
	public void addAction(ENTER a, int offset) { addJSONAction(g.toJson(a), offset); }
	public void addAction(LEFT a, int offset) { addJSONAction(g.toJson(a), offset); }
	public void addAction(PASTE a, int offset) { addJSONAction(g.toJson(a), offset); }
	public void addAction(RIGHT a, int offset) { addJSONAction(g.toJson(a), offset); }
	public void addAction(RIGHTCLICK a, int offset) { addJSONAction(g.toJson(a), offset); }
	public void addAction(SELECTALL a, int offset) { addJSONAction(g.toJson(a), offset); }
	public void addAction(TAB a, int offset) { addJSONAction(g.toJson(a), offset); }
	public void addAction(TYPE a, int offset) { addJSONAction(g.toJson(a), offset); }
	public void addAction(UP a, int offset) { addJSONAction(g.toJson(a), offset); }
	
	public void endRecord() {
		String clientMessage = "{ \"ClientID\":\"" + AutoM8.client.ClientID.toString() + "\", \"type\": 8 }";
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
		System.out.println("Lost connection with AutoM8 Server: " + code + " additional info: " + reason);
		System.out.println("Restart Client to Reconnect");
		System.exit(0);
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