package dk.via.sep4.LoraWanConnection;

import com.google.gson.Gson;
import dk.via.sep4.models.Metrics;
import dk.via.sep4.models.Room;
import dk.via.sep4.repo.MetricsRepository;
import dk.via.sep4.repo.RoomRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
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

  private WebSocket server;
  private Gson gson = new Gson();
  private MetricsRepository repo;
  Metrics metricsDB;
  HexaConverter convertorHex;
  private RoomRepository roomRepository;
  private Room roomDB;


  public WebSocket getServer()
  {
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
      String indented = null;
    try{
      indented = (new JSONObject(data.toString())).toString(4);
      DataReceivedMessage dataReceivedMessage =  gson.fromJson(indented, DataReceivedMessage.class);
      metricsDB= convertorHex.convertFromHexaToInt(dataReceivedMessage);
      repo.save(metricsDB);
      long id= 1;
      roomDB = roomRepository.getById(id);
      roomDB.addMetrics(metricsDB);
      roomRepository.save(roomDB);
      System.out.println("OnText");
      System.out.println(indented);

    }
    catch (JSONException e)
    {
      e.printStackTrace();

    }

    webSocket.request(1);
    return new CompletableFuture().completedFuture("onText() completed.").thenAccept(System.out::println);
  }





}
