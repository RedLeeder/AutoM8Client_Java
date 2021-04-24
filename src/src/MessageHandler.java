package src;

import com.google.gson.*;

public class MessageHandler {
	
	public MessageHandler() {
		
	}
	
	public void handle(JsonObject m) {
		
		switch (m.get("type").getAsInt()) {
			case 0: // Receive Registration Response
				AutoM8.client.LinkCode = m.get("payload").getAsJsonObject().get("LinkCode").getAsString();
				break;
		}
	}
}
