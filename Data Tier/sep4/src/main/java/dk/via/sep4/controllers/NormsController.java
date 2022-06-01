package dk.via.sep4.controllers;

import com.google.gson.Gson;
import dk.via.sep4.models.Norms;
import dk.via.sep4.LoraWanConnection.SendDownLink;
import dk.via.sep4.LoraWanConnection.WebSocketClient;
import dk.via.sep4.exceptions.CO2NotFoundException;
import dk.via.sep4.exceptions.HumidityNotFoundException;
import dk.via.sep4.exceptions.TemperatureNotFoundException;
import dk.via.sep4.models.CO2;
import dk.via.sep4.models.Humidity;
import dk.via.sep4.models.Temperature;
import dk.via.sep4.repo.CO2Repository;
import dk.via.sep4.repo.HumidityRepository;
import dk.via.sep4.repo.TemperatureRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NormsController {
    private final TemperatureRepository repo;
    private final HumidityRepository humidityRepository;
    private final CO2Repository co2Repository;

    Gson gson = new Gson();
    WebSocketClient webSocketClient = new WebSocketClient
            ("wss://iotnet.cibicom.dk/app?token=vnoUBwAAABFpb3RuZXQuY2liaWNvbS5ka54Zx4fqYp5yzAQtnGzDDUw=");

    public NormsController(TemperatureRepository repo, HumidityRepository humidityRepository, CO2Repository co2Repository) {
        this.repo = repo;
        this.humidityRepository = humidityRepository;
        this.co2Repository = co2Repository;
    }

    @PutMapping("/norms/{id}")
    Norms setNorm(@PathVariable Long id, @RequestParam double cO2Max, @RequestParam double humidityMax, @RequestParam double tempMax) {
        CO2 newCO2 = co2Repository.findById(id).orElseThrow(
                () -> new CO2NotFoundException(id)
        );
        newCO2.setMax(cO2Max);

        co2Repository.save(newCO2);
        Humidity newHumidity = humidityRepository.findById(id).orElseThrow(
                () -> new HumidityNotFoundException(id)
        );
        newHumidity.setMax(humidityMax);
        humidityRepository.save(newHumidity);

        Temperature newTemperature = repo.findById(id).orElseThrow(
                ()-> new TemperatureNotFoundException(id)
        );
        newTemperature.setMax(tempMax);
        repo.save(newTemperature);

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

        return norms;
    }
}
