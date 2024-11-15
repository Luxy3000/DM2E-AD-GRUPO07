package es.dm2egrupo07.accesoadatos.programa01;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Clase creada para almacenar los resultados estadísticos de cada ciudad.
 * Contiene los valores promedio, máximo y mínimo de temperatura,
 * así como la humedad y presión promedio.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CityStatistics {
    /** Nombre de la ciudad. */
    private String cityName;

    /** Temperatura promedio de la ciudad. */
    private double avgTemperature;

    /** Temperatura mínima registrada en la ciudad. */
    private double minTemperature;

    /** Temperatura máxima registrada en la ciudad. */
    private double maxTemperature;

    /** Humedad promedio de la ciudad. */
    private double avgHumidity;

    /** Presión atmosférica promedio de la ciudad. */
    private double avgPressure;
}