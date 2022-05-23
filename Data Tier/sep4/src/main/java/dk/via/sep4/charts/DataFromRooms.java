package dk.via.sep4.charts;



import dk.via.sep4.models.Metrics;

import java.util.Set;

public class DataFromRooms {
    private long IdRoom;
    private String nameRoom;
    private Set<Metrics> sensors;

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

    public Set<Metrics> getListOfSensors(){
        return sensors;
    }

    public void setListOfSensors(Set<Metrics> listOfSensors){
        this.sensors = listOfSensors;
    }
}
