package dk.via.sep4.models.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author $(Alina Chelmus)
 */


public class ClientConnection implements Runnable
{
  private final int PORT= 9450;
  private ServerSocket socket;

  public ClientConnection() throws IOException
  {
    socket= new ServerSocket(PORT);
  }

  @Override public void run()
  {
    while (true){
      try
      {
        Socket s= socket.accept();
        ClientHandler clientHandler= new ClientHandler(s);
        Thread thread= new Thread(clientHandler);
        thread.setDaemon(true);
        thread.start();
      }
      catch (IOException e){
        e.printStackTrace();
      }
    }
  }
}
