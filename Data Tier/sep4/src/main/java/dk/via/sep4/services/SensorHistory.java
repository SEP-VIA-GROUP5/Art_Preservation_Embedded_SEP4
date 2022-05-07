package dk.via.sep4.services;

import dk.via.sep4.models.charts.DataToSendHistory;

/**
 * @author $(Alina Chelmus)
 */
public class SensorHistory
{

    public SensorHistory(){


    }
    public DataToSendHistory getSensorsHistoryByPeriod(long id, int period) {
        DataToSendHistory sendHistories = new DataToSendHistory();
        return sendHistories;
    }
}
