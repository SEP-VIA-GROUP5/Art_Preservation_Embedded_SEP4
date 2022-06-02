package dk.via.sep4.LoraWanConnection;

/**
 * @author $(Alina Chelmus)
 */
public class DataReceivedMessage {
  private String cmd;
  private String EUI;
  private long ts;
  private boolean ack;
  private int fcnt;
  private int port;
  private String encdata;
  private String data;
  private int freq;
  private String dr;
  private int rssi;
  private double snr;

  public DataReceivedMessage(String cmd, String EUI, long ts, boolean ack, int fcnt, int port, String encdata, String data, int freq, String dr, int rssi, double snr) {
    this.cmd = cmd;
    this.EUI = EUI;
    this.ts = ts;
    this.ack = ack;
    this.fcnt = fcnt;
    this.port = port;
    this.encdata = encdata;
    this.data = data;
    this.freq = freq;
    this.dr = dr;
    this.rssi = rssi;
    this.snr = snr;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "Service.DataReceivedMessage{" +
        "cmd='" + cmd + '\'' +
        ", EUI='" + EUI + '\'' +
        ", ts=" + ts +
        ", ack=" + ack +
        ", fcnt=" + fcnt +
        ", port=" + port +
        ", encdata='" + encdata + '\'' +
        ", data='" + data + '\'' +
        ", freq=" + freq +
        ", dr='" + dr + '\'' +
        ", rssi=" + rssi +
        ", snr=" + snr +
        '}';
  }
}
