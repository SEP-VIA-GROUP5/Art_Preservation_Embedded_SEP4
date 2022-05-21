package dk.via.sep4.models.LoraWanConnection;

import com.google.gson.Gson;
import dk.via.sep4.models.sensor.Sensor;
import dk.via.sep4.models.sensor.SensorRepository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * @author $(Alina Chelmus)
 */


public class WebSocketClient implements WebSocket.Listener
{

  private WebSocket server = null;
  private Gson gson = new Gson();
  private SensorRepository sensorRepository;
  Sensor sensorToData;
  HexaConverter convertorHex;


  public WebSocket getServer(){
    return server;
  }
  public void DataReceivedMessage(String jsonTelegram)
  {
    server.sendText(jsonTelegram, true);
  }
  public WebSocketClient(String url) {

  HttpClient client = HttpClient.newHttpClient();
  CompletableFuture<WebSocket> ws = client.newWebSocketBuilder().
  buildAsync(URI.create(url), this);

  server = ws.join();
  }
    // onOpen
  public void onOpen(WebSocket webSocket){
  webSocket.request(1);
  System.out.println("WebSocket is open for request");
  }

  //onError
  public void onError(WebSocket webSocket, Throwable error){
  }

  // onClose
  public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
    System.out.println("WebSocket closed!");
    System.out.println("Status:" + statusCode + " Reason: " + reason);
    return new CompletableFuture().completedFuture("onClose() completed.").thenAccept(System.out::println);
  }

  //onPing()
  public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
    webSocket.request(1);
    System.out.println("Ping: Client ---> Server");
    System.out.println(message.asCharBuffer().toString());
    return new CompletableFuture().completedFuture("Ping completed.").thenAccept(System.out::println);
  }

  //onPong()
  public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
    webSocket.request(1);
    System.out.println("Pong: Client ---> Server");
    System.out.println(message.asCharBuffer().toString());
    return new CompletableFuture().completedFuture("Pong completed.").thenAccept(System.out::println);
  }

  //onText()
  public CompletionStage<?> onText​(WebSocket webSocket, CharSequence data, boolean last) {
    try{
      DataReceivedMessage indented =  gson.fromJson(data.toString(), DataReceivedMessage.class);
      System.out.println("OnText");
      System.out.println(indented.toString());
      webSocket.request(1);
      return new CompletableFuture().completedFuture("onText() completed.").thenAccept(System.out::println);
    }
    catch (Exception e)
    {

    }
    return null;

  };






}
