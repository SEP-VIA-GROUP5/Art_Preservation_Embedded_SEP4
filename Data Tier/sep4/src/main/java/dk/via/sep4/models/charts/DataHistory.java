package dk.via.sep4.models.charts;

import dk.via.sep4.models.Sensor.SensorModel;

import java.util.List;

public class DataHistory {

  private  SensorModel sensorModel;
  private List<ValueFromSensor> valueSensors;
  private long roomId;
  private long sensorId;


  public DataHistory(){

  }

  public long getRoomId()
  {
    return roomId;
  }

  public void setRoomId(long roomId)
  {
    this.roomId = roomId;
  }

  public List<ValueFromSensor> getValueSensors()
  {
    return valueSensors;
  }

  public void setValueFromSensors(List<ValueFromSensor> valueSensors)
  {
    this.valueSensors = valueSensors;
  }

  public long getSensorId()
  {
    return sensorId;
  }

  public void setSensorId(long sensorId)
  {
    this.sensorId = sensorId;
  }

  public SensorModel getModelName(){
    return sensorModel;
  }
  public void setModelName(SensorModel sensorModel){
    this.sensorModel = sensorModel;
  }
}
