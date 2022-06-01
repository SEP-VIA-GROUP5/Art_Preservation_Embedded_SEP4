package dk.via.sep4;

import com.google.gson.Gson;
import dk.via.sep4.LoraWanConnection.DataToSend;
import dk.via.sep4.LoraWanConnection.SendDownLink;
import dk.via.sep4.LoraWanConnection.WebSocketClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class
Sep4Application {
	public static void main(String[] args)
	{
		SpringApplication.run(Sep4Application.class, args);
		Gson gson = new Gson();
		WebSocketClient webSocketClient = new WebSocketClient
				("wss://iotnet.cibicom.dk/app?token=vnoUBwAAABFpb3RuZXQuY2liaWNvbS5ka54Zx4fqYp5yzAQtnGzDDUw=");
		while (true) {
			try {
				DataToSend dataToSend = new DataToSend();
				System.out.println("Test: " + dataToSend.getNorms());

				SendDownLink sendDownLink = new SendDownLink(
						"tx",
						"0004A30B00251001",
						2,
						false,
						dataToSend.getNorms()
				);
				System.out.println("String: " + gson.toJson(sendDownLink, SendDownLink.class));
				webSocketClient.sendDownLink(gson.toJson(sendDownLink, SendDownLink.class));
				Thread.sleep(180000);
			}
			catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
