package dk.via.sep4.LoraWanConnection;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author $(Alina Chelmus)
 */
public class SendDownLink
{
  @JsonProperty
  private final String cmd = "tx";
  @JsonProperty
  private final String EUI = "0004A30B00F398F2";
  @JsonProperty
  private final int port = 2;
  @JsonProperty
  private boolean confirmed;
  @JsonProperty
  private String data;

  public SendDownLink(boolean confirmed, String data) {
    this.confirmed = confirmed;
    this.data = data;
  }

  public String getCmd()
  {
    return cmd;
  }

  public String getEUI()
  {
    return EUI;
  }

  public int getPort()
  {
    return port;
  }

  public boolean isConfirmed()
  {
    return confirmed;
  }

  public void setConfirmed(boolean confirmed)
  {
    this.confirmed = confirmed;
  }

  public String getData()
  {
    return data;
  }

  public void setData(String data)
  {
    this.data = data;
  }



}
