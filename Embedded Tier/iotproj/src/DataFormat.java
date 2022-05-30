import java.util.Arrays;

public class DataFormat
{
  private byte[]bytes = new byte[5];
  private int CO2;
  private int HUM;
  private int TEMP;

  public DataFormat(int co2, int hum, int temp)
  {
    this.CO2 = co2;
    this.HUM = hum;
    this.TEMP = temp;
  }

  public String toBytes()
  {
    bytes[0] = (byte) (CO2 >> 8);
    bytes[1] = (byte) (CO2 & 255);
    bytes[2] = (byte) (HUM);
    bytes[3] = (byte) (TEMP >> 8);
    bytes[4] = (byte) (TEMP & 255);

    return Arrays.toString(bytes);
  }
}
