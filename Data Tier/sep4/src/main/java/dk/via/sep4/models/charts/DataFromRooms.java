package dk.via.sep4.models.charts;

import sun.management.Sensor;

import java.util.Set;

public class DataFromRooms {
    private long IdRoom;
    private String nameRoom;
    private Set<Sensor> sensors;

    public DataFromRooms() {

    }

    public long getIdRoom(){
        return  IdRoom;
    }

    public void setIdRoom(long IdRoom){
        this.IdRoom = IdRoom;
    }

    public String getNameRoom(){
        return nameRoom;
    }

    public void setNameRoom(String nameRoom){
        this.nameRoom = nameRoom;
    }

    public Set<Sensor> getListOfSensors(){
        return sensors;
    }

    public void setListOfSensors(Set<Sensor> listOfSensors){
        this.sensors = listOfSensors;
    }
}
