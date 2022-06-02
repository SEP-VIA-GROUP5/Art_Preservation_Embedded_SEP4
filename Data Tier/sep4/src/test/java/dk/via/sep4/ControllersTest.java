package dk.via.sep4;

import dk.via.sep4.models.*;
import dk.via.sep4.repo.BuildingRepository;
import dk.via.sep4.repo.MetricsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Sep4Application.class)
public class ControllersTest {

    @Autowired
    private MetricsRepository repo;
    @Autowired
    private BuildingRepository buildingRep;

    @Test
    public void metricsEquals(){
        CO2 co2 = new CO2(104);
        Humidity humidity = new Humidity(657);
        Temperature temperature = new Temperature(21);

        Metrics metric = new Metrics(co2, humidity, temperature);
        repo.save(metric);

        Metrics foundMetric = repo.findById(metric.getId()).orElseThrow(null);

        assertNotNull(foundMetric);
        assertEquals(metric.getId(), foundMetric.getId());
    }

    @Test
    public void buildingEquals(){
        Room room = new Room("PopArt", 45);

        Building building = new Building("Over the Rainbow");
        building.addRoom(room);
        buildingRep.save(building);

        Building foundBuilding = buildingRep.findById(building.getId()).orElseThrow();

        assertNotNull(foundBuilding);
        assertEquals(building.getId(), foundBuilding.getId());
    }
}

