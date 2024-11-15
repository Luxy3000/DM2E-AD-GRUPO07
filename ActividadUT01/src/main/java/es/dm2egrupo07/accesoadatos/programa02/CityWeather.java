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
    /** Nombre de la ciudad. */
    private String name;

    /** ID de la ciudad. */
    private String id;

    /** Lista de temperaturas registradas en la ciudad. */
    private List<Double> temperatures = new ArrayList<>();

    /** Lista de humedades registradas en la ciudad. */
    private List<Double> humidities = new ArrayList<>();

    /** Lista de presiones registradas en la ciudad. */
    private List<Double> pressures = new ArrayList<>();

    /**
     * Constructor que inicializa la ciudad con un nombre y un ID.
     *
     * @param id   ID de la ciudad.
     * @param name Nombre de la ciudad.
     */
    public CityWeather(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Añade un valor de temperatura a la lista.
     *
     * @param temp Valor de la temperatura.
     */
    public void addTemperature(double temp) {
        temperatures.add(temp);
    }

    /**
     * Añade un valor de humedad a la lista.
     *
     * @param hum Valor de la humedad.
     */
    public void addHumidity(double hum) {
        humidities.add(hum);
    }

    /**
     * Añade un valor de presión a la lista.
     *
     * @param pressure Valor de la presión.
     */
    public void addPressure(double pressure) {
        pressures.add(pressure);
    }
}