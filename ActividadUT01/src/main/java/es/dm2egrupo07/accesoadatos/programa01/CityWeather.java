package es.dm2egrupo07.accesoadatos.programa01;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase creada para manejar los datos de una ciudad
 */
@AllArgsConstructor
@Getter
public class CityWeather {
    /* Seguir editando según se necesite para las otras clases */
    private String name;
    private String id;
    private List<Double> temperatures;
    private List<Double> humidities;
    private List<Double> pressures;

    public CityWeather(String id, String name) {
        this.id = id;
        this.name = name;
        this.temperatures = new ArrayList<>();
        this.humidities = new ArrayList<>();
        this.pressures = new ArrayList<>();
    }

    public void addTemperature(double temp) {
        temperatures.add(temp);
    }

    public void addHumidity(double hum) {
        humidities.add(hum);
    }

    public void addPressure(double p) {
        pressures.add(p);
    }
}