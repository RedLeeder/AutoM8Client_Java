package src;

import com.google.gson.*;

public class MessageHandler {
	
	public MessageHandler() {
		
	}
	
	public void handle(JsonObject m) {
//		System.out.println(m.toString());
		switch (m.get("type").getAsInt()) {
			case 0: // Receive Registration Response
				AutoM8.client.LinkCode = m.get("payload").getAsJsonObject().get("LinkCode").getAsString();
				break;
			case 6: // Shutdown Signal
				AutoM8.exit();
				break;
			case 7: // Start Recording
				AutoM8.SR.enableRecording(m.get("nextId").getAsInt());
				break;
		}
	}
}
