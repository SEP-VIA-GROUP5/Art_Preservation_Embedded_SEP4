package dk.via.sep4;



import dk.via.sep4.models.LoraWanConnection.WebSocketClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class Sep4Application {

	public static void main(String[] args) {
		SpringApplication.run(Sep4Application.class, args);
		WebSocketClient webSocketClient = new WebSocketClient
				("wss://iotnet.cibicom.dk/app?token=vnoUBwAAABFpb3RuZXQuY2liaWNvbS5ka54Zx4fqYp5yzAQtnGzDDUw=");



	}

}
