package es.dm2egrupo07.accesoadatos.programa02;

import java.util.*;

/**
 * Clase creada para calcular las estadísticas
 */
public class WeatherStatistics {

    /**
     * Calcula las estadísticas para cada ciudad y las devuelve como una lista de CityStatistics.
     *
     * @param cities Lista de objetos CityWeather.
     * @return Lista de objetos CityStatistics con las estadísticas calculadas.
     */
    public static List<CityStatistics> calculateStatistics(List<CityWeather> cities){
        List<CityStatistics> statisticsList = new ArrayList<>();

        for(CityWeather city : cities){
            double avgTemperature = calculateAverage(city.getTemperatures());
            double minTemperature = calculateMin(city.getTemperatures());
            double maxTemperature = calculateMax(city.getTemperatures());
            double avgHumidity = calculateAverage(city.getHumidities());
            double avgPressure = calculateAverage(city.getPressures());

            CityStatistics stats = new CityStatistics(city.getName(), avgTemperature, minTemperature, maxTemperature, avgHumidity, avgPressure);

            statisticsList.add(stats);
        }
        return statisticsList;
    }

    private static double calculateMin(List<Double> values){
        double min = Double.MAX_VALUE;
        for(double value : values){
            if(value < min) {
                min = value;
            }
        }
        return min;
    }

    private static double calculateMax(List<Double> values){
        double max = Double.MIN_VALUE;
        for (double value : values){
            if(value > max){
                max = value;
            }
        }
        return max;
    }

    private static double calculateAverage(List<Double> values){
        double sum = 0;
        for(double value : values){
            sum += value;
        }
        return values.isEmpty() ? 0 : sum / values.size();
    }
}
