
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
    private double CO2;
    private double humidity;
    private double temperature;

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

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getEUI() {
        return EUI;
    }

    public double getCO2()
    {
        return CO2;
    }

    public double getHumidity()
    {
        return humidity;
    }

    public double getTemperature()
    {
        return temperature;
    }

    public void setCO2(double CO2)
    {
        this.CO2 = CO2;
    }

    public void setHumidity(double humidity)
    {
        this.humidity = humidity;
    }

    public void setTemperature(double temperature)
    {
        this.temperature = temperature;
    }

    public void setEUI(String EUI) {
        this.EUI = EUI;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public boolean isAck() {
        return ack;
    }

    public void setAck(boolean ack) {
        this.ack = ack;
    }

    public int getFcnt() {
        return fcnt;
    }

    public void setFcnt(int fcnt) {
        this.fcnt = fcnt;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getEncdata() {
        return encdata;
    }

    public void setEncdata(String encdata) {
        this.encdata = encdata;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public String getDr() {
        return dr;
    }

    public void setDr(String dr) {
        this.dr = dr;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public double getSnr() {
        return snr;
    }

    public void setSnr(double snr) {
        this.snr = snr;
    }

    @Override
    public String toString() {
        return "{" +
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