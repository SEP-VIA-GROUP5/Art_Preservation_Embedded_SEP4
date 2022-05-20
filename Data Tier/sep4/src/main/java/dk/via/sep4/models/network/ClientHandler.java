package dk.via.sep4.models.network;

import com.google.gson.Gson;
import dk.via.sep4.SpringConfiguration;
import dk.via.sep4.models.Sensor.SensorRepository;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author $(Alina Chelmus)
 */


public class ClientHandler implements Runnable
{
  private Socket socket;
  private InputStream inputStream;
  private DataOutputStream outputStream;
  private Gson gson;
  private SensorRepository sensorRepository;


  public ClientHandler(Socket socket) throws IOException {
    this.socket = socket;
    inputStream = this.socket.getInputStream();
    outputStream = new DataOutputStream(socket.getOutputStream());
    this.gson = new Gson();
    sensorRepository = (SensorRepository) SpringConfiguration.contextProvider().getApplicationContext().getBean("sensorRepository");
  }

  @Override
  public void run() {

    while (true) {
      try {
        String message= readData();
        System.out.println("Client > " + message);
        NetworkPackage incoming = gson.fromJson(message, NetworkPackage.class);

      } catch (Exception e) {
        System.out.println("Client disconnected");
        e.printStackTrace();
        break;
      }
    }
  }



  private void sendData(String response) throws IOException
  {
    byte[] toSendBytes = response.getBytes();
    int toSendLen = toSendBytes.length;
    byte[] toSendLenBytes = new byte[4];
    toSendLenBytes[0] = (byte) (toSendLen & 0xff);
    toSendLenBytes[1] = (byte) ((toSendLen >> 8) & 0xff);
    toSendLenBytes[2] = (byte) ((toSendLen >> 16) & 0xff);
    toSendLenBytes[3] = (byte) ((toSendLen >> 24) & 0xff);
    outputStream.write(toSendLenBytes);
    outputStream.write(toSendBytes);
  }

  public String readData() throws IOException {
    byte[] lenBytes = new byte[4];
    inputStream.read(lenBytes, 0, 4);
    int len = (((lenBytes[3] & 0xff) << 24) | ((lenBytes[2] & 0xff) << 16) |
        ((lenBytes[1] & 0xff) << 8) | (lenBytes[0] & 0xff));
    byte[] receivedBytes = new byte[len];
    inputStream.read(receivedBytes, 0, len);

    return new String(receivedBytes, 0, len);
  }


}
