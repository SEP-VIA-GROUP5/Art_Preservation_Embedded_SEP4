import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class DataFormat
{
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
    String hex = String.format("%04x%02x%04x", CO2, HUM, TEMP);
    return hex;
  }
}
