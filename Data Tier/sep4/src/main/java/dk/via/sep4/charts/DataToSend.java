package dk.via.sep4.charts;

import java.util.List;

public class DataToSend {
    private List<DataFromRooms> data;

    public DataToSend() {
    }

    public List<DataFromRooms> getData()
    {
        return data;
    }

    public void setData(List<DataFromRooms> data) {
        this.data = data;
    }
}
