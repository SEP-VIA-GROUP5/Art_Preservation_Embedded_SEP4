package dk.via.sep4.services.norms;

import com.google.gson.Gson;
import dk.via.sep4.LoraWanConnection.SendDownLink;
import dk.via.sep4.LoraWanConnection.WebSocketClient;
import dk.via.sep4.exceptions.CO2NotFoundException;
import dk.via.sep4.exceptions.HumidityNotFoundException;
import dk.via.sep4.exceptions.TemperatureNotFoundException;
import dk.via.sep4.models.CO2;
import dk.via.sep4.models.Humidity;
import dk.via.sep4.models.Norms;
import dk.via.sep4.models.Temperature;
import dk.via.sep4.repo.CO2Repository;
import dk.via.sep4.repo.HumidityRepository;
import dk.via.sep4.repo.TemperatureRepository;

public class NormsService implements INormsService {
    Gson gson ;
    WebSocketClient webSocketClient;

    public NormsService() {
        gson = new Gson();
        webSocketClient = new WebSocketClient
                ("wss://iotnet.cibicom.dk/app?token=vnoUBwAAABFpb3RuZXQuY2liaWNvbS5ka54Zx4fqYp5yzAQtnGzDDUw=");
    }

    @Override
    public void setNorm(Long id, double cO2Max, double humidityMax, double tempMax) {
        Norms norms = new Norms((int) cO2Max, (int) humidityMax, (int) tempMax);
        System.out.println("Test: " + norms.getNorms());

        SendDownLink sendDownLink = new SendDownLink(
                "tx",
                "0004A30B00251001",
                2,
                false,
                norms.getNorms()
        );
        System.out.println("String: " + gson.toJson(sendDownLink, SendDownLink.class));
        webSocketClient.sendDownLink(gson.toJson(sendDownLink, SendDownLink.class));
    }
}
