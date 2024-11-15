package es.dm2egrupo07.accesoadatos.programa02;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

/**
 * Clase creada para manejar los datos de una ciudad
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityWeather {
    private String name;
    private String id;
    private List<Double> temperatures;
    private List<Double> humidities;
    private List<Double> pressures;

    public CityWeather(String id, String name){
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
