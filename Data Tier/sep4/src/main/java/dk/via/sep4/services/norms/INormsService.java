package dk.via.sep4.services.norms;

public interface INormsService {
    void setNorm(Long id, double cO2Max, double humidityMax, double tempMax);
}
