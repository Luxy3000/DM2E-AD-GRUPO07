package es.dm2egrupo07.accesoadatos.programa01;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Clase creada para almacenar lso resultados de cada ciudad
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CityStatistics {
    private String cityName;
    private double avgTemperature;
    private double minTemperature;
    private double maxTemperature;
    private double avgHumidity;
    private double avgPressure;
}
