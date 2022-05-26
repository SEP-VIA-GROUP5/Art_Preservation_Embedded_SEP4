

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CompletableFuture;


public class WebsocketClient implements WebSocket.Listener {
    private WebSocket server = null;
    private Gson gson;

    // Send down-link message to device
    // Must be in Json format according to https://github.com/ihavn/IoT_Semester_project/blob/master/LORA_NETWORK_SERVER.md
    public void sendDownLink(String jsonTelegram) {
        server.sendText(jsonTelegram, true);
    }

    // E.g. url: "wss://iotnet.teracom.dk/app?token=??????????????????????????????????????????????="
    // Substitute ????????????????? with the token you have been given
    public WebsocketClient(String url) {
        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<WebSocket> ws = client.newWebSocketBuilder()
                .buildAsync(URI.create(url), this);
        server = ws.join();
        gson = new Gson();
    }

    //onOpen()
    public void onOpen(WebSocket webSocket) {
        // This WebSocket will invoke onText, onBinary, onPing, onPong or onClose methods on the associated listener (i.e. receive methods) up to n more times
        webSocket.request(1);
        System.out.println("WebSocket Listener has been opened for requests.");
    }

    //onError()
    public void onError​(WebSocket webSocket, Throwable error) {
        System.out.println("A " + error.getCause() + " exception was thrown.");
        System.out.println("Message: " + error.getLocalizedMessage());
        webSocket.abort();
    }

    ;

    //onClose()
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        System.out.println("WebSocket closed!");
        System.out.println("Status:" + statusCode + " Reason: " + reason);
        return new CompletableFuture().completedFuture("onClose() completed.").thenAccept(System.out::println);
    }

    ;

    //onPing()
    public CompletionStage<?> onPing​(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        System.out.println("Ping: Client ---> Server");
        byte[] bytes = message.array();
        StringBuilder messageString = new StringBuilder();
        for (byte byteInArray : bytes) {
            messageString.append(byteInArray);
        }
        System.out.println("Send back: " + messageString);
        webSocket.sendBinary(message, true);
        return new CompletableFuture().completedFuture("Ping completed.").thenAccept(System.out::println);
    }

    ;

    //onPong()
    public CompletionStage<?> onPong​(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        //System.out.println("Pong: Client ---> Server");
        //System.out.println(message.asCharBuffer().toString());
        return new CompletableFuture().completedFuture("Pong completed.").thenAccept(System.out::println);
    }

    ;

    //onText()
    public CompletionStage<?> onText​(WebSocket webSocket, CharSequence data, boolean last) {
        try {
            DataReceivedMessage indented = gson.fromJson(data.toString(), DataReceivedMessage.class);
            System.out.println("OnText");
            System.out.println(indented.toString());
            webSocket.request(1);
            String CO2 = indented.getData().substring(0, 4);
            int decimal = Integer.parseInt(CO2, 16);
            indented.setCO2((double) decimal / 10);
            String humidity = indented.getData().substring(4, 9);
            int decimal1 = Integer.parseInt(humidity, 16);
            indented.setHumidity((double) decimal1 / 10);
            String temp = indented.getData().substring(9);
            int decimal2 = Integer.parseInt(temp, 16);
            indented.setTemperature((double) decimal2 / 10);
            System.out.println("CO2: " + indented.getCO2() + " Humidity: " + indented.getHumidity() + " Temp: " + indented.getTemperature());
            DownLinkMessage downLinkMessage = new DownLinkMessage(
                    indented.getData(),
                    "tx",
                    indented.getEUI(),
                    2,
                    true);
            sendDownLink(gson.toJson(downLinkMessage, DownLinkMessage.class));
            return new CompletableFuture().completedFuture("onText() completed.").thenAccept(System.out::println);
        } catch (Exception e) {

        }
        return null;

    }

    ;
}
