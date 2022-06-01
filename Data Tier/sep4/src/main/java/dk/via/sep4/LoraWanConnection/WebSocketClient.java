package dk.via.sep4.LoraWanConnection;

import com.google.gson.Gson;
import dk.via.sep4.SpringConfiguration;
import dk.via.sep4.models.Metrics;
import dk.via.sep4.models.Room;
import dk.via.sep4.repo.MetricsRepository;
import dk.via.sep4.repo.RoomRepository;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * @author Alina Chelmus
 * @author Timothy Engkar
 */

public class WebSocketClient implements WebSocket.Listener
{

  private WebSocket server;
  private Gson gson = new Gson();
  private final MetricsRepository repo;
  private final RoomRepository roomRep;
  HexaConverter convertorHex = new HexaConverter();


  public WebSocket getServer()
  {
    return server;
  }
  public void sendDownLink(String jsonTelegram)
  {
    server.sendText(jsonTelegram, true);
  }
  public WebSocketClient(String url) {
    HttpClient client = HttpClient.newHttpClient();
    CompletableFuture<WebSocket> ws = client.newWebSocketBuilder().
            buildAsync(URI.create(url), this);
    server = ws.join();
    repo = (MetricsRepository) SpringConfiguration.contextProvider().getApplicationContext().getBean("metricsRepository");
    roomRep = (RoomRepository) SpringConfiguration.contextProvider().getApplicationContext().getBean("roomRepository");
  }

  //onOpen()
  public void onOpen(WebSocket webSocket) {
    webSocket.request(1);
    System.out.println("WebSocket Listener has been opened" +
            " for requests.");
  }

  //onError()
  public void onError(WebSocket webSocket, Throwable error) {
    System.out.println("A " + error.getCause() + " exception was thrown.");
    System.out.println("Message: " + error.getLocalizedMessage());
    webSocket.abort();
  }

  // onClose()
  public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
    System.out.println("WebSocket closed!");
    System.out.println("Status:" + statusCode + " Reason: " + reason);
    return new CompletableFuture().completedFuture("onClose() completed.").thenAccept(System.out::println);
  }

  //onPing()
  public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
    webSocket.request(1);
    System.out.println("Ping: Client ---> Server");
    System.out.println(message.asCharBuffer());
    return new CompletableFuture().completedFuture("Ping completed.").thenAccept(System.out::println);
  }

  //onPong()
  public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
    webSocket.request(1);
    System.out.println("Pong: Client ---> Server");
    System.out.println(message.asCharBuffer());
    return new CompletableFuture().completedFuture("Pong completed.").thenAccept(System.out::println);
  }

  //onText()
  public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
      String indented;

    try{
      indented = (new JSONObject(data.toString())).toString(4);
      DataReceivedMessage dataReceivedMessage =  gson.fromJson(indented, DataReceivedMessage.class);
      Metrics metricsDB = convertorHex.convertFromHexaToInt(dataReceivedMessage);
      repo.save(metricsDB);
    }
    catch (JSONException e)
    {
      e.printStackTrace();

    }

    webSocket.request(1);
    return new CompletableFuture().completedFuture("onText() completed.").thenAccept(System.out::println);
  }

}
