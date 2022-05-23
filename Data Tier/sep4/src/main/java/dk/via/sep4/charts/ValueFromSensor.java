package dk.via.sep4.charts;

public class ValueFromSensor {


  double value;
  long timestamp;


  public ValueFromSensor(double value, long timestamp){
    this.value = value;
    this.timestamp = timestamp;
  }

  public double getValue()
  {
    return value;
  }

  public void setValue(double value)
  {
    this.value = value;
  }

  public long getTimestamp()
  {
    return timestamp;
  }

  public void setTimestamp(long timestamp)
  {
    this.timestamp = timestamp;
  }
}
